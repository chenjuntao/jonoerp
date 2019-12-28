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
import pojo.businessquery.DiscountBill;
import pojo.businessquery.PresentBill;
import pojo.businessquery.ReturnBill;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class BillConditionsAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private FreeBillBean freeBillBean;
	private DiscountBean discountBean;
	private PresentBean presentBean;
	private ReturnBean returnBean;

	private List<Map> shopLst;
	private List<Map> shiftLst;
	private List<Map> periodLst;

	private String startDate;
	private String endDate;
	private String shopC;

	private String period;
	private String shift;

	public void returnInfo() throws NoPrivilegeException, SQLException, NoConnection {
		int totalCount = returnBean.getHeadCount(startDate, endDate, shopC, period, shift);
		setTotal(totalCount);

		List<ReturnBill> returnBills = returnBean.getReturn(startDate, endDate, shopC, period, shift, getStart(),
				getEnd());

		JSONArray jsonArray = new JSONArray();
		int rownumber = getStart();

		for (ReturnBill item : returnBills) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("businessDate", item.getBusinessDate());
			jObject.put("billC", item.getBillC());
			jObject.put("period", item.getPeriod());
			jObject.put("shift", item.getShift());
			jObject.put("table", item.getTable());
			jObject.put("foodBill", item.getFoodBill());
			jObject.put("foodC", item.getFoodC());
			jObject.put("foodN", item.getFoodN());
			jObject.put("unit", item.getUnit());
			jObject.put("price", item.getPrice());
			jObject.put("returnAmount", item.getReturnAmount());
			jObject.put("returnAmt", item.getReturnAmt());
			jObject.put("returnWhy", item.getReturnWhy());
			jObject.put("returnTime", item.getReturnTime());
			jObject.put("backMan", item.getBackMan());
			jObject.put("suitFlag", item.getSuitFlag());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());
			jObject.put("smallC", item.getSmallC());
			jObject.put("smallN", item.getSmallN());

			jsonArray.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getQueryText(String startDate, String endDate, String shopC, String period, String shift) {
		StringBuilder sb = new StringBuilder();
		if (!TextUtil.isEmpty(startDate)) {
			sb.append("  AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')>= '" + startDate + "' ");
		}

		if (!TextUtil.isEmpty(endDate)) {
			sb.append("  AND TO_CHAR(b.DBUSINESS,'yyyy-mm-dd')<= '" + endDate + "' ");
		}

		sb.append("  AND bs.eSuitFlag <> '套餐子项' AND bs.eRetSendFlag <> '赠送' ");

		if (!TextUtil.isEmpty(shopC)) {
			sb.append("  AND  b.CBRANCH_C = '" + shopC + "' ");
		}

		if (!TextUtil.isEmpty(period)) {
			sb.append("  AND  b.CPERIOD_N = '" + period + "' ");
		}

		if (!TextUtil.isEmpty(shift)) {
			sb.append("  AND  b.CSHIFT_N = '" + shift + "' ");
		}

		return sb.toString();
	}

	public void freeBillInfo() throws NoPrivilegeException, SQLException, NoConnection {
		String queryText = getQueryText(startDate, endDate, shopC, period, shift);

		int totalCount = freeBillBean.getDetailCount(queryText);
		setTotal(totalCount);

		JSONArray jsonArray = new JSONArray();

		if (totalCount > 0) {
			jsonArray = JSONArray.fromObject(freeBillBean.getDetail(queryText, getStart(), getEnd()));
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void discountInfo() throws NoPrivilegeException, SQLException, NoConnection {
		int totalCount = discountBean.getCount(startDate, endDate, shopC, period, shift);
		setTotal(totalCount);

		List<DiscountBill> discountBills = discountBean.getDiscount(startDate, endDate, shopC, period, shift,
				getStart(), getEnd());

		JSONArray jsonArray = new JSONArray();
		int rownumber = getStart();

		for (DiscountBill item : discountBills) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("shift", item.getShift());
			jObject.put("period", item.getPeriod());
			jObject.put("businessDate", item.getBusinessDate());
			jObject.put("billC", item.getBillC());
			jObject.put("table", item.getTable());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());
			jObject.put("foodAmt", item.getFoodAmt());
			jObject.put("disAmt", item.getDisAmt());
			jObject.put("payAmt", item.getPayAmt());
			jObject.put("disMan", item.getDisMan());
			jObject.put("disWhy", item.getDisWhy());

			jsonArray.add(jObject);
			rownumber++;
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void presentInfo() throws NoPrivilegeException, SQLException, NoConnection {
		List<PresentBill> presentBills = presentBean.getDetail(startDate, endDate, shopC, period, shift, getStart(),
				getEnd());

		int totalCount = presentBean.getDetailCount(startDate, endDate, shopC, period, shift);
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

	public void setReturnBean(ReturnBean returnBean) {
		this.returnBean = returnBean;
	}

	public void setPresentBean(PresentBean presentBean) {
		this.presentBean = presentBean;
	}

	public void setDiscountBean(DiscountBean discountBean) {
		this.discountBean = discountBean;
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

}
