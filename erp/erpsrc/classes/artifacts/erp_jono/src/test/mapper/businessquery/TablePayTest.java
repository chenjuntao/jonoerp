/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.businessquery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mapper.businessquery.TablePayMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.tanry.framework.acl.NoPrivilegeException;

import pojo.businessquery.TablePay;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class TablePayTest {
	  
	@Resource
	private TablePayMapper tablePayMapper;
	 

	@Test
	public void testlistTablePay() throws NoPrivilegeException{
		System.out.println("test listTablePay...");
		List<TablePay> tables = new ArrayList<TablePay>();
		List<String> tmp = tablePayMapper.listTablePay("jono", "2016-04-08", "2016-04-30", "10");
		for (int i = 0 ; i < tmp.size() ; i++){
			TablePay table = new TablePay();
			table.table = tmp.get(i);
			List<TablePay> tmp1 = tablePayMapper.listTablePay1("jono", "2016-04-08", "2016-04-30", "10", null);
			List billCList = new ArrayList();//存储每个单据的编号
			for (int j = 0 ; j < tmp1.size(); j++){
				billCList.add(tmp1.get(j).getCbillc());
				table.payAmt += tmp1.get(j).getPayAmt();
			}
			table.pay = new HashMap<String, Float>();
			for (int j=0; j<billCList.size(); j++)
			{
				String billC = (String)billCList.get(j);
				List<TablePay> tmp2 = tablePayMapper.listTablePay2("jono", "2016-04-08", "2016-04-30", "10", null);
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
		System.out.println("test OK!");
	}

}