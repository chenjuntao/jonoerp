/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on June 30, 2014 by pw
 * Last edited on Aug 6, 2014 by liyzh
 * 
 * 说明： 付款方式查询action
 */
package action.businessquery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import logic.NoConnection;
import logic.businessquery.ShopPayBean;
import logic.businessquery.TableBean;

import org.apache.struts2.ServletActionContext;

import pojo.businessquery.ShopPay;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;

@SuppressWarnings("rawtypes")
public class PaymentQueryAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private boolean fromMobile;
	private ShopPayBean shopPayBean;
	private TableBean tableBean;

	private Map<String, List> shops;
	private String startDate;
	private String endDate;

	private String shopC;

	private int pageCount = 0;

	private int currentPage = 0;
	private int pageSize = 2;

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public TableBean getTableBean() {
		return tableBean;
	}

	public void setTableBean(TableBean tableBean) {
		this.tableBean = tableBean;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		return DateTimeUtil.getDateTime(DateTimeUtil.getMonthFristDay(), DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	public String getEndDate() {
		if (!TextUtil.isEmpty(endDate)) {
			return endDate;
		}
		return DateTimeUtil.getDate();
	}

	private void verifyDate() {
		if (TextUtil.isEmpty(startDate)) {
			startDate = getStartDate();
		}

		if (TextUtil.isEmpty(endDate)) {
			endDate = getEndDate();
		}
	}

	public String doShopPayQuery() throws NoPrivilegeException, SQLException, NoConnection {
		verifyDate();

		List<ShopPay> shops = null;
		shops = shopPayBean.listOneShopPay(startDate, endDate, shopC);

		Map<String, Double> paySumMaps = new HashMap<String, Double>();

		for (int i = 0; i < shops.size(); i++) {
			Map<String, Double> map = shops.get(i).getPay();

			for (Entry<String, Double> entry : map.entrySet()) {
				String key = entry.getKey();
				Double value = entry.getValue();

				if (paySumMaps.containsKey(key)) {
					Double sumValue = paySumMaps.get(key) + value;
					paySumMaps.put(key, sumValue);
				} else {
					paySumMaps.put(key, value);
				}
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("paySumMaps", paySumMaps);
		request.setAttribute("shops", shops);

		return "success";
	}

	public String doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		verifyDate();

		List<ShopPay> shops = null;
		shops = shopPayBean.listShopPay(startDate, endDate);

		Map<String, Double> paySumMaps = new HashMap<String, Double>();

		for (int i = 0; i < shops.size(); i++) {
			Map<String, Double> map = shops.get(i).getPay();

			for (Entry<String, Double> entry : map.entrySet()) {
				String key = entry.getKey();
				Double value = entry.getValue();

				if (paySumMaps.containsKey(key)) {
					Double sumValue = paySumMaps.get(key) + value;
					paySumMaps.put(key, sumValue);
				} else {
					paySumMaps.put(key, value);
				}
			}
		}
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("paySumMaps", paySumMaps);
		request.setAttribute("shops", shops);

		if (fromMobile) {
			return "mobile";
		}
		return "success";
	}

	public String queryByDay() throws NoPrivilegeException, SQLException, NoConnection {
		java.sql.Date stime = SqlDateUtil.getSqlDate(DateTimeUtil.parse(getStartDate(), null));
		java.sql.Date etime = SqlDateUtil.getSqlDate(DateTimeUtil.parse(getEndDate(), null));

		// 按天查询分页
		int totalCount = shopPayBean.countByDay(stime, etime);
		pageCount = (totalCount / pageSize) + totalCount % 2;

		int startRow = (currentPage - 1) * pageSize + 1;
		shops = shopPayBean.listShopPayByDay(stime, etime, startRow, startRow + pageSize);

		List<Map<String, Double>> paySubSumList = new ArrayList<Map<String, Double>>();
		Map<String, Double> paySumMap = new HashMap<String, Double>();

		for (Entry<String, List> entry : shops.entrySet()) {
			List contentList = entry.getValue();
			Map<String, Double> subSumMap = new HashMap<String, Double>();
			for (int i = 0; i < contentList.size(); i++) {
				Map<String, Double> map = ((ShopPay) contentList.get(i)).getPay();

				for (Entry<String, Double> entry2 : map.entrySet()) {
					String key = entry2.getKey();
					Double value = entry2.getValue();

					// 小计
					if (subSumMap.containsKey(key)) {
						Double subSumValue = subSumMap.get(key) + value;
						subSumMap.put(key, subSumValue);
					} else {
						subSumMap.put(key, value);
					}
				}
			}
			paySubSumList.add(subSumMap);
		}

		for (Map<String, Double> map : paySubSumList) {
			for (Entry<String, Double> entry : map.entrySet()) {
				paySumMap.put(entry.getKey(), 0.0);// 去掉合计，仅用作键的遍历
			}
		}

		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("paySubSumList", paySubSumList);
		request.setAttribute("paySumMap", paySumMap);

		if (fromMobile) {
			return "mobile";
		}
		return "success";
	}

	public void setShopPayBean(ShopPayBean shopPayBean) {
		this.shopPayBean = shopPayBean;
	}

	public Map<String, List> getShops() {
		return shops;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public boolean getFromMobile() {
		return fromMobile;
	}

	public void setFromMobile(boolean fromMobile) {
		this.fromMobile = fromMobile;
	}
}
