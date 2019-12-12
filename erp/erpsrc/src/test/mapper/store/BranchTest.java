/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package test.mapper.store;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import mapper.store.BranchMapper;

import org.junit.Test;
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  

import com.tanry.framework.util.constant.PriceType;
import com.tanry.framework.util.enums.BranchTypeEnum;

import pojo.store.Branch;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:WEB-INF/spring-mybatis.xml"})
public class BranchTest {
	  
	@Resource
	private BranchMapper branchMapper;

	@Test
	public void testsaveEntity(){
		System.out.println("test saveEntity...");
		Branch branch = new Branch();
		branch.setBranchId("test001");
		branch.setBranchName("1号测试门店");
		branch.setQueryCode("1hcemd");
		branch.setBranchType(BranchTypeEnum.RESTAURANT.toString());
		branch.setPriceType(PriceType.BENCHMARK);
		branch.setBranchAddress("长沙市华府一航线");
		branch.setEnabled(0);
		int result = branchMapper.saveEntity("jono", branch);
		System.out.println("test OK!");
	}

	@Test
	public void testcreateBusinessDate(){
		System.out.println("test createBusinessDate...");
		int result = branchMapper.createBusinessDate("jono","test001", BranchTypeEnum.RESTAURANT.toString());
		System.out.println("test OK!");
	}

	@Test
	public void testupdateEntity(){
		System.out.println("test updateEntity...");
		Branch branch = new Branch();
		branch.setBranchId("test001");
		branch.setBranchName("2号测试门店");
		branch.setQueryCode("2hcemd");
		branch.setBranchType(BranchTypeEnum.RESTAURANT.toString());
		branch.setPriceType(PriceType.BENCHMARK);
		branch.setBranchAddress("长沙市华府一航线");
		branch.setEnabled(1);
		int result = branchMapper.updateEntity("jono", "test001", branch);
		System.out.println("test OK!");
	}

	@Test
	public void testcontainsEntity(){
		System.out.println("test containsEntity...");
		boolean result = branchMapper.containsEntity("jono","test001", BranchTypeEnum.RESTAURANT.toString());
		System.out.println("test OK!");
	}

	@Test
	public void testdeleteEntity(){
		System.out.println("test deleteEntity...");
		int result = branchMapper.deleteEntity("jono","test001");
		System.out.println("test OK!");
	}

	@Test
	public void testenableBranch(){
		System.out.println("test enableBranch...");
		int result = branchMapper.enableBranch("jono","test001", 0);
		System.out.println("test OK!");
	}

	@Test
	public void testGetBranchById(){
		System.out.println("test GetBranchById...");
		Branch result = branchMapper.GetBranchById("jono","test001");
		System.out.println("test OK!");
	}

	@Test
	public void testqueryByTypeName(){
		System.out.println("test queryByTypeName...");
		List<Branch> result = branchMapper.queryByTypeName("jono",BranchTypeEnum.RESTAURANT,"测试门店");
		System.out.println("test OK!");
	}

	@Test
	public void testqueryRestaurant(){
		System.out.println("test queryRestaurant...");
		List<Branch> result = branchMapper.queryRestaurant("jono",BranchTypeEnum.RESTAURANT);
		System.out.println("test OK!");
	}

	@Test
	public void testlistShopByType(){
		System.out.println("test listShopByType...");
		List<Map> result = branchMapper.listShopByType("jono",BranchTypeEnum.RESTAURANT);
		System.out.println("test OK!");
	}

	@Test
	public void testqueryById(){
		System.out.println("test queryById...");
		List<Map> result = branchMapper.queryById("jono","test001");
		System.out.println("test OK!");
	}
}