package action.hq;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.reportmanage.CfProduceBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateJsonValueProcessor;
import com.tanry.framework.util.DateTimeUtil;

public class ProduceAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	private Date completeDate;
	private CfProduceBean rCfProduceBean;
	private BranchBean branchBean;
	
	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		completeDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JsonConfig config = new JsonConfig();
		config.registerJsonValueProcessor(java.sql.Date.class, new DateJsonValueProcessor());

		List<Map> query = rCfProduceBean.query("", completeDate);
		JSONArray arr = JSONArray.fromObject(query, config);

		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setCompleteDate(Date completeDate) {
		this.completeDate = completeDate;
	}

	public String getCompleteDate() {
		return DateTimeUtil.getDateStr(completeDate);
	}

	public void setrCfProduceBean(CfProduceBean rCfProduceBean) {
		this.rCfProduceBean = rCfProduceBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

}
