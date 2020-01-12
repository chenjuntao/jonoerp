/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 30, 2016 by cjt
 * Last edited on Mar 30, 2016 by cjt
 * 
 * 说明： 读取COMPANY表中所有的企业对应的数据库名，对所有的数据库一一遍历进行更改
 */
package com.tanry.business.common.init;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ChangeAllComDB {
	private static Connection conn;

	public static void main(String[] args) {
		try {
			conn = DBUtil.getConnection();
			long start = System.currentTimeMillis();
			ChangeAllComDB changeComDB = new ChangeAllComDB();
			changeComDB.ChangeDB();
			long end = System.currentTimeMillis();
			System.out.println("完成！elapsed time(in miliseconds) is : " + (end - start));
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			
		}
	}

	/**
	 * 遍历所有的企业对应的数据库，一一进行修改
	 */
	private void ChangeDB() throws SQLException {
		//查询所有的企业数据库名
		String selAllComSql = "select * from com.d_t_company";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(selAllComSql);
		
		Statement tempSt = conn.createStatement();
		
		//一一遍历进行更新
		while (rs.next()) {
			String db = rs.getString("db_name");
			System.out.println("正在处理" + db);
			
			List<String> sqls = getSql(db);
			for(int i=0; i<sqls.size(); i++){
				try {
					String sql = sqls.get(i);
					System.out.println(sql);
					tempSt.execute(sql);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}
		}
		
		rs.close();
		st.close();
		tempSt.close();
	}
	
	//获取sql字符串，一次只能执行一条语句
	private List<String> getSql(String db){
		List<String> results = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		//读取文件
		try{
			InputStream input = this.getClass().getResourceAsStream("ChangeAllCom.sql");
	        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
	        String line = null;
	        while((line = reader.readLine()) != null){
	        	if(line.contains(" D_T")){
	        		line = line.replace(" D_T", " " + db +".D_T");  //给表名加上前缀
	        	}
	        	if(line.contains("--")){
	        		line = line.substring(0, line.indexOf("--"));  //去掉注解
	        	}

	        	if(line.contains(";")){
	        		line = line.substring(0, line.indexOf(";"));  //最后一行结束
	        		sb.append(line);
	        		results.add(sb.toString());
	        		sb.delete(0, sb.length());
	        	}else{
	        		sb.append(line);
		        	sb.append("\n");
	        	}
	        }
	        reader.close();
		}catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return results;
	}
}


