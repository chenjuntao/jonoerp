package action.centralfactory.productionDemand.workOrder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.ArrangmentDetailBean;
import logic.form.FormLockBean;
import logic.form.FormStatusBean;
import logic.form.MakingProcessBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ArrangmentDetail;
import pojo.form.MakingProcess;
import pojo.form.WorkOrderHeader;
import service.centralfactory.productionDemand.WorkOrderService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class CreateWorkOrderAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private WorkOrderService workOrderService;

	private ArrangmentDetailBean arrangmentDetailBean;
	private MakingProcessBean makingProcessBean;

	private String jsonData;
	private String arrangementFormId;

	// head
	private String formId;

	private WorkOrderHeader workOrderHeader;
	private ArrangmentDetail arrangmentDetail;

	private FormStatusBean formStatusBean;

	private FormLockBean formLockBean;

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setMakingProcessBean(MakingProcessBean makingProcessBean) {
		this.makingProcessBean = makingProcessBean;
	}

	public ArrangmentDetail getArrangmentDetail() {
		return arrangmentDetail;
	}

	public void setWorkOrderService(WorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	public WorkOrderHeader getWorkOrderHeader() {
		return workOrderHeader;
	}

	public void setWorkOrderHeader(WorkOrderHeader workOrderHeader) {
		this.workOrderHeader = workOrderHeader;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setArrangementFormId(String arrangementFormId) {
		this.arrangementFormId = arrangementFormId;
	}

	public String getArrangementFormId() {
		return arrangementFormId;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void setArrangmentDetailBean(ArrangmentDetailBean arrangmentDetailBean) {
		this.arrangmentDetailBean = arrangmentDetailBean;
	}

	public void doOrderWorkQuery() throws NoPrivilegeException, SQLException, NoConnection {

		JSONArray arr = new JSONArray();
		List<ArrangmentDetail> detailLst = arrangmentDetailBean.query(arrangementFormId);

		int rownumber = getStart();
		for (ArrangmentDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);

			String formId = detail.getWorkOrderId();
			if (formStatusBean.getCurrentStatus(formId) != null) {
				continue;
			}
			json.put("formId", formId);
			json.put("itemCount", detail.getProduceCount());
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doWorkDetailQuery() throws NoPrivilegeException, SQLException, NoConnection {

		JSONArray arr = new JSONArray();
		List<MakingProcess> detailLst = makingProcessBean.queryDetail(formId);

		for (MakingProcess detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("productionName", json.getString("stepOperation"));

			json.put("productionStep", json.getInt("step"));

			json.put("productionCount", 0);

			// 生产人员
			json.put("productionMan", "");

			// 备注
			json.put("productionNote", "");
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String doCreate() throws NoPrivilegeException, SQLException, NoConnection {
		// 加锁
		formLockBean.addLock(formId);
		arrangmentDetail = arrangmentDetailBean.queryByWorkOrderId(formId);
		return SUCCESS;
	}

	public void doBatchCreate() throws NoPrivilegeException, SQLException, NoConnection {
		workOrderService.doBatchCommit(getLoginBranchId(), getLoginUserId(), getLoginUsername(), jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void doWorkCommit() throws NoPrivilegeException, SQLException, NoConnection {
		workOrderHeader.setFormBranchId(getLoginBranchId());
		workOrderService.doCommit(arrangementFormId, getLoginUserId(), getLoginUsername(), workOrderHeader, jsonData);

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

}
