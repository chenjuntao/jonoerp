package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Branch;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class ShopInfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private String branchName;
	private String branchType;
	private String queryAllFlag;
	private String branchId;

	public String execute() {
		return SUCCESS;
	}

	public void listShopInfo() throws NoPrivilegeException, SQLException, NoConnection {
		if (branchName == null) {
			branchName = "";
		}

		List<Branch> shopInfoLists = branchBean.queryByTypeName(BranchTypeEnum.getEnum(branchType), branchName,
				queryAllFlag);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (Branch item : shopInfoLists) {
			JSONObject jObject = JSONObject.fromObject(item);
			jObject.put("rownumber", rownumber);
			jsonArray.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setQueryAllFlag(String queryAllFlag) {
		this.queryAllFlag = queryAllFlag;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchId() {
		return branchId;
	}

}
