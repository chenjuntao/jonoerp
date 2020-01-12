package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodSellCountBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.FoodSellCount;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class FoodSellCountExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;

	private FoodSellCountBean foodSellCountBean;
	private String type;

	public void setFoodSellCountBean(FoodSellCountBean foodSellCountBean) {
		this.foodSellCountBean = foodSellCountBean;
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

	public void littleCategoryExport() throws NoPrivilegeException, SQLException, NoConnection {

		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		List<FoodSellCount> foodSellCounts = foodSellCountBean.littleCategory(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> foodSellCountLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (FoodSellCount item : foodSellCounts) {
			Map<String, Object> foodSellCountMap = new HashMap<String, Object>();
			foodSellCountMap.put("rowNumber", rowNumber++);
			foodSellCountMap.put("categoryId", item.getCategoryId());
			foodSellCountMap.put("categoryN", item.getCategoryN());
			foodSellCountMap.put("qty", item.getQty());
			foodSellCountMap.put("amt", item.getAmt());
			foodSellCountMap.put("afterAmt", item.getAfterAmt());
			foodSellCountMap.put("shopC", item.getShopC());
			foodSellCountMap.put("shopN", item.getShopN());

			foodSellCountLst.add(foodSellCountMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, foodSellCountLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(foodSellCountLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void bigCategoryExport() throws NoPrivilegeException, SQLException, NoConnection {

		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		List<FoodSellCount> foodSellCounts = foodSellCountBean.bigCategory(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> foodSellCountLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (FoodSellCount item : foodSellCounts) {
			Map<String, Object> foodSellCountMap = new HashMap<String, Object>();
			foodSellCountMap.put("rowNumber", rowNumber++);
			foodSellCountMap.put("categoryId", item.getCategoryId());
			foodSellCountMap.put("categoryN", item.getCategoryN());
			foodSellCountMap.put("qty", item.getQty());
			foodSellCountMap.put("amt", item.getAmt());
			foodSellCountMap.put("afterAmt", item.getAfterAmt());
			foodSellCountMap.put("shopC", item.getShopC());
			foodSellCountMap.put("shopN", item.getShopN());

			foodSellCountLst.add(foodSellCountMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, foodSellCountLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(foodSellCountLst);
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
