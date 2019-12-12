package webtest.hq.salemanage;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

public class LcWholeSalePriceChangeFormCreate extends BaseTestUnit {
	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部销售管理";
		rightTabTitle = "物流中心批发调价单生成";

		super.resetMenu();
	}

	@Test
	public void testQuery() throws Exception {
	}

}
