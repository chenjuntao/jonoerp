/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 29, 2014 by cjt
 * Last edited on Sep 29, 2014 by cjt
 * 
 * 说明：  餐厅日结事务控制
 */
package service.restaurant.dailysettlement;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import logic.restapi.RestApiLogBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.DailyBranchStorageBean;
import logic.store.DailyFoodAmtBean;
import logic.store.DailyRawAmtBean;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.DailySettleRecord;

import com.tanry.business.module.hq.priceadjust.service.BillManageService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchCode;

public class DailySettlementService extends ConnectionPool {

	private DailyBranchStorageBean dailyBranchStorageBean;
	private DailyFoodAmtBean dailyFoodAmtBean;
	private DailyRawAmtBean dailyRawAmtBean;
	private BillManageService pBillManageService;
	private BranchStorageBean branchStorageBean;
	private RestApiLogBean restApiLogBean;
	public void setRestApiLogBean(RestApiLogBean restApiLogBean) {
		this.restApiLogBean = restApiLogBean;
	}
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	// 根据branchId修改营业时间
	public void settleDaily(String branchId, java.util.Date settleDate) throws NoPrivilegeException, SQLException,
			NoConnection {
		branchBean.setBusinessDate(branchId, settleDate);
	}

	// 库存日结
	public void SettleStorage(String branchId, java.util.Date settleDate, Map<String, Object> session)
			throws NoPrivilegeException, SQLException, NoConnection {
		String mainStorageId = branchStorageBean.queryMainStore(branchId).getStorageId();

		// 计算理论库存
		dailyBranchStorageBean.calcTheoryStorage(branchId, mainStorageId, settleDate);
		System.out.println("calcTheoryStorage-"
				+ DateTimeUtil.getDateTime(new Date(), DateTimeUtil.DEFAULT_DATETIME_FORMAT));
		session.put("downloadTokenValue", 1);
		// 计算实际库存
		dailyBranchStorageBean.calcActualStorage(branchId, settleDate);
		System.out.println("calcActualStorage-"
				+ DateTimeUtil.getDateTime(new Date(), DateTimeUtil.DEFAULT_DATETIME_FORMAT));
		session.put("downloadTokenValue", 2);
	}

	// 出品量日结
	public void SettleFoodAmt(String branchId, java.util.Date settleDate, Map<String, Object> session)
			throws NoPrivilegeException, SQLException, NoConnection {
		// 计算当天餐厅的营业额
		dailyFoodAmtBean.calcBusinessAmt(branchId, settleDate);
		// 计算当天餐厅的出品量
		dailyFoodAmtBean.calcFoodAmt(branchId, settleDate);
		session.put("downloadTokenValue", 3);
		System.out.println("SettleFoodAmt-"
				+ DateTimeUtil.getDateTime(new Date(), DateTimeUtil.DEFAULT_DATETIME_FORMAT));
	}

	// 用量日结
	public void SettleRawAmt(String branchId, java.util.Date settleDate, Map<String, Object> session)
			throws NoPrivilegeException, SQLException, NoConnection {
		// 计算理论用量
		dailyRawAmtBean.calcTheoryRawAmt(branchId, settleDate);
		session.put("downloadTokenValue", 5);
		System.out.println("计算理论用量" + DateTimeUtil.getDateTime(new Date(), DateTimeUtil.DEFAULT_DATETIME_FORMAT));

		// 计算实际用量
		dailyRawAmtBean.calcActualRawAmt(branchId, settleDate);
		session.put("downloadTokenValue", 7);
		System.out.println("计算实际用量" + DateTimeUtil.getDateTime(new Date(), DateTimeUtil.DEFAULT_DATETIME_FORMAT));
	}

	// 日结完成(记录日结记录，并将营业日期向前推进一天)
	// 如果是物流中心，则还要对定时生效的调价单进行推送
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void SettleComplete(DailySettleRecord settleRecord, Map<String, Object> session)
			throws NoPrivilegeException, SQLException, NoConnection {
		Date newBusinessDate = settleRecord.getBusinessDate();
		String branchId = settleRecord.getBranchId();
		session.put("downloadTokenValue", 9);
		branchBean.setBusinessDate(branchId, newBusinessDate);
		session.put("businessDate", newBusinessDate);

		// 物流中心日结时检查当日是否有需要生效的采购调价
		if (BranchCode.DEFAULT_LOGISTICSCENTER.equals(branchId)) {
			pBillManageService.CronEffect(newBusinessDate);
			
			//将传输数据的7天前的记录删除掉
			restApiLogBean.deleteByDate(newBusinessDate);
		}
	}

	public void setDailyBranchStorageBean(DailyBranchStorageBean dailyBranchStorageBean) {
		this.dailyBranchStorageBean = dailyBranchStorageBean;
	}

	public void setDailyFoodAmtBean(DailyFoodAmtBean dailyFoodAmtBean) {
		this.dailyFoodAmtBean = dailyFoodAmtBean;
	}

	public void setDailyRawAmtBean(DailyRawAmtBean dailyRawAmtBean) {
		this.dailyRawAmtBean = dailyRawAmtBean;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public void setpBillManageService(BillManageService pBillManageService) {
		this.pBillManageService = pBillManageService;
	}

}
