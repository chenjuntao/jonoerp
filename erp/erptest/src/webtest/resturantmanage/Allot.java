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
public class Allot {
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
		tester.clickLeftMenu("餐厅调拨管理", "餐厅调拨单生成");
		tester.switchRightTab("餐厅调拨单生成");
		driver.findElement(By.id("outBranchId")).sendKeys("[01]海东青店");;
		driver.findElement(By.id("outStorageId")).sendKeys("[01]海东青店仓库");;
		driver.findElement(By.xpath("//input[@value='根据原料自选']")).click();
		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
		driver.switchTo().frame(selMaterial);
		driver.findElement(By.id("itemName")).sendKeys("101001");
		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		tester.switchRightTab("餐厅调拨单生成");
		driver.findElement(By.xpath("//input[@value='生成调拨单']")).click();
		
		tester.switchRightTab("提交调拨单");
		driver.findElement(By.xpath("//input[@value='确认生成调拨单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.logout();
		Thread.sleep(2000);
		driver.get(tester.getBaseUrl() + "/erp/jsp/login/product.jsp");
		driver.findElement(By.id("companyId")).clear();
		driver.findElement(By.id("companyId")).sendKeys("");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("pw");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
		driver.findElement(By.id("branchLst")).sendKeys("[01]海东青店");
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		tester.clickLeftMenu("餐厅调拨管理", "餐厅调拨单审核");
		tester.switchRightTab("餐厅调拨单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核调拨单-");
		driver.findElement(By.xpath("//input[@value='调拨单确认通过']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(1000);
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅调拨管理", "餐厅调拨单生成");
		tester.switchRightTab("餐厅调拨单生成");
		driver.findElement(By.id("outBranchId")).sendKeys("[01]海东青店");;
		driver.findElement(By.id("outStorageId")).sendKeys("[01]海东青店仓库");;
		driver.findElement(By.xpath("//input[@value='根据原料自选']")).click();
		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
		driver.switchTo().frame(selMaterial);
		driver.findElement(By.id("itemName")).sendKeys("101001");
		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		tester.switchRightTab("餐厅调拨单生成");
		driver.findElement(By.xpath("//input[@value='生成调拨单']")).click();
		
		tester.switchRightTab("提交调拨单");
		driver.findElement(By.xpath("//input[@value='确认生成调拨单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.logout();
		Thread.sleep(2000);
		driver.get(tester.getBaseUrl() + "/erp/jsp/login/product.jsp");
		driver.findElement(By.id("companyId")).clear();
		driver.findElement(By.id("companyId")).sendKeys("");
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys("pw");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("admin");
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();
		driver.findElement(By.id("branchLst")).sendKeys("[01]海东青店");
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
		tester.clickLeftMenu("餐厅调拨管理", "餐厅调拨单审核");
		tester.switchRightTab("餐厅调拨单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.id("endDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		Thread.sleep(1000);
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核调拨单-");
		driver.findElement(By.xpath("//input[@value='调拨单确认通过']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		tester.logout();
		Thread.sleep(1000);
		tester.login("", "pw", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
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
