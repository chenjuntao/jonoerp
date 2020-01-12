/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 3, 2014 by liyzh
 * Last edited on Nov 3, 2014 by liyzh
 * 
 * 说明： 内部万元需求量查询
 */
package com.tanry.business.module.lc.request.action;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodCategoryBean;
import logic.module.lc.DemandTTCNYBean;
import pojo.businessquery.FoodCategory;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * TTCNY is "ten thousand CNY(china yuan)"
 */
public class DemandTTCNYAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String categoryId;
	private BigDecimal payamt;

	private DemandTTCNYBean demandTTCNYBean;
	private FoodCategoryBean foodCategoryBean;

	private List<FoodCategory> categoryLst;
	private List<Map> detailLst;

	private Map<String, Integer> headMap;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		payamt = demandTTCNYBean.queryTotal(startDate, endDate);

		categoryLst = foodCategoryBean.listRawCategory();

		headMap = new LinkedHashMap<String, Integer>();

		for (int i = 0; i < categoryLst.size(); i++) {
			FoodCategory category = categoryLst.get(i);
			String categoryName = category.getCategoryName();

			if (!headMap.containsKey(categoryName)) {
				headMap.put(categoryName, 1);
			} else {
				int value = headMap.get(categoryName);
				headMap.put(categoryName, ++value);
			}
		}

		return SUCCESS;
	}

	public String queryDetail() throws NoPrivilegeException, SQLException, NoConnection {
		detailLst = demandTTCNYBean.queryDetail(startDate, endDate, categoryId);
		return SUCCESS;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public BigDecimal getPayamt() {
		return this.payamt;
	}

	public List<FoodCategory> getCategoryLst() {
		return categoryLst;
	}

	public Map<String, Integer> getHeadMap() {
		return headMap;
	}

	public List<Map> getDetailLst() {
		return detailLst;
	}

	public void setFoodCategoryBean(FoodCategoryBean foodCategoryBean) {
		this.foodCategoryBean = foodCategoryBean;
	}

	public void setDemandTTCNYBean(DemandTTCNYBean demandTTCNYBean) {
		this.demandTTCNYBean = demandTTCNYBean;
	}

}
