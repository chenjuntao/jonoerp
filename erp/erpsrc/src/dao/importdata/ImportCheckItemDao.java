/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on May 20, 2015 by liyzh
 * Last edited on May 20, 2015 by liyzh
 * 
 * 说明： 保存盘点清单明细，批处理
 */
package dao.importdata;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;

import com.tanry.framework.util.TextUtil;

public class ImportCheckItemDao extends ConnectionPool {

	public List<Map<String, Object>> insertItem(String formId, Map<String, Map<String, Object>> detailMap)
			throws SQLException {
		Connection conn = getConnection();

		setCompanyId(getSessionCom());

		Set<String> idSet = detailMap.keySet();
		String selSql = "SELECT m.ITEM_ID, m.ITEM_NAME, m.ITEM_DIMENSION, m.ITEM_SPECIFICATION, m.CATEGORY_ID FROM "
				+ Com_("D_T2_ITEM_META") + " m WHERE m.ITEM_ID IN ('" + StringUtils.join(idSet, "','") + "') ";
		String insertSql = "INSERT INTO " + Com_("D_T1_CHECK_DETAIL")
				+ " (FORM_ID, ITEM_ID, ITEM_NAME, ITEM_DIMENSION, ITEM_SPECIFICATION, ITEM_CATEGORY, CHECK_COUNT)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?)";
		PreparedStatement insPs = conn.prepareStatement(insertSql);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(selSql);
		Set<String> existIdSet = new HashSet<String>();
		while (rs.next()) {
			String itemId = rs.getString("ITEM_ID");
			String itemName = rs.getString("ITEM_NAME");
			String itemDimension = rs.getString("ITEM_DIMENSION");
			String itemSpecification = rs.getString("ITEM_SPECIFICATION");
			String categoryId = rs.getString("CATEGORY_ID");
			insPs.setString(1, formId);
			insPs.setString(2, itemId);
			insPs.setString(3, itemName);
			insPs.setString(4, itemDimension);
			insPs.setString(5, itemSpecification);
			insPs.setString(6, categoryId);
			insPs.setString(7, (String) detailMap.get(itemId).get("checkCount"));

			insPs.addBatch();

			existIdSet.add(itemId);
		}
		rs.close();
		st.close();

		insPs.executeBatch();
		insPs.clearBatch();
		insPs.close();

		releaseConnection(conn);

		// 找出哪些物料在系统中不存在，返回到前台给出提示信息
		boolean result = idSet.removeAll(existIdSet);
		if (result) {
			List<Map<String, Object>> missingLst = new ArrayList<Map<String, Object>>();
			for (String itemId : idSet) {
				if (!TextUtil.isEmpty(itemId)) {// 去掉合计行
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("itemId", itemId);
					item.put("rownumber", detailMap.get(itemId).get("rownumber"));
					missingLst.add(item);
				}
			}
			return missingLst;
		}
		return null;
	}
}
