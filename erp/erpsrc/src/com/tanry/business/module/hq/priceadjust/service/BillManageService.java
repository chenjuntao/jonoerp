/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Feb 13, 2015 by liyzh
 * Last edited on Feb 13, 2015 by liyzh
 * 
 * 说明： 调价单管理
 */
package com.tanry.business.module.hq.priceadjust.service;

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
import logic.form.PriceAdjustDetailBean;
import logic.form.PriceAdjustHeaderBean;
import logic.module.hq.PriceAdjustQueryBean;
import logic.store.ItemPriceBean;
import logic.store.TherapyBean;
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
import pojo.form.PriceAdjustDetail;
import pojo.form.PriceAdjustHeader;
import pojo.store.ItemPrice;
import pojo.store.Therapy;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;
import com.tanry.framework.util.constant.PriceType;

import dao.price.PriceAdjustDao;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class BillManageService {

	private PriceAdjustHeaderBean priceAdjustHeaderBean;
	private PriceAdjustDetailBean priceAdjustDetailBean;
	private ItemPriceBean itemPriceBean;
	private FormStatusBean formStatusBean;
	private PriceAdjustDao priceAdjustDao;
	private TherapyBean therapyBean;
	private PriceAdjustQueryBean priceAdjustQueryBean;

	/**
	 * 保存新的调价单
	 * 
	 * @param userId
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createBill(String userId, PriceAdjustHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);

		priceAdjustHeaderBean.saveEntity(header);
		String formId = header.getFormId();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			PriceAdjustDetail detail = new PriceAdjustDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemDimension(json.getString("itemDimension"));

			Object itemSpecification = json.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				detail.setItemSpecification((String) itemSpecification);
			}

			detail.setOldPrice(json.getDouble("oldPrice"));
			detail.setNewPrice(json.getDouble("newPrice"));

			priceAdjustDetailBean.saveEntity(detail);
		}

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.UNADUIT_CN, userId));
		OperateLogUtil.record(formId, LogType.CREATE, "新增调价单");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doInvalid(String[] formIds, String userId) throws NoPrivilegeException, SQLException, NoConnection {
		for (int i = 0; i < formIds.length; i++) {
			String itemForm = formIds[i];
			formStatusBean.saveEntity(new FormStatus(itemForm, "已作废", userId));
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateBill(String userId, PriceAdjustHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);

		priceAdjustHeaderBean.updateEntity(header);
		String formId = header.getFormId();
		List<String> itemIdLst = new ArrayList<String>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			PriceAdjustDetail detail = new PriceAdjustDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);
			detail.setItemPackager(json.getDouble("itemPackager"));
			detail.setOldPrice(json.getDouble("oldPrice"));
			detail.setNewPrice(json.getDouble("newPrice"));

			if (!TextUtil.isEmpty(json.getString("formId"))) {// 这个条件综合应用了前台的数据操作方式
				priceAdjustDetailBean.updateEntity(detail);
			} else {
				priceAdjustDetailBean.saveEntity(detail);
			}
			itemIdLst.add(itemId);
		}
		// 删除除此之外的其它记录
		priceAdjustDetailBean.deleteExcept(formId, itemIdLst);
	}

	public int checkEnabled(PriceAdjustHeader header) throws NoPrivilegeException, SQLException, NoConnection {
		if (itemPriceBean.checkEnabled(header.getFormId()) != 0)
			return 0;
		return 1;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, PriceAdjustHeader header, String jsonData) throws NoPrivilegeException,
			SQLException, NoConnection {
		updateBill(userId, header, jsonData);
		JSONArray arr = JSONArray.fromObject(jsonData);

		String itemIds = "";
		if ("immediate".equals(header.getEffectType())) {
			// 使价格生效
			String priceType = header.getAdjustType();
			String supplierId = header.getSupplierId();
			for (Object obj : arr) {
				JSONObject json = (JSONObject) obj;
				String itemId = json.getString("itemId");
				double newPrice = json.getDouble("newPrice");

				PriceAdjustEffect(itemId, priceType, newPrice, supplierId);

				if (priceType.equals(PriceType.PURCHASE)) {
					itemIds += itemId + ",";
				}
			}
		}
		if (!TextUtil.isEmpty(itemIds)) {
			updatePrice(itemIds, "SEMIS");// 根据原料进货价修改半成品成本价
		}

		formStatusBean.saveEntity(new FormStatus(header.getFormId(), BillStatus.AUDITED_CN, userId));
		OperateLogUtil.record(header.getFormId(), LogType.AUDIT, "审核调价单");
	}

	// 定时生效的采购调价单生效, by cjt
	public void CronEffect(Date businessDate) throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = priceAdjustQueryBean.queryItem("", businessDate);
		String itemIds = "";
		for (Map<String, Object> item : itemLst) {
			String itemId = (String) item.get("itemId");
			String priceType = (String) item.get("priceType");
			double newPrice = (Double) item.get("newPrice");
			String supplierId = (String) item.get("supplierId");

			PriceAdjustEffect(itemId, priceType, newPrice, supplierId);

			if (priceType.equals(PriceType.PURCHASE)) {
				itemIds += itemId + ",";
			}
		}
		if (!TextUtil.isEmpty(itemIds)) {
			updatePrice(itemIds, "SEMIS");// 根据原料进货价修改半成品成本价
		}
	}

	// 调价单生效（将调价单中的价格拷贝进ItemPrice表中）,by cjt
	private void PriceAdjustEffect(String itemId, String priceType, double newPrice, String supplierId)
			throws NoPrivilegeException, SQLException, NoConnection {
		ItemPrice price = new ItemPrice();
		price.setItemId(itemId);
		price.setPriceType(priceType);
		price.setItemPrice(newPrice);
		itemPriceBean.saveEntity(price);

		// 向前台推送数据（通过symmetricDS方式）
		if (priceType.startsWith("SALE")) {
			itemPriceBean.PushSalePrice(itemId, priceType, newPrice);
		}

		// 除了修改统一的进货价之外，修改相应供应商的价格
		if (PriceType.PURCHASE.equals(priceType)) {
			price.setPriceType(PriceType.SUPPLIER);
			price.setSupplierId(supplierId);
			itemPriceBean.saveEntity(price);
		}
	}

	/**
	 * 根据原料编码更新包含该原料的配方成本价
	 */
	public void updatePrice(String itemIds, String itemType) throws NoPrivilegeException, SQLException, NoConnection {
		// 根据原料编码查询包含此原料的配方集合
		List<Therapy> therapys = therapyBean.GetTherapys(itemIds, itemType);
		priceAdjustDao.computePrice(therapys);
	}

	public void doUpdate() throws NoPrivilegeException, SQLException, NoConnection {
		priceAdjustDao.computeCount();
	}

	public void doBack(String startDate, String endDate, String branchId, String branchType)
			throws NoPrivilegeException, SQLException, NoConnection {
		priceAdjustDao.computeBack(startDate, endDate, branchId, branchType);
	}

	/**
	 * 根据配方编码更新配方成本价
	 */
	public void computePrice(String therapyIds, String itemType) throws NoPrivilegeException, SQLException,
			NoConnection {
		// 根据配方编码查询该配方的成本价集合
		List<Therapy> therapys = therapyBean.GetTherapysAmt(therapyIds, itemType);
		priceAdjustDao.computePrice(therapys);
	}

	/**
	 * 调价单刪除
	 * 
	 * @param formId
	 */
	public void deleteBill(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		priceAdjustHeaderBean.deleteEntity(formId);
		priceAdjustDetailBean.deleteByForm(formId);
	}

	// 商品调价单导入
	public JSONObject doImport(File file, String priceType) throws NoPrivilegeException, SQLException, NoConnection {
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		try {
			detailMap = readExcel(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject result = new JSONObject();
		result.put("existLst", priceAdjustDao.getItem(priceType, detailMap));
		return result;
	}

	// 读取execl文件，暂只支持execl2003版本
	public Map<String, Map<String, Object>> readExcel(File file) throws FileNotFoundException, IOException {
		BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(inStream);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		DecimalFormat df = new DecimalFormat("#.##");

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

			String newPrice = "";
			cell = row.getCell(1);
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				newPrice = df.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				newPrice = cell.getStringCellValue();
			}

			Map<String, Object> item = new HashMap<String, Object>();
			item.put("rownumber", rowIndex + 1);// 存储导入数据的行号，方便最后给出错误提示
			item.put("newPrice", newPrice);
			detailMap.put(itemId, item);
		}

		inStream.close();
		return detailMap;
	}

	public void setPriceAdjustHeaderBean(PriceAdjustHeaderBean priceAdjustHeaderBean) {
		this.priceAdjustHeaderBean = priceAdjustHeaderBean;
	}

	public void setPriceAdjustDetailBean(PriceAdjustDetailBean priceAdjustDetailBean) {
		this.priceAdjustDetailBean = priceAdjustDetailBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setPriceAdjustDao(PriceAdjustDao priceAdjustDao) {
		this.priceAdjustDao = priceAdjustDao;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public void setPriceAdjustQueryBean(PriceAdjustQueryBean priceAdjustQueryBean) {
		this.priceAdjustQueryBean = priceAdjustQueryBean;
	}
}
