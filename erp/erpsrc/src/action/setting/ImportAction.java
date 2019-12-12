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
 * 系统数据还原
 */

package action.setting;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import logic.NoConnection;
import logic.form.FoodBillsBean;
import logic.store.BranchBean;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;

import action.common.BaseAction;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.OperateLogUtil;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.constant.LogType;

import dao.importdata.ImportFSDao;

public class ImportAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	/** 上传的文件 */
	private File attachment;

	private ImportFSDao importFSDao;
	static final int BUFFER = 2048;
	private FoodBillsBean foodBillsBean;
	private BranchBean branchBean;
	private ZipFile zf;
	private int stuta;

	/**
	 * 解析导入文件
	 * 
	 * @throws IOException
	 */
	public void doImport() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		int flag = 1;
		int i = 0;
		Map<String, Object> bName = new HashMap<String, Object>();
		List<String> sidLst = new ArrayList<String>();
		JSONObject result = new JSONObject();
		Map<String, String> fileNames = BuildMap.fileNames();
		if (attachment.exists()) {
			// BufferedInputStream bi;
			zf = new ZipFile(attachment, "GBK");// 支持中文
			Enumeration ze = zf.getEntries();
			ZipEntry zefs;
			String yearName = "branch";
			String branchNames = "";
			while (ze.hasMoreElements()) {
				zefs = (ZipEntry) ze.nextElement();
				if (zefs.isDirectory()) {
					response.getWriter().print(
							"<script language=\"javascript\">alert('导入失败！未找到文件');window.close();</script>");
					response.getWriter().close();
				} else {
					String fileName = zefs.getName();
					yearName = fileName.substring(3, 10);
					Date yearMonthDate = DateTimeUtil.parse(yearName, "yyyy-MM");
					String startDate = getDateStr(DateTimeUtil.getMonthFristDay(yearMonthDate));
					String endDate = getDateStr(DateTimeUtil.getMonthLastDay(yearMonthDate));
					String tbName = trimExtension(11, fileName);
					String branchId = fileName.substring(0, 2);
					String branchName = branchBean.GetBranchById(branchId).getBranchName();

					int count;

					List<Map<String, Map<String, Object>>> tableList = BuildMap.buildBusiness();
					for (Map<String, Map<String, Object>> map : tableList) {
						if (map.containsKey(tbName)) {// 判别文件是否导入正确

							Map<String, Object> value = map.get(tbName);
							// count = importFSDao.countTable(tbName, value,
							// startDate, endDate, branchId);
							count = 0;
							if (count == 0) {
								BufferedReader brfs = new BufferedReader(new InputStreamReader(zf.getInputStream(zefs),
										"UTF-8"));
								flag = importFSDao.inBueness(tbName, brfs);

								brfs.close();
								bName.put(branchName, "导入成功");
								i++;
							} else {
								// bName.put(branchName, "数据已存在");
							}
						} else {
							result.put("msg", 3);// 文件导入错误
						}
					}

				}
			}

			zf.close();
			Set<String> s = bName.keySet();
			Iterator<String> iter = (Iterator<String>) s.iterator();
			while (iter.hasNext()) {
				branchNames += iter.next() + ",";
			}
			if (i == 0) {
				result.put("msg", i);
				result.put("succes", "数据已存在！");
			} else {
				result.put("msg", flag);
				result.put("succes", "还原" + branchNames + yearName + "业务信息成功！");
				OperateLogUtil.record("", LogType.IMPORT, "还原" + branchNames + yearName + "业务信息成功！");
			}
			try {
				this.outHTML(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 导入branch.zip
	 * 
	 * @throws NoPrivilegeException
	 * @throws SQLException
	 * @throws NoConnection
	 * @throws IOException
	 */
	public void doInBranch() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		int flag = 1;
		int i = 0;
		List<String> sidLst = new ArrayList<String>();
		JSONObject result = new JSONObject();
		Map<String, String> fileNames = BuildMap.fileNames();
		if (attachment.exists()) {
			// BufferedInputStream bi;
			zf = new ZipFile(attachment, "GBK");// 支持中文
			Enumeration ze = zf.getEntries();
			ZipEntry zefs;
			String yearName = "branch";
			while (ze.hasMoreElements()) {
				zefs = (ZipEntry) ze.nextElement();

				if (zefs.isDirectory()) {
					response.getWriter().print(
							"<script language=\"javascript\">alert('导入失败！未找到文件');window.close();</script>");
					response.getWriter().close();
				} else {
					if (stuta == 1) {// 基本信息
						// importFSDao.backupsBranch(stuta);// 先备份，后删除
						String fileName = zefs.getName();
						String tbName = trimExtension(0, fileName);
						int count;

						List<Map<String, Map<String, Object>>> tableList = BuildMap.buildBranch();
						for (Map<String, Map<String, Object>> map : tableList) {
							Map<String, Object> value = map.get(tbName);
							importFSDao.clearItem(tbName);
							// count = importFSDao.countTable(tbName, value);
							count = 0;
							if (count == 0) {
								BufferedReader brfs = new BufferedReader(new InputStreamReader(zf.getInputStream(zefs),
										"UTF-8"));
								flag = importFSDao.inBueness(tbName, brfs);
								i++;
							} else {
								result.put("msg", flag);
								// sidLst.add(tbName + "表数据已存在！");
							}
						}

					} else if (stuta == 2) {// 系统信息
						importFSDao.backupsBranch(stuta);// 先备份，后删除
						String fileName = zefs.getName();
						String tbName = trimExtension(0, fileName);
						int count;

						List<Map<String, Map<String, Object>>> tableList = BuildMap.buildSys();
						for (Map<String, Map<String, Object>> map : tableList) {
							Map<String, Object> value = map.get(tbName);
							// count = importFSDao.countTable(tbName, value);
							importFSDao.clearItem(tbName);
							count = 0;
							if (count == 0) {
								BufferedReader brfs = new BufferedReader(new InputStreamReader(zf.getInputStream(zefs),
										"UTF-8"));
								flag = importFSDao.inBueness(tbName, brfs);
								i++;
							} else {
								result.put("msg", flag);
								sidLst.add(tbName + "表数据已存在！");
							}
						}

					} else {// 单据还原
						String fileName = zefs.getName();
						yearName = fileName.substring(0, 7);
						Date yearMonthDate = DateTimeUtil.parse(yearName, "yyyy-MM");
						String startDate = getDateStr(DateTimeUtil.getMonthFristDay(yearMonthDate));
						String endDate = getDateStr(DateTimeUtil.getMonthLastDay(yearMonthDate));
						String tbName = trimExtension(8, fileName);

						int count;

						List<Map<String, Map<String, Object>>> tableList = BuildMap.buildMap();
						for (Map<String, Map<String, Object>> map : tableList) {
							Map<String, Object> value = map.get(tbName);
							// count = importFSDao.countTable(tbName, value,
							// startDate, endDate);
							count = 0;
							if (count == 0) {
								BufferedReader brfs = new BufferedReader(new InputStreamReader(zf.getInputStream(zefs),
										"UTF-8"));
								flag = importFSDao.inBueness(tbName, brfs);
								i++;
							} else {
								result.put("msg", flag);
								// sidLst.add(tbName + "表数据已存在！");
							}
						}
					}
				}

			}
			if (stuta == 1) {
				sidLst.add("还原基本信息");
			} else if (stuta == 2) {
				sidLst.add("还原系统信息");
			} else {
				sidLst.add("还原" + yearName + "单据信息");
			}
			zf.close();
			OperateLogUtil.record("", LogType.IMPORT, StringUtils.join(sidLst, ","));
			if (i == 0) {
				result.put("msg", i);
			} else {
				result.put("msg", flag);
			}
			try {
				this.outHTML(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void doInSystem() throws NoPrivilegeException, SQLException, NoConnection, IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		int flag = 1;
		int i = 0;
		List<String> sidLst = new ArrayList<String>();
		JSONObject result = new JSONObject();
		Map<String, String> fileNames = BuildMap.fileNames();
		if (attachment.exists()) {
			// BufferedInputStream bi;
			zf = new ZipFile(attachment, "GBK");// 支持中文
			Enumeration ze = zf.getEntries();
			ZipEntry zefs;
			while (ze.hasMoreElements()) {
				zefs = (ZipEntry) ze.nextElement();
				if (zefs.isDirectory()) {
					response.getWriter().print(
							"<script language=\"javascript\">alert('导入失败！未找到文件');window.close();</script>");
					response.getWriter().close();
				} else {
					String fileName = trimExtension(0, zefs.getName());

					String tbName = getTableName(fileNames, fileName);
					BufferedReader brfs = new BufferedReader(new InputStreamReader(zf.getInputStream(zefs), "GBK"));
					flag = importFSDao.inTable(tbName, brfs);
					i++;

				}
			}
			if (stuta == 1) {
				sidLst.add("导入基本信息");
			} else if (stuta == 2) {
				sidLst.add("导入系统信息");
			} else {
				sidLst.add("导入单据信息");
			}
			zf.close();
			OperateLogUtil.record("", LogType.IMPORT, StringUtils.join(sidLst, ","));
			if (i == 0) {
				result.put("msg", i);
			} else {
				result.put("msg", flag);
			}
			try {
				this.outHTML(result.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static String trimExtension(int j, String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length()))) {
				return filename.substring(j, i);
			}
		}
		return filename;
	}

	public static String getTableName(Map<String, String> map, String value) {
		Set set = map.entrySet();
		Iterator iterator = set.iterator();
		String tableName = "";
		while (iterator.hasNext()) {
			Map.Entry<String, String> enter = (Entry<String, String>) iterator.next();
			if (enter.getValue().equals(value)) {
				tableName = enter.getKey();
			}
		}
		return tableName;
	}

	public static void main(String[] args) {
		class Name {
			String flag1;
			String flag2;

			public Name(String flag1, String flag2) {
				super();
				this.flag1 = flag1;
				this.flag2 = flag2;
			}

			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((flag1 == null) ? 0 : flag1.hashCode());
				result = prime * result + ((flag2 == null) ? 0 : flag2.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				Name other = (Name) obj;
				if (flag1 == null) {
					if (other.flag1 != null)
						return false;
				} else if (!flag1.equals(other.flag1))
					return false;
				if (flag2 == null) {
					if (other.flag2 != null)
						return false;
				} else if (!flag2.equals(other.flag2))
					return false;
				return true;
			}

		}

		List<Name> branchLst = new ArrayList<Name>();
		List<Name> branchLst2 = new ArrayList<Name>();

		branchLst.add(new Name("1", "2"));
		branchLst.add(new Name("2", "2"));

		branchLst2.add(new Name("1", "2"));

		branchLst.removeAll(branchLst2);
	}

	private String getDateStr(Date date) {
		if (date != null) {
			return DateTimeUtil.getDateTime(date, DateTimeUtil.DEFAULT_DATE_FORMAT);
		}
		return "";
	}

	public FoodBillsBean getFoodBillsBean() {
		return foodBillsBean;
	}

	public void setFoodBillsBean(FoodBillsBean foodBillsBean) {
		this.foodBillsBean = foodBillsBean;
	}

	public File getAttachment() {
		return attachment;
	}

	public void setAttachment(File attachment) {
		this.attachment = attachment;
	}

	public void setImportFSDao(ImportFSDao importFSDao) {
		this.importFSDao = importFSDao;
	}

	public ImportFSDao getImportFSDao() {
		return importFSDao;
	}

	public void setStuta(int stuta) {
		this.stuta = stuta;
	}

	public int getStuta() {
		return stuta;
	}

	public ZipFile getZf() {
		return zf;
	}

	public void setZf(ZipFile zf) {
		this.zf = zf;
	}

	public void setBranchBean(BranchBean branchBean) {
		this.branchBean = branchBean;
	}

}