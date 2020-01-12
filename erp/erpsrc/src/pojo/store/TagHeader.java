/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Wed Jun 01 10:51:52 CST 2016 by pw
 * Last edited on Wed Jun 01 10:51:52 CST 2016 by pw
 * 
 * 说明：标签头表
 */
package pojo.store;

public class TagHeader {

	/**
	 * 标签ID
	 */
	private String tagId;
	/**
	 * 标签名称
	 */
	private String tagName;
	/**
	 * 标签类型
	 */
	private String typeCode;

	private String tagNote;

	private String rownumber;

	private String checked;

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

	public String getRownumber() {
		return rownumber;
	}

	public void setRownumber(String rownumber) {
		this.rownumber = rownumber;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getTypeCode() {
		return typeCode;
	}

	public void setTypeCode(String typeCode) {
		this.typeCode = typeCode;
	}

	public String getTagNote() {
		return tagNote;
	}

	public void setTagNote(String tagNote) {
		this.tagNote = tagNote;
	}

}