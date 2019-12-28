/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on July 31, 2014 by pw
 * Last edited on Aug 6, 2014 by liyzh
 * 
 * 说明： 万元用量分析action
 */
package action.businessquery;

import java.io.IOException;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodCategoryBean;
import logic.businessquery.FoodRawMaterialBean;
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

@SuppressWarnings("rawtypes")
public class FoodRawQueryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private String shopC;
	private String startDate;
	private String endDate;
	private String categoryId;
	private BigDecimal payamt;

	private FoodRawMaterialBean foodRawMaterialBean;

	private List<FoodCategoryBean> categoryLst;
	private List<Map> detailLst;

	private Map<String, Integer> headMap;

	private BranchBean branchBean;
	private List<Map> shopLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public void queryAmount() throws NoPrivilegeException, SQLException, NoConnection {
		payamt = foodRawMaterialBean.getAmt(shopC, getStartDate(), getEndDate());

		JSONObject result = new JSONObject();
		result.put("payAmt", payamt);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		detailLst = foodRawMaterialBean.getDetail(shopC, getStartDate(), getEndDate(), categoryId);
		return SUCCESS;
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

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getPayamt() {
		return this.payamt;
	}

	public List<FoodCategoryBean> getCategoryLst() {
		return categoryLst;
	}

	public Map<String, Integer> getHeadMap() {
		return headMap;
	}

	public List<Map> getDetailLst() {
		return detailLst;
	}

	public void setFoodCategoryBean(FoodCategoryBean foodCategoryBean) {
	}

	public void setFoodRawMaterialBean(FoodRawMaterialBean foodRawMaterialBean) {
		this.foodRawMaterialBean = foodRawMaterialBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

}
