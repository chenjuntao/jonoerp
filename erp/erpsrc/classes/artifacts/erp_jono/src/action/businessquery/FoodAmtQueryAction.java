package action.businessquery;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodAmtTTCNYBean;
import logic.businessquery.FoodCategoryBean;
import logic.store.BranchBean;
import logic.store.TherapyBean;
import pojo.businessquery.FoodAmtTTCNY;
import pojo.businessquery.FoodCategory;
import pojo.store.Therapy;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class FoodAmtQueryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String shopC;
	private String startDate;
	private String endDate;
	private String categoryId;
	private String foodRawMaterialId;

	private float amtTTCNY;

	private FoodAmtTTCNYBean foodAmtTTCNYBean;
	private FoodCategoryBean foodCategoryBean;
	private TherapyBean therapyBean;

	private List<FoodAmtTTCNY> foodSubAmtList;
	private List<FoodCategory> foodAmtCategoryList;

	private Map<String, Integer> headMap;

	private BranchBean branchBean;
	private List<Map> shopLst;

	private double allAmt;

	public Map<String, Integer> getHeadMap() {
		return headMap;
	}

	private List<Therapy> itemLst;

	public String getFoodRawMaterialId() {
		return foodRawMaterialId;
	}

	public void setFoodRawMaterialId(String foodRawMaterialId) {
		this.foodRawMaterialId = foodRawMaterialId;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public List<Therapy> getItemLst() {
		return itemLst;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public float getAmtTTCNY() {
		return amtTTCNY;
	}

	public void setAmtTTCNY(float amtTTCNY) {
		this.amtTTCNY = amtTTCNY;
	}

	public String getShopC() {
		return this.shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		Calendar cal = Calendar.getInstance();

		// 当前月的第一天
		cal.set(GregorianCalendar.DAY_OF_MONTH, 1);
		Date date = cal.getTime();
		return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
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

	public void setFoodAmtTTCNYBean(FoodAmtTTCNYBean foodAmtTTCNYBean) {
		this.foodAmtTTCNYBean = foodAmtTTCNYBean;
	}

	public void setFoodCategoryBean(FoodCategoryBean foodCategoryBean) {
		this.foodCategoryBean = foodCategoryBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public FoodAmtTTCNYBean getFoodAmtTTCNYBean() {
		return foodAmtTTCNYBean;
	}

	public FoodCategoryBean getFoodCategoryBean() {
		return foodCategoryBean;
	}

	public List<FoodAmtTTCNY> getFoodSubAmtList() {
		return foodSubAmtList;
	}

	public List<FoodCategory> getFoodAmtCategoryList() {
		return foodAmtCategoryList;
	}

	public double getAllAmt() {
		return allAmt;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "%");
		allItem.put("name", "--请选择--");
		shopLst.add(0, allItem);
		return SUCCESS;
	}

	private void verifyDate() {
		if (TextUtil.isEmpty(startDate)) {
			startDate = getStartDate();
		}

		if (TextUtil.isEmpty(endDate)) {
			endDate = getEndDate();
		}
	}

	public String doCategoryQuery() throws NoPrivilegeException, SQLException, NoConnection {
		verifyDate();

		foodAmtCategoryList = foodCategoryBean.listCategory(shopC, startDate, endDate);

		headMap = new LinkedHashMap<String, Integer>();

		allAmt = foodCategoryBean.getAmt(shopC, startDate, endDate).doubleValue();

		for (int i = 0; i < foodAmtCategoryList.size(); i++) {
			FoodCategory foodCategory = foodAmtCategoryList.get(i);
			String categoryName = foodCategory.getCategoryName();

			if (!headMap.containsKey(categoryName)) {
				headMap.put(categoryName, 1);
			} else {
				int value = headMap.get(categoryName);
				headMap.put(categoryName, ++value);
			}
		}

		return SUCCESS;
	}

	public String doDetailCategoryQuery() throws NoPrivilegeException, SQLException, NoConnection {
		verifyDate();

		foodSubAmtList = foodAmtTTCNYBean.listFoodAmtTTCNY(shopC, startDate, endDate, categoryId);
		return SUCCESS;
	}

	public String dofoodRawMaterialQuery() throws NoPrivilegeException, SQLException, NoConnection {
		itemLst = therapyBean.GetTherapysById(foodRawMaterialId);
		return SUCCESS;
	}

}
