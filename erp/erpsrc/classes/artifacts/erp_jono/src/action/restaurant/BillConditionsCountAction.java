package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FreeBillBean;
import logic.store.BranchBean;
import logic.store.DiscountBean;
import logic.store.PresentBean;
import logic.store.ReturnBean;
import logic.store.ShiftPeriodBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.FreeBill;
import pojo.businessquery.PresentBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class BillConditionsCountAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private FreeBillBean freeBillBean;
	private PresentBean presentBean;
	private List<Map> shopLst;
	private List<Map> shiftLst;
	private List<Map> periodLst;

	private String startDate;
	private String endDate;
	private String shopC;

	private String period;
	private String shift;

	public void setReturnBean(ReturnBean returnBean) {
	}

	public void setPresentBean(PresentBean presentBean) {
		this.presentBean = presentBean;
	}

	public void setDiscountBean(DiscountBean discountBean) {
	}

	public void setFreeBillBean(FreeBillBean freeBillBean) {
		this.freeBillBean = freeBillBean;
	}

	private ShiftPeriodBean shiftPeriodBean;

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getShift() {
		return shift;
	}

	public void setShift(String shift) {
		this.shift = shift;
	}

	public void setShiftPeriodBean(ShiftPeriodBean shiftPeriodBean) {
		this.shiftPeriodBean = shiftPeriodBean;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public List<Map> getShiftLst() {
		return shiftLst;
	}

	public List<Map> getPeriodLst() {
		return periodLst;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		return DateTimeUtil.getDateTime(DateTimeUtil.getMonthFristDay(), DateTimeUtil.DEFAULT_DATE_FORMAT);
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

	public void freeBillInfo() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		List<FreeBill> freeBills = freeBillBean.getHead(startDate, endDate, shopC, period, shift, getStart(), getEnd());

		int totalCount = freeBillBean.getHeadCount(startDate, endDate, shopC, period, shift);
		setTotal(totalCount);

		JSONArray jsonArray = new JSONArray();

		int rownumber = getStart();

		for (FreeBill item : freeBills) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("shift", item.getShift());
			jObject.put("period", item.getPeriod());
			jObject.put("businessDate", item.getBusinessDate());
			jObject.put("payAmt", item.getPayAmt());
			jObject.put("foodAmt", item.getFoodAmt());

			jObject.put("billC", item.getBillC());
			jObject.put("table", item.getTable());

			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		if (totalCount < getEnd()) {
			JSONObject sumJObject = new JSONObject();
			sumJObject.put("rownumber", "合计");
			// FreeBill item = freeBillBean.getHeadSum(startDate, endDate,
			// shopC, period, shift);
			// sumJObject.put("payAmt", item.getPayAmt());
			// sumJObject.put("foodAmt", item.getFoodAmt());
			jsonArray.add(sumJObject);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void setConditions() {
		if (TextUtil.isEmpty(shopC)) {
			shopC = "%%";
		}

		if (TextUtil.isEmpty(period)) {
			period = "%%";
		}

		if (TextUtil.isEmpty(shift)) {
			shift = "%%";
		}
	}

	// public void discountInfo() throws NoPrivilegeException, SQLException,
	// NoConnection {
	//
	// setConditions();
	//
	// int totalCount = discountBean.getCount(startDate, endDate, shopC, period,
	// shift);
	// setTotal(totalCount);
	//
	// List<DiscountBean> discountBeans = discountBean.getDiscount(startDate,
	// endDate, shopC, period, shift, getStart(), getEnd());
	//
	// JSONArray jsonArray = new JSONArray();
	// int rownumber = getStart();
	//
	// for (DiscountBean item : discountBeans) {
	// JSONObject jObject = new JSONObject();
	// jObject.put("id", rownumber);
	// jObject.put("rownumber", rownumber);
	// jObject.put("shift", item.getShift());
	// jObject.put("period", item.getPeriod());
	// jObject.put("businessDate", item.getBusinessDate());
	// jObject.put("billC", item.getBillC());
	// jObject.put("table", item.getTable());
	// jObject.put("shopC", item.getShopC());
	// jObject.put("shopN", item.getShopN());
	// jObject.put("foodAmt", item.getFoodAmt());
	// jObject.put("disAmt", item.getDisAmt());
	// jObject.put("payAmt", item.getPayAmt());
	// jObject.put("disMan", item.getDisMan());
	// jObject.put("disWhy", item.getDisWhy());
	//
	// jsonArray.add(jObject);
	// rownumber++;
	// }
	//
	// if (totalCount < getEnd()) {
	// JSONObject sumJObject = new JSONObject();
	// DiscountBean item = discountBean.getDiscountSum(startDate, endDate,
	// shopC, period, shift);
	//
	// sumJObject.put("rownumber", "合计");
	// sumJObject.put("foodAmt", item.getFoodAmt());
	// sumJObject.put("disAmt", item.getDisAmt());
	// sumJObject.put("payAmt", item.getPayAmt());
	// jsonArray.add(sumJObject);
	// }
	//
	// try {
	// this.outJS(jsonArray.toString());
	// } catch (IOException e) {
	// e.printStackTrace();
	// }
	//
	// }

	public void presentInfo() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		List<PresentBill> presentBills = presentBean.getHead(startDate, endDate, shopC, period, shift, getStart(),
				getEnd());

		int totalCount = presentBean.getHeadCount(startDate, endDate, shopC, period, shift);
		setTotal(totalCount);

		JSONArray jsonArray = new JSONArray();
		int rownumber = getStart();

		for (PresentBill item : presentBills) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("businessDate", item.getBusinessDate());
			jObject.put("billC", item.getBillC());
			jObject.put("period", item.getPeriod());
			jObject.put("shift", item.getShift());
			jObject.put("table", item.getTable());
			jObject.put("foodName", item.getFoodName());
			jObject.put("unit", item.getUnit());
			jObject.put("presentAmount", item.getPresentAmount());
			jObject.put("price", item.getPrice());
			jObject.put("presentPrice", item.getPresentPrice());
			jObject.put("extracPrice", item.getExtracPrice());
			jObject.put("presentMan", item.getPresentMan());
			jObject.put("presentWhy", item.getPresentWhy());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());
			jObject.put("bigC", item.getBigC());
			jObject.put("bigN", item.getBigN());

			jsonArray.add(jObject);
			rownumber++;
		}

		if (totalCount < getEnd()) {
			JSONObject sumJObject = new JSONObject();
			PresentBill item = presentBean.getDetailSum(startDate, endDate, shopC, period, shift);

			sumJObject.put("rownumber", "合计");
			sumJObject.put("presentAmount", item.getPresentAmount());
			sumJObject.put("presentPrice", item.getPresentPrice());
			sumJObject.put("extracPrice", item.getExtracPrice());
			jsonArray.add(sumJObject);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "");
		allItem.put("name", "--请选择--");
		shopLst.add(0, allItem);

		Map<String, String> allShiftItem = new HashMap<String, String>();
		shiftLst = shiftPeriodBean.getShift();
		allShiftItem.put("code", "");
		allShiftItem.put("name", "--请选择--");
		shiftLst.add(0, allShiftItem);

		Map<String, String> allPeriodItem = new HashMap<String, String>();
		periodLst = shiftPeriodBean.getPeriod();
		allPeriodItem.put("code", "");
		allPeriodItem.put("name", "--请选择--");
		periodLst.add(0, allPeriodItem);

		return SUCCESS;
	}

}
