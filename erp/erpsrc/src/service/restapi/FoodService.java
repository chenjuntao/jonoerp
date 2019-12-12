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
import logic.store.ItemMetaBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.ItemMeta;
import service.restapi.util.JsonHelper;
import service.restapi.util.RestApiLog;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.ItemType;

/**
 * 说明：RESTAPI写入出品数据
 */
public class FoodService {
	public static Logger log = Logger.getLogger("rest");

	private CompanyBean companyBean;

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	private ItemMetaBean itemMetaBean;

	public void setItemMetaBean(ItemMetaBean itemMeta) {
		this.itemMetaBean = itemMeta;
	}

	// post菜品详细信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveFood(String food) throws NoPrivilegeException, SQLException, NoConnection {
		JsonHelper jsonHelper = new JsonHelper();
		String companyId = "";
		JSONArray arr = jsonHelper.getJsonArr(food);
		if (arr != null) {
			Map<String, String> comMap = companyBean.mapDbName();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				jsonHelper.start(i, json);

				ItemMeta itemMeta = new ItemMeta();
				companyId = jsonHelper.getJsonVId("brandId", 32);
				String foodId = jsonHelper.getJsonVId("foodId", 32);
				if (!companyId.equals("") && !foodId.equals("")) {
					itemMeta.setItemId(foodId);
					itemMeta.setItemName(jsonHelper.getJsonVStr("foodName", 150));
					itemMeta.setItemNameEng(jsonHelper.getJsonVStr("shortName", 64));
					itemMeta.setItemDimension(jsonHelper.getJsonVStr("foodDimension", 16));
					itemMeta.setQueryCode(jsonHelper.getJsonVStr("shortHandName", 64));
					itemMeta.setItemPic(jsonHelper.getJsonVStr("image", 32));
					itemMeta.setItemNote(jsonHelper.getJsonVStr("description", 200));
					itemMeta.setEnabled(jsonHelper.getJsonVInt("status", 9));
					itemMeta.setEffectDate(jsonHelper.getJsonVDateTime("lastModify"));
					itemMeta.setItemType(ItemType.PRODUCT);

					itemMeta.setItemSpecification("RESTAPI");

					// 如果类别为空，则设置为默认的类别
					String categoryId = jsonHelper.getJsonVStr("categoryId", 32);
					if (categoryId.equals("")) {
						itemMeta.setCategoryId("DEFAULT_FOOD_CATEGORY");
					} else {
						itemMeta.setCategoryId(categoryId);
					}

					try {
						// String dbName =
						// companyBean.getDbNameByComId(companyId);
						String dbName = comMap.get(companyId).toString();
						if (dbName == null) {
							jsonHelper.errs.add("400:" + i + ":comErr:companyId not exist!");
						} else if (itemMetaBean.containsEntity(foodId, ItemType.PRODUCT, dbName)) {
							// 如果已存在记录则更新记录
							itemMetaBean.updateEntity(foodId, itemMeta, dbName);
						} else {// 如果不存在记录则插入记录
							itemMetaBean.saveEntity(itemMeta, dbName);
						}
					} catch (Exception e) {
						jsonHelper.errs.add("400:" + i + ":SqlException:" + e.toString());
					}
				}
				jsonHelper.end();
			}
		}

		JSONObject result = RestApiLog.restUtil.LogRestResult("food", companyId, jsonHelper);
		return result.toString();
	}
}
