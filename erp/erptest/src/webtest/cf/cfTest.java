package webtest.cf;

import static org.junit.Assert.fail;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import webtest.util.Tester;

public class cfTest {
	private WebDriver driver;
	private Tester tester;

	@Before
	public void setUp() throws Exception {
		tester = new Tester();
		driver = tester.getDriver();
	}

	public boolean doesWebElementExist(WebDriver driver, By selector) {
		try {
			driver.findElement(selector);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void testProducePlan(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 //生成计划单
		 tester.clickLeftMenu("生产需求管理", "汇总生成生产计划单");
		 tester.switchRightTab("汇总生成生产计划单");
		 try{
			 driver.findElement(By.id("startDate")).clear();
			 driver.findElement(By.id("endDate")).clear();
			 driver.findElement(By.xpath("//input[@value='查    询']")).click();
			 driver.findElement(By.xpath("//input[@value='汇总数据']")).click();
		 }catch(Exception e){
			 logger.debug("中央工厂汇总生成生产计划单数据错误");
			 logger.error(e);
		 }
		 tester.switchRightTab("汇总数据");
		 driver.findElement(By.id("submitBtn")).click();
		 tester.switchRightTab("生产计划单提交");
		 driver.findElement(By.xpath("//input[@value='生成生产计划单']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("中央工厂汇总生成生产计划单生成失败");
		 }

		 //审核计划单
		 tester.clickLeftMenu("生产需求管理", "生产计划单审核");
		 tester.switchRightTab("生产计划单审核");
		 try{
			 driver.findElement(By.id("startDate")).clear();
			 driver.findElement(By.id("endDate")).clear();
			 driver.findElement(By.xpath("//input[@value='查    询']")).click();
			 driver.findElement(By.id("operate1")).click();
		 }catch(Exception e){
			 logger.debug("中央工厂汇总生成生产计划单审核 未检查到计划单");
			 logger.error(e);
		 }
		 tester.switchRightTabLike("审核计划单-");
		 driver.findElement(By.xpath("//input[@value='审 核 通 过']")).click();
		 Thread.sleep(2000);
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("中央工厂汇总生成生产计划单审核失败");
		 }					
		 logger.debug("中央工厂汇总生成生产计划单审核完成");
	}
	
	@Test
	public void testProducePlan() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);

		 //生成计划单
		 tester.clickLeftMenu("生产需求管理", "汇总生成生产计划单");
		 tester.switchRightTab("汇总生成生产计划单");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.id("endDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.xpath("//input[@value='汇总数据']")).click();
		 tester.switchRightTab("汇总数据");
		 driver.findElement(By.id("submitBtn")).click();
		 tester.switchRightTab("生产计划单提交");
		 driver.findElement(By.xpath("//input[@value='生成生产计划单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 //审核计划单
		 tester.clickLeftMenu("生产需求管理", "生产计划单审核");
		 tester.switchRightTab("生产计划单审核");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.id("endDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核计划单-");
		 driver.findElement(By.xpath("//input[@value='审 核 通 过']")).click();
	}

	@Test
	public void testItemBuy() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 
		 //原料采购单生成
		 tester.clickLeftMenu("生产需求管理", "原料采购单生成");
		 tester.switchRightTab("原料采购单生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.xpath("//input[@value='汇总订货数据']")).click();
		 tester.switchRightTab("汇总订货数据");
		 Thread.sleep(3000);
		 driver.findElement(By.id("refDateStart1")).sendKeys("2016-09-13");
		 Thread.sleep(1000);
		 driver.findElement(By.id("refDateEnd1")).sendKeys("2016-09-20");
		 Thread.sleep(1000);
		 driver.findElement(By.id("refDateStart2")).sendKeys("2016-09-01");
		 Thread.sleep(1000);
		 driver.findElement(By.id("refDateEnd2")).sendKeys("2016-09-30");
		 Thread.sleep(1000);
		 driver.findElement(By.id("refDateStart3")).sendKeys("2016-09-01");
		 Thread.sleep(1000);
		 driver.findElement(By.id("refDateEnd3")).sendKeys("2016-09-13");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='计算建议值']")).click();
		 driver.findElement(By.id("btn_submit")).click();
		 tester.switchRightTab("采购单生成");
		 driver.findElement(By.xpath("//input[@value='确认生成采购单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	
	public void testItemBuy(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 //原料采购单生成
		 tester.clickLeftMenu("生产需求管理", "原料采购单生成");
		 tester.switchRightTab("原料采购单生成");
		 try{
			 driver.findElement(By.xpath("//input[@value='查    询']")).click();
			 driver.findElement(By.xpath("//input[@value='汇总订货数据']")).click();
		 }catch(Exception e){
			 logger.debug("原料采购单生成 没前置单据");
			 logger.error(e);
		 }
		 tester.switchRightTab("汇总订货数据");
		 Thread.sleep(3000);
		 driver.findElement(By.id("refDateStart1")).sendKeys("2016-09-13");
		 driver.findElement(By.id("refDateEnd1")).sendKeys("2016-09-20");
		 driver.findElement(By.id("refDateStart2")).sendKeys("2016-09-01");
		 driver.findElement(By.id("refDateEnd2")).sendKeys("2016-09-30");
		 driver.findElement(By.id("refDateStart3")).sendKeys("2016-09-01");
		 driver.findElement(By.id("refDateEnd3")).sendKeys("2016-09-13");
		 try{
			 driver.findElement(By.xpath("//input[@value='计算建议值']")).click();
		 }catch (Exception e){
			 logger.debug("原料采购单生成计算建议值出错");
			 logger.error(e);
		 }
		 Thread.sleep(3000);
		 driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		 tester.switchRightTab("采购单生成");
		 driver.findElement(By.xpath("//input[@value='确认生成采购单']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("原料采购单生成失败");
		 }
	}
	
	@Test
	public void testUnaiBuy() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 //手动生成采购单
		 tester.clickLeftMenu("生产需求管理", "手动生成采购单");
		 tester.switchRightTab("手动生成采购单");
		 driver.findElement(By.xpath("//input[@value='选择供应商']")).click();
		 WebElement selSupplier =
		 driver.findElement(By.id("ifr_selSupplier"));
		 driver.switchTo().frame(selSupplier);
		 driver.findElement(By.id("supplierName")).sendKeys("0000");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.cssSelector("input[type=\"radio\"]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='自选原料']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("001");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确    定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 tester.switchRightTab("生成采购单");
		 driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testUnaiBuy(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 //手动生成采购单
		 tester.clickLeftMenu("生产需求管理", "手动生成采购单");
		 tester.switchRightTab("手动生成采购单");
		 driver.findElement(By.xpath("//input[@value='选择供应商']")).click();
		 WebElement selSupplier =
		 driver.findElement(By.id("ifr_selSupplier"));
		 driver.switchTo().frame(selSupplier);
		 driver.findElement(By.id("supplierName")).sendKeys("0000");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.cssSelector("input[type=\"radio\"]")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='自选原料']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("001");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确    定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 tester.switchRightTab("生成采购单");
		 driver.findElement(By.xpath("//input[@value='生成采购单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	@Test
	public void testBuyAudit() throws Exception {
			// 央厂cf登陆
			tester.login("", "cf", "3456");

			// 中央工厂测试-------------------------------------------------------------
			tester.clickTopMenu("中央工厂");
			 Thread.sleep(100);
		 //采购单审核
		 tester.clickLeftMenu("生产需求管理", "原料采购单审核");
		 tester.switchRightTab("原料采购单审核");
		 if (doesWebElementExist(driver, By.id("lodopWarning"))) {
		 driver.findElement(By.id("lodopWarning"));
		 driver.findElement(By.className("dijitDialogTitleBar"));
		 driver.findElement(By.className("dijitDialogCloseIcon")).click();
		 tester.switchRightTab("采购单审核");
		 }
		 driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
		 WebElement ele;
		 int i = 0;
		 while (doesWebElementExist(driver,
		 By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
		 ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++
		 + "']/div"));
		 if (ele.getText().startsWith("TP")||ele.getText().startsWith("CG")) {
		 ele.click();
		 break;
		 }
		 }
		 //
		 driver.findElement(By.xpath("//div[@class='dijitTreeNodeContainer']/div")).click();
		 // Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='审    核']")).click();
		 tester.switchRightTabLike("审核采购单-");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确认审核']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testBuyAudit(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 //采购单审核
		 tester.clickLeftMenu("生产需求管理", "原料采购单审核");
		 tester.switchRightTab("原料采购单审核");
//		 if (doesWebElementExist(driver, By.id("lodopWarning"))) {
//			 driver.findElement(By.id("lodopWarning"));
//			 driver.findElement(By.className("dijitDialogTitleBar"));
//			 driver.findElement(By.className("dijitDialogCloseIcon")).click();
//			 	tester.switchRightTab("采购单审核");
//			 }
		 WebElement ele;
		 int i = 0;
		 try{
			 driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
			 while (doesWebElementExist(driver,By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
				 ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i+++ "']/div"));
				 if (ele.getText().startsWith("TP")||ele.getText().startsWith("CG")) {
					 ele.click();
					 break;
				 }
			 }
			 //
	//		 driver.findElement(By.xpath("//div[@class='dijitTreeNodeContainer']/div")).click();
			 // Thread.sleep(1000);
			 driver.findElement(By.xpath("//input[@value='审    核']")).click();
		 }catch (Exception e){
			 logger.debug("原料采购单审核 没采购单");
			 logger.error(e);
		 }
		 tester.switchRightTabLike("审核采购单-");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确认审核']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("原料采购单审核失败");
		 }
		 logger.debug("原料采购单生成完毕");
	}
	
	
	@Test
	public void testAchieve() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 领料单生成
		 tester.clickLeftMenu("生产管理", "领料单生成");
		 tester.switchRightTab("领料单生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("生成领料单-");
		 driver.findElement(By.xpath("//input[@value='领料单生成']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testAchieve(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 // 领料单生成
		 tester.clickLeftMenu("生产管理", "领料单生成");
		 tester.switchRightTab("领料单生成");
		 try{
			 driver.findElement(By.xpath("//input[@value='查    询']")).click();
			 driver.findElement(By.id("operate1")).click();
		 }catch(Exception e){
			 logger.debug("中央工厂领料单生成无前置单据");
			 logger.error(e);
		 }
		 tester.switchRightTabLike("生成领料单-");
		 driver.findElement(By.xpath("//input[@value='领料单生成']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("中央工厂领料单生成失败");
		 }
		 logger.debug("中央工厂领料单生成完成");
	}

	@Test
	public void testBuyInput() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 /*
		 * 生成采购入库单
		 */
		 tester.clickLeftMenu("库存基础管理", "采购入库单生成");
		 tester.switchRightTab("采购入库单生成");
		 if (doesWebElementExist(driver, By.id("lodopWarning"))) {
		 driver.findElement(By.id("lodopWarning"));
		 driver.findElement(By.className("dijitDialogTitleBar"));
		 driver.findElement(By.className("dijitDialogCloseIcon")).click();
		 tester.switchRightTab("采购入库单生成");
		 }
		 driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
		 WebElement ele;
		 int i = 0;
		 while (doesWebElementExist(driver,
		 By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
		 ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++
		 + "']/div"));
		 if (ele.getText().startsWith("CG")) {
		 ele.click();
		 break;
		 }
		 }
		 //
		 driver.findElement(By.xpath("//div[@class='dijitTreeNodeContainer']/div")).click();
		 // Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='根据采购单入库']")).click();
		 tester.switchRightTabLike("采购单入库-");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 tester.switchRightTab("入库单生成确认");
		 driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 Thread.sleep(2000);

		 /*
		 * 审核采购入库单
		 */
		 tester.clickLeftMenu("库存基础管理", "采购入库单审核");
		 tester.switchRightTab("采购入库单审核");
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核入库单-");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='入库单审核确认']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testBuyInput(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 /*
		 * 生成采购入库单
		 */
		 tester.clickLeftMenu("库存基础管理", "采购入库单生成");
		 tester.switchRightTab("采购入库单生成");
//		 if (doesWebElementExist(driver, By.id("lodopWarning"))) {
//			 driver.findElement(By.id("lodopWarning"));
//			 driver.findElement(By.className("dijitDialogTitleBar"));
//			 driver.findElement(By.className("dijitDialogCloseIcon")).click();
//			 tester.switchRightTab("采购入库单生成");
//		 }
		 WebElement ele;
		 int i = 0;
		 try{
			 driver.findElement(By.xpath("//div[@id='dijit__TreeNode_1']/div")).click();
			 while (doesWebElementExist(driver,By.xpath("//div[@id='dijit__TreeNode_" + i + "']/div"))) {
				 ele = driver.findElement(By.xpath("//div[@id='dijit__TreeNode_" + i++ + "']/div"));
				 if (ele.getText().startsWith("CG")) {
					 ele.click();
					 break;
				 }
			 }
			 //
	//		 driver.findElement(By.xpath("//div[@class='dijitTreeNodeContainer']/div")).click();
			 // Thread.sleep(1000);
			 driver.findElement(By.xpath("//input[@value='根据采购单入库']")).click();
		 }catch(Exception e){
			 logger.debug("中央工厂采购入库单生成 无采购单");
			 logger.error(e);
		 }
		 tester.switchRightTabLike("采购单入库-");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		 if (tester.isAlertPresent()) {
			 tester.closeAlertAndGetItsText();
		 }
		 tester.switchRightTab("入库单生成确认");
		 driver.findElement(By.xpath("//input[@value='确认生成入库单']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("中央工厂采购入库单生成失败");
		 }
		 Thread.sleep(2000);

		 /*
		 * 审核采购入库单
		 */
		 tester.clickLeftMenu("库存基础管理", "采购入库单审核");
		 tester.switchRightTab("采购入库单审核");
		 try{
			 driver.findElement(By.id("operate1")).click();
			 tester.switchRightTabLike("审核入库单-");
		 }catch(Exception e){
			 logger.debug("采购入库单审核 未生成入库单");
			 logger.error(e);
		 }
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='入库单审核确认']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("采购入库单审核失败");
		 }
		 if (tester.isAlertPresent()) {
			 tester.closeAlertAndGetItsText();
		 }
		 logger.debug("采购入库单生成完成");
	}

	@Test
	public void testBatchAchieve() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 //批量领料单生成
		 tester.clickLeftMenu("生产管理", "领料单批量生成");
		 tester.switchRightTab("领料单批量生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.xpath("//input[@value='批 量 领 料']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testBatchAchieve(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 //批量领料单生成
		 tester.clickLeftMenu("生产管理", "领料单批量生成");
		 tester.switchRightTab("领料单批量生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.xpath("//input[@value='批 量 领 料']")).click();
		 if (tester.isAlertPresent()) {
			 tester.closeAlertAndGetItsText();
		 }
	}

	@Test
	public void testAchieveAudit() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 领料单审核
		 tester.clickLeftMenu("库存基础管理", "生产领料审核");
		 tester.switchRightTab("生产领料审核");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核生产领料单-");
		 driver.findElement(By.xpath("//input[@value='确认领料']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testAchieveAudit(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 // 领料单审核
		 tester.clickLeftMenu("库存基础管理", "生产领料审核");
		 tester.switchRightTab("生产领料审核");
		 try{
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 }catch(Exception e){
			 logger.debug("生产领料审核 未生成单据");
			 logger.error(e);
		 }
		 tester.switchRightTabLike("审核生产领料单-");
		 driver.findElement(By.xpath("//input[@value='确认领料']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("生产领料审核失败");
		 }
		 logger.debug("生产领料审核完成");
	}

	@Test
	public void testOverAchieve() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 超领单生成
		 tester.clickLeftMenu("生产管理", "超领单生成");
		 tester.switchRightTab("超领单生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTab("生成超领单");
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='超领单生成']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 生产超领审核
		 tester.clickLeftMenu("库存基础管理", "生产超领审核");
		 tester.switchRightTab("生产超领审核");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("超领单审核");
		 driver.findElement(By.xpath("//input[@value='确认领料']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testOverAchieve(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 // 超领单生成
		 tester.clickLeftMenu("生产管理", "超领单生成");
		 tester.switchRightTab("超领单生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTab("生成超领单");
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("10");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='超领单生成']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 生产超领审核
		 tester.clickLeftMenu("库存基础管理", "生产超领审核");
		 tester.switchRightTab("生产超领审核");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("超领单审核");
		 driver.findElement(By.xpath("//input[@value='确认领料']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	@Test
	public void testReturn() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);

		 // 退料单生成
		 tester.clickLeftMenu("生产管理", "产品入库单生成");
		 tester.switchRightTab("产品入库单生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("return1")).click();
		 tester.switchRightTab("退料");
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='退料单生成']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 生产退料审核
		 tester.clickLeftMenu("库存基础管理", "生产退料审核");
		 tester.switchRightTab("生产退料审核");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("退料审核");
		 driver.findElement(By.xpath("//input[@value='确认退料']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	public void testReturn(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 // 退料单生成
		 tester.clickLeftMenu("生产管理", "产品入库单生成");
		 tester.switchRightTab("产品入库单生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("return1")).click();
		 tester.switchRightTab("退料");
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("13");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='退料单生成']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 生产退料审核
		 tester.clickLeftMenu("库存基础管理", "生产退料审核");
		 tester.switchRightTab("生产退料审核");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("退料审核");
		 driver.findElement(By.xpath("//input[@value='确认退料']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	@Test
	public void testItemInput() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 产品入库单生成
		 tester.clickLeftMenu("生产管理", "产品入库单生成");
		 tester.switchRightTab("产品入库单生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='批 量 入 库']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 产品入库审核
		 tester.clickLeftMenu("库存基础管理", "产品入库审核");
		 tester.switchRightTab("产品入库审核");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核入库单-");
		 driver.findElement(By.xpath("//input[@value='确认入库']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	public void testItemInput(Tester tester,WebDriver driver, Logger logger) throws Exception {
		 // 产品入库单生成
		 tester.clickLeftMenu("生产管理", "产品入库单生成");
		 tester.switchRightTab("产品入库单生成");
		 try{
			 driver.findElement(By.id("startDate")).clear();
			 driver.findElement(By.id("startDate")).sendKeys("2016-09-18");
			 driver.findElement(By.xpath("//input[@value='查    询']")).click();
			 Thread.sleep(2000);
			 driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
			 driver.findElement(By.xpath("//input[@value='批 量 入 库']")).click();
		 }catch(Exception e){
			 logger.debug("产品入库单生成 无前置单据");
			 logger.error(e);
		 }
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("产品入库单生成失败");
		 }
		 Thread.sleep(1000);

		 // 产品入库审核
		 tester.clickLeftMenu("库存基础管理", "产品入库审核");
		 tester.switchRightTab("产品入库审核");
		 try{
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 }catch(Exception e){
			 logger.debug("产品入库单审核 无产品入库单");
			 logger.error(e);
		 }
		 tester.switchRightTabLike("审核入库单-");
		 driver.findElement(By.xpath("//input[@value='确认入库']")).click();
		 if (tester.isAlertPresent()) {
			 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
					logger.debug("产品入库单审核失败");
		 }
		 logger.debug("产品入库单生成完成");
	}
	
	@Test
	public void testItemOutput() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 产品出库单生成
		 tester.clickLeftMenu("库存基础管理", "产品出库单生成");
		 tester.switchRightTab("产品出库单生成");
		 driver.findElement(By.id("startDate")).sendKeys("2016-09-15");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("产品出库单生成-");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='提交出货单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 产品出库单审核
		 tester.clickLeftMenu("库存基础管理", "产品出库单审核");
		 tester.switchRightTab("产品出库单审核");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核出库单");
		 driver.findElement(By.xpath("//input[@value='审核通过']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testItemOutput(Tester tester,WebDriver driver, Logger logger) throws Exception {
		// 产品出库单生成
				 tester.clickLeftMenu("库存基础管理", "产品出库单生成");
				 tester.switchRightTab("产品出库单生成");
				 try{
					 driver.findElement(By.id("startDate")).clear();
					 driver.findElement(By.id("startDate")).sendKeys("2016-09-18");
					 driver.findElement(By.xpath("//input[@value='查    询']")).click();
					 driver.findElement(By.id("operate1")).click();
				 }catch(Exception e){
					 logger.debug("产品出库单生成 无前置单据");
					 logger.error(e);
				 }
				 Thread.sleep(2000);
				 tester.switchRightTabLike("产品出库单生成-");
				 Thread.sleep(2000);
				 driver.findElement(By.xpath("//input[@value='提交出货单']")).click();
				 if (tester.isAlertPresent()) {
					 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
							logger.debug("产品出库单生成失败");
				 }

				 // 产品出库单审核
				 tester.clickLeftMenu("库存基础管理", "产品出库单审核");
				 tester.switchRightTab("产品出库单审核");
				 try{
					 driver.findElement(By.xpath("//input[@value='查    询']")).click();
					 driver.findElement(By.id("operate1")).click();
				 }catch(Exception e){
					 logger.debug("产品出库单审核 无出库单");
					 logger.error(e);
				 }
				 tester.switchRightTabLike("审核出库单");
				 driver.findElement(By.xpath("//input[@value='审核通过']")).click();
				 if (tester.isAlertPresent()) {
					 if (tester.closeAlertAndGetItsText().indexOf("失败")!=-1)
							logger.debug("产品出库单审核失败");
				 }
				 logger.debug("产品出库单生成完成");
	}

	@Test
	public void testItemLoss() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 原料报损单生成
		 tester.clickLeftMenu("报损管理", "原料报损单生成");
		 tester.switchRightTab("原料报损单生成");
		 driver.findElement(By.xpath("//input[@value='根据库存自选']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("201111");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='生成中央工厂原料报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 tester.switchRightTab("中央工厂原料报损单生成确认");
		 driver.findElement(By.xpath("//input[@value='确认生成中央工厂原料报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 原料报损单审核
		 tester.clickLeftMenu("报损管理", "原料报损单审核");
		 tester.switchRightTab("原料报损单审核");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核原料报损单-");
		 driver.findElement(By.xpath("//input[@value='中央工厂原料报损单审核通过']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testItemLoss(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 // 原料报损单生成
		 tester.clickLeftMenu("报损管理", "原料报损单生成");
		 tester.switchRightTab("原料报损单生成");
		 driver.findElement(By.xpath("//input[@value='根据库存自选']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("201111");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='生成中央工厂原料报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 tester.switchRightTab("中央工厂原料报损单生成确认");
		 driver.findElement(By.xpath("//input[@value='确认生成中央工厂原料报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 原料报损单审核
		 tester.clickLeftMenu("报损管理", "原料报损单审核");
		 tester.switchRightTab("原料报损单审核");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核原料报损单-");
		 driver.findElement(By.xpath("//input[@value='中央工厂原料报损单审核通过']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	@Test
	public void testFoodLoss() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 出品报损单生成
		 tester.clickLeftMenu("报损管理", "出品报损单生成");
		 tester.switchRightTab("出品报损单生成");
		 driver.findElement(By.xpath("//input[@value='选择出品']")).click();
		 WebElement selProduct = driver.findElement(By.id("ifr_selProduct"));
		 driver.switchTo().frame(selProduct);
		 driver.findElement(By.id("itemName")).sendKeys("111107");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='生成出品报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 tester.switchRightTab("中央工厂出品报损单生成确认");
		 driver.findElement(By.xpath("//input[@value='确认生成出品报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 出品报损单审核
		 tester.clickLeftMenu("报损管理", "出品报损单审核");
		 tester.switchRightTab("出品报损单审核");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核出品报损单-");
		 driver.findElement(By.xpath("//input[@value='审核出品报损单']")).click();
		 tester.switchRightTabLike("中央工厂出品报损单审核确认");
		 driver.findElement(By.xpath("//input[@value='确认审核出品报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testFoodLoss(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 // 出品报损单生成
		 tester.clickLeftMenu("报损管理", "出品报损单生成");
		 tester.switchRightTab("出品报损单生成");
		 driver.findElement(By.xpath("//input[@value='选择出品']")).click();
		 WebElement selProduct = driver.findElement(By.id("ifr_selProduct"));
		 driver.switchTo().frame(selProduct);
		 driver.findElement(By.id("itemName")).sendKeys("111107");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 // 从子Frame切换到父Frame
		 driver.switchTo().parentFrame();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(2000);
		 driver.findElement(By.xpath("//input[@value='生成出品报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 tester.switchRightTab("中央工厂出品报损单生成确认");
		 driver.findElement(By.xpath("//input[@value='确认生成出品报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 出品报损单审核
		 tester.clickLeftMenu("报损管理", "出品报损单审核");
		 tester.switchRightTab("出品报损单审核");
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核出品报损单-");
		 driver.findElement(By.xpath("//input[@value='审核出品报损单']")).click();
		 tester.switchRightTabLike("中央工厂出品报损单审核确认");
		 driver.findElement(By.xpath("//input[@value='确认审核出品报损单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	@Test
	public void testBuyReturn() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 // 采购退货管理
		 tester.clickLeftMenu("采购退货管理", "采购退货生成");
		 tester.switchRightTab("采购退货生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("入库单退货-");
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 driver.findElement(By.xpath("//input[@value='生成采购退货单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 采购退货管理
		 tester.clickLeftMenu("采购退货管理", "采购退货");
		 tester.switchRightTab("采购退货");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核采购退货单-");
		 driver.findElement(By.xpath("//input[@value='采购退货单实施']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testBuyReturn(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 // 采购退货管理
		 tester.clickLeftMenu("采购退货管理", "采购退货生成");
		 tester.switchRightTab("采购退货生成");
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("入库单退货-");
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 driver.findElement(By.xpath("//input[@value='生成采购退货单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

		 // 采购退货管理
		 tester.clickLeftMenu("采购退货管理", "采购退货");
		 tester.switchRightTab("采购退货");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查    询']")).click();
		 driver.findElement(By.id("operate1")).click();
		 tester.switchRightTabLike("审核采购退货单-");
		 driver.findElement(By.xpath("//input[@value='采购退货单实施']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

		// // 盘点锁库t
		// tester.clickLeftMenu("盘点管理", "盘点锁库");
		// tester.switchRightTab("盘点锁库");
		// driver.findElement(By.xpath("//input[@value='进行盘点锁库']")).click();
		// tester.switchRightTab("盘点锁库记录生成");
		// driver.findElement(By.xpath("//input[@value='选择原料类别']")).click();
		// WebElement selCate = driver.findElement(By.id("ifr_selCate"));
		// driver.switchTo().frame(selCate);
		// driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		//
		// driver.findElement(By.xpath("//input[@value='确定']")).click();
		// // 从子Frame切换到父Frame
		// driver.switchTo().parentFrame();
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@value='确认进行盘点锁库']")).click();
		// if (tester.isAlertPresent()) {
		// tester.closeAlertAndGetItsText();
		// }

		// // 盘点清单生成
		// tester.clickLeftMenu("盘点管理", "盘点清单生成");
		// tester.switchRightTab("盘点清单生成");
		// driver.findElement(By.xpath("//input[@value='自选原料']")).click();
		// WebElement selMaterial =
		// driver.findElement(By.id("ifr_selMaterial"));
		// driver.switchTo().frame(selMaterial);
		// driver.findElement(By.id("itemName")).sendKeys("1111");
		// driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		// driver.findElement(By.cssSelector("input[type=\"checkbox\"]")).click();
		//
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@value='确定']")).click();
		// // 从子Frame切换到父Frame
		// driver.switchTo().parentFrame();
		//
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@value='生成盘点清单']")).click();
		// tester.switchRightTab("盘点清单生成确认");
		// driver.findElement(By.xpath("//input[@value='确认生成盘点清单']")).click();
		// if (tester.isAlertPresent()) {
		// tester.closeAlertAndGetItsText();
		// }

		// 盘点信息输入
		// tester.clickLeftMenu("盘点管理", "盘点信息输入");
		// tester.switchRightTab("盘点信息输入");
		//
		// driver.findElement(By.className("handsontableInput")).sendKeys("10");
		// driver.findElement(By.xpath("//input[@value='餐厅盘点清单输入确认']")).click();
		// tester.switchRightTab("餐厅盘点清单输入确认");
		// driver.findElement(By.xpath("//input[@value='盘点清单输入确认']")).click();

		// // 盘点单计算生成
		// tester.clickLeftMenu("盘点管理", "盘点单计算生成");
		// tester.switchRightTab("盘点单计算生成");
		// driver.findElement(By.xpath("//input[@value='盘点单生成']")).click();
		// tester.switchRightTabLike("生成盘点单-");
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@value='确认生成盘点单']")).click();
		// if (tester.isAlertPresent()) {
		// tester.closeAlertAndGetItsText();
		// }

		// // 盘点单审核
		// tester.clickLeftMenu("盘点管理", "盘点单审核");
		// tester.switchRightTab("盘点单审核");
		// driver.findElement(By.id("operate1")).click();
		// tester.switchRightTabLike("审核盘点单-");
		// Thread.sleep(2000);
		// driver.findElement(By.xpath("//input[@value='审核通过']")).click();
		// if (tester.isAlertPresent()) {
		// tester.closeAlertAndGetItsText();
		// }

		// //日结操作
		// tester.clickLeftMenu("日结管理", "日结操作");
		// tester.switchRightTab("日结操作");
		// driver.findElement(By.xpath("//input[@value='查询']")).click();
		// driver.findElement(By.xpath("//input[@value='进行日结']")).click();
		// tester.switchRightTab("日结");
		// driver.findElement(By.xpath("//input[@value='确认进行日结']")).click();


	@Test
	public void testAllot() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 /*
		 * 生成
		 */
		 tester.clickLeftMenu("中央工厂调拨管理", "内部调拨单生成");
		 tester.switchRightTab("内部调拨单生成");
		 // driver.findElement(By.id("outBranchId")).clear();
		 // driver.findElement(By.id("outBranchId")).sendKeys("[01]海东青店");;
		 // driver.findElement(By.id("outStorageId")).clear();
		 driver.findElement(By.id("outStorageId")).sendKeys("[F00B]长沙中央工厂分仓");
		 ;
		 driver.findElement(By.xpath("//input[@value='根据原料自选']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("101001");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 driver.switchTo().parentFrame();
		 // driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='生成调拨单']")).click();
		
		 tester.switchRightTab("提交调拨单");
		 // driver.findElement(By.id("btn_submit")).click();
		 driver.findElement(By.xpath("//input[@value='确认生成调拨单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 Thread.sleep(2000);
		
		 tester.clickLeftMenu("中央工厂调拨管理", "调拨单审核");
		 tester.switchRightTab("调拨单审核");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.id("endDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("operate1")).click();
		 Thread.sleep(1000);
		 tester.switchRightTabLike("审核调拨单-");
		 driver.findElement(By.xpath("//input[@value='调拨单确认通过']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}
	
	public void testAllot(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 /*
		 * 生成
		 */
		 tester.clickLeftMenu("中央工厂调拨管理", "内部调拨单生成");
		 tester.switchRightTab("内部调拨单生成");
		 // driver.findElement(By.id("outBranchId")).clear();
		 // driver.findElement(By.id("outBranchId")).sendKeys("[01]海东青店");;
		 // driver.findElement(By.id("outStorageId")).clear();
		 driver.findElement(By.id("outStorageId")).sendKeys("[F00B]长沙中央工厂分仓");
		 ;
		 driver.findElement(By.xpath("//input[@value='根据原料自选']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("101001");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 driver.switchTo().parentFrame();
		 // driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='生成调拨单']")).click();
		
		 tester.switchRightTab("提交调拨单");
		 // driver.findElement(By.id("btn_submit")).click();
		 driver.findElement(By.xpath("//input[@value='确认生成调拨单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 Thread.sleep(2000);
		
		 tester.clickLeftMenu("中央工厂调拨管理", "调拨单审核");
		 tester.switchRightTab("调拨单审核");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.id("endDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("operate1")).click();
		 Thread.sleep(1000);
		 tester.switchRightTabLike("审核调拨单-");
		 driver.findElement(By.xpath("//input[@value='调拨单确认通过']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
	}

	@Test
	public void testOuterAllot() throws Exception {
		// 央厂cf登陆
		tester.login("", "cf", "3456");

		// 中央工厂测试-------------------------------------------------------------
		tester.clickTopMenu("中央工厂");
		 Thread.sleep(100);
		 /*
		 * 外部生成调拨单
		 */
		 tester.clickLeftMenu("中央工厂调拨管理", "外部调拨单生成");
		 tester.switchRightTab("外部调拨单生成");
		 // driver.findElement(By.id("outBranchId")).clear();
		 // driver.findElement(By.id("outBranchId")).sendKeys("[01]海东青店");;
		 // driver.findElement(By.id("outStorageId")).clear();
		 //
		 driver.findElement(By.id("outStorageId")).sendKeys("[F00B]长沙中央工厂分仓");
		 driver.findElement(By.xpath("//input[@value='根据原料自选']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("101001");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 driver.switchTo().parentFrame();
		 // driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='生成调拨单']")).click();
		
		 tester.switchRightTab("提交调拨单");
		 // driver.findElement(By.id("btn_submit")).click();
		 driver.findElement(By.xpath("//input[@value='确认生成调拨单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 /*
		 * 审核
		 */
		 tester.logout();
		 Thread.sleep(2000);
		 tester.login("", "lc", "3456");
		
		 //
		// 中央工厂测试-------------------------------------------------------------
		 tester.clickTopMenu("物流中心");
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 Thread.sleep(2000);
		
		 tester.clickLeftMenu("物流中心调拨管理", "调拨单审核");
		 tester.switchRightTab("调拨单审核");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.id("endDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("operate1")).click();
		 Thread.sleep(1000);
		 tester.switchRightTabLike("审核调拨单-");
		 driver.findElement(By.xpath("//input[@value='调拨单确认通过']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }

	}

	public void testOuterAllot(Tester tester,WebDriver driver,Logger logger) throws Exception {
		 /*
		 * 外部生成调拨单
		 */
		 tester.clickLeftMenu("中央工厂调拨管理", "外部调拨单生成");
		 tester.switchRightTab("外部调拨单生成");
		 // driver.findElement(By.id("outBranchId")).clear();
		 // driver.findElement(By.id("outBranchId")).sendKeys("[01]海东青店");;
		 // driver.findElement(By.id("outStorageId")).clear();
		 //
		 driver.findElement(By.id("outStorageId")).sendKeys("[F00B]长沙中央工厂分仓");
		 driver.findElement(By.xpath("//input[@value='根据原料自选']")).click();
		 WebElement selMaterial =
		 driver.findElement(By.id("ifr_selMaterial"));
		 driver.switchTo().frame(selMaterial);
		 driver.findElement(By.id("itemName")).sendKeys("101001");
		 driver.findElement(By.xpath("//input[@value='查询原料']")).click();
		 driver.findElement(By.xpath("(//input[@type='checkbox'])")).click();
		 driver.findElement(By.xpath("//input[@value='确定']")).click();
		 driver.switchTo().parentFrame();
		 // driver.findElement(By.id("dijit_form_NumberTextBox_0")).clear();
		 driver.findElement(By.id("dijit_form_NumberTextBox_0")).sendKeys("1");
		 Thread.sleep(1000);
		 driver.findElement(By.xpath("//input[@value='生成调拨单']")).click();
		
		 tester.switchRightTab("提交调拨单");
		 // driver.findElement(By.id("btn_submit")).click();
		 driver.findElement(By.xpath("//input[@value='确认生成调拨单']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		
		 /*
		 * 审核
		 */
		 tester.logout();
		 Thread.sleep(2000);
		 tester.login("", "lc", "3456");
		
		 //
		// 中央工厂测试-------------------------------------------------------------
		 tester.clickTopMenu("物流中心");
			 if (tester.isAlertPresent()) {
			 tester.closeAlertAndGetItsText();
		 }
		 Thread.sleep(2000);
		
		 tester.clickLeftMenu("物流中心调拨管理", "调拨单审核");
		 tester.switchRightTab("调拨单审核");
		 driver.findElement(By.id("startDate")).clear();
		 driver.findElement(By.id("endDate")).clear();
		 driver.findElement(By.xpath("//input[@value='查询']")).click();
		 Thread.sleep(1000);
		 driver.findElement(By.id("operate1")).click();
		 Thread.sleep(1000);
		 tester.switchRightTabLike("审核调拨单-");
		 driver.findElement(By.xpath("//input[@value='调拨单确认通过']")).click();
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 if (tester.isAlertPresent()) {
		 tester.closeAlertAndGetItsText();
		 }
		 tester.logout();
		 tester.login("", "cf", "3456");
	}
	
	@After
	public void tearDown() throws Exception {
		driver.quit();
		String result = tester.verificationErrors.toString();
		if (!"".equals(result)) {
			fail(result);
		}
	}
}
