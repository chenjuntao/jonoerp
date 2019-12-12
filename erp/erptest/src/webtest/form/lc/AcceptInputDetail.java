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
package webtest.form.lc;

import static webtest.form.lc.suite.LcForm.tester;
import static webtest.form.lc.suite.LcForm.driver;
import static webtest.form.lc.suite.LcForm.logger;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 说明： 
 */
public class AcceptInputDetail {
	@Before
	public void setUp() throws Exception {
	}
	
	@Test
	public void testRequire() throws Exception {
		tester.clickLeftMenu("物流中心报表管理", "物流中心验收入库明细报表");
		Thread.sleep(1000);
		tester.switchRightTab("物流中心验收入库明细报表");
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return grid.get('store').getData()==null?false:true").toString());
			if (!condition) logger.debug("物流中心验收入库明细报表grid初始化失败");
			Assert.assertTrue(condition);
		}
		
		Thread.sleep(1000);
		tester.closeRightTab("物流中心验收入库明细报表");
		Thread.sleep(1000);
	}
}
