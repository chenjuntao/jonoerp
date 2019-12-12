package webtest.hq.pmanage;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

public class SupplierItemRelationDelete extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部采购管理";
		rightTabTitle = "供应商与商品对应关系删除";

		super.resetMenu();
	}

	@Test
	public void testQuery() throws Exception {
	}

}
