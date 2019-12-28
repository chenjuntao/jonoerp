package action.supplier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.InputHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.module.restaurant.InputReturnBean;
import logic.module.supplier.SupplierReturnBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.FormStatus;
import pojo.form.InputHeader;
import pojo.form.ReturnGoodsHeader;
import pojo.form.ShippingHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class SupplierReturnAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;

	private Date endDate;

	private String supplierId;

	private String formId;

	private BranchBean branchBean;

	private List<Map> shopLst;

	private SupplierReturnBean supplierReturnBean;
	private InputHeaderBean inputHeaderBean;
	private ShippingHeaderBean shippingHeaderBean;

	private InputHeader inputHeader;
	private ShippingHeader shippingHeader;

	private ReturnGoodsHeader returnHeader;
	private FormStatusBean formStatusBean;
	private List<Map> branchLst;

	private String formIdText1;

	private String formIdText2;

	private String branchId;

	private String status;
	private String parentFormId;

	private Date returnTime;
	private Date auditTime;
	private Date inputTime;

	private InputReturnBean inputReturnBean;

	private String initStatus;

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
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = inputReturnBean.queryById(formId);
			returnTime = returnHeader.getReturnTime();

			inputHeader = inputHeaderBean.queryById(returnHeader.getFormRefId());

			auditTime = inputHeader.getAuditTime();
			inputTime = inputHeader.getInputTime();
		}
		return SUCCESS;
	}

	public String outConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			returnHeader = inputReturnBean.queryOutById(formId);
			returnTime = returnHeader.getReturnTime();
			shippingHeader = shippingHeaderBean.queryById(returnHeader.getFormRefId());
			auditTime = shippingHeader.getAuditTime();
			inputTime = shippingHeader.getReceiveTime();
		}
		return SUCCESS;
	}

	public void query() throws NoPrivilegeException, SQLException, NoConnection {
		String branchType = getLoginBranchType();
		if (branchType.equals(BranchTypeEnum.SUPPLIER.toString())) {
			supplierId = getLoginBranchId();
		}
		int total = supplierReturnBean.count(startDate, endDate, supplierId, branchId, formIdText1, formIdText2,
				initStatus, status);

		setTotal(total);

		JSONArray arr = new JSONArray();
		List<Map> supplierList = supplierReturnBean.query(startDate, endDate, supplierId, branchId, formIdText1,
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

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doConfirm() throws NoPrivilegeException, SQLException, NoConnection {
		FormStatus fstatus = new FormStatus(formId, status, getLoginUserId());
		fstatus.setIsCurrent(2);// 特殊状态，表示供应商/订货方已确认
		formStatusBean.saveSpecial(fstatus);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");

		try {
			this.outJS(result.toString());
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

	public void setSupplierReturnBean(SupplierReturnBean supplierReturnBean) {
		this.supplierReturnBean = supplierReturnBean;
	}

	public void setInputReturnBean(InputReturnBean inputReturnBean) {
		this.inputReturnBean = inputReturnBean;
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

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public List<Map> getBranchLst() {
		return branchLst;
	}

	public void setFormIdText1(String formIdText1) {
		this.formIdText1 = formIdText1;
	}

	public void setFormIdText2(String formIdText2) {
		this.formIdText2 = formIdText2;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ReturnGoodsHeader getReturnHeader() {
		return returnHeader;
	}

	public void setReturnHeader(ReturnGoodsHeader returnHeader) {
		this.returnHeader = returnHeader;
	}

	public String getReturnTime() {
		return DateTimeUtil.getDateStr(returnTime);
	}

	public void setReturnTime(Date returnTime) {
		this.returnTime = returnTime;
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public void setAuditTime(Date auditTime) {
		this.auditTime = auditTime;
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public void setInitStatus(String initStatus) {
		this.initStatus = initStatus;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public String getParentFormId() {
		return parentFormId;
	}

	public void setParentFormId(String parentFormId) {
		this.parentFormId = parentFormId;
	}

	public String getStatus() {
		return status;
	}

	public ShippingHeader getShippingHeader() {
		return shippingHeader;
	}

	public void setShippingHeader(ShippingHeader shippingHeader) {
		this.shippingHeader = shippingHeader;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

}
