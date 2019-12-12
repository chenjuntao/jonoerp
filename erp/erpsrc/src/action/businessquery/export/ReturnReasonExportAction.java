package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.ReturnReasonBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.ReturnReason;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class ReturnReasonExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;
	private String shopC;
	private ReturnReasonBean returnReasonBean;

	private String type;

	public void setReturnReasonBean(ReturnReasonBean returnReasonBean) {
		this.returnReasonBean = returnReasonBean;
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

		List<ReturnReason> returnReasons = returnReasonBean.returnReason(startDate, endDate, shopC);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> returnReasonLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (ReturnReason item : returnReasons) {
			Map<String, Object> returnReasonMap = new HashMap<String, Object>();
			returnReasonMap.put("rowNumber", rowNumber);
			returnReasonMap.put("returnAmount", item.getReturnAmount());
			returnReasonMap.put("returnAmt", item.getReturnAmt());
			returnReasonMap.put("returnWhy", item.getReturnWhy());
			returnReasonMap.put("shopC", item.getShopC());
			returnReasonMap.put("shopN", item.getShopN());

			returnReasonLst.add(returnReasonMap);

			rowNumber++;
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, returnReasonLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(returnReasonLst);
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
