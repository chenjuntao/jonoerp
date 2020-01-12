/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Tue Apr 28 10:42:27 CST 2015 by lyz
 * Last edited on Tue Apr 28 10:42:27 CST 2015 by lyz
 * 
 * 说明：部门类型表(暂定七大类)
 */
package pojo.store;

public class BranchType {	
	
	/**
	 * 类型编码
	 */
	private String typeId;
	/**
	 * 类型名称
	 */
	private String typeName;
	/**
	 * 描述
	 */
	private String description;

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

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}