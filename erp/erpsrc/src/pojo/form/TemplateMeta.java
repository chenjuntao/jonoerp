/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明： 餐厅要货、外部订货商订货需求模板元信息
 */
package pojo.form;

public class TemplateMeta {
	/**
	 * 模板编号
	 */
	private String templateId;
	/**
	 * 模板名称
	 */
	private String templateName;
	/**
	 * 模板类别,eg: 要货、盘点
	 */
	private String templateType;
	/**
	 * 制作模板的部门
	 */
	private String branchId;
	/**
	 * 模板主要商品类别(最多列出前五种)
	 */
	private String categoryId;
	private String categoryName;
	/**
	 * 模板备注信息
	 */
	private String templateNote;
	/**
	 * 到货周期
	 */
	private Integer arrivePeriod;
	/**
	 * 配送区域，跟物流中心有关
	 */
	private String deliveryRegion;
	/**
	 * 配送模式
	 */
	private String deliveryType;

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateName() {
		return templateName;
	}

	public void setTemplateName(String templateName) {
		this.templateName = templateName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getTemplateNote() {
		return templateNote;
	}

	public void setTemplateNote(String templateNote) {
		this.templateNote = templateNote;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public Integer getArrivePeriod() {
		return this.arrivePeriod;
	}

	public void setArrivePeriod(Integer arrivePeriod) {
		this.arrivePeriod = arrivePeriod;
	}

	public String getDeliveryType() {
		return this.deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getDeliveryRegion() {
		return deliveryRegion;
	}

	public void setDeliveryRegion(String deliveryRegion) {
		this.deliveryRegion = deliveryRegion;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
}
