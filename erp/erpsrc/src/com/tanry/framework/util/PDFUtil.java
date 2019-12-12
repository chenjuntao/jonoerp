package com.tanry.framework.util;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;

import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.property.TextAlignment;

public class PDFUtil {

	static PdfFont helvetica = null;
	static PdfFont helveticaBold = null;
	static String[] titleArr = { "序号", "商品编码", "商品名称", "单位", "规格", "订货数量", "进货价", "进货金额" };

	public static void exportPdf(String bigFormId, String littleFormId, List<PurchasingDetail> details,
			PurchasingHeader purchasingHeader, String companyName) throws IOException {
		helvetica = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		helveticaBold = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);

		HttpServletResponse response = ServletActionContext.getResponse();
		System.out.println(String.format("HH:MM:SS：%tT%n", new Date()));
		response.reset();
		response.setHeader("Expires", "0");
		response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
		response.setHeader("Pragma", "public");
		response.setContentType("application/pdf; charset=UTF-8");
		response.setHeader("Content-disposition", "attachment; filename="
				+ new String((bigFormId + "-" + littleFormId + ".pdf").getBytes("GBK"), "iso8859-1"));

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Cookie cDate = new Cookie("lastVisited", format.format(new java.util.Date()));
		response.addCookie(cDate);

		OutputStream outFile = response.getOutputStream();

		PdfWriter writer = new PdfWriter(outFile);

		PdfDocument pdf = new PdfDocument(writer);
		pdf.addEventHandler(PdfDocumentEvent.END_PAGE, new MyEventHandler(companyName));

		Document document = new Document(pdf, PageSize.A4);

		document.add(new Paragraph("单据编号：").add((new Text(littleFormId).setUnderline())).add("      ").add("收货部门：")
				.add((new Text(purchasingHeader.getReceiver()).setUnderline())).setFont(helveticaBold).setFontSize(10));
		document.add(new Paragraph("供应商：").add((new Text(purchasingHeader.getProvider()).setUnderline()))
				.setFont(helveticaBold).setFontSize(10));
		document.add(new Paragraph("订货日期：")
				.add((new Text(DateTimeUtil.getDateStr(purchasingHeader.getFormTime())).setUnderline())).add("      ")
				.add("到货日期：")
				.add((new Text(DateTimeUtil.getDateStr(purchasingHeader.getReceiveTime())).setUnderline()))
				.add("      ").add("审核日期：")
				.add((new Text(DateTimeUtil.getDateStr(purchasingHeader.getAuditTime())).setUnderline()))
				.setFont(helveticaBold).setFontSize(10));

		Table table = new Table(new float[] { 5, 10, 20, 10, 10, 10, 10, 10 });
		table.setWidthPercent(100);

		for (int i = 0; i < titleArr.length; i++) {
			table.addHeaderCell(new Cell()
					.add(new Paragraph(titleArr[i]).setTextAlignment(TextAlignment.CENTER).setFont(helveticaBold))
					.setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f)));
		}

		for (int i = 0; i < details.size(); i++) {
			PurchasingDetail purchasingDetail = details.get(i);
			String itemId = purchasingDetail.getItemId();
			itemId = itemId == null ? "" : itemId;

			String itemName = purchasingDetail.getItemName();
			itemName = itemName == null ? "" : itemName;

			String itemDimension = purchasingDetail.getItemDimension();
			itemDimension = itemDimension == null ? "" : itemDimension;

			String itemSpecification = purchasingDetail.getItemSpecification();
			itemSpecification = itemSpecification == null ? "" : itemSpecification;

			table.addCell(new Cell()
					.add(new Paragraph(String.valueOf(purchasingDetail.getRownumber())).setTextAlignment(
							TextAlignment.CENTER).setFont(helvetica)).setFontSize(9)
					.setBorder(new SolidBorder(Color.BLACK, 0.5f)));
			table.addCell(new Cell()
					.add(new Paragraph(itemId).setTextAlignment(TextAlignment.CENTER).setFont(helvetica))
					.setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f)));

			table.addCell(new Cell().add(new Paragraph(itemName)).setFont(helvetica)).setFontSize(9)
					.setBorder(new SolidBorder(Color.BLACK, 0.5f));

			table.addCell(
					new Cell().add(new Paragraph(itemDimension)).setTextAlignment(TextAlignment.CENTER)
							.setFont(helvetica)).setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f));

			table.addCell(new Cell().add(new Paragraph(itemSpecification)).setFont(helvetica)).setFontSize(9)
					.setBorder(new SolidBorder(Color.BLACK, 0.5f));

			table.addCell(
					new Cell()
							.add(new Paragraph(purchasingDetail.getItemCount() == null ? "" : purchasingDetail
									.getItemCount().toString())).setTextAlignment(TextAlignment.RIGHT)
							.setFont(helvetica)).setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f));

			table.addCell(
					new Cell()
							.add(new Paragraph(purchasingDetail.getReceivePrice() == null ? "" : purchasingDetail
									.getReceivePrice().toString())).setTextAlignment(TextAlignment.RIGHT)
							.setFont(helvetica)).setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f));
			table.addCell(
					new Cell()
							.add(new Paragraph(purchasingDetail.getReceiveAmt() == null ? "" : purchasingDetail
									.getReceiveAmt().toString())).setTextAlignment(TextAlignment.RIGHT)
							.setFont(helvetica)).setFontSize(9).setBorder(new SolidBorder(Color.BLACK, 0.5f));
		}
		document.add(table);

		document.close();
		outFile.flush();

		outFile.close();
		System.out.println(String.format("HH:MM:SS：%tT%n", new Date()));
	}
}
