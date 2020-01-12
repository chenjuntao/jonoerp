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
import logic.form.FoodBillBean;
import logic.restapi.CompanyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FoodBill;
import service.restapi.util.JsonHelper;
import service.restapi.util.RestApiLog;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 说明：RESTAPI写入单据数据
 */
public class BillService {
	private CompanyBean companyBean;

	public static Logger log = Logger.getLogger("rest");

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	private FoodBillBean foodBillBean;

	public void setFoodBillBean(FoodBillBean foodBillBean) {
		this.foodBillBean = foodBillBean;
	}

	// post单据信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveBill(String bill) throws NoPrivilegeException, SQLException, NoConnection {
		JsonHelper jsonHelper = new JsonHelper();
		String companyId = "";
		JSONArray arr = jsonHelper.getJsonArr(bill);
		if (arr != null) {
			Map<String, String> comMap = companyBean.mapDbName();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				jsonHelper.start(i, json);

				FoodBill foodBill = new FoodBill();
				String billId = jsonHelper.getJsonVStr("orderId", 32);
				companyId = jsonHelper.getJsonVStr("brandId", 32);
				if (!companyId.equals("") && !billId.equals("")) {
					foodBill.setCbillC(billId);
					foodBill.setDbusiness(jsonHelper.getJsonVDate("businessDate"));
					foodBill.setCperiod(jsonHelper.getJsonVStr("period", 36));
					foodBill.setCbranchC(jsonHelper.getJsonVStr("restId", 32));
					foodBill.setCbranchN(jsonHelper.getJsonVStr("restName", 150));
					foodBill.setCtable(jsonHelper.getJsonVStr("deskName", 100));
					foodBill.setIguestnum(jsonHelper.getJsonVInt("personCount", 999));
					// foodBill.setCvipC(jsonHelper.getJsonVStr("consumerId",
					// 32));
					foodBill.setSremark(jsonHelper.getJsonVStr("memo", 500));
					foodBill.setDtbilltime(jsonHelper.getJsonVDateTime("confirmTime"));
					foodBill.setDtsettletime(jsonHelper.getJsonVDateTime("payTime"));
					foodBill.setNfoodamt(jsonHelper.getJsonVDouble("totalPrice", 999999));
					foodBill.setNdisamt(jsonHelper.getJsonVDouble("discountPrice", 999999));
					foodBill.setCdiscurwhy(jsonHelper.getJsonVStr("discountReason", 300));
					foodBill.setNoughtamt(jsonHelper.getJsonVDouble("realPrice", 999999));
					foodBill.setNroundamt(jsonHelper.getJsonVDouble("roundPrice", 999999));
					foodBill.setNpayamt(jsonHelper.getJsonVDouble("paidPrice", 999999));
					foodBill.setCcreateman(jsonHelper.getJsonVStr("employeeName", 100));
					foodBill.setCsettleman(jsonHelper.getJsonVStr("employeeName", 100));

					try {
						// String dbName =
						// companyBean.getDbNameByComId(companyId);
						// log.info("bill[" + companyId + "]" + billId);
						String dbName = comMap.get(companyId).toString();
						if (dbName == null) {
							jsonHelper.errs.add("400:" + i + ":comErr:companyId not exist!");
						} else {
							foodBillBean.saveEntity(foodBill, dbName);
						}
					} catch (Exception e) {
						jsonHelper.errs.add("400:" + i + ":SqlException:" + e.toString());
					}
				}
				jsonHelper.end();
			}
		}

		JSONObject result = RestApiLog.restUtil.LogRestResult("bill", companyId, jsonHelper);

		return result.toString();
	}

}
