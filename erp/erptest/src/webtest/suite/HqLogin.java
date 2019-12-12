package webtest.suite;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import webtest.util.Tester;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:config/spring.xml")
public class HqLogin {
	@Autowired
	private Tester tester;

	@Test
	public void login() throws Exception {
		tester.loginHead("", "pengwei", "admin");
		Thread.sleep(3000);
		tester.clickTopMenu("总部功能");
		Assert.assertTrue(true);
	}
}
