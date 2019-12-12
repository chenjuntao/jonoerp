package action.supplier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.InputHeaderBean;
import logic.form.PurchasingHeaderBean;
import logic.form.StatementDetailBean;
import logic.form.StatementHeadBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.InputHeader;
import pojo.form.PurchasingHeader;
import pojo.form.StatementDetail;
import pojo.form.StatementHead;
import service.supplier.SupplierService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class AcscanAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 制单开始时间
	private Date startDate;

	// 制单结束时间
	private Date endDate;

	// 供应商Id
	private String supplierId;

	// 单据状态
	private String status;

	// 操作
	private String operate;

	private String formId;

	private BranchBean branchBean;

	private List<Map> shopLst;
	private List<Map> branchLst;

	private PurchasingHeader purchasingHeader;

	private PurchasingHeaderBean purchasingHeaderBean;

	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private Date inputTime;
	private String deliveryType;

	private String branchType;

	private String formIdText;

	private String initStatus;

	private InputHeader inputHeader;

	private SupplierService supplierService;

	private String branchId;

	private String branchName;

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	private String provider;

	private String subFormIds;

	private String formType;

	private StatementHeadBean statementHeadBean;

	private StatementHead statementHead;

	private StatementDetailBean statementDetailBean;

	private String length;

	private String formList;

	public void queryExport() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<Map> details = statementDetailBean.exportQuery(formList, branchName, provider);
		int rownumber = getStart();
		for (Map detail : details) {
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

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String branchType = getLoginBranchType();
		if (branchType.equals(BranchTypeEnum.SUPPLIER.toString())) {
			branchId = getLoginBranchId();
			shopLst = branchBean.queryById(branchId);
		} else {
			shopLst = branchBean.listShopByType(BranchTypeEnum.SUPPLIER);
		}
		branchLst = branchBean.listShopByType(BranchTypeEnum.R_C_L);
		endDate = branchBean.getMaxBusinessDate();
		startDate = DateTimeUtil.addMonths(endDate, -1);

		return SUCCESS;
	}

	public String confirm() throws NoPrivilegeException, SQLException, NoConnection {
		purchasingHeader = purchasingHeaderBean.queryById(formId);
		receiveTime = purchasingHeader.getReceiveTime();
		formTime = purchasingHeader.getFormTime();
		auditTime = purchasingHeader.getAuditTime();
		deliveryType = purchasingHeader.getDeliveryType();

		return SUCCESS;
	}

	public String operate() throws NoPrivilegeException, SQLException, NoConnection {
		statementHead = statementHeadBean.queryById(formId);
		startDate = statementHead.getStartDate();
		endDate = statementHead.getEndDate();

		return SUCCESS;
	}

	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		supplierService.doInConfirm(getLoginUserId(), formId, status, subFormIds, formType);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();
		result.put("msg", "ok");

		supplierService.doDelete(subFormIds);

		Double allPayAmt = statementDetailBean.sumByFormId(formId);
		StatementHead statementHead = new StatementHead();
		statementHead.setFormId(formId);
		statementHead.setAllPayAmt(allPayAmt);
		statementHeadBean.updateEntity(statementHead);

		if ("1".equals(length)) {
			statementHeadBean.deleteEntity(formId);
		}

		result.put("sum", allPayAmt);

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			purchasingHeader = purchasingHeaderBean.queryById(formId);
			receiveTime = purchasingHeader.getReceiveTime();
			formTime = purchasingHeader.getFormTime();
			auditTime = purchasingHeader.getAuditTime();
			deliveryType = purchasingHeader.getDeliveryType();

		}
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		formIdText = formIdText.toUpperCase();
		String branchType = getLoginBranchType();
		if (branchType.equals(BranchTypeEnum.SUPPLIER.toString())) {
			supplierId = getLoginBranchId();
		}
		initStatus = BillStatus.tansferUSToCN(initStatus);
		int total = statementHeadBean.count(startDate, endDate, supplierId, branchId, formIdText, initStatus, formType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<StatementHead> supplierList = statementHeadBean.query(startDate, endDate, supplierId, branchId,
					formIdText, initStatus, formType, getStart(), getEnd());

			int rownumber = getStart();
			for (StatementHead statementHead : supplierList) {
				JSONObject json = JSONObject.fromObject(statementHead);
				json.put("rownumber", rownumber);
				json.put("formTime", DateTimeUtil.getDateStr(statementHead.getFormTime()));
				String formTimeActual = getDateStr(statementHead.getFormTimeActual(),
						DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				json.put("startDate", DateTimeUtil.getDateStr(statementHead.getStartDate()));
				json.put("endDate", DateTimeUtil.getDateStr(statementHead.getEndDate()));
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

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
	}

	public void queryInBySForm() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<StatementDetail> supplierList = statementDetailBean.query(formId);

		int rownumber = getStart();
		for (StatementDetail detail : supplierList) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("formOperateTime", DateTimeUtil.getDateStr(detail.getFormOperateTime()));
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

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public String getDeliveryType() {
		return deliveryType;
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

	public List<Map> getShopLst() {
		return shopLst;
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

	public PurchasingHeader getPurchasingHeader() {
		return purchasingHeader;
	}

	public void setJsonData(String jsonData) {
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setFormIdText(String formIdText) {
		this.formIdText = formIdText;
	}

	public void setInitStatus(String initStatus) {
		this.initStatus = initStatus;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public String getInitStatus() {
		return initStatus;
	}

	public List<Map> getBranchLst() {
		return branchLst;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setSubFormIds(String subFormIds) {
		this.subFormIds = subFormIds;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getFormType() {
		return formType;
	}

	public void setStatementHeadBean(StatementHeadBean statementHeadBean) {
		this.statementHeadBean = statementHeadBean;
	}

	public void setStatementHead(StatementHead statementHead) {
		this.statementHead = statementHead;
	}

	public StatementHead getStatementHead() {
		return statementHead;
	}

	public void setStatementDetailBean(StatementDetailBean statementDetailBean) {
		this.statementDetailBean = statementDetailBean;
	}

	public void setLength(String length) {
		this.length = length;
	}

	public String getFormList() {
		return formList;
	}

	public void setFormList(String formList) {
		this.formList = formList;
	}
}
