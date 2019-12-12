/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 23, 2014 by cjt
 * Last edited on Sep 23, 2014 by cjt
 * 
 * 说明：餐厅报损单管理
 */
package action.restaurant.reportdamage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.LossDetailBean;
import logic.form.LossHeaderBean;
import logic.form.OperationVersionBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.LossDetail;
import pojo.form.LossHeader;
import pojo.form.TransferHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.restaurant.reportdamage.ManageLossBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.DoubleJsonValueProcessor;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

public class ManageLossAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ManageLossBillService manageLossBillService;
	private BranchBean branchBean;
	private LossHeaderBean lossHeaderBean;
	private LossDetailBean lossDetailBean;
	private BranchStorageBean branchStorageBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String queryType;
	private String statusType;

	private String formId;

	private List<BranchStorage> storageList;

	private LossHeader lossHeader;
	private Date lossTime;
	private Date auditTime;
	private Date processTime;

	private String jsonData;
	private String dishJsonData; // 保存出品数据
	private String rawJsonData; // 保存原料数据
	private List<Map> shopList;

	private String branchType;

	private String brandWord;

	private String storageId;
	private String storageName;

	private Integer preVersion;
	private Integer currentVersion;
	private OperationVersionBean operationVersionBean;

	private Date businessDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		endDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取中央工厂当前的营业时间
		businessDate = endDate;
		startDate = DateTimeUtil.addDays(endDate, -1);
		brandWord = BranchType.getBrandWord(branchType);
		shopList = branchBean.queryById(getLoginBranchId());

		if (shopList.size() > 0) {
			storageList = branchStorageBean.query(getLoginBranchId(), branchType);
		}

		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public void refreshStorage() throws NoPrivilegeException, SQLException, NoConnection {
		storageList = branchStorageBean.query(branchId, branchType);
		JSONObject result = new JSONObject();
		result.put("msg", storageList);

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		QueryAll("RAWLOSS");
	}

	public void doDishQuery() throws NoPrivilegeException, SQLException, NoConnection {
		QueryAll("DISHLOSS");
	}

	public void QueryAll(String lossType) throws NoPrivilegeException, SQLException, NoConnection {
		int total = lossHeaderBean.countAll(startDate, endDate, branchId, storageId, lossType, branchType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<LossHeader> headerLst = lossHeaderBean.queryAll(startDate, endDate, branchId, storageId, lossType,
					branchType, getStart(), getEnd());
			int rownumber = getStart();
			for (LossHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String lossTime = getDateStr(header.getLossTime());
				json.put("lossTime", lossTime);
				String auditTime = getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile()) {
				double allPayAmt = lossHeaderBean.sum(startDate, endDate, branchId, storageId, lossType, branchType);
				TransferHeader item = new TransferHeader();
				JSONObject sumJObject = JSONObject.fromObject(item);
				sumJObject.put("rownumber", "合计");
				sumJObject.put("allPayAmt", allPayAmt);
				arr.add(sumJObject);
			}
		}

		if (isMobile()) {
			JSONObject result = new JSONObject();
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("iTotalDisplayRecords", total);
			result.put("data", arr);// for DataTables
			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryByStatus() throws NoPrivilegeException, SQLException, NoConnection {
		if ("-1".equals(branchId)) {
			branchId = "";// 统一非空条件
		}
		if (TextUtil.isEmpty(branchType)) {
			branchType = BranchType.RESTAURANT;
		}
		int total = lossHeaderBean.countByStatus(startDate, endDate, branchId, storageId, queryType, statusType,
				branchType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<LossHeader> headerLst = lossHeaderBean.queryByStatus(startDate, endDate, branchId, storageId,
					queryType, statusType, branchType);
			int rownumber = getStart();
			for (LossHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String lossTime = getDateStr(header.getLossTime());
				json.put("lossTime", lossTime);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String showView() throws NoPrivilegeException, SQLException, NoConnection {
		brandWord = BranchType.getBrandWord(branchType);

		if (!TextUtil.isEmpty(formId)) {
			lossHeader = lossHeaderBean.queryById(formId);
			lossTime = lossHeader.getLossTime();
			auditTime = lossHeader.getAuditTime();
			processTime = lossHeader.getProcessTime();
		}
		return SUCCESS;
	}

	/**
	 * 无论是原料报损还是出品报损，只查询原料信息
	 */
	public void queryRawDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<LossDetail> detailLst = lossDetailBean.queryRawDetail(formId, hasSum);

		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());

		int rownumber = 1;
		for (LossDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail, config);
			json.put("expired_time", getDateStr(detail.getExpired_time()));

			if (rownumber != detailLst.size()) {
				json.put("rownumber", rownumber);
			}

			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDishDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<LossDetail> detailLst = lossDetailBean.queryDishDetail(formId, hasSum);

		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());

		int rownumber = 1;
		for (LossDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail, config);
			json.put("expired_time", getDateStr(detail.getExpired_time()));

			if (rownumber != detailLst.size()) {
				json.put("rownumber", rownumber);
			}

			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDishHeader() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<LossDetail> detailLst = lossDetailBean.queryLossItem(formId);

		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());

		int rownumber = 1;
		for (LossDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail, config);
			json.put("expired_time", getDateStr(detail.getExpired_time()));

			if (rownumber != detailLst.size()) {
				json.put("rownumber", rownumber);
			}

			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDish() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<LossDetail> detailLst = lossDetailBean.queryDish(formId);

		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());

		int rownumber = 1;
		for (LossDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail, config);
			json.put("expired_time", getDateStr(detail.getExpired_time()));

			if (rownumber != detailLst.size()) {
				json.put("rownumber", rownumber);
			}

			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			preVersion = operationVersionBean.queryVersion(formId).getVersion();

			lossHeader = lossHeaderBean.queryById(formId);

			String branchId = lossHeader.getLossBranchId();
			Branch branchObj = branchBean.GetBranchById(branchId);

			lossTime = lossHeader.getLossTime();
			lossHeader.setAuditor(getLoginUsername());
		}

		return SUCCESS;
	}

	public void doSave() throws Exception {
		manageLossBillService.doSave(lossHeader, jsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDishSave() throws NoPrivilegeException, SQLException, NoConnection {
		manageLossBillService.doDishSave(lossHeader, jsonData, rawJsonData, currentVersion);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		manageLossBillService.doDelete(formId, currentVersion);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getLossTime() {
		return getDateStr(lossTime);
	}

	public String getAuditTime() {
		return getDateStr(auditTime);
	}

	public String getProcessTime() {
		return getDateStr(processTime);
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public LossHeader getLossHeader() {
		return lossHeader;
	}

	public void setLossHeader(LossHeader lossHeader) {
		this.lossHeader = lossHeader;
	}

	public List<Map> getShopList() {
		return shopList;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
	}

	public void setManageLossBillService(ManageLossBillService manageLossBillService) {
		this.manageLossBillService = manageLossBillService;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setLossDetailBean(LossDetailBean lossDetailBean) {
		this.lossDetailBean = lossDetailBean;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setStatusType(String statusType) {
		this.statusType = statusType;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public String getBrandWord() {
		return brandWord;
	}

	public void setBrandWord(String brandWord) {
		this.brandWord = brandWord;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public Integer getPreVersion() {
		return preVersion;
	}

	public void setPreVersion(Integer preVersion) {
		this.preVersion = preVersion;
	}

	public Integer getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public String getDishJsonData() {
		return dishJsonData;
	}

	public void setDishJsonData(String dishJsonData) {
		this.dishJsonData = dishJsonData;
	}

	public String getRawJsonData() {
		return rawJsonData;
	}

	public void setRawJsonData(String rawJsonData) {
		this.rawJsonData = rawJsonData;
	}
}
