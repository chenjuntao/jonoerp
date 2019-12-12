package com.tanry.framework.util.xls;

import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

public class ExcelInst {

	public HSSFWorkbook hwb;

	/**
	 * 表头字体颜色
	 */
	public static final short headerColor = 8;
	/**
	 * 表头背景色
	 */
	public static final short headerBColor = 9;
	/**
	 * 表格边框颜色
	 */
	public static final short borderColor = 10;
	/**
	 * 数据行字体颜色
	 */
	public static final short fontColor = 11;

	/**
	 * 合计行背景色
	 */
	public static final short sumColor = 12;

	/**
	 * 偶数行斑马色
	 */
	public static final short altColor = 13;

	/**
	 * 分区块边框色
	 */
	public static final short blockColor = 14;

	{
		hwb = new HSSFWorkbook();// 颜色没办法通用，这里必须先实际化一个老版本的类
		initColor();
		initStyle();
	}

	private void initColor() {
		HSSFPalette customPalette = hwb.getCustomPalette();
		customPalette.setColorAtIndex((short) 8, (byte) 6, (byte) 126,
				(byte) 187);
		customPalette.setColorAtIndex((short) 9, (byte) 238, (byte) 250,
				(byte) 255);
		customPalette.setColorAtIndex((short) 10, (byte) 187, (byte) 225,
				(byte) 241);
		customPalette.setColorAtIndex((short) 11, (byte) 102, (byte) 102,
				(byte) 102);
		customPalette.setColorAtIndex((short) 12, (byte) 238, (byte) 243,
				(byte) 255);
		customPalette.setColorAtIndex((short) 13, (byte) 252, (byte) 245,
				(byte) 252);
		customPalette.setColorAtIndex((short) 14, (byte) 0, (byte) 136,
				(byte) 102);
	}

	private void initStyle() {
		sHeader = hwb.createCellStyle();
		Font headerFont = hwb.createFont();
		headerFont.setFontHeightInPoints((short) 10);
		headerFont.setFontName("宋体");// 字体样式
		headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
		// headerFont.setColor(ExcelInst.headerColor);
		sHeader.setFont(headerFont);
		sHeader.setWrapText(true);
		sHeader.setAlignment(CellStyle.ALIGN_CENTER);// 居中
		sHeader.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
		// sHeader.setFillPattern(CellStyle.SOLID_FOREGROUND);
		// sHeader.setFillForegroundColor(ExcelInst.headerBColor);//
		// 真是奇怪，前景色与背景色搞不明白

		Font dataFont = hwb.createFont();
		dataFont.setColor(ExcelInst.fontColor);
		dataFont.setFontHeightInPoints((short) 10);
		dataFont.setFontName("宋体");// 字体样式
		sLeft = hwb.createCellStyle();
		sLeft.setFont(dataFont);
		// sLeft.setFillPattern(CellStyle.NO_FILL);
		sLeft.setWrapText(true);

		sCenter = hwb.createCellStyle();
		sCenter.cloneStyleFrom(sLeft);
		sCenter.setAlignment(CellStyle.ALIGN_CENTER);

		sRight = hwb.createCellStyle();
		sRight.cloneStyleFrom(sLeft);
		sRight.setAlignment(CellStyle.ALIGN_RIGHT);// 居右

		sLeftAlt = hwb.createCellStyle();
		sLeftAlt.cloneStyleFrom(sLeft);
		sLeftAlt.setFillPattern(CellStyle.SOLID_FOREGROUND);
		sLeftAlt.setFillForegroundColor(ExcelInst.altColor);

		sCenterAlt = hwb.createCellStyle();
		sCenterAlt.cloneStyleFrom(sLeftAlt);
		sCenterAlt.setAlignment(CellStyle.ALIGN_CENTER);

		sRightAlt = hwb.createCellStyle();
		sRightAlt.cloneStyleFrom(sLeftAlt);
		sRightAlt.setAlignment(CellStyle.ALIGN_RIGHT);// 居右
	}

	public static CellStyle sHeader;
	public static CellStyle sLeft;
	public static CellStyle sCenter;
	public static CellStyle sRight;
	public static CellStyle sLeftAlt;
	public static CellStyle sCenterAlt;
	public static CellStyle sRightAlt;
}

enum Style {
	left, center, right, leftalt, centeralt, rightalt
}