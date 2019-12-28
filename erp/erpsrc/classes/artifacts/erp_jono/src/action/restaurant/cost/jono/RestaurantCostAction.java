/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Apr 26, 2016 by liyzh
 * Last edited on Apr 26, 2016 by liyzh
 * 
 * 说明： 特定于金牛角王餐厅的成本分析报表，因为报表中查询或不查询某些特定类别（eg:运营物料和包材不参与本期进货计算；查询调味料损耗等）的需求暂时无法通用化
 */
package action.restaurant.cost.jono;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.reportmanage.RestaurantCostDetailBean;
import logic.reportmanage.jono.RestaurantCostBean;
import logic.store.TagHeaderBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import service.reportmanage.ReportManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.xls.ExportUtil;

public class RestaurantCostAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String branchId;

	private String tagId;
	private String tagName;

	private ReportManageService reportManageService;

	private RestaurantCostBean restaurantCostBean;
	private RestaurantCostDetailBean rRestaurantCostDetailBean;

	private TagHeaderBean tagHeaderBean;

	private String branchType;

	private String jsonData;
	private String type;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		startDate = endDate = reportManageService.getBusinessDate(branchType);

		return SUCCESS;
	}

	public String detailView() throws NoPrivilegeException, SQLException, NoConnection {
		tagName = tagHeaderBean.queryByTagId(tagId).getTagName();
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(restaurantCostBean.query(startDate, endDate,
				DateTimeUtil.addDays(startDate, -1), tagId));

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDetailQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = JSONArray.fromObject(rRestaurantCostDetailBean.query(branchId, startDate, endDate,
				DateTimeUtil.addDays(startDate, -1), tagId));

		int size = arr.size();
		int rownumber = 1;

		for (int i = 0; i < size; i++) {
			JSONObject jsonObject = arr.getJSONObject(i);
			jsonObject.put("rownumber", i == size - 1 ? "合计" : rownumber);
			rownumber++;
		}

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String[] perArr = { "actualCostPer", "beforeCostPer", "afterCostPer", "staffCostPer", "presentCostPer", "dishPer",
			"rawPer", "lossGainPer", "totalPer" };

	public void export() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> headerLst = restaurantCostBean.query(startDate, endDate, DateTimeUtil.addDays(startDate, -1), tagId);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");
		String sheetName = structure.getString("sheetName");

		JSONArray arr = JSONArray.fromObject(headerLst);

		for (Map header : headerLst) {
			Iterator iter = header.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (header.get(key) == null) {
					header.put(key, "");
				} else if (TextUtil.containStr(perArr, key)) {
					header.put(key, header.get(key).toString() + "%");
				}
			}
		}

		arr = JSONArray.fromObject(headerLst);

		if ("csv".equals(type)) {
			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < headerLst.size(); i++) {
				Map<String, Object> map = headerLst.get(i);
				maplist.add(map);
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

	public void detailExport() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> headerLst = rRestaurantCostDetailBean.query(branchId, startDate, endDate,
				DateTimeUtil.addDays(startDate, -1), tagId);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");
		String sheetName = structure.getString("sheetName");

		JSONArray arr = JSONArray.fromObject(headerLst);

		for (Map header : headerLst) {
			Iterator iter = header.keySet().iterator();
			while (iter.hasNext()) {
				String key = (String) iter.next();
				if (header.get(key) == null) {
					header.put(key, "");
				} else if (TextUtil.containStr(perArr, key)) {
					header.put(key, header.get(key).toString() + "%");
				}
			}
		}

		arr = JSONArray.fromObject(headerLst);

		int size = arr.size();
		int rownumber = 1;
		for (int i = 0; i < size; i++) {
			JSONObject jsonObject = arr.getJSONObject(i);
			jsonObject.put("rownumber", i == size - 1 ? "合计" : rownumber);
			rownumber++;
		}

		if ("csv".equals(type)) {
			List<Map<String, Object>> maplist = new ArrayList<Map<String, Object>>();
			for (int i = 0; i < headerLst.size(); i++) {
				Map<String, Object> map = headerLst.get(i);
				maplist.add(map);
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

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setRestaurantCostBean(RestaurantCostBean restaurantCostBean) {
		this.restaurantCostBean = restaurantCostBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setReportManageService(ReportManageService reportManageService) {
		this.reportManageService = reportManageService;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setrRestaurantCostDetailBean(RestaurantCostDetailBean rRestaurantCostDetailBean) {
		this.rRestaurantCostDetailBean = rRestaurantCostDetailBean;
	}

	public void setTagId(String tagId) {
		this.tagId = tagId;
	}

	public String getTagId() {
		return tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagHeaderBean(TagHeaderBean tagHeaderBean) {
		this.tagHeaderBean = tagHeaderBean;
	}

}
