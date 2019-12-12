package webtest.hq.fmanage;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;

import webtest.suite.BaseTestUnit;

/**
 * 测试[总部功能-->总部财务管理-->部门基本信息查询]模块
 * 
 * @author pengwei
 * 
 */
public class BranchBasicInfoQuery extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部财务管理";
		rightTabTitle = "部门基本信息查询";

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
}
