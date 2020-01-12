/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月17日 by cjt
 * Last edited on 2016年3月17日 by cjt
 */
package service.restapi;

import com.tanry.framework.acl.NoPrivilegeException;
import logic.NoConnection;
import logic.restapi.WeightBean;
import logic.store.BranchBean;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * 说明：上传重量以及图片
 */
public class UploadWeightService {

	private WeightBean weightBean;

	public void setWeightBean(WeightBean weightBean) {
		this.weightBean = weightBean;
	}

	//
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public int saveWeight(@Param("myid")String myid,@Param("num")String num,@Param("pic")String pic) throws NoPrivilegeException, SQLException, NoConnection {
		return weightBean.saveEntity(myid, num, pic);
	}

	public int saveStore(@Param("id")String id,@Param("num")String num,@Param("pic")String pic) throws NoPrivilegeException, SQLException, NoConnection {
		return weightBean.saveStore(id, num, pic);
	}

	public List selectStore() throws NoPrivilegeException, SQLException, NoConnection {
		return weightBean.selectStore();
	}

	public List selectTest() throws NoPrivilegeException, SQLException, NoConnection {
		return weightBean.selectTest();
	}

//	public List selectTests() throws NoPrivilegeException, SQLException, NoConnection {
//		return weightBean.selectTests();
//	}
}
