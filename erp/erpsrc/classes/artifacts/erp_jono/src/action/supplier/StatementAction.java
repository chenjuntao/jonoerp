package action.supplier;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.InputHeaderBean;
import logic.module.supplier.SupplierStatement;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.InputHeader;
import pojo.form.StatementHead;
import service.supplier.SupplierService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class StatementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	// 入库开始时间
	private Date startDate;

	// 入库结束时间
	private Date endDate;

	// 供应商Id
	private String supplierId;

	private String formId;

	private BranchBean branchBean;

	private List<Map> shopLst;
	private List<Map> branchLst;

	private Date formTime;
	private Date inputTime;

	private InputHeader inputHeader;

	private SupplierStatement supplierStatement;

	private SupplierService supplierService;

	private InputHeaderBean inputHeaderBean;

	private String ids;

	private StatementHead statementHead;

	private Double allPayAmt;

	private String formIdText;

	private String branchId;

	private String formType;

	private String jsonData;

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

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		allPayAmt = supplierStatement.inReturnSum(startDate, endDate, supplierId, formIdText, branchId);
		return SUCCESS;
	}

	public String checkView() throws NoPrivilegeException, SQLException, NoConnection {
		inputHeader = inputHeaderBean.queryById(formId);

		inputTime = inputHeader.getInputTime();
		return SUCCESS;
	}

	public void createBill() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = new JSONObject();

		statementHead.setProvider(branchBean.GetBranchById(supplierId).getBranchName());

		statementHead.setBranchName(branchBean.GetBranchById(branchId).getBranchName());
		supplierService.createBill(getLoginUserId(), getLoginUsername(), jsonData, statementHead, formType);
		result.put("msg", "ok");

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询满足条件的入库单以及退货单
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		String branchType = getLoginBranchType();
		if (branchType.equals(BranchType.SUPPLIER)) {
			supplierId = getLoginBranchId();
		}
		int total = supplierStatement.inReturnCount(startDate, endDate, supplierId, formIdText, branchId);
		setTotal(total);

		JSONArray arr = new JSONArray();

		if (total > 0) {
			List<InputHeader> inLists = supplierStatement.queryInReturn(startDate, endDate, supplierId, formIdText,
					branchId, getStart(), getEnd());

			int rownumber = getStart();
			for (InputHeader item : inLists) {
				JSONObject json = JSONObject.fromObject(item);
				json.put("inputTime", DateTimeUtil.getDateStr(item.getInputTime()));
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd()) {
				allPayAmt = supplierStatement.inReturnSum(startDate, endDate, supplierId, formIdText, branchId);
				JSONObject sumJObject = new JSONObject();
				sumJObject.put("rownumber", "合计");
				sumJObject.put("allPayAmt", allPayAmt);
				arr.add(sumJObject);

			}
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAllQuery() throws NoPrivilegeException, SQLException, NoConnection {

		JSONArray arr = new JSONArray();

		List<InputHeader> inLists = supplierStatement.queryInReturn(startDate, endDate, supplierId, formIdText,
				branchId, 0, Integer.MAX_VALUE);

		int rownumber = 1;
		for (InputHeader item : inLists) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("inputTime", DateTimeUtil.getDateStr(item.getInputTime()));
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

	public String getFormTime() {
		return DateTimeUtil.getDateStr(formTime);
	}

	public String getInputTime() {
		return DateTimeUtil.getDateStr(inputTime);
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
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

	public String getSupplierId() {
		return supplierId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setInputHeader(InputHeader inputHeader) {
		this.inputHeader = inputHeader;
	}

	public InputHeader getInputHeader() {
		return inputHeader;
	}

	public void setSupplierService(SupplierService supplierService) {
		this.supplierService = supplierService;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
	}

	public StatementHead getStatementHead() {
		return statementHead;
	}

	public void setStatementHead(StatementHead statementHead) {
		this.statementHead = statementHead;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public Double getAllPayAmt() {
		return allPayAmt;
	}

	public void setAllPayAmt(Double allPayAmt) {
		this.allPayAmt = allPayAmt;
	}

	public void setFormIdText(String formIdText) {
		this.formIdText = formIdText;
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

	public void setSupplierStatement(SupplierStatement supplierStatement) {
		this.supplierStatement = supplierStatement;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

}
