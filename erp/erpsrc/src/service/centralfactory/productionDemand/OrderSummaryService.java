package service.centralfactory.productionDemand;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ArrangmentHeaderBean;
import logic.form.CollectDetailBean;
import logic.form.CollectHeaderBean;
import logic.form.CollectRefFormBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.module.cf.RequestSummaryBean;
import logic.store.BranchBean;
import logic.store.SupplyCycleBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.CollectDetail;
import pojo.form.CollectHeader;
import pojo.form.CollectRefForm;
import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.store.Branch;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormUtil;
import com.tanry.business.module.lc.request.SummaryUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchCode;

public class OrderSummaryService {

	// 单据状态表
	private FormStatusBean formStatusBean;

	private CollectDetailBean collectDetailBean;
	private CollectHeaderBean collectHeaderBean;

	private PurchasingDetailBean purchasingDetailBean;

	private CollectRefFormBean collectRefFormBean;

	private PurchasingHeaderBean purchasingHeaderBean;

	private BranchBean branchBean;

	private SupplyCycleBean supplyCycleBean;

	private RequestSummaryBean cfRequestSummaryBean;

	private ArrangmentHeaderBean arrangmentHeaderBean;

	private OperationVersionBean operationVersionBean;

	private BigDecimal findAverage(List<Map> itemLst, String itemId) {
		for (Map item : itemLst) {
			if (item.get("itemId").equals(itemId)) {
				return new BigDecimal((Double) item.get("avgCount")).setScale(2, BigDecimal.ROUND_HALF_UP);
			}
		}
		return null;
	}

	/**
	 * 缓存门店的地址，尽可能减少数据库查询
	 */
	private Map<String, String> addressMap;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, List<String>> createSummaryBill(PurchasingHeader commonHeader, String ids, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = commonHeader.getRequesterId();
		List<Map> unifiedLst = cfRequestSummaryBean.querySummary(cfCode, ids);

		Branch cfBranch = branchBean.GetBranchById(cfCode);
		String cfRequester = cfBranch.getBranchName();
		commonHeader.setRequester(cfRequester);
		String cfAddress = cfBranch.getBranchAddress();
		commonHeader.setReceiveAddress(cfAddress);

		addressMap = new HashMap<String, String>();

		String commonFormId = FormUtil.genFormIdBody("CG", cfCode, commonHeader.getFormTime());
		commonHeader.setFormId(commonFormId);
		JSONArray mrpLst = JSONArray.fromObject(jsonData);// 前台数据可能有改动，并且结合了MRP计算，以前台的数据为准
		List<String> unifiedIds = saveHeader(commonHeader,
				SummaryUtil.groupUnified(commonHeader.getFormTime(), false, unifiedLst, mrpLst),
				FormConstant.UNIFIED_DELIVERY);

		// 设置要货单的状态
		String[] requestFormIdArr = ids.split(",");
		for (String requestFormId : requestFormIdArr) {
			arrangmentHeaderBean.savePurchaseStatus(requestFormId, "已下发采购");
		}

		Map<String, List<String>> idMap = new HashMap<String, List<String>>();
		idMap.put("unifiedIds", unifiedIds);
		return idMap;
	}

	/**
	 * 保存采购单表头数据
	 * 
	 * @param commonHeader
	 * @param count
	 * @param billLst
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private List<String> saveHeader(PurchasingHeader commonHeader, List<Map<String, Object>> billLst,
			String deliveryType) throws NoPrivilegeException, SQLException, NoConnection {
		String commonFormId = commonHeader.getFormId();
		Date businessDate = commonHeader.getFormTime();// 获取物流中心当前的营业时间
		String userId = commonHeader.getFormMakerId();
		String formMaker = commonHeader.getFormMaker();
		String cfCode = commonHeader.getRequesterId();
		String cfRequester = commonHeader.getRequester();
		String cfAddress = commonHeader.getReceiveAddress();
		String formNote = commonHeader.getFormNote();

		Integer serial = Integer.parseInt(operationVersionBean.getMaxFormIndex(commonFormId + "%")) + 1;

		// 保存所有的单据号
		List<String> idLst = new ArrayList<String>();
		for (Map<String, Object> bill : billLst) {
			PurchasingHeader pHeader = new PurchasingHeader();
			String pFormId = commonFormId + FormUtil.formatSerial(serial);
			pHeader.setRequesterId(cfCode);
			pHeader.setRequester(cfRequester);

			pHeader.setReceiveAddress(cfAddress);
			pHeader.setStorageId(commonHeader.getStorageId());
			pHeader.setStorage(commonHeader.getStorage());
			serial++;
			pHeader.setFormId(pFormId);
			idLst.add(pFormId);
			pHeader.setDeliveryType(deliveryType);
			pHeader.setFormTime(businessDate);
			String providerId = (String) bill.get("providerId");
			pHeader.setProviderId(providerId);
			pHeader.setProvider((String) bill.get("provider"));
			pHeader.setReceiveTime((Date) bill.get("receiveTime"));
			pHeader.setFormMakerId(userId);
			pHeader.setFormMaker(formMaker);
			pHeader.setFormNote(formNote);

			purchasingHeaderBean.saveEntity(pHeader);
			operationVersionBean.saveEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
					OperationVersion.INIT_VERSION, OperationVersion.SAVED, pFormId));

			List<Map<String, Object>> detailLst = (List<Map<String, Object>>) bill.get("details");
			saveDetail(pFormId, cfCode, detailLst);

			formStatusBean.saveEntity(new FormStatus(pFormId, BillStatus.UNADUIT_CN, userId));
		}
		return idLst;
	}

	private Double getSuggest(Double requestCount, Double allocation, Double _safeCount, Double realCount,
			Double roadCount) {
		Double suggest = requestCount + allocation + _safeCount - realCount - roadCount;
		if (suggest < 0) {
			return 0.0;
		}

		return suggest;
	}

	/**
	 * 查询日均要货量
	 * 
	 * @param startDate1
	 * @param endDate1
	 * @param startDate2
	 * @param endDate2
	 * @param startDate3
	 * @param endDate3
	 * @param jsonData
	 * @return
	 */
	public JSONArray queryAverageRequest(Date startDate1, Date endDate1, Date startDate2, Date endDate2,
			Date startDate3, Date endDate3, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		String[] ids = new String[arr.size()];
		for (int i = 0; i < arr.size(); i++) {
			JSONObject json = (JSONObject) arr.get(i);
			String itemId = json.getString("itemId");
			ids[i] = itemId;
		}

		if (startDate1 != null && endDate1 != null) {
			List<Map> itemLst = cfRequestSummaryBean.queryAverageRequest(startDate1, endDate1, ids);
			int rownumber = 1;
			for (Object obj : arr) {
				JSONObject json = (JSONObject) obj;
				String itemId = json.getString("itemId");
				BigDecimal average1 = findAverage(itemLst, itemId);

				if (average1 != null) {
					json.put("average1", average1);
					Double inventorysCycle = Double.valueOf(json.getDouble("inventorysCycle"));
					Double requestCount = Double.valueOf(json.getDouble("requestCount"));
					Double allocation = Double.valueOf(json.getDouble("allocation"));
					Double realCount = Double.valueOf(json.getDouble("realCount"));
					Double roadCount = Double.valueOf(json.getDouble("roadCount"));

					Double safeCount1 = average1.doubleValue() * inventorysCycle;
					json.put("average1", average1);
					json.put("safeCount1", safeCount1);
					json.put("suggestCount1", getSuggest(requestCount, allocation, safeCount1, realCount, roadCount));
				}

				json.put("rownumber", rownumber);
				rownumber++;
			}
		}

		if (startDate2 != null && endDate2 != null) {
			int rownumber = 1;
			List<Map> itemLst = cfRequestSummaryBean.queryAverageRequest(startDate2, endDate2, ids);
			for (Object obj : arr) {
				JSONObject json = (JSONObject) obj;
				String itemId = json.getString("itemId");
				BigDecimal average2 = findAverage(itemLst, itemId);

				if (average2 != null) {
					json.put("average2", average2);
					Double inventorysCycle = Double.valueOf(json.getDouble("inventorysCycle"));
					Double requestCount = Double.valueOf(json.getDouble("requestCount"));
					Double allocation = Double.valueOf(json.getDouble("allocation"));
					Double realCount = Double.valueOf(json.getDouble("realCount"));
					Double roadCount = Double.valueOf(json.getDouble("roadCount"));

					Double safeCount2 = average2.doubleValue() * inventorysCycle;
					json.put("average2", average2);
					json.put("safeCount2", safeCount2);
					json.put("suggestCount2", getSuggest(requestCount, allocation, safeCount2, realCount, roadCount));
				}

				json.put("rownumber", rownumber);
				rownumber++;
			}
		}

		if (startDate3 != null && endDate3 != null) {
			int rownumber = 1;
			List<Map> itemLst = cfRequestSummaryBean.queryAverageRequest(startDate3, endDate3, ids);
			for (Object obj : arr) {
				JSONObject json = (JSONObject) obj;
				String itemId = json.getString("itemId");

				BigDecimal average3 = findAverage(itemLst, itemId);
				if (average3 != null) {
					json.put("average3", average3);
					Double inventorysCycle = Double.valueOf(json.getDouble("inventorysCycle"));
					Double requestCount = Double.valueOf(json.getDouble("requestCount"));
					Double allocation = Double.valueOf(json.getDouble("allocation"));
					Double realCount = Double.valueOf(json.getDouble("realCount"));
					Double roadCount = Double.valueOf(json.getDouble("roadCount"));

					Double safeCount3 = average3.doubleValue() * inventorysCycle;
					json.put("average3", average3);
					json.put("safeCount3", safeCount3);
					json.put("suggestCount3", getSuggest(requestCount, allocation, safeCount3, realCount, roadCount));
				}

				json.put("rownumber", rownumber);
				rownumber++;
			}
		}
		return arr;
	}

	public Map<String, Object> querySummaryBill(String cfCode, Date businessDate, String ids, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> unifiedLst = cfRequestSummaryBean.querySummary(cfCode, ids);

		Map<String, Object> summary = new HashMap<String, Object>();
		JSONArray arr = JSONArray.fromObject(jsonData);// 前台数据可能有改动，并且结合了MRP计算，以前台的数据为准

		summary.put("unified2", SummaryUtil.groupUnified(businessDate, true, unifiedLst, arr));
		summary.put("noProvider", getNoProviderItems(arr, unifiedLst));
		return summary;
	}

	/**
	 * 
	 * @param arr
	 *            页面传递过来的数据
	 * @param unifiedLst
	 *            数据库中查询的原始数据
	 * @return
	 */
	private String getNoProviderItems(JSONArray arr, List<Map> unifiedLst) {

		StringBuilder sb = new StringBuilder();

		// 获取订货数量大于零的物料(处理过的物料)
		for (int i = 0; i < arr.size(); i++) {
			JSONObject jsonObject = arr.getJSONObject(i);
			int orderCount = jsonObject.getInt("orderCount");
			String itemId = jsonObject.getString("itemId");
			String itemName = jsonObject.getString("itemName");

			if (orderCount != 0) {
				for (int j = 0; j < unifiedLst.size(); j++) {
					Map map = unifiedLst.get(j);
					if (map.get("itemId").toString().equals(itemId)) {
						if (map.get("providerId") == null) {
							sb.append("[").append(itemId).append("]").append(itemName).append(",");
							break;
						}
					} else {
						continue;
					}
				}
			}
		}

		String resultStr = sb.toString();
		if (!TextUtil.isEmpty(resultStr)) {
			int index = resultStr.lastIndexOf(",");
			resultStr = resultStr.substring(0, index);
		}
		return resultStr;
	}

	/**
	 * 保存采购单明细数据
	 * 
	 * @param pFormId
	 * @param detailLst
	 * @return
	 */
	private int saveDetail(String pFormId, String cfCode, List<Map<String, Object>> detailLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		int maxCycle = 0;
		for (Map<String, Object> detail : detailLst) {
			int cycle = 0;
			String itemId = (String) detail.get("itemId");

			PurchasingDetail pDetail = new PurchasingDetail();

			List<Map> mapList = supplyCycleBean.queryItemById(cfCode, itemId);
			if (mapList != null) {
				try {
					Map map = mapList.get(0);
					cycle = Integer.valueOf(map.get("scycle").toString());
				} catch (Exception e) {
					cycle = 0;
				}
			}

			if (cycle > maxCycle) {
				maxCycle = cycle;
			}

			pDetail.setFormId(pFormId);
			pDetail.setItemId(itemId);
			pDetail.setItemCategory((String) detail.get("itemCategory"));
			pDetail.setItemName((String) detail.get("itemName"));
			pDetail.setItemDimension((String) detail.get("itemDimension"));
			pDetail.setItemSpecification((String) detail.get("itemSpecification"));
			pDetail.setItemCount((Double) detail.get("orderCount"));
			pDetail.setItemUnitPrice((Double) detail.get("itemUnitPrice"));
			pDetail.setReceivePrice((Double) detail.get("itemPrice"));

			try {
				Double payAmt = Double.valueOf(detail.get("payAmt").toString());
				pDetail.setPayAmt(payAmt);
			} catch (Exception e) {
				pDetail.setPayAmt(0.0);
			}
			try {
				Double receiveAmt = Double.valueOf(detail.get("unitAmt").toString());
				pDetail.setReceiveAmt(receiveAmt);
			} catch (Exception e) {
				pDetail.setReceiveAmt(0.0);
			}
			purchasingDetailBean.saveEntity(pDetail);
		}

		return maxCycle;
	}

	/**
	 * 由物流中心的采购要货单生成汇总单
	 * 
	 * @param userId
	 * @param userName
	 * @param header
	 * @param jsonData
	 * @param startDate
	 * @param endDate
	 */
	@Deprecated
	public void doCommit(String userId, String userName, CollectHeader header, String jsonData, Date startDate,
			Date endDate) throws NoPrivilegeException, SQLException, NoConnection {

		Date settleTime = branchBean.GetBranchById(BranchCode.DEFAULT_CENTRALFACTORY).getBusinessDate();

		// 默认中央工厂的ID 200
		Date now = new Date();

		// 自动生成，拼音缩写+部门编号+日期+流水号
		String formId = "HZD" + BranchCode.DEFAULT_CENTRALFACTORY + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";

		// detail
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			CollectDetail detail = new CollectDetail();

			// 单据编码
			detail.setFormId(formId);

			// 商品编码
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 关联ID
			String refId = TextUtil.uuid();
			detail.setFormRefId(refId);

			List<PurchasingDetail> formIdList = purchasingDetailBean.queryByItemId(itemId, startDate, endDate);

			for (int i = 0, length = formIdList.size(); i < length; i++) {
				CollectRefForm item = new CollectRefForm();
				item.setCollectFormId(refId);
				item.setRefFormId(formIdList.get(i).getFormId());
				collectRefFormBean.saveEntity(item);
			}

			// 商品名称
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);

			// 商品类别
			detail.setItemCategory(json.getString("itemCategory"));

			// 商品单位
			detail.setItemDimension(json.getString("itemDimension"));

			// 商品规格
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);

			// 单价
			if (json.get("itemUnitPrice") != null) {
				detail.setItemUnitPrice(json.getDouble("itemUnitPrice"));
			}

			// 数量
			if (json.get("itemCount") != null) {
				detail.setItemCount(json.getDouble("itemCount"));
			}

			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);

			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			collectDetailBean.saveEntity(detail);
		}

		// head
		header.setFormId(formId);
		header.setFormTime(settleTime);
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		header.setFormMakerId(userId);
		header.setFormMaker(userName);
		header.setBranchId(BranchCode.DEFAULT_CENTRALFACTORY);
		header.setBranch("[200]长沙中央工厂");

		collectHeaderBean.saveEntity(header);

		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("未审核");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	@Deprecated
	public void formIdToStatus(String userId, Date startDate, Date endDate) throws NoPrivilegeException, SQLException,
			NoConnection {
		String lcCode = BranchCode.DEFAULT_LOGISTICSCENTER;
		String cfCode = BranchCode.DEFAULT_CENTRALFACTORY;
		List<String> formIdLists = purchasingDetailBean.queryFormIds(lcCode, cfCode, startDate, endDate);
		for (int i = 0, length = formIdLists.size(); i < length; i++) {
			FormStatus fstatus = new FormStatus();
			fstatus.setFormId(formIdLists.get(i));
			fstatus.setStatus("已汇总");
			fstatus.setStatusTime(new Date());
			fstatus.setOperator(userId);
			formStatusBean.saveEntity(fstatus);
		}
	}

	public void auditBill(String userId, String userName, CollectHeader header, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			CollectDetail detail = new CollectDetail();
			detail.setFormId(json.getString("formId"));
			detail.setItemId(json.getString("itemId"));
			detail.setRequester(json.getString("requester"));

			try {
				detail.setProviderId(json.getString("providerId"));
			} catch (Exception e) {
				detail.setProviderId("");
			}

			try {
				detail.setProvider(json.getString("provider"));
			} catch (Exception e) {
				detail.setProvider("");
			}

			collectDetailBean.updateEntity(detail);
		}

		String formId = header.getFormId();
		collectHeaderBean.audit(formId, userId, userName, header.getAuditTime());

		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("已审核");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setCollectRefFormBean(CollectRefFormBean collectRefFormBean) {
		this.collectRefFormBean = collectRefFormBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setCollectDetailBean(CollectDetailBean collectDetailBean) {
		this.collectDetailBean = collectDetailBean;
	}

	public void setCollectHeaderBean(CollectHeaderBean collectHeaderBean) {
		this.collectHeaderBean = collectHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setSupplyCycleBean(SupplyCycleBean supplyCycleBean) {
		this.supplyCycleBean = supplyCycleBean;
	}

	public void setCfRequestSummaryBean(RequestSummaryBean cfRequestSummaryBean) {
		this.cfRequestSummaryBean = cfRequestSummaryBean;
	}

	public void setArrangmentHeaderBean(ArrangmentHeaderBean arrangmentHeaderBean) {
		this.arrangmentHeaderBean = arrangmentHeaderBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

}
