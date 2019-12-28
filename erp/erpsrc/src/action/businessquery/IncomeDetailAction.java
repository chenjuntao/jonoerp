package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.IncomeDetailBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.IncomeDetail;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class IncomeDetailAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private List<Map> shopLst;

	private String startDate;
	private String endDate;
	private String shopC;

	private IncomeDetailBean incomeDetailBean;

	public void setIncomeDetailBean(IncomeDetailBean incomeDetailBean) {
		this.incomeDetailBean = incomeDetailBean;
	}

	public List<Map> getShopLst() {
		return shopLst;
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

	public void dayIncomeDetail() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		List<IncomeDetail> dayIncomeDetails = incomeDetailBean.dayIncomeDetail(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (IncomeDetail item : dayIncomeDetails) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("businessDate", item.getBusinessDate());
			jObject.put("categoryN", item.getCategoryN());
			jObject.put("period1", item.getPeriod1());
			jObject.put("period2", item.getPeriod2());
			jObject.put("period3", item.getPeriod3());
			jObject.put("period4", item.getPeriod4());
			jObject.put("counts", item.getCounts());

			jsonArray.add(jObject);
			rownumber++;
		}

		IncomeDetail item = incomeDetailBean.daySum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("period1", item.getPeriod1());
		sumJObject.put("period2", item.getPeriod2());
		sumJObject.put("period3", item.getPeriod3());
		sumJObject.put("period4", item.getPeriod4());
		sumJObject.put("counts", item.getCounts());
		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void monthIncomeDetail() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		startDate = DateTimeUtil.getDateTime(DateTimeUtil.parse(startDate, "yyyy-MM"), "yyyy-MM");
		endDate = DateTimeUtil.getDateTime(DateTimeUtil.parse(endDate, "yyyy-MM"), "yyyy-MM");

		List<IncomeDetail> dayIncomeDetails = incomeDetailBean.monthIncomeDetail(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (IncomeDetail item : dayIncomeDetails) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("businessMonth", item.getBusinessMonth());
			jObject.put("categoryN", item.getCategoryN());
			jObject.put("period1", item.getPeriod1());
			jObject.put("period2", item.getPeriod2());
			jObject.put("period3", item.getPeriod3());
			jObject.put("period4", item.getPeriod4());
			jObject.put("counts", item.getCounts());

			jsonArray.add(jObject);
			rownumber++;
		}

		IncomeDetail item = incomeDetailBean.monthSum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("period1", item.getPeriod1());
		sumJObject.put("period2", item.getPeriod2());
		sumJObject.put("period3", item.getPeriod3());
		sumJObject.put("period4", item.getPeriod4());
		sumJObject.put("counts", item.getCounts());
		jsonArray.add(sumJObject);

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
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "");
		allItem.put("name", "--请选择--");
		shopLst.add(0, allItem);

		return SUCCESS;
	}

}
