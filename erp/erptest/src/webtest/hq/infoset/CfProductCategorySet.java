package webtest.hq.infoset;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

/**
 * 测试[总部功能-->信息配置管理-->中央工厂产品类别设置]模块
 * 
 * @author pengwei
 * 
 */
public class CfProductCategorySet extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "信息配置管理";
		rightTabTitle = "中央工厂产品类别设置";

		super.resetMenu();
	}

	@Test
	public void treeDataInit() throws Exception {
	}

}
