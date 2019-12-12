package webtest.suite;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import webtest.util.Tester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring.xml")
public class BaseTestUnit {
	@Autowired
	protected Tester tester;

	protected WebDriver driver;

	protected String topMenuTitle;
	protected String leftMenuTitle;
	protected String rightTabTitle;

	@Before
	public void resetMenu() throws Exception {
		driver = tester.getDriver();

		Thread.sleep(1000);

		// 有些菜单第一次点击聚焦菜单，第二次单击菜单
		tester.clickLeftMenu(leftMenuTitle, rightTabTitle);
		tester.clickLeftMenu(leftMenuTitle, rightTabTitle);

		tester.switchRightTab(rightTabTitle);
	}

	@After
	public void closeTab() throws Exception {
		tester.closeRightTab(rightTabTitle);
	}

}
