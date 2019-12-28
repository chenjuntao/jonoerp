/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on July 15, 2014 by liyzh
 * Last edited on Sep 1, 2015 by liyzh
 * 
 * 说明： 查询并显示餐厅原材料信息
 */
package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.restaurant.ItemQueryBean;
import logic.store.ItemCategoryBean;
import logic.store.ItemMetaBean;
import logic.store.PgroupBranchBean;
import logic.store.StorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemCategory;
import pojo.store.ItemMeta;
import pojo.store.PgroupBranch;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.PriceType;

public class ShopSourceAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemCategoryBean itemCategoryBean;
	private ItemMetaBean itemMetaBean;
	private ItemQueryBean itemQueryBean;
	private StorageBean storageBean;
	private PgroupBranchBean pgroupBranchBean;

	private String categoryId;
	private String storageId;

	private String jsonData;

	private String shelf;

	private String itemId;

	private String branchType;
	private String itemName;
	private String itemType;

	/**
	 * 餐厅物料信息查询，直营店查看标准价，加盟店查看加盟价
	 */
	public void listItemMeta() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = getLoginBranchId();
		PgroupBranch pgroupBranch = pgroupBranchBean.queryBenchmark(branchId);
		String priceType = PriceType.BENCHMARK;
		if (pgroupBranch != null) {
			priceType = pgroupBranch.getPriceGroupId();
		}
		List<Map> itemLst = null;
		if (!TextUtil.isEmpty(categoryId)) {
			itemLst = itemQueryBean.query(categoryId, priceType);
		} else {
			itemLst = itemQueryBean.queryByName(itemName, priceType);
		}
		int rownumber = 1;
		for (Map map : itemLst) {
			map.put("rownumber", rownumber);
			rownumber++;
		}

		JSONArray jsonArray = JSONArray.fromObject(itemLst);
		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据多个类别查询原材料及半成品信息
	 * 
	 * @author liyzh
	 */
	public void listMaterial() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray idArr = JSONArray.fromObject(jsonData);
		JSONArray jsonArray = new JSONArray();

		int rownumber = 1;
		for (Object obj : idArr) {
			List<ItemMeta> itemMetaBeans = itemMetaBean.queryByCategory((String) obj);
			for (ItemMeta item : itemMetaBeans) {
				jsonArray.add(formItem(item, rownumber));
				rownumber++;
			}
		}
		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据多个ID查询原材料及半成品信息
	 * 
	 * @author cjt
	 */
	public void listItemByIds() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray idArr = JSONArray.fromObject(jsonData);
		JSONArray jsonArray = new JSONArray();

		int rownumber = 1;
		for (Object obj : idArr) {
			ItemMeta item = itemMetaBean.GetItemById((String) obj);
			jsonArray.add(formItem(item, rownumber));
			rownumber++;
		}
		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateShelf() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "ok");

		storageBean.updateShelf(shelf, storageId, itemId);

		try {
			this.outJS(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把每一个原材料对象转换成JSON对象
	 * 
	 * @param item
	 * @param rownumber
	 * @return
	 */
	private JSONObject formItem(ItemMeta item, int rownumber) throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject json = new JSONObject();
		json.put("rownumber", rownumber);
		json.put("categoryId", item.getCategoryId().trim());
		String itemId = item.getItemId();
		json.put("itemId", itemId);
		json.put("itemName", item.getItemName());
		json.put("itemNameEng", item.getItemNameEng());
		json.put("itemDimension", item.getItemDimension());
		String queryCode = item.getQueryCode();
		if (queryCode != null) {
			queryCode = queryCode.trim();
		}
		json.put("queryCode", queryCode);
		String itemSpecification = item.getItemSpecification();
		if (itemSpecification != null) {
			itemSpecification = itemSpecification.trim();
		}
		json.put("itemSpecification", itemSpecification);

		return json;
	}

	/**
	 * 获取原材料类别，树形结构
	 */
	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有原料");
		root.put("isLeaf", "N");
		jsonArray.add(root);
		if (TextUtil.isEmpty(itemType)) {
			itemType = "RAW";
		}

		List<ItemCategory> items = itemCategoryBean.queryTree(itemType);

		for (int i = 0; i < items.size(); i++) {
			ItemCategory itemCategory = items.get(i);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", itemCategory.getCategoryId().trim());
			jsonObject.put("name", itemCategory.getCategoryName());
			jsonObject.put("isLeaf", itemCategory.getIsLeaf());
			if (itemCategory.getParentId().equals("*")) {
				jsonObject.put("parent", "root");
			} else {
				jsonObject.put("parent", itemCategory.getParentId().trim());
			}
			jsonObject.put("path", itemCategory.getPath());
			jsonArray.add(jsonObject);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 从餐厅原材料信息数据表中读取信息 只查询原料元数据ID和Name，不查询库存、供应商、价格等，查找更快速, by cjt
	 */
	public void listItemMetaQuickly() throws NoPrivilegeException, SQLException, NoConnection {
		List<ItemMeta> itemLst = itemMetaBean.queryByCategory(categoryId);

		JSONArray jsonArray = new JSONArray();

		int rownumber = 1;
		for (ItemMeta item : itemLst) {
			jsonArray.add(formItemQuickly(item, rownumber));
			rownumber++;
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 把每一个原材料对象转换成JSON对象
	 * 
	 * @param item
	 * @param rownumber
	 * @return
	 */
	private JSONObject formItemQuickly(ItemMeta item, int rownumber) throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONObject json = new JSONObject();
		json.put("rownumber", rownumber);
		json.put("categoryId", item.getCategoryId().trim());
		String itemId = item.getItemId();
		json.put("itemId", itemId);
		json.put("itemName", item.getItemName());
		json.put("itemNameEng", item.getItemNameEng());
		json.put("itemDimension", item.getItemDimension());
		String queryCode = item.getQueryCode();
		if (queryCode != null) {
			queryCode = queryCode.trim();
		}
		json.put("queryCode", queryCode);
		String itemSpecification = item.getItemSpecification();
		if (itemSpecification != null) {
			itemSpecification = itemSpecification.trim();
		}
		json.put("itemSpecification", itemSpecification);

		return json;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setShelf(String shelf) {
		this.shelf = shelf;
	}

	public String getStorageId() {
		return storageId;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setItemQueryBean(ItemQueryBean itemQueryBean) {
		this.itemQueryBean = itemQueryBean;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

}
