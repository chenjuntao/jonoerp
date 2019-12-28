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
import logic.form.BillPayBean;
import logic.restapi.CompanyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.BillPay;
import service.restapi.util.JsonHelper;
import service.restapi.util.RestApiLog;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 说明：RESTAPI写入单据对应的付款数据
 */
public class PayService {
	private CompanyBean companyBean;

	public static Logger log = Logger.getLogger("rest");

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	private BillPayBean billPayBean;

	public void setBillPayBean(BillPayBean billPayBean) {
		this.billPayBean = billPayBean;
	}

	// post单据付款详细信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String savePay(String pay) throws NoPrivilegeException, SQLException, NoConnection {
		JsonHelper jsonHelper = new JsonHelper();
		String companyId = "";
		JSONArray arr = jsonHelper.getJsonArr(pay);
		if (arr != null) {
			Map<String, String> comMap = companyBean.mapDbName();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				jsonHelper.start(i, json);

				BillPay billPay = new BillPay();
				companyId = jsonHelper.getJsonVId("brandId", 32);
				String billId = jsonHelper.getJsonVId("orderId", 32);
				String payId = jsonHelper.getJsonVId("payTypeId", 32);
				if (!companyId.equals("") && !billId.equals("") && !payId.equals("")) {
					billPay.setCbillC(billId);
					billPay.setCpayC(payId);
					billPay.setCpayN(jsonHelper.getJsonVStr("payTypeName", 100));
					billPay.setNpayamt(jsonHelper.getJsonVDouble("price", 999999));
					billPay.setSunit(jsonHelper.getJsonVStr("unit", 16));

					try {
						// String dbName =
						// companyBean.getDbNameByComId(companyId);
						// log.info("pay[" + companyId + "]" + billId);
						String dbName = comMap.get(companyId).toString();
						if (dbName == null) {
							jsonHelper.errs.add("400:" + i + ":comErr:companyId not exist!");
						} else {
							billPayBean.saveEntity(billPay, dbName);
						}
					} catch (Exception e) {
						jsonHelper.errs.add("400:" + i + ":SqlException:" + e.toString());
					}
				}
				jsonHelper.end();
			}
		}

		JSONObject result = RestApiLog.restUtil.LogRestResult("pay", companyId, jsonHelper);
		return result.toString();
	}
}
