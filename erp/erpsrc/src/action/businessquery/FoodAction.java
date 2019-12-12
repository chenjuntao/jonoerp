package action.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import logic.NoConnection;
import logic.businessquery.FoodBean;
import logic.businessquery.ShopPayBean;
import pojo.businessquery.Bill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.businessquery.BillDao;

@SuppressWarnings("rawtypes")
public class FoodAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private FoodBean foodBean;
	@Resource
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	private Bill bill;

	private String startDate;
	private String endDate;
	private String billC;

	private String shopC;
	private String table;

	private List foods;

	private ShopPayBean shopPayBean;

	private String payStr;

	public String getPayStr() {
		return payStr;
	}

	public void setShopPayBean(ShopPayBean shopPayBean) {
		this.shopPayBean = shopPayBean;
	}

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public Bill getBill() {
		return bill;
	}

	public List getFoods() {
		return foods;
	}

	public String getBillC() {
		return billC;
	}

	public void setBillC(String billC) {
		this.billC = billC;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public FoodBean getFoodBean() {
		return foodBean;
	}

	public void setFoodBean(FoodBean foodBean) {
		this.foodBean = foodBean;
	}

	public String listFood() throws NoPrivilegeException, SQLException, NoConnection {
		payStr = "";
		List<Map> payBillLists = shopPayBean.getBillPay(billC);
		for (Map map : payBillLists) {
			payStr += map.get("CPAY_N").toString() + " " + map.get("payTypeAmt").toString() + " ";
		}

		bill = billDao.listBillById(billC);
		foods = foodBean.listFood(billC);
		return "success";
	}
}
