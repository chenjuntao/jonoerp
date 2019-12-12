package webtest.hq.infoset;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

/**
 * 测试[总部功能-->信息配置管理-->中央工厂生产周期设置]模块
 * 
 * @author pengwei
 * 
 */
public class CfProductCycleSet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "中央工厂生产周期设置";

		super.resetMenu();
	}

	/**
	 * 测试左边原料树是否正常加载
	 * 
	 * @throws Exception
	 */
	@Test
	public void testTreeInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return treeStore==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

	/**
	 * 测试模糊查询
	 * 
	 * @throws Exception
	 */
	@Test
	public void testItemQuery() throws Exception {
		driver.findElement(By.id("itemName")).clear();
		driver.findElement(By.id("itemName")).sendKeys("1");
		driver.findElement(By.id("queryBtn")).click();

		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return itemStore.data.length == 0 ?false:true").toString());
			Assert.assertTrue(condition);
		}
	}
}
