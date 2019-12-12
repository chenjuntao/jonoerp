package webtest.hq.pmanage;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

public class PurchasePriceFormChange extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部采购管理";
		rightTabTitle = "采购单调价";

		super.resetMenu();
	}

	@Test
	public void testSave() throws Exception {
	}

}
