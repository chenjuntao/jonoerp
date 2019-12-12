package webtest.hq.infoset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

/**
 * 外部订货方信息设置
 * 
 * @author pengwei
 * 
 */
public class OutBasicInfoSet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "外部订货方基本信息配置";

		super.resetMenu();
	}

	@Test
	public void testGridInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return dataStore.getData()==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

}
