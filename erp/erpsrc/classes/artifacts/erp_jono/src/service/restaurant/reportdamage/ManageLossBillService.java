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
package service.restaurant.reportdamage;

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
import logic.form.LossDetailBean;
import logic.form.LossHeaderBean;
import logic.form.OperationVersionBean;
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
import pojo.form.LossDetail;
import pojo.form.LossHeader;
import pojo.form.OperationVersion;
import pojo.form.StorageInOut;
import pojo.store.ItemMeta;
import pojo.store.Therapy;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.NumberUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;

public class ManageLossBillService {

	private LossHeaderBean lossHeaderBean;
	private LossDetailBean lossDetailBean;
	private FormStatusBean formStatusBean;
	/**
	 * D_T2_STORAGE 只存储仓库信息，仓库ID是唯一的
	 */
	private StorageBean storageBean;
	private TherapyBean therapyBean;
	private ItemMetaBean itemMetaBean;

	private StorageInOutBean storageInOutBean;

	private OperationVersionBean operationVersionBean;

	private BranchBean branchBean;

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String CreateLossBill(LossHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String prefix = "BSR";
		String formBody = FormUtil.genFormIdBody(prefix, header.getLossBranchId(), header.getLossTime());
		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);
		header.setFormId(formId);

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			LossDetail detail = new LossDetail();
			detail.setForm_id(formId);
			String itemId = json.getString("itemId");
			detail.setItem_id(itemId);
			String itemName = json.getString("itemName");
			detail.setItem_name(itemName);
			detail.setItem_category(json.getString("itemCategory"));
			detail.setItem_dimension(json.getString("itemDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItem_specification(itemSpecification);
			detail.setItem_count(json.getDouble("lossNum"));
			detail.setStorage_count(json.getDouble("inventory"));
			detail.setUnit_price(json.getDouble("itemUnitPrice"));
			double payAmt = json.getDouble("payAmt");
			detail.setPay_amt(payAmt);
			detail.setExpired_time(DateTimeUtil.parse(json.getString("exp_date")));
			detail.setReason(json.getString("reason"));
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			lossDetailBean.saveEntity(detail);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		lossHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", header.getLossManId()));
		OperateVersionUtil.save(formId);
		OperateLogUtil.record(formId, LogType.CREATE, "新增原料报损单");

		return formId;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditById(String formId, String userId, String userName, Date businessDate)
			throws NoPrivilegeException, SQLException, NoConnection {
		lossHeaderBean.audit(formId, userId, userName, businessDate);
		formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String CreateDishLossBill(LossHeader header, String jsonData, String rawJsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String prefix = "BSD";
		String formBody = FormUtil.genFormIdBody(prefix, header.getLossBranchId(), header.getLossTime());
		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);
		header.setFormId(formId);

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = 0.0;
		String maxPayItem = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			LossDetail detail = new LossDetail();
			detail.setForm_id(formId);
			String itemId = json.getString("itemId");
			detail.setItem_id(itemId);
			String itemName = json.getString("itemName");
			detail.setItem_name(itemName);
			detail.setItem_category(json.getString("categoryId"));
			detail.setItem_dimension(json.getString("itemDimension"));
			detail.setItem_count(json.getDouble("lossNum"));
			detail.setUnit_price(json.getDouble("itemUnitPrice"));
			double payAmt = json.getDouble("payAmt");
			detail.setPay_amt(payAmt);
			detail.setReason(json.getString("reason"));
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			lossDetailBean.saveEntity(detail);
		}

		JSONArray rawArr = JSONArray.fromObject(rawJsonData);
		for (Object obj : rawArr) {
			JSONObject json = (JSONObject) obj;
			LossDetail detail = new LossDetail();
			detail.setForm_id(formId);
			String itemId = json.getString("itemId");
			detail.setItem_id(itemId);
			String itemName = json.getString("itemName");
			detail.setItem_name(itemName);
			detail.setItem_category(json.getString("itemCategory"));
			detail.setItem_dimension(json.getString("itemDimension"));
			String itemId2 = json.getString("therapyId");
			detail.setItem_id2(itemId2);
			String itemName2 = json.getString("therapyName");
			detail.setItem_name2(itemName2);
			detail.setItem_count2(json.getDouble("therapyNum"));
			detail.setItem_dimension2(json.getString("therapyDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItem_specification(itemSpecification);
			detail.setItem_count(json.getDouble("lossNum"));
			detail.setUnit_price(NumberUtil.getDouble(json.get("itemUnitPrice")));
			double payAmt = json.getDouble("payAmt");
			detail.setPay_amt(payAmt);
			detail.setReason("DISH2RAW");

			lossDetailBean.saveEntity(detail);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		lossHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", header.getLossManId()));
		OperateVersionUtil.save(formId);
		OperateLogUtil.record(formId, LogType.CREATE, "新增出品报损单");
		return formId;
	}

	/**
	 * 餐厅报损单修改
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void UpdateLossBill(LossHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			LossDetail detail = new LossDetail();
			detail.setForm_id(formId);
			detail.setItem_id(json.getString("item_id"));
			detail.setItem_count(json.getDouble("item_count"));
			detail.setPay_amt(json.getDouble("pay_amt"));
			detail.setReason(json.getString("reason"));

			lossDetailBean.updateEntity(detail);
		}
		lossHeaderBean.updateEntity(header);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doAudit(LossHeader header, String jsonData, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		UpdateLossBill(header, jsonData);// 审核之前先更改数据
		AuditLossBill(header);

		String formId = header.getFormId();
		OperateLogUtil.record(formId, LogType.AUDIT, "审核原料报损单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.AUDITED);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doAuditDish(LossHeader header, String jsonData, String rawJsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		UpdateLossBill(header, jsonData);// 审核之前先更改数据
		UpdateDishLossBill(header.getFormId(), rawJsonData);// 对于出品报损，还要更改出品转换的原料数据
		AuditLossBill(header);

		String formId = header.getFormId();
		OperateLogUtil.record(formId, LogType.AUDIT, "审核出品报损单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.AUDITED);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void UpdateLossBill1(LossHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			LossDetail detail = new LossDetail();
			detail.setForm_id(formId);
			detail.setItem_id(json.getString("itemId"));
			detail.setItem_count(json.getDouble("lossNum"));
			detail.setPay_amt(json.getDouble("payAmt"));
			detail.setReason("DISH2RAW");

			lossDetailBean.updateEntity(detail);
		}
		lossHeaderBean.updateEntity(header);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doSave(LossHeader lossHeader, String jsonData, Integer currentVersion) throws NoPrivilegeException,
			SQLException, NoConnection {
		UpdateLossBill(lossHeader, jsonData);

		String rawJsonData = getRawFromDish(jsonData);
		UpdateLossBill1(lossHeader, rawJsonData);

		String lossType = lossHeader.getLossType();
		String formId = lossHeader.getFormId();

		if ("DISHLOSS".equals(lossType)) {
			OperateLogUtil.record(formId, LogType.UPDATE, "编辑出品报损单");
		} else {
			OperateLogUtil.record(formId, LogType.UPDATE, "编辑原料报损单");
		}

		OperateVersionUtil.update(formId, currentVersion, OperationVersion.EDITED);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doDishSave(LossHeader lossHeader, String jsonData, String rawJsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		UpdateLossBill(lossHeader, jsonData);// 审核之前先更改数据
		UpdateDishLossBill(lossHeader.getFormId(), rawJsonData);// 对于出品报损，还要更改出品转换的原料数据
		String formId = lossHeader.getFormId();
		OperateLogUtil.record(formId, LogType.UPDATE, "编辑出品报损单");

		OperateVersionUtil.update(formId, currentVersion, OperationVersion.EDITED);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doDelete(String formId, Integer currentVersion) throws NoPrivilegeException, SQLException, NoConnection {
		LossHeader lossHeader = lossHeaderBean.queryById(formId);
		deleteLossBill(formId);

		String lossType = lossHeader.getLossType();

		if ("DISHLOSS".equals(lossType)) {
			OperateLogUtil.record(formId, LogType.DELETE, "删除出品报损单");
		} else {
			OperateLogUtil.record(formId, LogType.DELETE, "删除原料报损单");
		}

		OperateVersionUtil.update(formId, currentVersion, OperationVersion.DELETED);

	}

	/**
	 * 餐厅报损单修改（仅修改出品转换为原材料数据）
	 * 
	 * @param header
	 * @param jsonData
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void UpdateDishLossBill(String formId, String rawJsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		lossDetailBean.deleteDish2RawByForm(formId);// 先删除旧的出品转换成的原料数据

		JSONArray rawArr = JSONArray.fromObject(rawJsonData);
		for (Object obj : rawArr) {
			JSONObject json = (JSONObject) obj;
			LossDetail detail = new LossDetail();
			detail.setForm_id(formId);
			String itemId = json.getString("itemId");
			detail.setItem_id(itemId);
			String itemName = json.getString("itemName");
			detail.setItem_name(itemName);
			detail.setItem_category(json.getString("itemCategory"));
			detail.setItem_dimension(json.getString("itemDimension"));
			String itemId2 = json.getString("therapyId");
			detail.setItem_id2(itemId2);
			String itemName2 = json.getString("therapyName");
			detail.setItem_name2(itemName2);
			detail.setItem_dimension2(json.getString("therapyDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItem_specification(itemSpecification);
			detail.setItem_count(json.getDouble("lossNum"));
			detail.setItem_count2(json.getDouble("therapyNum"));
			detail.setUnit_price(NumberUtil.getDouble(json.getDouble("itemUnitPrice")));
			double payAmt = json.getDouble("payAmt");
			detail.setPay_amt(payAmt);
			detail.setReason("DISH2RAW");

			lossDetailBean.saveEntity(detail);
		}
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void AuditLossBill(LossHeader header) throws NoPrivilegeException, SQLException, NoConnection {
		String formId = header.getFormId();
		String autidor = header.getAuditor();
		String autidorId = header.getAuditorId();

		// 门店ID
		String branchId = header.getLossBranchId();

		// 仓库ID
		String storageId = header.getStorageId();

		Date businessDate = branchBean.GetBranchById(branchId).getBusinessDate();

		lossHeaderBean.audit(formId, autidorId, autidor, header.getAuditTime());

		// -----------------------------------------
		// 操作时间
		Date operationTime = DateTimeUtil.getNow();
		String lossType = header.getLossType();
		String reason = null;
		List<LossDetail> detailLst = new ArrayList<LossDetail>();
		if ("DISHLOSS".equals(lossType)) {
			reason = "CPBS";
			detailLst = lossDetailBean.queryDishDetail(header.getFormId());
		} else {
			reason = "YLBS";
			detailLst = lossDetailBean.queryRawDetail(header.getFormId());
		}
		// -----------------------------------------
		Map<String, Double> storageMap = lossDetailBean.queryStorage(formId, storageId);

		for (LossDetail detail : detailLst) {
			if ("DISHLOSS".equals(header.getLossType())) {
				if (!"DISH2RAW".equals(detail.getReason())) {
					continue; // 如果是出品报损单的出品信息，则忽略
				}
			}

			String itemId = detail.getItem_id();

			// -----------------------------------------
			// 出入库记录

			// 入库数量
			Double itemCountIn = 0.0;

			// 出库数量
			Double itemCountOut = detail.getItem_count();

			// 期初数量
			Double orgiCount = storageMap.get(itemId);

			// 结存数量
			Double resultCount = orgiCount - itemCountOut;
			storageMap.put(itemId, resultCount);

			StorageInOut storageInOut = new StorageInOut(branchId, storageId, businessDate, operationTime, itemId,
					detail.getUnit_price(), orgiCount, itemCountIn, itemCountOut, resultCount, formId, reason);

			storageInOutBean.saveEntity(storageInOut);

			// 更新实时库存
			storageBean.updateItemCount(itemId, storageId, -itemCountOut);
		}

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.AUDITED_CN, autidorId));
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
	public void deleteLossBill(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		lossHeaderBean.deleteEntity(formId);
		lossDetailBean.deleteByForm(formId);
	}

	// 根据出品转换为原料
	public String getRawFromDish(String dishJsonData) throws NoPrivilegeException, SQLException, NoConnection {
		DecimalFormat df2 = new DecimalFormat("#0.00"); // 小数保留两位

		JSONArray dishArr = JSONArray.fromObject(dishJsonData);
		Map<String, LossDetail> detailMap = new HashMap<String, LossDetail>();// 多个出品中如果有相同的物料成分，则合并数量与金额
		for (Object obj : dishArr) {
			JSONObject dishJson = (JSONObject) obj;
			String dishId = "";
			if (dishJson.containsKey("itemId")) {
				dishId = dishJson.getString("itemId");
			} else {
				dishId = dishJson.getString("item_id");
			}
			double lossCount = 0;
			if (dishJson.containsKey("lossNum")) {
				lossCount = dishJson.getDouble("lossNum");
			} else {
				lossCount = dishJson.getDouble("item_count");
			}
			List<Therapy> itemList = therapyBean.GetTherapysById(dishId);
			for (Therapy therapy : itemList) {
				String rawId = therapy.getItemId();

				Double benchmarkPrice = therapy.getBenchmarkPrice();
				if (benchmarkPrice == null) {
					benchmarkPrice = 0.0;
				}

				// 原耗料量， 在前台与净要耗料量使用相同的单位（配方单位），在后台使用库存单位
				BigDecimal itemCount = new BigDecimal(therapy.getItemGrossCount()).multiply(new BigDecimal(lossCount));

				BigDecimal benchmarkAmt = new BigDecimal(0);
				if (benchmarkPrice != null) {
					benchmarkAmt = itemCount.multiply(new BigDecimal(benchmarkPrice)).setScale(2,
							BigDecimal.ROUND_HALF_EVEN);
				}
				// 先算金额，再进行转换
				itemCount = itemCount.multiply(new BigDecimal(therapy.getUnitConvertFactor())).setScale(2,
						BigDecimal.ROUND_HALF_EVEN);

				if (!detailMap.containsKey(rawId)) {
					ItemMeta item = itemMetaBean.GetById(rawId);
					LossDetail detail = new LossDetail();
					detail.setItem_id(item.getItemId());
					detail.setItem_name(item.getItemName());
					detail.setItem_dimension(item.getItemDimension());
					detail.setItem_specification(item.getItemSpecification());
					detail.setItem_category(item.getCategoryId());
					detail.setUnit_price(benchmarkPrice);
					detail.setPay_amt(benchmarkAmt.doubleValue());
					detail.setItem_count(itemCount.doubleValue());

					detailMap.put(rawId, detail);
				} else {// 多个出品合并相同的物料
					LossDetail detail = detailMap.get(rawId);
					double allCount = detail.getItem_count();
					allCount = new BigDecimal(allCount).add(itemCount).doubleValue();
					detail.setItem_count(allCount);
					double allAmt = detail.getPay_amt();
					allAmt = new BigDecimal(allAmt).add(benchmarkAmt).doubleValue();
					detail.setPay_amt(allAmt);
				}
			}
		}

		JSONArray rawArr = new JSONArray();
		int rownumber = 1;
		for (LossDetail detailItem : detailMap.values()) {
			JSONObject json = new JSONObject();
			json.put("rownumber", rownumber);
			json.put("itemId", detailItem.getItem_id());
			json.put("itemName", detailItem.getItem_name());
			json.put("itemDimension", detailItem.getItem_dimension());
			json.put("itemSpecification", detailItem.getItem_specification());
			json.put("itemCategory", detailItem.getItem_category());
			json.put("lossNum", df2.format(detailItem.getItem_count()));
			json.put("itemUnitPrice", detailItem.getUnit_price());
			json.put("payAmt", detailItem.getPay_amt());
			rawArr.add(json);
			rownumber++;
		}
		return rawArr.toString();
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
		DecimalFormat df2 = new DecimalFormat("#0.00"); // 小数保留两位

		JSONArray dishArr = JSONArray.fromObject(dishJsonData);
		Map<String, LossDetail> detailMap = new HashMap<String, LossDetail>();// 多个出品中如果有相同的物料成分，则合并数量与金额
		for (Object obj : dishArr) {
			JSONObject dishJson = (JSONObject) obj;
			String dishId = "";
			if (dishJson.containsKey("itemId")) {
				dishId = dishJson.getString("itemId");
			} else {
				dishId = dishJson.getString("item_id");
			}
			double lossCount = 0;
			if (dishJson.containsKey("lossNum")) {
				lossCount = dishJson.getDouble("lossNum");
			} else {
				lossCount = dishJson.getDouble("item_count");
			}
			List<Therapy> itemList = therapyBean.GetTherapyItems(dishId);
			for (Therapy therapy : itemList) {
				String rawId = therapy.getItemId();

				Double benchmarkPrice = therapy.getBenchmarkPrice();
				if (benchmarkPrice == null) {
					benchmarkPrice = 0.0;
				}

				// 原耗料量， 在前台与净要耗料量使用相同的单位（配方单位），在后台使用库存单位
				BigDecimal itemCount = new BigDecimal(therapy.getItemGrossCount()).multiply(new BigDecimal(lossCount))
						.divide(new BigDecimal(therapy.getUnitConvertFactor()), 4);

				BigDecimal benchmarkAmt = new BigDecimal(0);
				if (benchmarkPrice != null) {
					benchmarkAmt = itemCount.multiply(new BigDecimal(benchmarkPrice)).setScale(2,
							BigDecimal.ROUND_HALF_EVEN);
				}
				// 先算金额，再进行转换
				itemCount = itemCount.multiply(new BigDecimal(therapy.getUnitConvertFactor())).setScale(2,
						BigDecimal.ROUND_HALF_EVEN);

				if (!detailMap.containsKey(rawId)) {
					LossDetail detail = new LossDetail();
					detail.setItem_id(therapy.getItemId());
					detail.setItem_name(therapy.getItemName());
					detail.setItem_dimension(therapy.getItemDimension());
					detail.setItem_specification(therapy.getItemSpecification());
					detail.setItem_category(therapy.getItemCategory());
					detail.setUnit_price(benchmarkPrice);
					detail.setPay_amt(benchmarkAmt.doubleValue());
					detail.setItem_count(itemCount.doubleValue());

					detailMap.put(rawId, detail);
				} else {// 多个出品合并相同的物料
					LossDetail detail = detailMap.get(rawId);
					double allCount = detail.getItem_count();
					allCount = new BigDecimal(allCount).add(itemCount).doubleValue();
					detail.setItem_count(allCount);
					double allAmt = detail.getPay_amt();
					allAmt = new BigDecimal(allAmt).add(benchmarkAmt).doubleValue();
					detail.setPay_amt(allAmt);
				}
			}
		}

		JSONArray rawArr = new JSONArray();
		int rownumber = 1;
		for (LossDetail detailItem : detailMap.values()) {
			JSONObject json = new JSONObject();
			json.put("rownumber", rownumber);
			json.put("itemId", detailItem.getItem_id());
			json.put("itemName", detailItem.getItem_name());
			json.put("itemDimension", detailItem.getItem_dimension());
			json.put("itemSpecification", detailItem.getItem_specification());
			json.put("itemCategory", detailItem.getItem_category());
			json.put("lossNum", df2.format(detailItem.getItem_count()));
			json.put("itemUnitPrice", detailItem.getUnit_price());
			json.put("payAmt", detailItem.getPay_amt());
			rawArr.add(json);
			rownumber++;
		}
		return rawArr.toString();
	}

	public void setLossDetailBean(LossDetailBean lossDetailBean) {
		this.lossDetailBean = lossDetailBean;
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
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

}
