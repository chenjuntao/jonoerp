package webtest.hq.pmanage;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

public class SupplierStatusChangeRecord extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部采购管理";
		rightTabTitle = "供应商资格变更记录";

		super.resetMenu();
	}

	@Test
	public void testQuery() throws Exception {
	}

}
