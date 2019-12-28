/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Apr 16 10:36:50 CST 2015 by lyz
 * Last edited on Thu Apr 16 10:36:50 CST 2015 by lyz
 * 
 * 说明：箱子类型(冷藏、非冷藏等)
 */
package pojo.store;

public class BoxType {

	/**
	 * 编码
	 */
	private String typeId;
	/**
	 * 名称
	 */
	private String typeName;
	/**
	 * 体积(升/L)，不能超限
	 */
	private Double volume;
	/**
	 * 重量(克/G)，不能超限
	 */
	private Double weight;

	private Integer enabled;

	public String getTypeId() {
		return this.typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return this.typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public Double getWeight() {
		return this.weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Integer getEnabled() {
		return enabled;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

}