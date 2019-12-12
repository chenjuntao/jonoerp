package webtest.hq.fmanage;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.springframework.beans.factory.annotation.Autowired;

import webtest.hq.fmanage.dao.MonthSettleJDBCTemplate;
import webtest.suite.BaseTestUnit;
import webtest.util.DateTimeUtil;

/**
 * 测试[总部功能-->总部财务管理-->月结操作]模块
 * 
 * @author pengwei
 * 
 */
public class MonthSettleAccount extends BaseTestUnit {
	private static final String CLOSE_DATE_PRE_TEXT = "关账日为";

	@Autowired
	private MonthSettleJDBCTemplate monthSettleJDBCTemplate;

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部财务管理";
		rightTabTitle = "月结操作";

		super.resetMenu();
	}

	/**
	 * 测试表格是否初始化成功
	 * 
	 * @throws Exception
	 */
	@Test
	public void testGridInit() throws Exception {
		if (driver instanceof JavascriptExecutor) {
			Boolean condition = Boolean.valueOf(((JavascriptExecutor) driver).executeScript(
					"return dataStore.getData()==null?false:true").toString());
			Assert.assertTrue(condition);
		}
	}

	private static final String ALERT_TEXT = "月结成功！";

	/**
	 * 后台默认将所有门店的营业日期修改为关账日的那一天，然后点击进行月结
	 * 
	 * @throws Exception
	 */
	@Test
	public void testMonthSettle() throws Exception {
		tester.switchRightTab("月结操作");

		String closeDateStr = driver.findElement(By.id("closeDate")).getText();
		Integer closeDate = Integer.valueOf(closeDateStr.substring(CLOSE_DATE_PRE_TEXT.length()));

		String monthDateStr = driver.findElement(By.id("monthDate")).getAttribute("value");
		monthDateStr += "-01";

		Date businessDate = DateTimeUtil.parse(monthDateStr);
		businessDate = DateTimeUtil.addDays(businessDate, closeDate);

		monthSettleJDBCTemplate.deleteAllMonthSettleRecord();
		monthSettleJDBCTemplate.updateAllBranchDate(businessDate);

		driver.findElement(By.id("refreshBtn")).click();
		driver.findElement(By.id("btnSubmit")).click();

		// 跳转到月结的tab页面
		tester.switchRightTab("月结");
		driver.findElement(By.id("btnSubmit")).click();

		String alertText = tester.closeAlertAndGetItsText();
		if (ALERT_TEXT.equals(alertText)) {
			Assert.assertTrue(true);
			tester.closeRightTab("月结");
			return;
		}

		tester.closeRightTab("月结");
		Assert.assertTrue(false);
	}

}
