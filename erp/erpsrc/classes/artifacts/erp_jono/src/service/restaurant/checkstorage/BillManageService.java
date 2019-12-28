/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 18, 2014 by liyzh
 * Last edited on Sep 18, 2014 by liyzh
 * 
 * 说明： 盘点清单管理
 */
package service.restaurant.checkstorage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckDetailBean;
import logic.form.CheckHeaderBean;
import logic.form.CheckLockBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.StorageInOutBean;
import logic.store.BranchBean;
import logic.store.DailyBranchStorageBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.CheckDetail;
import pojo.form.CheckHeader;
import pojo.form.CheckLock;
import pojo.form.FormStatus;
import pojo.form.StorageInOut;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.LogType;

public class BillManageService {

	private CheckHeaderBean checkHeaderBean;
	private CheckDetailBean checkDetailBean;
	private FormStatusBean formStatusBean;
	private CheckLockBean checkLockBean;
	private StorageBean storageBean;

	private StorageInOutBean storageInOutBean;
	private OperationVersionBean operationVersionBean;
	private DailyBranchStorageBean dailyBranchStorageBean;

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditById(String formId, String userId, String userName, Date businessDate)
			throws NoPrivilegeException, SQLException, NoConnection {
		CheckHeader head = new CheckHeader();
		head.setFormId(formId);
		head.setAuditor(userId);
		head.setAuditor(userName);
		head.setAuditTime(businessDate);
		checkHeaderBean.audit(head);

		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("已审核");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void SettleStorage(String branchId, String storageId, java.util.Date settleDate)
			throws NoPrivilegeException, SQLException, NoConnection {
		dailyBranchStorageBean.calcTheoryStorage(branchId, storageId, settleDate);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveBill(CheckHeader checkHeader, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String formId = checkHeader.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			CheckDetail detail = new CheckDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);

			detail.setItemOrder(json.getInt("rownumber"));

			if (json.get("checkCount") != null) {
				detail.setCheckCount(json.getDouble("checkCount"));
			}
			if (json.get("theoryCount") != null) {
				detail.setTheoryCount(json.getDouble("theoryCount"));
			}
			detail.setItemStatus((String) json.get("itemStatus"));

			Double payAmt = null;
			try {
				payAmt = json.getDouble("itemPrice");// 标准价
			} catch (Exception e) {
				payAmt = 0.0;
			}

			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}
			checkDetailBean.saveEntity(detail);
		}
		// checkHeader.setAllPayAmt(allPayAmt); 没有数量，所以屏蔽掉
		checkHeader.setMaxPayItem(maxPayItem);
		checkHeaderBean.saveEntity(checkHeader);
	}

	/**
	 * 创建清单
	 * 
	 * @param userId
	 * @param checkHeader
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String createItemList(String userId, CheckHeader checkHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		Date formTime = checkHeader.getFormTime();
		String branchId = checkHeader.getCheckBranchId();
		int newSerial = checkHeaderBean.newSerial(formTime, branchId, FormConstant.CHECK_LIST);
		// CL, check list
		String formId = FormUtil.genFormIdBody("CL", branchId, formTime) + FormUtil.formatSerial(newSerial);
		checkHeader.setFormId(formId);

		saveBill(checkHeader, jsonData);

		formStatusBean.saveEntity(new FormStatus(formId, "未输入", userId));

		OperateLogUtil.record(formId, LogType.CREATE, "新增盘点清单");
		return formId;
	}

	/**
	 * 创建盘点单
	 * 
	 * @param userId
	 * @param checkHeader
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String createCheckBill(String userId, CheckHeader checkHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		CheckHeader oldCheck = checkHeaderBean.queryForm(checkHeader.getCheckBatchId());
		if (oldCheck != null) {
			checkHeaderBean.deleteEntity(oldCheck.getFormId());
			checkDetailBean.deleteByForm(oldCheck.getFormId());
		}

		Date formTime = checkHeader.getFormTime();
		String branchId = checkHeader.getCheckBranchId();
		String formBody = FormUtil.genFormIdBody("CS", branchId, formTime);

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);
		// 保存单据版本信息
		OperateVersionUtil.save(formId);

		checkHeader.setFormId(formId);

		CheckLock lock = checkLockBean.queryById(checkHeader.getCheckBatchId());
		checkHeader.setCheckStorageId(lock.getLockStorageId());
		checkHeader.setCheckStorage(lock.getLockStorage());// 使用锁库时选择的仓库
		saveBill(checkHeader, jsonData);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
		OperateLogUtil.record(formId, LogType.CREATE, "新增盘点单");
		return formId;
	}

	/**
	 * 餐厅盘点清单修改
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(CheckHeader header, String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			CheckDetail detail = new CheckDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			Double checkCount = null;
			try {
				checkCount = json.getDouble("checkCount");
			} catch (Exception e) {
				checkCount = 0.0;
			}

			detail.setCheckCount(checkCount);
			// Double payAmt = itemPriceBean.getItemPrice(itemId,
			// PriceType.BENCHMARK);// 标准价
			Double payAmt = 0.0;
			if (json.containsKey("itemPrice")) {
				try {
					payAmt = json.getDouble("itemPrice");
				} catch (Exception e) {
					payAmt = 0.0;
				}

				payAmt = payAmt * checkCount;
			}
			String itemName = json.getString("itemName");
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}
			System.out.println(itemId + itemName);
			checkDetailBean.updateEntity(detail);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		checkHeaderBean.updateEntity(header);
	}

	/**
	 * 清单录入
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void fillBill(String userId, CheckHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		updateBill(header, jsonData);

		// 修改清单状态
		String formId = header.getFormId();
		formStatusBean.saveEntity(new FormStatus(formId, "已输入", userId));
		OperateLogUtil.record(header.getFormId(), LogType.UPDATE, "编辑盘点清单");
	}

	/**
	 * 餐厅盘点单审核操作
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, CheckHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		updateBill(header, jsonData);
		checkHeaderBean.audit(header);

		// -----------------------------------------

		// 操作时间
		Date operationTime = DateTimeUtil.getNow();

		// 业务类型 餐厅盘点
		String reason = "PD";

		// -----------------------------------------

		String branchId = header.getCheckBranchId();

		String storageId = header.getCheckStorageId();

		JSONArray arr = JSONArray.fromObject(jsonData);
		List<Map> itemCountLst = storageBean.queryItemCount(header.getFormId(), storageId);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			// -----------------------------------------
			// 出入库记录

			// -------------- 餐厅
			String itemId = json.getString("itemId");
			Double storage = 0.0;
			for (Map itemCount : itemCountLst) {
				if (itemId.equals(itemCount.get("itemId"))) {
					storage = (Double) itemCount.get("itemCount");
					storage = (storage == null ? 0 : storage);
					break;
				}
			}

			Double checkCount = json.getDouble("checkCount");
			if (checkCount == null) {
				checkCount = 0.0;
			}

			Double diff = storage - checkCount;

			// 入库数量
			Double itemCountIn = diff < 0.0 ? -diff : 0.0;

			// 出库数量
			Double itemCountOut = diff > 0.0 ? diff : 0.0;

			// 期初数量
			Double orgiCount = storage;

			// 结存数量
			Double resultCount = checkCount;

			Double itemUnitPrice = null;
			try {

				itemUnitPrice = json.getDouble("itemPrice");
			} catch (Exception e) {
				itemUnitPrice = (itemUnitPrice == null ? 0 : itemUnitPrice);
			}

			Date businessDt = branchBean.GetBranchById(branchId).getBusinessDate();
			StorageInOut storageInOut = new StorageInOut(branchId, storageId, businessDt, operationTime, itemId,
					itemUnitPrice, orgiCount, itemCountIn, itemCountOut, resultCount, header.getFormId(), reason);

			storageInOutBean.saveEntity(storageInOut);
			// -----------------------------------------

			// 审核通过之后，修改实际库存为盘点数量
			storageBean.updateCheckStock(json.getString("itemId"), storageId, json.getDouble("checkCount"));
		}

		// 修改盘点单状态
		String formId = header.getFormId();
		formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));

		checkLockBean.finish(header.getCheckBatchId());

		OperateLogUtil.record(header.getFormId(), LogType.AUDIT, "审核盘点单");
	}

	/**
	 * 餐厅盘点清单刪除
	 * 
	 * @param formId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBill(String userId, String formId) throws NoPrivilegeException, SQLException, NoConnection {
		checkHeaderBean.deleteEntity(formId);
		checkDetailBean.deleteByForm(formId);

		// 修改盘点单状态
		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus("已删除");
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
		OperateLogUtil.record(formId, LogType.DELETE, "删除盘点清单");
	}

	/**
	 * 删除批次及相关盘点数据
	 * 
	 * @param checkBatchId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteBatch(String checkBatchId) throws NoPrivilegeException, SQLException, NoConnection {
		checkDetailBean.deleteByBatch(checkBatchId);// 先删子表，再删除主表数据
		checkHeaderBean.deleteByBatch(checkBatchId);
		checkLockBean.deleteEntity(checkBatchId);

	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

	public void setCheckDetailBean(CheckDetailBean checkDetailBean) {
		this.checkDetailBean = checkDetailBean;
	}

	public void setCheckLockBean(CheckLockBean checkLockBean) {
		this.checkLockBean = checkLockBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setDailyBranchStorageBean(DailyBranchStorageBean dailyBranchStorageBean) {
		this.dailyBranchStorageBean = dailyBranchStorageBean;
	}

}
