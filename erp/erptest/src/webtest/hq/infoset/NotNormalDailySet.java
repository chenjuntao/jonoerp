package webtest.hq.infoset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

public class NotNormalDailySet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "非正常日结设置";

		super.resetMenu();
	}

	@Test
	public void testTreeInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return userTree==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

}
