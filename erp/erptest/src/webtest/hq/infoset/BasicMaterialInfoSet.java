package webtest.hq.infoset;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

/**
 * 测试[总部功能-->信息配置管理-->物料基本信息设置]模块
 * 
 * @author pengwei
 * 
 */
public class BasicMaterialInfoSet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "物料基本信息设置";

		super.resetMenu();
	}

	@Test
	public void testTreeInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return myStore==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

	@Test
	public void testItemQuery() throws Exception {
		driver.findElement(By.id("itemName")).clear();
		driver.findElement(By.id("itemName")).sendKeys("*");
		driver.findElement(By.id("dijit_form_Button_0_label")).click();

		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return myStore.getData()==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}
}
