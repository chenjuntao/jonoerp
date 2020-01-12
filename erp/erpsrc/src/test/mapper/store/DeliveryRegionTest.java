/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.store;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import mapper.store.DeliveryRegionMapper;
import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class DeliveryRegionTest {
	  
	@Resource
	private DeliveryRegionMapper deliveryRegionMapper;
	 

	@Test
	public void testsaveEntity(){
		System.out.println("test saveEntity...");
//		int result = deliveryRegionMapper.saveEntity(group);
		System.out.println("test OK!");
	}

	@Test
	public void testupdateEntity(){
		System.out.println("test updateEntity...");
//		int result = deliveryRegionMapper.updateEntity(oldRegionId, group);
		System.out.println("test OK!");
	}

	@Test
	public void testdeleteEntity(){
		System.out.println("test deleteEntity...");
//		int result = deliveryRegionMapper.deleteEntity(regionId);
		System.out.println("test OK!");
	}

	@Test
	public void testenableGroup(){
		System.out.println("test enableGroup...");
//		int result = deliveryRegionMapper.enableGroup(regionId, enabled);
		System.out.println("test OK!");
	}

	@Test
	public void testqueryAll(){
		System.out.println("test queryAll...");
//		List<DeliveryRegion> result = deliveryRegionMapper.queryAll();
		System.out.println("test OK!");
	}

	@Test
	public void testqueryTree(){
		System.out.println("test queryTree...");
//		List<Map> result = deliveryRegionMapper.queryTree();
		System.out.println("test OK!");
	}

	@Test
	public void testqueryStore(){
		System.out.println("test queryStore...");
//		List<Map> result = deliveryRegionMapper.queryStore();
		System.out.println("test OK!");
	}

	@Test
	public void testqueryById(){
		System.out.println("test queryById...");
//		DeliveryRegion result = deliveryRegionMapper.queryById(regionId);
		System.out.println("test OK!");
	}
}