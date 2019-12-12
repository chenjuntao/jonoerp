/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 21, 2014 by liyzh
 * Last edited on Aug 21, 2014 by liyzh
 * 
 * 说明： 自选原料
 */
package action.restaurant.reportdamage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.restaurant.SelMaterialBean;
import logic.store.PgroupBranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.PgroupBranch;
import vo.restaurant.RequestItemVO;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.PriceType;

public class SelMaterialAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private SelMaterialBean selMaterialBean;
	private PgroupBranchBean pgroupBranchBean;

	private String itemName;
	private String storageId;

	/**
	 * 报损单根据库存自选原料（包括半成品），获取标准价
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();
		PgroupBranch pgroupBranch = pgroupBranchBean.queryBenchmark(branchId);
		String priceType = PriceType.BENCHMARK;
		if (pgroupBranch != null) {
			priceType = pgroupBranch.getPriceGroupId();
		}
		List<RequestItemVO> itemLst = selMaterialBean.queryItemInventory(itemName, storageId, priceType);

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

	/**
	 * 报损单根据库存自选原料（不包括半成品），获取标准价
	 */
	public void queryInventory() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();
		PgroupBranch pgroupBranch = pgroupBranchBean.queryBenchmark(branchId);
		String priceType = PriceType.BENCHMARK;
		if (pgroupBranch != null) {
			priceType = pgroupBranch.getPriceGroupId();
		}
		List<RequestItemVO> itemLst = selMaterialBean.queryInventory(itemName, storageId, priceType);

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

	public void setSelMaterialBean(SelMaterialBean selMaterialBean) {
		this.selMaterialBean = selMaterialBean;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}

}
