package action.businessquery;

import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.businessquery.TableBean;
import pojo.businessquery.TableBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;

public class TableAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private TableBean tableBean;

	private String startDate;
	private String endDate;
	private String shopName;
	private String shopC;

	private List<TableBill> tables;

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

	public String getShopName() {
		return shopName;
	}

	public String getShopC() {
		return shopC;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public List<TableBill> getTables() {
		return tables;
	}

	public TableBean getTableBean() {
		return tableBean;
	}

	public void setTableBean(TableBean tableBean) {
		this.tableBean = tableBean;
	}

	public String listTable() throws NoPrivilegeException, SQLException, NoConnection {
		shopName = tableBean.getShopName(shopC);

		tables = tableBean.listTable(startDate, endDate, shopC);
		return "success";
	}
}
