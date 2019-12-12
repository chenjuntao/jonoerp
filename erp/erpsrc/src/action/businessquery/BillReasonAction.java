package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.DiscountReasonBean;
import logic.businessquery.PresentReasonBean;
import logic.businessquery.ReturnReasonBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.DiscountReason;
import pojo.businessquery.PresentReason;
import pojo.businessquery.ReturnReason;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

@SuppressWarnings("rawtypes")
public class BillReasonAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;
	private ReturnReasonBean returnReasonBean;
	private PresentReasonBean presentReasonBean;
	private DiscountReasonBean discountReasonBean;

	private List<Map> shopLst;

	private String startDate;
	private String endDate;
	private String shopC;

	public void setDiscountReasonBean(DiscountReasonBean discountReasonBean) {
		this.discountReasonBean = discountReasonBean;
	}

	public void setPresentReasonBean(PresentReasonBean presentReasonBean) {
		this.presentReasonBean = presentReasonBean;
	}

	public void setReturnReasonBean(ReturnReasonBean returnReasonBean) {
		this.returnReasonBean = returnReasonBean;
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

	public void discountReason() throws NoPrivilegeException, SQLException, NoConnection {
		setConditions();

		List<DiscountReason> discountReasons = discountReasonBean.discountReason(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (DiscountReason item : discountReasons) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("foodAmt", item.getFoodAmt());
			jObject.put("disAmt", item.getDisAmt());
			jObject.put("payAmt", item.getPayAmt());
			jObject.put("disWhy", item.getDisWhy());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		DiscountReason item = discountReasonBean.discountReasonSum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("foodAmt", item.getFoodAmt());
		sumJObject.put("disAmt", item.getDisAmt());
		sumJObject.put("payAmt", item.getPayAmt());
		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void presentReason() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		List<PresentReason> presentReasons = presentReasonBean.presentReason(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (PresentReason item : presentReasons) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("presentAmount", item.getPresentAmount());
			jObject.put("presentPrice", item.getPresentPrice());
			jObject.put("presentWhy", item.getPresentWhy());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		PresentReason item = presentReasonBean.presentReasonSum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("presentAmount", item.getPresentAmount());
		sumJObject.put("presentPrice", item.getPresentPrice());
		jsonArray.add(sumJObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void returnReason() throws NoPrivilegeException, SQLException, NoConnection {

		setConditions();

		List<ReturnReason> returnReasons = returnReasonBean.returnReason(startDate, endDate, shopC);

		JSONArray jsonArray = new JSONArray();
		int rownumber = 1;

		for (ReturnReason item : returnReasons) {
			JSONObject jObject = new JSONObject();
			jObject.put("id", rownumber);
			jObject.put("rownumber", rownumber);
			jObject.put("returnAmount", item.getReturnAmount());
			jObject.put("returnAmt", item.getReturnAmt());
			jObject.put("returnWhy", item.getReturnWhy());
			jObject.put("shopC", item.getShopC());
			jObject.put("shopN", item.getShopN());

			jsonArray.add(jObject);
			rownumber++;
		}

		ReturnReason item = returnReasonBean.returnReasonSum(startDate, endDate, shopC);

		JSONObject sumJObject = new JSONObject();
		sumJObject.put("rownumber", "合计");
		sumJObject.put("returnAmount", item.getReturnAmount());
		sumJObject.put("returnAmt", item.getReturnAmt());
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
