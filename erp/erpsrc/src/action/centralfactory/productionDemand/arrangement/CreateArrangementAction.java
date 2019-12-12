package action.centralfactory.productionDemand.arrangement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.CollectDetailBean;
import logic.form.CollectHeaderBean;
import logic.form.FormStatusBean;
import logic.store.BranchBean;
import logic.store.ProductionCycleBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ArrangmentHeader;
import pojo.form.CollectDetail;
import pojo.form.CollectHeader;
import service.centralfactory.productionDemand.ArrangementService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class CreateArrangementAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ArrangementService arrangementService;

	private FormStatusBean formStatusBean;
	private CollectDetailBean collectDetailBean;
	private CollectHeaderBean collectHeaderBean;
	private BranchBean branchBean;

	private ProductionCycleBean productionCycleBean;

	private Date startDate;
	private Date endDate;

	private String jsonData;

	private String formId;

	private ArrangmentHeader arrangmentHeader;

	public void setArrangmentHeader(ArrangmentHeader arrangmentHeader) {
		this.arrangmentHeader = arrangmentHeader;
	}

	public void setCollectHeaderBean(CollectHeaderBean collectHeaderBean) {
		this.collectHeaderBean = collectHeaderBean;
	}

	public void setCollectDetailBean(CollectDetailBean collectDetailBean) {
		this.collectDetailBean = collectDetailBean;
	}

	public void setProductionCycleBean(ProductionCycleBean productionCycleBean) {
		this.productionCycleBean = productionCycleBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doAuditedQuery() throws NoPrivilegeException, SQLException, NoConnection {

		JSONArray arr = new JSONArray();
		List<CollectHeader> headList = collectHeaderBean.queryAudited(startDate, endDate, null);

		int rownumber = getStart();
		for (CollectHeader header : headList) {
			JSONObject json = JSONObject.fromObject(header);
			json.put("rownumber", rownumber);

			// 制单日期
			String formTime = getDateStr(header.getFormTime());
			json.put("formTime", formTime);

			// 审核日期
			String auditTime = getDateStr(header.getAuditTime());
			json.put("auditTime", auditTime);

			String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
			json.put("formStatus", formStatus);

			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String returnWorkOrderId(int rownumber) throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = getLoginBranchId();
		Date settleTime = branchBean.GetBranchById(cfCode).getBusinessDate();

		Date now = new Date();

		// 自动生成，拼音缩写+部门编号+日期+流水号
		String workOrderId = "GD" + cfCode + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss") + rownumber;
		return workOrderId;
	}

	public void toArrangement() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();

		String cfCode = getLoginBranchId();
		if (!TextUtil.isEmpty(formId)) {
			List<CollectDetail> detailLst = collectDetailBean.query(formId);

			int rownumber = getStart();
			for (CollectDetail detail : detailLst) {
				JSONObject json = JSONObject.fromObject(detail);
				json.put("rownumber", rownumber);

				// 计划生产量
				json.put("produceCount", detail.getItemCount());

				// 工单单号
				json.put("workOrderId", returnWorkOrderId(rownumber));

				// 生产车间

				// 生产日期 默认当天 可选
				json.put("produceTime", DateTimeUtil.getDateTime(new Date(), "yyyy-MM-dd"));

				// 生产周期
				Integer cycle = productionCycleBean.queryCycle(cfCode, detail.getItemId());
				if (cycle == null) {
					cycle = 1;
				}
				json.put("productionCycle", cycle);

				// 完工日期
				json.put("completeTime",
						DateTimeUtil.getDateTime(DateTimeUtil.getDateTime(DateTimeUtil.addDays(cycle), "yyyy-MM-dd")));

				// 备注
				json.put("note", "");

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

	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		arrangmentHeader.setFormBranchId(getLoginBranchId());
		arrangementService.doCommit(getLoginUserId(), getLoginUsername(), arrangmentHeader, jsonData, formId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setArrangementService(ArrangementService arrangementService) {
		this.arrangementService = arrangementService;
	}
}
