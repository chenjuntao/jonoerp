package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.ReturnBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.ReturnBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ReturnExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;
	private String period;
	private String shift;
	private ReturnBean returnBean;

	private String type;

	public void setReturnBean(ReturnBean returnBean) {
		this.returnBean = returnBean;
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

		List<ReturnBill> returnBills = returnBean.getReturn(startDate, endDate, shopC, period, shift, 0,
				Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> returnLst = new ArrayList<Map<String, Object>>();

		for (ReturnBill item : returnBills) {
			Map<String, Object> returnMap = new HashMap<String, Object>();
			returnMap.put("businessDate", item.getBusinessDate());
			returnMap.put("billC", item.getBillC());
			returnMap.put("shift", item.getShift());
			returnMap.put("period", item.getPeriod());
			returnMap.put("table", item.getTable());
			returnMap.put("foodBill", item.getFoodBill());
			returnMap.put("foodC", item.getFoodC());
			returnMap.put("foodN", item.getFoodN());
			returnMap.put("unit", item.getUnit());
			returnMap.put("price", item.getPrice());
			returnMap.put("returnAmount", item.getReturnAmount());
			returnMap.put("returnAmt", item.getReturnAmt());
			returnMap.put("returnWhy", item.getReturnWhy());
			returnMap.put("returnTime", DateTimeUtil.formatDateString(item.getReturnTime()));
			returnMap.put("backMan", item.getBackMan());
			returnMap.put("suitFlag", item.getSuitFlag());
			returnMap.put("shopC", item.getShopC());
			returnMap.put("shopN", item.getShopN());
			returnMap.put("smallC", item.getSmallC());
			returnMap.put("smallN", item.getSmallN());

			returnLst.add(returnMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, returnLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(returnLst);
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
