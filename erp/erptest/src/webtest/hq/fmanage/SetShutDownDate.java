package webtest.hq.fmanage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

import webtest.suite.BaseTestUnit;

/**
 * 测试[总部功能-->总部财务管理-->设置关账日期]模块
 * 
 * @author pengwei
 * 
 */
public class SetShutDownDate extends BaseTestUnit {
	private static final String ALERT_TEXT = "保存成功!";

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部财务管理";
		rightTabTitle = "设置关账日期";

		super.resetMenu();
	}

	/**
	 * 1. 点击下拉列表，选择关账日为5号 </br> 2. 点击保存，判断保存操作是否执行正确。如果前台显示保存成功则测试通过，否则不通过。
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {
		Select dropdown = new Select(driver.findElement(By.id("closeDate")));
		dropdown.selectByIndex(4);

		driver.findElement(By.id("saveBtn")).click();
		String alertText = tester.closeAlertAndGetItsText();

		if (ALERT_TEXT.equals(alertText)) {
			Assert.assertTrue(true);
			return;
		}

		Assert.assertTrue(false);
	}

}
