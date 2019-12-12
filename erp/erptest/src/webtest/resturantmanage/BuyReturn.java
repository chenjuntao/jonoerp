/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年9月22日 by yunshucjt
 * Last edited on 2016年9月22日 by yunshu
 */
package webtest.resturantmanage;

import static org.junit.Assert.fail;






import java.util.List;
import java.util.NoSuchElementException;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webtest.util.Tester;


/**
 * 说明： 
 */
public class BuyReturn {
	private Tester tester;
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();

	}
	
	public boolean doesWebElementExist(WebDriver driver, By selector)
	{ 
	        try 
	        { 
	               driver.findElement(selector); 
	               return true; 
	        } 
	        catch (Exception e) 
	        {
	                return false; 
	        } 
	}
	
	@Test
	public void testRequirement() throws Exception {
		tester.login("", "pw", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅采购退货管理", "餐厅采购退货生成");
		tester.switchRightTab("餐厅采购退货生成");
		driver.findElement(By.id("startDate")).clear();
//		driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
//		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		
		tester.switchRightTabLike("入库单退货-");
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		tester.switchRightTab("餐厅采购退货生成");
		tester.switchRightTabLike("入库单退货-LK");
		driver.findElement(By.xpath("//input[@value='生成采购退货单']")).click();
		

		tester.switchRightTab("采购退货单生成确认");
//		driver.findElement(By.id("btn_submit")).click();
		driver.findElement(By.xpath("//input[@value='确认生成采购退货单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅采购退货管理", "餐厅采购退货审核");
		tester.switchRightTab("餐厅采购退货审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核采购退货单-");
		driver.findElement(By.xpath("//input[@value='采购退货单实施']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅采购退货管理", "餐厅采购退货生成");
		tester.switchRightTab("餐厅采购退货生成");
		driver.findElement(By.id("startDate")).clear();
//		driver.findElement(By.id("startDate")).sendKeys("2016-05-21");
//		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		
		tester.switchRightTabLike("入库单退货-");
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		tester.switchRightTab("餐厅采购退货生成");
		tester.switchRightTabLike("入库单退货-LK");
		driver.findElement(By.xpath("//input[@value='生成采购退货单']")).click();
		

		tester.switchRightTab("采购退货单生成确认");
//		driver.findElement(By.id("btn_submit")).click();
		driver.findElement(By.xpath("//input[@value='确认生成采购退货单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅采购退货管理", "餐厅采购退货审核");
		tester.switchRightTab("餐厅采购退货审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核采购退货单-");
		driver.findElement(By.xpath("//input[@value='采购退货单实施']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result =	tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}

}
