/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 30, 2015 by liyzh
 * Last edited on Mar 30, 2015 by liyzh
 * 
 * 说明： 初始化所有的配送方式数据
 */
package com.tanry.business.common.init;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckToBranch {
	public static void main(String[] args) {
		List<Map<String, Object>> checkLst = new ArrayList<Map<String, Object>>();

		// 对餐厅门店进行关联查询,用户门店表，部门仓库表，配送区域门店，价格组门店表
		// SELECT * FROM D_T2_BRANCH b WHERE b.BRANCH_TYPE ='RESTAURANT' AND
		// b.BRANCH_ID NOT IN ( SELECT u.BRANCH_ID FROM D_T0_USERTOBRANCH u)
		String branchs = "D_T0_USERTOBRANCH, D_T2_DELIVERY_REGION_BRANCH, D_T2_BRANCH_STORAGE,D_T2_PRICE_GROUP_BRANCH";
		Map<String, Object> checkMap1 = new HashMap<String, Object>();
		checkMap1.put("mainTable", "D_T2_BRANCH");// 主表
		checkMap1.put("tableItem", "BRANCH_ID");// 验证餐厅关联
		checkMap1.put("branchs", branchs);// 从表
		// 附加条件，没有则为1=1
		checkMap1.put("condition", "b.BRANCH_TYPE ='RESTAURANT'");

		checkLst.add(checkMap1);

		// 对原料进行关联查询，配送模式表，配送单位表，库位表，仓库存储信息表，供应商-门店-商品,其他基本信息
		Map<String, Object> checkMap2 = new HashMap<String, Object>();
		branchs = "D_T2_DELIVERY_TYPE,D_T2_DELIVERY_UNIT,D_T2_SHELF_ITEM,D_T2_STORAGE,D_T2_SUPPLIER_BRANCH_ITEM,D_T2_DELIVERY_UNIT";
		checkMap2.put("mainTable", "D_T2_ITEM_META");// 主表
		checkMap2.put("tableItem", "ITEM_ID");// 验证餐厅关联
		checkMap2.put("branchs", branchs);// 从表
		checkMap2.put("condition", " b.ITEM_TYPE NOT IN ('PRODUCT') ");// 附加条件，没有则为1=1
		checkLst.add(checkMap2);

		// 对半成品进行关联表查询库位,与物料关系表，半成品生产周期表，供应商与门店以及商品的对应关系
		Map<String, Object> checkMap3 = new HashMap<String, Object>();
		branchs = "D_T2_SHELF_ITEM,D_T2_PRODUCTION_CYCLE,D_T2_SUPPLIER_BRANCH_ITEM";
		checkMap3.put("mainTable", "D_T2_ITEM_META");// 主表
		checkMap3.put("tableItem", "ITEM_ID");// 验证餐厅关联
		checkMap3.put("branchs", branchs);// 从表
		checkMap3.put("condition", " b.ITEM_TYPE IN ('SEMIS') ");// 附加条件，没有则为1=1
		checkLst.add(checkMap3);

		// 物料价格必须在价格组中存在
		// Map<String, Object> checkMap4 = new HashMap<String, Object>();
		// branchs = "D_T2_PRICE_GROUP";
		// checkMap4.put("mainTable", "D_T2_ITEM_PRICE");
		// checkMap4.put("tableItem", "PRICE_TYPE");
		// checkMap4.put("branchs", branchs);
		// checkMap4.put("condition", "1=1");
		// checkLst.add(checkMap4);

		try {
			Connection conn = DBUtil.getConnection();
			String[] tableLst;
			String tableItem;
			String condition;
			String mainTable;
			for (Map<String, Object> check : checkLst) {
				mainTable = check.get("mainTable").toString();
				String item = check.get("branchs").toString();
				tableLst = item.split(",");
				tableItem = check.get("tableItem").toString();
				condition = check.get("condition").toString();
				for (String tableName : tableLst) {
					chackBranch(conn, mainTable, tableName, tableItem, condition);
				}

			}

			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历原材料
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> chackBranch(Connection conn, String mainTable, String tableName,
			String tableItem, String condition) throws SQLException {
		String sql = "SELECT * FROM " + mainTable + " b WHERE " + condition + " AND b." + tableItem
				+ " NOT IN ( SELECT u." + tableItem + " FROM " + tableName + " u)";
		System.out.println(sql);
		Statement st = conn.createStatement();

		ResultSet rs = st.executeQuery(sql);

		while (rs.next()) {
			String item = rs.getString(tableItem);
			System.out.println("[" + item + "] 在表" + tableName + "中缺失数据");
		}
		rs.close();
		st.close();
		return null;
	}
}
