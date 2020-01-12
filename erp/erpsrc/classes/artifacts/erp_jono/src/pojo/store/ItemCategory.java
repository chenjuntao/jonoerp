/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 2, 2015 by liyzh
 * Last edited on Mar 2, 2015 by liyzh
 * 
 * 说明：  原料、出品等类别设置
 */
package pojo.store;

public class ItemCategory {

	private String isLeaf;

	private String categoryId; // 原材料、成品、半成品类别编号

	private String categoryName; // 类别名称

	private String categoryLevel; // 类别级别

	private String parentId; // 父类别ID
	/**
	 * 类别树路径
	 */
	private String path;
	private String itemType; // 类别类型(1原材料/2半成品/3成品)

	public String getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(String isLeaf) {
		this.isLeaf = isLeaf;
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

	public String getCategoryLevel() {
		return categoryLevel;
	}

	public void setCategoryLevel(String categoryLevel) {
		this.categoryLevel = categoryLevel;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public ItemCategory() {
		super();
	}

	public ItemCategory(String isLeaf, String categoryId, String categoryName, String categoryLevel, String parentId,
			String path, String itemType) {
		super();
		this.isLeaf = isLeaf;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.categoryLevel = categoryLevel;
		this.parentId = parentId;
		this.path = path;
		this.itemType = itemType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoryId == null) ? 0 : categoryId.hashCode());
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
		ItemCategory other = (ItemCategory) obj;
		if (categoryId == null) {
			if (other.categoryId != null)
				return false;
		} else if (!categoryId.equals(other.categoryId))
			return false;
		return true;
	}

}
