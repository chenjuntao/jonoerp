/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on June 13, 2014 by pw
 * Last edited on Aug 6, 2014 by liyzh
 * 
 * 说明： 收入明细查询action
 */
package action.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.ShopBean;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

@SuppressWarnings("rawtypes")
public class ShopAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShopBean shopBean;

	private boolean fromMobile;
	private String startDate;
	private String endDate;

	private int pageCount = 0;// 总页数

	private int currentPage = 0;// 总页数
	private int pageSize = 0;// 总页数

	private List shops;
	private Map<String, List> paySumMaps;

	public Map<String, List> getPaySumMaps() {
		return paySumMaps;
	}

	public ShopBean getShopBean() {
		return shopBean;
	}

	public void setShopBean(ShopBean shopBean) {
		this.shopBean = shopBean;
	}

	public List getShops() {
		return shops;
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

	private void verifyDate() {
		if (TextUtil.isEmpty(startDate)) {
			startDate = getStartDate();
		}

		if (TextUtil.isEmpty(endDate)) {
			endDate = getEndDate();
		}
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String listShop() throws NoPrivilegeException, SQLException, NoConnection {
		verifyDate();
		shops = shopBean.listShop(startDate, endDate);
		if (fromMobile) {
			return "mobile";
		}
		return "success";
	}

	public String listByDay() throws NoPrivilegeException, SQLException, NoConnection {
		verifyDate();

		// 按天查询分页
		int totalCount = shopBean.countByDay(startDate, endDate);
		pageCount = (totalCount / pageSize) + totalCount % 2;

		int startRow = (currentPage - 1) * pageSize + 1;
		paySumMaps = shopBean.listShopByDay(startDate, endDate, startRow, startRow + pageSize);

		if (fromMobile) {
			return "mobile";
		}
		return "success";
	}

	public boolean getFromMobile() {
		return fromMobile;
	}

	public void setFromMobile(boolean fromMobile) {
		this.fromMobile = fromMobile;
	}
}
