package webtest;

import java.util.List;
import java.util.Random;

import org.junit.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.*;
import webtest.util.Tester;

public class test1 {
	private Tester tester;
	private WebDriver driver;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();

	}

	@Test
	public void testTt() throws Exception {

		//pw登陆
		tester.login("", "pw", "admin");
		
		//数据查询测试-------------------------------------------------------------
		tester.clickTopMenu("数据查询");

		//各类报表查询
		tester.clickLeftMenu("营业信息查询", "按门店付款方式查询");
		tester.switchRightTab("按门店付款方式查询");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();

		tester.clickLeftMenu("营业信息查询", "收入明细查询");
		tester.switchRightTab("收入明细查询");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		
		tester.clickLeftMenu("营业信息查询", "营业统计");
		tester.switchRightTab("营业统计");
		driver.findElement(By.id("startDate")).clear();
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		
		//总部功能测试-------------------------------------------------------------
		tester.clickTopMenu("总部功能");
		Thread.sleep(1000);
		
		tester.clickLeftMenu("总部经理分析功能", "餐厅万元用量分析");
		tester.switchRightTab("餐厅万元用量分析");
		driver.findElement(By.id("DIYwithnoModel")).click();
		WebElement selProduct = driver.findElement(By.id("ifr_selProduct"));
		driver.switchTo().frame(selProduct);
		driver.findElement(By.id("itemName")).sendKeys("01");
		driver.findElement(By.id("queryBtn")).click();
		
		driver.findElement(By.cssSelector("input[type=\"radio\"]")).click();

		driver.findElement(By.xpath("//input[@value='确定']")).click();
		//从子Frame切换到父Frame
		driver.switchTo().parentFrame();

		driver.findElement(By.xpath("//input[@value='查询']")).click();
		if(tester.isAlertPresent()){
			tester.closeAlertAndGetItsText();
		}
		
			
		//设置功能测试-------------------------------------------------------------
		tester.clickTopMenu("系统服务");
		Thread.sleep(1000);
		
		tester.clickLeftMenu("权限管理", "用户查询");
		tester.switchRightTab("用户查询");
		driver.findElement(By.id("userName")).sendKeys("周");
		driver.findElement(By.id("dijit_form_Button_0_label")).click();
		
	/*	//lc登陆
		tester.logout();
		tester.login("db_test", "lc", "3456");
		
		//物流中心功能测试---------------------------------------------------------
		tester.clickTopMenu("物流中心");
		
		tester.clickLeftMenu("物流中心基本资源查询", "物流中心基本信息查询");
		
		tester.clickLeftMenu("物流中心基本资源查询", "物流中心原料信息查询");
		tester.switchRightTab("物流中心原料信息查询");
		driver.findElement(By.id("itemName")).sendKeys("01");
		driver.findElement(By.xpath("//span[contains(text()), '查询物料']")).click();
		
		tester.clickLeftMenu("物流中心结算管理", "物流中心日结操作");
		tester.switchRightTab("物流中心日结操作");
		driver.findElement(By.xpath("//input[@value='查询']")).click();
		driver.findElement(By.xpath("//input[@value='进行日结']")).click();
		tester.switchRightTab("日结");

		
		tester.logout();*/
		
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result =	tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}
}
