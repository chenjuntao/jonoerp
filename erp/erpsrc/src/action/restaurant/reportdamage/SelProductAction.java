/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 30, 2015 by liyzh
 * Last edited on Apr 30, 2015 by liyzh
 * 
 * 说明：生成报损单时选择出品（餐厅）或半成品（央厂）
 */
package action.restaurant.reportdamage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.restaurant.SelProductBean;
import logic.store.PgroupBranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.PgroupBranch;
import vo.restaurant.RequestItemVO;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.constant.PriceType;

public class SelProductAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private SelProductBean selProductBean;
	private PgroupBranchBean pgroupBranchBean;

	private String itemName;
	/**
	 * 部门类型（餐厅或央厂）
	 */
	private String branchType;

	/**
	 * 报损单根据库存自选原料（包括半成品），获取成本价
	 */
	public void queryProduct() throws NoPrivilegeException, SQLException, NoConnection {
		String itemType = ItemType.PRODUCT;
		if (BranchType.CENTRALFACTORY.equals(branchType)) {
			itemType = ItemType.SEMIS;
		}
		String branchId = getLoginBranchId();
		PgroupBranch pgroupBranch = pgroupBranchBean.queryBenchmark(branchId);
		String priceType = PriceType.BENCHMARK;
		if (pgroupBranch != null) {
			priceType = pgroupBranch.getPriceGroupId();
		}
		List<RequestItemVO> itemLst = selProductBean.queryProduct(itemName, itemType, priceType);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (RequestItemVO item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			Double itemPrice = item.getItemPrice();
			if (itemPrice == null) {
				itemPrice = 0.0;
			}
			json.put("itemUnitPrice", itemPrice);
			json.put("categoryId", item.getItemCategory());
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setSelProductBean(SelProductBean selProductBean) {
		this.selProductBean = selProductBean;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}

}
