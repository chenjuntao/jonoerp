package action.restaurant.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class CurrentStorageExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String type;

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date,
					DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setType(String type) {
		this.type = type;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;
	private String exportContent;

	public void setExportContent(String exportContent) {
		this.exportContent = exportContent;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	private String nullToStr(String str) {
		if (TextUtil.isEmpty(str)) {
			return "";
		}

		return str;
	}

	public void export() throws NoPrivilegeException, SQLException,
			NoConnection {

		JSONObject structure = JSONObject.fromObject(jsonData);

		JSONArray colsJA = structure.getJSONArray("columns");

		JSONObject exportJO = JSONObject.fromObject(exportContent);
		JSONArray exportData = exportJO.getJSONArray("data");

		List<Map<String, Object>> currentStorageLst = new ArrayList<Map<String, Object>>();

		for (int i = 0; i < exportData.size(); i++) {
			JSONObject jObject = (JSONObject) exportData.get(i);
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("rownumber", (i + 1));

			// 编码
			itemMap.put("itemId", jObject.get("itemId"));

			// 名称
			itemMap.put("itemName", jObject.get("itemName"));

			// 简称
			itemMap.put("itemNameEng", jObject.get("itemNameEng"));

			// 助记码
			itemMap.put("queryCode", jObject.get("queryCode"));

			// 单位
			itemMap.put("itemDimension", jObject.get("itemDimension"));

			// 供应商
			itemMap.put("supplier", nullToStr((String) jObject.get("supplier")));

			// 配送价
			itemMap.put("itemUnitPrice", jObject.get("itemUnitPrice"));

			// 库存量
			if (jObject.get("inventory") == null) {
				itemMap.put("inventory", 0);
			} else {
				itemMap.put("inventory", (jObject.get("inventory")));
			}

			currentStorageLst.add(itemMap);
		}

		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, currentStorageLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(currentStorageLst);
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
