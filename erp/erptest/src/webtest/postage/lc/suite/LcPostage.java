package webtest.postage.lc.suite;

import java.io.IOException;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;

import org.apache.log4j.Logger;
import org.junit.runner.RunWith;
import org.junit.runners.AllTests;
import org.openqa.selenium.WebDriver;

import webtest.suite.DynamicTestRun;
import webtest.util.Tester;


@RunWith(AllTests.class)
public class LcPostage {
	public static Tester tester = new Tester();
	public static WebDriver driver = tester.getDriver();
	public static Logger logger = tester.getLogger();
	private static String url = "classpath:/webtest/postage/lc/*.class";

	public static TestSuite suite() throws IOException, InterruptedException {
		login();

		TestSuite suite = new TestSuite();

		Class<?>[] clas = DynamicTestRun.suite(url);
		for (int i = 0; i < clas.length; i++) {
			suite.addTest(new JUnit4TestAdapter(clas[i]));
		}

		logger.debug("物流中心单据测试完毕");
		return suite;
	}

	private static void login() throws InterruptedException {
		tester.login("", "lc", "3456");
		Thread.sleep(3000);
		tester.clickTopMenu("物流中心");
		Thread.sleep(1000);
	}

}
