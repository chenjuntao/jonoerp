package service.supplier;

import java.sql.SQLException;
import java.util.Date;

import logic.NoConnection;
import logic.form.FormStatusBean;
import logic.form.PurchasingDetailBean;
import logic.form.PurchasingHeaderBean;
import logic.form.StatementDetailBean;
import logic.form.StatementHeadBean;
import logic.store.OptionBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pojo.form.FormStatus;
import pojo.form.PurchasingDetail;
import pojo.form.PurchasingHeader;
import pojo.form.StatementDetail;
import pojo.form.StatementHead;

import com.tanry.business.form.FormUtil;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.TextUtil;

public class SupplierService {

	// 单据状态表
	private FormStatusBean formStatusBean;

	private PurchasingDetailBean purchasingDetailBean;
	private PurchasingHeaderBean purchasingHeaderBean;

	private StatementHeadBean statementHeadBean;
	private StatementDetailBean statementDetailBean;
	private OptionBean optionBean;

	// 确认发货，确认发货时可能修改数量以及金额
	// 采购单明细修改金额，采购单表头相应也要修改总金额，金额最大的商品
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doConfirm(String formId, String jsonData, String userId, String status) throws NoPrivilegeException,
			SQLException, NoConnection {

		JSONArray arr = JSONArray.fromObject(jsonData);
		Double all_pay_amt = 0.0, temp = 0.0;
		String max_pay_item = "";

		// detail
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			PurchasingDetail detail = new PurchasingDetail();

			detail.setFormId(formId);

			// 商品编码
			String itemId = json.getString("itemId");
			detail.setItemId(itemId);

			// 商品名称
			String itemName = json.getString("itemName");

			// 数量
			Double itemCount = json.getDouble("itemCount");
			detail.setItemCount(itemCount);

			// 收货部门ID
			String receiverId = json.getString("receiverId");
			detail.setReceiverId(receiverId);

			// 金额
			Double payAmt = json.getDouble("payAmt");
			detail.setPayAmt(payAmt);
			all_pay_amt += payAmt;

			if (payAmt >= temp) {
				temp = payAmt;
				max_pay_item = "[" + itemId + "]" + itemName;
			}
			purchasingDetailBean.updateEntity(detail);
		}

		PurchasingHeader purchasingHeader = purchasingHeaderBean.queryById(formId);
		purchasingHeader.setAllPayAmt(all_pay_amt);
		purchasingHeader.setMaxPayItem(max_pay_item);
		purchasingHeaderBean.updateEntity(purchasingHeader);

		formStatusBean.saveEntity(new FormStatus(formId, status, userId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doInConfirm(String userId, String formId, String status, String subFormIds, String formType)
			throws NoPrivilegeException, SQLException, NoConnection {

		if (!TextUtil.isEmpty(status)) {
			formStatusBean.saveEntity(new FormStatus(formId, status, userId));
		}

		String str = "供应商已确认";
		if (!TextUtil.isEmpty(formType) && "W".equals(formType)) {
			str = "外部订货方已确认";
		} else if ("J".equals(formType)) {
			str = "加盟店已确认";
		}

		if (!TextUtil.isEmpty(subFormIds)) {
			FormStatus fstatus = new FormStatus("", str, userId);
			fstatus.setIsCurrent(2);// 特殊状态，表示供应商/订货方已确认
			for (String inFormId : subFormIds.split(",")) {
				fstatus.setFormId(inFormId);
				formStatusBean.saveSpecial(fstatus);
			}
		}
	}

	public void doDelete(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		statementDetailBean.deleteByForm(formId);
	}

	/**
	 * 生成对账单
	 */
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void createBill(String userId, String userName, String jsonData, StatementHead header, String formType)
			throws NoPrivilegeException, SQLException, NoConnection {
		Date dateVar = DateTimeUtil.parse(DateTimeUtil.getDate());
		int newSerial = statementHeadBean.newSerial(dateVar, formType);

		String formHead = "G".equals(formType) ? "GDZ" : "WDZ";
		String formId = FormUtil.genFormIdBody(formHead, "", dateVar) + FormUtil.formatSerial(newSerial);

		header.setFormId(formId);
		header.setFormType(formType);
		header.setFormMakerId(userId);
		header.setFormMaker(userName);
		header.setFormTime(dateVar);

		statementHeadBean.saveEntity(header);

		JSONArray arr = JSONArray.fromObject(jsonData);
		for (Object obj : arr) {
			JSONObject json = (JSONObject) obj;
			StatementDetail detail = new StatementDetail();

			detail.setFormId(formId);

			detail.setSubFormId(json.getString("formId"));
			detail.setFormRefId(json.getString("formRefId"));
			detail.setAllPayAmt(json.getDouble("allPayAmt"));
			detail.setFormNote(json.getString("formNote"));
			detail.setFormOperate(json.getString("inputer"));
			detail.setFormOperateId(json.getString("inputerId"));
			detail.setFormOperateTime(DateTimeUtil.parse(json.getString("inputTime")));

			statementDetailBean.saveEntity(detail);
		}

		formStatusBean.saveEntity(new FormStatus(formId, "未审核", userId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void doOperate(String formId, String userId, String status) throws NoPrivilegeException, SQLException,
			NoConnection {
		formStatusBean.saveEntity(new FormStatus(formId, status, userId));
	}

	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void updateStatus(String formId, String userId) throws NoPrivilegeException, SQLException, NoConnection {
		formStatusBean.saveEntityWithoutSetCurrent(new FormStatus(formId, "已查看", userId));
	}

	/**
	 * 查询采购单发货状态
	 * 
	 * @param formId
	 * @return
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 */
	public String deliveryStatus(String formId) throws NoPrivilegeException, SQLException, NoConnection {
		if (formStatusBean.hasStatus(formId, "已结案")) {
			return "已发货";
		}

		if (formStatusBean.hasStatus(formId, "已入库")) {
			return "部分发货";
		}

		return "未发货";
	}

	public void setPurchasingHeaderBean(PurchasingHeaderBean purchasingHeaderBean) {
		this.purchasingHeaderBean = purchasingHeaderBean;
	}

	public void setPurchasingDetailBean(PurchasingDetailBean purchasingDetailBean) {
		this.purchasingDetailBean = purchasingDetailBean;
	}

	public void setFormStatusBean(FormStatusBean formStatusBean) {
		this.formStatusBean = formStatusBean;
	}

	public void setStatementHeadBean(StatementHeadBean statementHeadBean) {
		this.statementHeadBean = statementHeadBean;
	}

	public void setStatementDetailBean(StatementDetailBean statementDetailBean) {
		this.statementDetailBean = statementDetailBean;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}

}
