package action.businessquery;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.ShopPaymentBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.businessquery.ShopPayment;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class ShopPaymentAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ShopPaymentBean shopPaymentBean;

	private String startDate;
	private String endDate;

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setShopPaymentBean(ShopPaymentBean shopPaymentBean) {
		this.shopPaymentBean = shopPaymentBean;
	}

	public String getStartDate() {
		if (!TextUtil.isEmpty(startDate)) {
			return startDate;
		}
		return DateTimeUtil.getDateTime(DateTimeUtil.getMonthFristDay(), DateTimeUtil.DEFAULT_DATE_FORMAT);
	}

	public String getEndDate() {
		if (!TextUtil.isEmpty(endDate)) {
			return endDate;
		}
		return DateTimeUtil.getDate();
	}

	public void getDataByDay() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = new JSONObject();

		Map<String, String> dateTagMap = new HashMap<String, String>();
		Map<String, String> shopTagMap = new HashMap<String, String>();

		List<ShopPayment> shopPayInfos = shopPaymentBean.getShopPayInfoByDay(startDate, endDate);

		shopPayInfos.add(new ShopPayment());

		for (int i = 0; i < shopPayInfos.size(); i++) {
			ShopPayment item = shopPayInfos.get(i);

			if (!dateTagMap.containsKey(item.getBusinessDate())) {
				shopTagMap.clear();
			}

			if (!shopTagMap.containsKey(item.getShopC())) {

				if (i != 0) {
					jsonArray.add(jsonObject);
				}
				jsonObject = new JSONObject();

				jsonObject.put("businessDate", item.getBusinessDate());
				jsonObject.put("shopC", item.getShopC());
				jsonObject.put("shopN", item.getShopN());
				jsonObject.put("foodAmt", item.getFoodAmt());
				jsonObject.put("billNum", item.getBillNum());
				jsonObject.put("amtPerBill", item.getAmtPerBill());
				jsonObject.put("guestNum", item.getGuestNum());
				jsonObject.put("amtPerGuest", item.getAmtPerGuest());
				jsonObject.put("tableNum", item.getTableNum());
				jsonObject.put("amtPerTable", item.getAmtPerTable());
				jsonObject.put("guestPerTable", item.getGuestPerTable());
				jsonObject.put("disAmt", item.getDisAmt());
				jsonObject.put("roundAmt", item.getRoundAmt());
				jsonObject.put("presentAmt", item.getPresentAmt());
				jsonObject.put("oughtAmt", item.getOughtAmt());
				jsonObject.put("payAmt", item.getPayAmt());

				dateTagMap.put(item.getBusinessDate(), item.getBusinessDate());
				shopTagMap.put(item.getShopC(), item.getShopC());
			}

			JSONObject payJsonObject = new JSONObject();
			payJsonObject.put("payCode", item.getPayC());
			payJsonObject.put("payTypeAmt", item.getPayTypeAmt());
			jsonObject.put("pay_" + item.getPayC(), payJsonObject);

		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getData() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject jsonObject = null;

		Map<String, String> tagMap = new HashMap<String, String>();

		List<ShopPayment> shopPayInfos = shopPaymentBean.getShopPayInfo(startDate, endDate);

		shopPayInfos.add(new ShopPayment());

		for (int i = 0; i < shopPayInfos.size(); i++) {
			ShopPayment item = shopPayInfos.get(i);

			if (!tagMap.containsKey(item.getShopC())) {
				if (i != 0) {
					jsonArray.add(jsonObject);
				}

				jsonObject = new JSONObject();

				jsonObject.put("shopC", item.getShopC());
				jsonObject.put("shopN", item.getShopN());
				jsonObject.put("foodAmt", item.getFoodAmt());
				jsonObject.put("billNum", item.getBillNum());
				jsonObject.put("amtPerBill", item.getAmtPerBill());
				jsonObject.put("guestNum", item.getGuestNum());
				jsonObject.put("amtPerGuest", item.getAmtPerGuest());
				jsonObject.put("tableNum", item.getTableNum());
				jsonObject.put("amtPerTable", item.getAmtPerTable());
				jsonObject.put("guestPerTable", item.getGuestPerTable());
				jsonObject.put("disAmt", item.getDisAmt());
				jsonObject.put("roundAmt", item.getRoundAmt());
				jsonObject.put("presentAmt", item.getPresentAmt());
				jsonObject.put("oughtAmt", item.getOughtAmt());
				jsonObject.put("payAmt", item.getPayAmt());

				tagMap.put(item.getShopC(), item.getShopC());
			}

			JSONObject payJsonObject = new JSONObject();
			payJsonObject.put("payCode", item.getPayC());
			payJsonObject.put("payTypeAmt", item.getPayTypeAmt());
			jsonObject.put("pay_" + item.getPayC(), payJsonObject);

		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getColumn() throws NoPrivilegeException, SQLException, NoConnection {

		List<ShopPayment> shopPayInfos = shopPaymentBean.getShopPayInfo(startDate, endDate);

		JSONArray jsonArray = new JSONArray();
		JSONObject jObject = new JSONObject();
		Map<String, String> tagMap = new HashMap<String, String>();

		for (int i = 0; i < shopPayInfos.size(); i++) {
			ShopPayment item = shopPayInfos.get(i);
			if (!tagMap.containsKey(item.getPayC())) {
				jObject.put("pay_" + item.getPayC(), item.getPayN());
			}
		}

		jsonArray.add(jObject);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
