package webtest.hq.infoset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

public class MaterialCategorySet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "原料类别设置";

		super.resetMenu();
	}

	@Test
	public void testTreeInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return treeStore.data.length==0?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

}
