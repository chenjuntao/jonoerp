package action.centralfactory.productionDemand.summary;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ArrangmentHeaderBean;
import logic.form.FormStatusBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.module.cf.PurchaseQueryBean;
import logic.store.BranchBean;
import logic.store.FactoryWorkShopBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ArrangmentHeader;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.store.BranchStorage;
import pojo.store.FactoryWorkShop;
import service.centralfactory.productionDemand.SummaryService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class SummaryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private PurchaseQueryBean purchaseQueryBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private PurchasingDetailBean purchasingDetailBean;
	private ArrangmentHeaderBean arrangmentHeaderBean;
	private SummaryService summaryService;
	private ArrangmentHeader arrangmentHeader;
	private PurchasingHeader purchasingHeader;
	private String formId;
	private String ids;
	private String queryType;

	private String formNote;

	private Date businessDate;
	private Date startDate;
	private Date endDate;
	private Date formTime;
	private String supplierId;

	// 到货日期
	private Date receiveTime;

	private String auditTime;
	private Date startDate1;
	private Date endDate1;
	private Date startDate2;
	private Date endDate2;
	private Date startDate3;
	private Date endDate3;

	private String status;
	private String itemName;

	private FormStatusBean formStatusBean;

	private String jsonData;
	private Map<String, Object> summary;
	private List<BranchStorage> storeLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取中央工厂当前的营业时间
		startDate = DateTimeUtil.addDays(businessDate, -7);
		endDate = DateTimeUtil.addDays(businessDate, 7);

		receiveTime = businessDate;

		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			status = formStatusBean.getCurrentStatus(formId);

			purchasingHeader = purchasingHeaderBean.queryById(formId);
			receiveTime = purchasingHeader.getReceiveTime();
			formTime = purchasingHeader.getFormTime();
			auditTime = DateTimeUtil.getDateStr(purchasingHeader.getAuditTime());
		}
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		formTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		return SUCCESS;
	}

	public String estimateView() throws NoPrivilegeException, SQLException, NoConnection {
		arrangmentHeader = new ArrangmentHeader();
		arrangmentHeader.setFormMaker(getLoginUsername());
		arrangmentHeader.setFormMakerId(getLoginUserId());

		formTime = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();

		supplierId = getCfCode();
		// 参考区间
		endDate1 = endDate2 = endDate3 = formTime;
		startDate1 = DateTimeUtil.addDays(formTime, -1);
		startDate2 = DateTimeUtil.addDays(formTime, -7);
		startDate3 = DateTimeUtil.addMonths(formTime, -1);
		return SUCCESS;
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<PurchasingDetail> detailLst = purchasingDetailBean.query(formId);

		int rownumber = 1;
		for (PurchasingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
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

	@SuppressWarnings("rawtypes")
	public void queryEstimateSummary() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();

		List<Map> detailLst = arrangmentHeaderBean.queryEstimateSummary(getLoginBranchId(), ids);
		int rownumber = 1;
		for (Map map : detailLst) {
			JSONObject json = JSONObject.fromObject(map);
			Date completeTime = (Date) map.get("completeTime");
			json.put("completeTime", DateTimeUtil.getDateStr(completeTime));
			Date businessDate = (Date) map.get("businessDate");
			json.put("businessDate", DateTimeUtil.getDateStr(businessDate));

			BigDecimal suggestAmt = new BigDecimal((Double) map.get("suggestAmt"));
			json.put("orderCount", suggestAmt.multiply(new BigDecimal((Double) map.get("deliveryFactor"))));
			json.put("orderCount2", suggestAmt);
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

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		// 供应商为中央工厂
		JSONArray arr = new JSONArray();
		if (TextUtil.isEmpty(queryType)) {
			queryType = "unsummary";
		}
		List<PurchasingHeader> headerLst = purchaseQueryBean.query(getLoginBranchId(), startDate, endDate, queryType);

		int rownumber = getStart();
		for (PurchasingHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
			String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
			json.put("receiveTime", receiveTime);
			json.put("auditTime", auditTime);
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

	public void queryUnOrder() throws NoPrivilegeException, SQLException, NoConnection {
		// 供应商为中央工厂
		JSONArray arr = new JSONArray();
		List<PurchasingHeader> headerLst = purchaseQueryBean.queryUnOrder(getLoginBranchId(), itemName, startDate,
				endDate);

		int rownumber = getStart();
		for (PurchasingHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);
			String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
			String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
			json.put("receiveTime", receiveTime);
			json.put("auditTime", auditTime);
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

	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		arrangmentHeader.setFormBranchId(getLoginBranchId());
		String formId = "";
		if (TextUtil.isEmpty(ids)) {
			// 手动生成
			formId = summaryService.doCommit(getLoginUserId(), arrangmentHeader, jsonData);
		} else {
			// 汇总生成
			formId = summaryService.doCommit(ids, getLoginUserId(), arrangmentHeader, jsonData);
		}

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", formId);

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void returnSummaryCount() throws NoPrivilegeException, SQLException, NoConnection {
		Integer counts = summaryService.getSummaryCount(ids);
		counts = counts == null ? 0 : counts;

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("counts", counts);

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public ArrangmentHeader getArrangmentHeader() {
		return arrangmentHeader;
	}

	public void setArrangmentHeader(ArrangmentHeader arrangmentHeader) {
		this.arrangmentHeader = arrangmentHeader;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getJsonData() {
		return jsonData;
	}

	public Map<String, Object> getSummary() {
		return summary;
	}

	public String getFormNote() {
		return formNote;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartDate2() {
		return DateTimeUtil.getDateStr(startDate2);
	}

	public void setStartDate2(Date startDate2) {
		this.startDate2 = startDate2;
	}

	public String getStartDate3() {
		return DateTimeUtil.getDateStr(startDate3);
	}

	public void setStartDate3(Date startDate3) {
		this.startDate3 = startDate3;
	}

	public String getEndDate2() {
		return DateTimeUtil.getDateStr(endDate2);
	}

	public void setEndDate2(Date endDate2) {
		this.endDate2 = endDate2;
	}

	public String getEndDate3() {
		return DateTimeUtil.getDateStr(endDate3);
	}

	public void setEndDate3(Date endDate3) {
		this.endDate3 = endDate3;
	}

	public String getStartDate1() {
		return DateTimeUtil.getDateStr(startDate1);
	}

	public void setStartDate1(Date startDate1) {
		this.startDate1 = startDate1;
	}

	public String getEndDate1() {
		return DateTimeUtil.getDateStr(endDate1);
	}

	public void setEndDate1(Date endDate1) {
		this.endDate1 = endDate1;
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public void setReceiveTime(Date receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public PurchasingHeader getPurchasingHeader() {
		return purchasingHeader;
	}

	public void setPurchasingHeader(PurchasingHeader purchasingHeader) {
		this.purchasingHeader = purchasingHeader;
	}

	public void setPurchaseQueryBean(PurchaseQueryBean purchaseQueryBean) {
		this.purchaseQueryBean = purchaseQueryBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setArrangmentHeaderBean(ArrangmentHeaderBean arrangmentHeaderBean) {
		this.arrangmentHeaderBean = arrangmentHeaderBean;
	}

	public void setSummaryService(SummaryService summaryService) {
		this.summaryService = summaryService;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getStatus() {
		return status;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setFactoryWorkShopBean(FactoryWorkShopBean factoryWorkShopBean) {
	}

	public void setFactoryWorkShop(FactoryWorkShop factoryWorkShop) {
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

}
