package action.businessquery.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import logic.NoConnection;
import logic.businessquery.BillBean;
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

public class PeopleBillExportAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String startDate;
	private String endDate;

	private String createMan;
	private String settleMan;
	private String disCurMan;

	@Resource
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	private String type;

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setType(String type) {
		this.type = type;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setCreateMan(String createMan) {
		this.createMan = createMan;
	}

	public void setSettleMan(String settleMan) {
		this.settleMan = settleMan;
	}

	public void setDisCurMan(String disCurMan) {
		this.disCurMan = disCurMan;
	}

	@SuppressWarnings("rawtypes")
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {

		List peopleBills = null;

		createMan = TextUtil.isEmpty(createMan) ? "%" : createMan;
		settleMan = TextUtil.isEmpty(settleMan) ? "%" : settleMan;
		disCurMan = TextUtil.isEmpty(disCurMan) ? "%" : disCurMan;

		peopleBills = billDao.listBillByMan(startDate, endDate, createMan, settleMan, disCurMan, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> shopLst = new ArrayList<Map<String, Object>>();

		for (Object obj : peopleBills) {
			Bill billBean = (Bill) obj;
			Map<String, Object> billMap = new HashMap<String, Object>();
			billMap.put("billC", billBean.getBillC());
			billMap.put("period", billBean.getPeriod());
			billMap.put("shift", billBean.getShift());
			billMap.put("guestNum", billBean.getGuestNum());

			billMap.put("billTime", DateTimeUtil.formatDateString(billBean.getBillTime()));
			billMap.put("settleTime", DateTimeUtil.formatDateString(billBean.getSettleTime()));

			billMap.put("createMan", billBean.getCreateMan());
			billMap.put("settleMan", billBean.getSettleMan());
			billMap.put("foodAmt", billBean.getFoodAmt());
			billMap.put("roundAmt", billBean.getRoundAmt());
			billMap.put("presentAmt", billBean.getPresentAmt());
			billMap.put("payAmt", billBean.getPayAmt());
			billMap.put("disAmt", billBean.getDisAmt());

			shopLst.add(billMap);
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
