/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Dec 5, 2014 by liyzh
 * Last edited on Dec 5, 2014 by liyzh
 * 
 * 说明： 物流中心入库管理
 */
package com.tanry.business.module.lc.stock.action;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.module.lc.PutinstorageBean;
import logic.restapi.WeightBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.InputDetail;
import pojo.form.Weight;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchCode;

public class StockInAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private PutinstorageBean putinstorageBean;
	private BranchStorageBean branchStorageBean;
	private WeightBean weightBean;

	public void setWeightBean(WeightBean weightBean) {
		this.weightBean = weightBean;
	}

	public WeightBean getWeightBean() {
		return weightBean;
	}

	private String formId;
	private List<BranchStorage> storeLst;
	private List<Weight> weigLst;

	public void setWeigLst(List<Weight> weigLst) {
		this.weigLst = weigLst;
	}

	public List<Weight> getWeigLst() {
		return weigLst;
	}

	private String branchType;
	private Date startDate;
	private Date endDate;
	private String deliveryType;
	private String supplierId;
	private String itemName;
	private BranchBean branchBean;
	private String logCode;
	private String weight;
	private String ss;

	public void setSs(String ss) {
		this.ss = ss;
	}

	public String getSs() {
		return ss;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getWeight() {
		return weight;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	private Date businessDate;
	private String isWelcome;

	public String getIsWelcome() {
		return isWelcome;
	}

	public void setIsWelcome(String isWelcome) {
		this.isWelcome = isWelcome;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();// 获取物流中心当前的营业时间
		endDate = DateTimeUtil.getMonthLastDay(businessDate);
		startDate = DateTimeUtil.getMonthFristDay(businessDate);
		if (!TextUtil.isEmpty(isWelcome) && isWelcome.equals("Y"))
			businessDate = endDate = startDate = null;
		return SUCCESS;
	}

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		logCode = getLoginBranchId();
		String branchType = branchBean.GetBranchById(logCode).getBranchType();
		storeLst = branchStorageBean.query(logCode, branchType);
		return SUCCESS;
	}

	/**
	 * 显示物流中心所有未入库的采购单（直配除外），以树形控件组织，日期-〉采购单
	 */
	public void loadTree() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "采购单");
		arr.add(root);
		List<Map> nodeLst = !TextUtil.isEmpty(itemName) ? putinstorageBean.queryUnStorage(startDate, endDate,
				getLoginBranchId(), itemName) : putinstorageBean.queryUnStorage(startDate, endDate, getLoginBranchId());
		for (Map node : nodeLst) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			String dtype = (String) node.get("dtype");
			if (FormConstant.CROSS_DELIVERY.equals(dtype)) {
				jsonObject.put("type", "cross");
			} else if (FormConstant.UNIFIED_DELIVERY.equals(dtype)) {
				jsonObject.put("type", "unified");
			}
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 显示中央工厂所有未入库的采购单，以树形控件组织，日期-〉采购单
	 */
	@Deprecated
	public void loadTreeCF() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "采购单");
		arr.add(root);
		List<Map> nodeLst = !TextUtil.isEmpty(itemName) ? putinstorageBean.queryCFUnStorage(startDate, endDate,
				BranchCode.DEFAULT_CENTRALFACTORY, itemName) : putinstorageBean.queryCFUnStorage(startDate, endDate,
				BranchCode.DEFAULT_CENTRALFACTORY);
		for (Map node : nodeLst) {
			JSONObject jsonObject = JSONObject.fromObject(node);
			String dtype = (String) node.get("dtype");
			if (FormConstant.CROSS_DELIVERY.equals(dtype)) {
				jsonObject.put("type", "cross");
			} else if (FormConstant.UNIFIED_DELIVERY.equals(dtype)) {
				jsonObject.put("type", "unified");
			}
			arr.add(jsonObject);
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 查询统配和直配采购单明细，用于入库，关联查询超收率
	 */
	public void queryUnifiedDetail() throws NoPrivilegeException, SQLException, NoConnection {

		String logCode = getLoginBranchId();
		List<InputDetail> detailLst = putinstorageBean.queryUnifiedDetail(formId, supplierId, logCode);
		weigLst=weightBean.selectTest();
		JSONArray arr = new JSONArray();
		List piclist=new ArrayList();
		List<InputDetail> piclists=new ArrayList();
		String pic="";
		String nums="";
		String num="";
		int rownumber = 1;
		for (InputDetail detail : detailLst) {
			System.out.println(detailLst);
				JSONObject json = JSONObject.fromObject(detail);
				Double orderCount = detail.getOrderCount();
				Double receivedCount = detail.getReceivedCount();
				if (receivedCount == null) {
					receivedCount = 0.0;
				}
				Double receiveCount = orderCount - receivedCount;
				if (receiveCount < 0) {
					receiveCount = 0.0;
				}
			    if (ss!=null){
				    System.out.println(ss);
				    if (weigLst.size()>0){
					for (int i = 0; i <weigLst.size() ; i++) {
						nums=weigLst.get(weigLst.size()-1).getNum();
						pic=weigLst.get(weigLst.size()-1).getPic();
						detailLst.get(detailLst.size()-1).setNum(nums);
						num=detailLst.get(detailLst.size()-1).getNum();
					}
				    }

			    }
			       json.put("receiveCount", receiveCount);
				   json.put("differentCount", orderCount - (receiveCount + receivedCount));
			       json.put("expiredTime", DateTimeUtil.getDateStr(detail.getExpiredTime()));
			       json.put("rownumber", rownumber);
			       json.put("pics", pic);
			       json.put("link", "<a href='#' onclick='loadGridData()'>读取</a>");
			       arr.add(json);
			       rownumber++;
			       json.put("nums", num);
			       arr.add(json);
		}
		JSONObject result = new JSONObject();
		for(int i=0;i<arr.size();i++){
			JSONObject job = arr.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			System.out.println(job);
			System.out.println(job.get("pic")+"=") ;  // 得到 每个对象中的属性值
		}
		result.put("rows", arr);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询统配和直配采购单明细，用于入库，关联查询超收率
	 */
	public void queryUnifiedDetails() throws NoPrivilegeException, SQLException, NoConnection {
        weigLst=weightBean.selectTest();
		JSONArray arr = new JSONArray();
		String itemId="";
		String pic="";
		String nums="";
		int rownumber = 1;
		for (Weight weight : weigLst) {
            JSONObject json = JSONObject.fromObject(weight);
			itemId=weight.getMyid();
            pic=weight.getPic();
			nums=weight.getNum();
			json.put("rownumber", rownumber);
            json.put("link", nums);
			json.put("itemId", itemId);
			json.put("pic", "<a href='#' onclick=''>查看</a><input type='hidden' id='sss' name='jsonDat' value='"+pic+"' />");
			json.put("pics", pic);
			arr.add(json);
			rownumber++;
			}
		JSONObject result = new JSONObject();
		for(int i=0;i<arr.size();i++){
			JSONObject job = arr.getJSONObject(i);  // 遍历 jsonarray 数组，把每一个对象转成 json 对象
			System.out.println(job);
			System.out.println(job.get("pic")+"=") ;  // 得到 每个对象中的属性值
		}
		result.put("rows", arr);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}







	/**
	 * 出库单生成，过滤掉已结案的工单对应的半成品
	 * */
	public void queryUnOrder() throws NoPrivilegeException, SQLException, NoConnection {
		String logCode = getLoginBranchId();
		List<InputDetail> detailLst = putinstorageBean.queryUnOrder(formId, supplierId, logCode);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (InputDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			Double orderCount = detail.getOrderCount();
			Double sumItemCount = detail.getSumItemCount();
			Double receivedCount = detail.getReceivedCount();
			if (receivedCount == null) {
				receivedCount = 0.0;
			}
			Double receiveCount = sumItemCount - receivedCount;
			if (receiveCount < 0) {
				receiveCount = 0.0;
			}
			json.put("receiveCount", receiveCount);
			json.put("differentCount", orderCount - sumItemCount);
			json.put("expiredTime", DateTimeUtil.getDateStr(detail.getExpiredTime()));
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		JSONObject result = new JSONObject();
		result.put("rows", arr);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询越库采购单明细，分组汇总
	 */
	public void queryCrossDetail() throws NoPrivilegeException, SQLException, NoConnection {
		List<InputDetail> detailLst = putinstorageBean.queryCrossDetail(formId, supplierId);
		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (InputDetail detail : detailLst) {
			JSONObject json = JSONObject.fromObject(detail);
			json.put("receiveCount", detail.getOrderCount());
			json.put("differentCount", 0);
			// json.put("payAmt", 0);
			json.put("expiredTime", DateTimeUtil.getDateStr(detail.getExpiredTime()));
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		JSONObject result = new JSONObject();
		result.put("rows", arr);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public List<BranchStorage> getStoreLst() {
		return storeLst;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setPutinstorageBean(PutinstorageBean putinstorageBean) {
		this.putinstorageBean = putinstorageBean;
	}

	public String getDeliveryType() {
		return deliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public String getLogCode() {
		return logCode;
	}

	public void setLogCode(String logCode) {
		this.logCode = logCode;
	}

}
