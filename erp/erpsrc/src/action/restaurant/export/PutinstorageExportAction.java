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
import logic.form.InputHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.InputHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.xls.ExportUtil;

public class PutinstorageExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String type;

	private FormStatusBean formStatusBean;
	private InputHeaderBean inputHeaderBean;

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
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

		List<InputHeader> headerLst = inputHeaderBean.query(startDate, endDate, branchId, 0, Integer.MAX_VALUE, null,
				BranchType.RESTAURANT);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> inputHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (InputHeader obj : headerLst) {
			InputHeader inputHeader = (InputHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("rownumber", rownumber++);

			// 入库单号
			itemMap.put("formId", inputHeader.getFormId());

			// 供应商
			itemMap.put("provider", inputHeader.getProvider());

			// 采购单号
			itemMap.put("requestFormId", inputHeader.getFormRefId());

			// 入库部门
			itemMap.put("inputDepartment", inputHeader.getInputDepartment());

			// 入库人员
			itemMap.put("inputer", inputHeader.getInputer());

			// 入库日期
			String inputTime = getDateStr(inputHeader.getInputTime());
			itemMap.put("inputTime", inputTime);

			// 备注
			itemMap.put("formNote", nullToStr(inputHeader.getFormNote()));

			// 主要入库品
			itemMap.put("maxPayItem", nullToStr(inputHeader.getMaxPayItem()));

			// 入库总额
			itemMap.put("allPayAmt", inputHeader.getAllPayAmt());

			// 单据状态
			String formStatus = formStatusBean.getCurrentStatus(inputHeader.getFormId());
			itemMap.put("formStatus", formStatus);

			inputHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, inputHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(inputHeaderLst);
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
