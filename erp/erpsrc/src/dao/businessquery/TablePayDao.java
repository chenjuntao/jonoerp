/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
 
package dao.businessquery;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import pojo.businessquery.TablePay;
import logic.NoConnection;
import logic.businessquery.TablePayBean;
import mapper.businessquery.TablePayMapper;

import com.tanry.framework.acl.NoPrivilegeException;

import dao.BaseDao;

/**
 *  created by yxg, 2016-09-09
 *  TablePay的DAO层
 */
public class TablePayDao extends BaseDao {
	 
	private TablePayMapper tablePayMapper;	
	private String strDateFormat = "yyyy-MM-dd";
	public void setTablePayMapper(TablePayMapper tablePayMapper) {
		this.tablePayMapper = tablePayMapper;
	}

	private TablePayBean tablePayBean;
	public void setTablePayBean(TablePayBean tablePayBean) {
		this.tablePayBean = tablePayBean;
	}
	

	public List<TablePay> listTablePay(String startTime, String endTime, String shopC)
		throws NoPrivilegeException, SQLException, NoConnection{
		if(beUseMySql){
			List<TablePay> tables = new ArrayList<TablePay>();
			List<String> tmp = tablePayMapper.listTablePay(getCom(), startTime, endTime, shopC);
			for (int i = 0 ; i < tmp.size() ; i++){
				TablePay table = new TablePay();
				table.table = tmp.get(i);
				String table_N = table.getTable();
				List<TablePay> tmp1 = tablePayMapper.listTablePay1(getCom(), startTime, endTime, shopC, table_N);
				List billCList = new ArrayList();//存储每个单据的编号
				for (int j = 0 ; j < tmp1.size(); j++){
					billCList.add(tmp1.get(j).getCbillc());
					table.payAmt += tmp1.get(j).getPayAmt();
				}
				table.pay = new HashMap<String, Float>();
				for (int j=0; j<billCList.size(); j++)
				{
					String billC = (String)billCList.get(j);
					List<TablePay> tmp2 = tablePayMapper.listTablePay2(getCom(), startTime, endTime, shopC, billC);
					for (int k = 0 ; k < tmp2.size() ; k++){
						String payN = tmp2.get(k).getCPayN();
						if (table.pay.containsKey(payN))
						{
							float payAmt = table.pay.get(payN);
							payAmt += tmp2.get(k).getPayAmt();
							table.pay.put(payN, payAmt);
						}
						else
						{
							table.pay.put(payN, tmp2.get(k).getPayAmt());
						}
					}
				}
				tables.add(table);
			}
			return tables;
		}else{
			return tablePayBean.listTablePay(startTime, endTime, shopC);
		}
	}
	
	public Map<String, List> listTablePayByDay(String startTime, String endTime, String shopC)
			throws NoPrivilegeException,SQLException,NoConnection
		{
			Map<String, List> result = new TreeMap<String, List>();
			List<String> dateList = getDates(startTime, endTime);
			for (int i=0; i<dateList.size()-1; i++) 
			{
				String date = dateList.get(i);
				String nextDate = dateList.get(i+1);
				List<TablePay> shopList = listTablePay(date, nextDate, shopC); 
				if(shopList.size()>0)
				{
					result.put(date, shopList);
				}
			}
			return result;
		}

		/**
		 * Get the Dates between Start Date and End Date.
		 * @param start_date	Start Date
		 * @param end_date		End Date
		 * @return Dates List
		 */
		public List<String> getDates(String start_date, String end_date) 
		{	
			SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
			Calendar p_start = new GregorianCalendar();
			Calendar p_end = new GregorianCalendar();

			try 
			{
				p_start.setTime(sdf.parse(start_date));
				p_end.setTime(sdf.parse(end_date));
			} 
			catch (ParseException e) 
			{
				e.printStackTrace();
			}

			List<String> result = new ArrayList<String>();
			if(p_start.before(p_end))
			{
				while (p_start.before(p_end)) 
				{
					result.add(sdf.format(p_start.getTime()));
					p_start.add(Calendar.DAY_OF_YEAR, 1);
				}
				result.add(sdf.format(p_start.getTime()));
			}
			return result;
		}
}