package webtest.hq.salemanage;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

public class LcRetailPriceChangeFormCreate extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部销售管理";
		rightTabTitle = "物流中心零售调价单生成";

		super.resetMenu();
	}

	@Test
	public void testQuery() throws Exception {
	}

}
