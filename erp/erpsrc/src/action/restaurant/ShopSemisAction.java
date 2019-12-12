package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.store.ItemCategoryBean;
import logic.store.ItemMetaBean;
import logic.store.ItemPriceBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemCategory;
import pojo.store.ItemMeta;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

public class ShopSemisAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemCategoryBean itemCategoryBean;
	private ItemMetaBean itemMetaBean;
	private ItemPriceBean itemPriceBean;

	private String categoryId;

	private List<ItemMeta> itemMetaBeans;

	/**
	 * 获取半成品基本信息，标准价等
	 */
	public void listItemMeta() throws NoPrivilegeException, SQLException, NoConnection {
		itemMetaBeans = itemMetaBean.queryByCategory(categoryId);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (ItemMeta item : itemMetaBeans) {
			JSONObject jObject = new JSONObject();
			jObject.put("rownumber", rownumber);
			jObject.put("categoryId", item.getCategoryId().trim());
			String itemId = item.getItemId();
			jObject.put("itemId", itemId);
			jObject.put("itemName", item.getItemName());
			jObject.put("itemNameEng", item.getItemNameEng());
			jObject.put("itemDimension", item.getItemDimension());
			jObject.put("itemSpecification", item.getItemSpecification());
			jObject.put("queryCode", item.getQueryCode());

			// Double itemPrice = itemPriceBean.getItemPrice(itemId,
			// PriceType.BENCHMARK);// 餐厅的原料信息中不应看到进货价，而应是标准价
			// jObject.put("itemUnitPrice", itemPrice);
			jsonArray.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取半成类别，树形结构
	 */
	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有半成品");
		root.put("isLeaf", "N");
		jsonArray.add(root);

		List<ItemCategory> items = itemCategoryBean.queryTree("SEMIS");

		for (int i = 0; i < items.size(); i++) {
			ItemCategory itemCategory = items.get(i);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", itemCategory.getCategoryId().trim());
			jsonObject.put("name", itemCategory.getCategoryName());
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

	public List<ItemMeta> getItemMetaBeans() {
		return itemMetaBeans;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}
}
