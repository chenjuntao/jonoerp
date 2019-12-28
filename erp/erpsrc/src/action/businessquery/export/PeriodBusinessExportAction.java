package action.businessquery.export;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.PeriodBusinessBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class PeriodBusinessExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private PeriodBusinessBean periodBusinessBean;

	private String startDate;
	private String endDate;
	private String shopC;

	private String type;

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void export() throws NoPrivilegeException, SQLException, NoConnection, UnsupportedEncodingException {
		StringBuilder sb2 = new StringBuilder();
		sb2.append(" 1=1 ");

		if (!TextUtil.isEmpty(startDate)) {
			sb2.append(" AND DBUSINESS >= to_date('" + startDate + "', 'yyyy-mm-dd') ");
		}

		if (!TextUtil.isEmpty(endDate)) {
			sb2.append(" AND DBUSINESS <= to_date('" + endDate + "', 'yyyy-mm-dd') ");
		}

		if (!TextUtil.isEmpty(shopC)) {
			sb2.append(" AND b.CBRANCH_C = '" + shopC + "' ");
		}
		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map> itemsLst2 = periodBusinessBean.listPeriodBusiness(sb2.toString());

		List<Map<String, Object>> itemsLst = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < itemsLst2.size(); i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map = itemsLst2.get(i);
			itemsLst.add(map);
		}

		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, itemsLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(itemsLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public void setPeriodBusinessBean(PeriodBusinessBean periodBusinessBean) {
		this.periodBusinessBean = periodBusinessBean;
	}

}
