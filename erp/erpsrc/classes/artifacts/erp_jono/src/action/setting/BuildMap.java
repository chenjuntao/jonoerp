/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 24, 2015 by czc
 * Last edited on Apr 24, 2015 by czc
 * 
 * 说明： 配置备份和还原的各种表结构
 */
package action.setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuildMap {
	public static final String[] headers = { "D_T0_STORAGE_IN_OUT", "D_T1_REQUEST_DETAIL", "D_T1_REQUEST_HEADER",
			"D_T1_PURCHASING_DETAIL", "D_T1_PURCHASING_HEADER", "D_T1_COLLECT_DETAIL", "D_T1_COLLECT_HEADER",
			"D_T1_INPUT_DETAIL", "D_T1_INPUT_HEADER", "D_T1_SHIPPING_DETAIL", "D_T1_SHIPPING_HEADER",
			"D_T1_SHIPPING_ANTIAUDIT_DETAIL", "D_T1_SHIPPING_ANTIAUDIT_HEADER", "D_T1_RETURN_GOODS_DETAIL",
			"D_T1_RETURN_GOODS_HEADER", "D_T1_LOSS_DETAIL", "D_T1_LOSS_HEADER", "D_T1_TRANSFER_DETAIL",
			"D_T1_TRANSFER_HEADER", "D_T1_CHECK_LOCK", "D_T1_CHECK_DETAIL", "D_T1_CHECK_HEADER",
			"D_T1_ARRENGMENT_DETAIL", "D_T1_ARRENGMENT_HEADER", "D_T1_WORKORDER_ITEM", "D_T1_WORK_ORDER_HEADER",
			"D_T1_REQUISITION_DETAIL", "D_T1_REQUISITION_HEADER", "D_T1_PICKING_DETAIL", "D_T1_PICKING_HEADER",
			"D_T1_PACKING_DETAIL", "D_T1_PACKING_HEADER", "D_T1_PRICE_ADJUST_DETAIL", "D_T1_PRICE_ADJUST_HEADER",
			"D_T1_STATEMENT_HEADER", "D_T1_STATEMENT_DETAIL" };

	public static final String[] businesses = { "D_T_FOOD_BILLS", "D_T_BILL_PAY", "D_T_FOOD_BILL" };

	public static final String[] branchs = { "D_T0_OPTION", "D_T2_BRANCH_TYPE", "D_T2_BOX_TYPE", "D_T2_ITEM_PRICE",
			"D_T2_ITEM_FOOD_SUIT", "D_T2_BRANCH_STORAGE", "D_T2_BRANCH", "D_T2_ITEM_META", "D_T2_ITEM_CATEGORY",
			"D_T2_THERAPY", "D_T2_STORAGE", "D_T2_SHELF_ITEM", "D_T2_SUPPLIER_BRANCH_ITEM", "D_T2_SUPPLY_CYCLE",
			"D_T2_PRODUCTION_CYCLE", "D_T2_DELIVERY_REGION", "D_T2_DELIVERY_REGION_BRANCH", "D_T2_DELIVERY_TYPE",
			"D_T2_DELIVERY_UNIT", "D_T2_MAKING_PROCESS", "D_T1_TEMPLATE_META", "D_T1_TEMPLATE_ITEM" };

	public static final String[] syses = { "D_T0_ROLE", "D_T0_MENU", "D_T0_PERMISSION", "D_T2_USER", "D_T0_USERTOROLE",
			"D_T0_USERTOBRANCH" };

	public static final String[] raws = { "D_T2_ITEM_META", "D_T2_DELIVERY_TYPE", "D_T2_DELIVERY_UNIT",
			"D_T2_ITEM_PRICE", "D_T2_SUPPLY_CYCLE" };

	public static final String[] semises = { "D_T2_ITEM_META", "D_T2_ITEM_PRICE", "D_T2_ITEM_WORKSHOP",
			"D_T2_MAKING_PROCESS", "D_T2_PRODUCTION_CYCLE", "D_T2_SHELF_ITEM", "D_T2_STORAGE",
			"D_T2_SUPPLIER_BRANCH_ITEM", "D_T2_THERAPY" };

	public static Map<String, String> fileNames() {
		Map<String, String> fileMap = new HashMap<String, String>();
		fileMap.put("D_T2_ITEM_META", "物料基本信息表");
		fileMap.put("D_T2_DELIVERY_TYPE", "配送模式表");
		fileMap.put("D_T2_DELIVERY_UNIT", "原料其他基础信息表");
		fileMap.put("D_T2_ITEM_PRICE", "物料价格表");
		fileMap.put("D_T2_SUPPLY_CYCLE", "供货周期表");
		fileMap.put("D_T2_PRODUCTION_CYCLE", "生产周期表");

		return fileMap;
	}

	public static Map<String, String> itemNames() {
		Map<String, String> itemMap = new HashMap<String, String>();
		itemMap.put("ITEM_ID", "物料编码");
		itemMap.put("ITEM_BAR_CODE", "条码");
		itemMap.put("ITEM_NAME", "物料名称");
		itemMap.put("ITEM_NAME_ENG", "英文名称");
		itemMap.put("ITEM_DIMENSION", "库存单位");
		itemMap.put("QUERY_CODE", "助记码");
		itemMap.put("CATEGORY_ID", "类别编码");
		itemMap.put("ITEM_SPECIFICATION", "规格");
		itemMap.put("ITEM_TYPE", "类型(原材料:RAW/半成品:SEMIS/成品:PRODUCT)");
		itemMap.put("ITEM_PIC", "对应的图片id");
		itemMap.put("ITEM_NOTE", "备注信息");
		itemMap.put("BOX_TYPE", "箱子类型(冷藏/非冷藏)");
		itemMap.put("ENABLED", "是否启用(0未启用，1已启用)");
		itemMap.put("EFFECT_DATE", "生效日期");

		return itemMap;
	}

	/**
	 * 建立导出半成品信息表结构
	 * 
	 * @return
	 */
	public static List<Map<String, Map<String, Object>>> buildSemises() {
		List<Map<String, Map<String, Object>>> tableList = new ArrayList<Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> tableMap = new HashMap<String, Map<String, Object>>();
		tableMap.put("D_T2_ITEM_META", null);
		tableMap.put("D_T2_ITEM_PRICE", null);
		tableMap.put("D_T2_ITEM_WORKSHOP", null);
		tableMap.put("D_T2_MAKING_PROCESS", null);
		tableMap.put("D_T2_PRODUCTION_CYCLE", null);
		tableMap.put("D_T2_SHELF_ITEM", null);
		tableMap.put("D_T2_STORAGE", null);
		tableMap.put("D_T2_SUPPLIER_BRANCH_ITEM", null);
		tableMap.put("D_T2_THERAPY", null);
		tableList.add(tableMap);

		return tableList;
	}

	/**
	 * 建立导出基本物料信息表结构
	 * 
	 * @return
	 */
	public static List<Map<String, Map<String, Object>>> buildRaws() {
		List<Map<String, Map<String, Object>>> tableList = new ArrayList<Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> tableMap = new HashMap<String, Map<String, Object>>();
		tableMap.put("D_T2_ITEM_META", null);
		tableMap.put("D_T2_DELIVERY_TYPE", null);
		tableMap.put("D_T2_DELIVERY_UNIT", null);
		tableMap.put("D_T2_ITEM_PRICE", null);
		tableMap.put("D_T2_SHELF_ITEM", null);
		tableMap.put("D_T2_STORAGE", null);
		tableMap.put("D_T2_SUPPLIER_BRANCH_ITEM", null);
		tableMap.put("D_T2_SUPPLY_CYCLE", null);
		tableList.add(tableMap);

		return tableList;
	}

	/* 构建导出单据结构 */
	public static List<Map<String, Map<String, Object>>> buildMap() {
		List<Map<String, Map<String, Object>>> tableList = new ArrayList<Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> tableMap = new HashMap<String, Map<String, Object>>();
		Map<String, Object> Map = new HashMap<String, Object>();
		tableMap.put("D_T0_FORM_META", null);

		Map = new HashMap<String, Object>();
		Map.put("data", "STATUS_TIME");
		tableMap.put("D_T0_FORM_STATUS", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "OPERATION_TIME");
		tableMap.put("D_T0_STORAGE_IN_OUT", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "OPERATION_TIME");
		tableMap.put("D_T0_STORAGE_IN_OUT", Map);

		tableMap.put("D_T1_TEMPLATE_META", null);

		tableMap.put("D_T1_TEMPLATE_ITEM", null);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_REQUEST_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_REQUEST_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_REQUEST_DETAIL", Map);

		tableMap.put("D_T1_PURCHASING_MAPPING", null);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_PURCHASING_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_PURCHASING_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_PURCHASING_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_COLLECT_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_COLLECT_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_COLLECT_DETAIL", Map);

		tableMap.put("D_T1_COLLECT_REF_FORM", null);

		Map = new HashMap<String, Object>();
		Map.put("data", "INPUT_TIME");
		tableMap.put("D_T1_INPUT_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_INPUT_HEADER");
		Map.put("data", "INPUT_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_INPUT_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_SHIPPING_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_SHIPPING_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_SHIPPING_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "ANTIAUDIT_TIME");
		tableMap.put("D_T1_SHIPPING_ANTIAUDIT_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_SHIPPING_ANTIAUDIT_HEADER");
		Map.put("data", "ANTIAUDIT_TIME");
		Map.put("item", "FORM_REF_ID");
		tableMap.put("D_T1_SHIPPING_ANTIAUDIT_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "RETURN_TIME");
		tableMap.put("D_T1_RETURN_GOODS_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_RETURN_GOODS_HEADER");
		Map.put("data", "RETURN_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_RETURN_GOODS_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "LOSS_TIME");
		tableMap.put("D_T1_LOSS_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_LOSS_HEADER");
		Map.put("data", "LOSS_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_LOSS_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_TRANSFER_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_TRANSFER_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_TRANSFER_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "LOCK_TIME");
		tableMap.put("D_T1_CHECK_LOCK", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_CHECK_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_CHECK_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_CHECK_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_ARRENGMENT_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_ARRENGMENT_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_ARRENGMENT_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_WORK_ORDER_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_WORK_ORDER_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_WORKORDER_ITEM", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_REQUISITION_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_REQUISITION_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_REQUISITION_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_PICKING_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_PICKING_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_PICKING_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_PICKING_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_PICKING_REF", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_PACKING_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_PACKING_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_PACKING_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_PRICE_ADJUST_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_PRICE_ADJUST_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_PRICE_ADJUST_DETAIL", Map);

		Map = new HashMap<String, Object>();
		Map.put("data", "FORM_TIME");
		tableMap.put("D_T1_STATEMENT_HEADER", Map);

		Map = new HashMap<String, Object>();
		Map.put("table", "D_T1_STATEMENT_HEADER");
		Map.put("data", "FORM_TIME");
		Map.put("item", "FORM_ID");
		tableMap.put("D_T1_STATEMENT_DETAIL", Map);

		tableList.add(tableMap);
		return tableList;
	}

	/**
	 * 建立导出业务信息结构
	 * 
	 * @return
	 */
	public static List<Map<String, Map<String, Object>>> buildBusiness() {
		List<Map<String, Map<String, Object>>> tableList = new ArrayList<Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> tableMap = new HashMap<String, Map<String, Object>>();
		Map<String, Object> Map = new HashMap<String, Object>();
		Map = new HashMap<String, Object>();
		Map.put("table", "D_T_FOOD_BILL");
		Map.put("data", "DBUSINESS");
		Map.put("item", "cBill_c");
		tableMap.put("D_T_FOOD_BILLS", Map);

		Map.put("table", "D_T_FOOD_BILL");
		Map.put("data", "DBUSINESS");
		Map.put("item", "cBill_c");
		tableMap.put("D_T_BILL_PAY", Map);

		Map.put("data", "DBUSINESS");
		tableMap.put("D_T_FOOD_BILL", Map);

		tableList.add(tableMap);

		return tableList;
	}

	/**
	 * 建立导出基本表结构
	 * 
	 * @return
	 */
	public static List<Map<String, Map<String, Object>>> buildBranch() {
		List<Map<String, Map<String, Object>>> tableList = new ArrayList<Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> tableMap = new HashMap<String, Map<String, Object>>();
		tableMap.put("D_T2_BRANCH_TYPE", null);
		tableMap.put("D_T1_TEMPLATE_META", null);
		tableMap.put("D_T1_TEMPLATE_ITEM", null);
		tableMap.put("D_T0_OPTION", null);
		tableMap.put("D_T2_ITEM_FOOD_SUIT", null);
		tableMap.put("D_T2_ITEM_PRICE", null);
		tableMap.put("D_T2_PRICE_GROUP", null);
		tableMap.put("D_T2_PRICE_GROUP_BRANCH", null);
		tableMap.put("D_T2_BRANCH_STORAGE", null);
		tableMap.put("D_T2_BOX_TYPE", null);
		tableMap.put("D_T2_BRANCH", null);
		tableMap.put("D_T2_ITEM_META", null);
		tableMap.put("D_T2_ITEM_CATEGORY", null);
		tableMap.put("D_T2_THERAPY", null);
		tableMap.put("D_T2_STORAGE_WORKSHOP", null);
		tableMap.put("D_T2_STORAGE", null);
		tableMap.put("D_T2_WORKSHOP", null);
		tableMap.put("D_T2_SHELF_ITEM", null);
		tableMap.put("D_T2_SUPPLIER_BRANCH_ITEM", null);
		tableMap.put("D_T2_SUPPLY_CYCLE", null);
		tableMap.put("D_T2_PRODUCTION_CYCLE", null);
		tableMap.put("D_T2_DELIVERY_REGION", null);
		tableMap.put("D_T2_DELIVERY_REGION_BRANCH", null);
		tableMap.put("D_T2_DELIVERY_TYPE", null);
		tableMap.put("D_T2_DELIVERY_UNIT", null);
		tableMap.put("D_T2_MAKING_PROCESS", null);
		tableList.add(tableMap);

		return tableList;
	}

	/**
	 * 建立导出系统表结构
	 * 
	 * @return
	 */
	public static List<Map<String, Map<String, Object>>> buildSys() {
		List<Map<String, Map<String, Object>>> tableList = new ArrayList<Map<String, Map<String, Object>>>();
		Map<String, Map<String, Object>> tableMap = new HashMap<String, Map<String, Object>>();
		tableMap.put("D_T0_ROLE", null);
		tableMap.put("D_T0_MENU", null);
		tableMap.put("D_T0_PERMISSION", null);
		tableMap.put("D_T2_USER", null);
		tableMap.put("D_T0_USERTOROLE", null);
		tableMap.put("D_T0_USERTOBRANCH", null);
		tableList.add(tableMap);

		return tableList;
	}
}
