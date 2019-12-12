/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 19, 2014 by cjt
 * Last edited on Sep 19, 2014 by cjt
 * 
 * 说明：  餐厅报损单事务控制
 */
package service.restaurant.production;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.SelfSemisDetailBean;
import logic.form.SelfSemisHeaderBean;
import logic.form.StorageInOutBean;
import logic.store.BranchBean;
import logic.store.ItemMetaBean;
import logic.store.StorageBean;
import logic.store.TherapyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.SelfSemisDetail;
import pojo.form.SelfSemisHeader;
import pojo.form.StorageInOut;
import pojo.store.Therapy;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;

public class ManageSelfBillService {

	private SelfSemisDetailBean selfSemisDetailBean;
	private SelfSemisHeaderBean selfSemisHeaderBean;
	private SelfSemisHeader selfSemisHeader;
	private SelfSemisDetail selfSemisDetail;
	private FormStatusBean formStatusBean;
	/**
	 * D_T2_STORAGE 只存储仓库信息，仓库ID是唯一的
	 */
	private StorageBean storageBean;
	private TherapyBean therapyBean;
	private ItemMetaBean itemMetaBean;

	private StorageInOutBean storageInOutBean;

	private BranchBean branchBean;

	private OperationVersionBean operationVersionBean;

	// 半成品原料同时保存
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String CreateSelfBill(SelfSemisHeader header, String jsonData, String rawJsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String prefix = "BCR";// 半成品自产入库
		String formBody = FormUtil.genFormIdBody(prefix, header.getBranchId(), header.getFormTime());
		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);
		header.setFormId(formId);

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double maxCount = 0.0;
		String maxCountItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			SelfSemisDetail detail = new SelfSemisDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = "FOOD";// 用来区别是原料还是半成品
			detail.setItemSpecification(itemSpecification);
			Double itemCountActual = json.getDouble("itemCountActual");
			detail.setItemCountActual(itemCountActual);
			if (itemCountActual > maxCount) {
				maxCount = itemCountActual;
				maxCountItem = "[" + itemId + "]" + itemName;
			}

			selfSemisDetailBean.saveEntity(detail);
		}

		JSONArray rawArr = JSONArray.fromObject(rawJsonData);
		for (Object obj : rawArr) {
			JSONObject json = (JSONObject) obj;
			SelfSemisDetail detail = new SelfSemisDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);
			Double itemCountActual = json.getDouble("itemCountActual");
			detail.setItemCountActual(itemCountActual);
			// if (itemCountActual > maxCount) {
			// maxCount = itemCountActual;
			// maxCountItem = "[" + itemId + "]" + itemName;
			// }

			selfSemisDetailBean.saveEntity(detail);
		}

		header.setMainItem(maxCountItem);
		selfSemisHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", header.getCreatorManId()));
		OperateVersionUtil.save(formId);
		OperateLogUtil.record(formId, LogType.CREATE, "新增半成品自产入库单");

		return formId;
	}

	// 只保存半成品，不保存原料
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String CreateSelfBill(SelfSemisHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String prefix = "BCR";// 半成品自产入库
		String formBody = FormUtil.genFormIdBody(prefix, header.getBranchId(), header.getFormTime());
		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);
		header.setFormId(formId);

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double maxCount = 0.0;
		String maxCountItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			SelfSemisDetail detail = new SelfSemisDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = "FOOD";// 用来区别是原料还是半成品
			detail.setItemSpecification(itemSpecification);
			Double itemCountActual = json.getDouble("itemCountActual");
			detail.setItemCountActual(itemCountActual);
			if (itemCountActual > maxCount) {
				maxCount = itemCountActual;
				maxCountItem = "[" + itemId + "]" + itemName;
			}

			selfSemisDetailBean.saveEntity(detail);
		}

		header.setMainItem(maxCountItem);
		selfSemisHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", header.getCreatorManId()));
		OperateVersionUtil.save(formId);
		OperateLogUtil.record(formId, LogType.CREATE, "新增半成品自产入库单");

		return formId;
	}

	/**
	 * 餐厅半成品入库单修改
	 * 
	 * @param header
	 * @param jsonData
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void UpdateSelfBill(SelfSemisHeader header, String jsonData, String rawJsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double maxCount = 0.0;
		String maxCountItem = "";

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			SelfSemisDetail detail = new SelfSemisDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			Double itemCountActual = json.getDouble("itemCountActual");
			detail.setItemCountActual(itemCountActual);
			if (itemCountActual > maxCount) {
				maxCount = itemCountActual;
				maxCountItem = "[" + itemId + "]" + itemName;
			}

			selfSemisDetailBean.updateEntity(detail);
		}

		JSONArray rawArr = JSONArray.fromObject(rawJsonData);
		for (Object obj : rawArr) {
			JSONObject json = (JSONObject) obj;
			SelfSemisDetail detail = new SelfSemisDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);
			Double itemCountActual = json.getDouble("itemCountActual");
			detail.setItemCountActual(itemCountActual);
			detail.setItemCountPlan(json.getDouble("itemCountPlan"));

			selfSemisDetailBean.saveEntity(detail);
		}

		header.setMainItem(maxCountItem);
		selfSemisHeaderBean.updateEntity(header);

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doAudit(SelfSemisHeader header, String jsonData, String rawJsonData, Integer currentVersion,
			String storageId) throws NoPrivilegeException, SQLException, NoConnection {
		UpdateSelfBill(header, jsonData, rawJsonData);// 审核之前先更改数据
		AuditSelfBill(header, currentVersion, storageId);
	}

	// 仅修改半成品
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void UpdateSelfBill(SelfSemisHeader header, Integer currentVersion, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double maxCount = 0.0;
		String maxCountItem = "";

		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			SelfSemisDetail detail = new SelfSemisDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			Double itemCountActual = json.getDouble("itemCountActual");
			detail.setItemCountActual(itemCountActual);
			if (itemCountActual > maxCount) {
				maxCount = itemCountActual;
				maxCountItem = "[" + itemId + "]" + itemName;
			}

			selfSemisDetailBean.updateEntity(detail);
		}

		header.setMainItem(maxCountItem);
		selfSemisHeaderBean.updateEntity(header);

		OperateLogUtil.record(formId, LogType.UPDATE, "编辑半成品入库单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.EDITED);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void AuditSelfBill(SelfSemisHeader header, Integer currentVersion, String storageId)
			throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		String autidor = header.getAuditor();
		String autidorId = header.getAuditorId();

		// 门店ID
		String branchId = header.getBranchId();

		Date businessDate = branchBean.GetBranchById(branchId).getBusinessDate();

		selfSemisHeaderBean.audit(formId, autidorId, autidor, header.getAuditTime());

		// -----------------------------------------
		// 操作时间
		Date operationTime = DateTimeUtil.getNow();
		List<SelfSemisDetail> detailLst = new ArrayList<SelfSemisDetail>();
		detailLst = selfSemisDetailBean.queryDetail(header.getFormId(), "ALL");
		// -----------------------------------------
		Map<String, Double> storageMap = selfSemisDetailBean.queryStorage(formId, storageId);

		for (SelfSemisDetail detail : detailLst) {

			String itemId = detail.getItemId();

			// -----------------------------------------
			// 出入库记录

			// 入库数量
			Double itemCountIn = 0.0;

			// 期初数量
			Double orgiCount = storageMap.get(itemId);
			// 出库数量
			Double itemCountOut = 0.0;
			Double itemCount = 0.0;

			String reason = "SSRK";
			if ("FOOD".equals(detail.getItemSpecification())) {
				itemCount = itemCountIn = detail.getItemCountActual();
			} else {
				itemCountOut = detail.getItemCountActual();
				itemCount = -itemCountOut;
				reason = "SSHL";
			}
			// 结存数量
			Double resultCount = orgiCount + itemCount;

			storageMap.put(itemId, resultCount);
			StorageInOut storageInOut = new StorageInOut(branchId, storageId, businessDate, operationTime, itemId, 1.0,
					orgiCount, itemCountIn, itemCountOut, resultCount, formId, reason);

			storageInOutBean.saveEntity(storageInOut);

			// 更新实时库存
			storageBean.updateItemCount(itemId, storageId, itemCount);
		}

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.AUDITED_CN, autidorId));

		OperateLogUtil.record(formId, LogType.AUDIT, "审核半成品入库单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.AUDITED);
	}

	/**
	 * 餐厅报损单刪除
	 * 
	 * @param formId
	 * @throws NoConnection
	 * @throws SQLException
	 * @throws NoPrivilegeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteSelfBill(String formId, Integer currentVersion) throws NoPrivilegeException, SQLException,
			NoConnection {
		selfSemisHeaderBean.deleteEntity(formId);
		selfSemisDetailBean.deleteEntity(formId);
		OperateLogUtil.record(formId, LogType.DELETE, "删除半成品入库单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.DELETED);
	}

	// 根据出品转换为原料，多出品不合并原料
	public String getRawFromDishs(String dishJsonData) throws NoPrivilegeException, SQLException, NoConnection {
		DecimalFormat df2 = new DecimalFormat("#0.0000"); // 小数保留两位

		JSONArray dishArr = JSONArray.fromObject(dishJsonData);
		Map<String, Double> countMap = new HashMap<String, Double>();// 多个出品中暂存报损数量方便后面计算金额
		String dishIds = "";// 保存所有出品id，方便一次性查询原料
		for (Object obj : dishArr) {
			JSONObject dishJson = (JSONObject) obj;
			String dishId = "";
			if (dishJson.containsKey("itemId")) {
				dishId = dishJson.getString("itemId");
			} else {
				dishId = dishJson.getString("item_id");
			}
			dishIds += dishId + ",";
			double lossCount = 0;
			if (dishJson.containsKey("lossNum")) {
				lossCount = dishJson.getDouble("lossNum");
			} else {
				lossCount = dishJson.getDouble("item_count");
			}
			countMap.put(dishId, lossCount);

		}

		List<Therapy> itemList = therapyBean.GetTherapyItems(dishIds);// 数据库多品牌原料一次查询出来
		int rownumber = 1;
		JSONArray rawArr = new JSONArray();
		for (Therapy therapy : itemList) {
			String dishId = therapy.getTherapyId();

			Double benchmarkPrice = therapy.getBenchmarkPrice();
			if (benchmarkPrice == null) {
				benchmarkPrice = 0.0;
			}
			double lossCount = countMap.get(dishId);
			// 原耗料量， 在前台与净要耗料量使用相同的单位（配方单位），在后台使用库存单位
			BigDecimal itemCount = new BigDecimal(therapy.getItemGrossCount()).multiply(new BigDecimal(lossCount));

			BigDecimal benchmarkAmt = new BigDecimal(0);
			if (benchmarkPrice != null) {
				benchmarkAmt = itemCount.multiply(new BigDecimal(benchmarkPrice)).setScale(4,
						BigDecimal.ROUND_HALF_EVEN);
			}

			JSONObject json = new JSONObject();
			json.put("rownumber", rownumber);
			json.put("itemId", therapy.getItemId());
			json.put("itemName", therapy.getItemName());
			json.put("itemDimension", therapy.getItemDimension());
			json.put("therapyId", therapy.getTherapyId());
			json.put("therapyName", therapy.getTherapyName());
			json.put("therapyDimension", therapy.getTherapyDimension());
			json.put("itemSpecification", therapy.getItemSpecification());
			json.put("itemCategory", therapy.getItemCategory());
			json.put("lossNum", df2.format(itemCount));
			json.put("therapyNum", df2.format(lossCount));
			json.put("itemUnitPrice", benchmarkPrice);
			json.put("payAmt", benchmarkAmt);
			rawArr.add(json);
			rownumber++;
		}
		return rawArr.toString();
	}

	// 根据出品转换为原料,多出品原料合并
	public String getRawFromDishs2(String dishJsonData) throws NoPrivilegeException, SQLException, NoConnection {
		DecimalFormat df2 = new DecimalFormat("#0.0000"); // 小数保留两位

		JSONArray dishArr = JSONArray.fromObject(dishJsonData);
		Map<String, SelfSemisDetail> detailMap = new HashMap<String, SelfSemisDetail>();// 多个出品中如果有相同的物料成分，则合并数量与金额
		for (Object obj : dishArr) {
			JSONObject dishJson = (JSONObject) obj;
			String therapyId = dishJson.getString("itemId");
			double itemCountActual = dishJson.getDouble("itemCountActual");
			List<Therapy> itemList = therapyBean.GetTherapyItems(therapyId);
			for (Therapy therapy : itemList) {
				String rawId = therapy.getItemId();

				Double benchmarkPrice = therapy.getBenchmarkPrice();
				if (benchmarkPrice == null) {
					benchmarkPrice = 0.0;
				}

				// 原耗料量， 在前台与净要耗料量使用相同的单位（配方单位），在后台使用库存单位
				BigDecimal itemCount = new BigDecimal(therapy.getItemGrossCount()).multiply(new BigDecimal(
						itemCountActual));

				if (!detailMap.containsKey(rawId)) {
					SelfSemisDetail detail = new SelfSemisDetail();
					detail.setItemId(therapy.getItemId());
					detail.setItemName(therapy.getItemName());
					detail.setItemDimension(therapy.getItemDimension());
					detail.setItemSpecification(therapy.getItemSpecification());
					detail.setItemCategory(therapy.getItemCategory());
					detail.setItemCountActual(itemCount.doubleValue());
					detail.setItemCountPlan(itemCount.doubleValue());

					detailMap.put(rawId, detail);
				} else {// 多个出品合并相同的物料
					SelfSemisDetail detail = detailMap.get(rawId);
					double allCount = detail.getItemCountActual();
					allCount = new BigDecimal(allCount).add(itemCount).doubleValue();
					detail.setItemCountActual(allCount);
					detail.setItemCountPlan(allCount);
				}
			}
		}

		JSONArray rawArr = new JSONArray();
		int rownumber = 1;
		for (SelfSemisDetail detailItem : detailMap.values()) {
			JSONObject json = new JSONObject();
			json.put("rownumber", rownumber);
			json.put("itemId", detailItem.getItemId());
			json.put("itemName", detailItem.getItemName());
			json.put("itemDimension", detailItem.getItemDimension());
			json.put("itemSpecification", detailItem.getItemSpecification());
			json.put("itemCategory", detailItem.getItemCategory());
			json.put("itemCountActual", df2.format(detailItem.getItemCountActual()));
			json.put("itemCountPlan", df2.format(detailItem.getItemCountPlan()));
			rawArr.add(json);
			rownumber++;
		}
		return rawArr.toString();
	}

	// 根据单据查询原料耗料数
	public List<SelfSemisDetail> getRawFromDishs1(String formId) throws NoPrivilegeException, SQLException,
			NoConnection {
		List<SelfSemisDetail> semisLst = selfSemisDetailBean.queryDetail(formId, "SEMIS");
		List<SelfSemisDetail> semisDetailLst = new ArrayList<SelfSemisDetail>();
		Map<String, SelfSemisDetail> detailMap = new HashMap<String, SelfSemisDetail>();// 多个出品中如果有相同的物料成分，则合并数量与金额
		for (SelfSemisDetail semisDetail : semisLst) {

			String therapyId = semisDetail.getItemId();
			double itemCountActual = semisDetail.getItemCountActual();
			List<Therapy> itemList = therapyBean.GetTherapyItems(therapyId);
			for (Therapy therapy : itemList) {
				String rawId = therapy.getItemId();

				Double benchmarkPrice = therapy.getBenchmarkPrice();
				if (benchmarkPrice == null) {
					benchmarkPrice = 0.0;
				}

				// 原耗料量， 在前台与净要耗料量使用相同的单位（配方单位），在后台使用库存单位
				BigDecimal itemCount = new BigDecimal(therapy.getItemGrossCount()).multiply(new BigDecimal(
						itemCountActual));

				if (!detailMap.containsKey(rawId)) {
					SelfSemisDetail detail = new SelfSemisDetail();
					detail.setItemId(therapy.getItemId());
					detail.setItemName(therapy.getItemName());
					detail.setItemDimension(therapy.getItemDimension());
					detail.setItemSpecification(therapy.getItemSpecification());
					detail.setItemCategory(therapy.getItemCategory());
					detail.setItemCountActual(0.0);
					detail.setItemCountPlan(itemCount.setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue());

					detailMap.put(rawId, detail);
				} else {// 多个出品合并相同的物料
					SelfSemisDetail detail = detailMap.get(rawId);
					double allCount = detail.getItemCountActual();
					allCount = new BigDecimal(allCount).add(itemCount.setScale(4, BigDecimal.ROUND_HALF_UP))
							.doubleValue();
					detail.setItemCountPlan(allCount);
				}
			}
		}
		for (SelfSemisDetail detailItem : detailMap.values()) {

			semisDetailLst.add(detailItem);
		}
		return semisDetailLst;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setStorageBean(StorageBean storageBean) {
		this.storageBean = storageBean;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setStorageInOutBean(StorageInOutBean storageInOutBean) {
		this.storageInOutBean = storageInOutBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public SelfSemisHeader getSelfSemisHeader() {
		return selfSemisHeader;
	}

	public void setSelfSemisHeader(SelfSemisHeader selfSemisHeader) {
		this.selfSemisHeader = selfSemisHeader;
	}

	public SelfSemisDetail getSelfSemisDetail() {
		return selfSemisDetail;
	}

	public void setSelfSemisDetail(SelfSemisDetail selfSemisDetail) {
		this.selfSemisDetail = selfSemisDetail;
	}

	public void setSelfSemisDetailBean(SelfSemisDetailBean selfSemisDetailBean) {
		this.selfSemisDetailBean = selfSemisDetailBean;
	}

	public void setSelfSemisHeaderBean(SelfSemisHeaderBean selfSemisHeaderBean) {
		this.selfSemisHeaderBean = selfSemisHeaderBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

}
