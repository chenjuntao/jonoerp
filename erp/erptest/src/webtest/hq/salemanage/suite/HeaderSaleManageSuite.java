package webtest.hq.salemanage.suite;

import java.io.IOException;

import junit.framework.JUnit4TestAdapter;
import junit.framework.TestSuite;

import org.junit.runner.RunWith;
import org.junit.runners.AllTests;

import webtest.suite.DynamicTestRun;
import webtest.suite.HqLogin;
import webtest.suite.Logout;

/**
 * 测试总部功能-->总部采购管理下包含的所有子菜单
 * 
 * @author pengwei
 * 
 */
@RunWith(AllTests.class)
public class HeaderSaleManageSuite {
	private static String url = "classpath:/webtest/hq/salemanage/*.class";

	public static TestSuite suite() throws IOException, InterruptedException {

		TestSuite suite = new TestSuite();
		suite.addTest(new JUnit4TestAdapter(HqLogin.class));

		Class<?>[] clas = DynamicTestRun.suite(url);
		for (int i = 0; i < clas.length; i++) {
			suite.addTest(new JUnit4TestAdapter(clas[i]));
		}

		suite.addTest(new JUnit4TestAdapter(Logout.class));
		return suite;
	}

}
