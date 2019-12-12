/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 11, 2016 by liyzh
 * Last edited on Mar 11, 2016 by liyzh
 * 
 * 说明： TODO
 */
package com.tanry.framework.util;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.tanry.framework.acl.NoPrivilegeException;
import com.tanry.framework.log.ConnLogUtil;

public class StatusUtil {

	private static Map<String, Long> connLogMap = new HashMap<>();

	public static void addConnLog(String key) {
		connLogMap.put(key, new Date().getTime());
	}

	public static void removeConnLog(String key, String type) throws NoPrivilegeException, SQLException {
		// System.out.println(connLogMap);
		// connLogMap.clear();
		Long beginTime = connLogMap.get(key);
		if (beginTime == null) {
			System.out.println(key + " has problem");// 可能是程序逻辑有问题：单例属性是否会相互干扰？
			return;
		}
		Long consumeTime = new Date().getTime() - beginTime;
		// System.out.println("api is : " + key);
		// System.out.println("connInUse is : " + connLogMap.keySet().size());
		if (consumeTime > 500) {// 记录耗时大于一毫秒的操作，如果对系统性能有影响，可酌情加大记录起始毫秒数
			if (key.indexOf("saveConnLog") == -1) {
				ConnLogUtil.record(key, type, consumeTime, connLogMap.keySet().size());
			}
			// try {
			// // OperateLogUtil.record("test", "test", "teset13265");
			// } catch (NoConnection e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
		}
		connLogMap.remove(key);
	}
}
