/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mon Nov 03 10:14:53 CST 2014 by pw
 * Last edited on Mon Nov 03 10:14:53 CST 2014 by pw
 * 
 */
package pojo.form;

public class CollectRefForm {
	private String collectFormId;
	private String itemId;
	private String refFormId;

	public String getCollectFormId() {
		return this.collectFormId;
	}

	public void setCollectFormId(String collectFormId) {
		this.collectFormId = collectFormId;
	}

	public String getRefFormId() {
		return this.refFormId;
	}

	public void setRefFormId(String refFormId) {
		this.refFormId = refFormId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public CollectRefForm() {
	}

	public CollectRefForm(String collectFormId, String itemId, String refFormId) {
		this.collectFormId = collectFormId;
		this.itemId = itemId;
		this.refFormId = refFormId;
	}

}