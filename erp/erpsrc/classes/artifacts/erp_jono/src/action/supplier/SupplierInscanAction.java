package action.supplier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.InputHeaderBean;
import logic.module.supplier.SupplierInScanBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.InputHeader;
import service.supplier.SupplierService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

/**
 * 采购入库单查询
 * 
 * @author pengwei
 */
@SuppressWarnings("rawtypes")
public class SupplierInscanAction extends BaseAction {

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
	private String parentFormId;

	private BranchBean branchBean;
	private List<Map> branchLst;

	private List<Map> shopLst;

	private SupplierService supplierService;

	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private String branchId;

	/**
	 * 入库单号
	 */
	private String formIdText1;

	/**
	 * 采购单号
	 */
	private String formIdText2;

	private InputHeader inputHeader;
	private InputHeaderBean inputHeaderBean;

	private SupplierInScanBean supplierInScanBean;

	private Date inputTime;

	private String subFormIds;

	private String formType;

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

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {

		inputHeader = inputHeaderBean.queryById(formId);

		inputTime = inputHeader.getInputTime();
		return SUCCESS;
	}

	/**
	 * 支持单个确认和多个确认
	 * 
	 */
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

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		String branchType = getLoginBranchType();
		if (branchType.equals(BranchTypeEnum.SUPPLIER.toString())) {
			supplierId = getLoginBranchId();
		}
		String initStatus = "已审核";
		Integer total = supplierInScanBean.count(startDate, endDate, supplierId, branchId, formIdText1, formIdText2,
				initStatus, status);

		setTotal(total);
		JSONArray arr = new JSONArray();

		if (total > 0) {
			List<Map> supplierList = supplierInScanBean.inScan(startDate, endDate, supplierId, branchId, formIdText1,
					formIdText2, initStatus, status, getStart(), getEnd());

			int rownumber = getStart();
			for (Map itemMap : supplierList) {
				JsonConfig config = new JsonConfig();
				config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
				JSONObject json = JSONObject.fromObject(itemMap, config);
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

	public String getReceiveTime() {
		return DateTimeUtil.getDateStr(receiveTime);
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
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

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public List<Map> getBranchLst() {
		return branchLst;
	}

	public void setSupplierInScanBean(SupplierInScanBean supplierInScanBean) {
		this.supplierInScanBean = supplierInScanBean;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setFormIdText1(String formIdText1) {
		this.formIdText1 = formIdText1;
	}

	public void setFormIdText2(String formIdText2) {
		this.formIdText2 = formIdText2;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setSubFormIds(String subFormIds) {
		this.subFormIds = subFormIds;
	}

	public String getFormType() {
		return formType;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public String getParentFormId() {
		return parentFormId;
	}

	public void setParentFormId(String parentFormId) {
		this.parentFormId = parentFormId;
	}

}
