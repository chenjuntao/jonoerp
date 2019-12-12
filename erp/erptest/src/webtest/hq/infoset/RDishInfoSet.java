package webtest.hq.infoset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

public class RDishInfoSet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "餐厅出品信息设置";

		super.resetMenu();
	}

	@Test
	public void testItemQuery() throws Exception {
		driver.findElement(By.id("condition")).clear();
		driver.findElement(By.id("condition")).sendKeys("1");

		driver.findElement(By.id("dijit_form_Button_0_label")).click();

		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return myStore.getData().length==0?false:true").toString());
			Assert.assertTrue(condition);
		}
	}
}
