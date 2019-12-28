/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 7, 2015 by liyzh
 * Last edited on Apr 7, 2015 by liyzh
 * 
 * 说明： 原料、出品等类别设置
 */
package com.tanry.business.module.hq.config.tag;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.TagHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.store.TagHeader;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.tag.service.TagManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class TagManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private TagHeaderBean tagHeaderBean;
	private TagHeader tagHeader;

	private String type;
	private String tagId;

	private String cateIds;
	private String tagIds;

	private String bindTag;

	private String itemCondition;
	private String tagCondition;

	private TagManageService tagManageService;

	private String jsonData;

	private String itemCategoryType;

	@Override
	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		tagHeader = tagHeaderBean.queryByTagId(tagId);
		return SUCCESS;
	}

	public String setTagView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkTagId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		TagHeader tagHeader = tagHeaderBean.queryByTagId(tagId);
		JSONObject result = new JSONObject();
		if (tagHeader != null && tagHeader.getTagId() != null) {
			exist = true;
		}
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = JSONArray.fromObject(tagHeaderBean.query(""));

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryItems() throws NoPrivilegeException, SQLException, NoConnection {
		setTotal(tagManageService.itemsCount(itemCondition, cateIds, tagCondition, itemCategoryType));

		JSONArray jsonArray = JSONArray.fromObject(tagManageService.queryItems(itemCondition, cateIds, tagCondition,
				itemCategoryType, getStart(), getEnd()));

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void export() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = tagManageService.queryItems(itemCondition, cateIds, tagCondition, itemCategoryType, 0,
				Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");
		JSONArray arr = new JSONArray();

		String sheetName = structure.getString("sheetName");

		int rownumber = 1;
		for (Map map : itemLst) {
			JSONObject json = JSONObject.fromObject(map);

			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		if ("csv".equals(type)) {
			rownumber = 1;

			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < itemLst.size(); i++) {
				Map itemMeta = itemLst.get(i);
				JSONObject json = JSONObject.fromObject(itemMeta);
				json.put("rownumber", rownumber++);
				maplist.add(json);
			}

			StringBuffer sb = CsvUtil.exportCsv(colsJA, maplist);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryTagTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<TagHeader> tagHeaderLists = tagHeaderBean.query("");

		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有标签");
		arr.add(root);

		for (int i = 0; i < tagHeaderLists.size(); i++) {
			TagHeader tagHeader = tagHeaderLists.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", tagHeader.getTagId());
			jsonObject.put("name", "[" + tagHeader.getTagId() + "]" + tagHeader.getTagName());
			jsonObject.put("parent", "root");
			arr.add(jsonObject);
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryISingleTagTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<TagHeader> tagHeaderLists = tagHeaderBean.queryCheckedTag(cateIds);

		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "所有标签");
		arr.add(root);

		for (int i = 0; i < tagHeaderLists.size(); i++) {
			TagHeader tagHeader = tagHeaderLists.get(i);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", tagHeader.getTagId());
			jsonObject.put("name", "[" + tagHeader.getTagId() + "]" + tagHeader.getTagName());
			jsonObject.put("parent", "root");
			String checked = tagHeader.getChecked();
			if (checked.equals("Y")) {
				jsonObject.put("checked", true);
			} else {
				jsonObject.put("checked", false);
			}
			arr.add(jsonObject);
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		tagManageService.doSave(tagHeader);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doBatchTagSet() throws NoPrivilegeException, SQLException, NoConnection {
		tagManageService.doBatchTagSet(cateIds, tagIds, bindTag);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doISingleTagSet() throws NoPrivilegeException, SQLException, NoConnection {
		tagManageService.doISingleTagSet(cateIds, tagIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		tagManageService.doDelete(tagId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTagId() {
		return tagId;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public void setTagHeaderBean(TagHeaderBean tagHeaderBean) {
		this.tagHeaderBean = tagHeaderBean;
	}

	public void setTagHeader(TagHeader tagHeader) {
		this.tagHeader = tagHeader;
	}

	public TagHeader getTagHeader() {
		return tagHeader;
	}

	public void setTagManageService(TagManageService tagManageService) {
		this.tagManageService = tagManageService;
	}

	public String getCateIds() {
		return cateIds;
	}

	public void setTagIds(String tagIds) {
		this.tagIds = tagIds;
	}

	public void setCateIds(String cateIds) {
		this.cateIds = cateIds;
	}

	public String getBindTag() {
		return bindTag;
	}

	public void setBindTag(String bindTag) {
		this.bindTag = bindTag;
	}

	public void setItemCondition(String itemCondition) {
		this.itemCondition = itemCondition;
	}

	public void setTagCondition(String tagCondition) {
		this.tagCondition = tagCondition;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getItemCategoryType() {
		return itemCategoryType;
	}

	public void setItemCategoryType(String itemCategoryType) {
		this.itemCategoryType = itemCategoryType;
	}

}
