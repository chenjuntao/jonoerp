/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Fri Sep 18 08:48:46 CST 2015 by lyz
 * Last edited on Fri Sep 18 08:48:46 CST 2015 by lyz
 * 
 * 说明：总部-研发样品信息表
 */
package pojo.form;

public class SampleInfo {

	/**
	 * 
	 */
	private String id;
	/**
	 * 商品名称
	 */
	private String productName;
	/**
	 * 起订量
	 */
	private Integer minimumOrderQuantity;
	/**
	 * 产地
	 */
	private String producePlace;
	/**
	 * 订货周期(天)
	 */
	private Integer orderCycle;
	/**
	 * 使用周期(天，季节性因素)
	 */
	private Integer useCycle;
	/**
	 * 食品安全资格，资格证照片
	 */
	private String qualificationPic;
	/**
	 * 照片
	 */
	private String samplePic;
	/**
	 * 价格(元)
	 */
	private Double price;
	/**
	 * 毛价(元)
	 */
	private Double grossPrice;
	/**
	 * 毛重(克)
	 */
	private Double grossWeight;
	/**
	 * 净重(克)
	 */
	private Double netWeight;
	/**
	 * 出净率(净菜/毛菜*100=出净率)
	 */
	private Double netRatio;
	/**
	 * 保质期(月)
	 */
	private Integer shelfLife;
	/**
	 * 送样时间(天)
	 */
	private Integer deliveryTime;
	/**
	 * 验收标准
	 */
	private String accptanceCriteria;
	/**
	 * 供应商
	 */
	private String supplier;

	private String createUserId;

	/**
	 * query only
	 */
	private String formStatus;

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getProductName() {
		return this.productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Integer getMinimumOrderQuantity() {
		return this.minimumOrderQuantity;
	}

	public void setMinimumOrderQuantity(Integer minimumOrderQuantity) {
		this.minimumOrderQuantity = minimumOrderQuantity;
	}

	public String getProducePlace() {
		return this.producePlace;
	}

	public void setProducePlace(String producePlace) {
		this.producePlace = producePlace;
	}

	public Integer getOrderCycle() {
		return this.orderCycle;
	}

	public void setOrderCycle(Integer orderCycle) {
		this.orderCycle = orderCycle;
	}

	public Integer getUseCycle() {
		return this.useCycle;
	}

	public void setUseCycle(Integer useCycle) {
		this.useCycle = useCycle;
	}

	public String getQualificationPic() {
		return this.qualificationPic;
	}

	public void setQualificationPic(String qualificationPic) {
		this.qualificationPic = qualificationPic;
	}

	public String getSamplePic() {
		return this.samplePic;
	}

	public void setSamplePic(String samplePic) {
		this.samplePic = samplePic;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getGrossPrice() {
		return this.grossPrice;
	}

	public void setGrossPrice(Double grossPrice) {
		this.grossPrice = grossPrice;
	}

	public Double getGrossWeight() {
		return this.grossWeight;
	}

	public void setGrossWeight(Double grossWeight) {
		this.grossWeight = grossWeight;
	}

	public Double getNetWeight() {
		return this.netWeight;
	}

	public void setNetWeight(Double netWeight) {
		this.netWeight = netWeight;
	}

	public Double getNetRatio() {
		return this.netRatio;
	}

	public void setNetRatio(Double netRatio) {
		this.netRatio = netRatio;
	}

	public Integer getShelfLife() {
		return this.shelfLife;
	}

	public void setShelfLife(Integer shelfLife) {
		this.shelfLife = shelfLife;
	}

	public Integer getDeliveryTime() {
		return this.deliveryTime;
	}

	public void setDeliveryTime(Integer deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getAccptanceCriteria() {
		return this.accptanceCriteria;
	}

	public void setAccptanceCriteria(String accptanceCriteria) {
		this.accptanceCriteria = accptanceCriteria;
	}

	public String getSupplier() {
		return this.supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getFormStatus() {
		return formStatus;
	}

	public void setFormStatus(String formStatus) {
		this.formStatus = formStatus;
	}

}