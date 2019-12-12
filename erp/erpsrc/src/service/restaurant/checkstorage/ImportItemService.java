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
package service.restaurant.checkstorage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckHeaderBean;
import logic.form.FormStatusBean;
import net.sf.json.JSONObject;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.CheckHeader;
import pojo.form.FormStatus;

import com.tanry.business.form.FormConstant;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.constant.LogType;

import dao.importdata.ImportCheckItemDao;

public class ImportItemService {

	private CheckHeaderBean checkHeaderBean;
	private ImportCheckItemDao importCheckItemDao;
	private FormStatusBean formStatusBean;

	/**
	 * 读取excel信息，保存为新的盘点清单，状态为已输入
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public JSONObject doImport(String userId, CheckHeader checkHeader, File file) throws NoPrivilegeException,
			SQLException, NoConnection {
		Date formTime = checkHeader.getFormTime();
		String branchId = checkHeader.getCheckBranchId();
		int newSerial = checkHeaderBean.newSerial(formTime, branchId, FormConstant.CHECK_LIST);
		String formId = FormUtil.genFormIdBody("CL", branchId, formTime) + FormUtil.formatSerial(newSerial);
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		try {
			detailMap = readExcel(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		checkHeader.setFormId(formId);
		checkHeaderBean.saveEntity(checkHeader);
		List<Map<String, Object>> missingLst = importCheckItemDao.insertItem(formId, detailMap);
		formStatusBean.saveEntity(new FormStatus(formId, "已输入", userId));

		OperateLogUtil.record(formId, LogType.CREATE, "导入生成盘点清单");

		JSONObject result = new JSONObject();
		result.put("formId", formId);
		result.put("missingLst", missingLst);
		return result;
	}

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

			cell = row.getCell(1);
			String checkCount = "";
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				checkCount = dd.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				checkCount = cell.getStringCellValue();
			}
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("rownumber", rowIndex + 1);// 存储导入数据的行号，方便最后给出错误提示
			item.put("checkCount", checkCount);
			detailMap.put(itemId, item);
		}

		inStream.close();
		return detailMap;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

	public void setImportCheckItemDao(ImportCheckItemDao importCheckItemDao) {
		this.importCheckItemDao = importCheckItemDao;
	}

}
