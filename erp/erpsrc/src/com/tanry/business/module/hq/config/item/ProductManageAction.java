/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 16, 2015 by liyzh
 * Last edited on Mar 16, 2015 by liyzh
 * 
 * 说明： 餐厅出品信息设置
 */
package com.tanry.business.module.hq.config.item;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.hq.ItemMetaQueryBean;
import logic.store.ItemCategoryBean;
import logic.store.ItemMetaBean;
import logic.store.ItemPriceBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.ItemCategory;
import pojo.store.ItemMeta;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.service.ProductManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.Cn2Spell;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.PriceType;
import com.tanry.framework.util.constant.SysOption;

public class ProductManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemMetaBean itemMetaBean;
	private ItemMetaQueryBean itemMetaQueryBean;
	private ItemCategoryBean itemCategoryBean;
	private ItemPriceBean itemPriceBean;
	private ProductManageService productManageService;

	private String itemId;
	private String itemType;
	private String categoryId;

	private ItemMeta itemMeta;
	private Double salePrice;

	private String binhandpro;
	private String bdisfood;
	private String bdiscount;
	private String bwaimai;

	private String searchType;
	private String condition;
	private int newEnd;
	private String displayAllFlag;

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(itemId)) {
			itemMeta = itemMetaBean.GetItemById(itemId);
			ItemCategory category = itemCategoryBean.queryById(itemMeta.getCategoryId());
			itemMeta.setCategoryName(category.getCategoryName());
			salePrice = itemPriceBean.getItemPrice(itemId, PriceType.SALE);
		} else {
			itemMeta = new ItemMeta();
			ItemCategory category = itemCategoryBean.queryById(categoryId);
			itemMeta.setItemType(category.getItemType());
			itemMeta.setCategoryName(category.getCategoryName());
		}
		return SUCCESS;
	}

	/**
	 * 获取出品的基础信息，成本价，售卖价等
	 */
	public void queryProduct() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = new ArrayList<Map>();
		int total = 0;
		if (newEnd == 1) {
			newEnd = Integer.MAX_VALUE;
		} else {
			newEnd = getEnd();
		}
		if (!TextUtil.isEmpty(searchType) && !TextUtil.isEmpty(condition)) {
			if ("product".equals(searchType)) {
				total = itemMetaQueryBean.countByProduct(condition, displayAllFlag);
				setTotal(total);
				if (total > 0) {
					itemLst = itemMetaQueryBean.queryByProduct(condition, displayAllFlag, getStart(), newEnd);
				}
			} else if ("raw".equals(searchType)) {
				total = itemMetaQueryBean.countRaw(condition);
				setTotal(total);
				if (total > 0) {
					itemLst = itemMetaQueryBean.queryByRaw(condition, getStart(), newEnd);
				}
			}

		} else {
			total = itemMetaQueryBean.countProduct(categoryId, displayAllFlag);
			setTotal(total);
			if (total > 0) {
				itemLst = itemMetaQueryBean.queryProduct(categoryId, displayAllFlag, getStart(), newEnd);
			}
		}

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (Map item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("effectDate", DateTimeUtil.getDateStr((Date) item.get("effectDate")));
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String itemName = itemMeta.getItemName();
		itemMeta.setItemName(itemName.replace("'", "‘"));
		itemMeta.setItemId(itemMeta.getItemId().replace("'", ""));
		String queryCode = Cn2Spell.converterToFirstSpell(itemMeta.getItemName());
		queryCode = (queryCode.replace("'", ""));
		itemMeta.setQueryCode(Cn2Spell.StringFilter(queryCode));

		int inhandpro = TextUtil.isEmpty(binhandpro) ? 0 : 1;
		int disfood = TextUtil.isEmpty(bdisfood) ? 0 : 1;
		int discount = TextUtil.isEmpty(bdiscount) ? 0 : 1;
		int waimai = TextUtil.isEmpty(bwaimai) ? 0 : 1;
		itemMeta.setbInHandPro(inhandpro);
		itemMeta.setbDisFood(disfood);
		itemMeta.setbDisCount(discount);
		itemMeta.setBwaimai(waimai);
		productManageService.saveProduct(itemId, itemMeta, salePrice);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取套餐小类的编码
	 * 
	 * @return
	 */
	public String getSuitCateId() {
		return SysOption.SUIT_CATE_ID;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public ItemMeta getItemMeta() {
		return itemMeta;
	}

	public void setItemMeta(ItemMeta itemMeta) {
		this.itemMeta = itemMeta;
	}

	public Double getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(Double salePrice) {
		this.salePrice = salePrice;
	}

	public void setBinhandpro(String binhandpro) {
		this.binhandpro = binhandpro;
	}

	public void setBdisfood(String bdisfood) {
		this.bdisfood = bdisfood;
	}

	public void setBdiscount(String bdiscount) {
		this.bdiscount = bdiscount;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setBwaimai(String bwaimai) {
		this.bwaimai = bwaimai;
	}

	public void setItemMetaQueryBean(ItemMetaQueryBean itemMetaQueryBean) {
		this.itemMetaQueryBean = itemMetaQueryBean;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

	public void setProductManageService(ProductManageService productManageService) {
		this.productManageService = productManageService;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public int getNewEnd() {
		return newEnd;
	}

	public void setNewEnd(int newEnd) {
		this.newEnd = newEnd;
	}

	public void setDisplayAllFlag(String displayAllFlag) {
		this.displayAllFlag = displayAllFlag;
	}

}
