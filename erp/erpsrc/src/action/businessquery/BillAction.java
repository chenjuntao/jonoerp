/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on June 20, 2014 by pw
 * Last edited on Aug 6, 2014 by liyzh
 * 
 * 说明： 人员操作查询action
 */
package action.businessquery;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import logic.NoConnection;
import logic.businessquery.PeopleBean;
import logic.businessquery.ShopPayBean;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

import dao.businessquery.BillDao;

@SuppressWarnings(value={"serial","rawtypes"})
public class BillAction extends BaseAction {
	public static Logger log = Logger.getLogger(ShopAction.class);

	private boolean fromMobile;

	public boolean getFromMobile() {
		return fromMobile;
	}

	public void setFromMobile(boolean fromMobile) {
		this.fromMobile = fromMobile;
	}

	private int rowPerPage = 10;// 每页的行数
	private int pageCount = 0;// 总页数

	@Resource
	private BillDao billDao;
	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	private PeopleBean peopleBean;

	private ShopPayBean shopPayBean;

	public ShopPayBean getShopPayBean() {
		return shopPayBean;
	}

	public void setShopPayBean(ShopPayBean shopPayBean) {
		this.shopPayBean = shopPayBean;
	}

	private String payName;

	public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
	}

	public PeopleBean getPeopleBean() {
		return peopleBean;
	}

	public void setPeopleBean(PeopleBean peopleBean) {
		this.peopleBean = peopleBean;
	}

	public String listBill() throws NoPrivilegeException, SQLException, NoConnection {

		List bills = null;
		if (billDao != null) {

			HttpServletRequest request = ServletActionContext.getRequest();
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String shopC = request.getParameter("shopC");
			String table = request.getParameter("table");
			int currentPage = Integer.parseInt(request.getParameter("currentPage")); // 当前要显示的页码

			int startRow = 0;
			if (currentPage == 0) { // 如果是第一次显示，则用0表示，需要计算总行数和总页数
				int allRowCount = billDao.billCountByST(startDate, endDate, shopC, table);
				pageCount = (allRowCount / rowPerPage) + (allRowCount % rowPerPage > 0 ? 1 : 0);
				request.setAttribute("pageCount", pageCount);
				currentPage = 1;
			} else {
				startRow = (currentPage - 1) * rowPerPage;
				request.setAttribute("pageCount", request.getParameter("pageCount"));
			}

			bills = billDao.listBillByST(startDate, endDate, shopC, table, startRow, rowPerPage);

			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("shopC", shopC);
			request.setAttribute("table", table);
			request.setAttribute("bills", bills);
			request.setAttribute("currentPage", currentPage);
			return "success";
		} else {
			return "failure";
		}
	}
	public String listBillByPay() throws NoPrivilegeException, SQLException, NoConnection {
		List bills = null;
		if (billDao != null) {

			HttpServletRequest request = ServletActionContext.getRequest();
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			String shopC = request.getParameter("shopC");
			String type = request.getParameter("type");

			if (TextUtil.isEmpty(payName)) {
				payName = shopPayBean.getPayName(type);
			}

			int currentPage;
			try {
				currentPage = Integer.parseInt(request.getParameter("currentPage")); // 当前要显示的页码
			} catch (Exception e) {
				currentPage = 0;
			}
			int startRow = 0;

			if (currentPage == 0) { // 如果是第一次显示，则用0表示，需要计算总行数和总页数
				int allRowCount = billDao.billCountByPay(startDate, endDate, shopC, type);
				pageCount = (allRowCount / rowPerPage) + (allRowCount % rowPerPage > 0 ? 1 : 0);
				request.setAttribute("pageCount", pageCount);
				currentPage = 1;
			} else {
				startRow = (currentPage - 1) * rowPerPage;
				request.setAttribute("pageCount", request.getParameter("pageCount"));
			}

			bills = billDao.listBillByPay(startDate, endDate, shopC, type, startRow, rowPerPage);

			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("shopC", shopC);
			request.setAttribute("type", type);
			request.setAttribute("bills", bills);
			request.setAttribute("currentPage", currentPage);
			return "success";
		} else {
			return "failure";
		}
	}

	public String listBillPeople() throws NoPrivilegeException, SQLException, NoConnection {
		HttpServletRequest request = ServletActionContext.getRequest();

		List<Map> createMans, settleMans, disCurMans = null;

		createMans = peopleBean.listMan("cCreateMan", "cCreateMan");
		settleMans = peopleBean.listMan("cSettleMan", "cSettleMan");
		disCurMans = peopleBean.listMan("cDisManCur_n", "cDisManCur_n");

		request.setAttribute("createMans", createMans);
		request.setAttribute("settleMans", settleMans);
		request.setAttribute("disCurMans", disCurMans);

		if (fromMobile) {
			return "mobile";
		}
		return "success";
	}

	public String listBillByMan() throws NoPrivilegeException, SQLException, NoConnection {

		List peopleBills = null;

		if (billDao != null) {

			HttpServletRequest request = ServletActionContext.getRequest();

			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");

			String createMan = TextUtil.isEmpty(request.getParameter("createMan")) ? "%" : request
					.getParameter("createMan");
			String settleMan = TextUtil.isEmpty(request.getParameter("settleMan")) ? "%" : request
					.getParameter("settleMan");
			String disCurMan = TextUtil.isEmpty(request.getParameter("disCurMan")) ? "%" : request
					.getParameter("disCurMan");

			int currentPage;
			try {
				currentPage = Integer.parseInt(request.getParameter("currentPage")); // 当前要显示的页码
			} catch (Exception e) {
				currentPage = 0;
			}
			int startRow = 0;
			int endRow = 8;

			if (currentPage == 0) { // 如果是第一次显示，则用0表示，需要计算总行数和总页数
				int allRowCount = billDao.billCountByMan(startDate, endDate, createMan, settleMan, disCurMan);
				pageCount = (allRowCount / rowPerPage) + (allRowCount % rowPerPage > 0 ? 1 : 0);
				request.setAttribute("pageCount", pageCount);
				currentPage = 1;
			} else {
				startRow = (currentPage - 1) * rowPerPage;
				endRow = currentPage * rowPerPage;
				request.setAttribute("pageCount", request.getParameter("pageCount"));
			}

			peopleBills = billDao.listBillByMan(startDate, endDate, createMan, settleMan, disCurMan, startRow, endRow);

			request.setAttribute("createMan", createMan);
			request.setAttribute("settleMan", settleMan);
			request.setAttribute("disCurMan", disCurMan);
			request.setAttribute("endDate", endDate);
			request.setAttribute("startDate", startDate);
			request.setAttribute("peopleBills", peopleBills);
			request.setAttribute("currentPage", currentPage);

			if (fromMobile) {
				return "mobile";
			}
			return "success";
		} else {
			return "failure";
		}
	}

}
