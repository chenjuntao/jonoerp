package action.restaurant.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.RequestHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.RequestHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class GoodsBillExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private RequestHeaderBean requestHeaderBean;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String type;

	private FormStatusBean formStatusBean;

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	private String nullToStr(String str) {
		if (TextUtil.isEmpty(str)) {
			return "";
		}

		return str;
	}

	public void export() throws NoPrivilegeException, SQLException, NoConnection {

		List<RequestHeader> headerLst = requestHeaderBean.query(startDate, endDate, branchId, 0, Integer.MAX_VALUE,
				"request", null);// formType为餐厅要货

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> transferHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (RequestHeader obj : headerLst) {
			RequestHeader requestHeader = (RequestHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("rownumber", rownumber++);

			// 要货单编号
			itemMap.put("formId", requestHeader.getFormId());

			// 要货部门
			itemMap.put("buyerName", requestHeader.getBuyerName());

			// 制单人
			itemMap.put("formMaker", requestHeader.getFormMaker());

			// 制单日期
			String formTime = getDateStr(requestHeader.getFormTime());
			itemMap.put("formTime", formTime);

			// 到货日期
			String receiveTime = getDateStr(requestHeader.getReceiveTime());
			itemMap.put("receiveTime", receiveTime);

			// 备注
			itemMap.put("formNote", nullToStr(requestHeader.getFormNote()));

			// 主要要货
			itemMap.put("maxPayItem", nullToStr(requestHeader.getMaxPayItem()));

			// 要货总额
			itemMap.put("allPayAmt", requestHeader.getAllPayAmt());

			// 单据状态
			String formStatus = formStatusBean.getCurrentStatus(requestHeader.getFormId());
			itemMap.put("formStatus", formStatus);

			transferHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, transferHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(transferHeaderLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
