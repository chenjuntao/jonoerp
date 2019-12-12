package action.hq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.reportmanage.PriceChangeBean;
import logic.store.ItemPriceBean;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;

public class PriceChangeAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;

	private PriceChangeBean rPriceChangeBean;
	private ItemPriceBean itemPriceBean;
	private String itemId;

	private Double old_price;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());

		List<Map> listData = new ArrayList<Map>();

		setStartPrice(listData);
		listData.addAll(rPriceChangeBean.query(itemId, startDate, endDate));
		setEndPrice(listData);

		Map dataMap = new HashMap();
		dataMap.put("content", listData);
		dataMap.put("date", rPriceChangeBean.getEffectTime(itemId, ""));

		Double maxPrice = 0.0;
		for (Map map : listData) {
			Double new_price = 0.0;
			Double old_price = 0.0;

			if (map.containsKey("new_price")) {
				new_price = Double.valueOf(map.get("new_price").toString());

			}
			if (map.containsKey("old_price")) {
				old_price = Double.valueOf(map.get("old_price").toString());
			}

			if (new_price > maxPrice) {
				maxPrice = new_price;
			}

			if (new_price > maxPrice) {
				maxPrice = old_price;
			}

		}

		dataMap.put("maxPrice", maxPrice);

		JSONObject jObject = JSONObject.fromObject(dataMap, config);

		try {
			this.outJS(jObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 标准价 加盟价 零售价 批发价 进货价
	private String[] priceTypeArr = { "BENCHMARK", "JOIN", "RETAIL", "WHOLESALE", "PURCHASE" };

	// 加载各价格类型的起始价格
	// 若在价格变更表中未查询得到，则到D_T2_ITEM_PRICE表中查询
	private void setStartPrice(List<Map> listData) throws NoPrivilegeException, SQLException, NoConnection {
		for (int i = 0; i < priceTypeArr.length; i++) {
			String priceType = priceTypeArr[i];

			Double startPrice = rPriceChangeBean.getStartPrice(itemId, priceType, startDate, endDate);
			startPrice = startPrice == null ? itemPriceBean.getItemPrice(itemId, priceType) : startPrice;
			Map map = new HashMap();
			map.put("new_price", startPrice);
			map.put("adjust_type", priceType);
			listData.add(map);
		}

	}

	// 加载各价格类型的结束价格
	// 若在价格变更表中未查询得到，则到D_T2_ITEM_PRICE表中查询
	private void setEndPrice(List<Map> listData) throws NoPrivilegeException, SQLException, NoConnection {
		for (int i = 0; i < priceTypeArr.length; i++) {
			String priceType = priceTypeArr[i];

			Double endPrice = rPriceChangeBean.getEndPrice(itemId, priceType, startDate, endDate);
			endPrice = endPrice == null ? itemPriceBean.getItemPrice(itemId, priceType) : endPrice;
			Map map = new HashMap();
			map.put("new_price", endPrice);
			map.put("adjust_type", priceType);
			listData.add(map);
		}

	}

	public void setrPriceChangeBean(PriceChangeBean rPriceChangeBean) {
		this.rPriceChangeBean = rPriceChangeBean;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

	public Double getOld_price() {
		return old_price;
	}

}
