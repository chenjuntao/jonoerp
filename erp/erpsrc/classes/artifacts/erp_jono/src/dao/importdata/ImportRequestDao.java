/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on May 20, 2015 by charles
 * Last edited on May 20, 2015 by charles
 * 
 * 说明： 导入要货单相关
 */

package dao.importdata;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.NoConnection;
import logic.pool.ConnectionPool;

import org.apache.commons.lang.StringUtils;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * @author charles
 * 
 */
public class ImportRequestDao extends ConnectionPool {
	public List<Map<String, Object>> getExistItem(Map<String, Map<String, Object>> detailMap) throws SQLException,
			NoPrivilegeException, NoConnection {
		Connection conn = getConnection();

		setCompanyId(getSessionCom());

		Set<String> idSet = detailMap.keySet();
		String selSql = "SELECT m.ITEM_ID, m.ITEM_NAME, m.ITEM_DIMENSION, m.ITEM_SPECIFICATION, m.CATEGORY_ID, p.ITEM_PRICE FROM "
				+ Com_("D_T2_ITEM_META")
				+ " m LEFT JOIN "
				+ Com_("D_T2_ITEM_PRICE")
				+ " p ON p.ITEM_ID = m.ITEM_ID AND p.PRICE_TYPE = 'BENCHMARK'"
				+ " WHERE m.ITEM_ID IN ('"
				+ StringUtils.join(idSet, "','") + "') ";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(selSql);

		List<Map<String, Object>> existLst = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> itemMap = new HashMap<String, Object>();

			String itemId = rs.getString("ITEM_ID");
			String itemName = rs.getString("ITEM_NAME");
			itemName = itemName == null ? "" : itemName;

			String itemDimension = rs.getString("ITEM_DIMENSION");
			itemDimension = itemDimension == null ? "" : itemDimension;

			String itemSpecification = rs.getString("ITEM_SPECIFICATION");
			itemSpecification = itemSpecification == null ? "" : itemSpecification;

			String categoryId = rs.getString("CATEGORY_ID");
			categoryId = categoryId == null ? "" : categoryId;

			itemMap.put("itemId", itemId);
			itemMap.put("itemName", itemName);
			itemMap.put("itemDimension", itemDimension);
			itemMap.put("itemSpecification", itemSpecification);
			itemMap.put("itemCategory", categoryId);
			itemMap.put("orderCount", detailMap.get(itemId).get("orderCount"));
			double itemPrice = rs.getDouble("ITEM_PRICE");
			itemMap.put("itemUnitPrice", itemPrice);
			existLst.add(itemMap);
		}
		rs.close();
		st.close();

		releaseConnection(conn);

		return existLst;
	}

	public List<Map<String, Object>> getExistPurchase(Map<String, Map<String, Object>> detailMap) throws SQLException,
			NoPrivilegeException, NoConnection {
		Connection conn = getConnection();

		setCompanyId(getSessionCom());

		Set<String> idSet = detailMap.keySet();
		String selSql = "SELECT m.ITEM_ID, m.ITEM_NAME, m.ITEM_DIMENSION, m.ITEM_SPECIFICATION, m.CATEGORY_ID, p.ITEM_PRICE AS ITEM_UNIT_PRICE, p2.ITEM_PRICE AS RECEIVE_PRICE, b.BRANCH_NAME AS PROVIDER,s.SUPPLIER_ID providerId FROM "
				+ Com_("D_T2_ITEM_META")
				+ " m INNER JOIN "
				+ Com_("D_T2_SUPPLIER_BRANCH_ITEM")
				+ " s ON s.BRANCH_ID = 'L00' AND s.ITEM_ID = m.ITEM_ID AND s.PRIORITY = 0 LEFT JOIN "
				+ Com_("D_T2_BRANCH")
				+ " b ON b.BRANCH_ID = s.SUPPLIER_ID LEFT JOIN "
				+ Com_("D_T2_ITEM_PRICE")
				+ " p ON p.ITEM_ID = m.ITEM_ID AND p.PRICE_TYPE = 'BENCHMARK' AND p.IS_CURRENT = 1 LEFT JOIN "
				+ Com_("D_T2_ITEM_PRICE")
				+ " p2 ON p2.ITEM_ID = m.ITEM_ID AND p2.PRICE_TYPE = 'PURCHASE' AND p2.IS_CURRENT = 1 WHERE m.ITEM_ID IN  ('"
				+ StringUtils.join(idSet, "','") + "') ";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(selSql);

		List<Map<String, Object>> existLst = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> itemMap = new HashMap<String, Object>();

			String itemId = rs.getString("ITEM_ID");
			String itemName = rs.getString("ITEM_NAME");
			itemName = itemName == null ? "" : itemName;

			String itemDimension = rs.getString("ITEM_DIMENSION");
			itemDimension = itemDimension == null ? "" : itemDimension;

			String itemSpecification = rs.getString("ITEM_SPECIFICATION");
			itemSpecification = itemSpecification == null ? "" : itemSpecification;

			String categoryId = rs.getString("CATEGORY_ID");
			categoryId = categoryId == null ? "" : categoryId;

			String provider = rs.getString("provider");
			provider = provider == null ? "" : provider;

			String providerId = rs.getString("providerId");
			providerId = providerId == null ? "" : providerId;

			itemMap.put("itemId", itemId);
			itemMap.put("itemName", itemName);
			itemMap.put("itemDimension", itemDimension);
			itemMap.put("itemSpecification", itemSpecification);
			itemMap.put("itemCategory", categoryId);
			itemMap.put("provider", provider);
			itemMap.put("providerId", providerId);
			itemMap.put("itemCount", detailMap.get(itemId).get("itemCount"));
			// double itemPrice =
			// itemPriceBean.getItemPrice(itemId,"BENCHMARK");
			double itemPrice = rs.getDouble("ITEM_UNIT_PRICE");
			itemMap.put("itemUnitPrice", itemPrice);

			double receivePrice = rs.getDouble("RECEIVE_PRICE");
			itemMap.put("receivePrice", receivePrice);
			existLst.add(itemMap);
		}
		rs.close();
		st.close();

		releaseConnection(conn);

		return existLst;
	}

	public List<Map<String, Object>> getTemplateItem(Map<String, Map<String, Object>> detailMap) throws SQLException,
			NoPrivilegeException, NoConnection {
		Connection conn = getConnection();

		setCompanyId(getSessionCom());

		Set<String> idSet = detailMap.keySet();
		String selSql = "SELECT m.ITEM_ID itemId, m.ITEM_NAME itemName, m.ITEM_DIMENSION itemDimension, m.ITEM_SPECIFICATION itemSpecification, m.CATEGORY_ID itemCategory, s.SHELF_NAME shelfName FROM "
				+ Com_("D_T2_ITEM_META")
				+ " m LEFT JOIN "
				+ Com_("D_T2_SHELF_ITEM")
				+ " si ON m.ITEM_ID = si.ITEM_ID AND si.PRIORITY = 0 LEFT JOIN "
				+ Com_("D_T2_SHELF")
				+ " s ON si.SHELF_ID = s.SHELF_ID WHERE m.ITEM_ID IN  ('" + StringUtils.join(idSet, "','") + "') ";

		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(selSql);

		List<Map<String, Object>> existLst = new ArrayList<Map<String, Object>>();
		while (rs.next()) {
			Map<String, Object> itemMap = new HashMap<String, Object>();
			String itemId = rs.getString("itemId");

			String itemName = rs.getString("itemName");
			itemName = itemName == null ? "" : itemName;

			String itemDimension = rs.getString("itemDimension");
			itemDimension = itemDimension == null ? "" : itemDimension;

			String itemSpecification = rs.getString("itemSpecification");
			itemSpecification = itemSpecification == null ? "" : itemSpecification;

			String categoryId = rs.getString("itemCategory");
			categoryId = categoryId == null ? "" : categoryId;

			String shelfName = rs.getString("shelfName");
			shelfName = categoryId == null ? "" : shelfName;

			itemMap.put("itemId", itemId);
			itemMap.put("itemName", itemName);
			itemMap.put("itemDimension", itemDimension);
			itemMap.put("itemSpecification", itemSpecification);
			itemMap.put("itemCategory", categoryId);
			itemMap.put("shelfName", shelfName);
			existLst.add(itemMap);
		}
		rs.close();
		st.close();

		releaseConnection(conn);

		return existLst;
	}

}
