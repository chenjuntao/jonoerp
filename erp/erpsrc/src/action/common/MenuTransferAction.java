/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 19, 2015 by liyzh
 * Last edited on Mar 19, 2015 by liyzh
 * 
 * 说明： 菜单批量转换工具类
 */
package action.common;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.tanry.framework.util.xls.ExcelInst;

public class MenuTransferAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String jsonData;

	public void transfer() throws Exception {
		JSONObject json = JSONObject.fromObject(jsonData);

		ExcelInst ei = new ExcelInst();
		Workbook wb = ei.hwb;
		Sheet sheet = wb.createSheet();

		int rowCnt = 1;
		for (Object name : json.keySet()) {
			Object url = json.get(name);
			Row row = sheet.createRow(++rowCnt);
			Cell cell = row.createCell(1);
			cell.setCellValue(name.toString());
			cell = row.createCell(5);
			cell.setCellValue(url.toString());
		}
		try {
			this.outXls("test" + ".xls", wb);
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

}