package com.tanry.business.module.lc.request.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CollectRefFormBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.form.PurchasingMappingBean;
import logic.form.RequestHeaderBean;
import logic.module.lc.RequestSummaryBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.CollectRefForm;
import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.form.PurchasingMapping;
import pojo.store.Branch;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormUtil;
import com.tanry.business.module.lc.request.SummaryUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.constant.LogType;

public class OrderSummaryService {

	private FormStatusBean formStatusBean;

	private BranchBean branchBean;
	private RequestSummaryBean lcRequestSummaryBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private PurchasingMappingBean purchasingMappingBean;
	private PurchasingDetailBean purchasingDetailBean;
	private RequestHeaderBean requestHeaderBean;
	private CollectRefFormBean collectRefFormBean;

	private OperationVersionBean operationVersionBean;

	public Map<String, Object> querySummaryBill(String lcCode, Date businessDate, String ids, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String n = null;
		n = "nihao ";
		List<Map> directLst = lcRequestSummaryBean.querySummary(lcCode, ids, FormConstant.DIRECT_DELIVERY);
		List<Map> crossLst = lcRequestSummaryBean.querySummary(lcCode, ids, FormConstant.CROSS_DELIVERY);
		List<Map> unifiedLst = lcRequestSummaryBean.querySummary(lcCode, ids, FormConstant.UNIFIED_DELIVERY);

		Map<String, Object> summary = new HashMap<String, Object>();
		summary.put("direct2", SummaryUtil.groupCross(businessDate, true, directLst, null));
		summary.put("cross", SummaryUtil.groupCross(businessDate, true, crossLst, null));
		JSONArray arr = JSONArray.fromObject(jsonData);// 前台数据可能有改动，并且结合了MRP计算，以前台的数据为准
		summary.put("unified2", SummaryUtil.groupUnified(businessDate, true, unifiedLst, arr));

		StringBuffer sb = new StringBuffer();
		getNoProviderItems(arr, unifiedLst, sb);
		getNoProviderItems(crossLst, sb);
		getNoProviderItems(directLst, sb);

		String resultStr = sb.toString();
		if (!TextUtil.isEmpty(resultStr)) {
			int index = resultStr.lastIndexOf(",");
			resultStr = resultStr.substring(0, index);
		}

		summary.put("noProvider", resultStr);
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
	private void getNoProviderItems(JSONArray arr, List<Map> unifiedLst, StringBuffer sb) {

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
	}

	private void getNoProviderItems(List<Map> unifiedLst, StringBuffer sb) {

		for (int j = 0; j < unifiedLst.size(); j++) {
			Map map = unifiedLst.get(j);

			if (map.get("providerId") == null) {
				sb.append("[").append(map.get("itemId")).append("]").append(map.get("itemName")).append(",");
				break;
			}
		}

	}

	private Double getSuggest(Double distribution, Double allocation, Double _safeCount, Double realCount,
			Double roadCount) {
		Double suggest = distribution + allocation + _safeCount - realCount - roadCount;
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
			List<Map> itemLst = lcRequestSummaryBean.queryAverageRequest(startDate1, endDate1, ids);
			int rownumber = 1;
			for (Object obj : arr) {
				JSONObject json = (JSONObject) obj;
				String itemId = json.getString("itemId");
				BigDecimal average1 = findAverage(itemLst, itemId);

				if (average1 != null) {
					json.put("average1", average1);
					Double inventorysCycle = Double.valueOf(json.getDouble("inventorysCycle"));
					Double distribution = Double.valueOf(json.getDouble("distribution"));
					Double allocation = Double.valueOf(json.getDouble("allocation"));
					Double realCount = Double.valueOf(json.getDouble("realCount"));
					Double roadCount = Double.valueOf(json.getDouble("roadCount"));

					Double safeCount1 = average1.doubleValue() * inventorysCycle;
					json.put("average1", average1);
					json.put("safeCount1", safeCount1);
					json.put("suggestCount1", getSuggest(distribution, allocation, safeCount1, realCount, roadCount));
				}

				json.put("rownumber", rownumber);
				rownumber++;
			}
		}

		if (startDate2 != null && endDate2 != null) {
			List<Map> itemLst = lcRequestSummaryBean.queryAverageRequest(startDate2, endDate2, ids);
			int rownumber = 1;
			for (Object obj : arr) {
				JSONObject json = (JSONObject) obj;
				String itemId = json.getString("itemId");
				BigDecimal average2 = findAverage(itemLst, itemId);

				if (average2 != null) {
					json.put("average2", average2);
					Double inventorysCycle = Double.valueOf(json.getDouble("inventorysCycle"));
					Double distribution = Double.valueOf(json.getDouble("distribution"));
					Double allocation = Double.valueOf(json.getDouble("allocation"));
					Double realCount = Double.valueOf(json.getDouble("realCount"));
					Double roadCount = Double.valueOf(json.getDouble("roadCount"));

					Double safeCount2 = average2.doubleValue() * inventorysCycle;
					json.put("average2", average2);
					json.put("safeCount2", safeCount2);
					json.put("suggestCount2", getSuggest(distribution, allocation, safeCount2, realCount, roadCount));
				}

				json.put("rownumber", rownumber);
				rownumber++;
			}
		}

		if (startDate3 != null && endDate3 != null) {
			List<Map> itemLst = lcRequestSummaryBean.queryAverageRequest(startDate3, endDate3, ids);
			int rownumber = 1;

			for (Object obj : arr) {
				JSONObject json = (JSONObject) obj;
				String itemId = json.getString("itemId");
				BigDecimal average3 = findAverage(itemLst, itemId);

				if (average3 != null) {
					json.put("average3", average3);
					Double inventorysCycle = Double.valueOf(json.getDouble("inventorysCycle"));
					Double distribution = Double.valueOf(json.getDouble("distribution"));
					Double allocation = Double.valueOf(json.getDouble("allocation"));
					Double realCount = Double.valueOf(json.getDouble("realCount"));
					Double roadCount = Double.valueOf(json.getDouble("roadCount"));

					Double safeCount3 = average3.doubleValue() * inventorysCycle;
					json.put("average3", average3);
					json.put("safeCount3", safeCount3);
					json.put("suggestCount3", getSuggest(distribution, allocation, safeCount3, realCount, roadCount));
				}

				json.put("rownumber", rownumber);
				rownumber++;
			}
		}
		return arr;
	}

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

	// /**
	// * 保存全局流水号，直配、越库、统配使用同一流水
	// */
	// private int serial;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public Map<String, List<String>> createSummaryBill(PurchasingHeader commonHeader, String ids, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String lcCode = commonHeader.getRequesterId();
		List<Map> directLst = lcRequestSummaryBean.querySummary(lcCode, ids, FormConstant.DIRECT_DELIVERY);
		List<Map> crossLst = lcRequestSummaryBean.querySummary(lcCode, ids, FormConstant.CROSS_DELIVERY);
		List<Map> unifiedLst = lcRequestSummaryBean.querySummary(lcCode, ids, FormConstant.UNIFIED_DELIVERY);

		Branch lcBranch = branchBean.GetBranchById(lcCode);// 物流中心
		String lcRequester = lcBranch.getBranchName();
		commonHeader.setRequester(lcRequester);
		String lcAddress = lcBranch.getBranchAddress();
		commonHeader.setReceiveAddress(lcAddress);

		addressMap = new HashMap<String, String>();

		String commonFormId = FormUtil.genFormIdBody("ZP", lcCode, commonHeader.getFormTime());// 直配ZP；物流中心，直配大单
		commonHeader.setFormId(commonFormId);
		List<String> directIds = saveBigHeader(commonHeader,
				SummaryUtil.groupDir(commonHeader.getFormTime(), false, directLst, null));// 餐厅直配大小单

		commonFormId = FormUtil.genFormIdBody("YK", lcCode, commonHeader.getFormTime());// 越库YK；
		commonHeader.setFormId(commonFormId);

		List<String> crossIds = saveHeader(commonHeader,
				SummaryUtil.groupCross(commonHeader.getFormTime(), false, crossLst, null), FormConstant.CROSS_DELIVERY);

		commonFormId = FormUtil.genFormIdBody("TP", lcCode, commonHeader.getFormTime());// 统配TP；
		commonHeader.setFormId(commonFormId);
		JSONArray mrpLst = JSONArray.fromObject(jsonData);// 前台数据可能有改动，并且结合了MRP计算，以前台的数据为准
		List<String> unifiedIds = saveHeader(commonHeader,
				SummaryUtil.groupUnified(commonHeader.getFormTime(), false, unifiedLst, mrpLst),
				FormConstant.UNIFIED_DELIVERY);

		// 设置要货单的状态
		String[] requestFormIdArr = ids.split(",");
		for (String requestFormId : requestFormIdArr) {
			requestHeaderBean.savePurchaseStatus(requestFormId, "已汇总");
		}

		Map<String, List<String>> idMap = new HashMap<String, List<String>>();
		idMap.put("directIds", directIds);
		idMap.put("crossIds", crossIds);
		idMap.put("unifiedIds", unifiedIds);
		String formIds = join(directIds, ", ");
		formIds += join(crossIds, ", ");
		formIds += join(unifiedIds, ", ");
		lcRequestSummaryBean.saveEntity(ids, formIds);
		return idMap;
	}

	static public String join(List<String> list, String conjunction) {
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String item : list) {
			if (first)
				first = false;
			else
				sb.append(conjunction);
			sb.append(item);
		}
		return sb.toString();
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
		String lcRequester = commonHeader.getRequester();
		String lcAddress = commonHeader.getReceiveAddress();
		String formNote = commonHeader.getFormNote();

		Integer serial = Integer.parseInt(operationVersionBean.getMaxFormIndex(commonFormId + "%")) + 1;

		String lcCode = commonHeader.getRequesterId();
		// 保存所有的单据号
		List<String> idLst = new ArrayList<String>();
		for (Map<String, Object> bill : billLst) {
			PurchasingHeader pHeader = new PurchasingHeader();
			String pFormId = commonFormId + FormUtil.formatSerial(serial);
			pHeader.setRequesterId(lcCode);
			pHeader.setRequester(lcRequester);

			pHeader.setReceiveAddress(lcAddress);
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
			List<Map<String, Object>> detailLst = (List<Map<String, Object>>) bill.get("details");
			String maxPayItem = saveDetail(pFormId, detailLst);
			pHeader.setMaxPayItem(maxPayItem);

			purchasingHeaderBean.saveEntity(pHeader);

			operationVersionBean.saveEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
					OperationVersion.INIT_VERSION, OperationVersion.SAVED, pFormId));

			formStatusBean.saveEntity(new FormStatus(pFormId, BillStatus.UNADUIT_CN, userId));

			OperateLogUtil.record(pFormId, LogType.CREATE, "汇总生成采购单");
		}
		return idLst;
	}

	/**
	 * 保存直配大单表头数据
	 * 
	 * @param commonHeader
	 * @param count
	 * @param billLst
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private List<String> saveBigHeader(PurchasingHeader commonHeader, List<Map<String, Object>> billLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		String commonFormId = commonHeader.getFormId();
		Date businessDate = commonHeader.getFormTime();// 获取物流中心当前的营业时间
		String userId = commonHeader.getFormMakerId();
		String formMaker = commonHeader.getFormMaker();
		String lcRequester = commonHeader.getRequester();
		String formNote = commonHeader.getFormNote();

		Integer serial = Integer.parseInt(operationVersionBean.getMaxFormIndex(commonFormId + "%")) + 1;

		String lcCode = commonHeader.getRequesterId();
		// 保存所有的单据号
		List<String> idLst = new ArrayList<String>();
		for (Map<String, Object> bill : billLst) {
			PurchasingHeader pHeader = new PurchasingHeader();

			String pFormId = commonFormId + FormUtil.formatSerial(serial);// ZP+物流中心是大单，ZP+门店是小单
			pHeader.setRequesterId(lcCode);
			pHeader.setRequester(lcRequester);

			serial++;
			pHeader.setFormId(pFormId);
			idLst.add(pFormId);
			pHeader.setDeliveryType(FormConstant.DIRECT_DELIVERY);
			pHeader.setFormTime(businessDate);
			String providerId = (String) bill.get("providerId");
			pHeader.setProviderId(providerId);
			pHeader.setProvider((String) bill.get("provider"));
			// pHeader.setReceiveTime(commonHeader.getReceiveTime());
			pHeader.setReceiveTime((Date) bill.get("receiveTime"));
			pHeader.setFormMakerId(userId);
			pHeader.setFormMaker(formMaker);
			pHeader.setFormNote(formNote);
			purchasingHeaderBean.saveEntity(pHeader);

			operationVersionBean.saveEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
					OperationVersion.INIT_VERSION, OperationVersion.SAVED, pFormId));

			List<Map<String, Object>> buyerLst = (List<Map<String, Object>>) bill.get("buyers");
			saveDirectMapping(pHeader, buyerLst);

			formStatusBean.saveEntity(new FormStatus(pFormId, BillStatus.UNADUIT_CN, userId));

			OperateLogUtil.record(pFormId, LogType.CREATE, "汇总生成采购单");
		}
		return idLst;
	}

	/**
	 * 保存直配大单与小单的对应关系 大单与小单有几个区别，大单的单据号中部门是物流中心，而小单中是餐厅；大单的表头中收货地址为空；大单没有明细数据
	 * 
	 * @param pFormId
	 * @param detailLst
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private int saveDirectMapping(PurchasingHeader bigHeader, List<Map<String, Object>> buyerLst)
			throws NoPrivilegeException, SQLException, NoConnection {
		PurchasingMapping pMapping = new PurchasingMapping();
		pMapping.setBigFormId(bigHeader.getFormId());

		String lcCode = bigHeader.getRequesterId();
		String userId = bigHeader.getFormMakerId();
		for (Map<String, Object> bill : buyerLst) {// 每个门店一个小单
			String buyerId = (String) bill.get("buyerId");
			int count = purchasingHeaderBean.newSerial(bigHeader.getFormTime(), buyerId, BranchType.RESTAURANT);// 直配单以各个门店的流水号为准
			PurchasingHeader pHeader = new PurchasingHeader();
			// 直配小单与门店相关
			String smallFormId = FormUtil.genFormIdBody("ZP", buyerId, bigHeader.getFormTime())
					+ FormUtil.formatSerial(count);// 直配ZP；
			count++;
			pHeader.setFormId(smallFormId);
			pHeader.setRequesterId(lcCode);
			pHeader.setRequester(bigHeader.getRequester());
			pHeader.setReceiverId(buyerId);
			pHeader.setReceiver((String) bill.get("buyerName"));
			// 设置直配小单的收货地址
			String address = addressMap.get(buyerId);
			if (address == null) {
				Branch branch = branchBean.GetBranchById(buyerId);
				address = branch.getBranchAddress();
				addressMap.put(buyerId, address);
			}
			pHeader.setReceiveAddress(address);
			pHeader.setDeliveryType(FormConstant.DIRECT_DELIVERY);
			// pHeader.setFormRefId(formId);
			pHeader.setFormTime(bigHeader.getFormTime());
			String providerId = (String) bill.get("providerId");
			pHeader.setProviderId(providerId);
			pHeader.setProvider((String) bill.get("provider"));
			pHeader.setReceiveTime(bigHeader.getReceiveTime());
			pHeader.setFormMakerId(userId);
			pHeader.setFormMaker(bigHeader.getFormMaker());
			pHeader.setFormNote(bigHeader.getFormNote());
			List<Map<String, Object>> detailLst = (List<Map<String, Object>>) bill.get("details");
			String maxPayItem = saveDetail(smallFormId, detailLst);
			pHeader.setMaxPayItem(maxPayItem);
			purchasingHeaderBean.saveEntity(pHeader);
			// 保存大小表关联关系
			pMapping.setSmallFormId(smallFormId);
			purchasingMappingBean.saveEntity(pMapping);

			// 直配小单，需要与大单审核后同步改变状态，再到餐厅入库
			formStatusBean.saveEntity(new FormStatus(smallFormId, BillStatus.UNADUIT_CN, userId));
		}
		return buyerLst.size();
	}

	/**
	 * 保存采购单明细数据
	 * 
	 * @param pFormId
	 * @param detailLst
	 * @return
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private String saveDetail(String pFormId, List<Map<String, Object>> detailLst) throws NoPrivilegeException,
			SQLException, NoConnection {
		int count = 0;
		double payAmt = -1.0;
		String itemName = "";
		for (Map<String, Object> detail : detailLst) {
			PurchasingDetail pDetail = new PurchasingDetail();
			pDetail.setFormId(pFormId);
			String itemId = (String) detail.get("itemId");
			pDetail.setItemId(itemId);
			pDetail.setItemName((String) detail.get("itemName"));
			pDetail.setItemDimension((String) detail.get("itemDimension"));
			pDetail.setItemSpecification((String) detail.get("itemSpecification"));
			pDetail.setItemCategory((String) detail.get("itemCategory"));
			String buyerId = (String) detail.get("buyerId");
			pDetail.setReceiverId(buyerId);
			pDetail.setReceiver((String) detail.get("buyerName"));
			// pDetail.setItemCount((Double) detail.get("total"));
			Double orderCount = (Double.parseDouble(detail.get("orderCount") + ""));
			pDetail.setItemCount(orderCount);
			pDetail.setItemUnitPrice((Double) detail.get("itemUnitPrice"));
			double amt = Double.parseDouble(detail.get("payAmt").toString());
			pDetail.setPayAmt(amt);
			pDetail.setReceivePrice((Double) detail.get("itemPrice"));
			double receiveAmt = Double.parseDouble(detail.get("unitAmt").toString());
			pDetail.setReceiveAmt(receiveAmt);
			purchasingDetailBean.saveEntity(pDetail);
			if (amt > payAmt) {
				payAmt = amt;
				itemName = '[' + (String) detail.get("itemId") + ']' + (String) detail.get("itemName");
			}
			count++;

			List<Map<String, Object>> refLst = (List<Map<String, Object>>) detail.get("details");
			for (Map<String, Object> ref : refLst) {
				CollectRefForm refForm = new CollectRefForm();
				refForm.setCollectFormId(pFormId);
				refForm.setItemId(itemId);
				refForm.setRefFormId((String) ref.get("formId"));
				System.out.println(pFormId + "---" + refForm.getRefFormId() + "---" + itemId + "\n");
				collectRefFormBean.saveEntity(refForm);
				count++;
			}
		}
		return itemName;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setLcRequestSummaryBean(RequestSummaryBean lcRequestSummaryBean) {
		this.lcRequestSummaryBean = lcRequestSummaryBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setCollectRefFormBean(CollectRefFormBean collectRefFormBean) {
		this.collectRefFormBean = collectRefFormBean;
	}

	public void setPurchasingMappingBean(PurchasingMappingBean purchasingMappingBean) {
		this.purchasingMappingBean = purchasingMappingBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

}
