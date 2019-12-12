/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 10, 2015 by liyzh
 * Last edited on Mar 10, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.setting.user.vo;

import net.sf.json.JSONObject;

public class SysMenu {
	private String id;
	private String name;
	private String imageName;
	private String path;
	private Integer priority;
	private String url;
	private String description;
	private String parentId;
	private Integer isLeaf;
	private Integer checked;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	public Integer getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(Integer isLeaf) {
		this.isLeaf = isLeaf;
	}

	public Integer getChecked() {
		return checked;
	}

	public void setChecked(Integer checked) {
		this.checked = checked;
	}

	public SysMenu() {
		super();
	}
	
	public SysMenu(String parentId, String parentPath, JSONObject json) {
		super();
		if(json.has("Id")){
			setId(json.getString("Id"));
			setName(!json.has("Name") ? "" : json.getString("Name"));
			setImageName(!json.has("ImageName") ? "" : json.getString("ImageName"));
			setPriority(!json.has("Priority") ? 0 : json.getInt("Priority"));
			setUrl(!json.has("Url") ? "" : json.getString("Url"));
			setIsLeaf(json.has("Children") ? 0 : 1);
			
			setParentId(parentId);
			setPath(parentPath+"/"+id);
		}else{
			System.out.print("菜单错误：菜单项的ID不能为空！");
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SysMenu other = (SysMenu) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
