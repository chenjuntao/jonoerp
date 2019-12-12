package webtest.hq.pmanage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

public class SupplierCycleSet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部采购管理";
		rightTabTitle = "供应商供货周期设置 ";

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

	@Test
	public void testGridInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return itemStore.data.length==0?false:true").toString());
			Assert.assertTrue(condition);
		}
	}
}
