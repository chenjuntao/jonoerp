package service.reportmanage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.special.BranchIncomeBean;
import logic.special.DepartmentIncomeBean;
import logic.special.FoodCTRBean;
import logic.special.LossCheckBean;
import logic.special.RequestBranchBean;
import logic.special.TimeIncomeBean;
import logic.store.BranchBean;

import com.tanry.framework.acl.NoPrivilegeException;

public class SpecialReportManageService {

	private BranchIncomeBean rBranchIncomeBean;
	private RequestBranchBean rRequestBranchBean;
	private DepartmentIncomeBean rDepartmentIncomeBean;
	private TimeIncomeBean rTimeIncomeBean;

	private FoodCTRBean foodCTRBean;
	private LossCheckBean lossCheckBean;
	
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	private final String BRANCH_INCOME = "branchIncome";
	private final String REQUEST_COLLECT = "REQUEST_COLLECT";
	private final String DEPARTMENT_INCOME = "DEPARTMENT_INCOME";
	private final String TIME_INCOME = "TIME_INCOME";

	private final String FOOD_CRT = "FOOD_CRT";

	private final String LOSS_CHECK = "LOSS_CHECK";

	public int count(String flag, String queryStr) throws NoPrivilegeException, SQLException, NoConnection {
		if (BRANCH_INCOME.equals(flag)) {
			return rBranchIncomeBean.count(queryStr);
		} else if (REQUEST_COLLECT.equals(flag)) {
			return rRequestBranchBean.count(queryStr);
		} else if (DEPARTMENT_INCOME.equals(flag)) {
			return rDepartmentIncomeBean.count(queryStr);
		} else if (TIME_INCOME.equals(flag)) {
			return rTimeIncomeBean.count(queryStr);
		} else if (FOOD_CRT.equals(flag)) {
			return foodCTRBean.count(queryStr);
		} else if (LOSS_CHECK.equals(flag)) {
			return lossCheckBean.count(queryStr);
		}

		return 0;
	}

	public List<Map> query(String flag, String queryStr, int start, int end) throws NoPrivilegeException, SQLException,
			NoConnection {
		if (BRANCH_INCOME.equals(flag)) {
			return rBranchIncomeBean.query(queryStr, start, end);
		} else if (REQUEST_COLLECT.equals(flag)) {
			return rRequestBranchBean.query(queryStr, start, end);
		} else if (DEPARTMENT_INCOME.equals(flag)) {
			return rDepartmentIncomeBean.query(queryStr, start, end);
		} else if (TIME_INCOME.equals(flag)) {
			return rTimeIncomeBean.query(queryStr, start, end);
		} else if (FOOD_CRT.equals(flag)) {
			return foodCTRBean.query(queryStr, start, end);
		} else if (LOSS_CHECK.equals(flag)) {
			return lossCheckBean.query(queryStr, start, end);
		}

		return null;
	}

	public List<Map> queryBranch(String queryStr) throws NoPrivilegeException, SQLException, NoConnection {
		return rRequestBranchBean.queryBranch(queryStr);
	}

	public Date getBusinessByBranchId(String loginBranchId) throws NoPrivilegeException, SQLException, NoConnection {
		return branchBean.GetBranchById(loginBranchId).getBusinessDate();
	}


	public void setrBranchIncomeBean(BranchIncomeBean rBranchIncomeBean) {
		this.rBranchIncomeBean = rBranchIncomeBean;
	}

	public void setrRequestBranchBean(RequestBranchBean rRequestBranchBean) {
		this.rRequestBranchBean = rRequestBranchBean;
	}

	public void setrDepartmentIncomeBean(DepartmentIncomeBean rDepartmentIncomeBean) {
		this.rDepartmentIncomeBean = rDepartmentIncomeBean;
	}

	public void setrTimeIncomeBean(TimeIncomeBean rTimeIncomeBean) {
		this.rTimeIncomeBean = rTimeIncomeBean;
	}

	public void setFoodCTRBean(FoodCTRBean foodCTRBean) {
		this.foodCTRBean = foodCTRBean;
	}

	public void setLossCheckBean(LossCheckBean lossCheckBean) {
		this.lossCheckBean = lossCheckBean;
	}

}
