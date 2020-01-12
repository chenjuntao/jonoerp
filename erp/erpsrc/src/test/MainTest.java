package test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.store.BranchBean;
import net.sf.json.JSONObject;
import pojo.store.Branch;
import pojo.store.BranchStorage;
import pojo.store.DailySettleRecord;
import service.restaurant.dailysettlement.DailySettlementService;
import test.Service.GoodsBillService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.enums.BranchTypeEnum;

public class MainTest extends BaseAction {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String numGoodsBill;
	private String numEGoodsBill;
	private GoodsBillService testGgoodsbillService;

	private Date businessDate;
	private DailySettlementService dailySettlementService;
	private DailySettleRecord settleRecord;
	private BranchBean branchBean;
	private String Id;
	private String branchId;
	private Date startDate;
	private Date endDate;
	private List<BranchStorage> storageList;
	private List<Map> shopLst;

	public String execute() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		branchId = getLoginBranchId();
		String branchType = "RESTAURANT,CENTRALFACTORY,LOGISTICSCENTER";
		endDate = startDate = branchBean.GetBranchById(getLoginBranchId()).getBusinessDate();
		// storageList = branchStorageBean.query(null, branchType);
		shopLst = branchBean.listShopByType(BranchTypeEnum.getEnum(branchType));
		return SUCCESS;
	}

	public void setId(String id) {
		Id = id;
	}

	public void doTestSettle() throws Exception {
		// String Id = "01";
		businessDate = branchBean.GetBranchById(Id).getBusinessDate();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("开始日结" + Id + " " + df.format(new Date()));
		settleRecord = new DailySettleRecord();
		settleRecord.setBranchId(Id);
		Branch branch = branchBean.GetBranchById(Id);
		settleRecord.setBranchName(branch.getBranchName());
		settleRecord.setOperatorId(getLoginUserId());
		settleRecord.setOperatorName(getLoginUsername());
		getSessionMap().put("downloadTokenValue", 0);
		dailySettlementService.SettleStorage(Id, businessDate, getSessionMap());
		dailySettlementService.SettleFoodAmt(Id, businessDate, getSessionMap());
		dailySettlementService.SettleRawAmt(Id, businessDate, getSessionMap());
		settleRecord.setSettleStatus("日结成功！");
		System.out.println("成功日结" + Id + " " + df.format(new Date()));
		dailySettlementService.SettleComplete(settleRecord, getSessionMap());
		String branchId = settleRecord.getBranchId();
		String branchName = settleRecord.getBranchName();

		String currentDateStr = DateTimeUtil.getDateStr(businessDate);
		String nextDateStr = DateTimeUtil.getDateStr(settleRecord.getBusinessDate());

		OperateLogUtil.record("[" + branchId + "]" + branchName + "进行日结的营业日期为" + currentDateStr + "，" + "日结后日期为"
				+ nextDateStr);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		getSessionMap().put("downloadTokenValue", 10);
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 格式化输出
	public static String formatJson(String jsonStr) {
		if (null == jsonStr || "".equals(jsonStr))
			return "";
		StringBuilder sb = new StringBuilder();
		char last = '\0';
		char current = '\0';
		int indent = 0;
		for (int i = 0; i < jsonStr.length(); i++) {
			last = current;
			current = jsonStr.charAt(i);
			switch (current) {
			case '{':
			case '[':
				sb.append(current);
				sb.append('\n');
				indent++;
				addIndentBlank(sb, indent);
				break;
			case '}':
			case ']':
				sb.append('\n');
				indent--;
				addIndentBlank(sb, indent);
				sb.append(current);
				break;
			case ',':
				sb.append(current);
				if (last != '\\') {
					sb.append('\n');
					addIndentBlank(sb, indent);
				}
				break;
			default:
				sb.append(current);
			}
		}

		return sb.toString();
	}

	private static void addIndentBlank(StringBuilder sb, int indent) {
		for (int i = 0; i < indent; i++) {
			sb.append('\t');
		}
	}

	public void doTestGoodsBill() throws Exception {
		int num = Integer.valueOf(numGoodsBill).intValue();
		int numE = Integer.valueOf(numEGoodsBill).intValue();
		testGgoodsbillService.doTestGoodsBill(num, numE);
	}

	public void doTestLoss() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
			SecurityException, NoSuchFieldException, NoPrivilegeException, SQLException, NoConnection {
		int num = Integer.valueOf(numGoodsBill).intValue();
		int numE = Integer.valueOf(numEGoodsBill).intValue();
		testGgoodsbillService.doTestLoss(num, numE);
	}

	public void doDeleteGoodsBill() throws IllegalArgumentException, IllegalAccessException, NoSuchMethodException,
			SecurityException, NoSuchFieldException, NoPrivilegeException, SQLException, NoConnection {
		testGgoodsbillService.doDeleteTestData("D_T1_REQUEST_DETAIL");
		testGgoodsbillService.doDeleteTestData("D_T1_REQUEST_HEADER");
	}

	public void setNumGoodsBill(String numGoodsBill) {
		this.numGoodsBill = numGoodsBill;
	}

	public void setNumEGoodsBill(String numEGoodsBill) {
		this.numEGoodsBill = numEGoodsBill;
	}

	public void setTestGgoodsbillService(GoodsBillService testGgoodsbillService) {
		this.testGgoodsbillService = testGgoodsbillService;
	}

	public void setBusinessDate(Date businessDate) {
		this.businessDate = businessDate;
	}

	public void setDailySettlementService(DailySettlementService dailySettlementService) {
		this.dailySettlementService = dailySettlementService;
	}

	public void setSettleRecord(DailySettleRecord settleRecord) {
		this.settleRecord = settleRecord;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}
	
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getStartDate() {
		return DateTimeUtil.getDateStr(startDate);
	}

	public String getEndDate() {
		return DateTimeUtil.getDateStr(endDate);
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public List<BranchStorage> getStorageList() {
		return storageList;
	}

	public void setStorageList(List<BranchStorage> storageList) {
		this.storageList = storageList;
	}

	public List<Map> getShopLst() {
		return shopLst;
	}

	public void setShopLst(List<Map> shopLst) {
		this.shopLst = shopLst;
	}

}
