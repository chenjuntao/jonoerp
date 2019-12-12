/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 26, 2014 by cjt
 * Last edited on Sep 26, 2014 by cjt
 * 
 * 说明：餐厅日结管理
 */

package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.CheckHeaderBean;
import logic.form.FoodBillBean;
import logic.form.InputHeaderBean;
import logic.form.LossHeaderBean;
import logic.form.PickingHeaderBean;
import logic.form.PriceAdjustHeaderBean;
import logic.form.PurchasingHeaderBean;
import logic.form.ShippingAntiauditHeaderBean;
import logic.store.BranchBean;
import logic.store.DailySettleRecordBean;
import logic.store.PriceGroupBean;
import net.sf.json.JSONObject;
import pojo.store.Branch;
import pojo.store.DailySettleRecord;
import pojo.store.PriceGroup;
import service.restaurant.dailysettlement.DailySettlementService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;

@SuppressWarnings("rawtypes")
public class DailySettlementAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private BranchBean branchBean;
	private InputHeaderBean inputHeaderBean;
	private ShippingAntiauditHeaderBean antiauditHeaderBean;
	private LossHeaderBean lossHeaderBean;
	private FoodBillBean foodBillBean;
	private DailySettlementService dailySettlementService;
	private PriceAdjustHeaderBean priceAdjustHeaderBean;
	private PriceGroupBean priceGroupBean;
	private PickingHeaderBean pickingHeaderBean;
	private CheckHeaderBean checkHeaderBean;
	private DailySettleRecordBean dailySettleRecordBean;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private Date businessDate;
	private String operatorId;
	private String operatorName;

	private Date operatingTime;
	private Date maxTime;
	private Date minDate;
	private String adjustType;
	private String queryType;

	private Date afterbusinessDate;

	private DailySettleRecord settleRecord;

	private List<Map> shopLst;

	private String branchType;
	private String branchName;

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		// shopLst = branchBean.listShop();
		shopLst = branchBean.queryById(getLoginBranchId());
		return SUCCESS;
	}

	public String setDaliyView() throws NoPrivilegeException, SQLException, NoConnection {
		// shopLst = branchBean.listShop("R_C_L");
		shopLst = branchBean.queryById(getLoginBranchId());
		businessDate = branchBean.GetBranchById(shopLst.get(0).get("code").toString()).getBusinessDate();
		operatingTime = new Date();
		afterbusinessDate = DateTimeUtil.addDays(operatingTime, -1);
		operatorId = getLoginUserId();
		operatorName = getLoginUsername();
		return SUCCESS;
	}

	public void queryDate() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(branchId).getBusinessDate();
		JSONObject result = new JSONObject();
		result.put("businessDate", DateTimeUtil.getDateStr(businessDate));
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSaveDaily() throws NoPrivilegeException, SQLException, NoConnection {
		String currentDateStr = DateTimeUtil.getDateStr(branchBean.GetBranchById((branchId)).getBusinessDate());
		// afterbusinessDate = DateTimeUtil.addDays(businessDate, -1);
		String nextDateStr = DateTimeUtil.getDateStr(afterbusinessDate);
		dailySettlementService.settleDaily(branchId, afterbusinessDate);
		OperateLogUtil.record(null, "逆日结", branchName + "进行逆日结的营业日期为" + currentDateStr + "，" + "逆日结后日期为" + nextDateStr);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("businessDate", nextDateStr);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 检查是否还存在未审核或处理的单据，这些单据没有处理完原则上是不能进行日结的
	 */
	public void checkBill() throws NoPrivilegeException, SQLException, NoConnection {
		boolean finished = true;
		int total = inputHeaderBean.count(startDate, endDate, branchId, BillStatus.UNADUIT_US, null);
		if (total > 0) {
			finished = false;
		} else {
			total = pickingHeaderBean.queryByStatusCount(startDate, queryType);

			if (total > 0) {
				finished = false;
			} else {
				total = antiauditHeaderBean
						.count(startDate, endDate, branchId, null, BillStatus.UNADUIT_US, branchType);
				if (total > 0) {
					finished = false;
				} else {
					if (total > 0) {
						finished = false;
					} else {
						total = lossHeaderBean.countByStatus(startDate, endDate, branchId, "", "RAWLOSS",
								BillStatus.UNADUIT_CN, branchType);
						if (total > 0) {
							finished = false;
						} else {
							total = lossHeaderBean.countByStatus(startDate, endDate, branchId, "", "DISHLOSS",
									BillStatus.UNADUIT_CN, branchType);
							if (total > 0) {
								finished = false;
							} else {
								endDate = DateTimeUtil.addDays(branchBean.GetBranchById(getLoginBranchId())
										.getBusinessDate(), 1);
								List<PriceGroup> priLst = priceGroupBean.queryAll();
								adjustType = "";
								for (PriceGroup priceGroup : priLst) {
									adjustType += priceGroup.getPriceGroupId() + ",";
								}
								total = priceAdjustHeaderBean.countEffect(startDate, endDate, adjustType, "unaudit");
								if (total > 0) {
									finished = false;
								} else {
									total = checkHeaderBean.count(startDate, endDate, branchId, "form", "unaudit");
									if (total > 0) {
										finished = false;
									}
								}
							}
						}
					}
				}
			}
		}

		JSONObject result = new JSONObject();
		result.put("finished", finished);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String settleView() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(branchId).getBusinessDate();
		operatingTime = new Date();
		afterbusinessDate = DateTimeUtil.addDays(businessDate, 1);
		String loginBranchId = getLoginBranchId();
		maxTime = foodBillBean.queryDate(afterbusinessDate, operatingTime, loginBranchId);// 从现在营业日期起开始查询有营业数据的日期
		settleRecord = new DailySettleRecord();
		settleRecord.setBranchId(branchId);
		Branch branch = branchBean.GetBranchById(branchId);
		settleRecord.setBranchName(branch.getBranchName());

		settleRecord.setOperatorId(getLoginUserId());
		settleRecord.setOperatorName(getLoginUsername());
		return SUCCESS;
	}

	public void settleRestaurant() throws NoPrivilegeException, SQLException, NoConnection {
		getSessionMap().put("downloadTokenValue", 0);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		businessDate = branchBean.GetBranchById(branchId).getBusinessDate();
		dailySettlementService.SettleStorage(branchId, businessDate, getSessionMap());
		dailySettlementService.SettleFoodAmt(branchId, businessDate, getSessionMap());
		dailySettlementService.SettleRawAmt(branchId, businessDate, getSessionMap());
		settleRecord.setSettleStatus("日结成功！");
		dailySettlementService.SettleComplete(settleRecord, getSessionMap());
		String branchId = settleRecord.getBranchId();
		String branchName = settleRecord.getBranchName();

		String currentDateStr = DateTimeUtil.getDateStr(businessDate);
		String nextDateStr = DateTimeUtil.getDateStr(settleRecord.getBusinessDate());

		DailySettleRecord dailySettleRecord = new DailySettleRecord();
		Date currentDate = DateTimeUtil.getNow();
		dailySettleRecord.setBranchId(branchId);
		dailySettleRecord.setBranchName(branchName);
		dailySettleRecord.setOperatorId(operatorId);
		dailySettleRecord.setOperatingTime(currentDate);
		dailySettleRecord.setIsCurrent(0);
		dailySettleRecord.setBusinessDate(businessDate);
		dailySettleRecord.setOperatorName(operatorName);
		dailySettleRecordBean.saveEntity(dailySettleRecord);
		OperateLogUtil.record(null, "日结", "[" + branchId + "]" + branchName + "进行日结的营业日期为" + currentDateStr + "，"
				+ "日结后日期为" + nextDateStr);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		getSessionMap().put("downloadTokenValue", 10);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 日结第一步，仓库库存日结完成
	public void settleStorage() throws NoPrivilegeException, SQLException, NoConnection {
		dailySettlementService.SettleStorage(branchId, businessDate, getSessionMap());

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 日结第二步，正在进行万元出品量计算
	public void settleFoodAmtTTCNY() throws NoPrivilegeException, SQLException, NoConnection {
		dailySettlementService.SettleFoodAmt(branchId, businessDate, getSessionMap());

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 日结第三步，正在进行原材料万元用量计算
	public void settleRawAmtTTCNY() throws NoPrivilegeException, SQLException, NoConnection {
		dailySettlementService.SettleRawAmt(branchId, businessDate, getSessionMap());

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 日结第四步，日结成功
	public void saveSettleRecord() throws NoPrivilegeException, SQLException, NoConnection {
		businessDate = branchBean.GetBranchById(branchId).getBusinessDate();
		settleRecord.setSettleStatus("日结成功！");
		dailySettlementService.SettleComplete(settleRecord, getSessionMap());

		String branchId = settleRecord.getBranchId();
		String branchName = settleRecord.getBranchName();
		String currentDateStr = DateTimeUtil.getDateStr(businessDate);
		Date newDate = settleRecord.getBusinessDate();
		String nextDateStr = DateTimeUtil.getDateStr(newDate);

		OperateLogUtil.record(null, "日结", "[" + branchId + "]" + branchName + "进行日结的营业日期为" + currentDateStr + "，"
				+ "日结后日期为" + nextDateStr);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public DailySettleRecord getSettleRecord() {
		return settleRecord;
	}

	public void setSettleRecord(DailySettleRecord settleRecord) {
		this.settleRecord = settleRecord;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public void setAntiauditHeaderBean(ShippingAntiauditHeaderBean antiauditHeaderBean) {
		this.antiauditHeaderBean = antiauditHeaderBean;
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
	}

	public void setDailySettlementService(DailySettlementService dailySettlementService) {
		this.dailySettlementService = dailySettlementService;
	}

	public String getOperatingTime() {
		return DateTimeUtil.getDateStr(operatingTime);
	}

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getAfterbusinessDate() {
		return DateTimeUtil.getDateStr(afterbusinessDate);
	}

	public Date getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(Date maxTime) {
		this.maxTime = maxTime;
	}

	public void setFoodBillBean(FoodBillBean foodBillBean) {
		this.foodBillBean = foodBillBean;
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
	}

	public String getAdjustType() {
		return adjustType;
	}

	public void setAdjustType(String adjustType) {
		this.adjustType = adjustType;
	}

	public String getQueryType() {
		return queryType;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public void setPriceAdjustHeaderBean(PriceAdjustHeaderBean priceAdjustHeaderBean) {
		this.priceAdjustHeaderBean = priceAdjustHeaderBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setPriceGroupBean(PriceGroupBean priceGroupBean) {
		this.priceGroupBean = priceGroupBean;
	}

	public String getOperatorId() {
		return operatorId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public void setAfterbusinessDate(Date afterbusinessDate) {
		this.afterbusinessDate = afterbusinessDate;
	}

	public String getMinDate() {
		return DateTimeUtil.getDateStr(minDate);
	}

	public void setPickingHeaderBean(PickingHeaderBean pickingHeaderBean) {
		this.pickingHeaderBean = pickingHeaderBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

	public void setDailySettleRecordBean(DailySettleRecordBean dailySettleRecordBean) {
		this.dailySettleRecordBean = dailySettleRecordBean;
	}

}
