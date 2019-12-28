/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Thu Jun 02 16:10:43 CST 2016 by pw
 * Last edited on Thu Jun 02 16:10:43 CST 2016 by pw
 * 
 * 说明：标签明细表
 */
package pojo.store;

public class TagDetail {

	/**
	 * 标签ID
	 */
	private String tagId;
	/**
	 * 物料编码/其他编码
	 */
	private String code;

	public String getTagId() {
		return this.tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}