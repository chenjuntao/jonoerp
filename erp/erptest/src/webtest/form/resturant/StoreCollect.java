/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年10月20日 by yunshucjt
 * Last edited on 2016年10月20日 by yunshu
 */
package webtest.form.resturant;

import static webtest.form.resturant.suite.ResturantForm.driver;
import static webtest.form.resturant.suite.ResturantForm.logger;
import static webtest.form.resturant.suite.ResturantForm.tester;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 说明： 
 */
public class StoreCollect {
//	@Before
	public void setUp() throws Exception {
		tester.clickTopMenu("餐厅管理");
		Thread.sleep(1000);
	}
	
	@Test
	public void testRequire() throws Exception {
		tester.clickLeftMenu("餐厅报表管理", "餐厅仓库汇总帐");
		Thread.sleep(1000);
		tester.switchRightTab("餐厅仓库汇总帐");
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return grid.get('store').getData()==null?false:true").toString());
			if (!condition) logger.debug("餐厅仓库汇总帐grid初始化失败");
			Assert.assertTrue(condition);
		}
		
		Thread.sleep(1000);
		tester.closeRightTab("餐厅仓库汇总帐");
		Thread.sleep(1000);
	}
}
