/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Feb 04 09:39:01 CST 2015 by lyz
 * Last edited on Wed Feb 04 09:39:01 CST 2015 by lyz
 * 
 * 说明：配送单位表
 */
package pojo.store;

public class DeliveryUnit {

	/**
	 * 物料ID
	 */
	private String itemId;
	/**
	 * 配方单位
	 */
	private String recipeUnit;
	/**
	 * 转换因子(库存单位=配方单位*转换因子)
	 */
	private Double recipeFactor;

	/**
	 * 配送单位
	 */
	private String deliveryUnit;
	/**
	 * 转换因子(库存单位=配送单位*转换因子)
	 */
	private Double deliveryFactor;

	/**
	 * old转换因子
	 */
	private Double oldDeliveryFactor;

	/**
	 * old转换因子
	 */
	private Double oldRecipeFactor;
	/**
	 * (配送单位)单位体积(立方分米，即升)
	 */
	private Double unitVolume;
	/**
	 * (配送单位)单位重量(克G)
	 */
	private Double unitWeight;

	/**
	 * 最小订货量(库存单位)
	 */
	private Double minOrderCount;

	private Double oldMinOrderCount;

	/**
	 * 超收率(实收差异超过此百分比率,不予接收)
	 */
	private Double outReceiveRate;

	public String getItemId() {
		return this.itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getDeliveryUnit() {
		return this.deliveryUnit;
	}

	public void setDeliveryUnit(String deliveryUnit) {
		this.deliveryUnit = deliveryUnit;
	}

	public Double getDeliveryFactor() {
		return deliveryFactor;
	}

	public void setDeliveryFactor(Double deliveryFactor) {
		this.deliveryFactor = deliveryFactor;
	}

	public Double getUnitVolume() {
		return unitVolume;
	}

	public void setUnitVolume(Double unitVolume) {
		this.unitVolume = unitVolume;
	}

	public Double getUnitWeight() {
		return unitWeight;
	}

	public void setUnitWeight(Double unitWeight) {
		this.unitWeight = unitWeight;
	}

	public Double getOutReceiveRate() {
		return this.outReceiveRate;
	}

	public void setOutReceiveRate(Double outReceiveRate) {
		this.outReceiveRate = outReceiveRate;
	}

	public String getRecipeUnit() {
		return recipeUnit;
	}

	public void setRecipeUnit(String recipeUnit) {
		this.recipeUnit = recipeUnit;
	}

	public Double getRecipeFactor() {
		return recipeFactor;
	}

	public void setRecipeFactor(Double recipeFactor) {
		this.recipeFactor = recipeFactor;
	}

	public Double getMinOrderCount() {
		return minOrderCount;
	}

	public void setMinOrderCount(Double minOrderCount) {
		this.minOrderCount = minOrderCount;
	}

	public Double getOldRecipeFactor() {
		return oldRecipeFactor;
	}

	public void setOldRecipeFactor(Double oldRecipeFactor) {
		this.oldRecipeFactor = oldRecipeFactor;
	}

	public Double getOldDeliveryFactor() {
		return oldDeliveryFactor;
	}

	public void setOldDeliveryFactor(Double oldDeliveryFactor) {
		this.oldDeliveryFactor = oldDeliveryFactor;
	}

	public Double getOldMinOrderCount() {
		return oldMinOrderCount;
	}

	public void setOldMinOrderCount(Double oldMinOrderCount) {
		this.oldMinOrderCount = oldMinOrderCount;
	}

}