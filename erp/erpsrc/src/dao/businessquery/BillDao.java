/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月16日 by cjt
 * Last edited on 2016年6月16日 by cjt
 * 
 * 说明： 
 */
package dao.businessquery;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import logic.NoConnection;
import logic.businessquery.BillBean;
import mapper.businessquery.BillMapper;

import org.springframework.stereotype.Repository;

import com.tanry.framework.acl.NoPrivilegeException;

import pojo.businessquery.Bill;
import dao.BaseDao;

@SuppressWarnings("rawtypes")
public class BillDao extends BaseDao {
	
	private BillMapper billMapper;
	public void setBillMapper(BillMapper billMapper) {
		this.billMapper = billMapper;
	}

	private BillBean billBean;
	public void setBillBean(BillBean billBean) {
		this.billBean = billBean;
	}

		//根据门店编号和台位编号获取门店名字和台位名字
		public Bill getBillBean(String shopC, String table)throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.getBillBean(getCom(), shopC, table);
			}else{
				return billBean.getBillBean(shopC, table);
			}
		}
		
		//指定门店的指定台位的单据数量
		public int billCountByST(String startTime,String endTime, String shopC, String table)
				throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.billCountByST(getCom(), startTime, endTime, shopC, table);
			}else{
				return billBean.billCountByST(startTime, endTime, shopC, table);
			}
		}
		
		//指定门店的指定台位的单据列表
		public List listBillByST(String startTime, String endTime, String shopC, String table, int startRow, int pageNum)
						throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.listBillByST(getCom(), startTime, endTime, shopC, table, startRow, pageNum);
			}else{
				return billBean.listBillByST(startTime, endTime, shopC, table, startRow, pageNum);
			}
		}
		
		//指定开台人和指定结账人等的账单数量
		public int billCountByMan(String startTime, String endTime, String createMan, String settleMan, String disManCur_N)
						throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.billCountByMan(getCom(), startTime, endTime, createMan, settleMan, disManCur_N);
			}else{
				return billBean.billCountByMan(startTime, endTime, createMan, settleMan, disManCur_N);
			}
		}
		
		//指定开台人和指定结账人等的账单列表
		public List listBillByMan(String startTime, String endTime, String createMan, String settleMan, String disManCur_N, int startRow, int pageNum)
						throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.listBillByMan(getCom(), startTime, endTime, createMan, settleMan, disManCur_N, startRow, pageNum);
			}else{
				return billBean.listBillByMan(startTime, endTime, createMan, settleMan, disManCur_N, startRow, pageNum);
			}
		}
		
		//指定门店的指定付款类型的单据数量
		public int billCountByPay(String startTime, String endTime, String shopC, String payType)
						throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.billCountByPay(getCom(), startTime, endTime, shopC, payType);
			}else{
				return billBean.billCountByPay(startTime, endTime, shopC, payType);
			}
		}
		
		//指定门店的指定付款类型的单据列表
		public List listBillByPay(String startTime, String endTime, String shopC, String payType, int startRow, int pageNum)
						throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.listBillByPay(getCom(), startTime, endTime, shopC, payType, startRow, pageNum);
			}else{
				return billBean.listBillByPay(startTime, endTime, shopC, payType, startRow, pageNum);
			}
		}
		
		//根据指定的单据号查询指定的单据信息
		public Bill listBillById(String id)
				throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.listBillById(getCom(), id);
			}else{
				return billBean.listBillById(id);
			}
		}
		
		//指定门店的指定单据的单据数量
		public int listBillBylikeCodeCount(String startDate,String endDate, String billC, String shopC)
						throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.listBillBylikeCodeCount(getCom(), startDate, endDate, billC, shopC);
			}else{
				return billBean.listBillBylikeCodeCount(startDate, endDate, billC, shopC);
			}
		}
		
		//指定门店的指定单据的单据信息
		public List<Bill> listBillBylikeCode(String startDate,String endDate, String billC, String shopC,int startRow, int pageNum)
					throws NoPrivilegeException, SQLException, NoConnection{
			if(beUseMySql){
				return billMapper.listBillBylikeCode(getCom(), startDate, endDate, billC, shopC, startRow, pageNum);
			}else{
				return billBean.listBillBylikeCode(startDate, endDate, billC, shopC, startRow, pageNum);
			}
		}

}
