package action.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.ShopPayBean;
import pojo.businessquery.Bill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.businessquery.BillDao;

@SuppressWarnings("rawtypes")
public class BillInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private int rowPerPage = 10;// 每页的行数
	private int pageCount = 0;// 总页数

	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	private ShopPayBean shopPayBean;

	private String startDate;
	private String endDate;
	private String shopC;

	private String table;
	private String shopName;
	private int currentPage;

	private List<Bill> bills;
	private List<Map> payBillLists;

	private String CBILL_C;

	public void setCBILL_C(String cBILL_C) {
		CBILL_C = cBILL_C;
	}

	public String getCBILL_C() {
		return CBILL_C;
	}

	public List<Map> getPayBillLists() {
		return payBillLists;
	}

	public void setPayBillLists(List<Map> payBillLists) {
		this.payBillLists = payBillLists;
	}

	public ShopPayBean getShopPayBean() {
		return shopPayBean;
	}

	public void setShopPayBean(ShopPayBean shopPayBean) {
		this.shopPayBean = shopPayBean;
	}

	public int getRowPerPage() {
		return rowPerPage;
	}

	public void setRowPerPage(int rowPerPage) {
		this.rowPerPage = rowPerPage;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
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

	public String getShopName() {
		return shopName;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	@SuppressWarnings("unchecked")
	public String listBill() throws NoPrivilegeException, SQLException, NoConnection {
		Bill nameInfo = billDao.getBillBean(shopC, table);
		shopName = nameInfo.getShopName();
		table = nameInfo.getTable();

		int startRow = 0;
		if (currentPage == 0) { // 如果是第一次显示，则用0表示，需要计算总行数和总页数
			int allRowCount = billDao.billCountByST(startDate, endDate, shopC, table);
			pageCount = (allRowCount / rowPerPage) + (allRowCount % rowPerPage > 0 ? 1 : 0);
			setPageCount(pageCount);
			currentPage = 1;
		} else {
			startRow = (currentPage - 1) * rowPerPage;
		}

		bills = billDao.listBillByST(startDate, endDate, shopC, table, startRow, rowPerPage);

		return "success";
	}

	public String getBillPay() throws NoPrivilegeException, SQLException, NoConnection {
		payBillLists = shopPayBean.getBillPay(CBILL_C);
		return "success";
	}
}
