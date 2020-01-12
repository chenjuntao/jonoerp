package com.tanry.business.function;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.PDFUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ExportAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String jsonData;
	private String type;
	private String downloadTokenValue;

	private String littleFormId;

	private String bigFormId;

	private PurchasingDetailBean purchasingDetailBean;
	private PurchasingHeaderBean purchasingHeaderBean;

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setLittleFormId(String littleFormId) {
		this.littleFormId = littleFormId;
	}

	public void setBigFormId(String bigFormId) {
		this.bigFormId = bigFormId;
	}

	public void exportPdf() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		List<PurchasingDetail> details = purchasingDetailBean.query(littleFormId, "Y");
		PurchasingHeader purchasingHeader = purchasingHeaderBean.queryById(littleFormId);
		PDFUtil.exportPdf(bigFormId, littleFormId, details, purchasingHeader, getSessionMap().get("companyName")
				.toString());
		getSessionMap().put("downloadTokenValue", downloadTokenValue);
	}

	@SuppressWarnings("unchecked")
	public void export() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");
		String sheetName = structure.getString("sheetName");

		JSONArray contentArr = structure.getJSONArray("content");
		if ("csv".equals(type)) {
			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();

			for (int i = 0; i < contentArr.size(); i++) {
				Map<String, Object> map = (Map<String, Object>) contentArr.get(i);
				maplist.add(map);
			}

			StringBuffer sb = CsvUtil.exportCsv(colsJA, maplist);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONObject data = new JSONObject();
		data.put("rows", contentArr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void isFinish() throws NoPrivilegeException, SQLException, NoConnection {

		JSONObject result = new JSONObject();
		result.put("msg", getSessionMap().get("downloadTokenValue"));
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getDownloadTokenValue() {
		return downloadTokenValue;
	}

	public void setDownloadTokenValue(String downloadTokenValue) {
		this.downloadTokenValue = downloadTokenValue;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

}
