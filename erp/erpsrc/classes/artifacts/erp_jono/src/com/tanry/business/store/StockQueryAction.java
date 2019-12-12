/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Nov 25, 2014 by liyzh
 * Last edited on Nov 25, 2014 by liyzh
 * 
 * 说明： 查询库存
 */
package com.tanry.business.store;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.StockQueryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.store.BranchStorage;
import pojo.store.ItemMeta;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;
import com.tanry.framework.util.xls.ExportUtil;

public class StockQueryAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private StockQueryBean stockQueryBean;
	private BranchStorageBean branchStorageBean;
	private BranchBean branchBean;

	private String branchId;
	private String cateIds;
	private String storageId;
	private String types;

	private String itemName;

	private List<Map> shopLst;
	private List<Map> numLst;
	private List<BranchStorage> storeLst;

	private String branchType;

	private String jsonData;
	private String type;
	private String branchFlag;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchFlag)) {
			shopLst = branchBean.queryById(getLoginBranchId());

			if (shopLst.size() > 0) {
				storeLst = branchStorageBean.query(getLoginBranchId(), branchType);
			}
		} else {
			shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
			storeLst = branchStorageBean.query("", branchType);
		}
		List<Map> tmp = new ArrayList();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "非负库存");
		map.put("types", "nonegtive");
		tmp.add(map);
		map = new HashMap<String, String>();
		map.put("name", "负库存");
		map.put("types", "negtive");
		tmp.add(map);
		map = new HashMap<String, String>();
		map.put("name", "全部库存");
		map.put("types", "all");
		tmp.add(map);
		numLst = tmp;
		return SUCCESS;
	}

	public String getType() {
		return type;
	}

	public List<Map> getNumLst() {
		return numLst;
	}

	public void setNumLst(List<Map> numLst) {
		this.numLst = numLst;
	}

	// 导出
	public void export() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchId)) {
			branchId = getLoginBranchId();// 使用当前登录人所在的门店
		}

		JSONArray arr = new JSONArray();
		if (TextUtil.isEmpty(types))
			types = "all";

		List<ItemMeta> itemLst = stockQueryBean.queryStock(cateIds, branchId, storageId, itemName, types, 0,
				Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		String sheetName = structure.getString("sheetName");

		int rownumber = 1;
		for (ItemMeta header : itemLst) {
			JSONObject json = JSONObject.fromObject(header);

			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		if ("csv".equals(type)) {
			rownumber = 1;

			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < itemLst.size(); i++) {
				ItemMeta itemMeta = itemLst.get(i);
				JSONObject json = JSONObject.fromObject(itemMeta);
				json.put("rownumber", rownumber++);
				maplist.add(json);
			}

			StringBuffer sb = CsvUtil.exportCsv(colsJA, maplist);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据多个类别查询原材料及半成品信息（含库存、供应商）
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchId)) {
			branchId = getLoginBranchId();// 使用当前登录人所在的门店
		}
		if (TextUtil.isEmpty(types))
			types = "all";
		int total = stockQueryBean.count(cateIds, branchId, storageId, itemName, types);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ItemMeta> itemLst = stockQueryBean.queryStock(cateIds, branchId, storageId, itemName, types,
					getStart(), getEnd());

			int rownumber = getStart();
			for (ItemMeta item : itemLst) {
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

	/**
	 * 根据多个类别查询原材料及半成品信息（含库存、供应商）
	 */
	public void doLcMrpQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchId)) {
			branchId = getLoginBranchId();// 使用当前登录人所在的门店
		}

		JSONArray arr = new JSONArray();
		List<ItemMeta> itemLst = stockQueryBean.queryLcMrp(cateIds, branchId, itemName);

		int rownumber = getStart();
		for (ItemMeta item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据多个类别查询原材料及半成品信息（含库存、供应商）
	 */
	public void doCfMrpQuery() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(branchId)) {
			branchId = getLoginBranchId();// 使用当前登录人所在的门店
		}

		JSONArray arr = new JSONArray();

		List<ItemMeta> itemLst = stockQueryBean.queryCfMrp(cateIds, branchId, itemName);

		int rownumber = getStart();
		for (ItemMeta item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setCateIds(String cateIds) {
		this.cateIds = cateIds;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setStockQueryBean(StockQueryBean stockQueryBean) {
		this.stockQueryBean = stockQueryBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	public String getBranchFlag() {
		return branchFlag;
	}

	public void setBranchFlag(String branchFlag) {
		this.branchFlag = branchFlag;
	}

}
