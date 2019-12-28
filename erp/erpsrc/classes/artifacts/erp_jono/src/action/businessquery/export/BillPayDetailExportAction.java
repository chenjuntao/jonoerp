package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.BillPayDetailBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.BillPayDetail;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class BillPayDetailExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;
	private String period;
	private String shift;
	private String table;

	private BillPayDetailBean billPayDetailBean;

	private String type;

	public void setBillPayDetailBean(BillPayDetailBean billPayDetailBean) {
		this.billPayDetailBean = billPayDetailBean;
	}

	public void setTable(String table) {
		this.table = table;
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

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

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

	public void export() throws NoPrivilegeException, SQLException, NoConnection {

		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		if (TextUtil.isEmpty(period)) {
			period = "%%";
		}

		if (TextUtil.isEmpty(shift)) {
			shift = "%%";
		}

		if (TextUtil.isEmpty(table)) {
			table = "%%";
		}

		List<BillPayDetail> billPayDetails = billPayDetailBean.billPay(startDate, endDate, shopC, period, shift,
				table, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> billPayDetailLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (BillPayDetail obj : billPayDetails) {
			BillPayDetail item = (BillPayDetail) obj;
			Map<String, Object> billPayDetailMap = new HashMap<String, Object>();
			billPayDetailMap.put("rowNumber", rowNumber++);
			billPayDetailMap.put("billC", item.getBillC());
			billPayDetailMap.put("payN", item.getPayN());
			billPayDetailMap.put("unit", item.getUnit());
			billPayDetailMap.put("payAmt", item.getPayAmt());
			billPayDetailMap.put("businessDate", item.getBusinessDate());
			billPayDetailMap.put("table", item.getTable());
			billPayDetailMap.put("shift", item.getShift());
			billPayDetailMap.put("period", item.getPeriod());
			billPayDetailMap.put("shopC", item.getShopC());
			billPayDetailMap.put("shopN", item.getShopN());
			billPayDetailMap.put("vipC", item.getVipC());
			billPayDetailMap.put("vipN", item.getVipN());

			billPayDetailLst.add(billPayDetailMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, billPayDetailLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(billPayDetailLst);
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
