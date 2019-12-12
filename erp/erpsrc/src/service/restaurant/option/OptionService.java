/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 18, 2015 by liyzh
 * Last edited on Mar 18, 2015 by liyzh
 * 
 * 说明： 用户权限管理
 */
package service.restaurant.option;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import logic.NoConnection;
import logic.store.BranchBean;
import logic.store.DailySettleRecordBean;
import pojo.store.DailySettleRecord;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class OptionService {

	private DailySettleRecordBean dailySettleRecordBean;
	private BranchBean branchBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveData(String branchId, String menuIds, String operatorId, String operatorName)
			throws NoPrivilegeException, SQLException, NoConnection {
		dailySettleRecordBean.deleteEntity(branchId);
		String branchName = branchBean.GetBranchById(branchId).getBranchName();

		if (!TextUtil.isEmpty(menuIds)) {
			String[] menuArr = menuIds.split(",");
			Date currentDate = DateTimeUtil.getNow();
			for (String menuId : menuArr) {
				DailySettleRecord dailySettleRecord = new DailySettleRecord();
				dailySettleRecord.setBranchId(branchId);
				dailySettleRecord.setBranchName(branchName);
				dailySettleRecord.setOperatorId(operatorId);
				dailySettleRecord.setOperatingTime(currentDate);
				dailySettleRecord.setIsCurrent(1);
				dailySettleRecord.setBusinessDate(DateTimeUtil.parse(menuId));
				dailySettleRecord.setOperatorName(operatorName);
				dailySettleRecordBean.saveEntity(dailySettleRecord);
			}
		}
		OperateLogUtil.record(null, null, "非正常日结设置");
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public List<Map> queryBranchTree() throws NoPrivilegeException, SQLException, NoConnection {
		return dailySettleRecordBean.queryBranchTree();
	}

	public List<Map> queryTree(String branchId, String startDate, String endDate) throws NoPrivilegeException,
			SQLException, NoConnection {
		return dailySettleRecordBean.queryTree(branchId, startDate, endDate);
	}

	public void setDailySettleRecordBean(DailySettleRecordBean dailySettleRecordBean) {
		this.dailySettleRecordBean = dailySettleRecordBean;
	}

}
