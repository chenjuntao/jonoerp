package action.restaurant.inventory;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.StorageInOutBean;
import logic.store.BranchBean;
import logic.store.BranchStorageBean;
import logic.store.ItemPriceBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.StorageInOut;
import pojo.form.StorageInOutSummary;
import pojo.store.BranchStorage;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.constant.PriceType;
import com.tanry.framework.util.constant.ReasonType;

public class InventoryManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private BranchBean branchBean;

	private Date startDate;
	private Date endDate;
	private Date defaultDate = DateTimeUtil.addDays(-1);

	private String branchId;
	private List<BranchStorage> storageList;

	private String formId;

	private List<Map> shopLst;

	private String selectedItemId;

	private String reason;

	private DecimalFormat df = new DecimalFormat("######0.00");

	private String branchType;

	private String brandWord;

	private BranchStorageBean branchStorageBean;

	private String storageId;

	private ItemPriceBean itemPriceBean;

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public void setBranchStorageBean(BranchStorageBean branchStorageBean) {
		this.branchStorageBean = branchStorageBean;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public String getBrandWord() {
		return BranchType.getBrandWord(branchType);
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

	private StorageInOutBean storageInOutBean;

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		shopLst = branchBean.queryById(getLoginBranchId());

		if (shopLst.size() > 0) {
			storageList = branchStorageBean.query(getLoginBranchId(), branchType);
		}

		branchId = getLoginBranchId();

		if (isMobile()) {
			return "mobile";
		}
		return SUCCESS;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	private String getDateTimeStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATETIME_FORMAT);
		}
		return "";
	}

	/**
	 * 出入库表记录比较大，所以一定要限制日期 开始日期和结束日期默认为当天的前一天
	 */
	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		int total = storageInOutBean.count(startDate, endDate, branchId, storageId, selectedItemId, reason, branchType);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<StorageInOut> storageInOuts = storageInOutBean.query(startDate, endDate, branchId, storageId,
					selectedItemId, reason, branchType, getStart(), getEnd());
			int rownumber = getStart();
			for (StorageInOut storageInOut : storageInOuts) {
				JSONObject json = JSONObject.fromObject(storageInOut);

				json.put("branchName", storageInOut.getBranchName());
				json.put("storageName", storageInOut.getStorageName());

				// 结存单价
				Double itemUnitPrice = storageInOut.getItemUnitPrice();
				if (itemUnitPrice == null) {
					itemUnitPrice = 0.0;
				}
				json.put("itemUnitPrice", itemUnitPrice);

				// 原料编码
				json.put("itemId", storageInOut.getItemId());

				// 原料名称
				json.put("itemName", storageInOut.getItemName());

				// 操作时间
				json.put("operationTime", getDateTimeStr(storageInOut.getOperationTime()));

				// 单据日期
				json.put("businessDate", getDateStr(storageInOut.getBusinessDate()));

				// 单据号码
				json.put("formId", storageInOut.getFormId());

				// 业务类型
				json.put("reason", ReasonType.getReasonWord(storageInOut.getReason(), branchType));

				// 期初数量
				json.put("orgiCount", df.format(storageInOut.getOrgiCount()));

				// 入库数量
				Double itemCountIn = storageInOut.getItemCountIn();
				json.put("itemCountIn", itemCountIn);

				// 入库金额
				json.put("itemCountInMoney", df.format(itemCountIn * itemUnitPrice));

				// 出库数量
				Double itemCountOut = storageInOut.getItemCountOut();
				json.put("itemCountOut", itemCountOut);

				// 出库金额
				json.put("itemCountOutMoney", df.format(itemCountOut * itemUnitPrice));

				// 结存数量
				Double resultCount = storageInOut.getResultCount();
				json.put("resultCount", df.format(resultCount));

				// 结存金额
				json.put("resultCountMoney", df.format(resultCount * itemUnitPrice));

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}

			// // 合计
			if (total < getEnd()) {
				StorageInOut storageInOut = storageInOutBean.detailSum(startDate, endDate, branchId, storageId,
						selectedItemId, reason, branchType);
				JSONObject sumJObject = JSONObject.fromObject(storageInOut);
				sumJObject.put("rownumber", "合计");
				sumJObject.put("itemCountIn", storageInOut.getItemCountIn());
				sumJObject.put("itemCountInMoney", storageInOut.getItemCountInMoney());
				sumJObject.put("itemCountOut", storageInOut.getItemCountOut());
				sumJObject.put("itemCountOutMoney", storageInOut.getItemCountOutMoney());
				arr.add(sumJObject);
			}
		}
		if (isMobile()) {
			JSONObject result = new JSONObject();
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("iTotalDisplayRecords", total);
			result.put("data", arr);// for DataTables
			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSummaryQuery() throws NoPrivilegeException, SQLException, NoConnection {

		int total = storageInOutBean.summaryCount(startDate, endDate, branchId, storageId, branchType, selectedItemId);
		setTotal(total);

		JSONArray arr = new JSONArray();
		if (total > 0) {
			List<StorageInOutSummary> storageInOutSummaries = storageInOutBean.summaryQuery(startDate, endDate,
					branchId, storageId, branchType, selectedItemId, getStart(), getEnd());
			int rownumber = getStart();
			for (StorageInOutSummary storageInOutSummary : storageInOutSummaries) {
				JSONObject json = JSONObject.fromObject(storageInOutSummary);

				// 原料编码
				String itemId = storageInOutSummary.getItemId();
				json.put("itemId", itemId);

				// 单价 餐厅直接取标准价
				Double itemUnitPrice = itemPriceBean.getItemPrice(itemId, PriceType.BENCHMARK);
				itemUnitPrice = itemUnitPrice == null ? 0.0 : itemUnitPrice;
				json.put("itemUnitPrice", itemUnitPrice);

				// 原料名称
				json.put("itemName", storageInOutSummary.getItemName());

				// 单位
				json.put("unit", storageInOutSummary.getUnit());

				// 期初数量
				Double orgicount = storageInOutSummary.getOrgiCount();
				json.put("orgicount", df.format(orgicount));

				// 期初金额
				json.put("orgicountmoney", df.format(orgicount * itemUnitPrice));

				// 入库数量合计
				Double itemCountIn = storageInOutSummary.getItemCountIn();
				json.put("itemCountIn", itemCountIn);

				// 入库金额合计
				json.put("itemCountInMoney", df.format(storageInOutSummary.getItemCountInMoney()));

				// 出库数量合计
				Double itemCountOut = storageInOutSummary.getItemCountOut();
				json.put("itemCountOut", itemCountOut);

				// 出库金额合计
				json.put("itemCountOutMoney", df.format(storageInOutSummary.getItemCountOutMoney()));

				// 结存数量 = 期初数量 + 入库数量 - 出库数量
				Double resultcount = orgicount + itemCountIn - itemCountOut;
				json.put("resultcount", df.format(resultcount));

				// 结存金额
				json.put("resultcountmoney", df.format(resultcount * itemUnitPrice));

				// 采购入库数量
				json.put("putinstorage", storageInOutSummary.getPutinstorage());

				// 配送入库数量
				json.put("distribution", storageInOutSummary.getDistribution());

				// 配送反审核入库数量
				json.put("antiauditIn", storageInOutSummary.getAntiauditIn());

				// 配送反审核出库数量
				json.put("antiauditOut", storageInOutSummary.getAntiauditOut());

				// 配送退货数量
				json.put("dreject", storageInOutSummary.getDreject());

				// 采购退货数量
				json.put("preject", storageInOutSummary.getPreject());

				// 原料报损数量
				json.put("rawLoss", storageInOutSummary.getRawLoss());

				// 出品报损数量
				json.put("dishLoss", storageInOutSummary.getDishLoss());

				// 调拨入库数量
				json.put("allocateitemIn", storageInOutSummary.getAllocateitemIn());

				// 调拨出库数量
				json.put("allocateitemOut", storageInOutSummary.getAllocateitemOut());

				// 盘点入库数量
				json.put("checkstorageIn", storageInOutSummary.getCheckstorageIn());

				// 盘点出库数量
				json.put("checkstorageOut", storageInOutSummary.getCheckstorageOut());

				// 理论扣库数量
				json.put("theoryDeduct", storageInOutSummary.getTheoryDeduct());

				// 门店名称
				json.put("branchName", storageInOutSummary.getBranchName());

				// 仓库名称
				json.put("storageName", storageInOutSummary.getStorageName());

				// 原料类别
				json.put("itemCategory", storageInOutSummary.getItemCategory());

				json.put("rownumber", rownumber);
				arr.add(json);
				rownumber++;
			}
		}
		if (isMobile()) {
			JSONObject result = new JSONObject();
			result.put("recordsTotal", total);
			result.put("recordsFiltered", total);
			result.put("iTotalDisplayRecords", total);
			result.put("data", arr);// for DataTables
			try {
				this.outJS(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(defaultDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(defaultDate);
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchId() {
		return branchId;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

}
