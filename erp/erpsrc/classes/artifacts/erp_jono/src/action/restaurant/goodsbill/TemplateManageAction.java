/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 29, 2014 by liyzh
 * Last edited on Aug 29, 2014 by liyzh
 * 
 * 说明： 要货单模板管理
 */
package action.restaurant.goodsbill;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.TemplateMetaBean;
import logic.module.restaurant.RequestTemplateBean;
import logic.store.BranchBean;
import logic.store.DeliveryRegionBean;
import logic.store.ItemCategoryBean;
import logic.store.PgroupBranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;

import pojo.form.TemplateMeta;
import pojo.store.DeliveryRegion;
import pojo.store.ItemCategory;
import pojo.store.PgroupBranch;
import service.restaurant.goodsbill.CreateBillService;
import service.restaurant.goodsbill.TemplateManageService;
import vo.restaurant.RequestItemVO;
import action.common.BaseAction;

import com.tanry.business.form.FormConstant;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchType;
import com.tanry.framework.util.constant.PriceType;

import dao.store.DeliveryRegionDao;

public class TemplateManageAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private TemplateManageService templateManageService;
	private DeliveryRegionDao deliveryRegionDao;
	private BranchBean branchBean;

	private DeliveryRegionBean deliveryRegionBean;
	private ItemCategoryBean itemCategoryBean;
	private RequestTemplateBean requestTemplateBean;
	private TemplateMetaBean templateMetaBean;
	private PgroupBranchBean pgroupBranchBean;
	private PgroupBranch pgroupBranch;

	private String branchId;
	private String branchName;

	private String templateId;
	private String templateType;
	private String templateOwner;
	private String itemName;
	/**
	 * 配送区域
	 */
	private String regionId;
	/**
	 * 区域分组名称，如果有多个，逗号分隔
	 */
	private String regionName;
	/**
	 * 类别名称，如果有多个，逗号分隔
	 */
	private String categoryName;

	private int arrivePeriod;

	private List<Map> regionLst;

	private TemplateMeta templateMeta;

	private CreateBillService createBillService;

	/** 上传的文件 */
	private File attachment;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		List<DeliveryRegion> drs = deliveryRegionDao.queryRegionByLcId(getLoginBranchId());
		regionLst = new ArrayList<Map>();
		for (int i = 0; i < drs.size(); i++) {
			Map<String, Object> region = new HashMap<String, Object>();
			region.put("id", drs.get(i).getRegionId());
			region.put("name", drs.get(i).getRegionName());
			region.put("deliveryCycle", drs.get(i).getDeliveryCycle());
			regionLst.add(region);
		}
		return SUCCESS;
	}

	public String addView() throws NoPrivilegeException, SQLException, NoConnection {
		List<DeliveryRegion> drs = deliveryRegionDao.queryRegionByLcId(getLoginBranchId());
		regionLst = new ArrayList<Map>();
		for (int i = 0; i < drs.size(); i++) {
			Map<String, Object> region = new HashMap<String, Object>();
			region.put("id", drs.get(i).getRegionId());
			region.put("name", drs.get(i).getRegionName());
			region.put("deliveryCycle", drs.get(i).getDeliveryCycle());
			regionLst.add(region);
		}
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);

			// 根据区域ID（逗号分隔）查询出对应的区域名称
			String regionId = templateMeta.getDeliveryRegion();
			String[] rIds = regionId.split(",");
			List<DeliveryRegion> drs = deliveryRegionBean.queryAllRegion();
			List<String> rNames = new ArrayList<String>();
			for (int i = 0; i < rIds.length; i++) {
				for (int j = 0; j < drs.size(); j++) {
					if (rIds[i].equals(drs.get(j).getRegionId())) {
						rNames.add(drs.get(j).getRegionName());
						break;
					}
				}
			}
			regionName = StringUtils.join(rNames, ",");

			// 根据类别ID（逗号分隔）查询出对应的类别名称
			String cateId = templateMeta.getCategoryId();
			String[] cateIds = cateId.split(",");
			List<ItemCategory> ics = itemCategoryBean.GetAllItemCategory();
			List<String> cateNames = new ArrayList<String>();
			for (int i = 0; i < cateIds.length; i++) {
				for (int j = 0; j < ics.size(); j++) {
					if (cateIds[i].equals(ics.get(j).getCategoryId())) {
						cateNames.add(ics.get(j).getCategoryName());
						break;
					}
				}
			}
			categoryName = StringUtils.join(cateNames, ",");
		}
		return SUCCESS;
	}

	/**
	 * 解析导入文件
	 */
	public void doImport() throws NoPrivilegeException, SQLException, NoConnection {
		JSONObject result = createBillService.doTemplateImport(attachment);
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String copyView() throws NoPrivilegeException, SQLException, NoConnection {
		if (!TextUtil.isEmpty(templateId)) {
			templateMeta = templateMetaBean.queryById(templateId);
		}
		return SUCCESS;
	}

	public String pickModelView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String saveModelView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();
		List<TemplateMeta> metaLst = null;

		if (FormConstant.BOTH_TEMPLATE.equals(templateOwner)) {// 典型应用：餐厅要货选择模板（包括自建模板和物流中心为相应分组创建的模板）
			metaLst = requestTemplateBean.query(branchId, "-1", templateType, itemName, arrivePeriod);
			// 获取餐厅所在的分组
			String regionId = deliveryRegionBean.getRegionByBranch(branchId).getRegionId();
			List<TemplateMeta> publicLst = requestTemplateBean.query("-1", regionId, templateType, itemName,
					arrivePeriod);
			metaLst.addAll(publicLst);
		} else if (FormConstant.SELF_TEMPLATE.equals(templateOwner)) {// eg:物流中心查看自己建立的要货模板
			metaLst = requestTemplateBean.query(branchId, "-1", templateType, itemName, arrivePeriod);
		} else if (FormConstant.PUBLIC_TEMPLATE.equals(templateOwner)) {// eg:物流中心为相应分组创建的模板
			metaLst = requestTemplateBean.query("-1", regionId, templateType, itemName, arrivePeriod);
		} else {// 任意查询
			String branchType = branchBean.GetBranchById(branchId).getBranchType();
			if (BranchType.LOGISTICSCENTER.equals(branchType)) {
				metaLst = requestTemplateBean.query("-1", regionId, templateType, itemName, arrivePeriod);
			} else {
				metaLst = requestTemplateBean.query(branchId, regionId, templateType, itemName, arrivePeriod);
			}

		}
		int rownumber = getStart();
		for (TemplateMeta meta : metaLst) {
			JSONObject json = JSONObject.fromObject(meta);
			json.put("rownumber", rownumber);
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 根据模板ID获取相应的原料信息，价格等
	 */
	public void queryItem() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray arr = new JSONArray();

		String priceType = PriceType.BENCHMARK;
		if (!TextUtil.isEmpty(branchId)) {
			pgroupBranch = pgroupBranchBean.queryBenchmark(branchId);
			if (pgroupBranch != null) {
				priceType = pgroupBranch.getPriceGroupId();
			}
		}
		List<RequestItemVO> itemLst = requestTemplateBean.queryItem(templateId, priceType);
		TemplateMeta templateMeta = templateMetaBean.queryById(templateId);
		int rownumber = 1;
		for (RequestItemVO item : itemLst) {
			JSONObject json = JSONObject.fromObject(item);
			json.put("rownumber", rownumber);
			json.put("itemUnitPrice", item.getItemPrice());
			json.put("templateName", templateMeta.getTemplateName());
			arr.add(json);
			rownumber++;
		}
		try {
			this.outJS(arr.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doCopy() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId = templateMeta.getBranchId();
		Date settleTime = branchBean.GetBranchById(branchId).getBusinessDate();
		Date now = new Date();
		// 自动生成，拼音缩写+部门编号+日期+流水号
		String newTempId = "YHMB" + branchId + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");
		templateMeta.setTemplateId(newTempId);
		templateManageService.copyTemplate(templateId, templateMeta);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public List<Map> getRegionLst() {
		return regionLst;
	}

	public TemplateMeta getTemplateMeta() {
		return templateMeta;
	}

	public void setTemplateMeta(TemplateMeta tempateMeta) {
		this.templateMeta = tempateMeta;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTemplateType() {
		return templateType;
	}

	public void setTemplateType(String templateType) {
		this.templateType = templateType;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public void setRegionId(String regionId) {
		this.regionId = regionId;
	}

	public String getRegionName() {
		return regionName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setArrivePeriod(int arrivePeriod) {
		this.arrivePeriod = arrivePeriod;
	}

	public void setTemplateOwner(String templateOwner) {
		this.templateOwner = templateOwner;
	}

	public String getTemplateOwner() {
		return templateOwner;
	}

	public void setRequestTemplateBean(RequestTemplateBean requestTemplateBean) {
		this.requestTemplateBean = requestTemplateBean;
	}

	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}

	public void setTemplateManageService(TemplateManageService templateManageService) {
		this.templateManageService = templateManageService;
	}

	public void setTemplateMetaBean(TemplateMetaBean templateMetaBean) {
		this.templateMetaBean = templateMetaBean;
	}

	public void setItemCategoryBean(ItemCategoryBean itemCategoryBean) {
		this.itemCategoryBean = itemCategoryBean;
	}

	public void setCreateBillService(CreateBillService createBillService) {
		this.createBillService = createBillService;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public void setPgroupBranchBean(PgroupBranchBean pgroupBranchBean) {
		this.pgroupBranchBean = pgroupBranchBean;
	}

	public void setPgroupBranch(PgroupBranch pgroupBranch) {
		this.pgroupBranch = pgroupBranch;
	}

	public void setDeliveryRegionDao(DeliveryRegionDao deliveryRegionDao) {
		this.deliveryRegionDao = deliveryRegionDao;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

}
