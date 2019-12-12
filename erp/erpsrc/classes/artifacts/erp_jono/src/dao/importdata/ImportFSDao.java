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
 * 系统数据备份还原的SQL生成和执行
 */

package dao.importdata;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import logic.NoConnection;
import logic.pool.ConnectionPool;
import logic.store.OptionBean;

import org.apache.commons.io.output.FileWriterWithEncoding;

import action.setting.BuildMap;
import action.setting.DeleteFolder;
import action.setting.FileToZip;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.util.DateTimeUtil;
import com.tanry.framework.util.SqlDateUtil;
import com.tanry.framework.util.SysUtil;
import com.tanry.framework.util.TextUtil;
import com.tanry.framework.util.constant.SysOption;

public class ImportFSDao extends ConnectionPool {
	private static int times = 0;
	private OptionBean optionBean;

	public void clearItem(String tbName) throws SQLException, NoPrivilegeException, NoConnection {
		clearItem(tbName, null, null, null);
	}

	public String readItem(String tbName, boolean stuta) throws SQLException, NoPrivilegeException, NoConnection {
		return readItem(tbName, null, null, null, stuta, null);
	}

	public String readItem(String tbName, Map<String, Object> values, String startDate, String endDate, boolean stuta)
			throws SQLException, NoPrivilegeException, NoConnection {
		return readItem(tbName, values, startDate, endDate, stuta, null);
	}

	public int countTable(String tbName, Map<String, Object> values, String startDate, String endDate)
			throws SQLException, NoPrivilegeException, NoConnection {
		return countTable(tbName, values, startDate, endDate, null);
	}

	public int countTable(String tbName, Map<String, Object> values) throws SQLException, NoPrivilegeException,
			NoConnection {
		return countTable(tbName, values, null, null, null);
	}

	public String buildSql(String tbName, Map<String, Object> values, String startDate, String endDate, String branchId) {
		String where = "1=1 ";
		this.setCompanyId(getSessionCom());
		String sql = "from " + Com_(tbName) + " s";

		try {
			if (values.get("table") == null) {
				if (!startDate.equals("") && startDate != null) {
					where += "and to_char(s." + values.get("data") + ",'yyyy-mm-dd') >= '" + startDate + "' ";
				}
				if (!endDate.equals("") && endDate != null) {
					where += "and to_char(s." + values.get("data") + ",'yyyy-mm-dd') <= '" + endDate + "' ";
				}
			} else {
				if (!startDate.equals("") && startDate != null) {
					where += "and to_char(h." + values.get("data") + ",'yyyy-mm-dd') >= '" + startDate + "' ";
				}
				if (!endDate.equals("") && endDate != null) {
					where += "and to_char(h." + values.get("data") + ",'yyyy-mm-dd') <= '" + endDate + "' ";
				}
				sql += " LEFT JOIN " + values.get("table") + " h ON h." + values.get("item") + "= s."
						+ values.get("item");
			}

			if (!TextUtil.isEmpty(branchId)) {
				where += "and h.CBRANCH_C = " + branchId;
			}

			sql += "  where " + where;
		} catch (NullPointerException e) {
		}
		return sql;
	}

	public int countTable(String tbName, Map<String, Object> values, String startDate, String endDate, String branchId)
			throws SQLException, NoPrivilegeException, NoConnection {
		int count = 0;
		Connection conn = getConnection();
		String sql = "SELECT count(*) ";
		sql += buildSql(tbName, values, startDate, endDate, branchId);

		PreparedStatement ps = conn.prepareStatement(sql);
		ResultSet rs = ps.executeQuery();
		rs.next();
		count = rs.getInt(1);
		rs.close();
		ps.close();
		releaseConnection(conn);
		return count;
	}

	public String readItem(String tbName, Map<String, Object> values, String startDate, String endDate, boolean stuta,
			String branchId) throws SQLException, NoPrivilegeException, NoConnection {
		// tbName = "D_T1_INPUT_HEADER";
		// startDate = "2016-08-03";
		// endDate = "2016-08-03";
		String bs = " ";
		Connection conn = getConnection();
		String sql = "select s.* ";
		sql += buildSql(tbName, values, startDate, endDate, branchId);
		PreparedStatement stmt;
		StringBuffer sb = new StringBuffer();
		Map<String, String> itemMap = BuildMap.itemNames();
		try {
			stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery(sql);
			ResultSetMetaData data = rs.getMetaData();
			// List<String> sidLst = new ArrayList<String>();
			for (int i = 1; i <= data.getColumnCount(); i++) {
				// sidLst.add(data.getColumnName(i));
				String columnName = itemMap.get(data.getColumnName(i));// 转中文名字
				// if (TextUtil.isEmpty(columnName)) {
				columnName = data.getColumnName(i);// 取英文字段名
				// }
				sb.append(columnName);
				if (i != data.getColumnCount()) {
					sb.append(SysOption.CSV_SPLITTER);
				}
			}
			sb.append("\n");
			Statement st = conn.createStatement();
			ResultSet rsd = st.executeQuery(sql);
			while (rsd.next()) {
				for (int i = 1; i <= data.getColumnCount(); i++) {
					Object value = rsd.getObject(data.getColumnName(i));
					if (value == null) {
						value = "";
					} else {
						value = value.toString().replaceAll("\n", "");
						value = value.toString().replaceAll("\r", "");
						value = value.toString().replaceAll(SysOption.CSV_SPLITTER, "");
					}
					sb.append(value);
					if (i != data.getColumnCount()) {
						sb.append(SysOption.CSV_SPLITTER);
					}
				}
				sb.append("\n");
			}
			if (stuta) {
				sql = "DELETE FROM (" + sql + ")";
				int result = st.executeUpdate(sql);
				System.out.println("delete records : " + result);
			}
			rsd.close();
			st.close();
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		bs = sb.toString();

		releaseConnection(conn);
		return bs;
	}

	public void clearItem(String tbName, Map<String, Object> values, String startDate, String endDate)
			throws SQLException, NoPrivilegeException, NoConnection {
		Connection conn = getConnection();
		String sql = "select s.* ";
		this.setCompanyId(getSessionCom());
		sql += buildSql(tbName, values, startDate, endDate, null);
		try {
			Statement st = conn.createStatement();
			if (values != null && values.get("table") != null) {
				sql = "delete from " + Com_(tbName) + " where exists(" + sql + ")";
			} else {
				sql = "DELETE FROM (" + sql + ")";
			}
			int result = st.executeUpdate(sql);
			System.out.println(tbName + "delete records : " + result);
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		releaseConnection(conn);
	}

	public int inTable(String tbName, BufferedReader brfs) throws SQLException, NumberFormatException, IOException {
		Connection conn = getConnection();
		// conn.setAutoCommit(false);
		String strLine = brfs.readLine();
		this.setCompanyId(getSessionCom());
		String sql = "select * from " + Com_(tbName);

		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData data = rs.getMetaData();
		// ResultSet pk = data.getPrimaryKeys(null, null, tbName);
		StringBuffer sb = new StringBuffer();
		StringBuffer bs = new StringBuffer();
		int number = data.getColumnCount();

		DatabaseMetaData dbmd = conn.getMetaData();
		ResultSet rsk = dbmd.getPrimaryKeys(null, null, tbName.toUpperCase()); // 要将表名转为大写才能正确取出主键来
		String cb = "1=1 ";// 已主键为过滤条件，防止数据重复
		String columnName = "";
		int len = 0;
		while (rsk.next()) {
			if (!columnName.equals(rsk.getString(4))) {
				cb += "AND " + rsk.getString(4) + "=? ";
				columnName = rsk.getString(4);
				len++;
			}
		}

		Map<String, String> map = new HashMap<String, String>();
		String[] types = new String[number];
		String[] columns = strLine.split(SysOption.CSV_REGEX_SPLITTER, number);// 逗号隔开的各个列

		for (int j = 1; j <= number; j++) {
			map.put(data.getColumnName(j), data.getColumnClassName(j));
		}
		for (int j = 0; j < number; j++) {
			String column = columns[j];
			types[j] = map.get(column);
			if (j == number - 1) {
				sb.append(column);
				bs.append("?");
			} else {
				sb.append(column);
				sb.append(",");
				bs.append("?");
				bs.append(",");
			}
		}
		String insertSql = "MERGE INTO " + Com_(tbName) + " USING dual ON (" + cb + ") WHEN NOT MATCHED THEN INSERT ("
				+ sb + ") VALUES (" + bs + ")";
		PreparedStatement insPs = conn.prepareStatement(insertSql);
		while ((strLine = brfs.readLine()) != null && !"".equals(strLine)) {
			if (!strLine.isEmpty()) {
				try {
					String[] values = strLine.split(SysOption.CSV_REGEX_SPLITTER, number);// 逗号隔开的各个列

					for (int k = 0; k < values.length; k++) {
						// String value = values[k];
						// System.out.print(value);
						if (k < len) {
							insPs.setString(k + 1, values[k]);
						}
						if (types[k].equals("java.sql.Timestamp") || types[k].equals("oracle.sql.TIMESTAMP")) {
							Date time = DateTimeUtil.parse(values[k], DateTimeUtil.DEFAULT_DATETIME_FORMAT);
							insPs.setDate(k + len + 1, SqlDateUtil.getSqlDate(time));
						} else if (types[k].equals("java.math.BigDecimal")) {
							try {
								if (values[k] != null && !"".equals(values[k])) {
									strLine += "111111111?" + values[k];
									insPs.setBigDecimal(k + len + 1, new BigDecimal(values[k]));
								} else {
									insPs.setBigDecimal(k + len + 1, null);
								}

							} catch (Exception e) {
								// TODO: handle exception
								System.out.println(values[k]);
							}
						} else if (types[k].equals("oracle.jdbc.OracleClob")) {
							if (values[k] != null && !"".equals(values[k])) {
								insPs.setString(k + len + 1, values[k]);
							} else {
								insPs.setString(k + len + 1, null);
							}
						} else {
							insPs.setObject(k + len + 1, values[k]);
						}
					}

				} catch (SQLException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}

				insPs.addBatch();
			}
		}

		insPs.executeBatch();
		insPs.clearBatch();
		insPs.close();
		releaseConnection(conn);
		return 2;
	}

	public int inBueness(String tbName, BufferedReader brfs) throws SQLException, NumberFormatException, IOException {
		Connection conn = getConnection();
		String strLine = brfs.readLine();
		this.setCompanyId(getSessionCom());
		String sql = "select * from " + Com_(tbName);
		Map<String, String> map = new HashMap<String, String>();
		PreparedStatement stmt = conn.prepareStatement(sql);
		ResultSet rs = stmt.executeQuery(sql);
		ResultSetMetaData data = rs.getMetaData();
		StringBuffer sb = new StringBuffer();
		StringBuffer bs = new StringBuffer();
		int number = data.getColumnCount();
		String[] columns = strLine.split(SysOption.CSV_REGEX_SPLITTER, number);// 逗号隔开的各个列

		for (int j = 1; j <= number; j++) {
			map.put(data.getColumnName(j), data.getColumnClassName(j));
		}
		String[] types = new String[number];
		for (int j = 0; j < number; j++) {
			String column = columns[j];
			types[j] = map.get(column);
			if (j == number - 1) {
				sb.append(column);
				bs.append("?");
			} else {
				sb.append(column);
				sb.append(",");
				bs.append("?");
				bs.append(",");
			}
		}
		String insertSql = "INSERT INTO " + Com_(tbName) + "(" + sb + ") VALUES (" + bs + ")";
		PreparedStatement insPs = conn.prepareStatement(insertSql);
		while ((strLine = brfs.readLine()) != null && !"".equals(strLine)) {
			if (!strLine.isEmpty()) {
				String[] values = strLine.split(SysOption.CSV_REGEX_SPLITTER, number);// 逗号隔开的各个列
				for (int k = 0; k < values.length; k++) {
					// String value = values[k];
					// System.out.print(value);

					if (types[k].equals("java.sql.Timestamp") || types[k].equals("oracle.sql.TIMESTAMP")) {
						Date time = DateTimeUtil.parse(values[k], DateTimeUtil.DEFAULT_DATETIME_FORMAT);
						insPs.setDate(k + 1, SqlDateUtil.getSqlDate(time));
					} else if (types[k].equals("java.math.BigDecimal")) {
						try {
							if (values[k] != null && !"".equals(values[k])) {
								insPs.setBigDecimal(k + 1, new BigDecimal(values[k]));
							} else {
								insPs.setBigDecimal(k + 1, null);
							}

						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(values[k]);
						}
					} else {
						insPs.setObject(k + 1, values[k]);
					}
				}

				insPs.addBatch();
			}
		}

		insPs.executeBatch();
		insPs.clearBatch();
		insPs.close();

		releaseConnection(conn);
		return 2;
	}

	public void backupsBranch(int stuta) throws NoPrivilegeException, SQLException, NoConnection, IOException {
		int tableCount = 0;
		String basePath = SysUtil.getExportCsvDir();
		String month = "branch";
		String monthPath = basePath + File.separator + month;
		String timeStr = DateTimeUtil.getDateTime("yyyyMMdd_HHmmss");
		String timeName = DateTimeUtil.getDateTime("yyyyMMdd");
		String timePath = basePath + File.separator + month + File.separator + timeStr;

		File timeDir = new File(timePath);
		if (!timeDir.exists()) {
			timeDir.mkdirs();
		}

		String bh = "";
		String[] branchs = BuildMap.branchs;
		String fileName = timeName + "基本信息";
		Integer timeNumber = Integer.parseInt(timeName);
		String deleteName = String.format("%08d", timeNumber - 7);// 保留四位整数，不足补0
		String deleteFile = deleteName + "基本信息";
		if (stuta == 2) {
			fileName = timeName + "系统信息";
			deleteFile = deleteName + "系统信息";
			branchs = BuildMap.syses;
		} else if (stuta == 3) {
			fileName = timeName + "单据信息";
			branchs = BuildMap.headers;
		}
		tableCount = branchs.length;
		for (int i = 0; i < tableCount; i++) {
			String tbName = branchs[i];
			File bpFile = new File(timePath + File.separator + tbName + ".txt");
			bh = readItem(tbName, false);

			try {
				FileWriterWithEncoding out = new FileWriterWithEncoding(bpFile, Charset.forName("UTF-8").newEncoder());
				out.write(bh);
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
			}
		}
		String zipFilePath = monthPath + File.separator + fileName + ".zip";
		FileToZip.zipDIR(timePath, zipFilePath);// 打包zip

		DeleteFolder.deleteDirectory(timePath);// 删除临时文件
		String deleteFilePath = monthPath + File.separator + deleteFile + ".zip";
		DeleteFolder.deleteFile(deleteFilePath);// 删除前第七天的zip包，只保留一周的基本，系统数据
	}

	public void backupsForm(int stuta) throws NoPrivilegeException, SQLException, NoConnection, IOException {
		int cycle = 0;
		try {
			cycle = Integer.parseInt(optionBean.getOption("BACKUP_CYCLE"));
		} catch (Exception e) {
			cycle = 7;
		}
		int tableCount = 0;
		String basePath = SysUtil.getExportCsvDir();
		String month = "branch";
		String monthPath = basePath + File.separator + month;
		String formTime = DateTimeUtil.getDateStr(DateTimeUtil.addDays(-1));
		String timeName = DateTimeUtil.getDateTime("yyyyMMdd");
		String timePath = basePath + File.separator + month + File.separator + "Forms";
		File timeDir = new File(timePath);
		if (!timeDir.exists()) {
			timeDir.mkdirs();
		}

		String bh = "";
		String[] branchs = BuildMap.headers;
		List<Map<String, Map<String, Object>>> tableList = BuildMap.buildMap();
		String fileName = timeName + times + "单据信息";
		if (stuta == 2) {
			fileName = timeName + times + "营业信息";
			branchs = BuildMap.businesses;
			tableList = BuildMap.buildBusiness();
		}
		tableCount = branchs.length;
		for (int i = 0; i < tableCount; i++) {
			String tbName = branchs[i];
			for (Map<String, Map<String, Object>> map : tableList) {
				File bpFile = new File(timePath + File.separator + timeName + tbName + ".txt");
				Map<String, Object> value = map.get(tbName);
				bh = readItem(tbName, value, formTime, formTime, false, "");

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
		}

		if (times % cycle == 0) {
			String zipFilePath = monthPath + File.separator + fileName + ".zip";
			FileToZip.zipDIR(timePath, zipFilePath);// 打包zip
			DeleteFolder.deleteDirectory(timePath);// 删除临时文件
		}
		times++;
	}

	public static String trimExtension(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int i = filename.lastIndexOf('.');
			if ((i > -1) && (i < (filename.length()))) {
				return filename.substring(0, i);
			}
		}
		return filename;
	}

	public void setOptionBean(OptionBean optionBean) {
		this.optionBean = optionBean;
	}

}
