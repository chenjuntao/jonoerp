package com.tanry.business.common.quartz;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;



/**
 * 定时任务
 * 
 * @author liyzh
 * 
 */
public class JobBean {

	private JdbcTemplate jdbcTemplate;
	private PlatformTransactionManager txManager;// 依赖注入管理事务

	private void invoke(String procName) {
		System.out.println(procName);
		if ("transformProduct".equals(procName)) {
			try {
//				ProductTransform.transform(jdbcTemplate);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void callProc(String description, String procName) {
		long beginTime = System.currentTimeMillis(); // 获取开始时间
		String ip_addr = "";// 用于判断quartz定时任务是在哪个服务器上触发的，比如正式或临时站点
		try {
			ip_addr = InetAddress.getLocalHost().toString();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		}
		String logSql = "insert into D_T0_QUARTZLOG(JOB_DESC, BEGIN_TIME, END_TIME, ELAPSED_TIME, JOB_RESULT, IP_ADDR) "
				+ "values(?, ?, ?, ?, ?, ?)";
		try {
			// invoke(procName); //事务测试
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			TransactionStatus status = txManager.getTransaction(def);
			try {
				invoke(procName); // 对业务进行事务处理，日志除外，日志必须生成
				txManager.commit(status);
			} catch (DataAccessException ex) {
				txManager.rollback(status);
				throw ex;
			}

			long endTime = System.currentTimeMillis(); // 获取结束时间
			double elapsedtime = (endTime - beginTime) * 0.001; // 以秒为单位，包含四位小数(毫秒)
			jdbcTemplate.update(logSql, new Object[] { description, new Date(beginTime), new Date(endTime),
					elapsedtime, "Normal!", ip_addr });
		} catch (RuntimeException e) {
			e.printStackTrace();

			long endTime = System.currentTimeMillis(); // 获取结束时间
			double elapsedtime = (endTime - beginTime) * 0.001;
			jdbcTemplate.update(logSql, new Object[] { description, new Date(beginTime), new Date(endTime),
					elapsedtime, "Exception happended!", ip_addr });
		}
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void setTxManager(PlatformTransactionManager txManager) {
		this.txManager = txManager;
	}
}
