/**
 * Copyright (c) 2013
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * All Rights Reserved.
 */
package com.tanry.framework.util.enums;

/**
 * created by cjt, 2016年8月18日 
 * 说明： BranchType枚举
 */
public enum BranchTypeEnum {
	
	/**
	 * 餐厅
	 */
	RESTAURANT("餐厅"), 
	/**
	 * 中央工厂
	 */
	CENTRALFACTORY("中央工厂"), 
	/**
	 * 物流中心
	 */
	LOGISTICSCENTER("物流中心"),
	/**
	 * 供应商
	 */
	SUPPLIER("供应商"),
	/**
	 * 加盟店
	 */
	FRANCHISEE("加盟店"),
	/**
	 * 外部订货方
	 */
	OUTERORDER("外部订货方"),
	
	/**
	 * 餐厅_外部订货方
	 */
	R_Out("餐厅_外部订货方"),
	/**
	 * 餐厅_加盟店_外部订货方
	 */
	R_F_O("餐厅_加盟店_外部订货方"),
	/**
	 * 餐厅_中央工厂_物流中心
	 */
	R_C_L("餐厅_中央工厂_物流中心"),
	/**
	 * 供应商_中央工厂
	 */
	S_Center("供应商_中央工厂");

    private String brandWord;
    private BranchTypeEnum(String _brandWord) {
        this.brandWord = _brandWord;
    }

    /**
     * 获取中文描述信息
     */
    public String getBrandWord() {
        return this.brandWord;
    }
    
    /**
     * 根据字符串获取BranchTypeEnum值，类似valueOf()，但
     * 在输入字符串为为空，或者字符串无法正常转换为BranchTypeEnum时，默认转换为RESTAURANT
     */
    public static BranchTypeEnum getEnum(String branchTypeStr) {
    	for(BranchTypeEnum m : BranchTypeEnum.values()) { 
    		if(m.toString().equals(branchTypeStr)){
    			return m;
    		}
    	}
    	
    	//默认转换为RESTAURANT
        return BranchTypeEnum.RESTAURANT;
    }
    
    public static void main(String args[]) {
    	   
        System.out.println("enum List:");
        for(BranchTypeEnum m : BranchTypeEnum.values()) { 
           System.out.println("enum " + m.toString() + "'s brandWord is:" + m.getBrandWord());
        }
        
        System.out.println("---------------------------------------------------");  
        BranchTypeEnum ret = BranchTypeEnum.getEnum("RESTAURANT"); 
        System.out.println("value of “RESTAURANT”'s enum is：" + ret.toString() + ret.getBrandWord());
        System.out.println(BranchTypeEnum.getEnum("LOGISTICSCENTER").getBrandWord());  
        
        boolean a = BranchTypeEnum.R_C_L.equals("R_C_L");
        System.out.println(a);
        boolean b = BranchTypeEnum.R_C_L.toString().equals("R_C_L");
        System.out.println(b);
        boolean c = "R_F_O".equals(BranchTypeEnum.R_F_O);
        System.out.println(c);
        boolean d = "R_F_O".equals(BranchTypeEnum.R_F_O.toString());
        System.out.println(d);
      }
}
