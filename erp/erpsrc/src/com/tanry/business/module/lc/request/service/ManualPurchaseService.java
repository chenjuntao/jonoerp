/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 19, 2014 by liyzh
 * Last edited on Dec 19, 2014 by liyzh
 * 
 * 说明： 物流中心手动生成采购单
 */
package com.tanry.business.module.lc.request.service;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.store.Branch;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormUtil;
import dao.importdata.ImportRequestDao;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;

public class ManualPurchaseService {

	private FormStatusBean formStatusBean;
	private BranchBean branchBean;
	private PurchasingHeaderBean purchasingHeaderBean;
	private PurchasingDetailBean purchasingDetailBean;
	private ImportRequestDao importRequestDao;
	private OperationVersionBean operationVersionBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<String> createPurchaseBill(PurchasingHeader commonHeader, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		String requesterId = commonHeader.getRequesterId();
		String commonFormId = FormUtil.genFormIdBody("TP", requesterId, commonHeader.getFormTime());// 统配TP；

		Integer count = Integer.parseInt(operationVersionBean.getMaxFormIndex(commonFormId + "%")) + 1;
		commonHeader.setFormId(commonFormId);

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

		Branch lcBranch = branchBean.GetBranchById(requesterId);// 物流中心
		String lcRequester = lcBranch.getBranchName();
		String lcAddress = lcBranch.getBranchAddress();
		Date businessDate = commonHeader.getFormTime();// 获取物流中心当前的营业时间
		String userId = commonHeader.getFormMakerId();
		String formMaker = commonHeader.getFormMaker();
		String formNote = commonHeader.getFormNote();
		// 保存所有的单据号
		List<String> idLst = new ArrayList<String>();
		for (Map<String, Object> bill : providerLst) {
			bill.put("details", providerMap.get(bill.get("key")));
			PurchasingHeader pHeader = new PurchasingHeader();
			String pFormId = commonFormId + FormUtil.formatSerial(count);

			pHeader.setFormId(pFormId);
			idLst.add(pFormId);
			count++;
			pHeader.setRequesterId(requesterId);
			pHeader.setRequester(lcRequester);

			pHeader.setReceiveAddress(lcAddress);
			pHeader.setStorageId(commonHeader.getStorageId());
			pHeader.setStorage(commonHeader.getStorage());
			pHeader.setReceiveTime(commonHeader.getReceiveTime());
			pHeader.setDeliveryType(FormConstant.UNIFIED_DELIVERY);
			pHeader.setFormTime(businessDate);
			String providerId = (String) bill.get("providerId");
			pHeader.setProviderId(providerId);
			pHeader.setProvider((String) bill.get("provider"));
			pHeader.setFormMakerId(userId);
			pHeader.setFormMaker(formMaker);
			pHeader.setFormNote(formNote);

			String maxPayItem = saveDetail(pFormId, providerMap.get(bill.get("key")));
			pHeader.setMaxPayItem(maxPayItem);
			purchasingHeaderBean.saveEntity(pHeader);

			OperateLogUtil.record(pFormId, LogType.CREATE, "手动生成采购单");

			formStatusBean.saveEntity(new FormStatus(pFormId, BillStatus.UNADUIT_CN, userId));
			insertOperationVerion(pFormId, userId);
		}
		return idLst;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void insertOperationVerion(String formId, String userId) throws NoPrivilegeException, SQLException,
			NoConnection {
		operationVersionBean.saveEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				OperationVersion.INIT_VERSION, OperationVersion.SAVED, formId));
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
		double payAmt = -1.0;
		String itemName = "";

		for (Map<String, Object> detail : detailLst) {
			PurchasingDetail pDetail = new PurchasingDetail();
			pDetail.setFormId(pFormId);
			pDetail.setItemId((String) detail.get("itemId"));
			pDetail.setItemName((String) detail.get("itemName"));
			pDetail.setItemDimension((String) detail.get("itemDimension"));
			Object itemSpecification = detail.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				pDetail.setItemSpecification((String) itemSpecification);
			}
			pDetail.setItemCategory((String) detail.get("itemCategory"));
			pDetail.setItemCount(Double.parseDouble(detail.get("itemCount") + ""));
			pDetail.setItemUnitPrice(Double.parseDouble(detail.get("itemUnitPrice") + ""));

			double amt = Double.parseDouble(detail.get("payAmt").toString());
			pDetail.setPayAmt(amt);
			pDetail.setReceivePrice(Double.parseDouble(detail.get("receivePrice") + ""));
			pDetail.setReceiveAmt(Double.parseDouble(detail.get("receiveAmt").toString()));

			purchasingDetailBean.saveEntity(pDetail);

			if (amt > payAmt) {
				payAmt = amt;
				itemName = '[' + (String) detail.get("itemId") + ']' + (String) detail.get("itemName");
			}
		}
		return itemName;
	}

	// 采购导入
	public JSONObject doImport(File file) throws NoPrivilegeException, SQLException, NoConnection {
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		try {
			detailMap = readExcel(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject result = new JSONObject();
		result.put("existLst", importRequestDao.getExistPurchase(detailMap));
		return result;
	}

	// 读取execl文件，暂只支持execl2003版本
	public Map<String, Map<String, Object>> readExcel(File file) throws FileNotFoundException, IOException {
		BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(inStream);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		DecimalFormat df = new DecimalFormat("####");
		DecimalFormat dd = new DecimalFormat("###0.0##");

		// 第一行为标题，不取
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			HSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				continue;
			}

			Cell cell = row.getCell(0);
			if (cell == null) {
				continue;
			}

			String itemId = "";

			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				itemId = df.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				itemId = cell.getStringCellValue();
			}

			String itemCount = "";
			cell = row.getCell(1);
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				itemCount = dd.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				itemCount = cell.getStringCellValue();
			}

			Map<String, Object> item = new HashMap<String, Object>();
			item.put("rownumber", rowIndex + 1);// 存储导入数据的行号，方便最后给出错误提示
			item.put("itemCount", itemCount);
			detailMap.put(itemId, item);
		}

		inStream.close();
		return detailMap;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setImportRequestDao(ImportRequestDao importRequestDao) {
		this.importRequestDao = importRequestDao;
	}

}
