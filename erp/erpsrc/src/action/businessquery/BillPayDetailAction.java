package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.BillPayDetailBean;
import logic.store.BranchBean;
import logic.store.ShiftPeriodBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.BillPayDetail;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class BillPayDetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private BillPayDetailBean billPayDetailBean;
	private ShiftPeriodBean shiftPeriodBean;

	private List<Map> shopLst;
	private List<Map> shiftLst;
	private List<Map> periodLst;

	private String startDate;
	private String endDate;
	private String shopC;
	private String period;
	private String shift;
	private String table;

	public void setTable(String table) {
		this.table = table;
	}

	public void setShiftPeriodBean(ShiftPeriodBean shiftPeriodBean) {
		this.shiftPeriodBean = shiftPeriodBean;
	}

	public void setBillPayDetailBean(BillPayDetailBean billPayDetailBean) {
		this.billPayDetailBean = billPayDetailBean;
	}

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

	public void billPay() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		int totalCount = billPayDetailBean.count(startDate, endDate, shopC, period, shift, table);
		setTotal(totalCount);

		List<BillPayDetail> billPayDetails = billPayDetailBean.billPay(startDate, endDate, shopC, period, shift,
				table, getStart(), getEnd());

		JSONArray jsonArray = new JSONArray();
		int rownumber = getStart();

		for (BillPayDetail item : billPayDetails) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);

			jObject.put("billC", item.getBillC());
			jObject.put("payN", item.getPayN());
			jObject.put("unit", item.getUnit());
			jObject.put("payAmt", item.getPayAmt());
			jObject.put("businessDate", item.getBusinessDate());
			jObject.put("table", item.getTable());
			jObject.put("period", item.getPeriod());
			jObject.put("shift", item.getShift());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());
			jObject.put("vipC", item.getVipC());
			jObject.put("vipN", item.getVipN());

			jsonArray.add(jObject);
			rownumber++;
		}

		if (totalCount < getEnd()) {
			BillPayDetail item = billPayDetailBean.sum(startDate, endDate, shopC, period, shift, table);

			JSONObject sumJObject = new JSONObject();
			sumJObject.put("rownumber", "合计");
			sumJObject.put("payAmt", item.getPayAmt());
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

		if (TextUtil.isEmpty(table)) {
			table = "%%";
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
