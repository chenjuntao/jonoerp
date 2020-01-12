package action.restaurant;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.PeriodBusinessBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class PeriodBusinessAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private PeriodBusinessBean periodBusinessBean;
	private BranchBean branchBean;

	private String startDate;
	private String endDate;
	private String shopC;

	private List<Map> shopLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		Map<String, String> allItem = new HashMap<String, String>();
		allItem.put("code", "");
		allItem.put("name", "--请选择--");
		shopLst.add(0, allItem);

		return SUCCESS;
	}

	public void listPeriodBussiness() throws NoPrivilegeException, SQLException, NoConnection {
		StringBuilder sb = new StringBuilder();
		sb.append(" 1=1 ");

		if (!TextUtil.isEmpty(startDate)) {
			sb.append(" AND DBUSINESS >= to_date('" + startDate + "', 'yyyy-mm-dd') ");
		}

		if (!TextUtil.isEmpty(endDate)) {
			sb.append(" AND DBUSINESS <= to_date('" + endDate + "', 'yyyy-mm-dd') ");
		}

		if (!TextUtil.isEmpty(shopC)) {
			sb.append(" AND b.CBRANCH_C = '" + shopC + "' ");
		}

		List<Map> mapLst = periodBusinessBean.listPeriodBusiness(sb.toString());
		JSONArray jsonArray = JSONArray.fromObject(mapLst);

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public PeriodBusinessBean getPeriodBusinessBean() {
		return periodBusinessBean;
	}

	public void setPeriodBusinessBean(PeriodBusinessBean periodBusinessBean) {
		this.periodBusinessBean = periodBusinessBean;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getStartDate() {
		if (TextUtil.isEmpty(startDate)) {
			startDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
		}
		return startDate;
	}

	public void setShopC(String shopC) {
		this.shopC = shopC;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		if (TextUtil.isEmpty(endDate)) {
			endDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
		}
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
}
