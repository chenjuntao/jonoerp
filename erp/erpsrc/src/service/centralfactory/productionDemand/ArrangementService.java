package service.centralfactory.productionDemand;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.ArrangmentDetailBean;
import logic.form.ArrangmentHeaderBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.WorkOrderHeaderBean;
import logic.form.WorkOrderItemBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.ArrangmentDetail;
import pojo.form.ArrangmentHeader;
import pojo.form.FormStatus;
import pojo.form.OperationVersion;
import pojo.form.WorkOrderHeader;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.BillStatus;

public class ArrangementService {

	// 单据状态表
	private FormStatusBean formStatusBean;
	private ArrangmentHeaderBean arrangmentHeaderBean;
	private ArrangmentDetailBean arrangmentDetailBean;
	private OperationVersionBean operationVersionBean;
	private WorkOrderHeaderBean workOrderHeaderBean;
	private WorkOrderItemBean workOrderItemBean;
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	/**
	 * 
	 * @param userId
	 * @param userName
	 * @param header
	 * @param jsonData
	 * @param formId
	 *            已审核的汇总单
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doCommit(String userId, String userName, ArrangmentHeader header, String jsonData, String formId)
			throws NoPrivilegeException, SQLException, NoConnection {

		// 将已审核汇总单的单据状态更改为已转化
		changebillStatus(userId, formId, "已转化");

		String cfCode = header.getFormBranchId();
		Date settleTime = branchBean.GetBranchById(cfCode).getBusinessDate();

		// 默认中央工厂的ID 200
		Date now = new Date();

		// 自动生成，拼音缩写+部门编号+日期+流水号
		String productFormId = "JH" + cfCode + DateTimeUtil.getDateTime(settleTime, "yyyyMMdd")
				+ DateTimeUtil.getDateTime(now, "HHmmss");

		JSONArray arr = JSONArray.fromObject(jsonData);

		// detail
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ArrangmentDetail detail = new ArrangmentDetail();

			// 单据编码
			detail.setFormId(productFormId);

			// 商品编码
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 商品名称
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);

			// 商品单位
			detail.setItemDimension(json.getString("itemDimension"));

			// 商品规格
			String itemSpecification = (String) json.get("itemSpecification");
			detail.setItemSpecification(itemSpecification);

			// 计划生产量
			if (json.get("produceCount") != null) {
				detail.setProduceCount(json.getDouble("produceCount"));
			}

			// 工单编号
			detail.setWorkOrderId(json.getString("workOrderId"));

			// // 生产车间
			// detail.setWorkshop(json.getString("workshop"));

			// 生产日期
			detail.setProduceTime(DateTimeUtil.parse(json.getString("produceTime")));

			// 生产周期
			detail.setProductionCycle(json.getDouble("productionCycle"));

			// 完工日期
			detail.setCompleteTime(DateTimeUtil.parse(json.getString("completeTime")));

			// 备注
			detail.setNote(json.getString("note"));

			arrangmentDetailBean.saveEntity(detail);
		}

		// head
		header.setFormId(productFormId);
		header.setFormRefId(formId);
		header.setFormTime(settleTime);
		header.setFormMakerId(userId);
		header.setFormMaker(userName);

		arrangmentHeaderBean.saveEntity(header);

		// 生产计划单生成之后该单的单据状态为未审核
		changebillStatus(userId, productFormId, "未审核");
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void changebillStatus(String userId, String formId, String status) throws NoPrivilegeException,
			SQLException, NoConnection {
		FormStatus fstatus = new FormStatus();
		fstatus.setFormId(formId);
		fstatus.setStatus(status);
		fstatus.setStatusTime(new Date());
		fstatus.setOperator(userId);
		formStatusBean.saveEntity(fstatus);
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String cfCode, String userId, String userName, ArrangmentHeader header, String jsonData,
			Integer currentVersion) throws NoPrivilegeException, SQLException, NoConnection {
		Date settleTime = branchBean.GetBranchById(cfCode).getBusinessDate();

		String formId = header.getFormId();
		arrangmentHeaderBean.audit(formId, userId, userName, header.getAuditTime());
		operationVersionBean.updateEntity(new OperationVersion(userId, userId, DateTimeUtil.getNow(),
				currentVersion + 1, OperationVersion.AUDITED, formId));

		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			String itemId = json.getString("itemId");
			Double produceCount = json.getDouble("produceCount");
			Date completeTime = DateTimeUtil.parse(json.getString("completeTime").toString());
			arrangmentDetailBean.updateProduceCount(formId, itemId, produceCount, completeTime);
			WorkOrderHeader workOrderHeader = new WorkOrderHeader();

			String workOrderId = json.getString("workOrderId");
			workOrderHeader.setFormId(json.getString("workOrderId"));

			workOrderHeader.setItemDimension(json.getString("itemDimension"));
			workOrderHeader.setItemDimension2(json.getString("itemDimension2"));

			workOrderHeader.setItemId(itemId);

			workOrderHeader.setItemName(json.getString("itemName"));
			workOrderHeader.setItemSpecification(json.getString("itemSpecification"));
			workOrderHeader.setFormTime(settleTime);

			workOrderHeader.setItemCount(produceCount);
			workOrderHeader.setItemCount2(json.getDouble("produceCount2"));
			workOrderHeader.setCompleteTime(completeTime);
			workOrderHeader.setWorkshop(json.getString("workshop"));

			workOrderHeader.setFormBranchId(cfCode);
			workOrderHeader.setFormMaker(userName);
			workOrderHeader.setFormMakerId(userId);
			workOrderHeader.setInputedCount(0.0);
			workOrderHeaderBean.saveEntity(workOrderHeader);

			formStatusBean.saveEntity(new FormStatus(workOrderId, "已审核", userId));

			// 根据半成品转化原料（伪二阶）
			workOrderItemBean.insert(workOrderId);
		}

		formStatusBean.saveEntity(new FormStatus(formId, BillStatus.AUDITED_CN, userId));
	}

	public void setArrangmentHeaderBean(ArrangmentHeaderBean arrangmentHeaderBean) {
		this.arrangmentHeaderBean = arrangmentHeaderBean;
	}

	public void setArrangmentDetailBean(ArrangmentDetailBean arrangmentDetailBean) {
		this.arrangmentDetailBean = arrangmentDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public void setWorkOrderItemBean(WorkOrderItemBean workOrderItemBean) {
		this.workOrderItemBean = workOrderItemBean;
	}

}
