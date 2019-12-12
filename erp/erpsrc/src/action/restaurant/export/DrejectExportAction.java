package action.restaurant.export;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.ReturnGoodsHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.ReturnGoodsHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class DrejectExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String queryType;

	private String type;

	private FormStatusBean formStatusBean;

	private ReturnGoodsHeaderBean returnGoodsHeaderBean;

	private String branchType;

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setReturnGoodsHeaderBean(ReturnGoodsHeaderBean returnGoodsHeaderBean) {
		this.returnGoodsHeaderBean = returnGoodsHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
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

	private String nullToStr(String str) {
		if (TextUtil.isEmpty(str)) {
			return "";
		}

		return str;
	}

	public void export() throws NoPrivilegeException, SQLException, NoConnection {
		List<ReturnGoodsHeader> headerLst = returnGoodsHeaderBean.query(startDate, endDate, branchId, queryType,
				branchType, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> reHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (ReturnGoodsHeader obj : headerLst) {
			ReturnGoodsHeader reHeader = (ReturnGoodsHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("rownumber", rownumber++);

			// 退货单号
			itemMap.put("formId", reHeader.getFormId());

			// // 退货部门 暂为空
			// itemMap.put("returnBranch", reHeader.getreturnBranch());

			// 退货人员
			itemMap.put("returner", reHeader.getReturner());

			// 退货日期
			String returnTime = getDateStr(reHeader.getReturnTime());
			itemMap.put("returnTime", returnTime);

			// 退货备注
			itemMap.put("formNote", nullToStr(reHeader.getFormNote()));

			// 配送部门
			itemMap.put("provider", nullToStr(reHeader.getProvider()));

			// 配送日期
			String receiveTime = getDateStr(reHeader.getReceiveTime());
			itemMap.put("receiveTime", receiveTime);

			// 订货部门
			itemMap.put("requester", nullToStr(reHeader.getRequester()));

			// 入库人员
			itemMap.put("inputer", nullToStr(reHeader.getInputer()));

			// 入库日期
			String inputTime = getDateStr(reHeader.getInputTime());
			itemMap.put("inputTime", inputTime);

			// 配送单备注
			itemMap.put("snote", nullToStr(reHeader.getSnote()));

			// 主要配送品
			itemMap.put("maxPayItem", nullToStr(reHeader.getMaxPayItem()));

			// 单据状态
			String formStatus = formStatusBean.getCurrentStatus(reHeader.getFormId());
			itemMap.put("formStatus", formStatus);

			reHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, reHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(reHeaderLst);
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
