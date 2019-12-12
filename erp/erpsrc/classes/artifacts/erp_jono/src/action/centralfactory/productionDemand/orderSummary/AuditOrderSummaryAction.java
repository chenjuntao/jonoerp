package action.centralfactory.productionDemand.orderSummary;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CollectDetailBean;
import logic.form.CollectHeaderBean;
import logic.form.FormLockBean;
import logic.form.FormStatusBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.CollectDetail;
import pojo.form.CollectHeader;
import service.centralfactory.productionDemand.OrderSummaryService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

/**
 * 中央工厂由物流中心的采购订货生成汇总单，已不使用（菜单已删）
 */
@Deprecated
@SuppressWarnings("rawtypes")
public class AuditOrderSummaryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private CollectHeaderBean collectHeaderBean;
	private CollectDetailBean collectDetailBean;

	private OrderSummaryService cfOrderSummaryService;
	private FormStatusBean formStatusBean;
	private FormLockBean formLockBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String branch;

	private String formId;

	private CollectHeader collectHeader;

	private Date formTime;
	private Date auditTime;

	private String jsonData;
	private List<Map> shopLst;

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchId() {
		return branchId;
	}

	public String getBranch() {
		return branch;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.CENTRALFACTORY);
		return SUCCESS;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			collectHeader = collectHeaderBean.queryById(formId);

			branch = collectHeader.getBranch();
			formTime = collectHeader.getFormTime();
			auditTime = formTime;

			collectHeader.setAuditor(getLoginUsername());
			collectHeader.setAuditorId(getLoginUserId());
		}
		formLockBean.addLock(formId);// 加锁
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			collectHeader = collectHeaderBean.queryById(formId);

			branch = collectHeader.getBranch();
			formTime = collectHeader.getFormTime();
			auditTime = formTime;

			collectHeader.setAuditor(getLoginUsername());
			collectHeader.setAuditorId(getLoginUserId());
		}

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = collectHeaderBean.countAudit(startDate, endDate, branchId);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<CollectHeader> headerLst = collectHeaderBean.queryAudit(startDate, endDate, branchId);

			int rownumber = getStart();
			for (CollectHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);
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

	@Deprecated
	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<CollectDetail> detailLst = collectDetailBean.query(formId);

		CollectHeader collectHeader = collectHeaderBean.queryById(formId);
		String brandId = collectHeader.getBranchId();

		int rownumber = 1;
		for (CollectDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);
			json.put("providerId", brandId);
			json.put("provider", branchBean.GetBranchById(brandId).getBranchName());

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
		cfOrderSummaryService.auditBill(getLoginUserId(), getLoginUsername(), collectHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCollectDetailBean(CollectDetailBean collectDetailBean) {
		this.collectDetailBean = collectDetailBean;
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

	public CollectHeader getCollectHeader() {
		return collectHeader;
	}

	public void setCollectHeader(CollectHeader collectHeader) {
		this.collectHeader = collectHeader;
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

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public Date getStartDate() {
		return startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setCfOrderSummaryService(OrderSummaryService cfOrderSummaryService) {
		this.cfOrderSummaryService = cfOrderSummaryService;
	}

	public void setCollectHeaderBean(CollectHeaderBean collectHeaderBean) {
		this.collectHeaderBean = collectHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}
}
