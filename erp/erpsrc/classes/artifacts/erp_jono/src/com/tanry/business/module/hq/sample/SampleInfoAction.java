/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Sep 18, 2015 by liyzh
 * Last edited on Sep 18, 2015 by liyzh
 * 
 * 说明： 研发样品信息增删改查
 */
package com.tanry.business.module.hq.sample;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import logic.NoConnection;
import logic.module.hq.SampleInfoBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.SampleInfo;
import action.common.BaseAction;

import com.tanry.business.module.hq.sample.service.SampleInfoService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;

public class SampleInfoAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private SampleInfoBean sampleInfoBean;
	private SampleInfoService sampleInfoService;

	private String id;
	private String ids;
	private String picType;

	private String productName;
	private String queryType;

	private SampleInfo sample;

	/**
	 * upload file
	 */
	private File attachment;

	public String createView() throws NoPrivilegeException, SQLException, NoConnection {
		sample = new SampleInfo();
		sample.setProductName("");
		sample.setMinimumOrderQuantity(1);
		sample.setProducePlace("");
		sample.setOrderCycle(1);
		sample.setUseCycle(1);
		sample.setPrice(1.0);
		sample.setGrossPrice(1.0);
		sample.setGrossWeight(1.0);
		sample.setNetWeight(1.0);
		sample.setNetRatio(100.0);
		sample.setShelfLife(10);
		sample.setDeliveryTime(1);
		sample.setAccptanceCriteria("");
		sample.setSupplier("");
		return SUCCESS;
	}

	public String editView() throws NoPrivilegeException, SQLException, NoConnection {
		sample = sampleInfoBean.queryById(id);
		return SUCCESS;
	}

	public void doQuery() throws NoPrivilegeException, SQLException, NoConnection {
		List<SampleInfo> sampleLst = sampleInfoBean.query(productName, queryType);

		JSONArray arr = new JSONArray();
		int rownumber = 1;
		for (SampleInfo sample : sampleLst) {
			JSONObject json = JSONObject.fromObject(sample);
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

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		if (TextUtil.isEmpty(id)) {
			sample.setCreateUserId(getLoginUserId());
		}
		sampleInfoService.save(id, sample);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doAudit() throws NoPrivilegeException, SQLException, NoConnection {
		sampleInfoService.audit(id, getLoginUserId());

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doDelete() throws NoPrivilegeException, SQLException, NoConnection {
		sampleInfoService.deleteSample(ids);

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

		sampleInfoService.uploadPhoto(id, bos.toByteArray(), picType);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}

	public SampleInfo getSample() {
		return sample;
	}

	public void setSample(SampleInfo sample) {
		this.sample = sample;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public void setSampleInfoBean(SampleInfoBean sampleInfoBean) {
		this.sampleInfoBean = sampleInfoBean;
	}

	public void setSampleInfoService(SampleInfoService sampleInfoService) {
		this.sampleInfoService = sampleInfoService;
	}

}
