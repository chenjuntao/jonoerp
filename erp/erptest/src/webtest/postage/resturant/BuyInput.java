/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年10月17日 by yunshucjt
 * Last edited on 2016年10月17日 by yunshu
 */
package webtest.postage.resturant;

import static org.junit.Assert.fail;

import static webtest.postage.resturant.suite.ResturantPostage.tester;
import static webtest.postage.resturant.suite.ResturantPostage.driver;
import static webtest.postage.resturant.suite.ResturantPostage.logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 说明： 
 */
public class BuyInput {

	@Before
	public void setUp() throws Exception {
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
	}
	
	@Test
	public void testRequire() throws Exception {
		tester.clickLeftMenu("餐厅单据查询", "餐厅采购入库单查询");
		Thread.sleep(1000);
		tester.switchRightTab("餐厅采购入库单查询");
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return grid.get('store').getData()==null?false:true").toString());
			if (!condition) logger.debug("餐厅采购入库单查询grid初始化失败");
			Assert.assertTrue(condition);
		}
		
		Thread.sleep(1000);
	}
	
//	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result =	tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}
}
