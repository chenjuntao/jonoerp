package action.outorder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.outer.OutOrderScanBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class OrderScanAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 制单开始时间
	private Date startDate;

	// 制单结束时间
	private Date endDate;

	// 供应商Id
	private String supplierId;

	// 单据状态
	private String status;

	private String formId;

	private BranchBean branchBean;

	private List<Map> branch_out_Lst;
	private List<Map> branch_lc_Lst;

	private String branch_out;
	private String branch_lc;

	private String formIdText;

	private OutOrderScanBean outOrderScanBean;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		branch_lc_Lst = branchBean.listShopByType(BranchTypeEnum.LOGISTICSCENTER);
		branch_out_Lst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = outOrderScanBean.count(startDate, endDate, branch_lc, branch_out, status);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<Map> dataList = outOrderScanBean.query(startDate, endDate, branch_lc, branch_out, status, getStart(),
					getEnd());

			int rownumber = getStart();
			for (Map map : dataList) {
				JsonConfig config = new JsonConfig();
				config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
				JSONObject json = JSONObject.fromObject(map, config);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormId() {
		return formId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setFormIdText(String formIdText) {
		this.formIdText = formIdText;
	}

	public void setOutOrderScanBean(OutOrderScanBean outOrderScanBean) {
		this.outOrderScanBean = outOrderScanBean;
	}

	public void setBranch_lc(String branch_lc) {
		this.branch_lc = branch_lc;
	}

	public List<Map> getBranch_out_Lst() {
		return branch_out_Lst;
	}

	public List<Map> getBranch_lc_Lst() {
		return branch_lc_Lst;
	}

	public void setBranch_out(String branch_out) {
		this.branch_out = branch_out;
	}

}
