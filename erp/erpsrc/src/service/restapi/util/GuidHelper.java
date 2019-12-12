/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on 2016年3月11日 by cjt
 * Last edited on 2016年3月11日 by cjt
 */
package service.restapi.util;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 说明：根据时间生产唯一的ID
 */
public class GuidHelper
{
	private static String oldId = generateId();
	private static int oldNum = 0;
	
    /**
     * 由时间生成ID，精确到秒。
     * 如果一秒以内生成多个ID，则后缀加上数字保证每个ID唯一不同。
     */
    public static synchronized String generateId() {
        Format dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        Date rightNow = new Date();
        String idStr = dateFormat.format(rightNow);
        if(idStr.equals(oldId)){
        	idStr += oldNum;
        	oldNum++;
        }else{
        	oldNum=0;
        	oldId=idStr;
        }
        return idStr;
    }
    
    public static void main(String[] args)
    {
    	try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	for(int i=0;i<30;i++)
    	{
    		System.out.println(generateId());
    		try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }

}
