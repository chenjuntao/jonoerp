/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月17日 by cjt
 * Last edited on 2016年3月17日 by cjt
 */
package service.restapi;

import java.sql.SQLException;
import java.util.Map;

import logic.NoConnection;
import logic.restapi.CompanyBean;
import logic.store.ItemCategoryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.ItemCategory;
import service.restapi.util.JsonHelper;
import service.restapi.util.RestApiLog;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.ItemType;

/**
 * 说明：RESTAPI写入出品类别数据
 */
public class FoodCategoryService {
	private CompanyBean companyBean;

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	private ItemCategoryBean itemCategoryBean;

	public void setItemCategoryBean(ItemCategoryBean itemCategory) {
		this.itemCategoryBean = itemCategory;
	}

	// post菜品分类信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveFoodCategory(String foodCategory) throws NoPrivilegeException, SQLException, NoConnection {
		JsonHelper jsonHelper = new JsonHelper();
		String companyId = "";
		JSONArray arr = jsonHelper.getJsonArr(foodCategory);
		if (arr != null) {
			Map<String, String> comMap = companyBean.mapDbName();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				jsonHelper.start(i, json);

				ItemCategory itemCategory = new ItemCategory();
				companyId = jsonHelper.getJsonVId("brandId", 32);
				String categoryId = jsonHelper.getJsonVId("id", 32);
				if (!companyId.equals("") && !categoryId.equals("")) {
					itemCategory.setCategoryId(categoryId);
					itemCategory.setCategoryName(jsonHelper.getJsonVStr("name", 100));
					itemCategory.setCategoryLevel(jsonHelper.getJsonVStr("level", 8));
					itemCategory.setPath(jsonHelper.getJsonVStr("path", 255));
					itemCategory.setItemType(ItemType.PRODUCT);

					itemCategory.setPath("RESTAPI");

					// 如果父类别为空，则设置为默认的类别
					String parentId = jsonHelper.getJsonVStr("parentId", 32);
					if (categoryId.equals("")) {
						itemCategory.setParentId("DEFAULT_FOOD_CATEGORY");

					} else {
						itemCategory.setParentId(parentId);
					}

					try {
						// String dbName =
						// companyBean.getDbNameByComId(companyId);
						String dbName = comMap.get(companyId).toString();
						if (dbName == null) {
							jsonHelper.errs.add("400:" + i + ":comErr:companyId not exist!");
						} else if (itemCategoryBean.containsEntity(categoryId, ItemType.PRODUCT, dbName)) {
							// 如果已存在记录则更新记录
							itemCategoryBean.updateEntity(itemCategory, dbName);
						} else {// 如果不存在记录则插入记录
							itemCategoryBean.saveEntity(itemCategory, dbName);
						}
					} catch (Exception e) {
						jsonHelper.errs.add("400:" + i + ":SqlException:" + e.toString());
					}
				}
				jsonHelper.end();
			}
		}

		JSONObject result = RestApiLog.restUtil.LogRestResult("foodCategory", companyId, jsonHelper);
		return result.toString();
	}
}
