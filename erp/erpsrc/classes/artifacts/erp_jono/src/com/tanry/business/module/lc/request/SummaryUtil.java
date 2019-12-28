/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 2, 2014 by liyzh
 * Last edited on Dec 2, 2014 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.lc.request;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.NumberUtil;

public class SummaryUtil {

	/**
	 * 对直配单据进行处理 根据供应商和门店分组，用于以后生成直配采购单，（已废弃），参考groupCross和groupDirect PD means
	 * provider and department
	 * 
	 * @param itemLst
	 * @return
	 */
	@Deprecated
	public static List<Map<String, Object>> groupByPD(List<Map> directLst) {
		List<Map<String, Object>> directLst2 = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> directMap = new HashMap<String, List<Map<String, Object>>>();
		for (Map item : directLst) {// 根据供应商和门店分组，用于以后生成直配采购单
			String providerId = (String) item.get("providerId");
			String buyerId = (String) item.get("buyerId");
			String key = providerId + "-" + buyerId;
			if (directMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("buyerId", buyerId);
				bill.put("provider", item.get("provider"));
				bill.put("buyerName", item.get("buyerName"));
				bill.put("deliveryType", item.get("deliveryType"));
				bill.put("key", key);
				directLst2.add(bill);
				directMap.put(key, new ArrayList<Map<String, Object>>());
			}
			directMap.get(key).add(item);
		}
		for (Map<String, Object> bill : directLst2) {
			List<Map<String, Object>> detailLst = SummaryUtil.groupByItem(directMap.get(bill.get("key")), null);
			detailLst.remove(detailLst.size() - 1);
			bill.put("details", detailLst);
		}
		return directLst2;
	}

	/**
	 * 对越库单据进行处理 先根据供应商分组，然后根据原料和门店分组，用于以后生成越库采购单，查询直配单时也按这种形式处理（大单形式）
	 * 
	 * @param itemLst
	 * @return
	 */
	public static List<Map<String, Object>> groupCross(Date businessDate, boolean forShow, List<Map> crossLst,
			List<Map> mrpLst) {
		List<Map<String, Object>> providerLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> providerMap = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Integer> periodMap = new HashMap<String, Integer>();

		for (Map item : crossLst) {// 先根据供应商分组，然后根据原料分组，列出所有门店，用于以后生成越库采购单
			String providerId = (String) item.get("providerId");
			String key = providerId;
			if (providerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("provider", item.get("provider"));
				bill.put("deliveryType", item.get("deliveryType"));
				bill.put("key", key);
				providerLst.add(bill);
				providerMap.put(key, new ArrayList<Map<String, Object>>());
				periodMap.put(key, 0);
			}
			Integer supplyPeriod = (Integer) item.get("supplyPeriod");
			supplyPeriod = supplyPeriod == null ? 0 : supplyPeriod;
			if (supplyPeriod > periodMap.get(key)) {
				periodMap.put(key, supplyPeriod);// 对于同一个供应商，获取各个原料中最大的周期
			}

			providerMap.get(key).add(item);
		}

		Iterator<Map<String, Object>> providerIter = providerLst.iterator();
		while (providerIter.hasNext()) {
			Map<String, Object> bill = providerIter.next();

			List<Map<String, Object>> detailLst = SummaryUtil.groupItemDept(providerMap.get(bill.get("key")), mrpLst);
			if (detailLst.size() == 0) {
				providerIter.remove();// 如果数量为零，则不需要下采购单
				continue;
			}

			Integer supplyPeriod = periodMap.get(bill.get("key"));

			bill.put("supplyPeriod", supplyPeriod);
			Date receiveTime = DateTimeUtil.addDays(businessDate, supplyPeriod);
			if (!forShow) {// 如果用于保存
				bill.put("receiveTime", receiveTime);
			} else {// 用于页面展示
				bill.put("receiveTime", DateTimeUtil.getDateStr(receiveTime));
			}

			bill.put("details", detailLst);

		}

		return providerLst;
	}

	public static List<Map<String, Object>> groupDir(Date businessDate, boolean forShow, List<Map> directLst,
			List<Map> mrpLst) {
		List<Map<String, Object>> providerLst = new ArrayList<Map<String, Object>>();
		Map<String, Integer> periodMap = new HashMap<String, Integer>();
		Map<String, List<Map<String, Object>>> providerMap = new HashMap<String, List<Map<String, Object>>>();
		for (Map item : directLst) {// 根据供应商分组，用于以后生成统配采购单
			String providerId = (String) item.get("providerId");
			String key = providerId;
			if (providerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("provider", item.get("provider"));
				bill.put("deliveryType", FormConstant.UNIFIED_DELIVERY);
				bill.put("key", key);
				providerLst.add(bill);
				providerMap.put(key, new ArrayList<Map<String, Object>>());
				periodMap.put(key, 0);
			}
			Integer supplyPeriod = (Integer) item.get("supplyPeriod");
			supplyPeriod = supplyPeriod == null ? 0 : supplyPeriod;
			if (supplyPeriod > periodMap.get(key)) {
				periodMap.put(key, supplyPeriod);// 对于同一个供应商，获取各个原料中最大的周期
			}
			providerMap.get(key).add(item);
		}
		for (Map<String, Object> bill : providerLst) {
			Integer supplyPeriod = periodMap.get(bill.get("key"));
			bill.put("supplyPeriod", supplyPeriod);
			bill.put("buyers", SummaryUtil.groupByDept(providerMap.get(bill.get("key"))));
			Date receiveTime = DateTimeUtil.addDays(businessDate, supplyPeriod);
			if (!forShow) {// 如果用于保存
				bill.put("receiveTime", receiveTime);
			} else {// 用于页面展示
				bill.put("receiveTime", DateTimeUtil.getDateStr(receiveTime));
			}
		}
		return providerLst;
	}

	/**
	 * 对统配单据进行处理 根据供应商分组，用于以后生成统配采购单
	 * 
	 * @param itemLst
	 * @return
	 */
	public static List<Map<String, Object>> groupUnified(Date businessDate, boolean forShow, List<Map> unifiedLst,
			List<Map> mrpLst) {
		// 对统配单据进行处理
		List<Map<String, Object>> providerLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> providerMap = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Object> periodMap = new HashMap<String, Object>();

		for (Map item : unifiedLst) {// 根据供应商分组，用于以后生成统配采购单
			String providerId = (String) item.get("providerId");
			String key = providerId;
			if (providerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("provider", item.get("provider"));
				bill.put("deliveryType", FormConstant.UNIFIED_DELIVERY);
				bill.put("key", key);
				providerLst.add(bill);
				providerMap.put(key, new ArrayList<Map<String, Object>>());
				// periodMap.put(key, 0);
			}

			// Integer supplyPeriod = (Integer) item.get("supplyPeriod");
			// if (supplyPeriod != null && supplyPeriod > periodMap.get(key)) {
			// periodMap.put(key, supplyPeriod);// 对于同一个供应商，获取各个原料中最大的周期
			// }

			providerMap.get(key).add(item);
		}

		Iterator<Map<String, Object>> providerIter = providerLst.iterator();
		while (providerIter.hasNext()) {
			Map<String, Object> bill = providerIter.next();
			List<Map<String, Object>> detailLst = SummaryUtil.groupByItem(providerMap.get(bill.get("key")), mrpLst);
			periodMap = detailLst.get(detailLst.size() - 1);
			if (detailLst.size() == 1) {
				providerIter.remove();// 如果数量为零，则不需要下采购单
				continue;
			}
			detailLst.remove(detailLst.size() - 1);
			bill.put("details", detailLst);

			Integer supplyPeriod = (Integer) periodMap.get(bill.get("key"));
			bill.put("supplyPeriod", supplyPeriod);
			Date receiveTime = DateTimeUtil.addDays(businessDate, supplyPeriod);
			if (!forShow) {// 如果用于保存
				bill.put("receiveTime", receiveTime);
			} else {// 用于页面展示
				bill.put("receiveTime", DateTimeUtil.getDateStr(receiveTime));
			}
		}
		return providerLst;
	}

	/**
	 * 对直配单据进行处理 先根据供应商分组，满足直配大单的要求；然后根据部门分组，满足直配小单的要求
	 * 
	 * @param itemLst
	 * @return
	 */
	public static List<Map<String, Object>> groupDirect(List<Map> directLst) {
		List<Map<String, Object>> providerLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> providerMap = new HashMap<String, List<Map<String, Object>>>();
		for (Map item : directLst) {// 根据供应商分组，用于以后生成统配采购单
			String providerId = (String) item.get("providerId");
			String key = providerId;
			if (providerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("provider", item.get("provider"));
				bill.put("deliveryType", FormConstant.UNIFIED_DELIVERY);
				bill.put("key", key);
				providerLst.add(bill);
				providerMap.put(key, new ArrayList<Map<String, Object>>());
			}
			providerMap.get(key).add(item);
		}
		for (Map<String, Object> bill : providerLst) {
			bill.put("buyers", SummaryUtil.groupByDept(providerMap.get(bill.get("key"))));
		}
		return providerLst;
	}

	/**
	 * 根据原料和门店进行分组，越库形式使用
	 * 
	 * @param itemLst
	 * @param mrpLst
	 * @return
	 */
	public static List<Map<String, Object>> groupItemDept(List<Map<String, Object>> itemLst, List<Map> mrpLst) {
		List<Map<String, Object>> detailLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> detailMap = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Double> countMap = new HashMap<String, Double>();
		for (Map<String, Object> item : itemLst) {
			String itemId = (String) item.get("itemId");
			String buyerId = (String) item.get("buyerId");
			String key = itemId + "-" + buyerId;

			if (detailMap.get(key) == null) {
				Map<String, Object> detail = new HashMap<String, Object>();
				detail.put("itemId", itemId);
				detail.put("itemName", item.get("itemName"));
				detail.put("itemCategory", item.get("itemCategory"));
				detail.put("itemDimension", item.get("itemDimension"));
				detail.put("itemSpecification", item.get("itemSpecification"));
				detail.put("buyerId", buyerId);
				detail.put("buyerName", item.get("buyerName"));
				detail.put("deliveryType", item.get("deliveryType"));
				detail.put("minOrderCount", item.get("minOrderCount"));
				Double itemUnitPrice = (Double) item.get("itemUnitPrice");
				if (itemUnitPrice == null) {
					itemUnitPrice = 0.0;
				}

				detail.put("itemUnitPrice", itemUnitPrice);

				Double itemPrice = (Double) item.get("itemPrice");
				if (itemPrice == null) {
					itemPrice = 0.0;
				}

				detail.put("itemPrice", itemPrice);
				detail.put("key", key);
				detailLst.add(detail);
				detailMap.put(key, new ArrayList<Map<String, Object>>());
				countMap.put(key, 0.0);
			}
			detailMap.get(key).add(item);
			countMap.put(key, countMap.get(key) + (Double) item.get("orderCount"));
		}

		Iterator<Map<String, Object>> detailIter = detailLst.iterator();
		while (detailIter.hasNext()) {
			Map<String, Object> detail = detailIter.next();

			String key = (String) detail.get("key");
			detail.put("details", detailMap.get(key));
			Double orderCount = countMap.get(key);
			detail.put("orderCount", orderCount);
			Double itemUnitPrice = (Double) detail.get("itemUnitPrice");
			Double itemPrice = (Double) detail.get("itemPrice");
			detail.put("payAmt", getPayAmt(orderCount, itemUnitPrice));
			detail.put("unitAmt", getPayAmt(orderCount, itemPrice));

			// 如果采购量小于最小订货量，则取最小订货量
			Double numberOrderCount = NumberUtil.getDouble(detail.get("orderCount"));
			Double numberMinOrderCount = NumberUtil.getDouble(detail.get("minOrderCount"));

			if (numberOrderCount > 0 && numberOrderCount < numberMinOrderCount) {
				detail.put("orderCount", numberMinOrderCount);
			} else if (numberOrderCount == 0) {
				detailIter.remove();// 如果数量为零，则不需要下采购单
				continue;
			} else {
				detail.put("orderCount", numberOrderCount);
			}

		}

		return detailLst;
	}

	private static double getPayAmt(double count, double unitPrice) {
		BigDecimal bCount = new BigDecimal(count);
		BigDecimal bUnitPrice = new BigDecimal(unitPrice);
		BigDecimal payAmt = bUnitPrice.multiply(bCount).setScale(2, BigDecimal.ROUND_HALF_EVEN);
		return payAmt.doubleValue();
	}

	/**
	 * 与前台传过来的MRP值进行比对，如果一致，则采用MRP计算后的数据，统配使用
	 * 
	 * @param mrpLst
	 * @param item
	 * @return
	 */
	private static Map<String, Object> findMRP(List<Map> mrpLst, Map<String, Object> item) {
		for (Map<String, Object> mrp : mrpLst) {
			if (mrp.get("itemId").equals(item.get("itemId"))) {
				Double orderCount = Double.parseDouble(mrp.get("orderCount") + "");
				item.put("orderCount", orderCount);
				item.put("payAmt", mrp.get("payAmt"));
				item.put("unitAmt", mrp.get("unitAmt"));
				break;
			}
		}
		return item;
	}

	/**
	 * 根据门店进行分组，直配形式使用
	 * 
	 * @param itemLst
	 * @return
	 */
	public static List<Map<String, Object>> groupByDept(List<Map<String, Object>> itemLst) {
		List<Map<String, Object>> buyerLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> buyerMap = new HashMap<String, List<Map<String, Object>>>();
		for (Map<String, Object> item : itemLst) {//
			String buyerId = (String) item.get("buyerId");
			String key = buyerId;
			if (buyerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", item.get("providerId"));
				bill.put("provider", item.get("provider"));
				bill.put("buyerId", buyerId);
				bill.put("buyerName", item.get("buyerName"));
				bill.put("deliveryType", item.get("deliveryType"));
				bill.put("key", key);
				buyerLst.add(bill);
				buyerMap.put(key, new ArrayList<Map<String, Object>>());
			}
			buyerMap.get(key).add(item);
		}
		for (Map<String, Object> bill : buyerLst) {
			List<Map<String, Object>> detailLst = SummaryUtil.groupByItem(buyerMap.get(bill.get("key")), null);
			detailLst.remove(detailLst.size() - 1);
			bill.put("details", detailLst);
		}
		return buyerLst;
	}

	/**
	 * 根据原料进行分组
	 * 
	 * @param itemLst
	 * @return
	 */
	public static List<Map<String, Object>> groupByItem(List<Map<String, Object>> itemLst, List<Map> mrpLst) {
		List<Map<String, Object>> detailLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> detailMap = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Double> countMap = new HashMap<String, Double>();
		Map<String, Object> periodMap = new HashMap<String, Object>();
		for (Map<String, Object> item : itemLst) {//
			String itemId = (String) item.get("itemId");

			if (detailMap.get(itemId) == null) {
				Map<String, Object> detail = new HashMap<String, Object>();
				detail.put("itemId", itemId);
				detail.put("itemName", item.get("itemName"));
				detail.put("itemCategory", item.get("itemCategory"));
				detail.put("itemDimension", item.get("itemDimension"));
				detail.put("itemSpecification", item.get("itemSpecification"));
				detail.put("minOrderCount", item.get("minOrderCount"));
				detail.put("providerId", item.get("providerId"));
				detail.put("supplyPeriod", item.get("supplyPeriod"));
				// detail.put("orderCount", detailIter.order_count());

				Double itemUnitPrice = (Double) item.get("itemUnitPrice");
				if (itemUnitPrice == null) {
					itemUnitPrice = 0.0;
				}

				detail.put("itemUnitPrice", itemUnitPrice);

				Double itemPrice = (Double) item.get("itemPrice");
				if (itemPrice == null) {
					itemPrice = 0.0;
				}

				detail.put("itemPrice", itemPrice);
				double orderCount = (Double) item.get("orderCount");
				detailLst.add(detail);
				detailMap.put(itemId, new ArrayList<Map<String, Object>>());
				countMap.put(itemId, 0.0);
			}
			detailMap.get(itemId).add(item);
			countMap.put(itemId, countMap.get(itemId) + (Double) item.get("orderCount"));
		}

		Iterator<Map<String, Object>> detailIter = detailLst.iterator();
		while (detailIter.hasNext()) {
			Map<String, Object> detail = detailIter.next();
			String key = (String) detail.get("itemId");
			if (mrpLst != null) {
				detail = findMRP(mrpLst, detail);// 统配使用
				if (NumberUtil.getDouble(detail.get("orderCount")) == 0) {
					detailIter.remove();// 如果数量为零，则不需要下采购单
					continue;
				}

			} else {
				Double orderCount = countMap.get(key);
				detail.put("orderCount", orderCount);// 直配
				Double itemUnitPrice = (Double) detail.get("itemUnitPrice");
				detail.put("payAmt", getPayAmt(orderCount, itemUnitPrice));
				Double itemPrice = (Double) detail.get("itemPrice");
				detail.put("unitAmt", getPayAmt(orderCount, itemPrice));
			}
			String providerId = (String) detail.get("providerId");
			if (periodMap.get(providerId) == null) {
				periodMap.put(providerId, 0);
			}
			Integer supplyPeriod = (Integer) detail.get("supplyPeriod");
			if (supplyPeriod != null && supplyPeriod > (Integer) periodMap.get(providerId)) {
				periodMap.put(providerId, supplyPeriod);// 对于同一个供应商，获取各个原料中最大的周期
			}
			detail.put("details", detailMap.get(key));
		}
		detailLst.add(periodMap);
		return detailLst;
	}
}
