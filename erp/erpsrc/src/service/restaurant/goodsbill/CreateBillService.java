/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 26, 2014 by liyzh
 * Last edited on Aug 26, 2014 by liyzh
 * 
 * 说明：  餐厅要货生成
 */
package service.restaurant.goodsbill;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.businessquery.FoodRawMaterialBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.RequestDetailBean;
import logic.form.RequestHeaderBean;
import logic.store.BranchBean;
import logic.store.DeliveryRegionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.RequestDetail;
import pojo.form.RequestHeader;

import com.tanry.business.form.FormPrefix;
import com.tanry.business.form.FormType;
import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;
import com.tanry.framework.util.constant.LogType;

import dao.importdata.ImportRequestDao;

public class CreateBillService {

	private RequestHeaderBean requestHeaderBean;
	private RequestDetailBean requestDetailBean;
	private FoodRawMaterialBean foodRawMaterialBean;
	private FormStatusBean formStatusBean;

	private DeliveryRegionBean deliveryRegionBean;
	private BranchBean branchBean;

	private ImportRequestDao importRequestDao;

	private OperationVersionBean operationVersionBean;

	/**
	 * 保存新的要货单
	 * 
	 * @param userId
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String doCommit(String userId, String userName, RequestHeader header, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		Date formTime = header.getFormTime();
		String branchId = header.getBuyerId();

		String formType = header.getFormType();
		String prefix = "YH";
		if (FormType.REQUEST_OUTER.equals(formType)) {
			prefix = FormPrefix.REQUEST_OUTER;
		}

		String formBody = FormUtil.genFormIdBody(prefix, branchId, formTime);

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;
		String formId = formBody + FormUtil.formatSerial(newSerial);

		// 保存单据版本信息
		OperateVersionUtil.save(formId);

		header.setFormId(formId);
		JSONArray arr = JSONArray.fromObject(jsonData);
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		String templateId = "";
		String templateName = "";
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			RequestDetail detail = new RequestDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);
			detail.setItemCategory(json.getString("itemCategory"));
			detail.setItemDimension(json.getString("itemDimension"));
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);

			try {
				String amt1 = json.get("amtTTCNY1").toString();
				String amt2 = json.get("amtTTCNY2").toString();
				String amt3 = json.get("amtTTCNY3").toString();
				if (!TextUtil.isEmpty(amt1) && !amt1.equals("null")) {
					detail.setAmtTTCNY1(json.getDouble("amtTTCNY1"));
					detail.setRequireCount1(json.getDouble("requireCount1"));
					detail.setSuggestCount1(json.getDouble("suggestCount1"));
				}
				if (!TextUtil.isEmpty(amt2) && !amt2.equals("null")) {
					detail.setAmtTTCNY2(json.getDouble("amtTTCNY2"));
					detail.setRequireCount2(json.getDouble("requireCount2"));
					detail.setSuggestCount2(json.getDouble("suggestCount2"));
				}
				if (!TextUtil.isEmpty(amt3) && !amt3.equals("null")) {
					detail.setAmtTTCNY3(json.getDouble("amtTTCNY3"));
					detail.setRequireCount3(json.getDouble("requireCount3"));
					detail.setSuggestCount3(json.getDouble("suggestCount3"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			detail.setInventory(json.getDouble("inventory"));
			detail.setOrderCount(json.getDouble("orderCount"));
			detail.setItemUnitPrice(json.getDouble("itemUnitPrice"));

			if (TextUtil.isEmpty(templateId)) {
				try {
					templateId = json.getString("templateId");
					templateName = json.getString("templateName");
				} catch (Exception e) {
					templateId = "";
					templateName = "";
				}

			}
			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}

			requestDetailBean.saveEntity(detail);
		}

		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);

		header.setTemplateId(templateId);
		header.setTemplateName(templateName);

		fillFull(header);

		requestHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.UNADUIT_CN, userId));
		recordLog(formId, LogType.CREATE, "新增要货单");

		if (!TextUtil.isEmpty(formId)) {
			updateDeliveryType(formId);
		}

		return formId;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateDeliveryType(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		requestHeaderBean.updateDeliveryType(formId);
	}

	public void recordLog(String formId, String logType, String msg) throws NoPrivilegeException, SQLException,
			NoConnection {
		OperateLogUtil.record(formId, logType, msg);
	}

	public OperationVersion getCurrentVeriosn(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		return operationVersionBean.queryVersion(formId);
	}

	// 补全RequestHeader
	private void fillFull(RequestHeader header) throws NoPrivilegeException, SQLException, NoConnection {

		// 根据门店所在的分组查询对应的物流中心
		String lcenter = deliveryRegionBean.getRegionByBranch(header.getBuyerId()).getBranchId();
		header.setProviderId(lcenter);

		header.setProvider("[" + lcenter + "]" + branchBean.GetBranchById(lcenter).getBranchName());
	}

	/**
	 * 餐厅要货单审核修改，过滤要货量为0
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditGoodsBill(RequestHeader header, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			RequestDetail detail = new RequestDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			detail.setInventory(json.getDouble("inventory"));
			double orderCount = json.getDouble("orderCount");
			detail.setOrderCount(orderCount);
			detail.setItemUnitPrice(json.getDouble("itemUnitPrice"));
			Double payAmt = json.getDouble("payAmt");
			String itemName = json.getString("itemName");
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}
			if (orderCount <= 0) {
				requestDetailBean.deleteByForm(formId, itemId);
				continue;
			}

			try {
				String amt1 = json.get("amtTTCNY1").toString();
				String amt2 = json.get("amtTTCNY2").toString();
				String amt3 = json.get("amtTTCNY3").toString();
				if (!TextUtil.isEmpty(amt1) && !amt1.equals("null")) {
					detail.setAmtTTCNY1(json.getDouble("amtTTCNY1"));
					detail.setRequireCount1(json.getDouble("requireCount1"));
					detail.setSuggestCount1(json.getDouble("suggestCount1"));
				}
				if (!TextUtil.isEmpty(amt2) && !amt2.equals("null")) {
					detail.setAmtTTCNY2(json.getDouble("amtTTCNY2"));
					detail.setRequireCount2(json.getDouble("requireCount2"));
					detail.setSuggestCount2(json.getDouble("suggestCount2"));
				}
				if (!TextUtil.isEmpty(amt3) && !amt3.equals("null")) {
					detail.setAmtTTCNY3(json.getDouble("amtTTCNY3"));
					detail.setRequireCount3(json.getDouble("requireCount3"));
					detail.setSuggestCount3(json.getDouble("suggestCount3"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			requestDetailBean.updateEntity(detail);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		requestHeaderBean.updateEntity(header);
	}

	/**
	 * 
	 * 管理要货单作废
	 * 
	 * @author charles_1018 return
	 * */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doInvalid(String[] formIds, Integer currentVersion, String userId, String cancelMsg)
			throws NoPrivilegeException, SQLException, NoConnection {
		for (int i = 0; i < formIds.length; i++) {
			String itemForm = formIds[i];
			formStatusBean.saveEntity(new FormStatus(itemForm, "已作废", userId));
			recordLog(itemForm, LogType.CANCELED, cancelMsg);
			OperateVersionUtil.update(itemForm, currentVersion, OperationVersion.CANCELED);
		}
	}

	/**
	 * 
	 * 管理修改要货单,没有过滤要货量为0的物料
	 * 
	 * @author charles_1018 return
	 * */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateGoodsBill(RequestHeader header, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		Double allPayAmt = 0.0, maxPayAmt = -1.0;
		String maxPayItem = "";
		String formId = header.getFormId();
		JSONArray arr = JSONArray.fromObject(jsonData);
		requestHeaderBean.updateEntity(header);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			RequestDetail detail = new RequestDetail();
			detail.setFormId(formId);
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);
			detail.setInventory(json.getDouble("inventory"));
			double orderCount = json.getDouble("orderCount");
			detail.setOrderCount(orderCount);
			detail.setItemUnitPrice(json.getDouble("itemUnitPrice"));
			Double payAmt = json.getDouble("payAmt");
			String itemName = json.getString("itemName");
			detail.setPayAmt(payAmt);
			allPayAmt = allPayAmt + payAmt;
			if (payAmt > maxPayAmt) {
				maxPayAmt = payAmt;
				maxPayItem = "[" + itemId + "]" + itemName;
			}
			// if (orderCount <= 0) {
			// requestDetailBean.deleteByForm(formId, itemId);
			// continue;
			// }

			try {
				String amt1 = json.get("amtTTCNY1").toString();
				String amt2 = json.get("amtTTCNY2").toString();
				String amt3 = json.get("amtTTCNY3").toString();
				if (!TextUtil.isEmpty(amt1) && !amt1.equals("null")) {
					detail.setAmtTTCNY1(json.getDouble("amtTTCNY1"));
					detail.setRequireCount1(json.getDouble("requireCount1"));
					detail.setSuggestCount1(json.getDouble("suggestCount1"));
				}
				if (!TextUtil.isEmpty(amt2) && !amt2.equals("null")) {
					detail.setAmtTTCNY2(json.getDouble("amtTTCNY2"));
					detail.setRequireCount2(json.getDouble("requireCount2"));
					detail.setSuggestCount2(json.getDouble("suggestCount2"));
				}
				if (!TextUtil.isEmpty(amt3) && !amt3.equals("null")) {
					detail.setAmtTTCNY3(json.getDouble("amtTTCNY3"));
					detail.setRequireCount3(json.getDouble("requireCount3"));
					detail.setSuggestCount3(json.getDouble("suggestCount3"));
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}

			requestDetailBean.updateEntity(detail);
		}
		header.setAllPayAmt(allPayAmt);
		header.setMaxPayItem(maxPayItem);
		recordLog(formId, LogType.UPDATE, "编辑要货单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.EDITED);
	}

	// 要货导入
	public JSONObject doImport(File file) throws NoPrivilegeException, SQLException, NoConnection {
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		try {
			detailMap = readExcel(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject result = new JSONObject();
		result.put("existLst", importRequestDao.getExistItem(detailMap));
		return result;
	}

	// 模版导入物料
	public JSONObject doTemplateImport(File file) throws NoPrivilegeException, SQLException, NoConnection {
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		try {
			detailMap = readExcel(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		JSONObject result = new JSONObject();
		result.put("existLst", importRequestDao.getTemplateItem(detailMap));
		return result;
	}

	// 读取execl文件，暂只支持execl2003版本
	public Map<String, Map<String, Object>> readExcel(File file) throws FileNotFoundException, IOException {
		BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
		POIFSFileSystem fs = new POIFSFileSystem(inStream);
		HSSFWorkbook wb = new HSSFWorkbook(fs);
		HSSFSheet sheet = wb.getSheetAt(0);
		Map<String, Map<String, Object>> detailMap = new HashMap<String, Map<String, Object>>();
		DecimalFormat df = new DecimalFormat("####");
		DecimalFormat dd = new DecimalFormat("###0.0##");

		// 第一行为标题，不取
		for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			HSSFRow row = sheet.getRow(rowIndex);
			if (row == null) {
				continue;
			}

			Cell cell = row.getCell(0);
			if (cell == null) {
				continue;
			}

			String itemId = "";

			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				itemId = df.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				itemId = cell.getStringCellValue();
			}

			String orderCount = "";
			cell = row.getCell(1);
			if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
				orderCount = dd.format(cell.getNumericCellValue());
			} else if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
				orderCount = cell.getStringCellValue();
			}

			Map<String, Object> item = new HashMap<String, Object>();
			item.put("rownumber", rowIndex + 1);// 存储导入数据的行号，方便最后给出错误提示
			item.put("orderCount", orderCount);
			detailMap.put(itemId, item);
		}

		inStream.close();
		return detailMap;
	}

	/**
	 * 餐厅要货单审核
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditGoodsBill(String userId, RequestHeader header, String jsonData, Integer currentVersion)
			throws NoPrivilegeException, SQLException, NoConnection {
		auditGoodsBill(header, jsonData);
		String formId = header.getFormId();

		requestHeaderBean.audit(formId, header.getAuditor(), header.getAuditTime());

		formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));

		OperateVersionUtil.update(formId, currentVersion, OperationVersion.AUDITED);
		recordLog(formId, LogType.AUDIT, "审核要货单");
	}

	/**
	 * 餐厅要货单审核，只改变状态，不修改数据
	 * 
	 * @param header
	 * @param jsonData
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditById(String formId, String userId, Date businessDate) throws NoPrivilegeException, SQLException,
			NoConnection {
		requestHeaderBean.audit(formId, userId, businessDate);
		formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));
	}

	/**
	 * 餐厅要货单刪除
	 * 
	 * @param formId
	 * @throws NoConnection
	 * @throws SQLException
	 * @throws NoPrivilegeException
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteGoodsBill(String formId, Integer currentVersion) throws NoPrivilegeException, SQLException,
			NoConnection {
		requestHeaderBean.deleteEntity(formId);
		recordLog(formId, LogType.DELETE, "删除要货单");
		OperateVersionUtil.update(formId, currentVersion, OperationVersion.DELETED);
		// requestDetailBean.deleteByForm(formId);
	}

	/**
	 * 查询万元用量
	 * 
	 * @param shopC
	 * @param startDate1
	 * @param endDate1
	 * @param startDate2
	 * @param endDate2
	 * @param startDate3
	 * @param endDate3
	 * @param jsonData
	 * @return
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	public JSONArray calcSuggest(String shopC, String startDate1, String endDate1, String startDate2, String endDate2,
			String startDate3, String endDate3, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {
		JSONArray arr = JSONArray.fromObject(jsonData);
		List<String> itemIdLst = new ArrayList<String>();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			itemIdLst.add(json.getString("itemId"));
		}

		String query = " (1,ITEM_ID)  IN ((1,'" + StringUtils.join(itemIdLst, ",").replaceAll(",", "'),(1,'") + "')) ";

		JSONArray myArr = JSONArray.fromObject(foodRawMaterialBean.getAllAmtTTCNY(shopC, startDate1, endDate1,
				startDate2, endDate2, startDate3, endDate3, query));

		JSONArray newArr = new JSONArray();
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			String itemId = json.getString("itemId");

			for (Object obj2 : myArr) {
				JSONObject json2 = (JSONObject) obj2;
				if (itemId.equals(json2.getString("itemId"))) {
					json.put("amtTTCNY1", json2.get("nqty1"));
					json.put("amtTTCNY2", json2.get("nqty2"));
					json.put("amtTTCNY3", json2.get("nqty3"));
					newArr.add(json);
				}
			}
		}
		return newArr;
	}

	public void setRequestDetailBean(RequestDetailBean requestDetailBean) {
		this.requestDetailBean = requestDetailBean;
	}

	public void setFoodRawMaterialBean(FoodRawMaterialBean foodRawMaterialBean) {
		this.foodRawMaterialBean = foodRawMaterialBean;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setImportRequestDao(ImportRequestDao importRequestDao) {
		this.importRequestDao = importRequestDao;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

}
