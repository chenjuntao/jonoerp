/**
 * Copyright (c) 2014

 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 28, 2015 by liyzh
 * Last edited on Jan 28, 2015 by liyzh
 * 
 * 说明： 配方表增删改
 */
package com.tanry.business.module.hq.config.item;

import java.io.IOException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.TherapyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.Therapy;
import action.common.BaseAction;

import com.tanry.business.module.hq.config.item.service.TherapyManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class TherapyManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private TherapyBean therapyBean;
	private TherapyManageService therapyManageService;

	private String itemId;
	private String itemName;

	private String therapyId;
	private String therapyName;
	private String jsonData;

	private Therapy therapy;

	private String categoryId;
	private int newEnd;

	private String searchType;
	private String condition;
	private Double productPrice;
	private String change;
	private String itemDimension;

	public void setItemDimension(String itemDimension) {
		this.itemDimension = itemDimension;
	}

	public String getItemDimension() {
		return itemDimension;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(itemId)) {
			setTherapy(therapyBean.queryDetail(therapyId, itemId));
		}
		return SUCCESS;
	}

	/**
	 * 根据类别查询半成品列表，显示是否存在成本卡
	 */
	public void queryItemByCate() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLst = null;
		int total = 0;
		if (newEnd == 1) {
			newEnd = 2147483647;
		} else {
			newEnd = getEnd();
		}
		if (!TextUtil.isEmpty(searchType) && !TextUtil.isEmpty(condition)) {
			if ("product".equals(searchType)) {
				total = therapyBean.countByItemName(condition);
				setTotal(total);
				if (total > 0) {
					itemLst = therapyBean.queryByItemName(condition, getStart(), newEnd);
				}
			} else if ("raw".equals(searchType)) {
				total = therapyBean.countByRaw(condition);
				setTotal(total);
				if (total > 0) {
					itemLst = therapyBean.queryByRaw(condition, getStart(), newEnd);
				}
			}

		} else {
			total = therapyBean.countItemByCate(categoryId);
			setTotal(total);
			if (total > 0) {
				itemLst = therapyBean.queryItemByCate(categoryId, getStart(), newEnd);
			}
		}
		JSONArray arr = new JSONArray();
		int rownumber = 1;
		if (total > 0) {
			for (Map item : itemLst) {
				JSONObject json = JSONObject.fromObject(item);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		therapyManageService.saveTherapy(therapyId, therapyName, jsonData, productPrice, change);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doReplace() throws NoPrivilegeException, SQLException, NoConnection ,IOException{
		itemId = URLDecoder.decode(itemId,"UTF-8");
		itemDimension = URLDecoder.decode(itemDimension,"UTF-8");
		String sd = itemId.replaceAll(",","");
		String str = sd.replaceAll("\\s*", "");
		String itemIds="";
		String therapyName="";
//		List<Therapy> therapyList=therapyBean.GetTherapysByIds(str);
		Therapy therapy=therapyBean.queryDetailsd(str);
		if (therapy!=null){
			itemIds=therapy.getItemId();
			therapyName=therapy.getTherapyName();
		}
		if (itemIds!=null&&itemIds!=""){
			therapy.setItemId(itemIds);
			therapy.setItemName(itemDimension);
			therapyBean.updateTherapy(therapy);
		}
		JSONObject result = new JSONObject();
		result.put("therapy", therapy);
		result.put("msg", "ok");
		result.put("therapyName", therapyName);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getTherapyId() {
		return therapyId;
	}

	public void setTherapyId(String therapyId) {
		this.therapyId = therapyId;
	}

	public void setTherapyName(String therapyName) {
		this.therapyName = therapyName;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public Therapy getTherapy() {
		return therapy;
	}

	public void setTherapy(Therapy therapy) {
		this.therapy = therapy;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public void setTherapyManageService(TherapyManageService therapyManageService) {
		this.therapyManageService = therapyManageService;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public int getNewEnd() {
		return newEnd;
	}

	public void setNewEnd(int newEnd) {
		this.newEnd = newEnd;
	}

	public void setChange(String change) {
		this.change = change;
	}

}
