/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Aug 04 17:01:18 CST 2015 by pw
 * Last edited on Tue Aug 04 17:01:18 CST 2015 by pw
 * 
 * 说明：出品岗位观察表头
 */
package pojo.form;


public class ObservationHeader {

	/**
	 * 出品编号
	 */
	private String itemId;
	/**
	 * 部分编码
	 */
	private String partId;
	/**
	 * 部分名称
	 */
	private String partName;
	/**
	 * 部分说明
	 */
	private String partRemark;

	/**
	 * 是否为模板
	 */
	private String isTemplate;

	public ObservationHeader(String itemId, String partId, String partName, String partRemark, String isTemplate) {
		this.itemId = itemId;
		this.partId = partId;
		this.partName = partName;
		this.partRemark = partRemark;
		this.isTemplate = isTemplate;
	}

	public ObservationHeader() {
	}

	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getPartId() {
		return this.partId;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public String getPartName() {
		return this.partName;
	}

	public void setPartName(String partName) {
		this.partName = partName;
	}

	public String getPartRemark() {
		return partRemark;
	}

	public void setPartRemark(String partRemark) {
		this.partRemark = partRemark;
	}

}