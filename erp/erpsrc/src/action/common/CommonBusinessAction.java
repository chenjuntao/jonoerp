/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on July 29, 2014 by liyzh
 * Last edited on Aug 6, 2014 by liyzh
 * 
 * 说明： 实现一些公用请求，比如获取餐厅列表
 */
package action.common;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.restaurant.ArrivePeriodBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.DeliveryRegionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.BranchStorage;
import pojo.store.DeliveryRegion;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class CommonBusinessAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private DeliveryRegionBean deliveryRegionBean;

	private ArrivePeriodBean arrivePeriodBean;
	private BranchStorageBean branchStorageBean;
	/**
	 * 配送模式
	 */
	private String deliveryType;
	/**
	 * 餐厅门店或物流中心的编号
	 */
	private String branchId;
	/**
	 * 部门类型
	 */
	private String branchType;
	private String branchType1;
	private String branchType2;
	private String branchFlag;

	/**
	 * 区域或分组ID
	 */
	private String regionId;

	/**
	 * 获取餐厅列表
	 */
	public void listShop() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
		JSONArray arr = JSONArray.fromObject(shopLst);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listStorage() throws NoPrivilegeException, SQLException, NoConnection {
		List<BranchStorage> shopLst = branchStorageBean.query("", branchType);
		JSONArray arr = JSONArray.fromObject(shopLst);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * list branch and storage data
	 */
	public void listBs() throws NoPrivilegeException, SQLException, NoConnection {
		Map map = new HashMap();
		if (!TextUtil.isEmpty(branchFlag)) {
			if (branchType1.indexOf(",") != -1) {
				map.put("branch", branchBean.listShopByType(branchType1));
			} else {
				map.put("branch", branchBean.listShopByType(BranchTypeEnum.getEnum(branchType1)));
			}
			map.put("storage", branchStorageBean.query("", branchType2));
		} else {
			if (!TextUtil.isEmpty(branchType1)) {
				// map.put("branch", branchBean.listShop(branchType1));
				map.put("branch", branchBean.queryById(getLoginBranchId()));
			}
			if (!TextUtil.isEmpty(branchType2)) {
				// map.put("storage", branchStorageBean.query("", branchType2));
				map.put("storage", branchStorageBean.query(getLoginBranchId(), branchType2));
			}
		}

		JSONObject jObject = JSONObject.fromObject(map);
		try {
			this.outJS(jObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryArrivePeriod() throws NoPrivilegeException, SQLException, NoConnection {
		List<Integer> periodLst = new ArrayList<Integer>();
		if (FormConstant.UNIFIED_DELIVERY.equals(deliveryType)) {
			periodLst = arrivePeriodBean.queryUnified();
		} else if (FormConstant.DIRECT_DELIVERY.equals(deliveryType)) {
			periodLst = arrivePeriodBean.queryDirect();
		} else if (FormConstant.CROSS_DELIVERY.equals(deliveryType)) {
			periodLst = arrivePeriodBean.queryCross();
		}
		JSONArray arr = JSONArray.fromObject(periodLst);
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询某一区域的配送周期
	 */
	public void queryDeliveryCycle() throws NoPrivilegeException, SQLException, NoConnection {
		DeliveryRegion dr = deliveryRegionBean.queryRegionById(regionId);
		int dCycle = -1; // 如果没有该记录，则默认为-1，by cjt
		if (dr != null) {
			dCycle = dr.getDeliveryCycle();
		}
		JSONObject result = new JSONObject();
		result.put("dCycle", dCycle);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据当前餐厅获取所属的区域
	 */
	public void getRegionByBranch() throws NoPrivilegeException, SQLException, NoConnection {
		DeliveryRegion region = deliveryRegionBean.getRegionByBranch(branchId);
		Map<String, Object> regionMap = new HashMap<String, Object>();
		regionMap.put("regionId", region.getRegionId());
		regionMap.put("regionName", region.getRegionName());

		JSONObject result = JSONObject.fromObject(regionMap);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据当前餐厅获取相应的营业日期
	 */
	public void queryBusinessDate() throws NoPrivilegeException, SQLException, NoConnection {
		Date businessDate = branchBean.GetBranchById(branchId).getBusinessDate();
		JSONObject result = new JSONObject();
		result.put("businessDate", DateTimeUtil.getDateTime(businessDate, DateTimeUtil.DEFAULT_DATE_FORMAT));
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public void setArrivePeriodBean(ArrivePeriodBean arrivePeriodBean) {
		this.arrivePeriodBean = arrivePeriodBean;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setBranchType1(String branchType1) {
		this.branchType1 = branchType1;
	}

	public void setBranchType2(String branchType2) {
		this.branchType2 = branchType2;
	}

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

}
