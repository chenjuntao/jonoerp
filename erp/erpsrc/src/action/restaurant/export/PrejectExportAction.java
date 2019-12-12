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
import logic.module.restaurant.InputReturnBean;
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

public class PrejectExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String type;
	private String queryType;

	private FormStatusBean formStatusBean;
	private InputReturnBean inputReturnBean;

	private String branchType;

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setInputReturnBean(InputReturnBean inputReturnBean) {
		this.inputReturnBean = inputReturnBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date,
					DateTimeUtil.DEFAULT_DATE_FORMAT);
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

	public void export() throws NoPrivilegeException, SQLException,
			NoConnection {

		List<ReturnGoodsHeader> headerLst = inputReturnBean.query(startDate,
				endDate, branchId, queryType, branchType, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> transferHeaderLst = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (ReturnGoodsHeader obj : headerLst) {
			ReturnGoodsHeader header = (ReturnGoodsHeader) obj;
			Map<String, Object> itemMap = new HashMap<String, Object>();
			itemMap.put("rownumber", rownumber++);

			// 退货单号
			itemMap.put("formId", header.getFormId());

			// 供应商
			itemMap.put("provider", header.getProvider());

			// 入库单编号
			itemMap.put("formRefId", header.getFormRefId());

			// 入库部门
			itemMap.put("inputDepartment", header.getInputDepartment());

			// 仓库
			itemMap.put("storage", header.getStorage());

			// 入库人员
			itemMap.put("inputer", header.getInputer());

			// 入库日期
			String inputTime = getDateStr(header.getInputTime());
			itemMap.put("inputTime", inputTime);

			// 退货人员
			itemMap.put("returner", header.getReturner());

			// 退货日期
			String returnTime = getDateStr(header.getReturnTime());
			itemMap.put("returnTime", returnTime);

			// 备注
			itemMap.put("formNote", nullToStr(header.getFormNote()));

			// 主要要货
			itemMap.put("maxPayItem", nullToStr(header.getMaxPayItem()));

			// 要货总额
			itemMap.put("allPayAmt", header.getAllPayAmt());

			// 单据状态
			String formStatus = formStatusBean.getCurrentStatus(header
					.getFormId());
			itemMap.put("formStatus", formStatus);

			transferHeaderLst.add(itemMap);
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, transferHeaderLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(transferHeaderLst);
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
