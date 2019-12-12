/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 14, 2014 by liyzh
 * Last edited on Aug 14, 2014 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.framework.util.xls;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * @author liyzh
 * 
 */
public class ExportUtil {
	public static Workbook export(JSONObject structure, JSONObject data) {

		ExcelInst ei = new ExcelInst();
		Workbook wb = ei.hwb;
		Sheet sheet = wb.createSheet();

		// 设置表单名称
		String sheetName = structure.getString("sheetName");
		wb.setSheetName(0, sheetName);
		// sheet.setDisplayGridlines(false);

		JSONArray colsJA = structure.getJSONArray("columns");
		int colCnt = colsJA.size();
		// 设置标题
		JSONObject titleJO = structure.getJSONObject("title");
		String title = titleJO.getString("text");
		setTitle(wb, sheet, title, colCnt);

		// 设置表头

		setHeader(wb, sheet, colsJA);

		// 设置数据区域
		JSONArray dataJA = data.getJSONArray("rows");
		setDatazone(wb, sheet, colsJA, dataJA);

		return wb;
	}

	public static Workbook sheetExport(Workbook wb, Sheet sheet, JSONObject structure, JSONObject data) {
		JSONArray colsJA = structure.getJSONArray("columns");
		int colCnt = colsJA.size();
		// 设置标题
		JSONObject titleJO = structure.getJSONObject("title");
		String title = titleJO.getString("text");
		setTitle(wb, sheet, title, colCnt);

		// 设置表头

		setHeader(wb, sheet, colsJA);

		// 设置数据区域
		JSONArray dataJA = data.getJSONArray("rows");
		setDatazone(wb, sheet, colsJA, dataJA);

		return wb;
	}

	/**
	 * 设置标题
	 * 
	 * @param wb
	 * @param sheet
	 * @param colsJA
	 * @param headerStyle
	 */
	public static void setTitle(Workbook wb, Sheet sheet, String title, int colCnt) {
		Row row = sheet.createRow(0);
		Cell cell = row.createCell(0);
		cell.setCellValue(title);

		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, colCnt - 1));
		row.setHeightInPoints(20);

		CellStyle titleStyle = wb.createCellStyle();
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 16);
		font.setFontName("宋体");// 字体样式
		font.setColor(ExcelInst.fontColor);
		titleStyle.setFont(font);
		titleStyle.setAlignment(CellStyle.ALIGN_CENTER);// 居中
		titleStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		cell.setCellStyle(titleStyle);
	}

	public static void setHeader(Workbook wb, Sheet sheet, JSONArray colsJA) {
		// 设置表头
		Row row = sheet.createRow(2);
		// row.seteightInPoints(18);

		String[] fields = new String[colsJA.size()];
		int colCnt = colsJA.size();
		for (int i = 0; i < colCnt; i++) {
			JSONObject colJO = (JSONObject) colsJA.get(i);
			String text = colJO.getString("display");
			String field = colJO.getString("name");
			fields[i] = field;
			int width = colJO.getInt("width");
			Cell cell = row.createCell(i);
			cell.setCellValue(text);

			sheet.setColumnWidth(i, width * 35);
			cell.setCellStyle(ExcelInst.sHeader);
		}
	}

	/**
	 * 设置数据区域
	 * 
	 * @param wb
	 * @param sheet
	 * @param colsJA
	 * @param dataJA
	 * @param dataStyle
	 * @return
	 */
	private static final int MAX_EXCEL_ROW_NUMBERS = 10000;

	public static int setDatazone(Workbook wb, Sheet sheet, JSONArray colsJA, JSONArray dataJA) {
		int rowCnt = 2;
		Map<String, Object> sumMap = new HashMap<String, Object>();
		for (int i = 0; i < dataJA.size(); i++) {
			if (i >= MAX_EXCEL_ROW_NUMBERS) {
				break;
			}

			Row row = sheet.createRow(++rowCnt);
			// row.setHeightInPoints(18);
			JSONObject dataJO = (JSONObject) dataJA.get(i);
			int colCnt = colsJA.size();
			for (int j = 0; j < colCnt; j++) {
				JSONObject colJO = (JSONObject) colsJA.get(j);
				String align = colJO.getString("align");
				Cell cell = row.createCell(j);
				cell.setCellStyle(getDataStyle(rowCnt, align));

				String field = colJO.getString("name");

				if (dataJO.get(field) == null) {
					continue;
				}
				String value = dataJO.getString(field);
				// Integer iValue = 0;
				// Pattern pattern = Pattern.compile("[0-9]*");
				// Matcher matcher = pattern.matcher(value);
				// if (matcher.matches() && !TextUtil.isEmpty(value)) {
				// iValue = new Integer(value);
				// cell.setCellValue(iValue);
				// }else{
				// cell.setCellValue(value);
				// }

				try {
					Double dvalue = Double.valueOf(value);
					cell.setCellValue(dvalue);
				} catch (Exception e) {
					cell.setCellValue(value);
				}

				JSONObject sumJO = (JSONObject) colJO.get("sum");
				if (sumJO != null) {
					Boolean count = (Boolean) sumJO.get("count");
					if (!count) {// 设置合计文本
						if (sumMap.get(field) == null) {// 只设置一次
							String text = (String) sumJO.get("text");
							sumMap.put(field, text);
						}
					} else if (count) {// 累计合计值
						BigDecimal sum = (BigDecimal) sumMap.get(field);
						if (sum == null) {
							sum = BigDecimal.ZERO;
						}
						sumMap.put(field, sum.add(new BigDecimal(value)));
					}
				}
			}
		}
		setSumRow(wb, sheet, colsJA, sumMap, rowCnt);
		return rowCnt;
	}

	private static int setSumRow(Workbook wb, Sheet sheet, JSONArray colsJA, Map<String, Object> sumMap, int rowCnt) {
		Row row = sheet.createRow(++rowCnt);
		int colCnt = colsJA.size();
		for (int j = 0; j < colCnt; j++) {
			JSONObject colJO = (JSONObject) colsJA.get(j);
			String align = colJO.getString("align");
			Cell cell = row.createCell(j);
			cell.setCellStyle(getDataStyle(rowCnt, align));

			String field = colJO.getString("name");

			if (sumMap.get(field) == null) {
				continue;
			}
			Object value = sumMap.get(field);
			cell.setCellValue(value.toString());
		}
		return rowCnt;
	}

	private static CellStyle getDataStyle(int rowIndex, String align) {
		Style type = Style.left;
		// 设置斑马色
		// if (rowIndex % 2 == 0) {
		// if ("right".equals(align)) {
		// type = Style.rightalt;
		// } else if ("center".equals(align)) {
		// type = Style.centeralt;
		// } else {
		// type = Style.leftalt;
		// }
		// } else {
		if ("right".equals(align)) {
			type = Style.right;
		} else if ("center".equals(align)) {
			type = Style.center;
		} else {
			type = Style.left;
		}
		// }
		CellStyle style;
		switch (type) {
		case right:
			style = ExcelInst.sRight;
			break;
		case center:
			style = ExcelInst.sCenter;
			break;
		case rightalt:
			style = ExcelInst.sRightAlt;
			break;
		case centeralt:
			style = ExcelInst.sCenterAlt;
			break;
		case leftalt:
			style = ExcelInst.sLeftAlt;
			break;
		default:
			style = ExcelInst.sLeft;
		}
		return style;
	}
}
