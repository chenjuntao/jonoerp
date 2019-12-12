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
package webtest.form.cf;

import static webtest.form.cf.suite.CfForm.tester;
import static webtest.form.cf.suite.CfForm.logger;
import static webtest.form.cf.suite.CfForm.driver;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 说明： 
 */
public class ProducePlanCollect {
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testRequire() throws Exception {
		tester.clickLeftMenu("报表管理", "中央工厂生产计划汇总报表");
		Thread.sleep(1000);
		tester.switchRightTab("中央工厂生产计划汇总报表");
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return grid.get('store').getData()==null?false:true").toString());
			if (!condition) logger.debug("中央工厂生产计划汇总报表grid初始化出错");
			Assert.assertTrue(condition);
		}
		
		Thread.sleep(1000);
		tester.closeRightTab("中央工厂生产计划汇总报表");
		Thread.sleep(1000);
	}
}
