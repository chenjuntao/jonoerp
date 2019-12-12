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
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.restapi.CompanyBean;
import logic.store.BranchBean;
import logic.store.DeliveryRegionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.Branch;
import pojo.store.DeliveryRegion;
import service.restapi.util.JsonHelper;
import service.restapi.util.RestApiLog;

import com.tanry.business.module.hq.config.branch.service.BranchManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.constant.BranchType;

/**
 * 说明：RESTAPI写入餐厅数据
 */
public class RestaurantService {
	public static Logger log = Logger.getLogger("rest");

	private CompanyBean companyBean;

	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}

	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private BranchManageService branchManageService;

	public void setBranchManageService(BranchManageService branchManageService) {
		this.branchManageService = branchManageService;
	}

	private DeliveryRegionBean deliveryRegionBean;

	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}

	// post餐厅门店详细信息
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String saveRestaurant(String restaurant) throws NoPrivilegeException, SQLException, NoConnection {
		JsonHelper jsonHelper = new JsonHelper();
		String companyId = "";
		JSONArray arr = jsonHelper.getJsonArr(restaurant);
		if (arr != null) {
			Map<String, String> comMap = companyBean.mapDbName();
			for (int i = 0; i < arr.size(); i++) {
				JSONObject json = arr.getJSONObject(i);
				jsonHelper.start(i, json);

				Branch branch = new Branch();
				companyId = jsonHelper.getJsonVId("brandId", 32);
				String restId = jsonHelper.getJsonVId("restaurantId", 32);
				if (!companyId.equals("") && !restId.equals("")) {
					branch.setBranchId(restId);
					branch.setBranchName(jsonHelper.getJsonVStr("rName", 150));
					branch.setQueryCode(jsonHelper.getJsonVStr("queryCode", 32));
					branch.setBranchAddress(jsonHelper.getJsonVStr("address", 100));
					branch.setContactMan(jsonHelper.getJsonVStr("contactMan", 64));
					branch.setEmail(jsonHelper.getJsonVStr("email", 32));
					branch.setTelephone(jsonHelper.getJsonVStr("phone", 16));
					branch.setFax(jsonHelper.getJsonVStr("fax", 16));
					branch.setRemark(jsonHelper.getJsonVStr("brief", 200));
					branch.setEnabled(jsonHelper.getJsonVInt("state", 9));
					branch.setPark(jsonHelper.getJsonVStr("park", 100));
					branch.setTag(jsonHelper.getJsonVStr("tag", 50));
					branch.setLon(jsonHelper.getJsonVDouble("lon", 180));
					branch.setLat(jsonHelper.getJsonVDouble("lat", 90));
					Date createTime = jsonHelper.getJsonVDateTime("createTime");
					Date lastModify = jsonHelper.getJsonVDateTime("lastModify");

					branch.setBranchType(BranchType.RESTAURANT);
					branch.setRemark("RESTAPI");

					try {
						// String dbName =
						// companyBean.getDbNameByComId(companyId);
						String dbName = comMap.get(companyId).toString();
						if (dbName == null) {
							jsonHelper.errs.add("400:" + i + ":comErr:companyId not exist!");
						} else if (branchBean.containsEntity(restId, BranchType.RESTAURANT, dbName)) {
							// 如果已存在记录则更新记录
							branchBean.updateEntity(restId, branch, dbName);
						} else {// 如果不存在记录则插入记录

							// 门店相关的配送区域
							List<DeliveryRegion> regions = deliveryRegionBean.queryRegionByLcId(
									BranchCode.DEFAULT_LOGISTICSCENTER, dbName);
							if (regions.size() == 0) {
								DeliveryRegion region = new DeliveryRegion();
								region.setBranchId(BranchCode.DEFAULT_LOGISTICSCENTER);
								region.setRegionId("rg001");
								region.setRegionName("默认区域");
								region.setEnabled(1);
								region.setDeliveryCycle(1);
								deliveryRegionBean.saveRegion(region, dbName);
								branch.setRegionId("rg001");
							} else {
								branch.setRegionId(regions.get(0).getRegionId());
							}

							branchManageService.saveRestaurant(branch, dbName);
						}
					} catch (Exception e) {
						jsonHelper.errs.add("400:" + i + ":SqlException:" + e.toString());
					}
				}
				jsonHelper.end();
			}
		}

		JSONObject result = RestApiLog.restUtil.LogRestResult("restaurant", companyId, jsonHelper);
		return result.toString();
	}
}
