package action.centralfactory.productionDemand.arrangement;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.module.cf.ArrangementQueryBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ArrangmentHeader;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;

public class PlanMangeAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ArrangementQueryBean arrangementQueryBean;

	private Date startDate;
	private Date endDate;

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = arrangementQueryBean.count(startDate, endDate);

		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<ArrangmentHeader> headerLst = arrangementQueryBean.query(startDate, endDate, getStart(), getEnd());

			int rownumber = getStart();
			for (ArrangmentHeader header : headerLst) {
				JSONObject json = JSONObject.fromObject(header);
				String formTime = DateTimeUtil.getDateStr(header.getFormTime());
				json.put("formTime", formTime);
				String formTimeActual = getDateStr(header.getFormTimeActual(), DateTimeUtil.DEFAULT_DATETIME_FORMAT);
				json.put("formTimeActual", formTimeActual);
				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDateStr(Date date, String format) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, format);
		}
		return "";
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setArrangementQueryBean(ArrangementQueryBean arrangementQueryBean) {
		this.arrangementQueryBean = arrangementQueryBean;
	}

}
