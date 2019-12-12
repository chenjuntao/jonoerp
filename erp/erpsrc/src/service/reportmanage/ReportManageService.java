package service.reportmanage;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.reportmanage.ArrangmentDetailBean;
import logic.reportmanage.ArrangmentFormBean;
import logic.reportmanage.ArrangmentHeaderBean;
import logic.reportmanage.CheckDetailBean;
import logic.reportmanage.InputDetailBean;
import logic.reportmanage.InputFormBean;
import logic.reportmanage.InputHeaderBean;
import logic.reportmanage.LossDetailBean;
import logic.reportmanage.LossDishDetailBean;
import logic.reportmanage.LossFormBean;
import logic.reportmanage.LossHeaderBean;
import logic.reportmanage.OperateLogQueryBean;
import logic.reportmanage.ProductAnalysisBean;
import logic.reportmanage.PscanBean;
import logic.reportmanage.PurchasingDetailBean;
import logic.reportmanage.PurchasingHeaderBean;
import logic.reportmanage.RequestDetailBean;
import logic.reportmanage.RequestFormBean;
import logic.reportmanage.RequestHeaderBean;
import logic.reportmanage.RequisitionDetailBean;
import logic.reportmanage.RequisitionHeaderBean;
import logic.reportmanage.ReturnGoodsDeliveryDetailBean;
import logic.reportmanage.ReturnGoodsDeliveryFormBean;
import logic.reportmanage.ReturnGoodsDeliveryHeaderBean;
import logic.reportmanage.ReturnGoodsDetailBean;
import logic.reportmanage.ReturnGoodsFormBean;
import logic.reportmanage.ReturnGoodsHeaderBean;
import logic.reportmanage.ShippingAntiauditDetailBean;
import logic.reportmanage.ShippingAntiauditFormBean;
import logic.reportmanage.ShippingAntiauditHeaderBean;
import logic.reportmanage.ShippingDetailBean;
import logic.reportmanage.ShippingFormBean;
import logic.reportmanage.ShippingHeaderBean;
import logic.reportmanage.StorageBean;
import logic.reportmanage.StorageInOutDetailBean;
import logic.reportmanage.StorageInOutSummaryBean;
import logic.reportmanage.TransferDetailBean;
import logic.reportmanage.TransferFormBean;
import logic.reportmanage.TransferHeaderBean;
import logic.reportmanage.UserBean;
import logic.reportmanage.WorkOrderDetailBean;
import logic.reportmanage.WorkOrderFormBean;
import logic.reportmanage.WorkOrderHeaderBean;
import logic.store.BranchBean;
import pojo.reportmanage.ReportFlag;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.constant.BranchCode;

public class ReportManageService {
	private RequestHeaderBean rRequestHeaderBean;
	private RequestFormBean rRequestFormBean;
	private RequestDetailBean rRequestDetailBean;

	private PurchasingHeaderBean rPurchasingHeaderBean;
	private PurchasingDetailBean rPurchasingDetailBean;

	private InputHeaderBean rInputHeaderBean;
	private InputDetailBean rInputDetailBean;
	private InputFormBean rInputFormBean;

	private LossFormBean rLossFormBean;
	private LossHeaderBean rLossHeaderBean;
	private LossDetailBean rLossDetailBean;
	private LossDishDetailBean rLossDishDetailBean;

	private TransferFormBean rTransferFormBean;
	private TransferHeaderBean rTransferHeaderBean;
	private TransferDetailBean rTransferDetailBean;

	private ArrangmentFormBean rArrangmentFormBean;
	private ArrangmentHeaderBean rArrangmentHeaderBean;
	private ArrangmentDetailBean rArrangmentDetailBean;

	private WorkOrderHeaderBean rWorkOrderHeaderBean;
	private WorkOrderDetailBean rWorkOrderDetailBean;
	private WorkOrderFormBean rWorkOrderFormBean;

	private ShippingDetailBean rShippingDetailBean;
	private ShippingHeaderBean rShippingHeaderBean;
	private ShippingFormBean rShippingFormBean;

	private StorageBean rStorageBean;

	private CheckDetailBean rCheckDetailBean;
	private ReturnGoodsFormBean rReturnGoodsFormBean;
	private ReturnGoodsDetailBean rReturnGoodsDetailBean;
	private ReturnGoodsHeaderBean rReturnGoodsHeaderBean;

	private ReturnGoodsDeliveryFormBean rReturnGoodsDeliveryFormBean;
	private ReturnGoodsDeliveryDetailBean rReturnGoodsDeliveryDetailBean;
	private ReturnGoodsDeliveryHeaderBean rReturnGoodsDeliveryHeaderBean;

	private ShippingAntiauditFormBean rShippingAntiauditFormBean;
	private ShippingAntiauditDetailBean rShippingAntiauditDetailBean;
	private ShippingAntiauditHeaderBean rShippingAntiauditHeaderBean;

	private PscanBean rPscanBean;

	private UserBean rUserBean;
	private OperateLogQueryBean rOperateLogQueryBean;

	private StorageInOutDetailBean rStorageInOutDetailBean;
	private StorageInOutSummaryBean rStorageInOutSummaryBean;

	private RequisitionHeaderBean rRequisitionHeaderBean;
	private RequisitionDetailBean rRequisitionDetailBean;

	private ProductAnalysisBean productAnalysisBean;
	
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public Date getBusinessDate(String branchType) throws NoPrivilegeException, SQLException, NoConnection {
		if(branchType.equals("CENTRALFACTORY")){
			return branchBean.GetBranchById(BranchCode.DEFAULT_CENTRALFACTORY).getBusinessDate();
		}else if(branchType.equals("LOGISTICSCENTER")){
			return branchBean.GetBranchById(BranchCode.DEFAULT_LOGISTICSCENTER).getBusinessDate();
		}else{
			return branchBean.getMaxBusinessDate();
		}
	}

	public Date getBusinessByBranchId(String loginBranchId) throws NoPrivilegeException, SQLException, NoConnection {
		return branchBean.GetBranchById(loginBranchId).getBusinessDate();
	}

	public int count(String reportFlag, String queryStr, String sortStr) throws NoPrivilegeException, SQLException,
			NoConnection {

		if (ReportFlag.D_T2_USER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rUserBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUEST_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUEST_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestHeaderBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUEST_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestDetailBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUISITION_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionHeaderBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUISITION_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_PURCHASING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingHeaderBean.count(queryStr);
		}

		if (ReportFlag.D_T1_PURCHASING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_INPUT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputHeaderBean.count(queryStr);
		}

		if (ReportFlag.D_T1_INPUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.countInputReturn(queryStr);
		}

		if (ReportFlag.D_T1_LOSS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossHeaderBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_LOSS_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.counts(queryStr);
		}

		if (ReportFlag.D_T1_LOSS_DISH_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDishDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_TRANSFER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferHeaderBean.count(queryStr);
		}

		if (ReportFlag.D_T1_TRANSFER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_TRANSFER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentHeaderBean.count(queryStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderHeaderBean.count(queryStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderFormBean.count(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingHeaderBean.count(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T2_STORAGE.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageBean.count(queryStr);
		}

		if (ReportFlag.D_T1_CHECK_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rCheckDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDetailBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsHeaderBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryHeaderBean.count(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditFormBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditDetailBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditHeaderBean.count(queryStr, sortStr);
		}

		if (ReportFlag.OTHER_P_SCAN.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPscanBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T0_OPERATELOG.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rOperateLogQueryBean.count(queryStr, sortStr);
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutDetailBean.count(queryStr);
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_SUMMARY.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutSummaryBean.count(queryStr);
		}

		if (ReportFlag.PRODUCT_ANALYSIS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return productAnalysisBean.count(queryStr);
		}

		return 0;
	}

	public List<Map> query(String reportFlag, String queryStr, String sortStr, int start, int end)
			throws NoPrivilegeException, SQLException, NoConnection {

		if (ReportFlag.D_T2_USER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rUserBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUEST_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUEST_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestHeaderBean.query(start, end, queryStr, sortStr);
		}
		if (ReportFlag.D_T1_REQUEST_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUISITION_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUISITION_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_PURCHASING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_PURCHASING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.queryIR(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.querys(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_DISH_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDishDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_TRANSFER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_TRANSFER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderHeaderBean.query(start, end, queryStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T2_STORAGE.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageBean.query(start, end, queryStr);
		}

		if (ReportFlag.D_T1_CHECK_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rCheckDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditHeaderBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T1_TRANSFER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferFormBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.OTHER_P_SCAN.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPscanBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T0_OPERATELOG.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rOperateLogQueryBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutDetailBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_SUMMARY.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutSummaryBean.query(start, end, queryStr, sortStr);
		}

		if (ReportFlag.PRODUCT_ANALYSIS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return productAnalysisBean.query(start, end, queryStr, sortStr);
		}

		return null;
	}

	public Map sum(String reportFlag, String queryStr, String sortStr) throws NoPrivilegeException, SQLException,
			NoConnection {

		if (ReportFlag.D_T1_REQUEST_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestFormBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUEST_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestHeaderBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUEST_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestDetailBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUISITION_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionHeaderBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_REQUISITION_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_PURCHASING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingHeaderBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_PURCHASING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_INPUT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputFormBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputHeaderBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_INPUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_INPUT_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.sumIR(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossFormBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossHeaderBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_LOSS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_LOSS_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.sums(queryStr);
		}

		if (ReportFlag.D_T1_LOSS_DISH_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDishDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_TRANSFER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferHeaderBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_TRANSFER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentHeaderBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_ARRENGMENT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderHeaderBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_WORK_ORDER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderFormBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingHeaderBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingFormBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T2_STORAGE.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_CHECK_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rCheckDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsFormBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDetailBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsHeaderBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryFormBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryDetailBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryHeaderBean.sum(queryStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditDetailBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditHeaderBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T1_TRANSFER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferFormBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.OTHER_P_SCAN.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPscanBean.sum(queryStr, sortStr);
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutDetailBean.sum(queryStr);
		}

		if (ReportFlag.PRODUCT_ANALYSIS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return productAnalysisBean.sum(queryStr);
		}

		return null;
	}

	public Map getColumnPrefix(String reportFlag) {
		if (ReportFlag.D_T2_USER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rUserBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_REQUEST_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_REQUEST_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_REQUEST_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_CHECK_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rCheckDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_REQUISITION_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_REQUISITION_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_PURCHASING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_PURCHASING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_INPUT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_INPUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_INPUT_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_INPUT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_TRANSFER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_TRANSFER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_LOSS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_LOSS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_LOSS_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_LOSS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_LOSS_DISH_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDishDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_ARRENGMENT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_ARRENGMENT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_WORK_ORDER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_WORK_ORDER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_WORK_ORDER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T2_STORAGE.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_SHIPPING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_SHIPPING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_SHIPPING_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_ARRENGMENT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentFormBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_ARRENGMENT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentHeaderBean.getColmunPrefix();
		}

		if (ReportFlag.D_T1_TRANSFER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferFormBean.getColmunPrefix();
		}

		if (ReportFlag.OTHER_P_SCAN.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPscanBean.getColmunPrefix();
		}

		if (ReportFlag.D_T0_OPERATELOG.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rOperateLogQueryBean.getColmunPrefix();
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutDetailBean.getColmunPrefix();
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_SUMMARY.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutSummaryBean.getColmunPrefix();
		}

		if (ReportFlag.PRODUCT_ANALYSIS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return productAnalysisBean.getColmunPrefix();
		}

		return null;
	}

	public Map getColumnType(String reportFlag) {

		if (ReportFlag.D_T2_USER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rUserBean.getColmunType();
		}

		if (ReportFlag.D_T1_REQUEST_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_REQUEST_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_REQUEST_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequestDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_REQUISITION_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_REQUISITION_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rRequisitionDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_PURCHASING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_PURCHASING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPurchasingDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_INPUT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_INPUT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_INPUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_INPUT_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rInputDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_LOSS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_LOSS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_LOSS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_LOSS_DETAILS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_LOSS_DISH_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rLossDishDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_TRANSFER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_TRANSFER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_ARRENGMENT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_ARRENGMENT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_WORK_ORDER_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_WORK_ORDER_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_WORK_ORDER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rWorkOrderFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_SHIPPING_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_SHIPPING_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_SHIPPING_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingFormBean.getColmunType();
		}

		if (ReportFlag.D_T2_STORAGE.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageBean.getColmunType();
		}

		if (ReportFlag.D_T1_CHECK_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rCheckDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_RETURN_GOODS_DELIVERY_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rReturnGoodsDeliveryHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditDetailBean.getColmunType();
		}

		if (ReportFlag.D_T1_SHIPPING_ANTIAUDIT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rShippingAntiauditHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_ARRENGMENT_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentFormBean.getColmunType();
		}

		if (ReportFlag.D_T1_ARRENGMENT_HEADER.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rArrangmentHeaderBean.getColmunType();
		}

		if (ReportFlag.D_T1_TRANSFER_FORM.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rTransferFormBean.getColmunType();
		}

		if (ReportFlag.OTHER_P_SCAN.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rPscanBean.getColmunType();
		}

		if (ReportFlag.D_T0_OPERATELOG.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rOperateLogQueryBean.getColmunType();
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_DETAIL.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutDetailBean.getColmunType();
		}

		if (ReportFlag.D_T0_STORAGE_IN_OUT_SUMMARY.toUpperCase().equals(reportFlag.toUpperCase())) {
			return rStorageInOutSummaryBean.getColmunType();
		}

		if (ReportFlag.PRODUCT_ANALYSIS.toUpperCase().equals(reportFlag.toUpperCase())) {
			return productAnalysisBean.getColmunType();
		}

		return null;
	}

	public void setrPurchasingHeaderBean(PurchasingHeaderBean rPurchasingHeaderBean) {
		this.rPurchasingHeaderBean = rPurchasingHeaderBean;
	}

	public void setrRequestHeaderBean(RequestHeaderBean rRequestHeaderBean) {
		this.rRequestHeaderBean = rRequestHeaderBean;
	}

	public void setrRequestDetailBean(RequestDetailBean rRequestDetailBean) {
		this.rRequestDetailBean = rRequestDetailBean;
	}

	public void setrPurchasingDetailBean(PurchasingDetailBean rPurchasingDetailBean) {
		this.rPurchasingDetailBean = rPurchasingDetailBean;
	}

	public void setrInputHeaderBean(InputHeaderBean rInputHeaderBean) {
		this.rInputHeaderBean = rInputHeaderBean;
	}

	public void setrInputDetailBean(InputDetailBean rInputDetailBean) {
		this.rInputDetailBean = rInputDetailBean;
	}

	public void setrLossHeaderBean(LossHeaderBean rLossHeaderBean) {
		this.rLossHeaderBean = rLossHeaderBean;
	}

	public void setrLossDetailBean(LossDetailBean rLossDetailBean) {
		this.rLossDetailBean = rLossDetailBean;
	}

	public void setrTransferHeaderBean(TransferHeaderBean rTransferHeaderBean) {
		this.rTransferHeaderBean = rTransferHeaderBean;
	}

	public void setrTransferDetailBean(TransferDetailBean rTransferDetailBean) {
		this.rTransferDetailBean = rTransferDetailBean;
	}

	public void setrArrangmentHeaderBean(ArrangmentHeaderBean rArrangmentHeaderBean) {
		this.rArrangmentHeaderBean = rArrangmentHeaderBean;
	}

	public void setrArrangmentDetailBean(ArrangmentDetailBean rArrangmentDetailBean) {
		this.rArrangmentDetailBean = rArrangmentDetailBean;
	}

	public void setrWorkOrderHeaderBean(WorkOrderHeaderBean rWorkOrderHeaderBean) {
		this.rWorkOrderHeaderBean = rWorkOrderHeaderBean;
	}

	public void setrWorkOrderDetailBean(WorkOrderDetailBean rWorkOrderDetailBean) {
		this.rWorkOrderDetailBean = rWorkOrderDetailBean;
	}

	public void setrStorageBean(StorageBean rStorageBean) {
		this.rStorageBean = rStorageBean;
	}

	public void setrShippingDetailBean(ShippingDetailBean rShippingDetailBean) {
		this.rShippingDetailBean = rShippingDetailBean;
	}

	public void setrShippingHeaderBean(ShippingHeaderBean rShippingHeaderBean) {
		this.rShippingHeaderBean = rShippingHeaderBean;
	}

	public void setrRequestFormBean(RequestFormBean rRequestFormBean) {
		this.rRequestFormBean = rRequestFormBean;
	}

	public void setrInputFormBean(InputFormBean rInputFormBean) {
		this.rInputFormBean = rInputFormBean;
	}

	public void setrLossFormBean(LossFormBean rLossFormBean) {
		this.rLossFormBean = rLossFormBean;
	}

	public void setrReturnGoodsFormBean(ReturnGoodsFormBean rReturnGoodsFormBean) {
		this.rReturnGoodsFormBean = rReturnGoodsFormBean;
	}

	public void setrReturnGoodsDetailBean(ReturnGoodsDetailBean rReturnGoodsDetailBean) {
		this.rReturnGoodsDetailBean = rReturnGoodsDetailBean;
	}

	public void setrReturnGoodsHeaderBean(ReturnGoodsHeaderBean rReturnGoodsHeaderBean) {
		this.rReturnGoodsHeaderBean = rReturnGoodsHeaderBean;
	}

	public void setrReturnGoodsDeliveryFormBean(ReturnGoodsDeliveryFormBean rReturnGoodsDeliveryFormBean) {
		this.rReturnGoodsDeliveryFormBean = rReturnGoodsDeliveryFormBean;
	}

	public void setrReturnGoodsDeliveryDetailBean(ReturnGoodsDeliveryDetailBean rReturnGoodsDeliveryDetailBean) {
		this.rReturnGoodsDeliveryDetailBean = rReturnGoodsDeliveryDetailBean;
	}

	public void setrReturnGoodsDeliveryHeaderBean(ReturnGoodsDeliveryHeaderBean rReturnGoodsDeliveryHeaderBean) {
		this.rReturnGoodsDeliveryHeaderBean = rReturnGoodsDeliveryHeaderBean;
	}

	public void setrShippingAntiauditFormBean(ShippingAntiauditFormBean rShippingAntiauditFormBean) {
		this.rShippingAntiauditFormBean = rShippingAntiauditFormBean;
	}

	public void setrShippingAntiauditDetailBean(ShippingAntiauditDetailBean rShippingAntiauditDetailBean) {
		this.rShippingAntiauditDetailBean = rShippingAntiauditDetailBean;
	}

	public void setrShippingAntiauditHeaderBean(ShippingAntiauditHeaderBean rShippingAntiauditHeaderBean) {
		this.rShippingAntiauditHeaderBean = rShippingAntiauditHeaderBean;
	}

	public void setrArrangmentFormBean(ArrangmentFormBean rArrangmentFormBean) {
		this.rArrangmentFormBean = rArrangmentFormBean;
	}

	public void setrTransferFormBean(TransferFormBean rTransferFormBean) {
		this.rTransferFormBean = rTransferFormBean;
	}

	public void setrPscanBean(PscanBean rPscanBean) {
		this.rPscanBean = rPscanBean;
	}

	public void setrShippingFormBean(ShippingFormBean rShippingFormBean) {
		this.rShippingFormBean = rShippingFormBean;
	}

	public void setrWorkOrderFormBean(WorkOrderFormBean rWorkOrderFormBean) {
		this.rWorkOrderFormBean = rWorkOrderFormBean;
	}

	public void setrUserBean(UserBean rUserBean) {
		this.rUserBean = rUserBean;
	}

	public void setrOperateLogQueryBean(OperateLogQueryBean rOperateLogQueryBean) {
		this.rOperateLogQueryBean = rOperateLogQueryBean;
	}

	public void setrStorageInOutDetailBean(StorageInOutDetailBean rStorageInOutDetailBean) {
		this.rStorageInOutDetailBean = rStorageInOutDetailBean;
	}

	public void setrStorageInOutSummaryBean(StorageInOutSummaryBean rStorageInOutSummaryBean) {
		this.rStorageInOutSummaryBean = rStorageInOutSummaryBean;
	}

	public void setrRequisitionHeaderBean(RequisitionHeaderBean rRequisitionHeaderBean) {
		this.rRequisitionHeaderBean = rRequisitionHeaderBean;
	}

	public void setrRequisitionDetailBean(RequisitionDetailBean rRequisitionDetailBean) {
		this.rRequisitionDetailBean = rRequisitionDetailBean;
	}

	public void setrLossDishDetailBean(LossDishDetailBean rLossDishDetailBean) {
		this.rLossDishDetailBean = rLossDishDetailBean;
	}

	public void setrCheckDetailBean(CheckDetailBean rCheckDetailBean) {
		this.rCheckDetailBean = rCheckDetailBean;
	}

	public void setProductAnalysisBean(ProductAnalysisBean productAnalysisBean) {
		this.productAnalysisBean = productAnalysisBean;
	}

}
