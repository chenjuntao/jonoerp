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
package action.centralfactory.productionDemand;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CFSummaryUtil {

	/**
	 * 对越库单据进行处理 先根据供应商分组，然后根据原料和门店分组
	 * 
	 * @param itemLst
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static List<Map<String, Object>> groupCross(List<Map> crossLst) {
		List<Map<String, Object>> providerLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> providerMap = new HashMap<String, List<Map<String, Object>>>();
		for (Map item : crossLst) {// 先根据供应商分组，然后根据原料分组
			String providerId = (String) item.get("providerId");
			String key = providerId;
			if (providerMap.get(key) == null) {
				Map<String, Object> bill = new HashMap<String, Object>();
				bill.put("providerId", providerId);
				bill.put("provider", item.get("provider"));
				bill.put("key", key);
				providerLst.add(bill);
				providerMap.put(key, new ArrayList<Map<String, Object>>());
			}
			providerMap.get(key).add(item);
		}
		for (Map<String, Object> bill : providerLst) {
			bill.put("details", CFSummaryUtil.groupItemDept(providerMap
					.get(bill.get("key"))));
		}
		return providerLst;
	}

	/**
	 * 根据原料和门店进行分组
	 * 
	 * @param itemLst
	 * @return
	 */
	public static List<Map<String, Object>> groupItemDept(
			List<Map<String, Object>> itemLst) {
		List<Map<String, Object>> detailLst = new ArrayList<Map<String, Object>>();
		Map<String, List<Map<String, Object>>> detailMap = new HashMap<String, List<Map<String, Object>>>();
		Map<String, Double> countMap = new HashMap<String, Double>();
		for (Map<String, Object> item : itemLst) {
			String itemId = (String) item.get("itemId");
			String key = itemId;
			if (detailMap.get(key) == null) {
				Map<String, Object> detail = new HashMap<String, Object>();
				detail.put("itemId", itemId);
				detail.put("itemName", item.get("itemName"));
				detail.put("itemCategory", item.get("itemCategory"));
				detail.put("itemDimension", item.get("itemDimension"));
				detail.put("itemSpecification", item.get("itemSpecification"));
				detail.put("item_count", item.get("item_count"));
				detail.put("key", key);
				detailLst.add(detail);
				detailMap.put(key, new ArrayList<Map<String, Object>>());
				countMap.put(key, 0.0);
			}
			detailMap.get(key).add(item);
			countMap.put(key,
					countMap.get(key) + (Double) item.get("item_count"));
		}
		for (Map<String, Object> detail : detailLst) {
			String key = (String) detail.get("key");
			detail.put("details", detailMap.get(key));
			detail.put("total", countMap.get(key));
		}
		return detailLst;
	}
}
