package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import logic.NoConnection;
import logic.businessquery.FoodBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.Bill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

import dao.businessquery.BillDao;

@SuppressWarnings("rawtypes")
public class BillCodeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	@Resource
	private BillDao billDao;

	public void setBillDao(BillDao billDao) {
		this.billDao = billDao;
	}

	private BranchBean branchBean;
	private FoodBean foodBean;

	private List<Map> shopLst;

	private String startDate;
	private String endDate;
	private String shopC;
	private String billCode;
	private Date businessDate;

	public String getBusinessDate() {
		return DateTimeUtil.getDateStr(businessDate);
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public String getBillCode() {
		return billCode;
	}

	public void setBillCode(String billCode) {
		this.billCode = billCode;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		return DateTimeUtil.getDate();
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		if (!TextUtil.isEmpty(endDate)) {
			return endDate;
		}
		return DateTimeUtil.getDate();
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

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setFoodBean(FoodBean foodBean) {
		this.foodBean = foodBean;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "");
		allItem.put("name", "--请选择--");
		shopLst.add(0, allItem);
		String cfCode = getLoginBranchId();
		businessDate = branchBean.GetBranchById(cfCode).getBusinessDate();
		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	public void ctrQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();

		List<Map> deptLst = foodBean.ctrQuery(startDate, endDate);
		int rownumber = 1;
		for (Map branch : deptLst) {
			JSONObject json = JSONObject.fromObject(branch);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void listBill() throws NoPrivilegeException, SQLException, NoConnection, IOException {

		String code = "%" + billCode + "%";

		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		int totalCount = 0;
		List<Bill> liBeans;

		totalCount = billDao.listBillBylikeCodeCount(getStartDate(), getEndDate(), code, shopC);

		liBeans = billDao.listBillBylikeCode(startDate, endDate, code, shopC, getStart(), getCount());

		JSONArray jsonArray = new JSONArray();

		for (Bill bill : liBeans) {
			JSONObject jObject = new JSONObject();

			String dBusiness = bill.getdBusiness();
			String billTime = bill.getBillTime();
			String settleTime = bill.getSettleTime();

			if (dBusiness.contains(".")) {
				dBusiness = dBusiness.substring(0, 10);
			}
			if (billTime.contains(".")) {
				billTime = billTime.substring(0, billTime.indexOf("."));
			}
			if (settleTime.contains(".")) {
				settleTime = settleTime.substring(0, settleTime.indexOf("."));
			}

			jObject.put("billC", bill.getBillC().trim());
			jObject.put("dBusiness", dBusiness);
			jObject.put("period", bill.getPeriod());
			jObject.put("shift", bill.getShift());
			jObject.put("guestNum", bill.getGuestNum());
			jObject.put("billTime", billTime);
			jObject.put("settleTime", settleTime);
			jObject.put("createMan", bill.getCreateMan());
			jObject.put("settleMan", bill.getSettleMan());
			jObject.put("foodAmt", bill.getFoodAmt());
			jObject.put("roundAmt", bill.getRoundAmt());
			jObject.put("presentAmt", bill.getPresentAmt());
			jObject.put("payAmt", bill.getPayAmt());
			jObject.put("disAmt", bill.getDisAmt());

			jsonArray.add(jObject);
		}

		if (isMobile()) {
			JSONObject result = new JSONObject();
			result.put("recordsTotal", totalCount);
			result.put("recordsFiltered", totalCount);
			result.put("iTotalDisplayRecords", totalCount);
			result.put("data", jsonArray);// for DataTables
			this.outJS(result.toString());
			return;
		}
		setTotal(totalCount);
		this.outJS(jsonArray.toString()); // for dojo
	}
}
