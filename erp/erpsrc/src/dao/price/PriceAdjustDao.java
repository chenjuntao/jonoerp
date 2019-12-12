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
 * 说明： 调价单相关
 */
package dao.price;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import logic.NoConnection;
import logic.form.StorageInOutBean;
import logic.pool.ConnectionPool;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.StorageBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.Therapy;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.constant.PriceType;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class PriceAdjustDao extends ConnectionPool {

	private JdbcTemplate jdbcTemplate;
	private StorageInOutBean storageInOutBean;
	private BranchStorageBean branchStorageBean;
	private StorageBean storageBean;
	private BranchBean branchBean;

	/**
	 * 更新配方成本价
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void computePrice(List<Therapy> therapys) throws NoPrivilegeException, SQLException, NoConnection {
		// 先清理老数据
		String deleteSql = "DELETE FROM " + Com_("D_T2_ITEM_PRICE") + " WHERE PRICE_TYPE ='PURCHASE' AND ITEM_ID = ?";
		// 保存新数据
		String insertSql = "INSERT INTO " + Com_("D_T2_ITEM_PRICE")
				+ " (ITEM_ID, ITEM_PRICE, PRICE_TYPE ,IS_CURRENT) VALUES(?, ?, ?,?)";
		// 保存供应商价
		// String supplierSql = "INSERT INTO " + Com_("D_T2_ITEM_PRICE") +
		// " (ITEM_ID, ITEM_PRICE, PRICE_TYPE ,IS_CURRENT,SUPPLIER_ID) VALUES(?, ?, ?,?,?)";
		List<Object[]> intchArgs = new ArrayList<Object[]>();
		List<Object[]> delArgs = new ArrayList<Object[]>();
		List<Object[]> supArgs = new ArrayList<Object[]>();
		for (Therapy item : therapys) {
			Object[] delArg = new Object[] { item.getTherapyId() };
			Object[] args = new Object[] { item.getTherapyId(), item.getPurchasePrice(), "PURCHASE", 1 };
			Object[] suparg = new Object[] { item.getTherapyId(), item.getPurchasePrice(), "SUPPLIER", 1, "FOO" };
			delArgs.add(delArg);
			intchArgs.add(args);
			supArgs.add(suparg);
		}
		jdbcTemplate.batchUpdate(deleteSql, delArgs);
		jdbcTemplate.batchUpdate(insertSql, intchArgs);
		// jdbcTemplate.batchUpdate(supplierSql, supArgs);
	}

	// 计算仓库明细账数据
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void computeCount() throws NoPrivilegeException, SQLException, NoConnection {
		System.out.println("开始！");
		Connection conn = getConnection();

		setCompanyId(getSessionCom());

		Statement st = conn.createStatement();
		ResultSet rs = null;
		// 先查询数据
		String selSql = "SELECT * FROM D_T0_STORAGE_IN_OUT i WHERE i.FORM_ID IN ( SELECT DISTINCT r.FORM_REF_ID FROM D_T1_PICKING_REF r INNER JOIN D_T1_PICKING_HEADER h ON r.FORM_ID = h.FORM_ID WHERE h.AUDIT_TIME = to_date('2016-07-21','YYYY-MM-DD')) AND i.REASON = 'PSCK' ORDER BY i.ITEM_ID,i.MY_TIMESTAMP";
		String updateSql = "UPDATE D_T0_STORAGE_IN_OUT i SET i.ORGI_COUNT = ?,i.RESULT_COUNT = ? WHERE i.FORM_ID = ? AND i.ITEM_ID = ? ";
		PreparedStatement updatePs = conn.prepareStatement(updateSql);
		rs = st.executeQuery(selSql);
		String oldItemId = "";
		Double resultCount = 0.0;
		while (rs.next()) {
			Double orgiCount = rs.getDouble("ORGI_COUNT");
			Double outCount = rs.getDouble("ITEM_COUNT_OUT");
			String itemId = rs.getString("ITEM_ID");
			if (!itemId.equals(oldItemId)) {
				oldItemId = itemId;
				resultCount = orgiCount;
			}
			String formId = rs.getString("FORM_ID");

			updatePs.setDouble(1, resultCount);
			resultCount -= outCount;
			updatePs.setDouble(2, resultCount);
			updatePs.setString(3, formId);
			updatePs.setString(4, itemId);

			updatePs.addBatch();
		}

		rs.close();
		st.close();

		updatePs.executeBatch();
		updatePs.clearBatch();
		updatePs.close();
		releaseConnection(conn);
		System.out.println("结束！");
	}

	// 仓库明细账数据恢复
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void computeBack(String startDate, String endDate, String branchId, String branchType)
			throws NoPrivilegeException, SQLException, NoConnection {
		System.out.println("开始！");
		// 先处理掉出入库数据
		storageInOutBean.deleteEntity(startDate, endDate, branchId);
		Connection conn = getConnection();

		setCompanyId(getSessionCom());
		// 先查询数据
		List<Map> detailLst;
		if (BranchType.RESTAURANT.equals(branchType)) {
			detailLst = storageInOutBean.queryItem(startDate, endDate, branchId);
		} else if (BranchType.LOGISTICSCENTER.equals(branchType)) {
			detailLst = storageInOutBean.queryLcItem(startDate, endDate, branchId);
		} else {
			detailLst = storageInOutBean.queryCFItem(startDate, endDate, branchId);
		}
		String storeId = branchStorageBean.queryMainStore(branchId).getStorageId();
		// 生成仓库明细账
		String insertSql = "INSERT INTO "
				+ Com_("D_T0_STORAGE_IN_OUT")
				+ " (BRANCH_ID, STORAGE_ID,BUSINESS_DATE, OPERATION_TIME, ITEM_ID, ITEM_UNIT_PRICE, ORGI_COUNT, ITEM_COUNT_IN, ITEM_COUNT_OUT, RESULT_COUNT, FORM_ID, REASON,MY_TIMESTAMP) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement insertPs = conn.prepareStatement(insertSql);
		String oldItemId = "";
		Double orgiCount = 0.0;
		for (int i = 0; i < detailLst.size(); i++) {
			Map detail = detailLst.get(i);
			insertPs.setObject(1, detail.get("branchId"));
			insertPs.setObject(2, storeId);
			insertPs.setDate(3, SqlDateUtil.getSqlDate((Date) detail.get("restaurantDate")));
			insertPs.setDate(4, SqlDateUtil.getSqlDate((Date) detail.get("operationTime")));
			String itemId = detail.get("itemId").toString();
			if (!itemId.equals(oldItemId)) {
				oldItemId = itemId;
				orgiCount = storageBean.getItemCount(itemId, storeId);
			}
			insertPs.setObject(5, itemId);
			insertPs.setObject(6, detail.get("itemUnitPrice"));
			insertPs.setObject(7, orgiCount);
			String typeFlag = detail.get("typeFlag").toString();
			Double itemCount = (Double) detail.get("itemCount");
			Double stoCount = 0.0;
			if (typeFlag.equals("IN")) {
				insertPs.setObject(8, itemCount);
				insertPs.setObject(9, 0.0);
				try {
					stoCount = itemCount + orgiCount;
				} catch (Exception e) {
					System.out.println(itemId);
				}
				insertPs.setObject(10, stoCount);
			} else {
				insertPs.setObject(8, 0.0);
				insertPs.setObject(9, itemCount);
				try {
					stoCount = orgiCount - itemCount;
				} catch (Exception e) {
					System.out.println(itemId);
				}
				insertPs.setObject(10, stoCount);
			}
			orgiCount = stoCount;
			storageBean.updateItemCount(itemId, storeId, 0.0, stoCount);
			insertPs.setObject(11, detail.get("formId"));
			insertPs.setObject(12, detail.get("reason"));
			insertPs.setObject(13, i);
			insertPs.addBatch();
		}

		insertPs.executeBatch();
		insertPs.clearBatch();
		insertPs.close();
		releaseConnection(conn);
		System.out.println("结束！");
	}

	// 仓库明细账数据验证
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String computeStorage() throws NoPrivilegeException, SQLException, NoConnection {
		// String busLst[] = { "", "" };
		// branchBean.setCompanyId("db_bvsh");
		List<Map> branchLst = branchBean.listShopByType(BranchTypeEnum.R_C_L);
		String startDate = DateTimeUtil.getDateStr(DateTimeUtil.addDays(-2));
		String content = "";
		for (Map branch : branchLst) {
			String branchId = branch.get("code").toString();
			// String branchId = "F00";
			String branchType = branchBean.GetBranchById(branchId).getBranchType();
			// 先查询数据
			List<Map> detailLst;
			if (BranchType.RESTAURANT.equals(branchType)) {
				detailLst = storageInOutBean.computeItem(startDate, branchId);
			} else if (BranchType.LOGISTICSCENTER.equals(branchType)) {
				detailLst = storageInOutBean.computeLcItem(startDate, branchId);
			} else {
				detailLst = storageInOutBean.computeCFItem(startDate, branchId);
			}
			String content1 = "";
			for (Map detail : detailLst) {
				content1 = content1 + "[" + detail.get("itemId") + "]" + detail.get("itemName") + "应该为"
						+ detail.get("itemCount") + "实际为" + detail.get("storageCount") + ",";
			}
			if (!TextUtil.isEmpty(content1)) {
				content += branchId + "门店,单据数据与进出库数据有差异的详情：" + content1;
			}

		}
		if (TextUtil.isEmpty(content)) {
			content = "一切正常！";
		}
		return content;

	}

	// 根据ItemId查找当天的出入库情况
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public List<Map> queryItem(String startDate, String itemId, String branchId) throws NoPrivilegeException,
			SQLException, NoConnection {
		String branchType = branchBean.GetBranchById(branchId).getBranchType();
		List<Map> detailLst;
		if (BranchType.RESTAURANT.equals(branchType)) {
			detailLst = storageInOutBean.queryByItemId(startDate, itemId, branchId);
		} else if (BranchType.LOGISTICSCENTER.equals(branchType)) {
			detailLst = storageInOutBean.queryLcItemId(startDate, itemId, branchId);
		} else {
			detailLst = storageInOutBean.queryCFItemId(startDate, itemId, branchId);
		}
		return detailLst;
	}

	// 商品调价单数据导入
	public List<Map<String, Object>> getItem(String priceType, Map<String, Map<String, Object>> detailMap)
			throws SQLException {
		Connection conn = getConnection();

		setCompanyId(getSessionCom());

		Set<String> idSet = detailMap.keySet();

		Statement st = conn.createStatement();
		ResultSet rs = null;
		List<Map<String, Object>> existLst = new ArrayList<Map<String, Object>>();

		String selSql = null;

		// 售卖价不需要考虑
		if (PriceType.SALE.equals(priceType)) {
			selSql = "SELECT m.*, p.ITEM_PRICE, 0 AS item_packager FROM " + Com_("D_T2_ITEM_META") + " m LEFT JOIN "
					+ Com_("D_T2_ITEM_PRICE") + " p ON p.ITEM_ID = m.ITEM_ID AND p.IS_CURRENT =1 AND p.PRICE_TYPE ='"
					+ priceType + "' " + " WHERE (1,m.ITEM_ID) IN ((1,'"
					+ StringUtils.join(idSet, ",").replaceAll(",", "'),(1,'") + "')) ";

		} else {
			selSql = "SELECT m.*, p.ITEM_PRICE, u.DELIVERY_FACTOR AS item_packager FROM " + Com_("D_T2_ITEM_META")
					+ " m LEFT JOIN " + Com_("D_T2_ITEM_PRICE")
					+ " p ON p.ITEM_ID = m.ITEM_ID AND p.IS_CURRENT =1 AND p.PRICE_TYPE ='" + priceType + "' JOIN "
					+ Com_("D_T2_DELIVERY_UNIT") + " u ON u.ITEM_ID = m.ITEM_ID" + " WHERE (1,m.ITEM_ID) IN ((1,'"
					+ StringUtils.join(idSet, ",").replaceAll(",", "'),(1,'") + "')) ";
		}

		rs = st.executeQuery(selSql);

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

			String itemPackager = rs.getString("ITEM_PACKAGER");
			itemPackager = itemPackager == null ? "" : itemPackager;

			String oldPrice = rs.getString("ITEM_PRICE");
			oldPrice = oldPrice == null ? "0" : oldPrice;
			itemMap.put("itemId", itemId);
			itemMap.put("itemName", itemName);
			itemMap.put("itemDimension", itemDimension);
			itemMap.put("itemSpecification", itemSpecification);
			itemMap.put("itemPackager", itemPackager);
			itemMap.put("oldPrice", oldPrice);
			itemMap.put("newPrice", detailMap.get(itemId).get("newPrice"));
			existLst.add(itemMap);
		}

		rs.close();
		st.close();
		releaseConnection(conn);

		return existLst;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

}
