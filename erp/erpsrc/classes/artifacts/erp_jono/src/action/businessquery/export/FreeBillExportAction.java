package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FreeBillBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class FreeBillExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;
	private String period;
	private String shift;

	private FreeBillBean freeBillBean;
	private String type;

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	private String getQueryText(String startDate, String endDate, String shopC, String period, String shift) {
		StringBuilder sb = new StringBuilder();
		if (!TextUtil.isEmpty(startDate)) {
			sb.append("  AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')>= '" + startDate + "' ");
		}

		if (!TextUtil.isEmpty(endDate)) {
			sb.append("  AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')<= '" + endDate + "' ");
		}

		sb.append("  AND bs.eSuitFlag <> '套餐子项' AND bs.eRetSendFlag <> '赠送' ");

		if (!TextUtil.isEmpty(shopC)) {
			sb.append("  AND  b.CBRANCH_C = '" + shopC + "' ");
		}

		if (!TextUtil.isEmpty(period)) {
			sb.append("  AND  b.CPERIOD_N = '" + period + "' ");
		}

		if (!TextUtil.isEmpty(shift)) {
			sb.append("  AND  b.CSHIFT_N = '" + shift + "' ");
		}

		return sb.toString();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void export() throws NoPrivilegeException, SQLException, NoConnection {
		String queryText = getQueryText(startDate, endDate, shopC, period, shift);
		List<Map> freeBills = freeBillBean.getDetail(queryText, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> freeBillCodeLst = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < freeBills.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map = freeBills.get(i);
			freeBillCodeLst.add(map);
		}

		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, freeBillCodeLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(freeBillCodeLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFreeBillBean(FreeBillBean freeBillBean) {
		this.freeBillBean = freeBillBean;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public String getStartDate() {
		if (TextUtil.isEmpty(startDate)) {
			startDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
		}
		return startDate;
	}

	public String getEndDate() {
		if (TextUtil.isEmpty(endDate)) {
			endDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
		}
		return endDate;
	}
}
