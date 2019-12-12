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

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import webtest.util.Tester;


/**
 * 说明： 
 */
public class Check {
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
		// 盘点锁库
//		tester.clickLeftMenu("餐厅盘点管理", "餐厅盘点锁库");
//		tester.switchRightTab("餐厅盘点锁库");
//		driver.findElement(By.xpath("//input[@value='检    查']")).click();
//		driver.findElement(By.xpath("//input[@value='盘点锁库']")).click();
//		if (tester.isAlertPresent()) {
//			System.out.println("存在单据未审核");
//			tester.closeAlertAndGetItsText();
//			return;
//		}
//		
//		tester.switchRightTab("盘点锁库记录生成");
//		driver.findElement(By.xpath("//input[@value='选择原料类别']")).click();
//		WebElement selCate = driver.findElement(By.id("ifr_selCate"));
//		driver.switchTo().frame(selCate);
//		driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
//				
//		driver.findElement(By.xpath("//input[@value='确定']")).click();
//		// 从子Frame切换到父Frame
//		driver.switchTo().parentFrame();
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@value='生成批次号']")).click();
//		if (tester.isAlertPresent()) {
//			tester.closeAlertAndGetItsText();
//		}
//
//		// 盘点清单生成
//		tester.clickLeftMenu("餐厅盘点管理", "餐厅盘点清单生成");
//		tester.switchRightTab("餐厅盘点清单生成");
//		driver.findElement(By.xpath("//input[@value='自选原料']")).click();
//		WebElement selMaterial = driver.findElement(By.id("ifr_selMaterial"));
//		driver.switchTo().frame(selMaterial);
//		driver.findElement(By.id("itemName")).sendKeys("1111");
//		driver.findElement(By.xpath("//input[@value='查询原料']")).click();
//		driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
//		
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@value='确定']")).click();
//		// 从子Frame切换到父Frame
//		driver.switchTo().parentFrame();
//				
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@value='生成餐厅盘点清单']")).click();
//		tester.switchRightTab("餐厅盘点清单生成确认");
//		driver.findElement(By.xpath("//input[@value='确认生成盘点清单']")).click();
//		if (tester.isAlertPresent()) {
//			tester.closeAlertAndGetItsText();
//		}

		// 盘点信息输入
		tester.clickLeftMenu("餐厅盘点管理", "餐厅盘点清单信息输入");
		tester.switchRightTab("餐厅盘点清单信息输入");
		driver.findElement(By.xpath("//textarea[@class='handsontableInput']")).sendKeys("1");
		
		Actions action = new Actions(driver); 
		List<WebElement> lst = driver.findElements(By.className("htNumeric"));
		for (WebElement ele : lst){
			ele.click();
			action.doubleClick();
			driver.findElement(By.className("handsontableInput")).sendKeys("12");
			int i = 1;
			i++;
		}
		
		driver.findElement(By.xpath("//input[@value='餐厅盘点清单输入确认']")).click();
		tester.switchRightTab("餐厅盘点清单输入确认");
		driver.findElement(By.xpath("//input[@value='盘点清单输入确认']")).click();
		if (tester.isAlertPresent()) {
			tester.closeAlertAndGetItsText();
		}
//
//		// 盘点单计算生成
//		tester.clickLeftMenu("餐厅盘点管理", "餐厅盘点单计算生成");
//		tester.switchRightTab("餐厅盘点单计算生成");
//		driver.findElement(By.xpath("//input[@value='餐厅盘点单生成']")).click();
//		tester.switchRightTabLike("生成餐厅盘点单-");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@value='确认生成盘点单']")).click();
//		if (tester.isAlertPresent()) {
//			tester.closeAlertAndGetItsText();
//		}
//
//		// 盘点单审核
//		tester.clickLeftMenu("餐厅盘点管理", "餐厅盘点单审核");
//		tester.switchRightTab("餐厅盘点单审核");
//		driver.findElement(By.id("operate1")).click();
//		tester.switchRightTabLike("审核盘点单-");
//		Thread.sleep(2000);
//		driver.findElement(By.xpath("//input[@value='审核通过']")).click();
//		if (tester.isAlertPresent()) {
//			tester.closeAlertAndGetItsText();
//		}
		
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
