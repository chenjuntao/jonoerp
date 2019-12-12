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
public class Logout {
	@Autowired
	private Tester tester;

	@Test
	public void login() throws Exception {
		tester.logout();
		Assert.assertTrue(true);
	}
}
