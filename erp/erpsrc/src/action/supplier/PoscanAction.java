package action.supplier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.PurchasingHeaderBean;
import logic.module.supplier.SupplierScanBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.PurchasingHeader;
import service.supplier.SupplierService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class PoscanAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;

	private Date endDate;

	private String supplierId;

	private String status;

	private String formId;

	private BranchBean branchBean;

	private List<Map> shopLst;

	private PurchasingHeader purchasingHeader;

	private SupplierScanBean supplierScanBean;

	private PurchasingHeaderBean purchasingHeaderBean;

	private SupplierService supplierService;
	private Date receiveTime;
	private Date formTime;
	private Date auditTime;
	private String deliveryType;

	private String formIdText;
	private String orderType;
	private String branchId;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		String branchType = getLoginBranchType();
		if (branchType.equals(BranchType.SUPPLIER)) {
			branchId = getLoginBranchId();
			shopLst = branchBean.queryById(branchId);
		} else {
			shopLst = branchBean.listShopByType(BranchTypeEnum.SUPPLIER);
		}
		endDate = branchBean.getMaxBusinessDate();
		startDate = DateTimeUtil.addMonths(endDate, -1);

		return SUCCESS;
	}

	public void deliveryStatus() throws NoPrivilegeException, SQLException, NoConnection {
		String status = supplierService.deliveryStatus(formId);

		JSONObject result = new JSONObject();
		result.put("msg", status);

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateStatus() throws NoPrivilegeException, SQLException, NoConnection {
		supplierService.updateStatus(formId, getLoginUserId());

		JSONObject result = new JSONObject();
		result.put("msg", "ok");

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
		String branchType = getLoginBranchType();
		if (branchType.equals(BranchTypeEnum.SUPPLIER.toString())) {
			supplierId = getLoginBranchId();
		}
		Integer total = supplierScanBean.count(startDate, endDate, supplierId, formIdText, orderType);

		setTotal(total);
		JSONArray arr = new JSONArray();

		if (total > 0) {
			List<Map> supplierList = supplierScanBean.scan(startDate, endDate, supplierId, formIdText, orderType,
					getStart(), getEnd());
			int rowNumber = 0;
			for (Map itemMap : supplierList) {
				JsonConfig config = new JsonConfig();
				config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
				itemMap.put("rowNumber", rowNumber);
				rowNumber++;
				JSONObject json = JSONObject.fromObject(itemMap, config);
				arr.add(json);
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

	public void setSupplierScanBean(SupplierScanBean supplierScanBean) {
		this.supplierScanBean = supplierScanBean;
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

	public PurchasingHeader getPurchasingHeader() {
		return purchasingHeader;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setFormIdText(String formIdText) {
		this.formIdText = formIdText;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public String getBranchId() {
		return branchId;
	}
}
