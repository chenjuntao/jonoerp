/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 20, 2015 by czc
 * Last edited on Mar 20, 2015 by czc
 * 
 * 系统数据备份
 */

package action.setting;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import logic.NoConnection;
import logic.form.BillPayBean;
import logic.form.FoodBillBean;
import logic.form.FoodBillsBean;
import logic.store.BranchBean;
import logic.store.OptionBean;
import net.sf.json.JSONObject;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import service.reportmanage.ReportManageService;
import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SysUtil;
import com.tanry.framework.util.constant.LogType;

import dao.importdata.ImportFSDao;

public class BackupAction extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ReportManageService reportManageService;
	private BillPayBean billPayBean;
	private BranchBean branchBean;
	private FoodBillsBean foodBillsBean;
	private FoodBillBean foodBillBean;
	private ImportFSDao importFSDao;
	private OptionBean optionBean;
	// private GroupBean groupBean;

	private String month;// 月份
	private boolean stuta;// 是否删除数据标记

	private String branchId;// 门店

	private int flag;
	private String branchIds;
	private String bnDate;
	private String downloadTokenValue;

	private String branchType;

	public final static char CR = (char) 0x0D;
	public final static char LF = (char) 0x0A;

	public final static String CRLF = "" + CR + LF; // "" forces conversion to

	public static void main(String[] args) {

		String twoLines = "中文1" + CRLF + "中文2"; // 12 characters
		System.out.println(twoLines);
		// System.out.println(twoLines.replaceAll(CRLF, ""));
	}

	public String execute() throws NoPrivilegeException, SQLException, NoConnection {
		Date Date = reportManageService.getBusinessDate("RESTAURANT");
		bnDate = DateTimeUtil.getDateTime(Date, "yyyy-MM");
		return SUCCESS;
	}

	/**
	 * 
	 * 分别导出基本，系统，业务表为csv文件
	 * 
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 * @throws IOException
	 */
	public void exportCsv() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		stuta = false;
		int tableCount = 0;
		int j = 0;
		JSONObject result = new JSONObject();
		String basePath = SysUtil.getExportCsvDir();
		List<String> sidLst = new ArrayList<String>();
		if (month == null) {
			month = "branch";
		}
		String monthPath = basePath + File.separator + month;
		String timeStr = DateTimeUtil.getDateTime("MMdd_HHmmss");
		String timePath = basePath + File.separator + month + File.separator + timeStr;
		boolean tag = true;
		String fileName = "";

		File timeDir = new File(timePath);
		if (!timeDir.exists()) {
			timeDir.mkdirs();
		}

		if (flag == 1) {
			fileName = "基本信息.zip";
			String bh = "";
			String[] branchs = BuildMap.branchs;
			tableCount = branchs.length;
			for (int i = 0; i < tableCount; i++) {
				String tbName = branchs[i];
				File bpFile = new File(timePath + File.separator + tbName + ".txt");
				bh = importFSDao.readItem(tbName, false);

				try {
					FileWriterWithEncoding out = new FileWriterWithEncoding(bpFile, Charset.forName("UTF-8")
							.newEncoder());
					out.write(bh);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
				}
			}
			sidLst.add("备份基本信息");
		} else if (flag == 2) {
			fileName = "系统信息.zip";

			String bh = "";
			String[] syses = BuildMap.syses;
			tableCount = syses.length;
			for (int i = 0; i < tableCount; i++) {
				String tbName = syses[i];
				File bpFile = new File(timePath + File.separator + tbName + ".txt");
				bh = importFSDao.readItem(tbName, false);
				try {
					FileWriterWithEncoding out = new FileWriterWithEncoding(bpFile, Charset.forName("UTF-8")
							.newEncoder());
					out.write(bh);
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
				}
			}
			sidLst.add("备份系统信息");
		} else if (flag == 3) {
			fileName = month + "单据信息.zip";
			Date yearMonthDate = DateTimeUtil.parse(month, "yyyy-MM");
			String startDate = getDateStr(DateTimeUtil.getMonthFristDay(yearMonthDate));
			String endDate = getDateStr(DateTimeUtil.getMonthLastDay(yearMonthDate));
			String bh = "";

			List<Map<String, Map<String, Object>>> tableList = BuildMap.buildMap();
			String[] headers = BuildMap.headers;
			try {
				tableCount = headers.length;
				for (int i = 0; i < tableCount; i++) {
					String tbName = headers[i];

					for (Map<String, Map<String, Object>> map : tableList) {
						Map<String, Object> value = map.get(tbName);
						if (value == null) {

							if (importFSDao.countTable(tbName, null) == 0) {
								j++;
							} else {
								bh = importFSDao.readItem(tbName, stuta);
								File bhFile = new File(timePath + File.separator + month + "_" + tbName + ".txt");
								FileWriterWithEncoding out = new FileWriterWithEncoding(bhFile, Charset
										.forName("UTF-8").newEncoder());
								out.write(bh);
								out.close();
								result.put("msg", 2);
							}
						} else {
							if (importFSDao.countTable(tbName, value, startDate, endDate) == 0) {
								j++;
							} else {
								bh = importFSDao.readItem(tbName, value, startDate, endDate, stuta);
								File bhFile = new File(timePath + File.separator + month + "_" + tbName + ".txt");

								FileWriterWithEncoding out = new FileWriterWithEncoding(bhFile, Charset
										.forName("UTF-8").newEncoder());
								out.write(bh);
								out.close();
								result.put("msg", 2);
							}

						}
					}
				}
			} catch (Exception e) {
				result.put("msg", 3);
				e.printStackTrace();

			} finally {
			}
			if (stuta) {
				sidLst.add("备份/删除" + month + "单据信息");
			} else {
				sidLst.add("备份" + month + "单据信息");
			}
		} else {
			fileName = month + "业务信息.zip";
			Date yearMonthDate = DateTimeUtil.parse(month, "yyyy-MM");
			String startDate = getDateStr(DateTimeUtil.getMonthFristDay(yearMonthDate));
			String endDate = getDateStr(DateTimeUtil.getMonthLastDay(yearMonthDate));
			String[] branchArr = branchIds.split(",");
			String branchName;
			String branchNames = "";
			String beanchFall = "";
			int z = 0;
			for (String branchId : branchArr) {
				branchName = branchBean.GetBranchById(branchId).getBranchName();
				branchNames += branchName + ",";
				String bh = "";
				String[] businesses = BuildMap.businesses;
				List<Map<String, Map<String, Object>>> tableList = BuildMap.buildBusiness();
				tableCount = businesses.length;
				for (int i = 0; i < tableCount; i++) {
					String tbName = businesses[i];
					for (Map<String, Map<String, Object>> map : tableList) {
						File bpFile = new File(timePath + File.separator + branchId + "_" + month + "_" + tbName
								+ ".txt");
						Map<String, Object> value = map.get(tbName);
						bh = importFSDao.readItem(tbName, value, startDate, endDate, stuta, branchId);
						try {
							FileWriterWithEncoding out = new FileWriterWithEncoding(bpFile, Charset.forName("UTF-8")
									.newEncoder());
							out.write(bh);
							out.close();
						} catch (IOException e) {
							beanchFall += branchName + ",";
							e.printStackTrace();
						} finally {
						}
					}
				}
				z++;
			}
			if (z < tableCount) {
				result.put("msg", 1);
				sidLst.add("备份" + beanchFall + month + "业务信息失败！");
			} else {
				result.put("msg", 2);
				sidLst.add("备份" + branchNames + month + "业务信息成功！");
			}
		}

		if (tag && j != tableCount) {
			String zipFilePath = monthPath + File.separator + "lp" + timeStr + ".zip";
			FileToZip.zipDIR(timePath, zipFilePath);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.reset();
			response.setCharacterEncoding("GBK");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("application/zip");

			response.setHeader("Location", fileName);
			// fileName应该是编码后的(utf-8)

			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String((fileName).getBytes("UTF-8"), "iso8859-1"));

			response.addCookie(new Cookie("fileDownloadToken", downloadTokenValue));

			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream(zipFilePath);

			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}

			outputStream.flush();
			outputStream.close();
			inputStream.close();
			outputStream = null;
			OperateLogUtil.record("", LogType.EXPORT, StringUtils.join(sidLst, ","));

		} else {
			result.put("msg", 1);

			try {
				this.outHTML(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		DeleteFolder.deleteDirectory(timePath);// 删除临时文件

		if (downloadTokenValue != null) {
			getSessionMap().put("downloadTokenValue", downloadTokenValue);
		}
	}

	/**
	 * 
	 * 分别导出基本，系统，业务表为csv文件
	 * 
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 * @throws IOException
	 */
	public void exportSys() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		stuta = false;
		int tableCount = 0;
		int j = 0;
		JSONObject result = new JSONObject();
		String basePath = SysUtil.getExportCsvDir();
		List<String> sidLst = new ArrayList<String>();
		if (month == null) {
			month = "branch";
		}
		String monthPath = basePath + File.separator + month;
		String timeStr = DateTimeUtil.getDateTime("MMdd_HHmmss");
		String timePath = basePath + File.separator + month + File.separator + timeStr;
		boolean tag = true;
		String fileName = "";

		File timeDir = new File(timePath);
		if (!timeDir.exists()) {
			timeDir.mkdirs();
		}
		String[] branchs = {};
		String bh = "";
		if (flag == 1) {
			fileName = "基本物料信息.zip";
			branchs = BuildMap.raws;
			sidLst.add("导出基本物料信息");
		} else if (flag == 2) {
			fileName = "半成品信息.zip";
			branchs = BuildMap.semises;
			sidLst.add("导出半成品信息");
		}
		tableCount = branchs.length;
		Map<String, String> fileNames = BuildMap.fileNames();
		for (int i = 0; i < tableCount; i++) {
			String tbName = branchs[i];
			File bpFile = new File(timePath + File.separator + fileNames.get(tbName) + ".txt");
			bh = importFSDao.readItem(tbName, false);

			try {
				FileWriterWithEncoding out = new FileWriterWithEncoding(bpFile, Charset.forName("UTF-8").newEncoder());
				out.write(bh);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}

		if (tag && j != tableCount) {
			String zipFilePath = monthPath + File.separator + "lp" + timeStr + ".zip";
			FileToZip.zipDIR(timePath, zipFilePath);
			HttpServletResponse response = ServletActionContext.getResponse();
			response.reset();
			response.setCharacterEncoding("GBK");
			response.setHeader("Cache-Control", "no-cache");
			response.setContentType("application/zip");

			response.setHeader("Location", fileName);
			// fileName应该是编码后的(utf-8)

			response.setHeader("Content-Disposition", "attachment; filename="
					+ new String((fileName).getBytes("UTF-8"), "iso8859-1"));

			response.addCookie(new Cookie("fileDownloadToken", downloadTokenValue));

			OutputStream outputStream = response.getOutputStream();
			InputStream inputStream = new FileInputStream(zipFilePath);

			byte[] buffer = new byte[1024];
			int i = -1;
			while ((i = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, i);
			}

			outputStream.flush();
			outputStream.close();
			inputStream.close();
			outputStream = null;
			OperateLogUtil.record("", LogType.EXPORT, StringUtils.join(sidLst, ","));

		} else {
			result.put("msg", 1);

			try {
				this.outHTML(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		DeleteFolder.deleteDirectory(timePath);// 删除临时文件

		if (downloadTokenValue != null) {
			getSessionMap().put("downloadTokenValue", downloadTokenValue);
		}
	}

	public void clearFoods() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		JSONObject result = new JSONObject();
		List<String> sidLst = new ArrayList<String>();
		Date yearMonthDate = DateTimeUtil.parse(month, "yyyy-MM");
		Date startDate = DateTimeUtil.getMonthFristDay(yearMonthDate);
		Date endDate = DateTimeUtil.getMonthLastDay(yearMonthDate);
		// startDate = DateTimeUtil.parse("2014-05-01");
		// endDate = DateTimeUtil.parse("2014-05-01");
		String[] branchArr = branchIds.split(",");
		String branchNames = "";
		String beanchFall = "";
		int i = 0;
		for (String branchId : branchArr) {
			String branchName = branchBean.GetBranchById(branchId).getBranchName();
			try {
				billPayBean.deleteEntity(startDate, endDate, branchId);
				foodBillsBean.deleteEntity(startDate, endDate, branchId);
				foodBillBean.deleteEntity(startDate, endDate, branchId);
				branchNames += branchName + ",";

			} catch (Exception e) {
				beanchFall += branchName + ",";
			}
			i++;
		}
		if (i < branchArr.length) {
			result.put("msg", 1);
			sidLst.add("删除" + beanchFall + month + "业务信息失败！");
		} else {
			result.put("msg", 2);
			sidLst.add("删除" + branchNames + month + "业务信息成功！");
		}
		result.put("sidLst", sidLst);
		OperateLogUtil.record("", LogType.DELETE, StringUtils.join(sidLst, ","));

		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void clearForms() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		JSONObject result = new JSONObject();
		List<String> sidLst = new ArrayList<String>();
		Date yearMonthDate = DateTimeUtil.parse(month, "yyyy-MM");
		String startDate = getDateStr(DateTimeUtil.getMonthFristDay(yearMonthDate));
		String endDate = getDateStr(DateTimeUtil.getMonthLastDay(yearMonthDate));
		List<Map<String, Map<String, Object>>> tableList = BuildMap.buildMap();
		String[] headers = BuildMap.headers;
		try {
			for (int i = 0; i < headers.length; i++) {
				String tbName = headers[i];
				for (Map<String, Map<String, Object>> map : tableList) {
					Map<String, Object> value = map.get(tbName);
					if (value == null) {
						importFSDao.clearItem(tbName);
					} else {
						importFSDao.clearItem(tbName, value, startDate, endDate);
					}
				}
			}
			result.put("msg", 1);
		} catch (Exception e) {
			result.put("msg", 3);
			e.printStackTrace();
		} finally {
		}
		sidLst.add("清理" + month + "单据信息");
		result.put("sidLst", sidLst);
		OperateLogUtil.record("", LogType.DELETE, StringUtils.join(sidLst, ","));
		try {
			this.outHTML(result.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 定时备份
	 * 
	 * @throws NoConnection
	 * @throws SQLException
	 * @throws NoPrivilegeException
	 * @throws IOException
	 */
	public void timerBackup() throws NoPrivilegeException, SQLException, NoConnection, IOException {

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 11); // 控制时
		calendar.set(Calendar.MINUTE, 0); // 控制分
		calendar.set(Calendar.SECOND, 0); // 控制秒

		Date time = calendar.getTime(); // 得出执行任务的时间,此处为今天的3：00：00

		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				System.out.println("-------设定要指定任务开始--------");
				try {
					importFSDao.backupsBranch(1);// 基本信息备份
					importFSDao.backupsBranch(2);// 系统信息备份

					importFSDao.backupsForm(1);// 业务信息备份
					importFSDao.backupsForm(2);// 营业信息备份
				} catch (NoPrivilegeException | SQLException | NoConnection | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("-------设定要指定任务结束--------");
			}
		}, time, 1000 * 60 * 60);// 这里设定将延时每天固定执行
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

	public void setBillPayBean(BillPayBean billPayBean) {
		this.billPayBean = billPayBean;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getMonth() {
		return month;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setFoodBillsBean(FoodBillsBean foodBillsBean) {
		this.foodBillsBean = foodBillsBean;
	}

	public boolean isStuta() {
		return stuta;
	}

	public void setStuta(boolean stuta) {
		this.stuta = stuta;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public void setFoodBillBean(FoodBillBean foodBillBean) {
		this.foodBillBean = foodBillBean;
	}

	public void setIoData(String ioData) {
	}

	public void setBranchIds(String branchIds) {
		this.branchIds = branchIds;
	}

	public String getBnDate() {
		return bnDate;
	}

	public void setBnDate(String bnDate) {
		this.bnDate = bnDate;
	}

	public void setReportManageService(ReportManageService reportManageService) {
		this.reportManageService = reportManageService;
	}

	public void setBranchType(String branchType) {
		this.branchType = branchType;
	}

	public String getBranchType() {
		return branchType;
	}

	public ImportFSDao getImportFSDao() {
		return importFSDao;
	}

	public void setImportFSDao(ImportFSDao importFSDao) {
		this.importFSDao = importFSDao;
	}

	public String getDownloadTokenValue() {
		return downloadTokenValue;
	}

	public void setDownloadTokenValue(String downloadTokenValue) {
		this.downloadTokenValue = downloadTokenValue;
	}

}