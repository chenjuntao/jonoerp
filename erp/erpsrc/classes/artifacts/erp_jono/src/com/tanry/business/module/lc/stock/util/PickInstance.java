/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 20, 2015 by liyzh
 * Last edited on Apr 20, 2015 by liyzh
 * 
 * 说明 ：一个捡货实例，根据原料分组总计，同时保存配送单与领料单之间的关联关系
 */
package com.tanry.business.module.lc.stock.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class PickInstance {

	/**
	 * 根据原料和门店分组，便于获取对应关系
	 * 
	 * @param itemLst
	 * @return
	 */
	public static List<JSONObject> group(List<Map> itemLst) {
		List<JSONObject> itemLst2 = new ArrayList<JSONObject>();
		Map<String, List<JSONObject>> detailMap = new HashMap<String, List<JSONObject>>();
		Map<String, Double> countMap = new HashMap<String, Double>();
		for (Map item : itemLst) {// 首先根据原料和门店分组，便于获取对应关系
			String itemId = (String) item.get("itemId");
			String requesterId = (String) item.get("requesterId");
			String key = itemId + "-" + requesterId;
			if (detailMap.get(key) == null) {
				JSONObject json = JSONObject.fromObject(item);
				json.put("key", key);
				itemLst2.add(json);
				detailMap.put(key, new ArrayList<JSONObject>());
				countMap.put(key, 0.0);
			}
			// 保存捡货与配送的关联关系
			JSONObject formRef = new JSONObject();
			formRef.put("formRefId", item.get("formId"));
			formRef.put("itemCount", item.get("packagingCount"));
			detailMap.get(key).add(formRef);
			countMap.put(key, countMap.get(key) + (Double) item.get("packagingCount"));
		}

		for (JSONObject json : itemLst2) {// 把累计的数量及明细记录放回到各条记录
			String itemId = (String) json.get("itemId");
			String requesterId = (String) json.get("requesterId");
			String key = itemId + "-" + requesterId;
			Double packagingCount = countMap.get(key);
			json.put("packagingCount", packagingCount);
			json.put("refLst", detailMap.get(key));
		}
		return itemLst2;
	}

	/**
	 * 部门数据行转列
	 * 
	 * @param itemLst
	 * @return
	 */
	public static JSONObject transfer(List<JSONObject> itemLst) {
		Map<String, Map<String, Object>> branchMap = new HashMap<String, Map<String, Object>>();
		List<Map<String, Object>> branchLst = new ArrayList<Map<String, Object>>();
		for (JSONObject item : itemLst) {
			String requesterId = (String) item.get("requesterId");
			String key = requesterId;
			if (branchMap.get(key) == null) {
				Map<String, Object> branch = new HashMap<String, Object>();
				branch.put("requesterId", requesterId);
				branch.put("requester", item.get("requester"));
				branch.put("packagingCount", item.get("packagingCount"));
				branch.put("key", key);
				branchLst.add(branch);
				branchMap.put(key, branch);
			}
		}
		Map<String, Object> bran = new HashMap<String, Object>();
		bran.put("requesterId", "sum");
		bran.put("requester", "合计");
		bran.put("key", "sum");
		branchMap.put("sum", bran);

		class BranchComparator implements Comparator<Map> {
			@Override
			public int compare(Map a, Map b) {
				String keya = (String) a.get("key");
				String keyb = (String) b.get("key");
				return keya.compareToIgnoreCase(keyb);
			}
		}
		Collections.sort(branchLst, new BranchComparator());

		List<String> branchArr = new ArrayList<String>();
		JSONArray branchCols = new JSONArray();
		for (Map<String, Object> branch : branchLst) {
			branchArr.add((String) branch.get("key"));
			JSONObject col = new JSONObject();
			col.put("label", branch.get("requester"));
			col.put("field", branch.get("key"));
			col.put("className", "grid-number");
			branchCols.add(col);
		}

		branchArr.add("sum");
		JSONObject col = new JSONObject();
		col.put("label", "合计");
		col.put("field", "sum");
		col.put("className", "grid-number");
		branchCols.add(col);
		// 按原料分组，将部门行转列
		List<JSONObject> itemLst2 = new ArrayList<JSONObject>();
		Map<String, JSONObject> itemMap = new HashMap<String, JSONObject>();
		int rownumber = 1;
		for (JSONObject item : itemLst) {
			String itemId = (String) item.get("itemId");
			String key = itemId;
			if (itemMap.get(key) == null) {
				JSONObject json = JSONObject.fromObject(item);
				json.put("key", key);
				json.put("rownumber", rownumber);
				itemLst2.add(json);
				itemMap.put(key, json);// 存入map后方便将部门行转列
				itemMap.get(key).put("sum", item.get("packagingCount"));// 计算合计
				rownumber++;
			} else {// 计算合计
				itemMap.get(key).put("sum", (Double) itemMap.get(key).get("sum") + (Double) item.get("packagingCount"));
			}
			String requesterId = (String) item.get("requesterId");
			itemMap.get(key).put(requesterId, item.get("packagingCount"));// 根据门店存入相应的列数据
		}
		JSONObject result = new JSONObject();
		result.put("branchCols", branchCols);
		result.put("branchIds", branchArr);
		result.put("itemLst", itemLst2);
		return result;
	}
}
