/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 16, 2014 by liyzh
 * Last edited on Sep 16, 2014 by liyzh
 * 
 * 说明： 餐厅配送反审核管理
 */
package action.restaurant.shipping;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.ShippingDetailBean;
import logic.form.ShippingHeaderBean;
import logic.store.BranchBean;
import logic.store.PrintTimesBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ReturnGoodsHeader;
import pojo.form.ShippingDetail;
import pojo.form.ShippingHeader;
import pojo.form.TransferHeader;
import pojo.store.PrintTimes;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;

@SuppressWarnings("rawtypes")
public class ShippingManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private FormStatusBean formStatusBean;

	private ShippingHeaderBean shippingHeaderBean;
	private ShippingDetailBean shippingDetailBean;
	private ReturnGoodsHeader returnHeader;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String formType;
	private String queryType;
	private String itemName;
	private String jsonData;
	private String parentFormId;
	/**
	 * 查询时是否需要汇总
	 */
	private Boolean sumOff;

	private String formId;
	private Integer preVersion;
	private Integer currentVersion;

	/**
	 * 配送日期
	 */
	private String receiveTime;

	/**
	 * 制单日期
	 */
	private String formTime;
	/**
	 * 审核日期
	 */
	private String auditTime;
	/**
	 * 入库时间
	 */
	private String inputTime;
	private Date returnTime;

	private String branchType;

	private String storageId;

	private String status;
	private Date businessDate;

	private String addFormFlag;

	private PrintTimesBean printTimesBean;

	private String sort;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		String loginType = getLoginBranchType();
		if ("RESTAURANT".equals(loginType)) {
			shopLst = branchBean.queryById(cfCode);
		} else {
			shopLst = branchBean.listShopByType(branchType);
		}
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}

	public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public String getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}

	public String getInputTime() {
		return inputTime;
	}

	public void setInputTime(String inputTime) {
		this.inputTime = inputTime;
	}

	private ShippingHeader shippingHeader;

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	private String formStatus;

	private List<Map> shopLst;

	public void setShippingDetailBean(ShippingDetailBean shippingDetailBean) {
		this.shippingDetailBean = shippingDetailBean;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			shippingHeader = shippingHeaderBean.queryById(formId);
			if (TextUtil.isEmpty(status)) {
				status = shippingHeader.getFormStatus();
			}

			receiveTime = DateTimeUtil.getDateTime(shippingHeader.getReceiveTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			formTime = DateTimeUtil.getDateTime(shippingHeader.getFormTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			auditTime = DateTimeUtil.getDateTime(shippingHeader.getAuditTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			inputTime = DateTimeUtil.getDateTime(shippingHeader.getInputTime(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);

			formStatus = shippingHeader.getFormStatus();
		}
		return SUCCESS;
	}

	/**
	 * 确认页面
	 * 
	 * @return
	 */
	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		shippingHeader = shippingHeaderBean.queryById(formId);
		status = formStatusBean.getCurrentStatus(formId);

		receiveTime = DateTimeUtil.getDateTime(shippingHeader.getReceiveTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
		formTime = DateTimeUtil.getDateTime(shippingHeader.getFormTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
		auditTime = DateTimeUtil.getDateTime(shippingHeader.getAuditTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
		inputTime = DateTimeUtil.getDateTime(shippingHeader.getInputTime(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		String branchId;
		if (BranchType.OUTERORDER.equals(branchType)) {
			branchId = getLoginBranchId();
		} else {
			branchId = shippingHeader.getInputerId();
		}

		formStatus = formStatusBean.getCurrentStatus(formId);

		Date settleTime = branchBean.GetBranchById(branchId).getBusinessDate();
		Date now = new Date();
		// 自动生成，拼音缩写+部门编号+日期+流水号
		String formId = "CTH" + branchId + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");
		returnHeader.setFormId(formId);

		returnHeader.setReturnerId(getLoginUserId());
		returnHeader.setReturner(getLoginUsername());
		returnTime = branchBean.GetBranchById(branchId).getBusinessDate();
		return SUCCESS;
	}

	public String preview() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			shippingHeader = shippingHeaderBean.queryById(formId);

			JSONObject jHeader = JSONObject.fromObject(shippingHeader);
			formTime = DateTimeUtil.getDateTime(shippingHeader.getFormTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			jHeader.put("formTime", formTime);
			auditTime = DateTimeUtil.getDateTime(shippingHeader.getAuditTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			jHeader.put("auditTime", auditTime);
			receiveTime = DateTimeUtil.getDateTime(shippingHeader.getReceiveTime(), DateTimeUtil.DEFAULT_DATE_FORMAT);
			jHeader.put("receiveTime", receiveTime);
			inputTime = DateTimeUtil.getDateTime(shippingHeader.getInputTime(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
			jHeader.put("inputTime", inputTime);

			JSONArray arr = new JSONArray();
			List<ShippingDetail> detailLst = shippingDetailBean.query(formId);
			int rownumber = 1;
			for (ShippingDetail detail : detailLst) {
				JSONObject json = JSONObject.fromObject(detail);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			JSONObject result = new JSONObject();
			result.put("shippingHeader", jHeader);
			result.put("shippingDetail", arr);
			result.put("msg", "ok");

			// 更新打印次数
			printTimesBean.updateEntity(new PrintTimes(formId));

			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		String sortQuery = "";

		if (!TextUtil.isEmpty(sort)) {
			JSONArray jsonArray = JSONArray.fromObject(sort);
			for (int i = 0; i < jsonArray.size(); i++) {
				if (i == 0) {
					String attribute = jsonArray.getJSONObject(0).getString("attribute");
					attribute = attribute.equals("formTimeActual") ? "FORM_TIME_ACTUAL" : attribute;
					sortQuery += " order by h." + attribute + " ";
					try {
						Boolean descending = Boolean.valueOf(jsonArray.getJSONObject(i).getString("descending"));
						sortQuery += descending ? "desc" : "asc";
					} catch (Exception e) {
						sortQuery += "";
					}

				}
			}
		} else {
			sortQuery += " order by H.REQUESTER_ID ";
		}

		if (TextUtil.isEmpty(formType)) {
			formType = FormConstant.REQUEST_TEMPLATE;
		}
		if (TextUtil.isEmpty(branchId)) {
			branchId = "-1";
		}
		int total = shippingHeaderBean.countAll(startDate, endDate, branchId, queryType, formType, branchType,
				storageId);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ShippingHeader> headerLst = shippingHeaderBean.queryAll(startDate, endDate, branchId, queryType,
					formType, branchType, storageId, getStart(), getEnd(), sortQuery);
			int rownumber = getStart();
			for (ShippingHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				// 配送日期
				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);

				// 制单日期
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);

				String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				// 审核日期
				String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);

				// 入库日期
				String inputTime = DateTimeUtil.getDateStr(header.getInputTime());
				json.put("inputTime", inputTime);

				json.put("rownumber", rownumber);
				if (header.getPrintTimes() == null) {
					json.put("printTimes", 0);
				}
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile() && sumOff == null) {
				double allPayAmt = shippingHeaderBean.sumAll(startDate, endDate, branchId, formType, branchType,
						storageId);
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

	public void doReturn() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(formType)) {
			formType = FormConstant.REQUEST_TEMPLATE;
		}
		if (TextUtil.isEmpty(branchId)) {
			branchId = "-1";
		}

		int total = shippingHeaderBean.countByCross(startDate, endDate, branchId, queryType, formType, branchType,
				storageId);
		setTotal(total);
		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ShippingHeader> headerLst;
			if (TextUtil.isEmpty(itemName)) {
				headerLst = shippingHeaderBean.queryByCross(startDate, endDate, branchId, queryType, formType,
						branchType, storageId, getStart(), getEnd());
			} else {
				headerLst = shippingHeaderBean.queryByCross(startDate, endDate, branchId, queryType, formType,
						branchType, storageId, itemName, getStart(), getEnd());
			}
			int rownumber = getStart();
			for (ShippingHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				// 配送日期
				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);

				// 制单日期
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);

				String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				// 审核日期
				String auditTime = DateTimeUtil.getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);

				// 入库日期
				String inputTime = DateTimeUtil.getDateStr(header.getInputTime());
				json.put("inputTime", inputTime);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd() && !isMobile() && sumOff == null) {
				double allPayAmt = shippingHeaderBean.sumAll(startDate, endDate, branchId, formType, branchType,
						storageId);
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

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<ShippingDetail> detailLst = shippingDetailBean.query(formId);
		int rownumber = 1;
		for (ShippingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			json.put("returnCount", 0);
			json.put("returnReason", "");
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
		JSONArray arr = new JSONArray();
		List<ShippingDetail> detailLst = shippingDetailBean.queryUnOrder(formId);
		int rownumber = 1;
		for (ShippingDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			json.put("returnCount", 0);
			json.put("returnReason", "");
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getFormId() {
		return formId;
	}

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setSumOff(Boolean sumOff) {
		this.sumOff = sumOff;
	}

	public String getStatus() {
		return status;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getQueryType() {
		return queryType;
	}

	public String getItemName() {
		return itemName;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public ReturnGoodsHeader getReturnHeader() {
		return returnHeader;
	}

	public void setReturnHeader(ReturnGoodsHeader returnHeader) {
		this.returnHeader = returnHeader;
	}

	public String getReturnTime() {
		return DateTimeUtil.getDateStr(returnTime);
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
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

	public void setAddFormFlag(String addFormFlag) {
		this.addFormFlag = addFormFlag;
	}

	public String getAddFormFlag() {
		return addFormFlag;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
	}

	public void setPrintTimesBean(PrintTimesBean printTimesBean) {
		this.printTimesBean = printTimesBean;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getParentFormId() {
		return parentFormId;
	}

	public void setParentFormId(String parentFormId) {
		this.parentFormId = parentFormId;
	}

}
