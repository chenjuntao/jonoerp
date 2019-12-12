package action.restaurant.export;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.StorageInOutBean;
import logic.store.DailyBranchStorageBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Workbook;

import pojo.form.StorageInOut;
import pojo.form.StorageInOutSummary;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.ReasonType;
import com.tanry.framework.util.xls.ExportUtil;

public class StorageInOutExportAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private Date startDate;
	private Date endDate;
	private String branchId;
	private StorageInOutBean storageInOutBean;
	private DailyBranchStorageBean dailyBranchStorageBean;

	private DecimalFormat df = new DecimalFormat("######0.00");

	private String selectedItemId;

	private String reason;

	private String branchType;

	private String storageId;

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getBranchType() {
		return branchType;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setSelectedItemId(String selectedItemId) {
		this.selectedItemId = selectedItemId;
	}

	public void setDailyBranchStorageBean(
			DailyBranchStorageBean dailyBranchStorageBean) {
		this.dailyBranchStorageBean = dailyBranchStorageBean;
	}

	private String type;

	public void setType(String type) {
		this.type = type;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	/**
	 * 从前端获取导出结构
	 */
	private String jsonData;

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date,
					DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	private String getDateTimeStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date,
					DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		}
		return "";
	}

	public void detailExport() throws NoPrivilegeException, SQLException,
			NoConnection {
		List<StorageInOut> storageInOuts = storageInOutBean.query(startDate,
				endDate, branchId, storageId, selectedItemId, reason,
				branchType, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> detailLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (StorageInOut obj : storageInOuts) {

			StorageInOut item = (StorageInOut) obj;
			Map<String, Object> detailMap = new HashMap<String, Object>();
			detailMap.put("rowNumber", rowNumber);

			detailMap.put("branchName", item.getBranchName());
			detailMap.put("storageName", item.getStorageName());

			Double itemUnitPrice = item.getItemUnitPrice() == null ? 0 : item
					.getItemUnitPrice();
			detailMap.put("itemUnitPrice", itemUnitPrice);

			detailMap.put("itemId", item.getItemId());

			String itemName = item.getItemName() == null ? "" : item
					.getItemName();
			detailMap.put("itemName", itemName);

			detailMap.put("operationTime",
					getDateTimeStr(item.getOperationTime()));

			detailMap.put("businessDate", getDateStr(item.getBusinessDate()));

			String formId = item.getFormId();
			detailMap.put("formId", TextUtil.isEmpty(formId) ? "" : formId);

			detailMap.put("reason",
					ReasonType.getReasonWord(item.getReason(), branchType));

			detailMap.put("orgiCount", item.getOrgiCount());

			detailMap.put("itemCountIn", item.getItemCountIn());

			Double itemCountInMoney = item.getItemCountIn() * itemUnitPrice;
			detailMap.put("itemCountInMoney", df.format(itemCountInMoney));

			detailMap.put("itemCountOut", item.getItemCountOut());

			Double itemCountOutMoney = item.getItemCountOut() * itemUnitPrice;
			detailMap.put("itemCountOutMoney", df.format(itemCountOutMoney));

			detailMap.put("resultCount", item.getResultCount());

			Double resultCountMoney = item.getResultCount() * itemUnitPrice;
			detailMap.put("resultCountMoney", df.format(resultCountMoney));

			detailLst.add(detailMap);

			rowNumber++;
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, detailLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(detailLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void summaryExport() throws NoPrivilegeException, SQLException,
			NoConnection {
		Date orgicountDate = DateTimeUtil.addDays(startDate, -1);

		List<StorageInOutSummary> storageInOuts = storageInOutBean
				.summaryQuery(startDate, endDate, branchId, storageId,
						branchType, selectedItemId, 0, Integer.MAX_VALUE);

		JSONObject structure = JSONObject.fromObject(jsonData);
		JSONArray colsJA = structure.getJSONArray("columns");

		List<Map<String, Object>> detailLst = new ArrayList<Map<String, Object>>();

		int rowNumber = 1;
		for (StorageInOutSummary obj : storageInOuts) {

			StorageInOutSummary item = (StorageInOutSummary) obj;
			Map<String, Object> detailMap = new HashMap<String, Object>();
			Double itemUnitPrice = item.getItemUnitPrice() == null ? 0 : item
					.getItemUnitPrice();
			detailMap.put("itemUnitPrice", itemUnitPrice);

			detailMap.put("rowNumber", rowNumber);

			String itemId = item.getItemId();
			detailMap.put("itemId", itemId);
			String itemName = item.getItemName() == null ? "" : item
					.getItemName();
			detailMap.put("itemName", itemName);

			detailMap.put("unit", item.getUnit());

			Double orgiCount = item.getOrgiCount();
			detailMap.put("orgiCount", orgiCount);
			detailMap.put("orgiCountmoney",
					df.format(orgiCount * itemUnitPrice));

			detailMap.put("itemCountIn", item.getItemCountIn());
			detailMap.put("itemCountInMoney", item.getItemCountInMoney());

			detailMap.put("itemCountOut", item.getItemCountOut());
			detailMap.put("itemCountOutMoney", item.getItemCountOutMoney());

			Double resultCount = orgiCount + item.getItemCountIn()
					- item.getItemCountOut();
			detailMap.put("resultCount", resultCount);
			detailMap.put("resultCountMoney",
					df.format(resultCount * itemUnitPrice));

			detailMap.put("putinstorage", item.getPutinstorage());
			detailMap.put("distribution", item.getDistribution());
			detailMap.put("antiauditIn", item.getAntiauditIn());
			detailMap.put("antiauditOut", item.getAntiauditOut());
			detailMap.put("dreject", item.getDreject());
			detailMap.put("preject", item.getPreject());
			detailMap.put("rawLoss", item.getRawLoss());
			detailMap.put("dishLoss", item.getDishLoss());
			detailMap.put("allocateitemIn", item.getAllocateitemIn());
			detailMap.put("allocateitemOut", item.getAllocateitemOut());
			detailMap.put("checkstorageIn", item.getCheckstorageIn());
			detailMap.put("checkstorageOut", item.getCheckstorageOut());
			detailMap.put("theoryDeduct", item.getTheoryDeduct());

			detailMap.put("branchName", item.getBranchName());
			detailMap.put("storageName", item.getStorageName());
			detailMap.put("itemCategory", item.getItemCategory());

			detailLst.add(detailMap);

			rowNumber++;
		}
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, detailLst);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		structure.put("columns", colsJA);

		JSONArray arr = JSONArray.fromObject(detailLst);
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		Workbook wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
