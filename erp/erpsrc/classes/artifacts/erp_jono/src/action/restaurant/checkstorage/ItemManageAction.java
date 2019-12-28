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
package action.restaurant.checkstorage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckDetailBean;
import logic.form.CheckHeaderBean;
import logic.form.FormStatusBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CheckHeader;
import service.restaurant.checkstorage.BillManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class ItemManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BillManageService billManageService;
	private BranchBean branchBean;
	private CheckHeaderBean checkHeaderBean;
	private CheckDetailBean checkDetailBean;
	private FormStatusBean formStatusBean;

	private String branchId;

	private String formId;
	private String batchId;

	private String itemIds;

	private CheckHeader checkHeader;
	private Date formTime;

	private String jsonData;
	private List<Map> shopLst;
	private List<String> batchLst;
	private List<CheckHeader> formLst;

	private Date startDate;
	private Date endDate;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		startDate = endDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();

		shopLst = branchBean.queryById(getLoginBranchId());
		batchLst = checkHeaderBean.queryBatch(getLoginBranchId());
		String branchIds = "";
		for (int i = 0, length = batchLst.size(); i < length; i++) {
			branchIds += batchLst.get(i) + ",";
		}
		formLst = checkHeaderBean.queryCheckLists(branchIds);
		return SUCCESS;
	}

	/**
	 * 清单录入确认页面
	 * 
	 * @return
	 */
	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		checkHeader = checkHeaderBean.queryById(checkHeader.getFormId());

		formTime = checkHeader.getFormTime();
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			checkHeader = checkHeaderBean.queryById(formId);

			formTime = checkHeader.getFormTime();
		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			checkHeader = checkHeaderBean.queryById(formId);

			formTime = checkHeader.getFormTime();
		}
		return SUCCESS;
	}

	/**
	 * 根据餐厅查询盘点批次
	 */
	public void queryBatch() throws NoPrivilegeException, SQLException, NoConnection {
		List<String> batchLst = checkHeaderBean.queryBatch(branchId);
		JSONArray arr = JSONArray.fromObject(batchLst);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 判断多个清单之间原料是否重复
	 */
	public void checkRepeat() throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = itemIds.split(",");
		String existItem = "";
		for (String itemId : idArr) {
			boolean exist = checkDetailBean.exist(batchId, itemId);
			if (exist) {
				existItem = itemId;
				break;
			}
		}

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("existItem", existItem);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据批次查询盘点清单，仅编号
	 */
	public void queryCheckList() throws NoPrivilegeException, SQLException, NoConnection {
		formLst = checkHeaderBean.queryCheckList(batchId);
		JSONObject result = new JSONObject();
		result.put("msg", formLst);

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据批次查询盘点清单，表头信息
	 */
	public void queryCheckHeader() throws NoPrivilegeException, SQLException, NoConnection {
		List<CheckHeader> headerLst = checkHeaderBean.query(batchId);

		JSONArray arr = new JSONArray();
		int rownumber = getStart();
		for (CheckHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);

			String formTime = DateTimeUtil.getDateStr(header.getFormTime());
			json.put("formTime", formTime);

			String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
			json.put("formStatus", formStatus);
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

	/**
	 * 清单录入
	 */
	public void fillBill() throws NoPrivilegeException, SQLException, NoConnection {
		String userId = getLoginUserId();
		billManageService.fillBill(userId, checkHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setBatchId(String batchId) {
		this.batchId = batchId;
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

	public CheckHeader getCheckHeader() {
		return checkHeader;
	}

	public void setCheckHeader(CheckHeader checkHeader) {
		this.checkHeader = checkHeader;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

	public void setBillManageService(BillManageService billManageService) {
		this.billManageService = billManageService;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public void setCheckDetailBean(CheckDetailBean checkDetailBean) {
		this.checkDetailBean = checkDetailBean;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public List<String> getBatchLst() {
		return batchLst;
	}

	public List<CheckHeader> getFormLst() {
		return formLst;
	}

}
