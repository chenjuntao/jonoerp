/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on July 16, 2014 by wp
 * Last edited on Aug 24, 2015 by liyzh
 * 
 * 说明： 查询并显示餐厅出品信息，每个餐厅的价格与品牌相关
 */
package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.restaurant.ProductQueryBean;
import logic.store.ItemCategoryBean;
import logic.store.PgroupBranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemCategory;
import pojo.store.PgroupBranch;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.vo.ItemVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.PriceType;
import com.tanry.framework.util.constant.SysOption;

public class ShopDishAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemCategoryBean itemCategoryBean;
	private ProductQueryBean productQueryBean;
	private PgroupBranchBean pgroupBranchBean;

	private String categoryId;
	private String itemName;

	/**
	 * 从餐厅出品信息数据表中读取信息
	 */
	public void listItem() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();
		PgroupBranch pgroupBranch = pgroupBranchBean.queryBenchmark(branchId);
		String priceType = PriceType.SALE;
		if (pgroupBranch != null) {
			priceType = pgroupBranch.getPriceGroupId();
		}
		List<ItemVO> itemLst = productQueryBean.query(branchId, priceType, itemName, categoryId);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (ItemVO item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);

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

	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		List<ItemCategory> categoryLst = itemCategoryBean.queryTree("PRODUCT");
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "出品分类");
		arr.add(root);
		for (ItemCategory category : categoryLst) {
			JSONObject item = new JSONObject();
			item.put("id", category.getCategoryId().trim());
			item.put("name", category.getCategoryName());
			if (category.getParentId().equals("*")) {
				item.put("parent", "root");
			} else {
				item.put("parent", category.getParentId().trim());
			}
			arr.add(item);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

	public void setProductQueryBean(ProductQueryBean productQueryBean) {
		this.productQueryBean = productQueryBean;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	/**
	 * 获取套餐小类的编码
	 * 
	 * @return
	 */
	public String getSuitCateId() {
		return SysOption.SUIT_CATE_ID;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}

}
