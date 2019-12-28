/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on July 16, 2014 by wp
 * Last edited on Aug 25, 2014 by liyzh
 * 
 * 说明： 查询并显示特定出品的成本卡信息
 */
package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.ItemMetaBean;
import logic.store.TherapyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import pojo.store.ItemMeta;
import pojo.store.Therapy;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DoubleJsonValueProcessor;

public class ShopCostCardAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private ItemMetaBean itemMetaBean;
	private TherapyBean therapyBean;

	private ItemMeta food;

	private String foodId;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		food = itemMetaBean.GetItemById(foodId);
		return SUCCESS;
	}

	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		// 成本卡暂时只支持两级树

		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", food.getItemId());
		root.put("name", "[" + food.getItemId() + "]" + food.getItemName());
		arr.add(root);

		therapyBean.queryTree(food.getItemId(), arr);

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取成本卡明细信息
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<Therapy> itemLst = therapyBean.GetTherapysById(foodId, hasSum);

		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.lang.Double.class, new DoubleJsonValueProcessor());
		JSONArray arr = new JSONArray();

		int size = itemLst.size();
		int rownumber = 1;
		for (Therapy therapy : itemLst) {
			JSONObject json = JSONObject.fromObject(therapy, config);
			if (rownumber == size) {
				json.put("rownumber", "合计");
			} else {
				json.put("rownumber", rownumber);
			}
			json.put("itemCount1", therapy.getItemCount());
			json.put("itemUsageRate1", therapy.getItemUsageRate());
			rownumber++;
			arr.add(json);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public ItemMeta getFood() {
		return food;
	}

	public void setFood(ItemMeta food) {
		this.food = food;
	}
}
