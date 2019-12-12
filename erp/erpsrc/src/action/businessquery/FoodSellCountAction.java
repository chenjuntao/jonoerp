package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodSellCountBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.FoodSellCount;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class FoodSellCountAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private FoodSellCountBean foodSellCountBean;

	private List<Map> shopLst;

	private String startDate;
	private String endDate;
	private String shopC;

	public void setFoodSellCountBean(FoodSellCountBean foodSellCountBean) {
		this.foodSellCountBean = foodSellCountBean;
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

	public void bigCategory() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		List<FoodSellCount> foodSellCounts = foodSellCountBean.bigCategory(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (FoodSellCount item : foodSellCounts) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("categoryId", item.getCategoryId());
			jObject.put("categoryN", item.getCategoryN());
			jObject.put("qty", item.getQty());
			jObject.put("amt", item.getAmt());
			jObject.put("afterAmt", item.getAfterAmt());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		FoodSellCount item = foodSellCountBean.bigCategorySum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("qty", item.getQty());
		sumJObject.put("amt", item.getAmt());
		sumJObject.put("afterAmt", item.getAfterAmt());
		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void littleCategory() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		List<FoodSellCount> foodSellCounts = foodSellCountBean.littleCategory(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (FoodSellCount item : foodSellCounts) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("categoryId", item.getCategoryId());
			jObject.put("categoryN", item.getCategoryN());
			jObject.put("qty", item.getQty());
			jObject.put("amt", item.getAmt());
			jObject.put("afterAmt", item.getAfterAmt());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		FoodSellCount item = foodSellCountBean.littleCategorySum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("qty", item.getQty());
		sumJObject.put("amt", item.getAmt());
		sumJObject.put("afterAmt", item.getAfterAmt());
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
