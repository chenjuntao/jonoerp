package action.centralfactory.productionDemand.workOrder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormLockBean;
import logic.form.WorkOrderHeaderBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.SupplierBranchItemBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.InputDetail;
import pojo.form.InputHeader;
import pojo.form.WorkOrderHeader;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import service.centralfactory.productionDemand.WorkOrderService;
import action.common.BaseAction;

import com.tanry.business.module.cf.production.service.OutputRecordService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchCode;

public class BillManageAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private WorkOrderHeaderBean workOrderHeaderBean;
	private WorkOrderService workOrderService;
	private BranchBean branchBean;
	private FormLockBean formLockBean;
	private String formId;

	private WorkOrderHeader workOrderHeader;
	private Date formTime;
	private Date auditTime;

	private InputHeader inputHeader;
	private InputDetail inputDetail;

	private OutputRecordService outputRecordService;

	private BranchStorageBean branchStorageBean;
	private String jsonData;
	private SupplierBranchItemBean supplierBranchItemBean;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		workOrderHeader = workOrderHeaderBean.queryById(formId);

		formTime = workOrderHeader.getFormTime();
		auditTime = workOrderHeader.getAuditTime();

		inputHeader = new InputHeader();
		inputHeader.setFormRefId(workOrderHeader.getFormId());

		String cfCode = getLoginBranchId();
		Branch cfBranch = branchBean.GetBranchById(cfCode);
		inputHeader.setInputDepartmentId(cfCode);
		inputHeader.setInputDepartment(cfBranch.getBranchName());

		inputHeader.setInputerId(getLoginUserId());
		inputHeader.setInputer(getLoginUsername());

		BranchStorage branchStorage = branchStorageBean.queryMainStore(cfCode);
		inputHeader.setStorage(branchStorage.getStorageName());
		inputHeader.setStorageId(branchStorage.getStorageId());

		inputDetail = new InputDetail();
		inputDetail.setItemId(workOrderHeader.getItemId());
		inputDetail.setItemName(workOrderHeader.getItemName());
		inputDetail.setItemDimension(workOrderHeader.getItemDimension());

		// 订货量
		inputDetail.setOrderCount(workOrderHeader.getItemCount());

		formLockBean.addLock(formId);// 加锁
		return SUCCESS;
	}

	public void putinStorage() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		// head
		for (Object obj : arr) {

			String cfCode = getLoginBranchId();
			Date businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();// 获取当前的营业时间
			JSONObject json = (JSONObject) obj;
			String formId = json.getString("formId");
			workOrderHeader = workOrderHeaderBean.queryById(formId);

			formTime = workOrderHeader.getFormTime();
			auditTime = workOrderHeader.getAuditTime();

			inputHeader = new InputHeader();
			inputHeader.setFormType("PRODUCE");
			inputHeader.setInputTime(businessDate);
			inputHeader.setFormRefId(workOrderHeader.getFormId());

			Branch cfBranch = branchBean.GetBranchById(cfCode);
			inputHeader.setInputDepartmentId(cfCode);
			inputHeader.setInputDepartment(cfBranch.getBranchName());

			inputHeader.setInputerId(getLoginUserId());
			inputHeader.setInputer(getLoginUsername());

			BranchStorage branchStorage = branchStorageBean.queryMainStore(cfCode);
			inputHeader.setStorage(branchStorage.getStorageName());
			inputHeader.setStorageId(branchStorage.getStorageId());
			String itemId = workOrderHeader.getItemId();
			inputHeader.setMaxPayItem("[" + itemId + "]" + workOrderHeader.getItemName());
			Map<String, Object> supplier = supplierBranchItemBean.queryMainSupplier(BranchCode.DEFAULT_LOGISTICSCENTER,
					itemId);
			inputHeader.setProviderId((String) supplier.get("supplierId"));
			inputHeader.setProvider((String) supplier.get("supplierName"));

			// 计算总额
			double receiveCount = json.getDouble("itemCount") * json.getDouble("deliveryFactor");// 乘以包装因子，将前端传的包装数改成库存数
			double itemPrice = json.getDouble("itemPrice");
			double allPayAmt = receiveCount * itemPrice;
			double receivePrice = json.getDouble("itemAmt");
			double receiveAmt = receiveCount * receivePrice;
			String itemCategory = json.getString("categoryId");
			inputHeader.setAllPayAmt(allPayAmt);

			inputDetail = new InputDetail();
			inputDetail.setItemId(itemId);
			inputDetail.setItemName(workOrderHeader.getItemName());
			inputDetail.setItemDimension(workOrderHeader.getItemDimension());

			// 订货量
			inputDetail.setItemCategory(itemCategory);// 类别id
			inputDetail.setItemSpecification(json.getString("itemSpecification"));// 规格
			inputDetail.setOrderCount(workOrderHeader.getItemCount());
			inputDetail.setReceiveCount(receiveCount);
			inputDetail.setReceivedCount(workOrderHeader.getInputedCount());
			inputDetail.setItemUnitPrice(itemPrice);
			inputDetail.setPayAmt(allPayAmt);
			inputDetail.setReceivePrice(receivePrice);
			inputDetail.setReceiveAmt(receiveAmt);

			outputRecordService.putinStorage(getLoginUserId(), inputHeader, inputDetail);
		}
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void manualInStorage() throws NoPrivilegeException, SQLException, NoConnection {
		inputHeader = new InputHeader();
		inputHeader.setFormType("PRODUCE");
		inputHeader.setInputTime(formTime);

		String cfCode = getLoginBranchId();
		Branch cfBranch = branchBean.GetBranchById(cfCode);
		inputHeader.setInputDepartmentId(cfCode);
		inputHeader.setInputDepartment(cfBranch.getBranchName());

		inputHeader.setInputerId(getLoginUserId());
		inputHeader.setInputer(getLoginUsername());

		BranchStorage branchStorage = branchStorageBean.queryMainStore(cfCode);
		inputHeader.setStorage(branchStorage.getStorageName());
		inputHeader.setStorageId(branchStorage.getStorageId());
		outputRecordService.manualInStorage(getLoginUserId(), inputHeader, jsonData);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveStep() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fillBill() throws NoPrivilegeException, SQLException, NoConnection {
		workOrderService.output(getLoginUserId(), workOrderHeader);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public WorkOrderHeader getWorkOrderHeader() {
		return workOrderHeader;
	}

	public void setWorkOrderHeader(WorkOrderHeader workOrderHeader) {
		this.workOrderHeader = workOrderHeader;
	}

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getAuditTime() {
		return DateTimeUtil.getDateStr(auditTime);
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public void setInputDetail(InputDetail inputDetail) {
		this.inputDetail = inputDetail;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public InputDetail getInputDetail() {
		return inputDetail;
	}

	public void setOutputRecordService(OutputRecordService outputRecordService) {
		this.outputRecordService = outputRecordService;
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public void setWorkOrderService(WorkOrderService workOrderService) {
		this.workOrderService = workOrderService;
	}

	public void setFormLockBean(FormLockBean formLockBean) {
		this.formLockBean = formLockBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setSupplierBranchItemBean(SupplierBranchItemBean supplierBranchItemBean) {
		this.supplierBranchItemBean = supplierBranchItemBean;
	}

}
