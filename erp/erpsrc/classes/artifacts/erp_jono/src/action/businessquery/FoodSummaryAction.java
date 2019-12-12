package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodSummaryBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.FoodSummary;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class FoodSummaryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private FoodSummaryBean foodSummaryBean;

	private List<Map> shopLst;

	private String startDate;
	private String endDate;
	private String shopC;

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setFoodSummaryBean(FoodSummaryBean foodSummaryBean) {
		this.foodSummaryBean = foodSummaryBean;
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

	public void foodSummary() throws NoPrivilegeException, SQLException, NoConnection {
		List<FoodSummary> foodSummarys = foodSummaryBean.foodSummary(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (FoodSummary item : foodSummarys) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("foodN", item.getFoodN());
			jObject.put("foodC", item.getFoodC());
			jObject.put("unit", item.getUnit());
			jObject.put("qty", item.getQty());
			jObject.put("pesentQty", item.getPesentQty());
			jObject.put("returnQty", item.getReturnQty());

			jsonArray.add(jObject);
			rownumber++;
		}

		FoodSummary item = foodSummaryBean.foodSummarySum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("qty", item.getQty());
		sumJObject.put("pesentQty", item.getPesentQty());
		sumJObject.put("returnQty", item.getReturnQty());
		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
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
