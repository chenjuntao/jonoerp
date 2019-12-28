package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.DiscountReasonBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.DiscountReason;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class DiscountReasonExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;

	private DiscountReasonBean discountReasonBean;

	private String type;

	public void setType(String type) {
		this.type = type;
	}

	public void setDiscountReasonBean(DiscountReasonBean discountReasonBean) {
		this.discountReasonBean = discountReasonBean;
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

		List<DiscountReason> discountReasons = discountReasonBean.discountReason(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> discountReasonLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (DiscountReason obj : discountReasons) {
			DiscountReason item = (DiscountReason) obj;
			Map<String, Object> discountReasonMap = new HashMap<String, Object>();
			discountReasonMap.put("rowNumber", rowNumber);
			discountReasonMap.put("foodAmt", item.getFoodAmt());
			discountReasonMap.put("disAmt", item.getDisAmt());
			discountReasonMap.put("payAmt", item.getPayAmt());
			discountReasonMap.put("disWhy", item.getDisWhy());
			discountReasonMap.put("shopC", item.getShopC());
			discountReasonMap.put("shopN", item.getShopN());

			discountReasonLst.add(discountReasonMap);

			rowNumber++;
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, discountReasonLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(discountReasonLst);
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
