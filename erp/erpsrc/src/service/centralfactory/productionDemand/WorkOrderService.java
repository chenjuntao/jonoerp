package service.centralfactory.productionDemand;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.ArrangmentDetailBean;
import logic.form.FormStatusBean;
import logic.form.WorkOrderHeaderBean;
import logic.form.WorkOrderItemBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.ArrangmentDetail;
import pojo.form.FormStatus;
import pojo.form.WorkOrderDetail;
import pojo.form.WorkOrderHeader;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class WorkOrderService {

	// 单据状态表
	private FormStatusBean formStatusBean;

	private WorkOrderHeaderBean workOrderHeaderBean;
	private ArrangmentDetailBean arrangmentDetailBean;
	private WorkOrderItemBean workOrderItemBean;
	
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setArrangmentDetailBean(ArrangmentDetailBean arrangmentDetailBean) {
		this.arrangmentDetailBean = arrangmentDetailBean;
	}

	public void setWorkOrderHeaderBean(WorkOrderHeaderBean workOrderHeaderBean) {
		this.workOrderHeaderBean = workOrderHeaderBean;
	}

	public void setWorkOrderItemBean(WorkOrderItemBean workOrderItemBean) {
		this.workOrderItemBean = workOrderItemBean;
	}

	// 生产工单不改变生产计划单单据状态
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doCommit(String arrangementFormId, String userId, String userName, WorkOrderHeader header,
			String jsonData) throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = header.getFormBranchId();
		Date settleTime = branchBean.GetBranchById(cfCode).getBusinessDate();
		JSONArray arr = JSONArray.fromObject(jsonData);

		// detail
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			WorkOrderDetail detail = new WorkOrderDetail();

			// 单据编码
			detail.setFormId(header.getFormId());

			// 制程
			String productionName = json.getString("productionName");
			detail.setProductionName(productionName);

			// 制程顺序号
			Integer productionStep = json.getInt("productionStep");
			detail.setProductionStep(productionStep);

			// 生成人员
			String productionMan = json.getString("productionMan");
			detail.setProductionMan(productionMan);

			detail.setIsCompleted(0);

			// 日期

			String productionTime = "";
			try {
				productionTime = json.getString("productionTime");
			} catch (Exception e) {
				productionTime = "";
			}
			detail.setProductionTime(TextUtil.isEmpty(productionTime) ? null : DateTimeUtil.parse(productionTime));

			// 产量
			if (json.get("productionCount") != null) {
				detail.setProductionCount(json.getDouble("productionCount"));
			}

			// 备注
			String productionNote = json.getString("productionNote");
			detail.setProductionNote(productionNote);

		}

		// head
		header.setFormTime(settleTime);
		header.setFormMakerId(userId);
		header.setFormMaker(userName);
		header.setInputedCount(0.0);
		workOrderHeaderBean.saveEntity(header);

		formStatusBean.saveEntity(new FormStatus(header.getFormId(), "未审核", userId));
	}

	// 批量生成生产工单
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doBatchCommit(String cfCode, String userId, String userName, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {

		Date settleTime = branchBean.GetBranchById(cfCode).getBusinessDate();
		JSONArray arr = JSONArray.fromObject(jsonData);

		// head
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			WorkOrderHeader workOrderHeader = new WorkOrderHeader();

			String workOrderId = json.getString("workOrderId");
			workOrderHeader.setFormId(json.getString("workOrderId"));

			workOrderHeader.setItemDimension(json.getString("itemDimension"));

			String itemId = json.getString("itemId");
			workOrderHeader.setItemId(itemId);

			workOrderHeader.setItemName(json.getString("itemName"));
			workOrderHeader.setItemSpecification(json.getString("itemSpecification"));
			workOrderHeader.setFormTime(settleTime);
			workOrderHeader.setItemCount(json.getDouble("itemCount"));
			workOrderHeader.setWorkshop(json.getString("workshop"));

			workOrderHeader.setFormBranchId(cfCode);
			workOrderHeader.setFormMaker(userName);
			workOrderHeader.setFormMakerId(userId);
			workOrderHeader.setInputedCount(0.0);

			workOrderHeaderBean.saveEntity(workOrderHeader);

			formStatusBean.saveEntity(new FormStatus(workOrderId, "未审核", userId));
		}
	}

	// 批量审核工单
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void auditBill(String userId, String userName, String jsonData) throws NoPrivilegeException, SQLException,
			NoConnection {

		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			WorkOrderHeader workOrderHeader = new WorkOrderHeader();

			// 单据编码
			String formId = json.getString("formId");
			workOrderHeader.setFormId(formId);

			// 计划生产量
			if (json.get("itemCount") != null) {
				workOrderHeader.setItemCount(json.getDouble("itemCount"));
			}

			workOrderHeader.setFormMakerId(json.getString("formMakerId"));
			workOrderHeader.setFormMaker(json.getString("formMaker"));
			workOrderHeader.setFormTime(DateTimeUtil.parse(json.getString("formTime")));
			workOrderHeader.setAuditTime(DateTimeUtil.parse(json.getString("formTime")));
			workOrderHeader.setFormNote(json.getString("formNote"));

			// 更新工单表头
			workOrderHeaderBean.updateEntity(workOrderHeader);

			// 修改工单对应的完工日期
			Date completeTime = DateTimeUtil.parse(json.getString("completeTime"));

			ArrangmentDetail detail = new ArrangmentDetail();
			detail.setWorkOrderId(formId);
			detail.setCompleteTime(completeTime);
			arrangmentDetailBean.updateByWorkId(detail);

			// 修改工单单据状态为已审核
			formStatusBean.saveEntity(new FormStatus(formId, "已审核", userId));

			// 工单审核后生成对应的生产工单原料明细
			insertWorkItem(formId);
		}

	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	private void insertWorkItem(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		workOrderItemBean.insert(formId);
	}

	/**
	 * 填写产出记录
	 * 
	 * @param userId
	 * @param userName
	 * @param header
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void output(String userId, WorkOrderHeader header) throws NoPrivilegeException, SQLException, NoConnection {
		workOrderHeaderBean.output(header);

		formStatusBean.saveEntity(new FormStatus(header.getFormId(), "已完成", userId));
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

}
