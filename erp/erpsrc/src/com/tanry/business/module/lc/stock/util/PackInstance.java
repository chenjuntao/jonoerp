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
 * 说明： 一个装箱实例，包含若干门店的若干个箱子
 */
package com.tanry.business.module.lc.stock.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pojo.form.PackingDetail;

public class PackInstance {

	private static Map<String, Integer> boxOrder = null;// 存储各个部门下的箱子排序
	private static BigDecimal weightSum = null;// 用于累计箱子已经存放了多少重量的原料
	private static BigDecimal volSum = null;// 用于累计箱子已经存放了多少体积的原料
	private static String boxId = null;
	private static Map<String, Map<String, Object>> boxMap = null;

	public static Map<String, Map<String, Object>> iterate(List<PackingDetail> itemLst) {
		{
			boxMap = new HashMap<String, Map<String, Object>>();
			boxOrder = new HashMap<String, Integer>();
			boxId = null;
		}
		// 按门店、箱子类别分别依次装箱，一个箱子既不能超过体积限制，也不能超过重量限制
		for (PackingDetail detail : itemLst) {
			String branchId = detail.getBranchId();
			String boxTypeId = detail.getBoxTypeId();
			String key = branchId + "-" + boxTypeId;
			Integer order = boxOrder.get(key);
			if (order == null) {
				boxId = newBox(detail);
			}
			detail.setBoxId(boxId);

			Double itemCount = detail.getItemCount();

			addItem(detail, itemCount);
		}
		return boxMap;
	}

	/**
	 * 一个箱子中放进去一种原料
	 * @param boxId
	 * @param detail
	 * @param itemCount
	 */
	private static void addItem(PackingDetail pDetail, double itemCount) {
		PackingDetail detail = copyDetail(pDetail);
		detail.setBoxId(boxId);
		detail.setBoxName((String) boxMap.get(boxId).get("boxName"));
		// 重量
		BigDecimal weight = new BigDecimal(detail.getUnitWeight() * itemCount);
		BigDecimal boxWeight = new BigDecimal(detail.getBoxWeight());
		BigDecimal unitWeight = new BigDecimal(detail.getUnitWeight());
		// 体积
		BigDecimal volume = new BigDecimal(detail.getUnitVolume() * itemCount);
		BigDecimal boxVolume = new BigDecimal(detail.getBoxVolume());
		BigDecimal unitVolume = new BigDecimal(detail.getUnitVolume());

		int weightCom = weightSum.add(weight).compareTo(boxWeight);
		int volCom = volSum.add(volume).compareTo(boxVolume);
		if (weightCom == 1 || volCom == 1) {// 如果一种原料在一个箱子放不下，则增加一个箱子，体积与重量的任一限制都不能超过
			BigDecimal remainWeight = boxWeight.subtract(weightSum);// 上一个箱子空余的重量
			int remainWCount = remainWeight.divide(unitWeight).intValue();
			System.out.println("remainWCount ：" + remainWCount);

			BigDecimal remainVol = boxWeight.subtract(weightSum);// 上一个箱子空余的体积
			int remainVCount = remainVol.divide(unitVolume).intValue();
			System.out.println("remainVCount ：" + remainVCount);

			int remainCount = remainVCount;
			if (remainVCount > remainWCount) {// 取数值小的量
				remainCount = remainWCount;
			}
			// 设置剩余项的数量
			detail.setItemCount((double) remainCount);

			// 设置剩余项的体积和重量
			BigDecimal itemWeight = unitWeight.multiply(new BigDecimal(remainCount));
			BigDecimal itemVol = unitWeight.multiply(new BigDecimal(remainCount));
			detail.setWeight(itemWeight.doubleValue());
			detail.setVolume(itemVol.doubleValue());

			List<PackingDetail> detailLst = (List<PackingDetail>) boxMap.get(boxId).get("detailLst");
			detailLst.add(detail);
			// boxMap.get(boxId).put("detailLst", detailLst);

			// System.out.println(boxId);
			weightSum = weightSum.add(itemWeight);
			volSum = volSum.add(itemVol);
			// System.out.println("boxSum ：" + boxSum);
			boxMap.get(boxId).put("weightSum", weightSum);
			boxMap.get(boxId).put("volSum", volSum);

			// System.out.println("itemCount ：" + itemCount);
			int nextCount = (int) (itemCount - remainCount);
			// System.out.println("nextCount ：" + nextCount);
			// 下次开始使用新箱子
			boxId = newBox(detail);
			addItem(detail, nextCount);// 递归调用
		} else {
			// System.out.println(boxId);
			weightSum = weightSum.add(weight);
			volSum = volSum.add(volume);
			boxMap.get(boxId).put("weightSum", weightSum);
			boxMap.get(boxId).put("volSum", volSum);

			detail.setItemCount(itemCount);
			BigDecimal itemWeight = unitWeight.multiply(new BigDecimal(itemCount));
			detail.setWeight(itemWeight.doubleValue());
			BigDecimal itemVol = unitVolume.multiply(new BigDecimal(itemCount));
			detail.setVolume(itemVol.doubleValue());
			List<PackingDetail> detailLst = (List<PackingDetail>) boxMap.get(boxId).get("detailLst");
			detailLst.add(detail);

			if (weightCom == 0) {
				// 下次开始使用新箱子
				boxId = newBox(detail);
			}
		}
	}

	private static String newBox(PackingDetail pDetail) {
		String branchId = pDetail.getBranchId();
		String boxTypeId = pDetail.getBoxTypeId();
		String key = branchId + "-" + boxTypeId;
		Integer order = boxOrder.get(key);
		if (order == null) {
			order = 1;
		} else {
			order++;
		}
		boxOrder.put(key, order);
		weightSum = BigDecimal.ZERO;
		volSum = BigDecimal.ZERO;
		String boxId = key + "-" + formatSerial(order);
		Map<String, Object> boxInfo = new HashMap<String, Object>();
		boxInfo.put("boxId", boxId);
		String boxName = pDetail.getBranchName() + pDetail.getBoxTypeName() + order + "号箱";
		boxInfo.put("boxName", boxName);
		boxInfo.put("branchId", pDetail.getBranchId());
		boxInfo.put("branchName", pDetail.getBranchName());
		boxInfo.put("boxTypeName", pDetail.getBoxTypeName());
		boxInfo.put("boxWeight", pDetail.getBoxWeight());
		boxInfo.put("boxVolume", pDetail.getBoxVolume());
		boxInfo.put("boxOrder", order);
		List<PackingDetail> detailLst = new ArrayList<PackingDetail>();
		boxInfo.put("detailLst", detailLst);
		boxMap.put(boxId, boxInfo);
		return boxId;
	}

	/**
	 * 补齐箱子的两位数编码
	 * @param serialNum
	 * @return
	 */
	public static String formatSerial(int serialNum) {
		String serial = serialNum + "";
		int addLength = 2 - serial.length(); // 补齐三位数编码
		for (int i = 0; i < addLength; i++) {
			serial = "0" + serial;
		}
		return serial;
	}

	private static PackingDetail copyDetail(PackingDetail detail) {
		PackingDetail pDetail = new PackingDetail();
		pDetail.setBoxId(detail.getBoxId());
		pDetail.setBranchId(detail.getBranchId());
		pDetail.setBranchName(detail.getBranchName());
		pDetail.setBoxTypeId(detail.getBoxTypeId());
		pDetail.setItemId(detail.getItemId());
		pDetail.setItemName(detail.getItemName());
		pDetail.setItemDimension(detail.getItemDimension());
		pDetail.setItemCategory(detail.getItemCategory());
		pDetail.setUnitWeight(detail.getUnitWeight());
		pDetail.setBoxWeight(detail.getBoxWeight());
		pDetail.setUnitVolume(detail.getUnitVolume());
		pDetail.setBoxVolume(detail.getBoxVolume());
		pDetail.setBoxTypeName(detail.getBoxTypeName());
		return pDetail;
	}
}
