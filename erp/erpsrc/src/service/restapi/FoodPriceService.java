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
import logic.store.ItemPriceBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.ItemPrice;
import service.restapi.util.JsonHelper;
import service.restapi.util.RestApiLog;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 说明：RESTAPI写入出品价格信息
 */
public class FoodPriceService {
	public static Logger log = Logger.getLogger("rest");

	private CompanyBean companyBean;

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	private ItemPriceBean itemPriceBean;

	public void setItemPriceBean(ItemPriceBean itemPrice) {
		this.itemPriceBean = itemPrice;
	}

	// post菜品价格信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveFoodPrice(String foodPrice) throws NoPrivilegeException, SQLException, NoConnection {
		log.debug(foodPrice);
		JsonHelper jsonHelper = new JsonHelper();
		String companyId = "";
		JSONArray arr = jsonHelper.getJsonArr(foodPrice);

		if (arr != null) {
			Map<String, String> comMap = companyBean.mapDbName();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				jsonHelper.start(i, json);

				ItemPrice itemPrice = new ItemPrice();
				companyId = jsonHelper.getJsonVId("brandId", 32);
				String foodId = jsonHelper.getJsonVId("foodId", 32);
				if (!companyId.equals("") && !foodId.equals("")) {
					itemPrice.setItemId(foodId);
					itemPrice.setItemPrice(jsonHelper.getJsonVDouble("price", 999999));
					itemPrice.setPriceType(jsonHelper.getJsonVStr("priceType", 32));
					itemPrice.setIsCurrent(jsonHelper.getJsonVInt("status", 9));
					itemPrice.setEffectTime(jsonHelper.getJsonVDateTime("lastModify"));

					itemPrice.setEffectiveFormId("RESTAPI");

					try {
						// String dbName =
						// companyBean.getDbNameByComId(companyId);
						String dbName = comMap.get(companyId).toString();
						if (dbName == null) {
							jsonHelper.errs.add("400:" + i + ":comErr:companyId not exist!");
						} else {
							itemPriceBean.saveEntity(itemPrice, dbName);
						}
					} catch (Exception e) {
						jsonHelper.errs.add("400:" + i + ":SqlException:" + e.toString());
					}
				}
				jsonHelper.end();
			}
		}

		JSONObject result = RestApiLog.restUtil.LogRestResult("foodPrice", companyId, jsonHelper);
		return result.toString();
	}
}
