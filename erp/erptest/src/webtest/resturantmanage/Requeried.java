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
public class Requeried {
	private Tester tester;
	private WebDriver driver;
	private Logger logger;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
		logger = tester.getLogger();
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
		tester.clickLeftMenu("餐厅需求管理", "餐厅要货生成");
		tester.switchRightTab("餐厅要货生成");
//		driver.findElement(By.xpath("//input[@text='12']")).click();
		driver.findElement(By.xpath("//input[@value='使用模板']")).click();
		WebElement pickModel = driver.findElement(By.id("ifr_pickModel"));
		driver.switchTo().frame(pickModel);
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
		driver.findElement(By.xpath("//input[@value='确定选择模板']")).click();
		driver.switchTo().parentFrame();

		WebElement ele;
		int i = 0;
		while (doesWebElementExist(driver,By.id("dijit_form_NumberTextBox_"+i))){
			ele = driver.findElement(By.id("dijit_form_NumberTextBox_"+ i++));
			ele.clear();
			ele.sendKeys("12");
		}
//		tester.switchRightTab("欢迎");
		tester.switchRightTab("餐厅要货生成");
		driver.findElement(By.id("refDateStart1")).sendKeys("2016-09-12");
		driver.findElement(By.id("refDateEnd1")).sendKeys("2016-09-22");
		driver.findElement(By.id("refDateStart2")).sendKeys("2016-09-12");
		driver.findElement(By.id("refDateEnd2")).sendKeys("2016-09-22");
		driver.findElement(By.id("refDateStart3")).sendKeys("2016-09-12");
		driver.findElement(By.id("refDateEnd3")).sendKeys("2016-09-22");
		driver.findElement(By.id("delayPredict")).sendKeys("12");

		driver.findElement(By.xpath("//input[@value='计算建议值']")).click();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//input[@value='提交要货单']")).click();
		tester.switchRightTab("餐厅要货单提交");
		driver.findElement(By.id("btn_submit")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		Thread.sleep(1000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅需求管理", "餐厅要货审核");
		tester.switchRightTab("餐厅要货审核");
		driver.findElement(By.id("operate1")).click();
		Thread.sleep(1000);
		tester.switchRightTabLike("审核要货单-");
		driver.findElement(By.xpath("//input[@value='要货单审核确认']")).click();
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
		tester.clickLeftMenu("餐厅需求管理", "餐厅要货生成");
		tester.switchRightTab("餐厅要货生成");
//		driver.findElement(By.xpath("//input[@text='12']")).click();
		try{
			driver.findElement(By.xpath("//input[@value='使用模板']")).click();
			WebElement pickModel = driver.findElement(By.id("ifr_pickModel"));
			driver.switchTo().frame(pickModel);
			driver.findElement(By.xpath("//input[@value='查询']")).click();
			driver.findElement(By.xpath("(//input[@type='radio'])[1]")).click();
			driver.findElement(By.xpath("//input[@value='确定选择模板']")).click();
			driver.switchTo().parentFrame();
		}catch(Exception E){
			logger.debug("餐厅要货生成选择模板出错");
			logger.error(E);
		}
		Thread.sleep(1000);
		WebElement ele;
		int i = 0;
		while (doesWebElementExist(driver,By.id("dijit_form_NumberTextBox_"+i))){
			ele = driver.findElement(By.id("dijit_form_NumberTextBox_"+ i++));
			ele.clear();
			ele.sendKeys("12");
		}
//		tester.switchRightTab("欢迎");
		tester.switchRightTab("餐厅要货生成");
		driver.findElement(By.id("refDateStart1")).sendKeys("2016-09-12");
		driver.findElement(By.id("refDateEnd1")).sendKeys("2016-09-22");
		driver.findElement(By.id("refDateStart2")).sendKeys("2016-09-12");
		driver.findElement(By.id("refDateEnd2")).sendKeys("2016-09-22");
		driver.findElement(By.id("refDateStart3")).sendKeys("2016-09-12");
		driver.findElement(By.id("refDateEnd3")).sendKeys("2016-09-22");
		driver.findElement(By.id("delayPredict")).sendKeys("12");

		try{
			driver.findElement(By.xpath("//input[@value='计算建议值']")).click();
		}catch (Exception E){
			logger.debug("餐厅要货生成计算建议值出错");
			logger.error(E);
		}
		Thread.sleep(5000);
		try{
			driver.findElement(By.xpath("//input[@value='提交要货单']")).click();
		}catch(Exception E){
			logger.debug("餐厅要货生成提交要货单出错");
			logger.error(E);
		}
		tester.switchRightTab("餐厅要货单提交");
		driver.findElement(By.id("btn_submit")).click();
		if(tester.isAlertPresent()){
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1){
				logger.debug("餐厅要货生成生成要货单失败");
			}
		}
		Thread.sleep(1000);
		/*
		 * 审核
		 */
		tester.clickLeftMenu("餐厅需求管理", "餐厅要货审核");
		tester.switchRightTab("餐厅要货审核");
		try{
			driver.findElement(By.id("operate1")).click();
		}catch (Exception E){
			logger.debug("餐厅要货审核要货单列表初始化失败");
			logger.error(E);
		}
		Thread.sleep(1000);
		tester.switchRightTabLike("审核要货单-");
		driver.findElement(By.xpath("//input[@value='要货单审核确认']")).click();
		if(tester.isAlertPresent()){
			if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
				logger.debug("餐厅要货审核审核要货单失败");
		}
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		logger.debug("餐厅要货完毕");
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
