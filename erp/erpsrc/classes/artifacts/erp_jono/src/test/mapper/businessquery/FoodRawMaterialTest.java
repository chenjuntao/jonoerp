/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.businessquery;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mapper.businessquery.FoodRawMaterialMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class FoodRawMaterialTest {
	  
	@Resource
	private FoodRawMaterialMapper foodRawMaterialMapper;
	 

	//@Test
	public void testgetAmt(){
		System.out.println("test getAmt...");
		BigDecimal result1 = foodRawMaterialMapper.getSum("jono","10", "2016-04-08", "2016-04-30");
		BigDecimal result2 = foodRawMaterialMapper.getAmt("jono","10", "2016-04-08", "2016-04-30");
		if (result1 != null) {
			result1 = (BigDecimal)result1;
		}else{
			result1 = BigDecimal.ZERO;
		}
		if (result2 != null) {
			result2 = (BigDecimal)result2;
		}else{
			result2 = BigDecimal.ZERO;
		}
		BigDecimal result3 = result1.subtract(result2);
		result3 = result3.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("amt is:"+result3);
		System.out.println("test OK!");
	}

	//@Test
	public void testgetDetail(){
		System.out.println("test getDetail...");
		List<Map> result = foodRawMaterialMapper.getDetail("jono","10", "2016-04-08", "2016-04-30", "2027");
		System.out.println("detail size is:"+result.size());
		System.out.println("test OK!");
	}

	@Test
	public void testgetAllAmtTTCNY(){
		System.out.println("test getAllAmtTTCNY...");
		BigDecimal sum = foodRawMaterialMapper.getSum("jono","10", "2016-04-08", "2016-04-30");
		BigDecimal amt = foodRawMaterialMapper.getAmt("jono","10", "2016-04-08", "2016-04-30");
		if (sum != null) {
			sum = (BigDecimal)sum;
		}else{
			sum = BigDecimal.ZERO;
		}
		if (amt != null) {
			amt = (BigDecimal)amt;
		}else{
			amt = BigDecimal.ZERO;
		}
		BigDecimal sa1 = sum.subtract(amt);
		sa1 = sa1.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("amt1 is:"+sa1);
		
		
		sum = foodRawMaterialMapper.getSum("jono","10", "2016-04-08", "2016-04-30");
		amt = foodRawMaterialMapper.getAmt("jono","10", "2016-04-08", "2016-04-30");
		if (sum != null) {
			sum = (BigDecimal)sum;
		}else{
			sum = BigDecimal.ZERO;
		}
		if (amt != null) {
			amt = (BigDecimal)amt;
		}else{
			amt = BigDecimal.ZERO;
		}
		BigDecimal sa2 = sum.subtract(amt);
		sa2 = sa2.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("amt2 is:"+sa2);
		
		sum = foodRawMaterialMapper.getSum("jono","10", "2016-04-08", "2016-04-30");
		amt = foodRawMaterialMapper.getAmt("jono","10", "2016-04-08", "2016-04-30");
		if (sum != null) {
			sum = (BigDecimal)sum;
		}else{
			sum = BigDecimal.ZERO;
		}
		if (amt != null) {
			amt = (BigDecimal)amt;
		}else{
			amt = BigDecimal.ZERO;
		}
		BigDecimal sa3 = sum.subtract(amt);
		sa3 = sa3.setScale(2, BigDecimal.ROUND_HALF_UP);
		System.out.println("amt3 is:"+sa3);
		
		List<Map> result = foodRawMaterialMapper.getAllAmtTTCNY("jono","10", "2016-04-08", "2016-04-30","2016-04-08", "2016-04-30","2016-04-08", "2016-04-30",  " 1 = 1 ", sa1, sa2 ,sa3);
		System.out.println("test OK!");
	}
}