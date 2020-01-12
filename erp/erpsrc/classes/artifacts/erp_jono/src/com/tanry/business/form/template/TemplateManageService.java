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
 * 说明： 通用单据模板管理
 */
package com.tanry.business.form.template;

import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.form.TemplateItemBean;
import logic.form.TemplateMetaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.TemplateItem;
import pojo.form.TemplateMeta;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.LogType;

public class TemplateManageService {

	private TemplateMetaBean templateMetaBean;
	private TemplateItemBean templateItemBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createTemplate(String oldId, TemplateMeta meta, String jsonData, String isSord)
			throws NoPrivilegeException, SQLException, NoConnection {

		String templateId = meta.getTemplateId();
		if (!TextUtil.isEmpty(oldId)) {
			deleteTemplate(oldId);
			saveOperation(templateId, LogType.UPDATE, "编辑模板");
		} else {
			saveOperation(templateId, LogType.CREATE, "新增模板");
		}
		JSONArray arr = JSONArray.fromObject(jsonData);
		String allCategory = "";
		int cateCount = 0;
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			TemplateItem item = new TemplateItem();
			item.setTemplateId(templateId);
			item.setItemId(json.getString("itemId"));
			if (isSord.equals("true")) {
				item.setItemOrder(json.getInt("itemId"));
			} else {
				item.setItemOrder(json.getInt("rownumber"));// 进行排序
			}
			templateItemBean.saveEntity(item);

			String cate = json.getString("itemCategory");
			if (cateCount < 5 && allCategory.indexOf(cate) < 0) {// 最多列出前五种
				allCategory = allCategory + cate + ",";
				cateCount++;
			}
		}
		if (allCategory.endsWith(",")) {
			allCategory = allCategory.substring(0, allCategory.length() - 1);
		}
		meta.setCategoryId(allCategory);
		templateMetaBean.saveEntity(meta);
		OperateVersionUtil.save(templateId);

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateTemplate(TemplateMeta meta, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String templateId = meta.getTemplateId();
		JSONArray itemArr = JSONArray.fromObject(jsonData);

		// 重新生成原料列表
		List<TemplateItem> itemLst = templateItemBean.query(templateId);
		for (TemplateItem item : itemLst) {// 寻找需要删除的原料
			String itemId = item.getItemId();
			boolean isDel = true;
			for (Object obj : itemArr) {
				JSONObject json = (JSONObject) obj;
				if (itemId.equals(json.getString("itemId"))) {// 如果记录依然存在，则不用删除
					isDel = false;
					break;
				}
			}
			if (isDel) {
				templateItemBean.deleteEntity(templateId, itemId);
			}
		}
		String allCategory = "";
		int cateCount = 0;
		for (Object obj : itemArr) {// 寻找需要新增的原料
			JSONObject json = (JSONObject) obj;
			String itemId = json.getString("itemId");
			boolean isAdd = true;
			for (TemplateItem item : itemLst) {
				if (itemId.equals(item.getItemId())) {// 如果记录已经存在，则不用新增
					isAdd = false;
					break;
				}
			}
			if (isAdd) {
				TemplateItem item = new TemplateItem();
				item.setTemplateId(templateId);
				item.setItemId(itemId);
				item.setItemOrder(json.getInt("rownumber"));// 使用序号进行排序
				templateItemBean.saveEntity(item);
			}

			String cate = json.getString("itemCategory");
			if (cateCount < 5 && allCategory.indexOf(cate) < 0) {// 最多列出前五种
				allCategory = allCategory + cate + ",";
				cateCount++;
			}
		}
		if (allCategory.endsWith(",")) {
			allCategory = allCategory.substring(0, allCategory.length() - 1);
		}
		meta.setCategoryId(allCategory);
		templateMetaBean.updateEntity(meta);
		saveOperation(templateId, LogType.UPDATE, "编辑模板");
	}

	/**
	 * @param templateId
	 * @param meta
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void copyTemplate(String templateId, TemplateMeta meta) throws NoPrivilegeException, SQLException,
			NoConnection {
		templateMetaBean.saveEntity(meta);

		List<TemplateItem> itemLst = templateItemBean.query(templateId);
		String newTempId = meta.getTemplateId();
		for (TemplateItem item : itemLst) {
			TemplateItem newItem = new TemplateItem();
			newItem.setTemplateId(newTempId);
			newItem.setItemId(item.getItemId());
			newItem.setItemOrder(item.getItemOrder());
			templateItemBean.saveEntity(newItem);
		}
	}

	/**
	 * @param ids
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteTemplate(String ids) throws NoPrivilegeException, SQLException, NoConnection {
		String[] idArr = ids.split(",");
		for (String templateId : idArr) {
			templateMetaBean.deleteEntity(templateId);
			templateItemBean.deleteItem(templateId);
		}
		saveOperation(ids, LogType.DELETE, "删除模板");
	}

	public void saveOperation(String ids, String type, String msg) throws NoPrivilegeException, SQLException,
			NoConnection {
		String[] idArr = ids.split(",");
		for (String templateId : idArr) {
			OperateLogUtil.record(templateId, type, msg);
		}
	}

	public void setTemplateItemBean(TemplateItemBean templateItemBean) {
		this.templateItemBean = templateItemBean;
	}

	public void setTemplateMetaBean(TemplateMetaBean templateMetaBean) {
		this.templateMetaBean = templateMetaBean;
	}

}
