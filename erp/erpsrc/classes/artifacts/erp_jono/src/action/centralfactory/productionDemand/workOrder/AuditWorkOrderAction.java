package action.centralfactory.productionDemand.workOrder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormLockBean;
import logic.form.FormStatusBean;
import logic.form.WorkOrderHeaderBean;
import logic.form.WorkOrderItemBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.WorkOrderHeader;
import pojo.form.WorkOrderItem;
import service.centralfactory.productionDemand.WorkOrderService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class AuditWorkOrderAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private WorkOrderService workOrderService;
	private FormStatusBean formStatusBean;
	private FormLockBean formLockBean;

	private Date startDate;
	private Date endDate;

	private String formId;

	private Date formTime;
	private Date auditTime;

	private String jsonData;
	private List<Map> shopLst;

	private WorkOrderHeaderBean workOrderHeaderBean;
	private WorkOrderItemBean workOrderItemBean;

	private String queryStr;

	private String status;

	private WorkOrderHeader workOrderHeader;

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			workOrderHeader = workOrderHeaderBean.queryById(formId);

			formTime = workOrderHeader.getFormTime();
			auditTime = workOrderHeader.getAuditTime();

			workOrderHeader.setAuditor(getLoginUsername());
			workOrderHeader.setAuditorId(getLoginUserId());
		}

		formLockBean.addLock(formId);// 加锁
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			status = formStatusBean.getCurrentStatus(formId);

			workOrderHeader = workOrderHeaderBean.queryById(formId);

			formTime = workOrderHeader.getFormTime();
			auditTime = workOrderHeader.getAuditTime();

			workOrderHeader.setAuditor(getLoginUsername());
			workOrderHeader.setAuditorId(getLoginUserId());
		}

		return SUCCESS;
	}

	public void doAuditedQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = workOrderHeaderBean.count(startDate, endDate, queryStr);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<WorkOrderHeader> headerLst = workOrderHeaderBean.query(startDate, endDate, queryStr);

			int rownumber = getStart();
			for (WorkOrderHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				// 审核日期
				String auditTime = getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);
				json.put("formStatus", formStatus);
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

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = workOrderHeaderBean.countByFormTime(startDate, endDate, queryStr);

		setTotal(total);

		String r = getLoginBranchId();
		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<WorkOrderHeader> headerLst = workOrderHeaderBean.queryByTime(startDate, endDate, queryStr,
					getLoginBranchId());

			int rownumber = getStart();
			for (WorkOrderHeader header : headerLst) {

				JSONObject json = JSONObject.fromObject(header);
				json.put("completeTime", DateTimeUtil.getDateStr(header.getCompleteTime()));
				json.put("formTime", DateTimeUtil.getDateStr(header.getFormTime()));

				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formStatus", formStatus);
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

	public void queryItemDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<WorkOrderItem> detailLst = workOrderItemBean.query(formId);
		int rownumber = 1;
		for (WorkOrderItem detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
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

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		workOrderService.auditBill(getLoginUserId(), getLoginUsername(), jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getFormTime() {
		return getDateStr(formTime);
	}

	public void setFormTime(Date formTime) {
		this.formTime = formTime;
	}

	public String getAuditTime() {
		return getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public Date getStartDate() {
		return startDate;
	}

	public WorkOrderHeader getWorkOrderHeader() {
		return workOrderHeader;
	}

	public void setWorkOrderHeader(WorkOrderHeader workOrderHeader) {
		this.workOrderHeader = workOrderHeader;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setWorkOrderService(WorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}

	public void setQueryStr(String queryStr) {
		this.queryStr = queryStr;
	}

	public String getStatus() {
		return status;
	}

	public void setWorkOrderItemBean(WorkOrderItemBean workOrderItemBean) {
		this.workOrderItemBean = workOrderItemBean;
	}

}
