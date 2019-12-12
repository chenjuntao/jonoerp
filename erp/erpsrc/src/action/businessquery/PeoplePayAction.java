package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.PeopleBean;
import logic.businessquery.PeoplePayBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class PeoplePayAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;

	private String branchId;
	private String peopleId;

	private PeoplePayBean peoplePayBean;

	private List<Map> shopLst;
	private BranchBean branchBean;

	private PeopleBean peopleBean;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		endDate = branchBean.getMaxBusinessDate();
		startDate = DateTimeUtil.addDays(endDate, -7);

		return SUCCESS;
	}

	public void getData() throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;

		List<Map> infoMapLists = peoplePayBean.query(startDate, endDate,
				branchId, peopleId);

		Map<String, String> tagMap = new HashMap<String, String>();

		for (int i = 0, length = infoMapLists.size(); i < length; i++) {
			Map map = infoMapLists.get(i);
			String index = map.get("cBranch_C").toString()
					+ map.get("cSettleMan").toString();

			if (!tagMap.containsKey(index)) {
				tagMap.put(index, "");
				jsonObject = JSONObject.fromObject(map);
				jsonObject.put(map.get("cPay_C"), map.get("nPayAmt"));
			} else {
				jsonObject.put(map.get("cPay_C"), map.get("nPayAmt"));

				if (i == length - 1) {
					jsonArray.add(jsonObject);
				} else {
					Map nextMap = infoMapLists.get(i + 1);
					String nextIndex = nextMap.get("cBranch_C").toString()
							+ nextMap.get("cSettleMan").toString();
					if (!index.equals(nextIndex)) {
						jsonArray.add(jsonObject);
					}
				}
			}
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 取得付款方式扩展的列信息
	public void getColumn() throws NoPrivilegeException, SQLException,
			NoConnection {
		List<Map> infoMaps = peoplePayBean.queryColumns(startDate, endDate,
				branchId, peopleId);

		JSONArray jsonArray = new JSONArray();
		JSONObject sumO = new JSONObject();
		sumO.put("pay_sum", "合计");
		jsonArray.add(sumO);

		for (Map map : infoMaps) {
			JSONObject o = new JSONObject();
			o.put(map.get("cPay_C").toString(), map.get("cPay_N").toString());
			jsonArray.add(o);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getPeople() throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray jsonArray = JSONArray.fromObject(peopleBean.listMan("cSettleMan", "cSettleMan"));

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPeoplePayBean(PeoplePayBean peoplePayBean) {
		this.peoplePayBean = peoplePayBean;
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

	public void setPeopleId(String peopleId) {
		this.peopleId = peopleId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setPeopleBean(PeopleBean peopleBean) {
		this.peopleBean = peopleBean;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateTime(startDate,
				DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateTime(endDate,
				DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

}
