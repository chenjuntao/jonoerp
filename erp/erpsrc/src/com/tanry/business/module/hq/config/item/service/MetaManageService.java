/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Jan 27, 2015 by liyzh
 * Last edited on Jan 27, 2015 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.business.module.hq.config.item.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import logic.NoConnection;
import logic.form.TemplateItemBean;
import logic.store.DeliveryRegionBean;
import logic.store.DeliveryTypeBean;
import logic.store.DeliveryUnitBean;
import logic.store.ItemMetaBean;
import logic.store.ItemPriceBean;
import logic.store.ItemWorkShopBean;
import logic.store.PicBean;
import logic.store.ProductionCycleBean;
import logic.store.ShelfItemBean;
import logic.store.SupplierBranchItemBean;
import logic.store.TherapyBean;

import org.apache.commons.lang3.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.store.DeliveryRegion;
import pojo.store.DeliveryType;
import pojo.store.DeliveryUnit;
import pojo.store.ItemMeta;
import pojo.store.ItemPrice;
import pojo.store.ItemWorkShop;
import pojo.store.ProductionCycle;
import pojo.store.ShelfItem;
import pojo.store.Therapy;

import com.tanry.business.module.hq.config.item.vo.PriceVO;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BranchCode;
import com.tanry.framework.util.constant.LogType;
import com.tanry.framework.util.constant.PriceType;

public class MetaManageService {

	private ItemMetaBean itemMetaBean;
	private DeliveryUnitBean deliveryUnitBean;
	private ItemPriceBean itemPriceBean;
	private PicBean picBean;
	private ProductionCycleBean productionCycleBean;
	private SupplierBranchItemBean supplierBranchItemBean;
	private ItemWorkShopBean itemWorkShopBean;
	private ShelfItemBean shelfItemBean;
	private TherapyBean therapyBean;
	private DeliveryRegionBean deliveryRegionBean;
	private DeliveryTypeBean deliveryTypeBean;

	private TemplateItemBean templateItemBean;

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void uploadPhoto(String itemId, byte[] picData) throws NoPrivilegeException, SQLException, NoConnection {
		ItemMeta item = itemMetaBean.GetItemById(itemId);
		String oldPicId = item.getItemPic();
		String picId = "pic" + itemId;// 用物料编号可以防止图片关系混乱
		if (!TextUtil.isEmpty(oldPicId)) {
			picBean.deleteEntity(oldPicId);// 替换老的图片
		}
		itemMetaBean.updatePic(itemId, picId);
		picBean.saveEntity(picId, picData);
	}

	public PriceVO queryPrice(String itemId) throws NoPrivilegeException, SQLException, NoConnection {
		PriceVO itemPrice = new PriceVO();
		Double purchasePrice = itemPriceBean.getItemPrice(itemId, PriceType.PURCHASE);
		itemPrice.setPurchasePrice(purchasePrice);
		Double benchmarkPrice = itemPriceBean.getItemPrice(itemId, PriceType.BENCHMARK);
		itemPrice.setBenchmarkPrice(benchmarkPrice);
		Double joinPrice = itemPriceBean.getItemPrice(itemId, PriceType.JOIN);
		itemPrice.setJoinPrice(joinPrice);
		Double retailPrice = itemPriceBean.getItemPrice(itemId, PriceType.RETAIL);
		itemPrice.setRetailPrice(retailPrice);
		Double wholesalePrice = itemPriceBean.getItemPrice(itemId, PriceType.WHOLESALE);
		itemPrice.setWholesalePrice(wholesalePrice);
		return itemPrice;
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void saveItem(String oldItemId, ItemMeta itemMeta, DeliveryUnit itemParam, PriceVO itemPrice,
			DeliveryType deliveryType, ItemWorkShop itemWorkShop, ShelfItem shelfItem) throws NoPrivilegeException,
			SQLException, NoConnection {
		String itemId = itemMeta.getItemId();
		String itemName = itemMeta.getItemName();
		String itemType = itemMeta.getItemType();
		itemMeta.setEnabled(1);
		if (itemParam != null) {
			itemParam.setItemId(itemId);
		}
		if (!TextUtil.isEmpty(oldItemId)) {
			itemMetaBean.updateEntity(oldItemId, itemMeta);
			if (itemParam != null) {
				deliveryUnitBean.deleteEntity(oldItemId);
				deliveryUnitBean.saveEntity(itemParam);
			}
			String log = "修改" + convert(itemType) + "[" + itemId + "]" + itemName;
			Double minOrder = itemParam.getMinOrderCount();
			Double oldMinOrder = itemParam.getOldMinOrderCount();

			if (oldMinOrder == null) {
				oldMinOrder = 0.0;
			}
			if (Math.abs(minOrder - oldMinOrder) > 0.001) {
				log = log + "，将最小订货倍量" + oldMinOrder + "修改为" + minOrder;
			}

			Double oldFactor = itemParam.getOldRecipeFactor();
			Double recipeFactor = itemParam.getRecipeFactor();

			if (oldFactor == null) {
				oldFactor = 1.0;
			}

			if (Math.abs(recipeFactor - oldFactor) > 0.001) {
				log = log + "，将配方单位转换因子" + oldFactor + "修改为" + recipeFactor;
			}

			Double deliveryFactor = itemParam.getDeliveryFactor();
			Double oldDelivery = itemParam.getOldDeliveryFactor();
			if (oldDelivery == null) {
				oldDelivery = 0.0;
			}
			if (Math.abs(deliveryFactor - oldDelivery) > 0.001) {
				log = log + "，将包装单位转换因子" + oldDelivery + "修改为" + deliveryFactor;
			}

			BigDecimal ratio = new BigDecimal(recipeFactor).divide(new BigDecimal(oldFactor), 2,
					BigDecimal.ROUND_HALF_UP);
			// if (ratio.doubleValue() != 1) {
			// 修改成本卡里的配方单位，转换因子
			Therapy therapy = new Therapy();
			therapy.setItemDimension(itemParam.getRecipeUnit());
			therapy.setItemId(itemId);
			therapy.setUnitConvertFactor(itemParam.getRecipeFactor());
			therapyBean.updateEntity(therapy, ratio.doubleValue());
			// }
			OperateLogUtil.record(null, LogType.UPDATE, "修改物料", log);
		} else {
			itemMetaBean.saveEntity(itemMeta);

			OperateLogUtil.record(null, LogType.CREATE, "新增物料", "新增" + convert(itemType) + "[" + itemId + "]"
					+ itemName);

			// 如果物料的类别属于半成品，自动向生产周期表插入生产周期为零的记录
			if ("SEMIS".equals(itemType)) {
				ProductionCycle productionCycle = new ProductionCycle();
				productionCycle.setBranchId(BranchCode.DEFAULT_CENTRALFACTORY);
				productionCycle.setItemId(itemId);
				productionCycle.setProductionCycle(0.0);
				productionCycleBean.saveEntity(productionCycle);

				// 半成品默认由中央工厂提供给物流中心
				supplierBranchItemBean.updateEntity(BranchCode.DEFAULT_CENTRALFACTORY,
						BranchCode.DEFAULT_LOGISTICSCENTER, itemId);
			}

			// 新增时初始化几种特定价格
			ItemPrice purchasePrice = new ItemPrice();
			purchasePrice.setPriceType(PriceType.PURCHASE);
			purchasePrice.setItemId(itemId);
			purchasePrice.setItemPrice(itemPrice.getPurchasePrice());
			itemPriceBean.saveEntity(purchasePrice);

			ItemPrice benchmarkPrice = new ItemPrice();
			benchmarkPrice.setPriceType(PriceType.BENCHMARK);
			benchmarkPrice.setItemId(itemId);
			benchmarkPrice.setItemPrice(itemPrice.getBenchmarkPrice());
			itemPriceBean.saveEntity(benchmarkPrice);

			ItemPrice joinPrice = new ItemPrice();
			joinPrice.setPriceType(PriceType.JOIN);
			joinPrice.setItemId(itemId);
			joinPrice.setItemPrice(itemPrice.getJoinPrice());
			itemPriceBean.saveEntity(joinPrice);

			ItemPrice retailPrice = new ItemPrice();
			retailPrice.setPriceType(PriceType.RETAIL);
			retailPrice.setItemId(itemId);
			retailPrice.setItemPrice(itemPrice.getRetailPrice());
			itemPriceBean.saveEntity(retailPrice);

			ItemPrice wholesalePrice = new ItemPrice();
			wholesalePrice.setPriceType(PriceType.WHOLESALE);
			wholesalePrice.setItemId(itemId);
			wholesalePrice.setItemPrice(itemPrice.getWholesalePrice());
			itemPriceBean.saveEntity(wholesalePrice);

			if (itemParam != null) {
				deliveryUnitBean.saveEntity(itemParam);
			}

			// 保存相应的区域配送模式
			deliveryType.setItemId(itemId);
			List<DeliveryRegion> derLst = deliveryRegionBean.queryAllRegion();
			for (DeliveryRegion deliveryRegion : derLst) {
				deliveryType.setRegionId(deliveryRegion.getRegionId());
				deliveryTypeBean.saveEntity(deliveryType);
			}
		}

		ItemWorkShop itemWorkShopOld = itemWorkShopBean.queryById(itemId);// 删除之前的车间
		if (itemWorkShopOld != null) {
			itemWorkShopBean.deleteEntity(itemId);
		}

		if (itemWorkShop != null) {
			itemWorkShop.setItemId(itemId);
			itemWorkShopBean.saveEntity(itemWorkShop);// 保存车间
		}

		ShelfItem shelfItemOld = shelfItemBean.queryByItemId(itemId);// 删除之前的库位
		if (shelfItemOld != null) {
			shelfItemBean.deleteEntity(itemId);
		}
		shelfItem.setItemId(itemId);
		shelfItemBean.saveEntity(shelfItem);
	}

	private String convert(String type) {
		if ("SEMIS".equals(type)) {
			return "半成品";
		} else if ("RAW".equals(type)) {
			return "原料";
		}

		return "产品";
	}

	/**
	 * @param cateId
	 * @param newCateId
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void moveCate(String itemId, String newCateId) throws NoPrivilegeException, SQLException, NoConnection {
		ItemMeta meta = itemMetaBean.GetItemById(itemId);
		if (meta.getCategoryId().equals(newCateId)) {// 不需要移动
			return;
		}

		meta.setCategoryId(newCateId);
		itemMetaBean.moveCate(itemId, newCateId);// 更新物料信息
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void deleteItem(String itemIds) throws NoPrivilegeException, SQLException, NoConnection {
		String[] itemArr = itemIds.split(",");

		List<String> logList = new ArrayList<String>();
		for (String itemId : itemArr) {
			String itemName = itemMetaBean.GetItemById(itemId).getItemName();
			itemMetaBean.deleteEntity(itemId);
			deliveryUnitBean.deleteEntity(itemId);
			shelfItemBean.deleteEntity(itemId);
			therapyBean.deleteEntity(itemId);
			productionCycleBean.deleteEntity(itemId);
			supplierBranchItemBean.deleteItemEntity(itemId);
			itemPriceBean.deleteEntity(itemId);
			itemWorkShopBean.deleteEntity(itemId);
			deliveryTypeBean.deleteEntity(itemId);
			logList.add("[" + itemId + "]" + itemName);
		}

		OperateLogUtil.record(null, LogType.DELETE, "删除物料", "删除" + StringUtils.join(logList, "、") + "等物料");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doEnable(String itemId, String itemType, Integer enabled, Date effectDate) throws NoPrivilegeException,
			SQLException, NoConnection {
		itemMetaBean.enableItem(itemId, itemType, enabled, effectDate);

		String title = null, operateDesc = null;
		String itemName = itemMetaBean.GetItemById(itemId).getItemName();
		if (enabled == 1) {
			title = "启用物料";
			operateDesc = "启用[" + itemId + "]" + itemName;
		} else {
			title = "停用物料";
			operateDesc = "停用[" + itemId + "]" + itemName;
			templateItemBean.deleteByItemId(itemId);
		}

		OperateLogUtil.record(title, operateDesc);
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public void setDeliveryUnitBean(DeliveryUnitBean deliveryUnitBean) {
		this.deliveryUnitBean = deliveryUnitBean;
	}

	public void setPicBean(PicBean picBean) {
		this.picBean = picBean;
	}

	public void setItemPriceBean(ItemPriceBean itemPriceBean) {
		this.itemPriceBean = itemPriceBean;
	}

	public void setProductionCycleBean(ProductionCycleBean productionCycleBean) {
		this.productionCycleBean = productionCycleBean;
	}

	public void setSupplierBranchItemBean(SupplierBranchItemBean supplierBranchItemBean) {
		this.supplierBranchItemBean = supplierBranchItemBean;
	}

	public void setShelfItemBean(ShelfItemBean shelfItemBean) {
		this.shelfItemBean = shelfItemBean;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public void setDeliveryRegionBean(DeliveryRegionBean deliveryRegionBean) {
		this.deliveryRegionBean = deliveryRegionBean;
	}

	public void setDeliveryTypeBean(DeliveryTypeBean deliveryTypeBean) {
		this.deliveryTypeBean = deliveryTypeBean;
	}

	public void setItemWorkShopBean(ItemWorkShopBean itemWorkShopBean) {
		this.itemWorkShopBean = itemWorkShopBean;
	}

	public void setTemplateItemBean(TemplateItemBean templateItemBean) {
		this.templateItemBean = templateItemBean;
	}

}
