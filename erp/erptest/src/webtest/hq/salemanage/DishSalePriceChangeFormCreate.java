package webtest.hq.salemanage;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

public class DishSalePriceChangeFormCreate extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部销售管理";
		rightTabTitle = "出品售卖价调价单生成";

		super.resetMenu();
	}

	@Test
	public void testQuery() throws Exception {
	}

}
