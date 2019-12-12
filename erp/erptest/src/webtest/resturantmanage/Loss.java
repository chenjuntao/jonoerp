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
public class Loss {
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
		tester.clickLeftMenu("餐厅报损管理", "餐厅原料报损单生成");
		tester.switchRightTab("餐厅原料报损单生成");
		driver.findElement(By.xpath("//input[@value='根据库存自选']")).click();
		Thread.sleep(1000);
		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
		driver.switchTo().frame(selMaterial);
		driver.findElement(By.id("itemName")).sendKeys("101001");
		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("2");
		tester.switchRightTab("餐厅原料报损单生成");
		driver.findElement(By.xpath("//input[@value='生成餐厅原料报损单']")).click();
		

		tester.switchRightTab("餐厅原料报损单生成确认");
//		driver.findElement(By.id("btn_submit")).click();
		driver.findElement(By.xpath("//input[@value='确认生成餐厅原料报损单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅报损管理", "餐厅原料报损单审核");
		tester.switchRightTab("餐厅原料报损单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核原料报损单-");
		driver.findElement(By.xpath("//input[@value='餐厅原料报损单审核通过']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(10000);
		
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅报损管理", "餐厅出品报损单生成");
		tester.switchRightTab("餐厅出品报损单生成");
		driver.findElement(By.xpath("//input[@value='选择出品']")).click();
		Thread.sleep(1000);
		WebElement selProduct = driver.findElement(By.id("ifr_selProduct"));
		driver.switchTo().frame(selProduct);
		driver.findElement(By.id("itemName")).sendKeys("31107");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("2");
		tester.switchRightTab("餐厅出品报损单生成");
		driver.findElement(By.xpath("//input[@value='生成出品报损单']")).click();
		tester.switchRightTab("餐厅出品报损单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成出品报损单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅报损管理", "餐厅出品报损单审核");
		tester.switchRightTab("餐厅出品报损单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核出品报损单-");
		driver.findElement(By.xpath("//input[@value='审核出品报损单']")).click();
		tester.switchRightTabLike("餐厅出品报损单审核确认");
		driver.findElement(By.xpath("//input[@value='确认审核出品报损单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(10000);
	}
	
	public void testRequirement(Tester tester,WebDriver driver,Logger logger) throws Exception {
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅报损管理", "餐厅原料报损单生成");
		tester.switchRightTab("餐厅原料报损单生成");
		driver.findElement(By.xpath("//input[@value='根据库存自选']")).click();
		Thread.sleep(1000);
		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
		driver.switchTo().frame(selMaterial);
		driver.findElement(By.id("itemName")).sendKeys("101001");
		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("2");
		tester.switchRightTab("餐厅原料报损单生成");
		driver.findElement(By.xpath("//input[@value='生成餐厅原料报损单']")).click();
		

		tester.switchRightTab("餐厅原料报损单生成确认");
//		driver.findElement(By.id("btn_submit")).click();
		driver.findElement(By.xpath("//input[@value='确认生成餐厅原料报损单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅报损管理", "餐厅原料报损单审核");
		tester.switchRightTab("餐厅原料报损单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查    询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核原料报损单-");
		driver.findElement(By.xpath("//input[@value='餐厅原料报损单审核通过']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(10000);
		
		/*
		 * 生成
		 */
		tester.clickLeftMenu("餐厅报损管理", "餐厅出品报损单生成");
		tester.switchRightTab("餐厅出品报损单生成");
		driver.findElement(By.xpath("//input[@value='选择出品']")).click();
		Thread.sleep(1000);
		WebElement selProduct = driver.findElement(By.id("ifr_selProduct"));
		driver.switchTo().frame(selProduct);
		driver.findElement(By.id("itemName")).sendKeys("31107");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		driver.findElement(By.xpath("//input[@value='确定']")).click();
		driver.switchTo().parentFrame();
		
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("2");
		tester.switchRightTab("餐厅出品报损单生成");
		driver.findElement(By.xpath("//input[@value='生成出品报损单']")).click();
		tester.switchRightTab("餐厅出品报损单生成确认");
		driver.findElement(By.xpath("//input[@value='确认生成出品报损单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(2000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅报损管理", "餐厅出品报损单审核");
		tester.switchRightTab("餐厅出品报损单审核");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.id("operate1")).click();
		tester.switchRightTabLike("审核出品报损单-");
		driver.findElement(By.xpath("//input[@value='审核出品报损单']")).click();
		tester.switchRightTabLike("餐厅出品报损单审核确认");
		driver.findElement(By.xpath("//input[@value='确认审核出品报损单']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(10000);
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
