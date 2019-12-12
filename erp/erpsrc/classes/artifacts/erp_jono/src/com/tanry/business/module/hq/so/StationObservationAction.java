package com.tanry.business.module.hq.so;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import logic.NoConnection;
import logic.form.ObservationDetailBean;
import logic.store.ItemMetaBean;
import logic.store.TherapyBean;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import pojo.form.ObservationDetail;
import pojo.form.ObservationHeader;
import pojo.store.ItemMeta;
import pojo.store.Therapy;
import action.common.BaseAction;

import com.tanry.business.module.hq.so.service.StationObservationService;
import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.NumberToCN;
import com.tanry.framework.util.TextUtil;

public class StationObservationAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private String jsonData;
	private String itemId;

	private ItemMetaBean itemMetaBean;
	private ItemMeta itemMeta;

	private TherapyBean therapyBean;

	private Therapy therapy;

	private StationObservationService stationObservationService;
	private File attachment;

	private ObservationDetail observationDetail;
	private List<Therapy> itemLst;
	private String partId;
	private String processId;
	private String processName;
	private Integer step;
	private String leftTitle;
	private String aPartContent;
	private String aPartTitle;
	private String costPartContent;
	private String bPartTitle;
	private String bPartContent;
	private String cPartTitle;
	private String cPartContent;
	private String dPartTitle;
	private String dPartContent;
	private List<ObservationDetail> aTable;
	private List<ObservationDetail> bTable;
	private List<ObservationDetail> cTable;

	private ObservationDetailBean observationDetailBean;

	@Override
	public String execute() throws Exception {
		itemMeta = itemMetaBean.GetItemById(itemId);
		List<Therapy> therapyInfoLists = therapyBean.GetTherapysById(itemId);
		for (Therapy t : therapyInfoLists) {
			therapy = t;
			if (!TextUtil.isEmpty(therapy.getTherapyId())) {
				break;
			}
		}

		return SUCCESS;
	}

	public void loadImage() throws Exception {
		byte[] picData = observationDetailBean.getPicData(itemId, processId, step);
		if (picData != null) {
			String fileName = "pic" + System.currentTimeMillis();
			this.getResponse().reset();
			this.getResponse().setContentType("image/jpeg");
			this.getResponse().setHeader("Content-disposition", "inline; filename=" + fileName);
			OutputStream outFile = getResponse().getOutputStream();
			outFile.write(picData);
			outFile.flush();
			outFile.close();
		} else {
			outBlank("不存在");
		}
	}

	/**
	 * 上传示范图片和示范说明
	 */
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

		observationDetail.setStepPicBlob(bos.toByteArray());
		observationDetail.setItemId(itemId);
		observationDetail.setPartId(partId);
		observationDetail.setProcessId(processId);
		observationDetail.setStepRemark(observationDetail.getStepRemark().trim());
		observationDetail.setProcessedName(processName);
		stationObservationService.uploadPhoto(observationDetail);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新示范图片和示范说明
	 */
	public void doUploadEdit() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		if (attachment != null) {
			InputStream fis = new FileInputStream(attachment);
			ByteArrayOutputStream bos = new ByteArrayOutputStream(1000);
			byte[] b = new byte[1000];
			int n;
			while ((n = fis.read(b)) != -1) {
				bos.write(b, 0, n);
			}
			fis.close();
			bos.close();

			observationDetail.setStepPicBlob(bos.toByteArray());
		}

		observationDetail.setItemId(itemId);
		observationDetail.setPartId(partId);
		observationDetail.setProcessId(processId);
		observationDetail.setStepRemark(observationDetail.getStepRemark().trim());
		observationDetail.setProcessedName(processName);
		stationObservationService.uploadEditPhoto(observationDetail);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doUploadDelete() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		observationDetail.setItemId(itemId);
		observationDetail.setPartId(partId);
		observationDetail.setProcessId(processId);

		stationObservationService.uploadDeletePhoto(observationDetail);
		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String edit() throws NoPrivilegeException, SQLException, NoConnection {
		itemMeta = itemMetaBean.GetItemById(itemId);
		List<Therapy> therapyInfoLists = therapyBean.GetTherapysById(itemId);
		for (Therapy t : therapyInfoLists) {
			therapy = t;
			if (!TextUtil.isEmpty(therapy.getTherapyId())) {
				break;
			}
		}

		return SUCCESS;
	}

	public String uploadView() throws NoPrivilegeException, SQLException, NoConnection {
		if ("1".equals(processId)) {
			processName = "初加工";
		} else if ("2".equals(processId)) {
			processName = "深加工";
		} else {
			processName = "见单制作";
		}

		return SUCCESS;
	}

	public String uploadEditView() throws NoPrivilegeException, SQLException, NoConnection {
		observationDetail = observationDetailBean.queryStep(itemId, processId, step);

		return SUCCESS;
	}

	public String doStartTemplate() throws NoPrivilegeException, SQLException, NoConnection {
		return SUCCESS;
	}

	public String doExport() throws NoPrivilegeException, SQLException, NoConnection {
		itemMeta = itemMetaBean.GetItemById(itemId);
		List<ObservationHeader> oList = stationObservationService.loadData(itemId);
		itemLst = therapyBean.GetTherapysById(itemId, hasSum);
		List<ObservationDetail> poLists = observationDetailBean.query(itemId);

		ObservationHeader item = new ObservationHeader();
		for (int i = 0; i < oList.size(); i++) {
			item = oList.get(i);
			switch (item.getPartId()) {
			case "leftTitle":
				leftTitle = item.getPartRemark();
				break;

			case "aPartTitle":
				aPartTitle = item.getPartRemark();
				break;

			case "aPartContent":
				aPartContent = item.getPartRemark();
				break;

			case "costPartContent":
				costPartContent = item.getPartRemark();
				break;

			case "bPartTitle":
				bPartTitle = item.getPartRemark();
				break;

			case "bPartContent":
				bPartContent = item.getPartRemark();
				break;

			case "cPartTitle":
				cPartTitle = item.getPartRemark();
				break;

			case "cPartContent":
				cPartContent = item.getPartRemark();
				break;

			case "dPartTitle":
				dPartTitle = item.getPartRemark();
				break;

			case "dPartContent":
				dPartContent = item.getPartRemark();
				break;

			default:
				break;
			}
		}

		aTable = new ArrayList<ObservationDetail>();
		bTable = new ArrayList<ObservationDetail>();
		cTable = new ArrayList<ObservationDetail>();

		for (ObservationDetail po : poLists) {
			if (po.getProcessId().equals("1")) {
				aTable.add(po);
			} else if (po.getProcessId().equals("2")) {
				bTable.add(po);
			} else if (po.getProcessId().equals("3")) {
				cTable.add(po);
			}

		}

		return SUCCESS;
	}

	public String scan() throws NoPrivilegeException, SQLException, NoConnection {
		itemMeta = itemMetaBean.GetItemById(itemId);
		return SUCCESS;
	}

	public void queryCostInfo() throws NoPrivilegeException, SQLException, NoConnection {
		List<Therapy> therapyInfoLists = therapyBean.GetTherapysById(itemId);
		JSONArray jsonArray = new JSONArray();
		for (Therapy therapy : therapyInfoLists) {
			JSONObject jsonObject = JSONObject.fromObject(therapy);
			jsonObject.put("itemCount", therapy.getItemCount() + therapy.getItemDimension());
			jsonArray.add(jsonObject);
		}

		try {
			this.outJS(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void getData() throws NoPrivilegeException, SQLException, NoConnection {
		List<ObservationDetail> poLists = observationDetailBean.query(itemId);
		JSONObject jsonObject = new JSONObject();

		JSONArray jsonArray1 = new JSONArray();
		JSONArray jsonArray2 = new JSONArray();
		JSONArray jsonArray3 = new JSONArray();

		for (ObservationDetail po : poLists) {
			JSONObject temp = JSONObject.fromObject(po);
			temp.put("stepName", "第" + NumberToCN.number2CNMontrayUnit(new BigDecimal(po.getStep())) + "步");
			if (po.getProcessId().equals("1")) {
				jsonArray1.add(temp);
			} else if (po.getProcessId().equals("2")) {
				jsonArray2.add(temp);
			} else if (po.getProcessId().equals("3")) {
				jsonArray3.add(temp);
			}

		}

		jsonObject.put("a", jsonArray1);
		jsonObject.put("b", jsonArray2);
		jsonObject.put("c", jsonArray3);

		try {
			this.outJS(jsonObject.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSave() throws NoPrivilegeException, SQLException, NoConnection {
		stationObservationService.saveData(jsonData, itemId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSaveTemplate() throws NoPrivilegeException, SQLException, NoConnection {
		stationObservationService.saveTemplate(jsonData);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doSaveData() throws NoPrivilegeException, SQLException, NoConnection {
		stationObservationService.saveData(jsonData, itemId);

		JSONObject result = new JSONObject();
		result.put("msg", "ok");
		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doLoadTemplate() throws NoPrivilegeException, SQLException, NoConnection {
		List<ObservationHeader> oList = stationObservationService.loadTemplate();
		JSONArray result = JSONArray.fromObject(oList);

		for (ObservationHeader item : oList) {
			JSONObject jsonObject = JSONObject.fromObject(item);
			result.add(jsonObject);
		}

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void doLoadData() throws NoPrivilegeException, SQLException, NoConnection {
		List<ObservationHeader> oList = stationObservationService.loadData(itemId);
		JSONArray result = JSONArray.fromObject(oList);

		for (ObservationHeader item : oList) {
			JSONObject jsonObject = JSONObject.fromObject(item);
			result.add(jsonObject);
		}

		try {
			this.outJS(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public void setItemMetaBean(ItemMetaBean itemMetaBean) {
		this.itemMetaBean = itemMetaBean;
	}

	public ItemMeta getItemMeta() {
		return itemMeta;
	}

	public void setItemMeta(ItemMeta itemMeta) {
		this.itemMeta = itemMeta;
	}

	public void setTherapyBean(TherapyBean therapyBean) {
		this.therapyBean = therapyBean;
	}

	public Therapy getTherapy() {
		return therapy;
	}

	public void setStationObservationService(StationObservationService stationObservationService) {
		this.stationObservationService = stationObservationService;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public void setObservationDetail(ObservationDetail observationDetail) {
		this.observationDetail = observationDetail;
	}

	public void setPartId(String partId) {
		this.partId = partId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setObservationDetailBean(ObservationDetailBean observationDetailBean) {
		this.observationDetailBean = observationDetailBean;
	}

	public ObservationDetail getObservationDetail() {
		return observationDetail;
	}

	public void setStep(Integer step) {
		this.step = step;
	}

	public String getProcessName() {
		return processName;
	}

	public void setProcessName(String processName) {
		this.processName = processName;
	}

	public String getLeftTitle() {
		return leftTitle;
	}

	public void setLeftTitle(String leftTitle) {
		this.leftTitle = leftTitle;
	}

	public String getAPartContent() {
		return aPartContent;
	}

	public void setAPartContent(String aPartContent) {
		this.aPartContent = aPartContent;
	}

	public String getAPartTitle() {
		return aPartTitle;
	}

	public void setAPartTitle(String aPartTitle) {
		this.aPartTitle = aPartTitle;
	}

	public String getCostPartContent() {
		return costPartContent;
	}

	public void setCostPartContent(String costPartContent) {
		this.costPartContent = costPartContent;
	}

	public String getBPartTitle() {
		return bPartTitle;
	}

	public void setBPartTitle(String bPartTitle) {
		this.bPartTitle = bPartTitle;
	}

	public String getBPartContent() {
		return bPartContent;
	}

	public void setBPartContent(String bPartContent) {
		this.bPartContent = bPartContent;
	}

	public String getCPartTitle() {
		return cPartTitle;
	}

	public void setCPartTitle(String cPartTitle) {
		this.cPartTitle = cPartTitle;
	}

	public String getCPartContent() {
		return cPartContent;
	}

	public void setCPartContent(String cPartContent) {
		this.cPartContent = cPartContent;
	}

	public String getDPartTitle() {
		return dPartTitle;
	}

	public void setDPartTitle(String dPartTitle) {
		this.dPartTitle = dPartTitle;
	}

	public String getDPartContent() {
		return dPartContent;
	}

	public void setDPartContent(String dPartContent) {
		this.dPartContent = dPartContent;
	}

	public List<Therapy> getItemLst() {
		return itemLst;
	}

	public void setItemLst(List<Therapy> itemLst) {
		this.itemLst = itemLst;
	}

	public List<ObservationDetail> getATable() {
		return aTable;
	}

	public void setATable(List<ObservationDetail> aTable) {
		this.aTable = aTable;
	}

	public List<ObservationDetail> getBTable() {
		return bTable;
	}

	public void setBTable(List<ObservationDetail> bTable) {
		this.bTable = bTable;
	}

	public List<ObservationDetail> getCTable() {
		return cTable;
	}

	public void setCTable(List<ObservationDetail> cTable) {
		this.cTable = cTable;
	}

}
