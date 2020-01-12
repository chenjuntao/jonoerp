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
import logic.form.FoodBillsBean;
import logic.restapi.CompanyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FoodBills;
import service.restapi.util.JsonHelper;
import service.restapi.util.RestApiLog;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 说明：RESTAPI写入单据中具体的菜品数据
 */
public class BillsService {
	private CompanyBean companyBean;

	public static Logger log = Logger.getLogger("rest");

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	private FoodBillsBean foodBillsBean;

	public void setFoodBillsBean(FoodBillsBean foodBillsBean) {
		this.foodBillsBean = foodBillsBean;
	}

	// post单据菜品详细信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveBills(String bills) throws NoPrivilegeException, SQLException, NoConnection {
		JsonHelper jsonHelper = new JsonHelper();
		String companyId = "";
		JSONArray arr = jsonHelper.getJsonArr(bills);

		if (arr != null) {
			Map<String, String> comMap = companyBean.mapDbName();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				jsonHelper.start(i, json);

				FoodBills foodBills = new FoodBills();
				companyId = jsonHelper.getJsonVId("brandId", 32);
				String billId = jsonHelper.getJsonVId("orderId", 32);
				String foodId = jsonHelper.getJsonVId("foodId", 32);
				if (!companyId.equals("") && !billId.equals("") && !foodId.equals("")) {
					foodBills.setCbillC(billId);
					foodBills.setCfoodC(foodId);
					foodBills.setCfoodN(jsonHelper.getJsonVStr("foodName", 150));
					foodBills.setClitclsC(jsonHelper.getJsonVStr("foodTypeId", 32));
					foodBills.setClitclsN(jsonHelper.getJsonVStr("foodTypeName", 100));
					foodBills.setSunit(jsonHelper.getJsonVStr("foodSpecName", 50));
					foodBills.setNqty(jsonHelper.getJsonVDouble("amount", 999999));
					foodBills.setNamt(jsonHelper.getJsonVDouble("totalOriginalPrice", 999999));
					foodBills.setNdisamt(jsonHelper.getJsonVDouble("discountPrice", 999999));
					foodBills.setEsuitflag(jsonHelper.getJsonVStr("isCombo", 1));
					foodBills.setCsuitbill(jsonHelper.getJsonVStr("comboId", 32));
					foodBills.setCsuitname(jsonHelper.getJsonVStr("comboName", 300));
					int resendFlag = jsonHelper.getJsonVInt("isDiscount", 1);
					String eresendFlag = "正常";
					if (resendFlag == 0) {
						eresendFlag = "正常";
					} else if (resendFlag == 1) {
						eresendFlag = "赠送";
					} else if (resendFlag == -1) {
						eresendFlag = "退品";
					}
					foodBills.setEretsendflag(eresendFlag);

					foodBills.setSretsendremark(jsonHelper.getJsonVStr("discountReason", 300));

					try {
						// log.info("bills[" + companyId + "]" + billId);
						String dbName = comMap.get(companyId).toString();
						// dbName = companyBean.getDbNameByComId(companyId);
						if (dbName == null) {
							jsonHelper.errs.add("400:" + i + ":comErr:companyId not exist!");
						} else {
							foodBillsBean.saveEntity(foodBills, dbName);
						}
					} catch (Exception e) {
						jsonHelper.errs.add("400:" + i + ":SqlException:" + e.toString());
					}
				}
				jsonHelper.end();
			}
		}

		JSONObject result = RestApiLog.restUtil.LogRestResult("bills", companyId, jsonHelper);
		return result.toString();
	}
}
