/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 26, 2015 by liyzh
 * Last edited on Jan 26, 2015 by liyzh
 * 
 * 说明： 配置原料、半成品、中央工厂产品、其他外部货品的基本信息：包括名称、类型、来源、基本保质期、检验标准及配图等。 
 */
package com.tanry.business.module.hq.config.item;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletResponse;

import logic.NoConnection;
import logic.form.TemplateItemBean;
import logic.module.hq.ItemMetaQueryBean;
import logic.store.BoxTypeBean;
import logic.store.DeliveryUnitBean;
import logic.store.FactoryWorkShopBean;
import logic.store.ItemCategoryBean;
import logic.store.ItemMetaBean;
import logic.store.ItemWorkShopBean;
import logic.store.PriceGroupBean;
import logic.store.ShelfBean;
import logic.store.ShelfItemBean;
import logic.store.TherapyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.struts2.ServletActionContext;

import pojo.store.BoxType;
import pojo.store.DeliveryType;
import pojo.store.DeliveryUnit;
import pojo.store.FactoryWorkShop;
import pojo.store.ItemCategory;
import pojo.store.ItemMeta;
import pojo.store.ItemWorkShop;
import pojo.store.PriceGroup;
import pojo.store.Shelf;
import pojo.store.ShelfItem;
import pojo.store.Therapy;
import action.common.BaseAction;
import action.setting.DeleteFolder;
import action.setting.FileToZip;

import com.tanry.business.module.hq.config.item.service.MetaManageService;
import com.tanry.business.module.hq.config.item.vo.PriceVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.Cn2Spell;
import com.tanry.framework.util.CsvUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SysUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.SysOption;
import com.tanry.framework.util.xls.ExcelInst;
import com.tanry.framework.util.xls.ExportUtil;

public class MetaManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ItemMetaBean itemMetaBean;
	private ItemMetaQueryBean itemMetaQueryBean;
	private ItemCategoryBean itemCategoryBean;
	private DeliveryUnitBean deliveryUnitBean;
	private BoxTypeBean boxTypeBean;
	private TherapyBean therapyBean;
	private FactoryWorkShopBean factoryWorkShopBean;
	private MetaManageService metaManageService;
	private ItemWorkShopBean itemWorkShopBean;
	private PriceGroupBean priceGroupBean;
	private ShelfItem shelfItem;
	private ShelfItemBean shelfItemBean;
	private ShelfBean shelfBean;
	private Shelf shelf;
	private String itemId;
	private String itemIds;
	private String itemNames;

	private String itemType;
	private String categoryId;
	private Integer enabled;
	private Date effectDate;
	private String type;
	private ItemMeta itemMeta;
	private ItemWorkShop itemWorkShop;
	private DeliveryUnit itemParam;
	private DeliveryType deliveryType;

	private String downloadTokenValue;
	private String itemName;
	private String jsonData;
	private String priceType;

	private String displayAllFlag;

	/**
	 * 用于设置物料的各种价格，仅新增时初始化，不可修改
	 */
	private PriceVO itemPrice;
	private List<BoxType> boxTypeLst;
	private List<FactoryWorkShop> factoryWorkShopLst;
	private List<ShelfItem> shelfItemLst;
	private List<Shelf> shelfLst;

	/** 上传的文件 */
	private File attachment;

	private String exportType;
	private int newEnd;

	private String viewType;

	private TemplateItemBean templateItemBean;

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String deleteView() throws NoPrivilegeException, SQLException, NoConnection {
		viewType = "delete";
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(itemId)) {
			itemMeta = itemMetaBean.GetItemById(itemId);
			itemParam = deliveryUnitBean.queryById(itemId);
			itemPrice = metaManageService.queryPrice(itemId);
			itemWorkShop = itemWorkShopBean.queryById(itemId);
			shelfItem = shelfItemBean.queryByItemId(itemId);
		} else {
			itemMeta = new ItemMeta();
			ItemCategory category = itemCategoryBean.queryById(categoryId);
			itemMeta.setItemType(category.getItemType());
		}
		boxTypeLst = boxTypeBean.query(SysOption.ENABLED);

		String factoryId = "F00";
		factoryWorkShopLst = factoryWorkShopBean.queryById(factoryId);
		shelfLst = shelfBean.query();
		return SUCCESS;
	}

	/**
	 * 验证编号是否重复
	 */
	public void checkItemId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		ItemMeta itemMeta = itemMetaBean.GetItemById(itemId);
		if (itemMeta != null) {
			exist = true;
		}

		JSONObject result = new JSONObject();
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证物料是否被成本卡使用
	 */
	public void checkIsTherapy() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		List<Map> itemLst = itemMetaBean.GetIsTherapy(itemIds);
		StringBuilder sb = new StringBuilder();
		if (itemLst.size() > 0) {
			exist = true;
			for (int i = 0, len = itemLst.size(); i < len; i++) {
				Map item = itemLst.get(i);
				sb.append("[").append(item.get("itemId")).append(item.get("itemName")).append("]存在")
						.append(item.get("info"));
				if (i < len - 1) {
					sb.append(",");
				} else {
					sb.append("等成本卡中，无法进行操作！");
				}
			}
		} else {
			sb.append("");
		}
		JSONObject result = new JSONObject();

		result.put("exist", exist);
		result.put("info", sb.toString());
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 验证物料所有仓库数为0
	 */
	public void checkItemCount() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		List<Map> itemLst = itemMetaBean.GetItemCount(itemIds);
		StringBuilder sb = new StringBuilder();
		if (itemLst.size() > 0) {
			exist = true;
			for (int i = 0, len = itemLst.size(); i < len; i++) {
				Map item = itemLst.get(i);
				sb.append("[").append(item.get("itemId")).append(item.get("itemName")).append("]在")
						.append(item.get("info"));
				if (i < len - 1) {
					sb.append(",");
				} else {
					sb.append("存在库存，无法进行操作！");
				}
			}
		} else {
			sb.append("");
		}
		JSONObject result = new JSONObject();

		result.put("exist", exist);
		result.put("info", sb.toString());
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * 注：编码已验证，此处只验证名称
	 * 
	 */
	public void checkItemName() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		StringBuilder sb = new StringBuilder();

		List<ItemMeta> litemMetas = itemMetaBean.getItemsByName(itemName);
		if (litemMetas != null) {
			exist = true;

			for (int i = 0, len = litemMetas.size(); i < len; i++) {
				ItemMeta itemMeta = litemMetas.get(i);
				sb.append("[").append(itemMeta.getItemId()).append("]").append(itemMeta.getItemName());

				if (i < len - 1) {
					sb.append(",");
				} else {
					sb.append("已使用[" + itemName + "]名称，是否继续操作？");
				}
			}
		}

		JSONObject result = new JSONObject();
		result.put("exist", exist);
		result.put("info", sb.toString());
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据分类获取原料基础信息，各种价格
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> itemLs = null;
		JSONArray arr = new JSONArray();
		int total = 0;
		if (newEnd == 1) {
			newEnd = 2147483647;
		} else {
			newEnd = getEnd();
		}

		if (!TextUtil.isEmpty(categoryId)) {
			total = itemMetaQueryBean.countItem(categoryId, displayAllFlag);
			setTotal(total);
			if (total > 0) {
				itemLs = itemMetaQueryBean.queryItem(categoryId, displayAllFlag, getStart(), newEnd);
			}
		} else {
			total = itemMetaQueryBean.countByItemName(itemName, displayAllFlag);
			setTotal(total);
			if (total > 0) {
				itemLs = itemMetaQueryBean.queryByItemName(itemName, displayAllFlag, getStart(), newEnd);
			}
		}

		int rownumber = 1;
		boxTypeLst = boxTypeBean.query(SysOption.ENABLED);
		if (total > 0) {
			for (Map item : itemLs) {
				JSONObject json = JSONObject.fromObject(item);
				json.put("rownumber", rownumber);
				json.put("boxTypeName", getBoxTypeName((String) item.get("boxType")));
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

	public void queryPrice() throws NoPrivilegeException, SQLException, NoConnection {
		String branchType = getLoginBranchType();
		List<PriceGroup> priceLst = null;
		if ("RESTAURANT".equals(branchType)) {
			priceLst = priceGroupBean.queryByType(priceType, getLoginBranchId());
		} else {
			priceLst = priceGroupBean.queryByType(priceType);
		}
		try {
			this.outJS(JSONArray.fromObject(priceLst).toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getBoxTypeName(String typeId) {
		for (BoxType btype : boxTypeLst) {
			if (btype.getTypeId().equals(typeId)) {
				return btype.getTypeName();
			}
		}
		return null;
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		String itemName = itemMeta.getItemName();
		itemMeta.setItemName(itemName.replace("'", "‘"));
		itemMeta.setItemId(itemMeta.getItemId().replace("'", ""));
		String queryCode = Cn2Spell.converterToFirstSpell(itemMeta.getItemName());
		queryCode = (queryCode.replace("'", ""));
		itemMeta.setQueryCode(queryCode);

		metaManageService.saveItem(itemId, itemMeta, itemParam, itemPrice, deliveryType, itemWorkShop, shelfItem);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUpload() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		InputStream fis = new FileInputStream(attachment);
		ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
		byte[] b = new byte[1000];
		int n;
		while ((n = fis.read(b)) != -1) {
			bos.write(b, 0, n);
		}
		fis.close();
		bos.close();

		metaManageService.uploadPhoto(itemId, bos.toByteArray());
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void moveCate() throws NoPrivilegeException, SQLException, NoConnection {
		metaManageService.moveCate(itemId, categoryId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doEnable() throws NoPrivilegeException, SQLException, NoConnection {
		metaManageService.doEnable(itemId, itemType, enabled, effectDate);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查询基本物料是否被启用
	 * 
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	public void queryEnable() throws NoPrivilegeException, SQLException, NoConnection {
		int enable = itemMetaBean.GetItemById(itemId).getEnabled();
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		result.put("enable", enable);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		metaManageService.deleteItem(itemIds);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doExport() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		JSONObject structure = JSONObject.fromObject(jsonData);
		String[] itemIdArr = itemIds.split(",");
		String[] itemNameArr = itemNames.split(",");
		ExcelInst ei = new ExcelInst();
		Workbook wb = ei.hwb;
		List<Map<String, Object>> TherapyList = new ArrayList<Map<String, Object>>();
		for (int j = 0; j < itemIdArr.length; j++) {
			String itmId = itemIdArr[j];
			String itmName = itemNameArr[j];
			List<Therapy> itemLst = therapyBean.GetTherapysById(itmId);
			// String FileName = "出品信息";
			int rownumber = 1;
			for (Therapy obj : itemLst) {
				Therapy pnHeader = (Therapy) obj;
				if (null != pnHeader && !"".equals(pnHeader)) {
					Map<String, Object> itemMap = new HashMap<String, Object>();
					if (rownumber == 1) {
						itemMap.put("itemIdRf", itmId);
						itemMap.put("itemNameRf", itmName);
					}
					itemMap.put("rownumber", rownumber++);

					itemMap.put("itemId", pnHeader.getItemId());

					itemMap.put("itemName", pnHeader.getItemName());

					itemMap.put("stockDimension", pnHeader.getStockDimension());

					itemMap.put("itemDimension", pnHeader.getItemDimension());

					itemMap.put("unitConvertFactor", pnHeader.getUnitConvertFactor());

					itemMap.put("itemCount", pnHeader.getItemCount());

					itemMap.put("itemUsageRate", pnHeader.getItemUsageRate());

					BigDecimal gCount = new BigDecimal(pnHeader.getItemGrossCount());
					if (pnHeader.getPurchasePrice() == null) {
						itemMap.put("purchasePrice", 0);
						itemMap.put("purchaseAmt", 0);
					} else {
						itemMap.put("purchasePrice", pnHeader.getPurchasePrice());
						BigDecimal purchaseAmt = gCount.multiply(new BigDecimal(pnHeader.getPurchasePrice())).setScale(
								2, BigDecimal.ROUND_HALF_EVEN);
						itemMap.put("purchaseAmt", purchaseAmt);
					}

					if (pnHeader.getBenchmarkPrice() == null) {
						itemMap.put("benchmarkPrice", 0);
						itemMap.put("benchmarkAmt", 0);
					} else {
						itemMap.put("itemPrice", pnHeader.getBenchmarkPrice());
						itemMap.put("benchmarkPrice", pnHeader.getBenchmarkPrice());
						BigDecimal benchmarkAmt = gCount.multiply(new BigDecimal(pnHeader.getBenchmarkPrice()))
								.setScale(2, BigDecimal.ROUND_HALF_EVEN);
						itemMap.put("benchmarkAmt", benchmarkAmt);
					}

					gCount = gCount.multiply(new BigDecimal(pnHeader.getUnitConvertFactor())).setScale(2,
							BigDecimal.ROUND_HALF_EVEN);
					itemMap.put("itemGrossCount", gCount);
					TherapyList.add(itemMap);
				}

			}
		}

		JSONArray colsJA = structure.getJSONArray("columns");// 导出csv
		String sheetName = structure.getString("sheetName");
		if ("csv".equals(type)) {
			StringBuffer sb = CsvUtil.exportCsv(colsJA, TherapyList);
			try {
				this.outCsv(sheetName + ".csv", sb);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}

		JSONArray arr = JSONArray.fromObject(TherapyList);// 导出excel
		JSONObject data = new JSONObject();
		data.put("rows", arr);
		wb = ExportUtil.export(structure, data);
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 分不同sheet页导出 */
	public void doSheetExport() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		JSONObject structure = JSONObject.fromObject(jsonData);
		String[] itemIdArr = itemIds.split(",");
		String[] itemNameArr = itemNames.split(",");
		ExcelInst ei = new ExcelInst();
		Workbook wb = ei.hwb;
		for (int j = 0; j < itemIdArr.length; j++) {
			String itmId = itemIdArr[j];
			String itmName = itemNameArr[j];
			List<Therapy> itemLst = therapyBean.GetTherapysById(itmId);
			String FileName = "出品信息";
			// FileName = new String((FileName).getBytes("GBK"), "iso8859-1");
			if ("excel".equals(type)) {
				List<Map<String, Object>> TherapyList = new ArrayList<Map<String, Object>>();
				int rownumber = 1;
				for (Therapy obj : itemLst) {
					Therapy pnHeader = (Therapy) obj;
					if (null != pnHeader && !"".equals(pnHeader)) {
						Map<String, Object> itemMap = new HashMap<String, Object>();
						itemMap.put("rownumber", rownumber++);

						itemMap.put("itemId", pnHeader.getItemId());

						itemMap.put("itemName", pnHeader.getItemName());

						itemMap.put("stockDimension", pnHeader.getStockDimension());

						itemMap.put("itemDimension", pnHeader.getItemDimension());

						itemMap.put("unitConvertFactor", pnHeader.getUnitConvertFactor());

						itemMap.put("itemCount", pnHeader.getItemCount());

						itemMap.put("itemUsageRate", pnHeader.getItemUsageRate());

						itemMap.put("itemGrossCount", pnHeader.getItemGrossCount());

						if (pnHeader.getPurchasePrice() == null) {
							itemMap.put("itemPrice", 0);
							itemMap.put("itemAmt", 0);
						} else {
							itemMap.put("itemPrice", pnHeader.getPurchasePrice());
							BigDecimal gCount = new BigDecimal(pnHeader.getItemGrossCount());
							BigDecimal itemAmt = gCount.multiply(new BigDecimal(pnHeader.getPurchasePrice())).setScale(
									2, BigDecimal.ROUND_HALF_EVEN);
							itemMap.put("itemAmt", itemAmt);
						}

						TherapyList.add(itemMap);
					}
				}
				JSONArray colsJA = structure.getJSONArray("columns");
				JSONArray arr = JSONArray.fromObject(TherapyList);
				JSONObject data = new JSONObject();
				data.put("rows", arr);
				Sheet sheet1 = wb.createSheet();
				// 设置表单名称
				wb.setSheetName(j, StringFilter(itmName));
				wb = ExportUtil.sheetExport(wb, sheet1, structure, data);
			} else {
			}
		}
		String sheetName = structure.getString("sheetName");
		try {
			this.outXls(sheetName + ".xls", wb);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* 导出多文件后打包 */
	public void doExport_copy() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		String basePath = SysUtil.getExportCsvDir();
		String timeStr = DateTimeUtil.getDateTime("MMdd_HHmmss");
		String filePath = basePath + File.separator + timeStr;
		File timeDir = new File(filePath);
		if (!timeDir.exists()) {
			timeDir.mkdirs();
		}

		String fileName = SysOption.META_ZIP;
		if ("SEMIS".equals(exportType)) {
			fileName = SysOption.SEMIS_ZIP;
		}

		String[] itemIdArr = itemIds.split(",");
		String[] itemNameArr = itemNames.split(",");
		for (int j = 0; j < itemIdArr.length; j++) {
			String itmId = itemIdArr[j];
			String itmName = itemNameArr[j];
			List<Therapy> itemLst = therapyBean.GetTherapysById(itmId);
			String FileName = filePath + File.separator + itmId;
			if ("csv".equals(type)) {
				String cp = readCsv(itemLst);
				File cpFile = new File(FileName + ".csv");
				FileWriter out = new FileWriter(cpFile);
				out.write(cp);
				out.close();
			} else {
				Workbook wb = readExcell(itemLst);
				File cpFile = new File(FileName + ".xls");
				try {
					FileOutputStream fileOut = new FileOutputStream(cpFile);// 创建文件流
					wb.write(fileOut);// 把Workbook对象输出到路径path中
					fileOut.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		String zipFilePath = basePath + File.separator + "cp" + timeStr + ".zip";
		FileToZip.zipDIR(filePath, zipFilePath);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.reset();
		response.setHeader("Cache-Control", "no-cache");
		response.setContentType("application/zip");

		response.setHeader("Location", fileName);
		// fileName应该是编码后的(utf-8)

		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String((fileName).getBytes("UTF-8"), "iso8859-1"));

		OutputStream outputStream = response.getOutputStream();
		InputStream inputStream = new FileInputStream(zipFilePath);
		byte[] buffer = new byte[1024];
		int i = -1;
		while ((i = inputStream.read(buffer)) != -1) {
			outputStream.write(buffer, 0, i);
		}
		outputStream.flush();
		outputStream.close();
		inputStream.close();
		outputStream = null;
		/*
		 * if (downloadTokenValue != null) {
		 * getSessionMap().put("downloadTokenValue", downloadTokenValue); }
		 */
		DeleteFolder.deleteDirectory(filePath);
	}

	public String readCsv(List<Therapy> itemLst) throws NoPrivilegeException, SQLException, NoConnection {
		StringBuffer sb = new StringBuffer();
		sb.append("序号,编码,名称,库存单位,配方单位,转换因子,净料耗量(配方单位),净耗料率(%),毛耗料量(配方单位),进货价,金额");
		sb.append("\n");
		int i = 1;
		for (Therapy item : itemLst) {
			sb.append(i + ",");
			i++;
			sb.append(item.getItemId() + ",");
			sb.append(item.getItemName() + ",");
			sb.append(item.getStockDimension() + ",");
			sb.append(item.getItemDimension() + ",");
			sb.append(item.getUnitConvertFactor() + ",");
			sb.append(item.getItemCount() + ",");
			sb.append(item.getItemUsageRate() + ",");
			sb.append(item.getItemGrossCount() + ",");

			if (item.getPurchasePrice() == null) {
				sb.append(0 + ",");
				sb.append(0 + ",");
			} else {
				sb.append(item.getPurchasePrice() + ",");
				BigDecimal gCount = new BigDecimal(item.getItemGrossCount());
				BigDecimal itemAmt = gCount.multiply(new BigDecimal(item.getPurchasePrice())).setScale(2,
						BigDecimal.ROUND_HALF_EVEN);
				sb.append(itemAmt + "\n");

			}
		}
		return sb.toString();
	}

	public Workbook readExcell(List<Therapy> itemLst) throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject structure = JSONObject.fromObject(jsonData);

		List<Map<String, Object>> TherapyList = new ArrayList<Map<String, Object>>();

		int rownumber = 1;
		for (Therapy obj : itemLst) {
			Therapy pnHeader = (Therapy) obj;
			if (null != pnHeader && !"".equals(pnHeader)) {
				Map<String, Object> itemMap = new HashMap<String, Object>();
				itemMap.put("rownumber", rownumber++);

				itemMap.put("itemId", pnHeader.getItemId());

				itemMap.put("itemName", pnHeader.getItemName());

				itemMap.put("stockDimension", pnHeader.getStockDimension());

				itemMap.put("itemDimension", pnHeader.getItemDimension());

				itemMap.put("unitConvertFactor", pnHeader.getUnitConvertFactor());

				itemMap.put("itemCount", pnHeader.getItemCount());

				itemMap.put("itemUsageRate", pnHeader.getItemUsageRate());

				itemMap.put("itemGrossCount", pnHeader.getItemGrossCount());

				if (pnHeader.getPurchasePrice() == null) {
					itemMap.put("itemPrice", 0);
					itemMap.put("itemAmt", 0);
				} else {
					itemMap.put("itemPrice", pnHeader.getPurchasePrice());
					BigDecimal gCount = new BigDecimal(pnHeader.getItemGrossCount());
					BigDecimal itemAmt = gCount.multiply(new BigDecimal(pnHeader.getPurchasePrice())).setScale(2,
							BigDecimal.ROUND_HALF_EVEN);
					itemMap.put("itemAmt", itemAmt);
				}

				TherapyList.add(itemMap);
			}
		}
		JSONArray colsJA = structure.getJSONArray("columns");
		JSONArray arr = JSONArray.fromObject(TherapyList);
		JSONObject data = new JSONObject();
		data.put("rows", arr);

		Workbook wb = ExportUtil.export(structure, data);

		return wb;
	}

	public static String StringFilter(String str) throws PatternSyntaxException {
		// 只允许字母和数字
		// String regEx = "[^a-zA-Z0-9]";
		// 清除掉所有特殊字符
		String regEx = "[`~!@#$%^&*()+=|{}':;',//[//].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(str);
		return m.replaceAll("").trim();
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemType() {
		return itemType;
	}

	public void setItemType(String itemType) {
		this.itemType = itemType;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setEnabled(Integer enabled) {
		this.enabled = enabled;
	}

	public void setEffectDate(Date effectDate) {
		this.effectDate = effectDate;
	}

	public ItemMeta getItemMeta() {
		return itemMeta;
	}

	public void setItemMeta(ItemMeta itemMeta) {
		this.itemMeta = itemMeta;
	}

	public DeliveryUnit getItemParam() {
		return itemParam;
	}

	public void setItemParam(DeliveryUnit itemParam) {
		this.itemParam = itemParam;
	}

	public PriceVO getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(PriceVO itemPrice) {
		this.itemPrice = itemPrice;
	}

	public List<BoxType> getBoxTypeLst() {
		return boxTypeLst;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setItemMetaQueryBean(ItemMetaQueryBean itemMetaQueryBean) {
		this.itemMetaQueryBean = itemMetaQueryBean;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

	public void setDeliveryUnitBean(DeliveryUnitBean deliveryUnitBean) {
		this.deliveryUnitBean = deliveryUnitBean;
	}

	public void setBoxTypeBean(BoxTypeBean boxTypeBean) {
		this.boxTypeBean = boxTypeBean;
	}

	public void setMetaManageService(MetaManageService metaManageService) {
		this.metaManageService = metaManageService;
	}

	public String getDownloadTokenValue() {
		return downloadTokenValue;
	}

	public void setDownloadTokenValue(String downloadTokenValue) {
		this.downloadTokenValue = downloadTokenValue;
	}

	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public List<FactoryWorkShop> getFactoryWorkShopLst() {
		return factoryWorkShopLst;
	}

	public void setFactoryWorkShopLst(List<FactoryWorkShop> factoryWorkShopLst) {
		this.factoryWorkShopLst = factoryWorkShopLst;
	}

	public void setFactoryWorkShopBean(FactoryWorkShopBean factoryWorkShopBean) {
		this.factoryWorkShopBean = factoryWorkShopBean;
	}

	public void setFactoryWorkShop(FactoryWorkShop factoryWorkShop) {
	}

	public ItemWorkShop getItemWorkShop() {
		return itemWorkShop;
	}

	public void setItemWorkShop(ItemWorkShop itemWorkShop) {
		this.itemWorkShop = itemWorkShop;
	}

	public void setItemWorkShopBean(ItemWorkShopBean itemWorkShopBean) {
		this.itemWorkShopBean = itemWorkShopBean;
	}

	public ShelfItem getShelfItem() {
		return shelfItem;
	}

	public void setShelfItem(ShelfItem shelfItem) {
		this.shelfItem = shelfItem;
	}

	public void setShelfItemBean(ShelfItemBean shelfItemBean) {
		this.shelfItemBean = shelfItemBean;
	}

	public List<ShelfItem> getShelfItemLst() {
		return shelfItemLst;
	}

	public void setShelfItemLst(List<ShelfItem> shelfItemLst) {
		this.shelfItemLst = shelfItemLst;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public List<Shelf> getShelfLst() {
		return shelfLst;
	}

	public void setShelfLst(List<Shelf> shelfLst) {
		this.shelfLst = shelfLst;
	}

	public void setShelfBean(ShelfBean shelfBean) {
		this.shelfBean = shelfBean;
	}

	public void setPriceGroupBean(PriceGroupBean priceGroupBean) {
		this.priceGroupBean = priceGroupBean;
	}

	public void setNewEnd(int newEnd) {
		this.newEnd = newEnd;
	}

	public void setDeliveryType(DeliveryType deliveryType) {
		this.deliveryType = deliveryType;
	}

	public String getPriceType() {
		return priceType;
	}

	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}

	public void setDisplayAllFlag(String displayAllFlag) {
		this.displayAllFlag = displayAllFlag;
	}

	public void setTemplateItemBean(TemplateItemBean templateItemBean) {
		this.templateItemBean = templateItemBean;
	}

}
