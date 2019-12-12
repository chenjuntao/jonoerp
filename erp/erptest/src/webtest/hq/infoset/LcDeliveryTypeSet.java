package webtest.hq.infoset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

public class LcDeliveryTypeSet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "物流中心配送模式设置";

		super.resetMenu();
	}

	@Test
	public void testItemQuery() throws Exception {
		driver.findElement(By.id("itemName")).clear();
		driver.findElement(By.id("itemName")).sendKeys("*");
		driver.findElement(By.id("queryBtn")).click();

		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return itemStore.data.length==0?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

}
