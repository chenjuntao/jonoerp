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
package action.restaurant.dreject;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ReturnGoodsDetailBean;
import logic.form.ReturnGoodsHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ReturnGoodsDetail;
import pojo.form.ReturnGoodsHeader;
import pojo.form.ShippingHeader;
import service.restaurant.dreject.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class BillManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BillManageService djBillManageService;
	private BranchBean branchBean;
	private ReturnGoodsHeaderBean returnGoodsHeaderBean;
	private ReturnGoodsDetailBean returnGoodsDetailBean;
	private ShippingHeaderBean shippingHeaderBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String queryType;

	private String formId;

	private ReturnGoodsHeader returnHeader;
	private ShippingHeader shippingHeader;
	private Date returnTime;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private Date inputTime;

	private String formStatus;

	private String jsonData;
	private List<Map> shopLst;

	private String branchType;

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	private Date businessDate;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = null;
		shopLst = branchBean.queryById(getLoginBranchId());
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	/**
	 * 退货确认，退货部门应为各个餐厅，而不是物流中心，否则永远看不到数据
	 */
	public String rMainView() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchType)) {
			branchType = BranchTypeEnum.R_Out.toString();
		}
		shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = returnGoodsHeaderBean.queryById(formId);
			returnTime = returnHeader.getReturnTime();

			shippingHeader = shippingHeaderBean.queryById(returnHeader.getFormRefId());

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getInputTime();
		}

		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = returnGoodsHeaderBean.queryById(formId);
			returnTime = returnHeader.getReturnTime();

			shippingHeader = shippingHeaderBean.queryById(returnHeader.getFormRefId());

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getInputTime();

			formStatus = returnHeader.getStatus();
		}
		return SUCCESS;
	}

	public String rejectView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = returnGoodsHeaderBean.queryById(formId);
			returnTime = returnHeader.getReturnTime();
			String lcCode = getLoginBranchId();
			auditTime = branchBean.GetBranchById(lcCode).getBusinessDate();// 获取当前的营业时间
			returnHeader.setAuditTime(auditTime);
			shippingHeader = shippingHeaderBean.queryById(returnHeader.getFormRefId());

			receiveTime = shippingHeader.getReceiveTime();
			formTime = shippingHeader.getFormTime();
			inputTime = shippingHeader.getInputTime();

			formStatus = returnHeader.getStatus();
		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();

		int total = returnGoodsHeaderBean.count(startDate, endDate, branchId, queryType, branchType);
		setTotal(total);

		if (total > 0) {
			List<ReturnGoodsHeader> headerLst = returnGoodsHeaderBean.query(startDate, endDate, branchId, queryType,
					branchType, getStart(), getEnd());
			int rownumber = getStart();
			for (ReturnGoodsHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String returnTime = DateTimeUtil.getDateStr(header.getReturnTime());
				json.put("returnTime", returnTime);

				String receiveTime = DateTimeUtil.getDateStr(header.getReceiveTime());
				json.put("receiveTime", receiveTime);
				String inputTime = DateTimeUtil.getDateStr(header.getInputTime());
				json.put("inputTime", inputTime);

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
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

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<ReturnGoodsDetail> detailLst = returnGoodsDetailBean.query(formId);
		int rownumber = 1;
		for (ReturnGoodsDetail detail : detailLst) {
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		djBillManageService.updateBill(returnHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		djBillManageService.deleteBill(getLoginUserId(), formId);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doReturn() throws NoPrivilegeException, SQLException, NoConnection {
		djBillManageService.doReturn(getLoginUserId(), returnHeader, branchId, null, "PSTH");

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Deprecated
	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		djBillManageService.doConfirm(getLoginUserId(), returnHeader);

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

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public ReturnGoodsHeader getReturnHeader() {
		return returnHeader;
	}

	public void setReturnHeader(ReturnGoodsHeader returnHeader) {
		this.returnHeader = returnHeader;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public String getReturnTime() {
		return DateTimeUtil.getDateStr(returnTime);
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public void setReturnGoodsHeaderBean(ReturnGoodsHeaderBean returnGoodsHeaderBean) {
		this.returnGoodsHeaderBean = returnGoodsHeaderBean;
	}

	public void setReturnGoodsDetailBean(ReturnGoodsDetailBean returnGoodsDetailBean) {
		this.returnGoodsDetailBean = returnGoodsDetailBean;
	}

	public void setDjBillManageService(BillManageService djBillManageService) {
		this.djBillManageService = djBillManageService;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}
}
