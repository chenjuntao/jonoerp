package com.tanry.framework.util;

import java.io.IOException;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class MyEventHandler implements IEventHandler {
	private String companyName;

	public MyEventHandler(String companyName) {
		this.companyName = companyName;
	}

	public void handleEvent(Event event) {
		PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		PdfDocument pdfDoc = docEvent.getDocument();
		PdfPage page = docEvent.getPage();
		int pageNumber = pdfDoc.getPageNumber(page);
		Rectangle pageSize = page.getPageSize();
		PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

		PdfFont helvetica = null;
		try {
			helvetica = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PdfFont helveticaBold = null;
		try {
			helveticaBold = PdfFontFactory.createFont("STSongStd-Light", "UniGB-UCS2-H", false);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Add header and footer
		pdfCanvas.beginText().setFontAndSize(helvetica, 12)
				.moveText(pageSize.getWidth() / 2 - 60, pageSize.getTop() - 20)
				.showText(companyName + "              " + "直配采购单      " + String.valueOf("第" + pageNumber + "页"))
				.endText();

		pdfCanvas.release();
	}
}
