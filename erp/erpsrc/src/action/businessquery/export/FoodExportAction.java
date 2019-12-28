package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.FoodBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class FoodExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private FoodBean foodBean;

	private String billC;
	private String type;

	public void setType(String type) {
		this.type = type;
	}

	public void setFoodBean(FoodBean foodBean) {
		this.foodBean = foodBean;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setBillC(String billC) {
		this.billC = billC;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {

		List<FoodBill> foods = foodBean.listFood(billC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopLst = new ArrayList<Map<String, Object>>();

		for (FoodBill food : foods) {
			Map<String, Object> shopMap = new HashMap<String, Object>();
			shopMap.put("foodC", food.getFoodC());
			shopMap.put("foodN", food.getFoodN());

			shopMap.put("litCls", food.getLitClsN());
			shopMap.put("unit", food.getUnit());

			shopMap.put("price", food.getPrice());
			shopMap.put("quantity", food.getQuantity());
			shopMap.put("amt", food.getAmt());
			shopMap.put("disAmt", food.getDisAmt());
			shopMap.put("suitFlag", food.getSuitFlag());
			shopMap.put("retSendFlag", food.getRetSendFlag());
			shopMap.put("retSendRemark", food.getRetSendRemark());
			shopMap.put("retSendMan", food.getPresentBackMan() == null ? "" : food.getPresentBackMan());

			shopLst.add(shopMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, shopLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(shopLst);
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
