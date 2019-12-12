/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package webtest.util;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * created by cjt, 2016年9月1日 说明： TanryERP项目的WebTest自动化测试公共类
 */
public class Tester {

	private String baseUrl;
	private boolean acceptNextAlert = true;
	private Logger logger;

	public String getBaseUrl() {
		return baseUrl;
	}

	private WebDriver driver;

	public StringBuffer verificationErrors = new StringBuffer();

	public Tester() {
		driver = new FirefoxDriver();
		baseUrl = "http://114.55.33.130:8081";
//		baseUrl = "http://localhost:81";
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String path = Tester.class.getResource("/").getPath();
		path = path.substring(1, path.indexOf("bin"));
		System.out.println(path+"src/log4j.properties");
		logger  =  Logger.getLogger(Tester.class );
	}

	public WebDriver getDriver() {
		return driver;
	}
	
	public Logger getLogger(){
		return logger;
	}

	/**
	 * 查询某个元素是否存在
	 * 
	 * @param by
	 */
	public boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			System.out.println("元素不存在：" + e.getMessage());
			return false;
		}
	}

	/**
	 * 弹出的提示框是否存在
	 */
	public boolean isAlertPresent() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			driver.switchTo().alert();
			return true;
		} catch (Exception e) {
			System.out.println("提示框不存在：" + e.getMessage());
			return false;
		}
	}

	/**
	 * 关闭弹出的提示框
	 */
	public String closeAlertAndGetItsText() {
		try {
			WebDriverWait wait = new WebDriverWait(driver, 2);
			wait.until(ExpectedConditions.alertIsPresent());
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}

	/**
	 * 登陆
	 */
	public boolean login(String companyId, String userName, String password) {
		driver.get(baseUrl + "/erp/jsp/login/product.jsp");
		driver.findElement(By.id("companyId")).clear();
		driver.findElement(By.id("companyId")).sendKeys(companyId);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();

		// 如果是餐厅用户，则弹出选择门店的对话框
		if (isElementPresent(By.className("dijitDialog"))) {
			driver.findElement(By.id("branchLst")).sendKeys("[02]东塘店");
			driver.findElement(By.xpath("//input[@value='确定']")).click();
		}

		// 日结提示弹出框
		if (isAlertPresent()) {
			closeAlertAndGetItsText();
		}
		return true;
	}

	/**
	 * 登陆
	 */
	public boolean loginWithoutMultiBranch(String companyId, String userName, String password) {
		driver.get(baseUrl + "/erp/jsp/login/product.jsp");
		driver.findElement(By.id("companyId")).clear();
		driver.findElement(By.id("companyId")).sendKeys(companyId);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();

		// 日结提示弹出框
		if (isAlertPresent()) {
			closeAlertAndGetItsText();
		}
		return true;
	}

	public boolean loginHead(String companyId, String userName, String password) {
		driver.get(baseUrl + "/erp/jsp/login/product.jsp");
		driver.findElement(By.id("companyId")).clear();
		driver.findElement(By.id("companyId")).sendKeys(companyId);
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(userName);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.cssSelector("input[type=\"button\"]")).click();

		return true;
	}

	/**
	 * 退出系统
	 */
	public void logout() {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");
		driver.findElement(By.id("exitSys")).click();
	}

	/**
	 * 点击顶部菜单
	 */
	public boolean clickTopMenu(String menuName) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("topFrame");

		By by = null;
		switch (menuName) {
		case "数据查询":
			by = By.id("bq");
			break;
		case "餐厅管理":
			by = By.id("restaurant");
			break;
		case "物流中心":
			by = By.id("lc");
			break;
		case "中央工厂":
			by = By.id("centralfactory");
			break;
		case "总部功能":
			by = By.id("hq");
			break;
		case "供应管理":
			by = By.id("supplier");
			break;
		case "外部订货":
			by = By.id("outerorder");
			break;
		case "系统服务":
			by = By.id("setting");
			break;
		}

		if (isElementPresent(by)) {
			driver.findElement(by).click();
			return true;
		} else {
			System.out.println("顶部菜单未找到：" + menuName);
			return false;
		}
	}

	/**
	 * 点击左侧菜单
	 * 
	 * @throws InterruptedException
	 */
	public boolean clickLeftMenu(String nodeName, String leafName) throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("navigation");

		// 查询node
		List<WebElement> allEles = driver.findElements(By.className("dijitTreeLabel"));
		for (WebElement ele : allEles) {
			if (ele.getText().equals(nodeName)) {
				// 如果节点是关闭的就打开
				if (ele.getAttribute("aria-expanded").equals("false")) {
					ele.click();
				}
				break;
			}
		}

		// 查询leaf
		allEles = driver.findElements(By.className("dijitTreeLabel"));
		for (WebElement ele : allEles) {
			if (ele.getText().equals(leafName)) {
				ele.click();
				return true;
			}
		}

		System.out.println("左侧菜单未找到：" + nodeName + leafName);
		logger.debug("左侧菜单未找到：" + nodeName + leafName);
		return false;
	}

	/**
	 * 切换到右侧Tab
	 * 
	 * @throws InterruptedException
	 */
	public boolean switchRightTab(String tabName) throws InterruptedException {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("tab");

		// 查询node
		List<WebElement> tabLables = driver.findElements(By.className("tabLabel"));
		List<WebElement> tabPages = driver.findElements(By.className("tab_iframe"));

		for (int i = 0; i < tabLables.size(); i++) {

			if (tabLables.get(i).getText().equals(tabName)) {
				tabLables.get(i).click();
				driver.switchTo().frame(tabPages.get(i - 1));
				return true;
			}
		}
		System.out.println("无法切换到不存在的右侧Tab页：" + tabName);
		logger.debug("无法切换到不存在的右侧Tab页：" + tabName);
		return false;
	}

	/**
	 * 用like切换到右侧Tab
	 */
	public boolean switchRightTabLike(String tabName) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("tab");

		// 查询node
		List<WebElement> tabLables = driver.findElements(By.className("tabLabel"));
		List<WebElement> tabPages = driver.findElements(By.className("tab_iframe"));

		for (int i = 0; i < tabLables.size(); i++) {
			if (tabLables.get(i).getText().startsWith(tabName)) {
				tabLables.get(i).click();
				driver.switchTo().frame(tabPages.get(i - 1));
				return true;
			}
		}

		System.out.println("无法切换到不存在的右侧Tab页：" + tabName);
		logger.debug("无法切换到不存在的右侧Tab页：" + tabName);
		return false;
	}

	/**
	 * 关闭右侧Tab
	 */
	public boolean closeRightTab(String tabName) {
		driver.switchTo().defaultContent();
		driver.switchTo().frame("tab");

		List<WebElement> tabLables = driver.findElements(By.className("tabLabel"));
		List<WebElement> tabCloseBtns = driver.findElements(By.xpath("//span[@title='关闭']"));

		for (int i = 0; i < tabLables.size(); i++) {
			if (tabLables.get(i).getText().equals(tabName)) {
				tabCloseBtns.get(i).click();
				return true;
			}
		}

		System.out.println("无法关闭不存在的右侧Tab页：" + tabName);
		return false;
	}
}
