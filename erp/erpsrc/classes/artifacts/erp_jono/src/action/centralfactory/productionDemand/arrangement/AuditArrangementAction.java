package action.centralfactory.productionDemand.arrangement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ArrangmentDetailBean;
import logic.form.ArrangmentHeaderBean;
import logic.form.CollectRefFormBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.store.BranchBean;
import logic.store.FactoryWorkShopBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ArrangmentDetail;
import pojo.form.ArrangmentHeader;
import pojo.store.FactoryWorkShop;
import service.centralfactory.productionDemand.ArrangementService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

@SuppressWarnings("rawtypes")
public class AuditArrangementAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ArrangmentHeaderBean arrangmentHeaderBean;
	private ArrangmentDetailBean arrangmentDetailBean;

	private ArrangementService arrangementService;
	private FormStatusBean formStatusBean;
	private FactoryWorkShopBean factoryWorkShopBean;
	private FactoryWorkShop factoryWorkShop;

	private CollectRefFormBean collectRefFormBean;
	private BranchBean branchBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private String branch;

	private String formId;

	private ArrangmentHeader arrangmentHeader;

	private Date formTime;
	private Date auditTime;

	private List<Map> shopLst;

	private String jsonData;

	private Integer preVersion;
	private Integer currentVersion;

	private OperationVersionBean operationVersionBean;
	private String isWelcome;

	@Override
	public String execute() throws Exception {
		Date businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		startDate = endDate = businessDate;
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			startDate = endDate = businessDate = null;
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String auditView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			arrangmentHeader = arrangmentHeaderBean.queryById(formId);

			formTime = arrangmentHeader.getFormTime();
			auditTime = formTime;

			arrangmentHeader.setAuditor(getLoginUsername());
			arrangmentHeader.setAuditorId(getLoginUserId());
			arrangmentHeader.setFormRefId(collectRefFormBean.queryRefs(formId));

			preVersion = operationVersionBean.queryVersion(formId).getVersion();
		}
		return SUCCESS;
	}

	public String scanView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(formId)) {
			arrangmentHeader = arrangmentHeaderBean.queryById(formId);

			if (arrangmentHeader != null) {
				arrangmentHeader.setFormRefId(collectRefFormBean.queryRefs(formId));
			}
			formTime = arrangmentHeader.getFormTime();
			auditTime = arrangmentHeader.getAuditTime();
		}

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = arrangmentHeaderBean.countUnAudit(startDate, endDate, branchId, UN_AUDIT);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ArrangmentHeader> headerLst = arrangmentHeaderBean
					.queryUnAudit(startDate, endDate, branchId, UN_AUDIT);

			int rownumber = getStart();
			for (ArrangmentHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				json.put("formStatus", header.getStatus());
				json.put("formRefId", collectRefFormBean.queryRefs(header.getFormId()));
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

	/**
	 * 审核后的手动生成计划单，做出库单查询
	 * */
	public void doManualQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = arrangmentHeaderBean.countManual(startDate, endDate, branchId, UN_OUT);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ArrangmentHeader> headerLst = arrangmentHeaderBean.queryManual(startDate, endDate, branchId, UN_OUT);

			int rownumber = getStart();
			for (ArrangmentHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String auditTime = getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);
				json.put("formStatus", header.getStatus());
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

	public void doAuditedQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = arrangmentHeaderBean.countAudit(startDate, endDate, branchId, AUDIT_ED);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ArrangmentHeader> headerLst = arrangmentHeaderBean.queryAudit(startDate, endDate, branchId, AUDIT_ED);

			int rownumber = getStart();
			for (ArrangmentHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);

				// 制单日期
				String formTime = getDateStr(header.getFormTime());
				json.put("formTime", formTime);

				// 审核日期
				String auditTime = getDateStr(header.getAuditTime());
				json.put("auditTime", auditTime);

				String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
				json.put("formRefId", collectRefFormBean.queryRefs(header.getFormId()));
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

	/**
	 * 原料采购单生成时使用
	 */
	public void doCFQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<ArrangmentHeader> headerLst = arrangmentHeaderBean.queryCFAudit(startDate, endDate);

		int rownumber = getStart();
		for (ArrangmentHeader header : headerLst) {
			JSONObject json = JSONObject.fromObject(header);

			// 制单日期
			String formTime = getDateStr(header.getFormTime());
			json.put("formTime", formTime);

			// 审核日期
			String auditTime = getDateStr(header.getAuditTime());
			json.put("auditTime", auditTime);

			json.put("formRefId", collectRefFormBean.queryRefs(header.getFormId()));

			String formStatus = formStatusBean.getCurrentStatus(header.getFormId());
			json.put("formStatus", formStatus);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		JSONObject result = new JSONObject();
		result.put("arrange", arr);
		result.put("msg", "ok");

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		String cfCode = getLoginBranchId();
		List<ArrangmentDetail> detailLst = arrangmentDetailBean.query(formId, cfCode);// 直接将库存数带出来，库存数是所有仓库库存和
		int rownumber = 1;

		factoryWorkShop = factoryWorkShopBean.queryByOrder(cfCode, 0);
		for (ArrangmentDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("rownumber", rownumber);

			String produceTime = getDateStr(detail.getProduceTime());
			json.put("produceTime", produceTime);

			String completeTime = getDateStr(detail.getCompleteTime());
			json.put("completeTime", completeTime);

			json.put("storageCount", detail.getItemCount());

			if (TextUtil.isEmpty(detail.getWorkshop())) {
				json.put("workshop", factoryWorkShop.getWorkshop());
			}

			if (detail.getItemPrice() == null) {
				json.put("itemUnitPrice", 0.0);
			} else {
				json.put("itemUnitPrice", detail.getItemPrice());
			}
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
		arrangementService.auditBill(getLoginBranchId(), getLoginUserId(), getLoginUsername(), arrangmentHeader,
				jsonData, currentVersion);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCollectRefFormBean(CollectRefFormBean collectRefFormBean) {
		this.collectRefFormBean = collectRefFormBean;
	}

	public void setArrangmentDetailBean(ArrangmentDetailBean arrangmentDetailBean) {
		this.arrangmentDetailBean = arrangmentDetailBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public ArrangmentHeader getArrangmentHeader() {
		return arrangmentHeader;
	}

	public void setArrangmentHeader(ArrangmentHeader arrangmentHeader) {
		this.arrangmentHeader = arrangmentHeader;
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

	public String getBranch() {
		return branch;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setArrangementService(ArrangementService arrangementService) {
		this.arrangementService = arrangementService;
	}

	public void setArrangmentHeaderBean(ArrangmentHeaderBean arrangmentHeaderBean) {
		this.arrangmentHeaderBean = arrangmentHeaderBean;
	}

	public void setStorageBean(StorageBean storageBean) {
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public Integer getPreVersion() {
		return preVersion;
	}

	public void setPreVersion(Integer preVersion) {
		this.preVersion = preVersion;
	}

	public Integer getCurrentVersion() {
		return currentVersion;
	}

	public void setCurrentVersion(Integer currentVersion) {
		this.currentVersion = currentVersion;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setFactoryWorkShopBean(FactoryWorkShopBean factoryWorkShopBean) {
		this.factoryWorkShopBean = factoryWorkShopBean;
	}

	public void setFactoryWorkShop(FactoryWorkShop factoryWorkShop) {
		this.factoryWorkShop = factoryWorkShop;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
}
