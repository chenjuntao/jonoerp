/**
 * for menu action
 * by yxg
 * 2016-06-20
 */
package com.tanry.business.special;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.form.ArrangmentHeaderBean;
import logic.form.CheckHeaderBean;
import logic.form.InputHeaderBean;
import logic.form.LossHeaderBean;
import logic.form.PickingHeaderBean;
import logic.form.RequestHeaderBean;
import logic.form.RequisitionHeaderBean;
import logic.form.ReturnGoodsHeaderBean;
import logic.form.ShippingHeaderBean;
import logic.form.TransferHeaderBean;
import logic.module.lc.PurchaseManageBean;
import logic.module.restaurant.InputReturnBean;
import logic.module.setting.PermissionBean;
import net.sf.json.JSONObject;
import action.common.BaseAction;

import com.tanry.business.form.FormType;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.BillStatus;

public class TodoAction extends BaseAction {
	public RequestHeaderBean requestHeaderBean;
	public InputHeaderBean inputHeaderBean;
	public ReturnGoodsHeaderBean returnGoodsHeaderBean;
	public InputReturnBean inputReturnBean;
	public LossHeaderBean lossHeaderBean;
	public TransferHeaderBean transferHeaderBean;
	public CheckHeaderBean checkHeaderBean;
	public PermissionBean permissionBean;
	public PickingHeaderBean pickingHeaderBean;
	public ShippingHeaderBean shippingHeaderBean;
	public PurchaseManageBean purchaseManageBean;
	public ArrangmentHeaderBean arrangmentHeaderBean;
	public RequisitionHeaderBean requisitionHeaderBean;

	public int getRequest(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		return requestHeaderBean.count(null, null, branchId, "request", "unaudit");
	}

	public int getInput(String branchId, String branchType) throws NoPrivilegeException, SQLException, NoConnection {
		return inputHeaderBean.count(null, null, branchId, null, "unaudit", branchType);
	}

	public int getReturnGoods(String branchId, String branchType) throws NoPrivilegeException, SQLException,
			NoConnection {
		return returnGoodsHeaderBean.count(null, null, branchId, "unaudit", branchType);
	}

	public int getInputReturn(String branchId, String branchType) throws NoPrivilegeException, SQLException,
			NoConnection {// 与物流中心复用采购退货
		return inputReturnBean.count(null, null, branchId, null, "audit", branchType);
	}

	public int getLossSale(String branchId, String branchType) throws NoPrivilegeException, SQLException, NoConnection {
		return lossHeaderBean.countByStatus(null, null, branchId, null, "DISHLOSS", BillStatus.UNADUIT_CN, branchType);
	}

	public int getLossItem(String branchId, String branchType) throws NoPrivilegeException, SQLException, NoConnection {
		return lossHeaderBean.countByStatus(null, null, branchId, null, "RAWLOSS", BillStatus.UNADUIT_CN, branchType);
	}

	public int getTransfer(String branchId, String branchType) throws NoPrivilegeException, SQLException, NoConnection {
		return transferHeaderBean.count(null, null, branchId, null, BillStatus.UNADUIT_CN, branchType);
	}

	public int getCheck(String branchId) throws NoPrivilegeException, SQLException, NoConnection {// 与物流中心复用
		return checkHeaderBean.count(null, null, branchId, "form", "unaudit");
	}

	public int getPicking() throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> nodeLst = pickingHeaderBean.queryTree(null, null, "unaudit");
		int result = 0;
		for (Map node : nodeLst) {
			if (node.containsKey("parent") && !TextUtil.isEmpty(node.get("parent").toString())
					&& !node.get("parent").toString().equals("root"))
				result++;
		}
		return result;
	}

	public int getDistribution() throws NoPrivilegeException, SQLException, NoConnection {
		return shippingHeaderBean.countByCross(null, null, "-1", "unaudit", FormType.DISTRI_CROSS, null, null);
	}

	public int getIn() throws NoPrivilegeException, SQLException, NoConnection {
		return inputHeaderBean.countNew("AND s.STATUS = '未审核'  AND b.BRANCH_TYPE = 'LOGISTICSCENTER'");
	}

	public int getPurchase(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> nodeLst = purchaseManageBean.queryTree(null, null, branchId, "unaudit");
		int result = 0;
		for (Map node : nodeLst) {
			if (node.containsKey("parent") && !TextUtil.isEmpty(node.get("parent").toString())
					&& !node.get("parent").toString().equals("root"))
				result++;
		}
		return result;
	}

	public int getPlan(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		return arrangmentHeaderBean.countUnAudit(null, null, branchId, UN_AUDIT);
	}

	public int getItemBuy(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		List<Map> nodeLst = purchaseManageBean.queryCFTree(null, null, branchId, "unaudit");
		int result = 0;
		for (Map node : nodeLst) {
			if (node.containsKey("parent") && !TextUtil.isEmpty(node.get("parent").toString())
					&& !node.get("parent").toString().equals("root"))
				result++;
		}
		return result;
	}

	public int getCfInput() throws NoPrivilegeException, SQLException, NoConnection {
		return inputHeaderBean
				.countNew("AND s.STATUS = '未审核'  AND b.BRANCH_TYPE = 'CENTRALFACTORY'   AND (h.FORM_TYPE = 'PURCHASE'  OR h.FORM_TYPE = 'MANUAL')");
	}

	public int getItemInput() throws NoPrivilegeException, SQLException, NoConnection {
		return inputHeaderBean
				.countNew("AND s.STATUS = '未审核' AND b.BRANCH_TYPE = 'CENTRALFACTORY'  AND (h.FORM_TYPE = 'PRODUCE')");
	}

	public int getWorkReturn() throws NoPrivilegeException, SQLException, NoConnection {
		return requisitionHeaderBean.count(null, null, "return", "unconfirm");
	}

	public int getWorkGet() throws NoPrivilegeException, SQLException, NoConnection {
		return requisitionHeaderBean.count(null, null, "produce", "unreceive");
	}

	public int getWorkSupper() throws NoPrivilegeException, SQLException, NoConnection {
		return requisitionHeaderBean.count(null, null, "extra", "unreceive");
	}

	public int getNoWorkGet() throws NoPrivilegeException, SQLException, NoConnection {
		return requisitionHeaderBean.count(null, null, "manual", "unaudit");
	}

	public int getItemOut() throws NoPrivilegeException, SQLException, NoConnection {
		return shippingHeaderBean.countAll(null, null, "-1", "unaudit", "PRODUCT", null, null);
	}

	public int getCfItemLoss(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		return lossHeaderBean.countByStatus(null, null, branchId, null, "RAWLOSS", BillStatus.UNADUIT_CN,
				"CENTRALFACTORY");
	}

	public int getCfSaleLoss(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		return lossHeaderBean.countByStatus(null, null, branchId, null, "DISHLOSS", BillStatus.UNADUIT_CN,
				"CENTRALFACTORY");
	}

	public int getGetReturn(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		return inputReturnBean.count(null, null, branchId, null, "audit", "CENTRALFACTORY");
	}

	public int getCfCheck(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		return checkHeaderBean.count(null, null, branchId, "form", "unaudit");
	}

	public int getCfTransfer(String branchId) throws NoPrivilegeException, SQLException, NoConnection {
		return transferHeaderBean.count(null, null, branchId, "", BillStatus.UNADUIT_CN, "CENTRALFACTORY");
	}

	public void getCounts() throws NoPrivilegeException, SQLException, NoConnection {
		String branchId, branchType;
		branchId = getLoginBranchId();
		branchType = getLoginBranchType();
		JSONObject result = new JSONObject();
		result.put("Request", this.getRequest(branchId));
		result.put("Input", this.getInput(branchId, branchType));
		result.put("ReturnGoods", this.getReturnGoods(branchId, branchType));
		result.put("InputReturn", this.getInputReturn(branchId, branchType));
		result.put("LossItem", this.getLossItem(branchId, branchType));
		result.put("LossSale", this.getLossSale(branchId, branchType));
		result.put("Transfer", this.getTransfer(branchId, branchType));
		result.put("Check", this.getCheck(branchId));
		result.put("OutIReturn", this.getInputReturn(branchId, "OUTERORDER"));
		result.put("Picking", this.getPicking());
		result.put("Distribution", this.getDistribution());
		result.put("In", this.getIn());
		result.put("Purchase", this.getPurchase(branchId));

		result.put("Plan", this.getPlan(branchId));
		result.put("ItemBuy", this.getItemBuy(branchId));
		result.put("CfInput", this.getCfInput());
		result.put("ItemInput", this.getItemInput());
		result.put("WorkReturn", this.getWorkReturn());
		result.put("WorkGet", this.getWorkGet());
		result.put("WorkSupper", this.getWorkSupper());
		result.put("NoWorkGet", this.getNoWorkGet());
		result.put("ItemOut", this.getItemOut());
		result.put("CfItemLoss", this.getCfItemLoss(branchId));
		result.put("CfSaleLoss", this.getCfSaleLoss(branchId));
		result.put("GetReturn", this.getGetReturn(branchId));
		result.put("CfCheck", this.getCfCheck(branchId));
		result.put("CfTransfer", this.getCfTransfer(branchId));
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getVisiable() throws SQLException {
		String uid = getLoginUserId();
		JSONObject result = new JSONObject();
		result.put("Request", permissionBean.queryVisi(uid, "restaurant0204"));
		result.put("Input", permissionBean.queryVisi(uid, "restaurant0303"));
		result.put("ReturnGoods", permissionBean.queryVisi(uid, "restaurant0503"));
		result.put("InputReturn", permissionBean.queryVisi(uid, "restaurant0603"));
		result.put("LossItem", permissionBean.queryVisi(uid, "restaurant0703"));
		result.put("LossSale", permissionBean.queryVisi(uid, "restaurant0705"));
		result.put("Transfer", permissionBean.queryVisi(uid, "restaurant0802"));
		result.put("Check", permissionBean.queryVisi(uid, "restaurant0906"));

		result.put("Purchase", permissionBean.queryVisi(uid, "lc0204"));
		result.put("In", permissionBean.queryVisi(uid, "lc0304"));
		result.put("Distribution", permissionBean.queryVisi(uid, "lc0306"));
		result.put("Picking", permissionBean.queryVisi(uid, "lc0308"));
		result.put("Confirmloss", permissionBean.queryVisi(uid, "lc0402"));
		result.put("OutReturn", permissionBean.queryVisi(uid, "lc0708"));
		result.put("GoodsReturn", permissionBean.queryVisi(uid, "lc0703"));
		result.put("Checkstorage", permissionBean.queryVisi(uid, "lc0805"));

		result.put("Plan", permissionBean.queryVisi(uid, "cf0202"));
		result.put("ItemBuy", permissionBean.queryVisi(uid, "cf0406"));
		result.put("Work", permissionBean.queryVisi(uid, "cf0204"));
		result.put("SupperGet", permissionBean.queryVisi(uid, "cf0304"));
		result.put("CfInput", permissionBean.queryVisi(uid, "cf0403"));
		result.put("ItemInput", permissionBean.queryVisi(uid, "cf0407"));
		result.put("WorkReturn", permissionBean.queryVisi(uid, "cf0409"));
		result.put("WorkGet", permissionBean.queryVisi(uid, "cf0410"));
		result.put("WorkSupper", permissionBean.queryVisi(uid, "cf0411"));
		result.put("NoWorkGet", permissionBean.queryVisi(uid, "cf0413"));
		result.put("ItemOut", permissionBean.queryVisi(uid, "cf0419"));
		result.put("CfItemLoss", permissionBean.queryVisi(uid, "cf0502"));
		result.put("CfSaleLoss", permissionBean.queryVisi(uid, "cf0505"));
		result.put("GetReturn", permissionBean.queryVisi(uid, "cf0603"));
		result.put("CfCheck", permissionBean.queryVisi(uid, "cf0706"));
		result.put("CfTransfer", permissionBean.queryVisi(uid, "cf1002"));
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RequestHeaderBean getRequestHeaderBean() {
		return requestHeaderBean;
	}

	public void setRequestHeaderBean(RequestHeaderBean requestHeaderBean) {
		this.requestHeaderBean = requestHeaderBean;
	}

	public InputHeaderBean getInputHeaderBean() {
		return inputHeaderBean;
	}

	public void setInputHeaderBean(InputHeaderBean inputHeaderBean) {
		this.inputHeaderBean = inputHeaderBean;
	}

	public ReturnGoodsHeaderBean getReturnGoodsHeaderBean() {
		return returnGoodsHeaderBean;
	}

	public void setReturnGoodsHeaderBean(ReturnGoodsHeaderBean returnGoodsHeaderBean) {
		this.returnGoodsHeaderBean = returnGoodsHeaderBean;
	}

	public InputReturnBean getInputReturnBean() {
		return inputReturnBean;
	}

	public void setInputReturnBean(InputReturnBean inputReturnBean) {
		this.inputReturnBean = inputReturnBean;
	}

	public LossHeaderBean getLossHeaderBean() {
		return lossHeaderBean;
	}

	public void setLossHeaderBean(LossHeaderBean lossHeaderBean) {
		this.lossHeaderBean = lossHeaderBean;
	}

	public TransferHeaderBean getTransferHeaderBean() {
		return transferHeaderBean;
	}

	public void setTransferHeaderBean(TransferHeaderBean transferHeaderBean) {
		this.transferHeaderBean = transferHeaderBean;
	}

	public CheckHeaderBean getCheckHeaderBean() {
		return checkHeaderBean;
	}

	public void setCheckHeaderBean(CheckHeaderBean checkHeaderBean) {
		this.checkHeaderBean = checkHeaderBean;
	}

	public PermissionBean getPermissionBean() {
		return permissionBean;
	}

	public void setPermissionBean(PermissionBean permissionBean) {
		this.permissionBean = permissionBean;
	}

	public PickingHeaderBean getPickingHeaderBean() {
		return pickingHeaderBean;
	}

	public void setPickingHeaderBean(PickingHeaderBean pickingHeaderBean) {
		this.pickingHeaderBean = pickingHeaderBean;
	}

	public ShippingHeaderBean getShippingHeaderBean() {
		return shippingHeaderBean;
	}

	public void setShippingHeaderBean(ShippingHeaderBean shippingHeaderBean) {
		this.shippingHeaderBean = shippingHeaderBean;
	}

	public PurchaseManageBean getPurchaseManageBean() {
		return purchaseManageBean;
	}

	public void setPurchaseManageBean(PurchaseManageBean purchaseManageBean) {
		this.purchaseManageBean = purchaseManageBean;
	}

	public ArrangmentHeaderBean getArrangmentHeaderBean() {
		return arrangmentHeaderBean;
	}

	public void setArrangmentHeaderBean(ArrangmentHeaderBean arrangmentHeaderBean) {
		this.arrangmentHeaderBean = arrangmentHeaderBean;
	}

	public RequisitionHeaderBean getRequisitionHeaderBean() {
		return requisitionHeaderBean;
	}

	public void setRequisitionHeaderBean(RequisitionHeaderBean requisitionHeaderBean) {
		this.requisitionHeaderBean = requisitionHeaderBean;
	}

}
