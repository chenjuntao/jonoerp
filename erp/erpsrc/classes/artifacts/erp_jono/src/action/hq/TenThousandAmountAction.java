package action.hq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.reportmanage.TenThousandAmountBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class TenThousandAmountAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;

	private TenThousandAmountBean rTenThousandAmountBean;
	private BranchBean branchBean;

	private String itemId;

	private String branchId;

	private List<Map> shopList;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopList = branchBean.listShopByType(BranchTypeEnum.RESTAURANT);
		endDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		startDate = DateTimeUtil.addMonths(endDate, -1);

		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {

		Double amt = rTenThousandAmountBean.getAmt(startDate, endDate, branchId);
		JSONArray jsonArray = new JSONArray();

		if (amt != null) {
			if (amt != 0) {
				jsonArray = JSONArray.fromObject(rTenThousandAmountBean
						.query(branchId, itemId, amt, startDate, endDate));
			}
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

	public void setrTenThousandAmountBean(TenThousandAmountBean rTenThousandAmountBean) {
		this.rTenThousandAmountBean = rTenThousandAmountBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public List<Map> getShopList() {
		return shopList;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

}
