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
package action.restaurant.goodsbill;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.restaurant.ArrivePeriodBean;
import logic.module.restaurant.SelMaterialBean;
import logic.store.BranchBean;
import logic.store.SupplierBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Branch;
import pojo.store.ItemMeta;
import vo.restaurant.RequestItemVO;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;
import com.tanry.framework.util.constant.PriceType;

public class SelMaterialAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private ArrivePeriodBean arrivePeriodBean;
	private SupplierBean supplierBean;
	private SelMaterialBean selMaterialBean;
	/**
	 * 门店分组
	 */
	private String regionId;
	private String deliveryType;
	private String itemType;
	private String itemName;
	private String branchType;
	private String transferType;

	/**
	 * 根据区域和配送方式查询匹配的原材料
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<ItemMeta> itemLst = arrivePeriodBean.queryItem(regionId, deliveryType);

		int rownumber = 1;
		for (ItemMeta item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);

			String supplier = supplierBean.getSupplier(item.getItemId());
			json.put("supplier", supplier);
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
	 * 餐厅要货单选择原料、半成品
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(itemType)) {
			itemType = ItemType.R_S;
		}
		Branch branch = branchBean.GetBranchById(getLoginBranchId());
		String priceType = branch.getPriceType();
		if (TextUtil.isEmpty(priceType)) {
			priceType = PriceType.BENCHMARK;
		}
		List<RequestItemVO> itemLst = selMaterialBean.queryItem(itemType, itemName, priceType);

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

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setArrivePeriodBean(ArrivePeriodBean arrivePeriodBean) {
		this.arrivePeriodBean = arrivePeriodBean;
	}

	public void setSupplierBean(SupplierBean supplierBean) {
		this.supplierBean = supplierBean;
	}

	public void setSelMaterialBean(SelMaterialBean selMaterialBean) {
		this.selMaterialBean = selMaterialBean;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
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

}
