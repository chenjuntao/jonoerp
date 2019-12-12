package action.restaurant.production;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONObject;
import pojo.form.SelfSemisDetail;
import pojo.form.SelfSemisHeader;
import pojo.store.BranchStorage;
import service.restaurant.production.ManageSelfBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class CreateSelfSemisAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private BranchBean branchBean;
	private BranchStorageBean branchStorageBean;

	private List<Map> shopList;
	private List<BranchStorage> storageList;
	private String branchType;
	private String jsonData;
	private String rawJsonData;
	private String formTime;

	private SelfSemisHeader selfSemisHeader;
	private SelfSemisDetail selfSemisDetail;
	private ManageSelfBillService manageSelfBillService;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopList = branchBean.queryById(getLoginBranchId());
		branchType = getLoginBranchType();
		if (shopList.size() > 0) {
			storageList = branchStorageBean.query(shopList.get(0).get("code").toString(), branchType);
		}
		return SUCCESS;
	}

	public String commitView() throws NoPrivilegeException, SQLException, NoConnection {

		String branchId = selfSemisHeader.getBranchId();
		Date businessDate = branchBean.GetBranchById(branchId).getBusinessDate();

		selfSemisHeader.setCreatorManId(getLoginUserId());
		selfSemisHeader.setFormMaker(getLoginUsername());

		formTime = DateTimeUtil.getDateStr(businessDate);
		return SUCCESS;
	}

	public void doCommit() throws NoPrivilegeException, SQLException, NoConnection {
		String formId = "";
		selfSemisHeader.setFormTime(DateTimeUtil.parse(formTime));

		formId = manageSelfBillService.CreateSelfBill(selfSemisHeader, jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("formId", formId);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Map> getShopList() {
		return shopList;
	}

	public void setShopList(List<Map> shopList) {
		this.shopList = shopList;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public void setStorageList(List<BranchStorage> storageList) {
		this.storageList = storageList;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getFormTime() {
		return formTime;
	}

	public void setFormTime(String formTime) {
		this.formTime = formTime;
	}

	public SelfSemisHeader getSelfSemisHeader() {
		return selfSemisHeader;
	}

	public void setSelfSemisHeader(SelfSemisHeader selfSemisHeader) {
		this.selfSemisHeader = selfSemisHeader;
	}

	public SelfSemisDetail getSelfSemisDetail() {
		return selfSemisDetail;
	}

	public void setSelfSemisDetail(SelfSemisDetail selfSemisDetail) {
		this.selfSemisDetail = selfSemisDetail;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public String getRawJsonData() {
		return rawJsonData;
	}

	public void setRawJsonData(String rawJsonData) {
		this.rawJsonData = rawJsonData;
	}

	public void setManageSelfBillService(ManageSelfBillService manageSelfBillService) {
		this.manageSelfBillService = manageSelfBillService;
	}

}
