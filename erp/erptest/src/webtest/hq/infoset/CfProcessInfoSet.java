package webtest.hq.infoset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

/**
 * 测试[总部功能-->信息配置管理-->中央工厂工序信息设置]模块
 * 
 * @author pengwei
 * 
 */
public class CfProcessInfoSet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "中央工厂工序信息设置";

		super.resetMenu();
	}

	@Test
	public void testGridInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return myStore==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

}
