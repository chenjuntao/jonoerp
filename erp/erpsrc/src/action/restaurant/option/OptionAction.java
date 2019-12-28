package action.restaurant.option;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import service.restaurant.option.OptionService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class OptionAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int currentYear;

	private OptionService optionService;

	private String branchId;
	private String menuIds;
	private int year;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		year = DateTimeUtil.getCurrentYear();
		return SUCCESS;
	}

	public void saveData() throws NoPrivilegeException, SQLException, NoConnection {
		optionService.saveData(branchId, menuIds, getLoginUserId(), getLoginUsername());
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryBranchTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "餐厅");
		arr.add(root);

		List<Map> roleLst = optionService.queryBranchTree();
		for (Map user : roleLst) {
			JSONObject json = JSONObject.fromObject(user);
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		currentYear = DateTimeUtil.getCurrentYear();

		String startDate = currentYear + "-" + "01" + "-" + "01";
		String endDate = currentYear + "-" + "12" + "-" + "31";

		List<Map> lists = optionService.queryTree(branchId, startDate, endDate);
		JSONArray arr = new JSONArray();
		for (Map temp : lists) {
			JSONObject json = JSONObject.fromObject(temp);
			if (temp.get("isCheck").toString().equals("Y")) {
				json.put("checked", true);
			} else {
				json.put("checked", false);
			}
			arr.add(json);
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setOptionService(OptionService optionService) {
		this.optionService = optionService;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getYear() {
		return year;
	}

	public void setMenuIds(String menuIds) {
		this.menuIds = menuIds;
	}

}
