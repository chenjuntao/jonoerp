package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.DiscountBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.DiscountBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class DiscountExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;
	private String period;
	private String shift;

	private DiscountBean discountBean;
	private String type;

	public void setDiscountBean(DiscountBean discountBean) {
		this.discountBean = discountBean;
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

		List<DiscountBill> discountBills = discountBean.getDiscount(startDate, endDate, shopC, period, shift, 0,
				Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> discountLst = new ArrayList<Map<String, Object>>();

		for (DiscountBill item : discountBills) {
			Map<String, Object> discountMap = new HashMap<String, Object>();
			discountMap.put("businessDate", item.getBusinessDate());
			discountMap.put("shift", item.getShift());
			discountMap.put("period", item.getPeriod());
			discountMap.put("billC", item.getBillC());
			discountMap.put("table", item.getTable());
			discountMap.put("shopC", item.getShopC());
			discountMap.put("shopN", item.getShopN());
			discountMap.put("foodAmt", item.getFoodAmt());
			discountMap.put("disAmt", item.getDisAmt());
			discountMap.put("payAmt", item.getPayAmt());
			discountMap.put("disMan", item.getDisMan());
			discountMap.put("disWhy", item.getDisWhy());

			discountLst.add(discountMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, discountLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(discountLst);
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
