package webtest.hq.pmanage;

import org.junit.Before;
import org.junit.Test;

import webtest.suite.BaseTestUnit;

public class SupplierItemPriceRelationQuery extends BaseTestUnit {

	@Before
	public void resetMenu() throws Exception {
		leftMenuTitle = "总部采购管理";
		rightTabTitle = "供应商商品-价格对应关系查询";

		super.resetMenu();
	}

	@Test
	public void testQuery() throws Exception {
	}

}
