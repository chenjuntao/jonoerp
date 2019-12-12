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
package webtest.postage.lc;

import static org.junit.Assert.fail;
import static webtest.postage.lc.suite.LcPostage.driver;
import static webtest.postage.lc.suite.LcPostage.tester;

import static webtest.postage.lc.suite.LcPostage.logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

/**
 * 说明： 
 */
public class DistributReturn {

	@Before
	public void setUp() throws Exception {
//		tester.login("", "lc", "3456");
//		Thread.sleep(3000);
		tester.clickTopMenu("物流中心");
		Thread.sleep(1000);
	}
	
	@Test
	public void testRequire() throws Exception {
		tester.clickLeftMenu("物流中心单据查询", "物流中心配送退货单查询");
		Thread.sleep(1000);
		tester.switchRightTab("物流中心配送退货单查询");
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return grid.get('store').getData()==null?false:true").toString());
			if (!condition) logger.debug("物流中心配送单查询初始化grid出错");
			Assert.assertTrue(condition);
		}
		
		Thread.sleep(1000);
		tester.closeRightTab("物流中心配送退货单查询");
	}
	
	//@After
	public void tearDown() throws Exception {
		driver.quit();
		String result =	tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}
}
