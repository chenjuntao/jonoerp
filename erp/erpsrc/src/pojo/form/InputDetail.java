//===============================================
//
// Copyright (c) 2014
// Tanry Electronic Technology Co., Ltd.
// ChangSha, China
//
// All Rights Reserved.
//
// First created on 2014.8.7 by cjt
// Last edited on 2014.8.25 by lyz
//
// Comments:read D_T2_STORAGE table.
// 说明：入库单表明细。
// by mep
//===============================================

package pojo.form;

import java.util.Date;

/**
 * Created by mep on 14-8-29.
 */
public class InputDetail {
	private String formId;
	private String itemId;
	private String itemName;
	private String itemDimension;
	private String itemSpecification;
	private String itemCategory;
	private Double orderCount;
	/**
	 * 查询多次入库总共已入库多少
	 */
	private Double receivedCount;
	private Double receiveCount;
	private Double differentCount;
	private Double itemUnitPrice;
	private Double payAmt;
	private Double receivePrice;
	private Double receiveAmt;
	private String batch;
	private Date expiredTime;
	private Double outReceiveRate;
	private Double sumItemCount;
	private String receiveId;
	private String receive;
	private Double supplyCycle;
	private String workOrderId;
    private String pic;
    private String num;
    private int isok;
	public void setNum(String num) {
		this.num = num;
	}

	public String getNum() {
		return num;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	public String getPic() {
		return pic;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemDimension() {
		return itemDimension;
	}

	public void setItemDimension(String itemDimension) {
		this.itemDimension = itemDimension;
	}

	public String getItemSpecification() {
		return itemSpecification;
	}

	public void setItemSpecification(String itemSpecification) {
		this.itemSpecification = itemSpecification;
	}

	public String getItemCategory() {
		return itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public Double getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Double orderCount) {
		this.orderCount = orderCount;
	}

	public Double getReceiveCount() {
		return receiveCount;
	}

	public void setReceiveCount(Double receiveCount) {
		this.receiveCount = receiveCount;
	}

	public Double getDifferentCount() {
		return differentCount;
	}

	public void setDifferentCount(Double differentCount) {
		this.differentCount = differentCount;
	}

	public Double getItemUnitPrice() {
		return itemUnitPrice;
	}

	public void setItemUnitPrice(Double itemUnitPrice) {
		this.itemUnitPrice = itemUnitPrice;
	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public Double getPayAmt() {
		return payAmt;
	}

	public void setPayAmt(Double payAmt) {
		this.payAmt = payAmt;
	}

	public Date getExpiredTime() {
		return expiredTime;
	}

	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	public Double getOutReceiveRate() {
		return outReceiveRate;
	}

	public void setOutReceiveRate(Double outReceiveRate) {
		this.outReceiveRate = outReceiveRate;
	}

	public Double getReceivedCount() {
		return receivedCount;
	}

	public void setReceivedCount(Double receivedCount) {
		this.receivedCount = receivedCount;
	}

	public Double getReceivePrice() {
		return receivePrice;
	}

	public void setReceivePrice(Double receivePrice) {
		this.receivePrice = receivePrice;
	}

	public Double getReceiveAmt() {
		return receiveAmt;
	}

	public void setReceiveAmt(Double receiveAmt) {
		this.receiveAmt = receiveAmt;
	}

	public Double getSumItemCount() {
		return sumItemCount;
	}

	public void setSumItemCount(Double sumItemCount) {
		this.sumItemCount = sumItemCount;
	}

	public String getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(String receiveId) {
		this.receiveId = receiveId;
	}

	public String getReceive() {
		return receive;
	}

	public void setReceive(String receive) {
		this.receive = receive;
	}

	public Double getSupplyCycle() {
		return supplyCycle;
	}

	public void setSupplyCycle(Double supplyCycle) {
		this.supplyCycle = supplyCycle;
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

}
