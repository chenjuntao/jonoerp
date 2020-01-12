package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.BusinessCountBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.BusinessCount;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class BusinessCountAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BusinessCountBean businessCountBean;

	private List<Map> shopLst;

	private String startDate;
	private String endDate;
	private String shopC;

	public void setBusinessCountBean(BusinessCountBean businessCountBean) {
		this.businessCountBean = businessCountBean;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		return DateTimeUtil.getDateTime(DateTimeUtil.getMonthFristDay(), DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		if (!TextUtil.isEmpty(endDate)) {
			return endDate;
		}
		return DateTimeUtil.getDate();
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void shift() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		List<BusinessCount> businessCounts = businessCountBean.shift(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (BusinessCount item : businessCounts) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("shift", item.getShift());
			jObject.put("billNum", item.getBillNum());
			jObject.put("guestNum", item.getGuestNum());
			jObject.put("foodAmt", item.getFoodAmt());
			jObject.put("disAmt", item.getDisAmt());
			jObject.put("roundAmt", item.getRoundAmt());
			jObject.put("oughtAmt", item.getOughtAmt());
			jObject.put("payAmt", item.getPayAmt());
			jObject.put("presentAmt", item.getPresentAmt());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		BusinessCount item = businessCountBean.shiftSum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("billNum", item.getBillNum());
		sumJObject.put("guestNum", item.getGuestNum());
		sumJObject.put("foodAmt", item.getFoodAmt());
		sumJObject.put("disAmt", item.getDisAmt());
		sumJObject.put("roundAmt", item.getRoundAmt());
		sumJObject.put("oughtAmt", item.getOughtAmt());
		sumJObject.put("payAmt", item.getPayAmt());
		sumJObject.put("presentAmt", item.getPresentAmt());

		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void floor() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		List<BusinessCount> businessCounts = businessCountBean.floor(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (BusinessCount item : businessCounts) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("billNum", item.getBillNum());
			jObject.put("guestNum", item.getGuestNum());
			jObject.put("foodAmt", item.getFoodAmt());
			jObject.put("disAmt", item.getDisAmt());
			jObject.put("roundAmt", item.getRoundAmt());
			jObject.put("oughtAmt", item.getOughtAmt());
			jObject.put("payAmt", item.getPayAmt());
			jObject.put("presentAmt", item.getPresentAmt());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		BusinessCount item = businessCountBean.floorSum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("billNum", item.getBillNum());
		sumJObject.put("guestNum", item.getGuestNum());
		sumJObject.put("foodAmt", item.getFoodAmt());
		sumJObject.put("disAmt", item.getDisAmt());
		sumJObject.put("roundAmt", item.getRoundAmt());
		sumJObject.put("oughtAmt", item.getOughtAmt());
		sumJObject.put("payAmt", item.getPayAmt());
		sumJObject.put("presentAmt", item.getPresentAmt());

		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void period() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		List<BusinessCount> businessCounts = businessCountBean.period(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (BusinessCount item : businessCounts) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("period", item.getPeriod());
     		jObject.put("billNum", item.getBillNum());
			jObject.put("guestNum", item.getGuestNum());
			jObject.put("foodAmt", item.getFoodAmt());
			jObject.put("disAmt", item.getDisAmt());
			jObject.put("roundAmt", item.getRoundAmt());
			jObject.put("oughtAmt", item.getOughtAmt());
			jObject.put("payAmt", item.getPayAmt());
			jObject.put("presentAmt", item.getPresentAmt());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		BusinessCount item = businessCountBean.periodSum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("billNum", item.getBillNum());
		sumJObject.put("guestNum", item.getGuestNum());
		sumJObject.put("foodAmt", item.getFoodAmt());
		sumJObject.put("disAmt", item.getDisAmt());
		sumJObject.put("roundAmt", item.getRoundAmt());
		sumJObject.put("oughtAmt", item.getOughtAmt());
		sumJObject.put("payAmt", item.getPayAmt());
		sumJObject.put("presentAmt", item.getPresentAmt());

		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setConditions() {
		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "");
		allItem.put("name", "--请选择--");
		shopLst.add(0, allItem);

		return SUCCESS;
	}

}
