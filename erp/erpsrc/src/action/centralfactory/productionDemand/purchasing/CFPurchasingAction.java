/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 5, 2014 by liyzh
 * Last edited on Nov 5, 2014 by liyzh
 * 
 * 说明： 物流中心由汇总单生成采购单
 */
package action.centralfactory.productionDemand.purchasing;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ArrangmentHeaderBean;
import logic.form.FormStatusBean;
import logic.form.RequestDetailBean;
import logic.form.RequestHeaderBean;
import logic.module.cf.RequestSummaryBean;
import logic.module.lc.ManualPurchaseBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.FormStatus;
import pojo.form.PurchasingHeader;
import pojo.form.RequestDetail;
import pojo.form.RequestHeader;
import pojo.store.BranchStorage;
import service.centralfactory.productionDemand.OrderSummaryService;
import action.centralfactory.productionDemand.CFSummaryUtil;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

@Deprecated
@SuppressWarnings("rawtypes")
public class CFPurchasingAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private String formId;
	private Date businessDate;
	private Date formTime;
	private Date receiveTime;

	private String jsonData;
	private RequestHeader requestHeader;

	private String ids;

	private RequestDetailBean requestDetailBean;
	private RequestHeaderBean requestHeaderBean;
	private FormStatusBean formStatusBean;

	private ArrangmentHeaderBean arrangmentHeaderBean;

	private PurchasingHeader commonHeader;

	private Map<String, Object> summary;

	private BranchStorageBean branchStorageBean;

	private List<BranchStorage> storeLst;

	private OrderSummaryService cfOrderSummaryService;

	private RequestSummaryBean cfRequestSummaryBean;
	private ManualPurchaseBean manualPurchaseBean;

	private Date refDateStart1;
	private Date refDateEnd1;
	private Date refDateStart2;
	private Date refDateEnd2;
	private Date refDateStart3;
	private Date refDateEnd3;

	private List<Map<String, Object>> billLst;
	private String noProvider;
	private String itemName;
	private String supplierId;

	public void setCfOrderSummaryService(OrderSummaryService cfOrderSummaryService) {
		this.cfOrderSummaryService = cfOrderSummaryService;
	}

	public void setStoreLst(List<BranchStorage> storeLst) {
		this.storeLst = storeLst;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public Map<String, Object> getSummary() {
		return summary;
	}

	public void setSummary(Map<String, Object> summary) {
		this.summary = summary;
	}

	public void setArrangmentHeaderBean(ArrangmentHeaderBean arrangmentHeaderBean) {
		this.arrangmentHeaderBean = arrangmentHeaderBean;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public PurchasingHeader getCommonHeader() {
		return commonHeader;
	}

	public void setCommonHeader(PurchasingHeader commonHeader) {
		this.commonHeader = commonHeader;
	}

	public void setRequestDetailBean(RequestDetailBean requestDetailBean) {
		this.requestDetailBean = requestDetailBean;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public RequestHeader getRequestHeader() {
		return requestHeader;
	}

	public void setRequestHeader(RequestHeader requestHeader) {
		this.requestHeader = requestHeader;
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		// 默认门店编号为200的中央工厂
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		return SUCCESS;
	}

	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		doCommit(getLoginUserId(), requestHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	public String checkCFView() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);

		List<Map<String, Object>> providerLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> providerMap = new HashMap<String, List<Map<String, Object>>>();
		for (Object obj : arr) {// 根据供应商分组
			JSONObject item = (JSONObject) obj;
			String providerId = (String) item.get("providerId");
			String key = providerId;
			if (providerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("provider", item.get("provider"));
				bill.put("deliveryType", FormConstant.UNIFIED_DELIVERY);
				bill.put("key", key);
				providerLst.add(bill);
				providerMap.put(key, new ArrayList<Map<String, Object>>());
			}
			providerMap.get(key).add(item);
		}
		for (Map<String, Object> bill : providerLst) {
			bill.put("details", providerMap.get(bill.get("key")));
		}
		billLst = providerLst;

		String lcCode = getLoginBranchId();
		formTime = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取物流中心当前的营业时间
		commonHeader = new PurchasingHeader();
		commonHeader.setFormId(formId);
		commonHeader.setRequesterId(lcCode);
		commonHeader.setFormMakerId(getLoginUserId());
		commonHeader.setFormMaker(getLoginUsername());

		storeLst = branchStorageBean.query(lcCode, BranchType.CENTRALFACTORY);
		return SUCCESS;
	}

	/* 根据物料编码，名称查询 ,排除半成品 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = !TextUtil.isEmpty(supplierId) ? manualPurchaseBean.queryCFItem(getLoginBranchId(),
				supplierId, itemName) : manualPurchaseBean.queryCFItem(getLoginBranchId(), itemName);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			if (item.get("itemSpecification") == null) {
				json.put("itemSpecification", "");
			}
			if (item.get("itemUnitPrice") == null) {
				json.put("itemUnitPrice", 0);
			}
			if (item.get("receivePrice") == null) {
				json.put("receivePrice", 0);
			}
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public String aggregateView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = businessDate;
		commonHeader = new PurchasingHeader();
		commonHeader.setFormId(formId);
		// commonHeader.setRequesterId(BranchCode.LOGISTICSCENTER);
		commonHeader.setFormMakerId(getLoginUserId());
		commonHeader.setFormMaker(getLoginUsername());

		List<Map> directLst = arrangmentHeaderBean.querySummary(ids);

		summary = new HashMap<String, Object>();
		summary.put("direct", directLst);
		summary.put("direct2", CFSummaryUtil.groupCross(directLst));

		storeLst = branchStorageBean.query(getLoginBranchId(), BranchType.CENTRALFACTORY);

		return SUCCESS;
	}

	/**
	 * 查询日均要货量
	 */
	public void queryAverageRequest() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray itemLst = cfOrderSummaryService.queryAverageRequest(refDateStart1, refDateEnd1, refDateStart2,
				refDateEnd2, refDateStart3, refDateEnd3, jsonData);

		int rownumber = 1;
		for (Object item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			rownumber++;
		}

		try {
			this.outJS(itemLst.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据选择的单据号查询MPR计算所需的各个值
	 */
	public void queryMRP() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = cfRequestSummaryBean.queryMRP(getLoginBranchId(), ids);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			if (item.get("itemUnitPrice") == null) {
				json.put("itemUnitPrice", 0);
			}
			if (item.get("itemPrice") == null) {
				json.put("itemPrice", 0);
			}
			if (item.get("realCount") == null) {
				json.put("realCount", 0);
			}
			if (item.get("allocation") == null) {
				json.put("allocation", 0);
			}
			if (item.get("roadCount") == null) {
				json.put("roadCount", 0);
			}
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String estimateView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = businessDate;
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = businessDate;
		commonHeader = new PurchasingHeader();
		commonHeader.setFormId(formId);

		String cfCode = getLoginBranchId();
		commonHeader.setRequesterId(cfCode);
		commonHeader.setFormMakerId(getLoginUserId());
		commonHeader.setFormMaker(getLoginUsername());

		summary = cfOrderSummaryService.querySummaryBill(getLoginBranchId(), businessDate, ids, jsonData);

		noProvider = summary.get("noProvider").toString();

		storeLst = branchStorageBean.query(cfCode, BranchType.CENTRALFACTORY);
		return SUCCESS;
	}

	public String treeAggregateView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = businessDate;
		commonHeader = new PurchasingHeader();
		commonHeader.setFormId(formId);
		commonHeader.setRequesterId(getLoginBranchId());
		commonHeader.setFormMakerId(getLoginUserId());
		commonHeader.setFormMaker(getLoginUsername());

		storeLst = branchStorageBean.query(getLoginBranchId(), BranchType.CENTRALFACTORY);

		return SUCCESS;
	}

	public void createSummaryBill() throws NoPrivilegeException, SQLException, NoConnection {
		Map<String, List<String>> idMap = cfOrderSummaryService.createSummaryBill(commonHeader, ids, jsonData);
		JSONObject result = new JSONObject();
		result.put("idMap", idMap);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doCommit(String userId, RequestHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			RequestDetail detail = new RequestDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);
			detail.setInventory(json.getDouble("inventory"));
			detail.setOrderCount(json.getDouble("orderCount"));
			detail.setItemUnitPrice(json.getDouble("itemUnitPrice"));

			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			requestDetailBean.saveEntity(detail);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		requestHeaderBean.saveEntity(header);

		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("未审核");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setCfRequestSummaryBean(RequestSummaryBean cfRequestSummaryBean) {
		this.cfRequestSummaryBean = cfRequestSummaryBean;
	}

	public void setRefDateStart1(Date refDateStart1) {
		this.refDateStart1 = refDateStart1;
	}

	public void setRefDateEnd1(Date refDateEnd1) {
		this.refDateEnd1 = refDateEnd1;
	}

	public void setRefDateStart2(Date refDateStart2) {
		this.refDateStart2 = refDateStart2;
	}

	public void setRefDateEnd2(Date refDateEnd2) {
		this.refDateEnd2 = refDateEnd2;
	}

	public void setRefDateStart3(Date refDateStart3) {
		this.refDateStart3 = refDateStart3;
	}

	public void setRefDateEnd3(Date refDateEnd3) {
		this.refDateEnd3 = refDateEnd3;
	}

	public String getNoProvider() {
		return noProvider;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setManualPurchaseBean(ManualPurchaseBean manualPurchaseBean) {
		this.manualPurchaseBean = manualPurchaseBean;
	}

	public List<Map<String, Object>> getBillLst() {
		return billLst;
	}

	public void setBillLst(List<Map<String, Object>> billLst) {
		this.billLst = billLst;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

}
