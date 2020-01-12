/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Aug 26, 2014 by yxg
 * Last edited on Aug 26, 2014 by yxg
 * 
 * 说明：  餐厅要货生成测试
 */
package test.Service;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import logic.NoConnection;
import logic.form.DeleteTestBean;
import logic.form.FormStatusBean;
import logic.form.LossDetailBean;
import logic.form.LossHeaderBean;
import logic.form.OperationVersionBean;
import logic.form.RequestDetailBean;
import logic.form.RequestHeaderBean;
import logic.module.setting.OperateLogBean;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.LossDetail;
import pojo.form.LossHeader;
import pojo.form.RequestDetail;
import pojo.form.RequestHeader;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.log.pojo.OperateLog;

@SuppressWarnings("rawtypes")
public class GoodsBillService {

	private RequestHeaderBean requestHeaderBean;

	private RequestDetailBean requestDetailBean;
	private JdbcTemplate jdbcTemplate;
	private LossDetailBean lossDetailBean;
	private FormStatusBean formStatusBean;
	private LossHeaderBean lossHeaderBean;
	private OperateLogBean operateLogBean;

	public void hehe(int num, int numE) throws Exception {
		Integer.toHexString(requestHeaderBean.hashCode());
		for (int i = 0; i < num; i++) {
			String theFormId = UUID.randomUUID().toString().substring(0, 32);
			requestHeaderBean.saveEntity((RequestHeader) returnTheO(new RequestHeader(), i, numE, theFormId));
			for (int j = 0; j < num; j++) {
				requestDetailBean.saveEntity((RequestDetail) returnTheO(new RequestDetail(), i, numE, theFormId));
			}
		}
	}

	@Transactional(propagation = Propagation.SUPPORTS, rollbackFor = Exception.class)
	public void doTestGoodsBill(int num, int numE) throws Exception {
		hehe(num, numE);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doTestLoss(int num, int numE) throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, NoSuchFieldException, NoPrivilegeException, SQLException,
			NoConnection {
		for (int i = 0; i < num; i++) {
			System.out.println(numE);
			String theFormId = UUID.randomUUID().toString().substring(0, 32);
			// operationVersionBean.saveEntity((OperationVersion) returnTheO(
			// new OperationVersion(), i, numE, theFormId));
			for (int j = 0; j < 100; j++)
				lossDetailBean.saveEntity((LossDetail) returnTheO(new LossDetail(), j, numE, theFormId));
			lossHeaderBean.saveEntity((LossHeader) returnTheO(new LossHeader(), i, numE, theFormId));
			formStatusBean.saveEntity((FormStatus) returnTheO(new FormStatus(), i, numE, theFormId));
			operateLogBean.saveEntity((OperateLog) returnTheO(new OperateLog(), i, numE, theFormId));
			OperateVersionUtil.save(theFormId);
		}
	}

	public void doDeleteTestData(String tbName) throws IllegalArgumentException, IllegalAccessException,
			NoSuchMethodException, SecurityException, NoSuchFieldException, NoPrivilegeException, SQLException,
			NoConnection {
		String deleteSql = "DELETE FROM " + tbName + " WHERE LENGTH(FORM_ID) = 32";
		jdbcTemplate.update(deleteSql);
	}

	private List<Map> getFiledsInfo(Object o) {
		Field[] fields = o.getClass().getDeclaredFields();
		List<Map> nodeLst = new ArrayList<Map>();
		for (int i = 0; i < fields.length; i++) {
			Map<String, String> node = new HashMap<String, String>();
			node.put("name", fields[i].getName());
			node.put("type", fields[i].getType().toString());
			nodeLst.add(node);
		}
		return nodeLst;
	}

	public String captureName(String name) {
		name = name.substring(0, 1).toUpperCase() + name.substring(1);
		return name;

	}

	public Object returnTheO(Object o, int pos, int nume, String theFormId) throws IllegalArgumentException,
			IllegalAccessException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		List<Map> nodeList = getFiledsInfo(o);
		Pattern pattern = Pattern.compile("[^a-z]*");

		Class cls = o.getClass();
		java.util.Date dt = new java.util.Date();
		Field field;
		for (Map node : nodeList) {
			String theType = ((String) node.get("type"));
			String s = (String) node.get("name");
			Matcher matcher = pattern.matcher(s);
			if (matcher.matches())
				continue;
			String theName = "set";
			field = cls.getDeclaredField(s);
			field.setAccessible(true);
			s = captureName(s);
			theName = theName + s;
			if (theType.indexOf("String") != -1) {
				if (s.indexOf("Form_id") != -1 || s.indexOf("FormId") != -1) {
					if (pos == nume - 1)
						field.set(o, "");
					else
						field.set(o, theFormId);
				} else {
					if (s.indexOf("Item_id") != -1 || s.indexOf("ItemId") != -1 || s.equals("Id")) {
						if (pos == nume - 1)
							field.set(o, "");
						else
							field.set(o, UUID.randomUUID().toString().substring(0, 32));
					} else
						field.set(o, "x");
				}
			} else {
				if (theType.indexOf("Double") != -1) {
					// m = cls.getDeclaredMethod(theName, Double.class);
					// m.invoke(cls.newInstance(), 0.0);
					field.set(o, 0.0);
				} else {
					if (theType.indexOf("Integer") != -1 || theType.indexOf("int") != -1) {
						field.set(o, 0);
					} else {
						field.set(o, dt);
					}
				}
			}
		}
		return o;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public void setRequestDetailBean(RequestDetailBean requestDetailBean) {
		this.requestDetailBean = requestDetailBean;
	}

	public void setDeleteTestBean(DeleteTestBean deleteTestBean) {
	}

	public void setOperationVersion(OperationVersionBean operationVersionBean) {
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setLossDetailBean(LossDetailBean lossDetailBean) {
		this.lossDetailBean = lossDetailBean;
	}

	public void setOperateLogBean(OperateLogBean operateLogBean) {
		this.operateLogBean = operateLogBean;
	}
}
