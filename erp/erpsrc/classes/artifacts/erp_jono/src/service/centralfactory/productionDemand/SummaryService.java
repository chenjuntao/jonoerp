package service.centralfactory.productionDemand;

import java.sql.SQLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

import logic.NoConnection;
import logic.form.ArrangmentDetailBean;
import logic.form.ArrangmentHeaderBean;
import logic.form.CollectRefFormBean;
import logic.form.FormStatusBean;
import logic.form.OperationVersionBean;
import logic.form.PurchasingHeaderBean;
import logic.store.BranchBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONNull;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.ArrangmentDetail;
import pojo.form.ArrangmentHeader;
import pojo.form.CollectRefForm;
import pojo.form.FormStatus;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.form.OperateVersionUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.NumberUtil;

public class SummaryService {

	// 单据状态表
	private FormStatusBean formStatusBean;

	private CollectRefFormBean collectRefFormBean;

	private PurchasingHeaderBean purchasingHeaderBean;
	private ArrangmentHeaderBean arrangmentHeaderBean;
	private ArrangmentDetailBean arrangmentDetailBean;

	private OperationVersionBean operationVersionBean;
	
	private BranchBean branchBean;
	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	// 汇总生成生产计划单
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String doCommit(String ids, String userId, ArrangmentHeader arrangmentHeader, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = arrangmentHeader.getFormBranchId();
		Date settleTime = branchBean.GetBranchById(cfCode).getBusinessDate();

		String formBody = FormUtil.genFormIdBody("JH", cfCode, settleTime);

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;

		String workshop = "";
		String formId = "";
		String formIds = "";
		JSONArray arr = JSONArray.fromObject(jsonData);
		class BranchComparator implements Comparator<Map> {
			@Override
			public int compare(Map a, Map b) {
				String keya = (String) a.get("workshop");
				String keyb = (String) b.get("workshop");
				return keya.compareToIgnoreCase(keyb);
			}
		}
		Collections.sort(arr, new BranchComparator());
		int rowNumber = 1;
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ArrangmentDetail detail = new ArrangmentDetail();
			String newWorkshop = json.getString("workshop");
			if (!workshop.equals(newWorkshop)) {
				formId = formBody + FormUtil.formatSerial(newSerial);
				workshop = newWorkshop;
				formIds += formId + ",";
				newSerial++;
				arrangmentHeader.setFormId(formId);
				arrangmentHeader.setFormTime(settleTime);
				arrangmentHeaderBean.saveEntity(arrangmentHeader);

				// 插入版本记录信息
				OperateVersionUtil.save(formId);

				formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
			}

			// 单据编码
			detail.setFormId(formId);

			// 商品编码
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 商品名称
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);

			// 商品库存单位
			detail.setItemDimension(json.getString("itemDimension"));

			// 商品包装单位
			detail.setItemDimension2(json.getString("itemDimension2"));
			// 商品规格
			Object itemSpecification = json.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				detail.setItemSpecification((String) itemSpecification);
			}

			// 包装单位为基础的净需求
			Double orderCount2 = json.getDouble("orderCount2");
			detail.setProduceCount2(orderCount2);

			// 库存单位为基础的净需求
			Double orderCount = json.getDouble("orderCount2") * json.getDouble("deliveryFactor");
			detail.setProduceCount(orderCount);

			// 毛需求
			Double itemCount = json.getDouble("itemCount");

			// 生产工单
			detail.setWorkOrderId(FormUtil.generateFormId("GD", cfCode, settleTime) + rowNumber);

			// 生产车间

			// 生产日期
			detail.setProduceTime(DateTimeUtil.parse(json.getString("businessDate").toString()));

			// 完工日期
			detail.setCompleteTime(DateTimeUtil.parse(json.getString("completeTime").toString()));

			// 生产周期
			detail.setProductionCycle(NumberUtil.getDouble(json.get("productionCycle")));
			detail.setWorkshop(newWorkshop);

			arrangmentDetailBean.saveEntity(detail);

			String[] idsArr = ids.split(",");
			for (int i = 0, length = idsArr.length; i < length; i++) {
				collectRefFormBean.saveEntity(new CollectRefForm(formId, itemId, idsArr[i]));
			}

			rowNumber++;
		}

		// 设置采购单的状态
		String[] pFormIdArr = ids.split(",");
		for (String pFormId : pFormIdArr) {
			purchasingHeaderBean.savePlanStatus(pFormId, "已汇总");
		}

		return formIds;
	}

	/*
	 * 手动生成计划单
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public String doCommit(String userId, ArrangmentHeader arrangmentHeader, String jsonData)
			throws NoPrivilegeException, SQLException, NoConnection {
		String cfCode = arrangmentHeader.getFormBranchId();
		Date settleTime = branchBean.GetBranchById(cfCode).getBusinessDate();

		String formBody = FormUtil.genFormIdBody("JHM", cfCode, settleTime);

		Integer newSerial = Integer.parseInt(operationVersionBean.getMaxFormIndex(formBody + "%")) + 1;

		String workshop = "";
		String formId = "";
		String formIds = "";
		JSONArray arr = JSONArray.fromObject(jsonData);

		int rowNumber = 1;
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			ArrangmentDetail detail = new ArrangmentDetail();
			String newWorkshop = json.getString("workshop");
			if (!workshop.equals(newWorkshop)) {
				formId = formBody + FormUtil.formatSerial(newSerial);
				workshop = newWorkshop;
				formIds += formId + ",";
				newSerial++;
				arrangmentHeader.setFormId(formId);
				arrangmentHeader.setFormTime(settleTime);
				arrangmentHeaderBean.saveEntity(arrangmentHeader);

				// 插入版本记录信息
				OperateVersionUtil.save(formId);

				formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
			}

			// 单据编码
			detail.setFormId(formId);

			// 商品编码
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 商品名称
			String itemName = json.getString("itemName");
			detail.setItemName(itemName);

			// 商品单位
			detail.setItemDimension(json.getString("itemDimension"));

			// 商品规格
			Object itemSpecification = json.get("itemSpecification");
			if (!JSONNull.getInstance().equals(itemSpecification)) {
				detail.setItemSpecification((String) itemSpecification);
			}

			// 净需求
			Double orderCount = json.getDouble("orderCount");
			detail.setProduceCount(orderCount);

			// 生产工单
			detail.setWorkOrderId(FormUtil.generateFormId("GD", cfCode, settleTime) + rowNumber);

			// 生产车间

			// 生产日期
			detail.setProduceTime(DateTimeUtil.parse(json.getString("businessDate").toString()));

			// 完工日期
			detail.setCompleteTime(DateTimeUtil.parse(json.getString("completeTime").toString()));

			// 生产周期
			detail.setProductionCycle(NumberUtil.getDouble(json.get("productionCycle")));

			arrangmentDetailBean.saveEntity(detail);

			rowNumber++;
		}

		return formIds;
	}

	public Integer getSummaryCount(String ids) throws NoPrivilegeException, SQLException, NoConnection {
		return collectRefFormBean.getSummarycount(ids);
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setArrangmentDetailBean(ArrangmentDetailBean arrangmentDetailBean) {
		this.arrangmentDetailBean = arrangmentDetailBean;
	}

	public void setArrangmentHeaderBean(ArrangmentHeaderBean arrangmentHeaderBean) {
		this.arrangmentHeaderBean = arrangmentHeaderBean;
	}

	public void setCollectRefFormBean(CollectRefFormBean collectRefFormBean) {
		this.collectRefFormBean = collectRefFormBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setOperationVersionBean(OperationVersionBean operationVersionBean) {
		this.operationVersionBean = operationVersionBean;
	}

}
