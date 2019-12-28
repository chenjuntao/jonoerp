/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年6月8日 by cjt
 * Last edited on 2016年6月8日 by cjt
 * 
 * 说明： BillDAO测试
 */
package test.mapper.businessquery;

import java.util.List;

import javax.annotation.Resource;

import mapper.businessquery.BillMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import pojo.businessquery.Bill;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class BillTest {
	 @Resource
	 private BillMapper billMapper;
	 
	 @Test
	 public void TestGetBillBean(){
		 System.out.println("TestGetBillBean...");
		 Bill bill = billMapper.getBillBean("jono", "10", "A11");
		 System.out.println(bill.shopC + bill.shopName + bill.table);
		 System.out.println("TestGetBillBean ok!");
	 }
	 
	 @Test
	 public void TestBillCountByST(){
		 System.out.println("TestBillCountByST...");
		 int count = billMapper.billCountByST("jono", "2016-04-08", "2016-04-30", "10", "A11");
		 System.out.println(count);
		 System.out.println("TestBillCountByST ok!");
	 }
	 
	 @Test
	 public void TestListBillByST(){
		 System.out.println("TestListBillByST...");
		 List bills = billMapper.listBillByST("jono", "2016-04-09", "2016-04-09", "10", "A11", 2, 5);
		 for (int i = 0; i < bills.size(); i++) {
			 Bill bill = (Bill)bills.get(i);
			 System.out.println(bill.billC+bill.billTime+bill.createMan+bill.shopC + bill.shopName + bill.table+bill.payAmt);
		 }
		 System.out.println("TestListBillByST ok!");
	 }
	 
	 @Test
	 public void TestBillCountByMan(){
		 System.out.println("TestBillCountByMan...");
		 int count = billMapper.billCountByMan("jono", "2016-04-08", "2016-04-30", "韩培培", null, null);
		 System.out.println("韩培培单数：" + count);
		 int count1 = billMapper.billCountByMan("jono", "2016-04-08", "2016-04-30", null, "梁影", null);
		 System.out.println("梁影单数：" + count1);
		 System.out.println("TestBillCountByMan ok!");
	 }
	 
	 @Test
	 public void TestListBillByMan(){
		 System.out.println("TestListBillByMan...");
		 List bills = billMapper.listBillByMan("jono", "2016-04-08", "2016-04-30", "韩培培", null, null, 0, 10);
		 System.out.println("韩培培：");
		 for (int i = 0; i < bills.size(); i++) {
			 Bill bill = (Bill)bills.get(i);
			 System.out.println(bill.billC+bill.billTime+bill.createMan+bill.shopC + bill.shopName + bill.table+bill.payAmt);
		 }
		 
		 List bills1 = billMapper.listBillByMan("jono", "2016-04-08", "2016-04-30", null, "梁影", null, 0, 10);
		 System.out.println("梁影：");
		 for (int i = 0; i < bills1.size(); i++) {
			 Bill bill = (Bill)bills1.get(i);
			 System.out.println(bill.billC+bill.billTime+bill.createMan+bill.shopC+bill.shopName+bill.table+bill.payAmt);
		 }
		 
		 System.out.println("TestListBillByMan ok!");
	 }
	 
	 @Test
	 public void TestBillCountByPay(){
		 System.out.println("TestBillCountByPay...");
		 int count = billMapper.billCountByPay("jono", "2016-04-08", "2016-04-30", "10", "1");
		 System.out.println("现金单数：" + count);
		 int count1 = billMapper.billCountByPay("jono", "2016-04-08", "2016-04-30", "10", "6");
		 System.out.println("支付宝单数：" + count1);
		 System.out.println("TestBillCountByPay ok!");
	 }
	 
	 @Test
	 public void TestListBillByPay(){
		 System.out.println("TestListBillByPay...");
		 List bills = billMapper.listBillByPay("jono", "2016-04-08", "2016-04-30", "10", "1", 0, 10);
		 System.out.println("现金：");
		 for (int i = 0; i < bills.size(); i++) {
			 Bill bill = (Bill)bills.get(i);
			 System.out.println(bill.billC+bill.billTime+bill.createMan+bill.shopC+bill.shopName+bill.table+bill.payAmt);
		 }
		 
		 List bills1 = billMapper.listBillByPay("jono", "2016-04-08", "2016-04-30", "10", "6", 0, 10);
		 System.out.println("支付宝：");
		 for (int i = 0; i < bills1.size(); i++) {
			 Bill bill = (Bill)bills1.get(i);
			 System.out.println(bill.billC+bill.billTime+bill.createMan+bill.shopC+bill.shopName+bill.table+bill.payAmt);
		 }
		 
		 System.out.println("TestListBillByPay ok!");
	 }
	 
	 @Test
	 public void TestListBillById(){
		 System.out.println("TestListBillById...");
		 Bill bill = billMapper.listBillById("jono", "106");
		 System.out.println(bill.billC+bill.billTime+bill.createMan+bill.shopC+bill.shopName+bill.table+bill.payAmt);
		 
		 System.out.println("TestListBillById ok!");
	 }
	 
	 @Test
	 public void TestListBillBylikeCodeCount(){
		 System.out.println("TestListBillBylikeCodeCount...");
		 int count = billMapper.listBillBylikeCodeCount("jono", "2016-04-08", "2016-04-30", "1%", "1%");
		 System.out.println(count);
		 System.out.println("TestListBillBylikeCodeCount ok!");
	 }
	 
	 @Test
	 public void TestListBillBylikeCode(){
		 System.out.println("TestListBillBylikeCode...");
		 List bills = billMapper.listBillBylikeCode("jono", "2016-04-08", "2016-04-30", "1%", "1%", 0, 100);
		 System.out.println("现金：");
		 for (int i = 0; i < bills.size(); i++) {
			 Bill bill = (Bill)bills.get(i);
			 System.out.println(bill.billC+bill.createMan+bill.shopC+bill.shopName+bill.table+bill.payAmt);
		 }
		 System.out.println("TestListBillBylikeCode ok!");
	 }
}
