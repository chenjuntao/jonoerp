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
package mapper.businessquery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pojo.businessquery.Bill;

/**
 * @author cjt
 */
@SuppressWarnings("rawtypes")
public interface BillMapper {
	//根据门店编号和台位编号获取门店名字和台位名字
	public Bill getBillBean(@Param("com")String com, @Param("shopC")String shopC, @Param("tableN")String table);
	
	//指定门店的指定台位的单据数量
	public int billCountByST(@Param("com")String com, @Param("startTime")String startTime, 
			@Param("endTime")String endTime, @Param("shopC")String shopC, @Param("tableN")String table);
	
	//指定门店的指定台位的单据列表
	public List listBillByST(@Param("com")String com, @Param("startTime")String startTime, 
			@Param("endTime")String endTime, @Param("shopC")String shopC, @Param("tableN")String table, 
			@Param("startRow")int startRow, @Param("pageNum")int pageNum);
	
	//指定开台人和指定结账人等的账单数量
	public int billCountByMan(@Param("com")String com, @Param("startTime")String startTime, 
			@Param("endTime")String endTime, @Param("createMan")String createMan, 
			@Param("settleMan")String settleMan, @Param("disManCur_N")String disManCur_N);
	
	//指定开台人和指定结账人等的账单列表
	public List listBillByMan(
			@Param("com")String com, 
			@Param("startTime")String startTime, 
			@Param("endTime")String endTime, 
			@Param("createMan")String createMan, 
			@Param("settleMan")String settleMan, 
			@Param("disManCur_N")String disManCur_N, 
			@Param("startRow")int startRow, 
			@Param("pageNum")int pageNum);
	
	//指定门店的指定付款类型的单据数量
	public int billCountByPay(@Param("com")String com, @Param("startTime")String startTime, 
			@Param("endTime")String endTime, @Param("shopC")String shopC, @Param("payType")String payType);
	
	//指定门店的指定付款类型的单据列表
	public List listBillByPay(@Param("com")String com, @Param("startTime")String startTime, 
			@Param("endTime")String endTime, @Param("shopC")String shopC, @Param("payType")String payType, 
			@Param("startRow")int startRow, @Param("pageNum")int pageNum);
	
	//根据指定的单据号查询指定的单据信息
	public Bill listBillById(@Param("com")String com, @Param("id")String id);
	
	//指定门店的指定单据的单据数量
	public int listBillBylikeCodeCount(@Param("com")String com, @Param("startDate")String startDate,
			@Param("endDate")String endDate, @Param("billC")String billC, @Param("shopC")String shopC);
	
	//指定门店的指定单据的单据信息
	public List<Bill> listBillBylikeCode(@Param("com")String com, @Param("startDate")String startDate,
			@Param("endDate")String endDate, @Param("billC")String billC, @Param("shopC")String shopC,
			@Param("startRow")int startRow, @Param("pageNum")int pageNum);
}
