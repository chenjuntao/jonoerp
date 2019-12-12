//===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014年9月17日 by pw
// Last edited on 2014年9月17日 by pw
//
// Comments:说明： 餐厅调拨单生成
//
//===============================================

package action.restaurant.allocateitem;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONObject;
import pojo.form.TransferHeader;
import pojo.store.BranchStorage;
import service.restaurant.allocateitem.CreateBillService;
import action.common.BaseAction;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.PrefixEnum;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class CreateBillAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private CreateBillService dbCreateBillService;
	private BranchBean branchBean;

	private List<Map> shopLst;
	private List<Map> inShopLst;

	private TransferHeader transferHeader;

	private Date auditTime;

	private String jsonData;

	private List<BranchStorage> inStorageList;
	private List<BranchStorage> outStorageList;

	private String branchType;

	private BranchStorageBean branchStorageBean;

	private String formTime;

	private String transferType;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		transferType = transferType == null ? "" : transferType.toUpperCase();
		if ("INNER".equals(transferType)) {
			inShopLst = new ArrayList<Map>();
			Map inShopMap = new HashMap();
			inShopMap.put("code", getLoginBranchId());
			inShopMap.put("name", getLoginBranchName());
			inShopLst.add(inShopMap);
			shopLst = inShopLst;

			inStorageList = branchStorageBean.query(getLoginBranchId(), null);
			outStorageList = inStorageList;
		} else {
			inShopLst = new ArrayList<Map>();
			Map inShopMap = new HashMap();
			inShopMap.put("code", getLoginBranchId());
			String l = getLoginBranchName();
			inShopMap.put("name", getLoginBranchName());
			inShopLst.add(inShopMap);

			if (branchType.indexOf(",") != -1) {
				shopLst = branchBean.listShopByType(branchType);
			} else {
				shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
			}

			for (int i = 0; i < shopLst.size(); i++) {
				Map tempMap = shopLst.get(i);
				if (tempMap.get("code").equals(getLoginBranchId())) {
					shopLst.remove(tempMap);
				}
			}

			if (shopLst.size() > 0) {
				inStorageList = branchStorageBean.query(getLoginBranchId(), null);
				outStorageList = branchStorageBean.query(null, branchType);
				for (int i = 0; i < outStorageList.size(); i++) {
					BranchStorage branchStorage = outStorageList.get(i);
					if (branchStorage.getBranchId().equals(getLoginBranchId())) {
						outStorageList.remove(i);
						i--;
					}
				}
			}
		}

		return SUCCESS;
	}

	public String commitView() throws NoPrivilegeException, SQLException, NoConnection {

		String inBranchId = transferHeader.getInBranchId();
		Date settleTime = branchBean.GetBranchById(inBranchId).getBusinessDate();

		formTime = DateTimeUtil.getDateStr(settleTime);

		String formId = FormUtil.generateFormId(PrefixEnum.DB, inBranchId, settleTime);
		transferHeader.setFormId(formId);

		transferHeader.setFromMakerId(getLoginUserId());
		transferHeader.setFromMaker(getLoginUsername());

		return SUCCESS;
	}

	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		transferHeader.setFormTime(DateTimeUtil.parse(formTime));
		String formId = dbCreateBillService.doCommit(getLoginUserId(), transferHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", formId);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getAuditTime() {
		return getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setDbCreateBillService(CreateBillService dbCreateBillService) {
		this.dbCreateBillService = dbCreateBillService;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public TransferHeader getTransferHeader() {
		return transferHeader;
	}

	public void setTransferHeader(TransferHeader transferHeader) {
		this.transferHeader = transferHeader;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public List<Map> getInShopLst() {
		return inShopLst;
	}

	public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public List<BranchStorage> getInStorageList() {
		return inStorageList;
	}

	public List<BranchStorage> getOutStorageList() {
		return outStorageList;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getTransferType() {
		return transferType;
	}

}
