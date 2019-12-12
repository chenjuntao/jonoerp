/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年1月26日 by cjt
 * Last edited on 2016年1月26日 by cjt
 * 
 * 说明： 与餐厅前台进行数据交互的REST格式API
 */

package action.restapi;

import java.io.IOException;
import java.sql.SQLException;

import logic.NoConnection;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import service.restapi.BillService;
import service.restapi.BillsService;
import service.restapi.FoodCategoryService;
import service.restapi.FoodPriceService;
import service.restapi.FoodService;
import service.restapi.PayService;
import service.restapi.RestaurantService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

/**
 * 与餐厅前台进行数据交互的REST格式API
 */
@SuppressWarnings({ "serial" })
public class RestApiAction extends BaseAction {

	public static Logger log = Logger.getLogger("RestApiAction");

	// 测试服务器是否连通-------------------------------------------------------------------------
	public void testConnect() throws IOException {
		log.debug("connect request test coming in........................");
		JSONObject result = new JSONObject();
		result.put("msg", "connection is ok!");
		this.outJS(result.toString());
	}

	// 判断是否为POST请求
	private boolean isPost() {
		String method = ServletActionContext.getRequest().getMethod();
		boolean isPostMethod = "POST".equalsIgnoreCase(method);
		return isPostMethod;
	}

	// restaurant-------------------------------------------------------------------------
	private String restaurant;

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	private RestaurantService restauarntService;

	public void setRestaurantService(RestaurantService restauarntService) {
		this.restauarntService = restauarntService;
	}

	// post餐厅门店详细信息
	public void postRestaurant() throws IOException, NoPrivilegeException, SQLException, NoConnection {
		String result = restauarntService.saveRestaurant(restaurant);
		this.outJS(result);
	}

	// bill-------------------------------------------------------------------------
	private String bill;

	public void setBill(String bill) {
		this.bill = bill;
	}

	private BillService billService;

	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	// post单据信息
	public void postBill() throws IOException, NoPrivilegeException, SQLException, NoConnection {
		String result = billService.saveBill(bill);
		this.outJS(result);
	}

	// bills-------------------------------------------------------------------------
	private String bills;

	public void setBills(String bills) {
		this.bills = bills;
	}

	private BillsService billsService;

	public void setBillsService(BillsService billsService) {
		this.billsService = billsService;
	}

	// post单据对应的菜品信息
	public void postBills() throws IOException, NoPrivilegeException, SQLException, NoConnection {
		String result = billsService.saveBills(bills);
		this.outJS(result);
	}

	// pay-------------------------------------------------------------------------
	private String pay;

	public void setPay(String pay) {
		this.pay = pay;
	}

	private PayService payService;

	public void setPayService(PayService payService) {
		this.payService = payService;
	}

	// post单据对应的付款信息
	public void postPay() throws IOException, NoPrivilegeException, SQLException, NoConnection {
		String result = payService.savePay(pay);
		this.outJS(result);
	}

	// food-------------------------------------------------------------------------
	private String food;

	public void setFood(String food) {
		this.food = food;
	}

	private FoodService foodService;

	public void setFoodService(FoodService foodService) {
		this.foodService = foodService;
	}

	// post出品信息
	public void postFood() throws IOException, NoPrivilegeException, SQLException, NoConnection {
		String result = foodService.saveFood(food);
		this.outJS(result);
	}

	// foodCategory-------------------------------------------------------------------------
	private String food_category;

	public void setFood_category(String food_category) {
		this.food_category = food_category;
	}

	private FoodCategoryService foodCategoryService;

	public void setFoodCategoryService(FoodCategoryService foodCategoryService) {
		this.foodCategoryService = foodCategoryService;
	}

	// post出品类别信息
	public void postFoodCategory() throws IOException, NoPrivilegeException, SQLException, NoConnection {
		String result = foodCategoryService.saveFoodCategory(food_category);
		this.outJS(result);
	}

	// foodPrice-------------------------------------------------------------------------
	private String food_price;

	public void setFood_price(String food_price) {
		this.food_price = food_price;
	}

	private FoodPriceService foodPriceService;

	public void setFoodPriceService(FoodPriceService foodPriceService) {
		this.foodPriceService = foodPriceService;
	}

	// post出品价格信息
	public void postFoodPrice() throws IOException, NoPrivilegeException, SQLException, NoConnection {
		String result = foodPriceService.saveFoodPrice(food_price);
		this.outJS(result);
	}
}
