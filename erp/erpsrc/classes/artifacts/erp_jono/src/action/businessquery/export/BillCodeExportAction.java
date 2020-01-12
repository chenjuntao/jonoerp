package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import logic.NoConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.businessquery.Bill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

import dao.businessquery.BillDao;

public class BillCodeExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	private String startDate;
	private String endDate;
	private String shopC;
	private String billCode;

	private String type;

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

	public void setBillCode(String billCode) {
		this.billCode = billCode;
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

	public void setBills(List<Bill> bills) {
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {

		String code = "%" + billCode + "%";

		List<Bill> liBeans;

		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		liBeans = billDao.listBillBylikeCode(startDate, endDate, code, shopC, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> billCodeLst = new ArrayList<Map<String, Object>>();

		for (Bill obj : liBeans) {
			Bill billBean = (Bill) obj;
			Map<String, Object> billCodeMap = new HashMap<String, Object>();
			billCodeMap.put("billC", billBean.getBillC().trim());
			billCodeMap.put("period", billBean.getPeriod());
			billCodeMap.put("shift", billBean.getShift());

			billCodeMap.put("guestNum", billBean.getGuestNum());
			billCodeMap.put("billTime", DateTimeUtil.formatDateString(billBean.getBillTime()));
			billCodeMap.put("settleTime", DateTimeUtil.formatDateString(billBean.getSettleTime()));

			billCodeMap.put("createMan", billBean.getCreateMan());
			billCodeMap.put("settleMan", billBean.getSettleMan());
			billCodeMap.put("foodAmt", billBean.getFoodAmt());

			billCodeMap.put("roundAmt", billBean.getRoundAmt());
			billCodeMap.put("presentAmt", billBean.getPresentAmt());
			billCodeMap.put("payAmt", billBean.getPayAmt());
			billCodeMap.put("disAmt", billBean.getDisAmt());

			billCodeLst.add(billCodeMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, billCodeLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(billCodeLst);
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
