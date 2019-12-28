package action.outorder;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.InputHeaderBean;
import logic.module.outer.OrderStatementBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.form.InputHeader;
import pojo.form.StatementHead;
import service.outorder.OutOrderService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class OuterStatementAction extends BaseAction {

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

	private OutOrderService outOrderService;

	private InputHeaderBean inputHeaderBean;

	private String ids;

	private Double allPayAmt;

	private String formIdText;

	private String branchId;

	private OrderStatementBean orderStatementBean;

	private StatementHead statementHead;

	private String jsonData;
	private String formType;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.LOGISTICSCENTER);
		branchLst = branchBean.queryById(getLoginBranchId());

		endDate = branchBean.GetBranchById(BranchCode.DEFAULT_LOGISTICSCENTER).getBusinessDate();
		startDate = DateTimeUtil.addMonths(endDate, -1);
		
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		allPayAmt = orderStatementBean.outReturnSum(startDate, endDate, supplierId, formIdText, branchId,
				getLoginBranchType());
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
		outOrderService.createBill(getLoginUserId(), getLoginUsername(), jsonData, statementHead, formType);
		result.put("msg", "ok");

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doInQuery() throws NoPrivilegeException, SQLException, NoConnection {

		String branchType = getLoginBranchType();
		int total = orderStatementBean.outReturnCount(startDate, endDate, supplierId, formIdText, branchId, branchType);
		setTotal(total);

		JSONArray arr = new JSONArray();

		if (total > 0) {
			List<Map> dataMaps = orderStatementBean.queryOutReturn(startDate, endDate, supplierId, formIdText,
					branchId, branchType, getStart(), getEnd());

			int rownumber = getStart();
			for (Map item : dataMaps) {
				JsonConfig config = new JsonConfig();
				config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());
				JSONObject json = JSONObject.fromObject(item, config);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// 合计
			if (total < getEnd()) {
				allPayAmt = orderStatementBean.outReturnSum(startDate, endDate, supplierId, formIdText, branchId,
						branchType);
				JSONObject sumJObject = new JSONObject();
				sumJObject.put("rownumber", "合计");
				sumJObject.put("all_pay_amt", allPayAmt);
				arr.add(sumJObject);
			}
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

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getIds() {
		return ids;
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

	public void setOrderStatementBean(OrderStatementBean orderStatementBean) {
		this.orderStatementBean = orderStatementBean;
	}

	public void setStatementHead(StatementHead statementHead) {
		this.statementHead = statementHead;
	}

	public StatementHead getStatementHead() {
		return statementHead;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setFormType(String formType) {
		this.formType = formType;
	}

	public void setOutOrderService(OutOrderService outOrderService) {
		this.outOrderService = outOrderService;
	}

	public String getFormType() {
		return formType;
	}

}
