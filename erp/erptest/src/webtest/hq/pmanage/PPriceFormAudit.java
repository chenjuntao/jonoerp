package webtest.hq.pmanage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

public class PPriceFormAudit extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部采购管理";
		rightTabTitle = "采购调价单审核";

		super.resetMenu();
	}

	@Test
	public void testGridInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return myStore.getData()==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

}
