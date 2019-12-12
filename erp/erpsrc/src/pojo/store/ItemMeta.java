/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Oct 23, 2014 by liyzh
 * Last edited on Oct 23, 2014 by liyzh
 * 
 * 说明： TODO
 */
package pojo.store;

import java.util.Date;

import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ItemType;

public class ItemMeta {
	private String itemId; // 原材料、半成品、成品编码

	private String itemBarCode; // 条码

	private String itemName; // 名称

	private String itemNameEng; // 英文名称

	private String itemType; // 类型(原材料1/半成品2/成品3)

	private String categoryId; // 类别编码

	private String categoryName;// 类别名称

	private String cBranchC;// 录入门店id

	private String itemDimension; // 库存（基本）单位（出品例牌）

	private String queryCode; // 助记码

	/**
	 * 规格
	 */
	private String itemSpecification;

	/**
	 * 对应的图片id(对应于图片表中的图片id)
	 */
	private String itemPic;

	private String itemNote;
	/**
	 * 箱子类型(冷藏/非冷藏)
	 */
	private String boxType;
	/**
	 * 是否启用(0未启用，1已启用)
	 */
	private Integer enabled;

	/**
	 * 生效日期
	 */
	private Date effectDate;

	/**
	 * 库存数
	 */
	private Double itemCount;

	// mrp已分配量
	private Double allocation;

	// mrp在途量
	private Double onTheWay;
	/**
	 * 供应商
	 */
	private String supplierName;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemNameEng() {
		return itemNameEng;
	}

	public void setItemNameEng(String itemNameEng) {
		this.itemNameEng = itemNameEng;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCBranchC() {
		return cBranchC;
	}

	public void setCBranchC(String cBranchC) {
		this.cBranchC = cBranchC;
	}

	public String getItemDimension() {
		return itemDimension;
	}

	public void setItemDimension(String itemDimension) {
		this.itemDimension = itemDimension;
	}

	public String getQueryCode() {
		return queryCode;
	}

	public void setQueryCode(String queryCode) {
		this.queryCode = queryCode;
	}

	public String getItemPic() {
		return itemPic;
	}

	public void setItemPic(String itemPic) {
		this.itemPic = itemPic;
	}

	public Double getItemCount() {
		return itemCount;
	}

	public void setItemCount(Double itemCount) {
		this.itemCount = itemCount;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getItemNote() {
		return itemNote;
	}

	public void setItemNote(String itemNote) {
		this.itemNote = itemNote;
	}

	public String getBoxType() {
		return boxType;
	}

	public void setBoxType(String boxType) {
		this.boxType = boxType;
	}

	public Date getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public ItemMeta() {
		super();
	}

	public ItemMeta(String itemId, String itemName, String itemNameEng, String itemType, String categoryId,
			String categoryName, String cBranchC, String itemDimension, String queryCode, String itemSpecification,
			String itemPic, String itemNote, String boxType, Double itemCount, String supplierName) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemNameEng = itemNameEng;
		this.itemType = itemType;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.cBranchC = cBranchC;
		this.itemDimension = itemDimension;
		this.queryCode = queryCode;
		this.itemSpecification = itemSpecification;
		this.itemPic = itemPic;
		this.itemNote = itemNote;
		this.boxType = boxType;
		this.itemCount = itemCount;
		this.supplierName = supplierName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemMeta other = (ItemMeta) obj;
		if (itemId == null) {
			if (other.itemId != null)
				return false;
		} else if (!itemId.equals(other.itemId))
			return false;
		return true;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public Double getAllocation() {
		return allocation;
	}

	public void setAllocation(Double allocation) {
		this.allocation = allocation;
	}

	public Double getOnTheWay() {
		return onTheWay;
	}

	public void setOnTheWay(Double onTheWay) {
		this.onTheWay = onTheWay;
	}

	public String getItemBarCode() {
		return itemBarCode;
	}

	public void setItemBarCode(String itemBarCode) {
		this.itemBarCode = itemBarCode;
	}

	public String getItemSpecification() {
		if (!TextUtil.isEmpty(itemType) && itemType.equals(ItemType.PRODUCT)) {
			this.itemSpecification = String.valueOf(bInHandPro) + String.valueOf(bDisFood) + String.valueOf(bDisCount)
					+ String.valueOf(bwaimai);
		}
		return itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		if (!TextUtil.isEmpty(itemType) && itemType.equals(ItemType.PRODUCT)) {
			try {
				this.bInHandPro = Integer.parseInt(itemSpecification.substring(0, 1));// 手持下载
				this.bDisFood = Integer.parseInt(itemSpecification.substring(1, 2));// 出品折扣
				this.bDisCount = Integer.parseInt(itemSpecification.substring(2, 3));// 照单折扣
				this.bwaimai = Integer.parseInt(itemSpecification.substring(3, 4));// 照单折扣
			} catch (Exception ex) {
				this.bInHandPro = 0;
				this.bDisFood = 0;
				this.bDisCount = 0;
				this.bwaimai = 0;
			}
		}
		this.itemSpecification = itemSpecification;
	}

	// 以下仅用作出品----------------------------------------------------------------------------------------------------
	private int bInHandPro;// 手持下载
	private int bDisFood;// 出品折扣
	private int bDisCount;// 照单折扣
	private int bwaimai;// 外卖出品

	// （出品）手持下载
	public int getbInHandPro() {
		return bInHandPro;
	}

	// （出品）手持下载
	public void setbInHandPro(int bInHandPro) {
		this.bInHandPro = bInHandPro;
	}

	// （出品）出品折扣
	public int getbDisFood() {
		return bDisFood;
	}

	// （出品）出品折扣
	public void setbDisFood(int bDisFood) {
		this.bDisFood = bDisFood;
	}

	// （出品）照单折扣
	public int getbDisCount() {
		return bDisCount;
	}

	// （出品）照单折扣
	public void setbDisCount(int bDisCount) {
		this.bDisCount = bDisCount;
	}

	// （出品）外卖
	public int getBwaimai() {
		return bwaimai;
	}

	// （出品）外卖
	public void setBwaimai(int bwaimai) {
		this.bwaimai = bwaimai;
	}

}
