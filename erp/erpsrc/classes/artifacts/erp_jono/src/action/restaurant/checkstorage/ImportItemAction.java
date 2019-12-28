/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on May 19, 2015 by liyzh
 * Last edited on May 19, 2015 by liyzh
 * 
 * 说明： 餐厅盘点清单导入
 */
package action.restaurant.checkstorage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.form.CheckLockBean;
import net.sf.json.JSONObject;
import pojo.form.CheckHeader;
import pojo.form.CheckLock;
import service.restaurant.checkstorage.ImportItemService;
import action.common.BaseAction;

import com.tanry.business.form.FormType;
import com.tanry.framework.acl.NoPrivilegeException;

public class ImportItemAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private CheckLockBean checkLockBean;
	private BranchBean branchBean;

	private ImportItemService importItemService;

	private String checkBatchId;
	private String formNote;

	/** 上传的文件 */
	private File attachment;

	/**
	 * 解析导入文件，保存为新的盘点清单，状态为已输入
	 */
	public void doImport() throws NoPrivilegeException, SQLException, NoConnection {
		CheckLock batch = checkLockBean.queryById(checkBatchId);
		String branchId = batch.getLockBranchId();
		CheckHeader checkHeader = new CheckHeader();
		checkHeader.setFormType(FormType.CHECK_LIST);
		Date formTime = branchBean.GetBranchById(branchId).getBusinessDate();
		checkHeader.setFormTime(formTime);
		checkHeader.setCheckBatchId(checkBatchId);
		checkHeader.setCheckBranchId(branchId);
		checkHeader.setCheckBranch("[" + batch.getLockBranchId() + "]" + batch.getLockBranch());
		checkHeader.setFormMakerId(getLoginUserId());
		checkHeader.setFormMaker(getLoginUsername());
		checkHeader.setFormNote(formNote);

		JSONObject result = importItemService.doImport(getLoginUserId(), checkHeader, attachment);
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCheckBatchId(String checkBatchId) {
		this.checkBatchId = checkBatchId;
	}

	public void setFormNote(String formNote) {
		this.formNote = formNote;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public void setCheckLockBean(CheckLockBean checkLockBean) {
		this.checkLockBean = checkLockBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
	
	public void setImportItemService(ImportItemService importItemService) {
		this.importItemService = importItemService;
	}

}
