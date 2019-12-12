//package action.businessquery.export;
//
//import java.io.IOException;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import logic.NoConnection;
//import logic.store.PresentBean;
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//
//import org.apache.poi.ss.usermodel.Workbook;
//
//import action.common.BaseAction;
//
//import com.tanry.framework.acl.NoPrivilegeException;
//import com.tanry.framework.util.CsvUtil;
//import com.tanry.framework.util.DateTimeUtil;
//import com.tanry.framework.util.TextUtil;
//import com.tanry.framework.util.xls.ExportUtil;
//
//public class PresentExportAction extends BaseAction {
//
//	private static final long serialVersionUID = 1L;
//
//	private String startDate;
//	private String endDate;
//	private String shopC;
//	private String period;
//	private String shift;
//
//	private PresentBean presentBean;
//	private String type;
//
//	public void setPresentBean(PresentBean presentBean) {
//		this.presentBean = presentBean;
//	}
//
//	public void setPeriod(String period) {
//		this.period = period;
//	}
//
//	public void setShift(String shift) {
//		this.shift = shift;
//	}
//
//	public void setType(String type) {
//		this.type = type;
//	}
//
//	/**
//	 * 从前端获取导出结构
//	 */
//	private String jsonData;
//
//	public void setJsonData(String jsonData) {
//		this.jsonData = jsonData;
//	}
//
//	public void setStartDate(String startDate) {
//		this.startDate = startDate;
//	}
//
//	public void setEndDate(String endDate) {
//		this.endDate = endDate;
//	}
//
//	public void setShopC(String shopC) {
//		this.shopC = shopC;
//	}
//
//	public String getStartDate() {
//		if (TextUtil.isEmpty(startDate)) {
//			startDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
//		}
//		return startDate;
//	}
//
//	public String getEndDate() {
//		if (TextUtil.isEmpty(endDate)) {
//			endDate = DateTimeUtil.getDateOff(DateTimeUtil.getDate(), -1);
//		}
//		return endDate;
//	}
//
//	public void export() throws NoPrivilegeException, SQLException,
//			NoConnection {
//
//		if (TextUtil.isEmpty(shopC)) {
//			shopC = "%%";
//		}
//
//		if (TextUtil.isEmpty(period)) {
//			period = "%%";
//		}
//
//		if (TextUtil.isEmpty(shift)) {
//			shift = "%%";
//		}
//
//		List<PresentBean> presentBeans = presentBean.getPresent(startDate,
//				endDate, shopC, period, shift, 0, Integer.MAX_VALUE);
//
//		JSONObject structure = JSONObject.fromObject(jsonData);
//		JSONArray colsJA = structure.getJSONArray("columns");
//
//		List<Map<String, Object>> presentCodeLst = new ArrayList<Map<String, Object>>();
//
//		for (PresentBean obj : presentBeans) {
//			PresentBean item = (PresentBean) obj;
//			Map<String, Object> presentMap = new HashMap<String, Object>();
//			presentMap.put("businessDate", item.getBusinessDate());
//			presentMap.put("billC", item.getBillC());
//			presentMap.put("period", item.getPeriod());
//			presentMap.put("shift", item.getShift());
//			presentMap.put("table", item.getTable());
//			presentMap.put("foodName", item.getFoodName());
//			presentMap.put("unit", item.getUnit());
//			presentMap.put("presentAmount", item.getPresentAmount());
//			presentMap.put("price", item.getPrice());
//			presentMap.put("presentPrice", item.getPresentPrice());
//			presentMap.put("extracPrice", item.getExtracPrice());
//			presentMap.put("presentMan", item.getPresentMan());
//			presentMap.put("presentWhy", item.getPresentWhy());
//			presentMap.put("shopC", item.getShopC());
//			presentMap.put("shopN", item.getShopN());
//
//			presentMap.put("bigC", item.getBigC());
//			presentMap.put("bigN", item.getBigN());
//
//			presentCodeLst.add(presentMap);
//		}
//		String sheetName = structure.getString("sheetName");
//		if ("csv".equals(type)) {
//			StringBuffer sb = CsvUtil.exportCsv(colsJA, presentCodeLst);
//			try {
//				this.outCsv(sheetName + ".csv", sb);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//			return;
//		}
//
//		structure.put("columns", colsJA);
//
//		JSONArray arr = JSONArray.fromObject(presentCodeLst);
//		JSONObject data = new JSONObject();
//		data.put("rows", arr);
//		Workbook wb = ExportUtil.export(structure, data);
//		try {
//			this.outXls(sheetName + ".xls", wb);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
// }
