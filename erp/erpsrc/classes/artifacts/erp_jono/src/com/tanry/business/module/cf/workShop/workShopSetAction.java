package com.tanry.business.module.cf.workShop;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.FactoryWorkShopBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.store.FactoryWorkShop;
import action.common.BaseAction;

import com.tanry.business.module.cf.workShop.service.WorkShopManageService;
import com.tanry.framework.acl.NoPrivilegeException;

public class workShopSetAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private WorkShopManageService workShopManageService;

	private FactoryWorkShopBean factoryWorkShopBean;
	private FactoryWorkShop factoryWorkShop;
	private String workOrderId;
	private String workshop;
	private Integer priority;
	private String factoryId;

	/**
	 * 验证编号是否重复
	 */
	public void checkId() throws NoPrivilegeException, SQLException, NoConnection {
		boolean exist = false;
		factoryId = "F00";
		FactoryWorkShop factoryWorkShop = factoryWorkShopBean.queryById(factoryId, workOrderId);
		JSONObject result = new JSONObject();
		if (factoryWorkShop != null) {
			exist = true;
			result.put("workshop", factoryWorkShop.getWorkshop());
		}
		result.put("exist", exist);
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String addView() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public void setMain() throws NoPrivilegeException, SQLException, NoConnection {
		workShopManageService.setMain(factoryWorkShop);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void queryWorkShop() throws NoPrivilegeException, SQLException, NoConnection {
		JSONArray jsonArray = new JSONArray();
		JSONObject root = new JSONObject();
		root.put("id", "root");
		root.put("name", "工厂");
		root.put("isLeaf", "N");
		jsonArray.add(root);

		List<Map> WorkShopInfos = factoryWorkShopBean.query();
		for (int i = 0; i < WorkShopInfos.size(); i++) {
			Map item = WorkShopInfos.get(i);
			JSONObject jsonObject = new JSONObject();
			if (item.get("workOrderId") == null) {
				jsonObject.put("id", "F00");
				jsonObject.put("name", "长沙中央工厂");
			} else {
				jsonObject.put("id", item.get("workOrderId"));
				jsonObject.put("name", item.get("workshop"));
			}
			jsonObject.put("parent", item.get("parent"));
			jsonObject.put("priority", item.get("priority"));
			jsonArray.add(jsonObject);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void save() throws NoPrivilegeException, SQLException, NoConnection {
		if (factoryWorkShop.getFactoryId() != null) {
			workShopManageService.del(factoryWorkShop.getWorkOrderId());
		}
		factoryWorkShop.setFactoryId("F00");
		factoryWorkShop.setPriority(2);

		workShopManageService.insert(factoryWorkShop);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void dellect() throws NoPrivilegeException, SQLException, NoConnection {
		int count = workShopManageService.del(workOrderId);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getWorkOrderId() {
		return workOrderId;
	}

	public void setWorkOrderId(String workOrderId) {
		this.workOrderId = workOrderId;
	}

	public String getWorkshop() {
		return workshop;
	}

	public void setWorkshop(String workshop) {
		this.workshop = workshop;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getFactoryId() {
		return factoryId;
	}

	public void setFactoryId(String factoryId) {
		this.factoryId = factoryId;
	}

	public void setWorkShopManageService(WorkShopManageService workShopManageService) {
		this.workShopManageService = workShopManageService;
	}

	public void setFactoryWorkShopBean(FactoryWorkShopBean factoryWorkShopBean) {
		this.factoryWorkShopBean = factoryWorkShopBean;
	}

	public FactoryWorkShop getFactoryWorkShop() {
		return factoryWorkShop;
	}

	public void setFactoryWorkShop(FactoryWorkShop factoryWorkShop) {
		this.factoryWorkShop = factoryWorkShop;
	}

	public WorkShopManageService getWorkShopManageService() {
		return workShopManageService;
	}

}
