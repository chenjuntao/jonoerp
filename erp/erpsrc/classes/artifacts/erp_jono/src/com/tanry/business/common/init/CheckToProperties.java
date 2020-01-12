/**
 * Copyright (c) 2014
 * Tanry Electronic Technology Co., Ltd.
 * ChangSha, China
 * 
 * All Rights Reserved.
 * 
 * First created on Mar 30, 2015 by liyzh
 * Last edited on Mar 30, 2015 by liyzh
 * 
 * 说明： 初始化所有的配送方式数据
 */
package com.tanry.business.common.init;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckToProperties {
	public static void main(String[] args) {
		List<Map<String, Object>> checkLst = new ArrayList<Map<String, Object>>();
		Map<String, Object> content0 = new HashMap<String, Object>();
		content0.put("tableName", "D_T0_BUSINESS_DATE");
		content0.put("RESTAURANT_ID", "VARCHAR2,32");
		content0.put("BUSINESS_DATE", "DATE,7");
		checkLst.add(content0);

		Map<String, Object> content1 = new HashMap<String, Object>();
		content1.put("tableName", "D_T0_CONN_LOG");
		content1.put("ID", "VARCHAR2,36");
		content1.put("API", "VARCHAR2,200");
		content1.put("API_TYPE", "VARCHAR2,50");
		content1.put("CONSUME_TIME", "NUMBER,22");
		content1.put("CONN_IN_USE", "NUMBER,22");
		content1.put("OPERATOR_ID", "VARCHAR2,32");
		content1.put("OPERATOR_NAME", "VARCHAR2,100");
		content1.put("LOG_TIME", "DATE,7");
		checkLst.add(content1);

		Map<String, Object> content2 = new HashMap<String, Object>();
		content2.put("tableName", "D_T0_FORM_LOCK");
		content2.put("FORM_ID", "VARCHAR2,32");
		content2.put("LOCK_STATUS", "NUMBER,22");
		content2.put("LOCK_TIME", "DATE,7");
		checkLst.add(content2);

		Map<String, Object> content3 = new HashMap<String, Object>();
		content3.put("tableName", "D_T0_FORM_META");
		content3.put("FORM_NAME", "VARCHAR2,32");
		content3.put("FORM_TYPE", "VARCHAR2,32");
		content3.put("FORM_NOTE", "VARCHAR2,200");
		checkLst.add(content3);

		Map<String, Object> content4 = new HashMap<String, Object>();
		content4.put("tableName", "D_T0_MENU");
		content4.put("ID", "VARCHAR2,32");
		content4.put("NAME", "VARCHAR2,50");
		content4.put("IMAGE_NAME", "VARCHAR2,30");
		content4.put("PATH", "VARCHAR2,255");
		content4.put("PRIORITY", "NUMBER,22");
		content4.put("URL", "VARCHAR2,200");
		content4.put("DESCRIPTION", "VARCHAR2,200");
		content4.put("PARENT_ID", "VARCHAR2,32");
		checkLst.add(content4);

		Map<String, Object> content5 = new HashMap<String, Object>();
		content5.put("tableName", "D_T0_OPTION");
		content5.put("OPTION_KEY", "VARCHAR2,16");
		content5.put("OPTION_VALUE", "VARCHAR2,128");
		checkLst.add(content5);

		Map<String, Object> content6 = new HashMap<String, Object>();
		content6.put("tableName", "D_T0_PERMISSION");
		content6.put("ID", "VARCHAR2,32");
		content6.put("RESOURCE_TYPE", "VARCHAR2,50");
		content6.put("PRINCIPAL_TYPE", "VARCHAR2,50");
		content6.put("OPERATE", "VARCHAR2,50");
		content6.put("RESOURCE_ID", "VARCHAR2,32");
		content6.put("PRINCIPAL_ID", "VARCHAR2,32");
		checkLst.add(content6);

		Map<String, Object> content7 = new HashMap<String, Object>();
		content7.put("tableName", "D_T0_PRINT_TIMES");
		content7.put("FORM_ID", "VARCHAR2,32");
		content7.put("TIMES", "NUMBER,22");
		checkLst.add(content7);

		Map<String, Object> content8 = new HashMap<String, Object>();
		content8.put("tableName", "D_T0_QUARTZLOG");
		content8.put("JOB_DESC", "VARCHAR2,200");
		content8.put("BEGIN_TIME", "DATE,7");
		content8.put("END_TIME", "DATE,7");
		content8.put("ELAPSED_TIME", "NUMBER,22");
		content8.put("JOB_RESULT", "VARCHAR2,32");
		content8.put("IP_ADDR", "VARCHAR2,32");
		checkLst.add(content8);

		Map<String, Object> content9 = new HashMap<String, Object>();
		content9.put("tableName", "D_T0_ROLE");
		content9.put("ROLE_ID", "VARCHAR2,32");
		content9.put("ROLE_NAME", "VARCHAR2,50");
		content9.put("BRANCH_TYPE", "VARCHAR2,32");
		content9.put("DESCRIPTION", "VARCHAR2,200");
		checkLst.add(content9);

		Map<String, Object> content10 = new HashMap<String, Object>();
		content10.put("tableName", "D_T0_STATUS_FLOW");
		content10.put("WORK_FLOW", "VARCHAR2,32");
		content10.put("STEP", "VARCHAR2,32");
		content10.put("REF_STEP", "VARCHAR2,32");
		content10.put("IS_PREV_NEXT", "VARCHAR2,16");
		checkLst.add(content10);

		Map<String, Object> content11 = new HashMap<String, Object>();
		content11.put("tableName", "D_T0_USERTOBRANCH");
		content11.put("ID", "VARCHAR2,32");
		content11.put("IS_PRIMARY", "VARCHAR2,1");
		content11.put("WEIGHT", "NUMBER,22");
		content11.put("USER_ID", "VARCHAR2,32");
		content11.put("BRANCH_ID", "VARCHAR2,32");
		checkLst.add(content11);

		Map<String, Object> content12 = new HashMap<String, Object>();
		content12.put("tableName", "D_T0_USERTOROLE");
		content12.put("ID", "VARCHAR2,32");
		content12.put("WEIGHT", "NUMBER,22");
		content12.put("USER_ID", "VARCHAR2,32");
		content12.put("BRANCH_ID", "VARCHAR2,32");
		content12.put("ROLE_ID", "VARCHAR2,32");
		checkLst.add(content12);

		Map<String, Object> content13 = new HashMap<String, Object>();
		content13.put("tableName", "D_T1_ARRENGMENT_DETAIL");
		content13.put("FORM_ID", "VARCHAR2,32");
		content13.put("ITEM_ID", "VARCHAR2,32");
		content13.put("ITEM_NAME", "VARCHAR2,64");
		content13.put("ITEM_DIMENSION", "VARCHAR2,16");
		content13.put("ITEM_DIMENSION2", "VARCHAR2,16");
		content13.put("ITEM_SPECIFICATION", "VARCHAR2,64");
		content13.put("PRODUCE_COUNT", "NUMBER,22");
		content13.put("PRODUCE_COUNT2", "NUMBER,22");
		content13.put("WORK_ORDER_ID", "VARCHAR2,32");
		content13.put("WORKSHOP", "VARCHAR2,64");
		content13.put("PRODUCE_TIME", "DATE,7");
		content13.put("PRODUCTION_CYCLE", "NUMBER,22");
		content13.put("COMPLETE_TIME", "DATE,7");
		content13.put("NOTE", "VARCHAR2,200");
		checkLst.add(content13);

		Map<String, Object> content14 = new HashMap<String, Object>();
		content14.put("tableName", "D_T1_ARRENGMENT_HEADER");
		content14.put("FORM_ID", "VARCHAR2,32");
		content14.put("FORM_REF_ID", "VARCHAR2,32");
		content14.put("FORM_BRANCH_ID", "VARCHAR2,32");
		content14.put("FORM_MAKER_ID", "VARCHAR2,32");
		content14.put("FORM_MAKER", "VARCHAR2,64");
		content14.put("FORM_TIME", "DATE,7");
		content14.put("FORM_TIME_ACTUAL", "DATE,7");
		content14.put("AUDITOR_ID", "VARCHAR2,32");
		content14.put("AUDITOR", "VARCHAR2,64");
		content14.put("AUDIT_TIME", "DATE,7");
		content14.put("FORM_NOTE", "VARCHAR2,200");
		content14.put("PURCHASE_STATUS", "VARCHAR2,32");
		checkLst.add(content14);

		Map<String, Object> content15 = new HashMap<String, Object>();
		content15.put("tableName", "D_T1_CHECK_DETAIL");
		content15.put("FORM_ID", "VARCHAR2,32");
		content15.put("ITEM_ID", "VARCHAR2,32");
		content15.put("ITEM_NAME", "VARCHAR2,150");
		content15.put("ITEM_DIMENSION", "VARCHAR2,32");
		content15.put("ITEM_SPECIFICATION", "VARCHAR2,128");
		content15.put("ITEM_CATEGORY", "VARCHAR2,32");
		content15.put("SHELF_ID", "VARCHAR2,32");
		content15.put("CHECK_COUNT", "NUMBER,22");
		content15.put("THEORY_COUNT", "NUMBER,22");
		content15.put("EXPIRED_TIME", "DATE,7");
		content15.put("ITEM_ORDER", "NUMBER,22");
		content15.put("ITEM_STATUS", "VARCHAR2,32");
		checkLst.add(content15);

		Map<String, Object> content16 = new HashMap<String, Object>();
		content16.put("tableName", "D_T1_CHECK_HEADER");
		content16.put("FORM_ID", "VARCHAR2,32");
		content16.put("FORM_TYPE", "VARCHAR2,8");
		content16.put("CHECK_BATCH_ID", "VARCHAR2,32");
		content16.put("CHECK_BRANCH_ID", "VARCHAR2,32");
		content16.put("CHECK_BRANCH", "VARCHAR2,150");
		content16.put("CHECK_STORAGE_ID", "VARCHAR2,32");
		content16.put("CHECK_STORAGE", "VARCHAR2,64");
		content16.put("FORM_MAKER_ID", "VARCHAR2,32");
		content16.put("FORM_MAKER", "VARCHAR2,64");
		content16.put("FORM_TIME", "DATE,7");
		content16.put("FORM_TIME_ACTUAL", "DATE,7");
		content16.put("AUDITOR_ID", "VARCHAR2,32");
		content16.put("AUDITOR", "VARCHAR2,64");
		content16.put("AUDIT_TIME", "DATE,7");
		content16.put("FORM_NOTE", "VARCHAR2,200");
		content16.put("PRINT_COUNT", "NUMBER,22");
		content16.put("ALL_PAY_AMT", "NUMBER,22");
		content16.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content16.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content16);

		Map<String, Object> content17 = new HashMap<String, Object>();
		content17.put("tableName", "D_T1_CHECK_LOCK");
		content17.put("CHECK_BATCH_ID", "VARCHAR2,32");
		content17.put("CHECK_BATCH_STATUS", "VARCHAR2,32");
		content17.put("LOCK_BRANCH_ID", "VARCHAR2,32");
		content17.put("LOCK_BRANCH", "VARCHAR2,150");
		content17.put("LOCK_STORAGE_ID", "VARCHAR2,32");
		content17.put("LOCK_STORAGE", "VARCHAR2,64");
		content17.put("LOCK_MAN_ID", "VARCHAR2,32");
		content17.put("LOCK_MAN", "VARCHAR2,64");
		content17.put("LOCK_DATE", "DATE,7");
		content17.put("LOCK_TIME", "DATE,7");
		content17.put("LOCK_NOTE", "VARCHAR2,200");
		content17.put("ITEM_REPEATABLE", "VARCHAR2,16");
		content17.put("ITEM_CATEGORY", "VARCHAR2,600");
		checkLst.add(content17);

		Map<String, Object> content18 = new HashMap<String, Object>();
		content18.put("tableName", "D_T1_COLLECT_DETAIL");
		content18.put("FORM_ID", "VARCHAR2,32");
		content18.put("ITEM_ID", "VARCHAR2,32");
		content18.put("ITEM_NAME", "VARCHAR2,64");
		content18.put("ITEM_DIMENSION", "VARCHAR2,16");
		content18.put("ITEM_SPECIFICATION", "VARCHAR2,64");
		content18.put("ITEM_CATEGORY", "VARCHAR2,32");
		content18.put("ITEM_COUNT", "NUMBER,22");
		content18.put("ITEM_UNIT_PRICE", "NUMBER,22");
		content18.put("PAY_AMT", "NUMBER,22");
		content18.put("FORM_REF_ID", "VARCHAR2,512");
		content18.put("REQUESTER", "VARCHAR2,512");
		content18.put("PROVIDER_ID", "VARCHAR2,32");
		content18.put("PROVIDER", "VARCHAR2,64");
		checkLst.add(content18);

		Map<String, Object> content19 = new HashMap<String, Object>();
		content19.put("tableName", "D_T1_COLLECT_HEADER");
		content19.put("FORM_ID", "VARCHAR2,32");
		content19.put("BRANCH_ID", "VARCHAR2,32");
		content19.put("BRANCH", "VARCHAR2,64");
		content19.put("FORM_MAKER_ID", "VARCHAR2,32");
		content19.put("FORM_MAKER", "VARCHAR2,64");
		content19.put("FORM_TIME", "DATE,7");
		content19.put("AUDITOR_ID", "VARCHAR2,32");
		content19.put("AUDITOR", "VARCHAR2,64");
		content19.put("AUDIT_TIME", "DATE,7");
		content19.put("FORM_NOTE", "VARCHAR2,200");
		content19.put("ALL_PAY_AMT", "NUMBER,22");
		content19.put("MAX_PAY_ITEM", "VARCHAR2,64");
		checkLst.add(content19);

		Map<String, Object> content20 = new HashMap<String, Object>();
		content20.put("tableName", "D_T1_COLLECT_REF_FORM");
		content20.put("COLLECT_FORM_ID", "VARCHAR2,32");
		content20.put("REF_FORM_ID", "VARCHAR2,32");
		content20.put("ITEM_ID", "VARCHAR2,32");
		checkLst.add(content20);

		Map<String, Object> content21 = new HashMap<String, Object>();
		content21.put("tableName", "D_T1_INPUT_DETAIL");
		content21.put("FORM_ID", "VARCHAR2,32");
		content21.put("ITEM_ID", "VARCHAR2,32");
		content21.put("ITEM_NAME", "VARCHAR2,150");
		content21.put("ITEM_DIMENSION", "VARCHAR2,32");
		content21.put("ITEM_SPECIFICATION", "VARCHAR2,128");
		content21.put("ITEM_CATEGORY", "VARCHAR2,32");
		content21.put("ORDER_COUNT", "NUMBER,22");
		content21.put("RECEIVED_COUNT", "NUMBER,22");
		content21.put("RECEIVE_COUNT", "NUMBER,22");
		content21.put("DIFFERENT_COUNT", "NUMBER,22");
		content21.put("ITEM_UNIT_PRICE", "NUMBER,22");
		content21.put("PAY_AMT", "NUMBER,22");
		content21.put("RECEIVE_PRICE", "NUMBER,22");
		content21.put("RECEIVE_AMT", "NUMBER,22");
		content21.put("BATCH", "VARCHAR2,80");
		content21.put("EXPIRED_TIME", "DATE,7");
		checkLst.add(content21);

		Map<String, Object> content22 = new HashMap<String, Object>();
		content22.put("tableName", "D_T1_INPUT_HEADER");
		content22.put("FORM_ID", "VARCHAR2,32");
		content22.put("FORM_REF_ID", "VARCHAR2,32");
		content22.put("FORM_TYPE", "VARCHAR2,16");
		content22.put("INPUT_DEPARTMENT_ID", "VARCHAR2,32");
		content22.put("INPUT_DEPARTMENT", "VARCHAR2,150");
		content22.put("INPUTER_ID", "VARCHAR2,32");
		content22.put("INPUTER", "VARCHAR2,64");
		content22.put("STORAGE_ID", "VARCHAR2,32");
		content22.put("STORAGE", "VARCHAR2,64");
		content22.put("INPUT_TIME", "DATE,7");
		content22.put("PROVIDER_ID", "VARCHAR2,32");
		content22.put("PROVIDER", "VARCHAR2,150");
		content22.put("FORM_NOTE", "VARCHAR2,200");
		content22.put("AUDITOR_ID", "VARCHAR2,32");
		content22.put("AUDITOR", "VARCHAR2,64");
		content22.put("AUDIT_TIME", "DATE,7");
		content22.put("RETURN_STATUS", "VARCHAR2,32");
		content22.put("ALL_PAY_AMT", "NUMBER,22");
		content22.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content22.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content22);

		Map<String, Object> content23 = new HashMap<String, Object>();
		content23.put("tableName", "D_T1_LOSS_DETAIL");
		content23.put("FORM_ID", "VARCHAR2,32");
		content23.put("ITEM_ID", "VARCHAR2,32");
		content23.put("ITEM_NAME", "VARCHAR2,150");
		content23.put("ITEM_DIMENSION", "VARCHAR2,32");
		content23.put("ITEM_SPECIFICATION", "VARCHAR2,128");
		content23.put("ITEM_ID2", "VARCHAR2,32");
		content23.put("ITEM_NAME2", "VARCHAR2,64");
		content23.put("ITEM_DIMENSION2", "VARCHAR2,32");
		content23.put("ITEM_CATEGORY", "VARCHAR2,32");
		content23.put("ITEM_COUNT", "NUMBER,22");
		content23.put("ITEM_COUNT2", "NUMBER,22");
		content23.put("UNIT_PRICE", "NUMBER,22");
		content23.put("PAY_AMT", "NUMBER,22");
		content23.put("STORAGE_COUNT", "NUMBER,22");
		content23.put("EXPIRED_TIME", "DATE,7");
		content23.put("REASON", "VARCHAR2,200");
		checkLst.add(content23);

		Map<String, Object> content24 = new HashMap<String, Object>();
		content24.put("tableName", "D_T1_LOSS_HEADER");
		content24.put("FORM_ID", "VARCHAR2,32");
		content24.put("LOSS_TYPE", "VARCHAR2,8");
		content24.put("LOSS_BRANCH_ID", "VARCHAR2,32");
		content24.put("LOSS_BRANCH", "VARCHAR2,150");
		content24.put("STORAGE_ID", "VARCHAR2,32");
		content24.put("STORAGE", "VARCHAR2,64");
		content24.put("LOSS_MAN_ID", "VARCHAR2,32");
		content24.put("LOSS_MAN", "VARCHAR2,64");
		content24.put("LOSS_TIME", "DATE,7");
		content24.put("AUDITOR_ID", "VARCHAR2,32");
		content24.put("AUDITOR", "VARCHAR2,64");
		content24.put("AUDIT_TIME", "DATE,7");
		content24.put("PROCESSOR_ID", "VARCHAR2,32");
		content24.put("PROCESSOR", "VARCHAR2,64");
		content24.put("PROCESS_TIME", "DATE,7");
		content24.put("FORM_NOTE", "VARCHAR2,1024");
		content24.put("ALL_PAY_AMT", "NUMBER,22");
		content24.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content24.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content24);

		Map<String, Object> content25 = new HashMap<String, Object>();
		content25.put("tableName", "D_T1_PACKING_DETAIL");
		content25.put("FORM_ID", "VARCHAR2,32");
		content25.put("BRANCH_ID", "VARCHAR2,32");
		content25.put("BOX_ID", "VARCHAR2,32");
		content25.put("BOX_NAME", "VARCHAR2,64");
		content25.put("ITEM_ID", "VARCHAR2,32");
		content25.put("ITEM_NAME", "VARCHAR2,64");
		content25.put("ITEM_DIMENSION", "VARCHAR2,16");
		content25.put("UNIT_VOLUME", "NUMBER,22");
		content25.put("UNIT_WEIGHT", "NUMBER,22");
		content25.put("ITEM_COUNT", "NUMBER,22");
		content25.put("VOLUME", "NUMBER,22");
		content25.put("WEIGHT", "NUMBER,22");
		content25.put("ITEM_CATEGORY", "VARCHAR2,32");
		checkLst.add(content25);

		Map<String, Object> content26 = new HashMap<String, Object>();
		content26.put("tableName", "D_T1_PACKING_HEADER");
		content26.put("FORM_ID", "VARCHAR2,32");
		content26.put("PACKING_BRANCH_ID", "VARCHAR2,32");
		content26.put("FORM_REF_ID", "VARCHAR2,32");
		content26.put("FORM_MAKER_ID", "VARCHAR2,32");
		content26.put("FORM_MAKER", "VARCHAR2,64");
		content26.put("FORM_TIME", "DATE,7");
		content26.put("FORM_TIME_ACTUAL", "DATE,7");
		content26.put("PACKING_TIME", "DATE,7");
		content26.put("FORM_NOTE", "VARCHAR2,200");
		checkLst.add(content26);

		Map<String, Object> content27 = new HashMap<String, Object>();
		content27.put("tableName", "D_T1_PICKING_DETAIL");
		content27.put("FORM_ID", "VARCHAR2,32");
		content27.put("ITEM_ID", "VARCHAR2,32");
		content27.put("ITEM_NAME", "VARCHAR2,64");
		content27.put("ITEM_DIMENSION", "VARCHAR2,16");
		content27.put("ITEM_CATEGORY", "VARCHAR2,32");
		content27.put("ITEM_COUNT", "NUMBER,22");
		content27.put("BRANCH_ID", "VARCHAR2,32");
		checkLst.add(content27);

		Map<String, Object> content28 = new HashMap<String, Object>();
		content28.put("tableName", "D_T1_PICKING_HEADER");
		content28.put("FORM_ID", "VARCHAR2,32");
		content28.put("PICKING_BRANCH_ID", "VARCHAR2,32");
		content28.put("STORAGE_ID", "VARCHAR2,32");
		content28.put("STORAGE", "VARCHAR2,64");
		content28.put("FORM_MAKER_ID", "VARCHAR2,32");
		content28.put("FORM_MAKER", "VARCHAR2,64");
		content28.put("FORM_TIME", "DATE,7");
		content28.put("FORM_TIME_ACTUAL", "DATE,7");
		content28.put("PICKING_MAN_ID", "VARCHAR2,32");
		content28.put("PICKING_MAN", "VARCHAR2,64");
		content28.put("PICKING_TIME", "DATE,7");
		content28.put("AUDITOR_ID", "VARCHAR2,32");
		content28.put("AUDITOR", "VARCHAR2,64");
		content28.put("AUDIT_TIME", "DATE,7");
		content28.put("PACK_STATUS", "VARCHAR2,32");
		content28.put("FORM_NOTE", "VARCHAR2,200");
		checkLst.add(content28);

		Map<String, Object> content29 = new HashMap<String, Object>();
		content29.put("tableName", "D_T1_PICKING_REF");
		content29.put("FORM_ID", "VARCHAR2,32");
		content29.put("FORM_REF_ID", "VARCHAR2,32");
		content29.put("ITEM_ID", "VARCHAR2,32");
		content29.put("BRANCH_ID", "VARCHAR2,32");
		content29.put("ITEM_COUNT", "NUMBER,22");
		checkLst.add(content29);

		Map<String, Object> content30 = new HashMap<String, Object>();
		content30.put("tableName", "D_T1_PRICE_ADJUST_DETAIL");
		content30.put("FORM_ID", "VARCHAR2,32");
		content30.put("ITEM_ID", "VARCHAR2,32");
		content30.put("ITEM_NAME", "VARCHAR2,150");
		content30.put("ITEM_DIMENSION", "VARCHAR2,32");
		content30.put("ITEM_SPECIFICATION", "VARCHAR2,128");
		content30.put("ITEM_PACKAGER", "NUMBER,22");
		content30.put("OLD_PRICE", "NUMBER,22");
		content30.put("NEW_PRICE", "NUMBER,22");
		checkLst.add(content30);

		Map<String, Object> content31 = new HashMap<String, Object>();
		content31.put("tableName", "D_T1_PRICE_ADJUST_HEADER");
		content31.put("ADJUST_SCOPE", "VARCHAR2,32");
		content31.put("SUPPLIER_ID", "VARCHAR2,32");
		content31.put("EFFECT_TYPE", "VARCHAR2,32");
		content31.put("EFFECT_TIME", "DATE,7");
		content31.put("NOTE", "VARCHAR2,32");
		content31.put("FORM_MAKER", "VARCHAR2,32");
		content31.put("MAKE_TIME", "DATE,7");
		content31.put("AUDITOR", "VARCHAR2,32");
		content31.put("AUDIT_TIME", "DATE,7");
		content31.put("FORM_NOTE", "VARCHAR2,200");
		content31.put("FORM_ID", "VARCHAR2,32");
		content31.put("FORM_TIME", "DATE,7");
		content31.put("ADJUST_TYPE", "VARCHAR2,32");
		checkLst.add(content31);

		Map<String, Object> content32 = new HashMap<String, Object>();
		content32.put("tableName", "D_T1_PURCHASING_DETAIL");
		content32.put("FORM_ID", "VARCHAR2,32");
		content32.put("ITEM_ID", "VARCHAR2,32");
		content32.put("ITEM_NAME", "VARCHAR2,150");
		content32.put("ITEM_DIMENSION", "VARCHAR2,32");
		content32.put("ITEM_SPECIFICATION", "VARCHAR2,128");
		content32.put("ITEM_CATEGORY", "VARCHAR2,32");
		content32.put("ITEM_COUNT", "NUMBER,22");
		content32.put("ITEM_UNIT_PRICE", "NUMBER,22");
		content32.put("PAY_AMT", "NUMBER,22");
		content32.put("RECEIVE_PRICE", "NUMBER,22");
		content32.put("RECEIVE_AMT", "NUMBER,22");
		content32.put("RECEIVER_ID", "VARCHAR2,32");
		content32.put("RECEIVER", "VARCHAR2,64");
		content32.put("EXPIRED_TIME", "DATE,7");
		checkLst.add(content32);

		Map<String, Object> content33 = new HashMap<String, Object>();
		content33.put("tableName", "D_T1_PURCHASING_HEADER");
		content33.put("FORM_ID", "VARCHAR2,32");
		content33.put("FORM_REF_ID", "VARCHAR2,32");
		content33.put("PROVIDER_ID", "VARCHAR2,32");
		content33.put("PROVIDER", "VARCHAR2,150");
		content33.put("REQUESTER_ID", "VARCHAR2,32");
		content33.put("REQUESTER", "VARCHAR2,150");
		content33.put("RECEIVER_ID", "VARCHAR2,32");
		content33.put("RECEIVER", "VARCHAR2,150");
		content33.put("RECEIVE_ADDRESS", "VARCHAR2,100");
		content33.put("STORAGE_ID", "VARCHAR2,32");
		content33.put("STORAGE", "VARCHAR2,64");
		content33.put("RECEIVE_TIME", "DATE,7");
		content33.put("FORM_MAKER_ID", "VARCHAR2,32");
		content33.put("FORM_MAKER", "VARCHAR2,64");
		content33.put("FORM_TIME", "DATE,7");
		content33.put("FORM_TIME_ACTUAL", "DATE,7");
		content33.put("AUDITOR_ID", "VARCHAR2,32");
		content33.put("AUDITOR", "VARCHAR2,64");
		content33.put("AUDIT_TIME", "DATE,7");
		content33.put("FORM_NOTE", "VARCHAR2,200");
		content33.put("DELIVERY_TYPE", "VARCHAR2,64");
		content33.put("PLAN_STATUS", "VARCHAR2,32");
		content33.put("OUT_STATUS", "VARCHAR2,32");
		content33.put("ALL_PAY_AMT", "NUMBER,22");
		content33.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content33.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content33);

		Map<String, Object> content34 = new HashMap<String, Object>();
		content34.put("tableName", "D_T1_PURCHASING_MAPPING");
		content34.put("BIG_FORM_ID", "VARCHAR2,32");
		content34.put("SMALL_FORM_ID", "VARCHAR2,32");
		checkLst.add(content34);

		Map<String, Object> content35 = new HashMap<String, Object>();
		content35.put("tableName", "D_T1_REQUEST_DETAIL");
		content35.put("FORM_ID", "VARCHAR2,32");
		content35.put("ITEM_ID", "VARCHAR2,32");
		content35.put("INTL_BAR_CODE", "VARCHAR2,32");
		content35.put("ITEM_NAME", "VARCHAR2,150");
		content35.put("ITEM_CATEGORY", "VARCHAR2,32");
		content35.put("ITEM_DIMENSION", "VARCHAR2,32");
		content35.put("ITEM_SPECIFICATION", "VARCHAR2,128");
		content35.put("AMT_TT_CNY1", "NUMBER,22");
		content35.put("AMT_TT_CNY2", "NUMBER,22");
		content35.put("AMT_TT_CNY3", "NUMBER,22");
		content35.put("REQUIRE_COUNT1", "NUMBER,22");
		content35.put("REQUIRE_COUNT2", "NUMBER,22");
		content35.put("REQUIRE_COUNT3", "NUMBER,22");
		content35.put("INVENTORY", "NUMBER,22");
		content35.put("SUGGEST_COUNT1", "NUMBER,22");
		content35.put("SUGGEST_COUNT2", "NUMBER,22");
		content35.put("SUGGEST_COUNT3", "NUMBER,22");
		content35.put("ORDER_COUNT", "NUMBER,22");
		content35.put("ITEM_UNIT_PRICE", "NUMBER,22");
		content35.put("PAY_AMT", "NUMBER,22");
		content35.put("DELIVERY_TYPE", "VARCHAR2,16");
		checkLst.add(content35);

		Map<String, Object> content36 = new HashMap<String, Object>();
		content36.put("tableName", "D_T1_REQUEST_HEADER");
		content36.put("DELAY_PREDICT", "NUMBER,22");
		content36.put("PURCHASE_PREDICT", "NUMBER,22");
		content36.put("SAFETY_STOCK", "NUMBER,22");
		content36.put("SELL_PREDICT", "NUMBER,22");
		content36.put("ALL_PAY_AMT", "NUMBER,22");
		content36.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content36.put("ARRIVE_PERIOD", "NUMBER,22");
		content36.put("DELIVERY_TYPE", "VARCHAR2,16");
		content36.put("TEMPLATE_ID", "VARCHAR2,32");
		content36.put("TEMPLATE_NAME", "VARCHAR2,64");
		content36.put("ISADDFORM", "VARCHAR2,1");
		content36.put("FORM_ID", "VARCHAR2,32");
		content36.put("FORM_TYPE", "VARCHAR2,32");
		content36.put("BUYER_ID", "VARCHAR2,32");
		content36.put("BUYER_NAME", "VARCHAR2,150");
		content36.put("STORAGE_ID", "VARCHAR2,32");
		content36.put("STORAGE", "VARCHAR2,64");
		content36.put("BUYER_ADDRESS", "VARCHAR2,100");
		content36.put("PROVIDER_ID", "VARCHAR2,32");
		content36.put("PROVIDER", "VARCHAR2,150");
		content36.put("RECEIVE_TIME", "DATE,7");
		content36.put("FORM_MAKER", "VARCHAR2,64");
		content36.put("FORM_TIME", "DATE,7");
		content36.put("FORM_TIME_ACTUAL", "DATE,7");
		content36.put("AUDITOR", "VARCHAR2,64");
		content36.put("AUDIT_TIME", "DATE,7");
		content36.put("PURCHASE_STATUS", "VARCHAR2,32");
		content36.put("SHIPPING_STATUS", "VARCHAR2,32");
		content36.put("FORM_NOTE", "VARCHAR2,200");
		content36.put("REF_DATE_START1", "DATE,7");
		content36.put("REF_DATE_END1", "DATE,7");
		content36.put("REF_DATE_START2", "DATE,7");
		content36.put("REF_DATE_END2", "DATE,7");
		content36.put("REF_DATE_START3", "DATE,7");
		content36.put("REF_DATE_END3", "DATE,7");
		checkLst.add(content36);

		Map<String, Object> content37 = new HashMap<String, Object>();
		content37.put("tableName", "D_T1_REQUISITION_DETAIL");
		content37.put("FORM_ID", "VARCHAR2,32");
		content37.put("ITEM_ID", "VARCHAR2,32");
		content37.put("ITEM_NAME", "VARCHAR2,64");
		content37.put("ITEM_DIMENSION", "VARCHAR2,16");
		content37.put("ITEM_SPECIFICATION", "VARCHAR2,64");
		content37.put("ITEM_COUNT", "NUMBER,22");
		content37.put("RECEIVE_COUNT", "NUMBER,22");
		content37.put("DIFFERENT_COUNT", "NUMBER,22");
		content37.put("EXPIRED_TIME", "DATE,7");
		checkLst.add(content37);

		Map<String, Object> content38 = new HashMap<String, Object>();
		content38.put("tableName", "D_T1_REQUISITION_HEADER");
		content38.put("FORM_ID", "VARCHAR2,32");
		content38.put("FORM_TYPE", "VARCHAR2,32");
		content38.put("FORM_REF_ID", "VARCHAR2,32");
		content38.put("WORKSHOP", "VARCHAR2,64");
		content38.put("STORAGE_ID", "VARCHAR2,32");
		content38.put("STORAGE", "VARCHAR2,64");
		content38.put("FORM_BRANCH_ID", "VARCHAR2,32");
		content38.put("FORM_MAKER_ID", "VARCHAR2,32");
		content38.put("FORM_MAKER", "VARCHAR2,64");
		content38.put("FORM_TIME", "DATE,7");
		content38.put("FORM_TIME_ACTUAL", "DATE,7");
		content38.put("AUDITOR_ID", "VARCHAR2,32");
		content38.put("AUDITOR", "VARCHAR2,64");
		content38.put("AUDIT_TIME", "DATE,7");
		content38.put("FORM_NOTE", "VARCHAR2,200");
		content38.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content38);

		Map<String, Object> content39 = new HashMap<String, Object>();
		content39.put("tableName", "D_T1_RETURN_GOODS_DETAIL");
		content39.put("FORM_ID", "VARCHAR2,32");
		content39.put("FORM_REF_ID", "VARCHAR2,32");
		content39.put("ITEM_ID", "VARCHAR2,32");
		content39.put("RETURN_COUNT", "NUMBER,22");
		content39.put("RETURN_PAY_AMT", "NUMBER,22");
		content39.put("RETURN_REASON", "VARCHAR2,200");
		checkLst.add(content39);

		Map<String, Object> content40 = new HashMap<String, Object>();
		content40.put("tableName", "D_T1_RETURN_GOODS_HEADER");
		content40.put("FORM_ID", "VARCHAR2,32");
		content40.put("FORM_REF_ID", "VARCHAR2,32");
		content40.put("RETURNER_ID", "VARCHAR2,32");
		content40.put("RETURNER", "VARCHAR2,64");
		content40.put("RETURN_TIME", "DATE,7");
		content40.put("FORM_NOTE", "VARCHAR2,200");
		content40.put("ALL_PAY_AMT", "NUMBER,22");
		content40.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content40.put("AUDIT_TIME", "DATE,7");
		content40.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content40);

		Map<String, Object> content41 = new HashMap<String, Object>();
		content41.put("tableName", "D_T1_SAMPLE_INFO");
		content41.put("ID", "VARCHAR2,36");
		content41.put("PRODUCT_NAME", "VARCHAR2,64");
		content41.put("MINIMUM_ORDER_QUANTITY", "NUMBER,22");
		content41.put("PRODUCE_PLACE", "VARCHAR2,100");
		content41.put("ORDER_CYCLE", "NUMBER,22");
		content41.put("USE_CYCLE", "NUMBER,22");
		content41.put("QUALIFICATION_PIC", "VARCHAR2,32");
		content41.put("SAMPLE_PIC", "VARCHAR2,32");
		content41.put("PRICE", "NUMBER,22");
		content41.put("GROSS_PRICE", "NUMBER,22");
		content41.put("GROSS_WEIGHT", "NUMBER,22");
		content41.put("NET_WEIGHT", "NUMBER,22");
		content41.put("NET_RATIO", "NUMBER,22");
		content41.put("SHELF_LIFE", "NUMBER,22");
		content41.put("DELIVERY_TIME", "NUMBER,22");
		content41.put("ACCPTANCE_CRITERIA", "VARCHAR2,300");
		content41.put("SUPPLIER", "VARCHAR2,100");
		content41.put("CREATE_USER_ID", "VARCHAR2,32");
		content41.put("CREATE_TIME", "DATE,7");
		checkLst.add(content41);

		Map<String, Object> content42 = new HashMap<String, Object>();
		content42.put("tableName", "D_T1_SELF_SEMIS_DETAIL");
		content42.put("FORM_ID", "VARCHAR2,32");
		content42.put("ITEM_ID", "VARCHAR2,32");
		content42.put("ITEM_NAME", "VARCHAR2,150");
		content42.put("ITEM_DIMENSION", "VARCHAR2,32");
		content42.put("ITEM_SPECIFICATION", "VARCHAR2,128");
		content42.put("ITEM_CATEGORY", "VARCHAR2,32");
		content42.put("ITEM_COUNT_PLAN", "NUMBER,22");
		content42.put("ITEM_COUNT_ACTUAL", "NUMBER,22");
		checkLst.add(content42);

		Map<String, Object> content43 = new HashMap<String, Object>();
		content43.put("tableName", "D_T1_SELF_SEMIS_HEADER");
		content43.put("MAIN_ITEM", "VARCHAR2,200");
		content43.put("AUDIT_TIME_ACTUAL", "DATE,7");
		content43.put("FORM_ID", "VARCHAR2,32");
		content43.put("BRANCH_ID", "VARCHAR2,32");
		content43.put("BRANCH", "VARCHAR2,150");
		content43.put("CREATOR_MAN_ID", "VARCHAR2,32");
		content43.put("FORM_MAKER", "VARCHAR2,64");
		content43.put("FORM_TIME", "DATE,7");
		content43.put("FORM_TIME_ACTUAL", "DATE,7");
		content43.put("AUDITOR_ID", "VARCHAR2,32");
		content43.put("AUDITOR", "VARCHAR2,64");
		content43.put("AUDIT_TIME", "DATE,7");
		content43.put("FORM_NOTE", "VARCHAR2,1024");
		checkLst.add(content43);

		Map<String, Object> content44 = new HashMap<String, Object>();
		content44.put("tableName", "D_T1_SHIPPING_ANTIAUDIT_DETAIL");
		content44.put("FORM_REF_ID", "VARCHAR2,32");
		content44.put("ITEM_ID", "VARCHAR2,32");
		content44.put("ANTIAUDIT_RECEIVE_COUNT", "NUMBER,22");
		content44.put("ANTIAUDIT_PAY_AMT", "NUMBER,22");
		checkLst.add(content44);

		Map<String, Object> content45 = new HashMap<String, Object>();
		content45.put("tableName", "D_T1_SHIPPING_ANTIAUDIT_HEADER");
		content45.put("FORM_REF_ID", "VARCHAR2,32");
		content45.put("ANTIAUDITOR_ID", "VARCHAR2,32");
		content45.put("ANTIAUDITOR", "VARCHAR2,64");
		content45.put("ANTIAUDIT_BRANCH_ID", "VARCHAR2,32");
		content45.put("ANTIAUDIT_BRANCH", "VARCHAR2,64");
		content45.put("ANTIAUDIT_TIME", "DATE,7");
		content45.put("FORM_NOTE", "VARCHAR2,200");
		content45.put("AUDIT_TIME", "DATE,7");
		content45.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content45);

		Map<String, Object> content46 = new HashMap<String, Object>();
		content46.put("tableName", "D_T1_SHIPPING_DETAIL");
		content46.put("FORM_ID", "VARCHAR2,32");
		content46.put("ITEM_ID", "VARCHAR2,32");
		content46.put("ITEM_NAME", "VARCHAR2,150");
		content46.put("ITEM_DIMENSION", "VARCHAR2,32");
		content46.put("ITEM_SPECIFICATION", "VARCHAR2,150");
		content46.put("ITEM_CATEGORY", "VARCHAR2,32");
		content46.put("PACKAGING_FACTOR", "NUMBER,22");
		content46.put("PACKAGING_UNIT", "VARCHAR2,8");
		content46.put("PACKAGING_COUNT", "NUMBER,22");
		content46.put("REQUEST_COUNT", "NUMBER,22");
		content46.put("SHIPPING_COUNT", "NUMBER,22");
		content46.put("DELIVERY_COUNT", "NUMBER,22");
		content46.put("RECEIVE_COUNT", "NUMBER,22");
		content46.put("DIFFERENT_COUNT", "NUMBER,22");
		content46.put("DIFFERENT_REASON", "VARCHAR2,100");
		content46.put("ITEM_UNIT_PRICE", "NUMBER,22");
		content46.put("PAY_AMT", "NUMBER,22");
		content46.put("EXPIRED_TIME", "DATE,7");
		checkLst.add(content46);

		Map<String, Object> content47 = new HashMap<String, Object>();
		content47.put("tableName", "D_T1_SHIPPING_HEADER");
		content47.put("FORM_ID", "VARCHAR2,32");
		content47.put("FORM_REF_ID", "VARCHAR2,32");
		content47.put("FORM_TYPE", "VARCHAR2,32");
		content47.put("PROVIDER_ID", "VARCHAR2,32");
		content47.put("PROVIDER", "VARCHAR2,150");
		content47.put("OUT_STORAGE_ID", "VARCHAR2,32");
		content47.put("OUT_STORAGE", "VARCHAR2,64");
		content47.put("RECEIVE_TIME", "DATE,7");
		content47.put("REQUESTER_ID", "VARCHAR2,32");
		content47.put("REQUESTER", "VARCHAR2,150");
		content47.put("REQUEST_ADDRESS", "VARCHAR2,100");
		content47.put("IN_STORAGE_ID", "VARCHAR2,32");
		content47.put("IN_STORAGE", "VARCHAR2,64");
		content47.put("FORM_MAKER_ID", "VARCHAR2,32");
		content47.put("FORM_MAKER", "VARCHAR2,64");
		content47.put("FORM_TIME", "DATE,7");
		content47.put("FORM_TIME_ACTUAL", "DATE,7");
		content47.put("AUDITOR_ID", "VARCHAR2,32");
		content47.put("AUDITOR", "VARCHAR2,64");
		content47.put("AUDIT_TIME", "DATE,7");
		content47.put("INPUTER_ID", "VARCHAR2,32");
		content47.put("INPUTER", "VARCHAR2,64");
		content47.put("INPUT_TIME", "DATE,7");
		content47.put("PICK_STATUS", "VARCHAR2,32");
		content47.put("INPUT_STATUS", "VARCHAR2,32");
		content47.put("DIFFERENT_STATUS", "VARCHAR2,32");
		content47.put("RETURN_STATUS", "VARCHAR2,32");
		content47.put("ANTI_STATUS", "VARCHAR2,32");
		content47.put("ON_STATUS", "VARCHAR2,32");
		content47.put("FORM_NOTE", "VARCHAR2,200");
		content47.put("ALL_PAY_AMT", "NUMBER,22");
		content47.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content47.put("AUDIT_TIME_ACTUAL", "DATE,7");
		content47.put("INPUT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content47);

		Map<String, Object> content48 = new HashMap<String, Object>();
		content48.put("tableName", "D_T1_STATEMENT_DETAIL");
		content48.put("FORM_ID", "VARCHAR2,32");
		content48.put("SUB_FORM_ID", "VARCHAR2,32");
		content48.put("FORM_REF_ID", "VARCHAR2,32");
		content48.put("ALL_PAY_AMT", "NUMBER,22");
		content48.put("FORM_OPERATE_ID", "VARCHAR2,32");
		content48.put("FORM_OPERATE", "VARCHAR2,64");
		content48.put("FORM_OPERATE_TIME", "DATE,7");
		content48.put("FORM_NOTE", "VARCHAR2,200");
		checkLst.add(content48);

		Map<String, Object> content49 = new HashMap<String, Object>();
		content49.put("tableName", "D_T1_STATEMENT_HEADER");
		content49.put("FORM_ID", "VARCHAR2,32");
		content49.put("FORM_TYPE", "VARCHAR2,10");
		content49.put("PROVIDER_ID", "VARCHAR2,32");
		content49.put("PROVIDER", "VARCHAR2,96");
		content49.put("BRANCH_ID", "VARCHAR2,32");
		content49.put("BRANCH_NAME", "VARCHAR2,64");
		content49.put("FORM_MAKER_ID", "VARCHAR2,32");
		content49.put("FORM_MAKER", "VARCHAR2,64");
		content49.put("FORM_TIME", "DATE,7");
		content49.put("FORM_TIME_ACTUAL", "DATE,7");
		content49.put("AUDITOR_ID", "VARCHAR2,32");
		content49.put("AUDITOR", "VARCHAR2,64");
		content49.put("AUDIT_TIME", "DATE,7");
		content49.put("ALL_PAY_AMT", "NUMBER,22");
		content49.put("START_DATE", "DATE,7");
		content49.put("END_DATE", "DATE,7");
		content49.put("FORM_NOTE", "VARCHAR2,200");
		checkLst.add(content49);

		Map<String, Object> content50 = new HashMap<String, Object>();
		content50.put("tableName", "D_T1_TEMPLATE_ITEM");
		content50.put("TEMPLATE_ID", "VARCHAR2,32");
		content50.put("ITEM_ID", "VARCHAR2,32");
		content50.put("ITEM_ORDER", "NUMBER,22");
		checkLst.add(content50);

		Map<String, Object> content51 = new HashMap<String, Object>();
		content51.put("tableName", "D_T1_TEMPLATE_META");
		content51.put("TEMPLATE_ID", "VARCHAR2,32");
		content51.put("TEMPLATE_NAME", "VARCHAR2,64");
		content51.put("TEMPLATE_TYPE", "VARCHAR2,32");
		content51.put("BRANCH_ID", "VARCHAR2,32");
		content51.put("CATEGORY_ID", "VARCHAR2,100");
		content51.put("TEMPLATE_NOTE", "VARCHAR2,200");
		content51.put("ARRIVE_PERIOD", "NUMBER,22");
		content51.put("DELIVERY_REGION", "VARCHAR2,32");
		content51.put("DELIVERY_TYPE", "VARCHAR2,16");
		checkLst.add(content51);

		Map<String, Object> content52 = new HashMap<String, Object>();
		content52.put("tableName", "D_T1_TRANSFER_DETAIL");
		content52.put("FORM_ID", "VARCHAR2,32");
		content52.put("ITEM_ID", "VARCHAR2,32");
		content52.put("ITEM_NAME", "VARCHAR2,150");
		content52.put("ITEM_DIMENSION", "VARCHAR2,32");
		content52.put("ITEM_SPECIFICATION", "VARCHAR2,150");
		content52.put("ITEM_CATEGORY", "VARCHAR2,32");
		content52.put("APPLY_COUNT", "NUMBER,22");
		content52.put("ACTUAL_COUNT", "NUMBER,22");
		content52.put("DIFFERENT_COUNT", "NUMBER,22");
		content52.put("UNIT_PRICE", "NUMBER,22");
		content52.put("PAY_AMT", "NUMBER,22");
		content52.put("EXPIRED_TIME", "DATE,7");
		checkLst.add(content52);

		Map<String, Object> content53 = new HashMap<String, Object>();
		content53.put("tableName", "D_T1_TRANSFER_HEADER");
		content53.put("FORM_ID", "VARCHAR2,32");
		content53.put("IN_BRANCH_ID", "VARCHAR2,32");
		content53.put("IN_BRANCH", "VARCHAR2,150");
		content53.put("IN_STORAGE_ID", "VARCHAR2,32");
		content53.put("IN_STORAGE", "VARCHAR2,64");
		content53.put("OUT_BRANCH_ID", "VARCHAR2,32");
		content53.put("OUT_BRANCH", "VARCHAR2,150");
		content53.put("OUT_STORAGE_ID", "VARCHAR2,32");
		content53.put("OUT_STORAGE", "VARCHAR2,64");
		content53.put("FROM_MAKER_ID", "VARCHAR2,32");
		content53.put("FROM_MAKER", "VARCHAR2,64");
		content53.put("FORM_TIME", "DATE,7");
		content53.put("FORM_TIME_ACTUAL", "DATE,7");
		content53.put("AUDITOR_ID", "VARCHAR2,32");
		content53.put("AUDITOR", "VARCHAR2,64");
		content53.put("AUDIT_TIME", "DATE,7");
		content53.put("CONFIRMER_ID", "VARCHAR2,32");
		content53.put("CONFIRMER", "VARCHAR2,64");
		content53.put("CONFIRM_TIME", "DATE,7");
		content53.put("OUT_MAN_ID", "VARCHAR2,32");
		content53.put("OUT_MAN", "VARCHAR2,64");
		content53.put("OUT_TIME", "DATE,7");
		content53.put("FORM_NOTE", "VARCHAR2,200");
		content53.put("ALL_PAY_AMT", "NUMBER,22");
		content53.put("MAX_PAY_ITEM", "VARCHAR2,150");
		content53.put("AUDIT_TIME_ACTUAL", "DATE,7");
		checkLst.add(content53);

		Map<String, Object> content54 = new HashMap<String, Object>();
		content54.put("tableName", "D_T1_WORKORDER_ITEM");
		content54.put("FORM_ID", "VARCHAR2,32");
		content54.put("ITEM_ID", "VARCHAR2,32");
		content54.put("ITEM_NAME", "VARCHAR2,64");
		content54.put("ITEM_DIMENSION", "VARCHAR2,16");
		content54.put("ITEM_SPECIFICATION", "VARCHAR2,64");
		content54.put("ITEM_COUNT", "NUMBER,22");
		content54.put("RECEIVED_COUNT", "NUMBER,22");
		content54.put("RETURNED_COUNT", "NUMBER,22");
		checkLst.add(content54);

		Map<String, Object> content55 = new HashMap<String, Object>();
		content55.put("tableName", "D_T1_WORK_ORDER_DETAIL");
		content55.put("FORM_ID", "VARCHAR2,32");
		content55.put("PRODUCTION_NAME", "VARCHAR2,32");
		content55.put("PRODUCTION_STEP", "NUMBER,22");
		content55.put("PRODUCTION_TIME", "DATE,7");
		content55.put("PRODUCTION_COUNT", "NUMBER,22");
		content55.put("PRODUCTION_MAN", "VARCHAR2,64");
		content55.put("PRODUCTION_NOTE", "VARCHAR2,200");
		content55.put("IS_COMPLETED", "NUMBER,22");
		checkLst.add(content55);

		Map<String, Object> content56 = new HashMap<String, Object>();
		content56.put("tableName", "D_T1_WORK_ORDER_HEADER");
		content56.put("FORM_ID", "VARCHAR2,32");
		content56.put("ITEM_ID", "VARCHAR2,32");
		content56.put("ITEM_NAME", "VARCHAR2,64");
		content56.put("ITEM_DIMENSION", "VARCHAR2,16");
		content56.put("ITEM_DIMENSION2", "VARCHAR2,16");
		content56.put("ITEM_SPECIFICATION", "VARCHAR2,64");
		content56.put("ITEM_COUNT", "NUMBER,22");
		content56.put("ITEM_COUNT2", "NUMBER,22");
		content56.put("ACTUAL_COUNT", "NUMBER,22");
		content56.put("WORKSHOP", "VARCHAR2,64");
		content56.put("FORM_BRANCH_ID", "VARCHAR2,32");
		content56.put("FORM_MAKER_ID", "VARCHAR2,32");
		content56.put("FORM_MAKER", "VARCHAR2,64");
		content56.put("FORM_TIME", "DATE,7");
		content56.put("FORM_TIME_ACTUAL", "DATE,7");
		content56.put("AUDITOR_ID", "VARCHAR2,32");
		content56.put("AUDITOR", "VARCHAR2,64");
		content56.put("AUDIT_TIME", "DATE,7");
		content56.put("RECEIVED_STATUS", "VARCHAR2,32");
		content56.put("INPUTED_COUNT", "NUMBER,22");
		content56.put("BATCH_FLAG", "VARCHAR2,1");
		content56.put("FORM_NOTE", "VARCHAR2,200");
		checkLst.add(content56);

		Map<String, Object> content57 = new HashMap<String, Object>();
		content57.put("tableName", "D_T2_BOX_TYPE");
		content57.put("TYPE_ID", "VARCHAR2,32");
		content57.put("TYPE_NAME", "VARCHAR2,64");
		content57.put("VOLUME", "NUMBER,22");
		content57.put("WEIGHT", "NUMBER,22");
		content57.put("ENABLED", "NUMBER,22");
		checkLst.add(content57);

		Map<String, Object> content58 = new HashMap<String, Object>();
		content58.put("tableName", "D_T2_BRANCH");
		content58.put("BRANCH_ID", "VARCHAR2,32");
		content58.put("BRANCH_NAME", "VARCHAR2,150");
		content58.put("QUERY_CODE", "VARCHAR2,32");
		content58.put("BRANCH_ADDRESS", "VARCHAR2,100");
		content58.put("CONTACT_MAN", "VARCHAR2,64");
		content58.put("EMAIL", "VARCHAR2,64");
		content58.put("TELEPHONE", "VARCHAR2,32");
		content58.put("FAX", "VARCHAR2,16");
		content58.put("BRANCH_TYPE", "VARCHAR2,16");
		content58.put("PRICE_TYPE", "VARCHAR2,16");
		content58.put("REMARK", "VARCHAR2,200");
		content58.put("ENABLED", "NUMBER,22");
		content58.put("PARK", "VARCHAR2,100");
		content58.put("LON", "NUMBER,22");
		content58.put("LAT", "NUMBER,22");
		content58.put("TAG", "VARCHAR2,50");
		content58.put("BUSINESS_DATE", "DATE,7");
		checkLst.add(content58);

		Map<String, Object> content59 = new HashMap<String, Object>();
		content59.put("tableName", "D_T2_BRANCH_STORAGE");
		content59.put("BRANCH_ID", "VARCHAR2,32");
		content59.put("STORAGE_ID", "VARCHAR2,32");
		content59.put("STORAGE_NAME", "VARCHAR2,64");
		content59.put("PRIORITY", "NUMBER,22");
		checkLst.add(content59);

		Map<String, Object> content60 = new HashMap<String, Object>();
		content60.put("tableName", "D_T2_BRANCH_TYPE");
		content60.put("TYPE_ID", "VARCHAR2,32");
		content60.put("TYPE_NAME", "VARCHAR2,64");
		content60.put("DESCRIPTION", "VARCHAR2,200");
		checkLst.add(content60);

		Map<String, Object> content61 = new HashMap<String, Object>();
		content61.put("tableName", "D_T2_DELIVERY_REGION");
		content61.put("REGION_ID", "VARCHAR2,32");
		content61.put("REGION_NAME", "VARCHAR2,32");
		content61.put("BRANCH_ID", "VARCHAR2,32");
		content61.put("DELIVERY_CYCLE", "NUMBER,22");
		content61.put("ENABLED", "NUMBER,22");
		checkLst.add(content61);

		Map<String, Object> content62 = new HashMap<String, Object>();
		content62.put("tableName", "D_T2_DELIVERY_REGION_BRANCH");
		content62.put("REGION_ID", "VARCHAR2,32");
		content62.put("BRANCH_ID", "VARCHAR2,32");
		checkLst.add(content62);

		Map<String, Object> content63 = new HashMap<String, Object>();
		content63.put("tableName", "D_T2_DELIVERY_TYPE");
		content63.put("REGION_ID", "VARCHAR2,32");
		content63.put("ITEM_ID", "VARCHAR2,32");
		content63.put("DELIVERY_TYPE", "VARCHAR2,16");
		checkLst.add(content63);

		Map<String, Object> content64 = new HashMap<String, Object>();
		content64.put("tableName", "D_T2_DELIVERY_UNIT");
		content64.put("ITEM_ID", "VARCHAR2,32");
		content64.put("DELIVERY_UNIT", "VARCHAR2,16");
		content64.put("DELIVERY_FACTOR", "NUMBER,22");
		content64.put("RECIPE_UNIT", "VARCHAR2,16");
		content64.put("RECIPE_FACTOR", "NUMBER,22");
		content64.put("UNIT_VOLUME", "NUMBER,22");
		content64.put("UNIT_WEIGHT", "NUMBER,22");
		content64.put("MIN_ORDER_COUNT", "NUMBER,22");
		content64.put("OUT_RECEIVE_RATE", "NUMBER,22");
		checkLst.add(content64);

		Map<String, Object> content65 = new HashMap<String, Object>();
		content65.put("tableName", "D_T2_FACTORY_WORKSHOP");
		content65.put("FACTORY_ID", "VARCHAR2,32");
		content65.put("WORK_ORDER_ID", "VARCHAR2,32");
		content65.put("WORKSHOP", "VARCHAR2,64");
		content65.put("PRIORITY", "NUMBER,22");
		checkLst.add(content65);

		Map<String, Object> content66 = new HashMap<String, Object>();
		content66.put("tableName", "D_T2_ITEM_CATEGORY");
		content66.put("CATEGORY_ID", "VARCHAR2,32");
		content66.put("CATEGORY_NAME", "VARCHAR2,80");
		content66.put("PARENT_ID", "VARCHAR2,32");
		content66.put("CATEGORY_LEVEL", "VARCHAR2,8");
		content66.put("ITEM_TYPE", "VARCHAR2,8");
		content66.put("PATH", "VARCHAR2,255");
		checkLst.add(content66);

		Map<String, Object> content67 = new HashMap<String, Object>();
		content67.put("tableName", "D_T2_ITEM_FOOD_SUIT");
		content67.put("FOOD_SUIT_ID", "VARCHAR2,32");
		content67.put("FOOD_ID", "VARCHAR2,32");
		content67.put("FOOD_OPTION_ID", "VARCHAR2,32");
		content67.put("ITEM_COUNT", "NUMBER,22");
		content67.put("ITEM_PRICE", "NUMBER,22");
		content67.put("IS_CURRENT", "NUMBER,22");
		checkLst.add(content67);

		Map<String, Object> content68 = new HashMap<String, Object>();
		content68.put("tableName", "D_T2_ITEM_META");
		content68.put("ITEM_ID", "VARCHAR2,32");
		content68.put("ITEM_BAR_CODE", "VARCHAR2,32");
		content68.put("ITEM_NAME", "VARCHAR2,150");
		content68.put("ITEM_NAME_ENG", "VARCHAR2,64");
		content68.put("ITEM_DIMENSION", "VARCHAR2,32");
		content68.put("QUERY_CODE", "VARCHAR2,64");
		content68.put("CATEGORY_ID", "VARCHAR2,32");
		content68.put("ITEM_SPECIFICATION", "VARCHAR2,64");
		content68.put("ITEM_TYPE", "VARCHAR2,8");
		content68.put("ITEM_PIC", "VARCHAR2,32");
		content68.put("ITEM_NOTE", "VARCHAR2,200");
		content68.put("BOX_TYPE", "VARCHAR2,32");
		content68.put("ENABLED", "NUMBER,22");
		content68.put("EFFECT_DATE", "DATE,7");
		checkLst.add(content68);

		Map<String, Object> content69 = new HashMap<String, Object>();
		content69.put("tableName", "D_T2_ITEM_PRICE");
		content69.put("ITEM_ID", "VARCHAR2,32");
		content69.put("ITEM_PRICE", "NUMBER,22");
		content69.put("PRICE_TYPE", "VARCHAR2,32");
		content69.put("IS_CURRENT", "NUMBER,22");
		content69.put("EFFITIVE_FORM_ID", "VARCHAR2,32");
		content69.put("EFFECT_TIME", "DATE,7");
		content69.put("SUPPLIER_ID", "VARCHAR2,32");
		checkLst.add(content69);

		Map<String, Object> content70 = new HashMap<String, Object>();
		content70.put("tableName", "D_T2_ITEM_WORKSHOP");
		content70.put("FACTORY_ID", "VARCHAR2,32");
		content70.put("WORK_ORDER_ID", "VARCHAR2,32");
		content70.put("ITEM_ID", "VARCHAR2,32");
		checkLst.add(content70);

		Map<String, Object> content71 = new HashMap<String, Object>();
		content71.put("tableName", "D_T2_MAKING_PROCESS");
		content71.put("ITEM_ID", "VARCHAR2,32");
		content71.put("STEP", "NUMBER,22");
		content71.put("STEP_OPERATION", "VARCHAR2,200");
		content71.put("STEP_NOTE", "VARCHAR2,200");
		checkLst.add(content71);

		Map<String, Object> content72 = new HashMap<String, Object>();
		content72.put("tableName", "D_T2_MRP_AMOUNT");
		content72.put("BRANCH_ID", "VARCHAR2,32");
		content72.put("ITEM_ID", "VARCHAR2,32");
		content72.put("ALLOCATION", "NUMBER,22");
		content72.put("ON_THE_WAY", "NUMBER,22");
		checkLst.add(content72);

		Map<String, Object> content73 = new HashMap<String, Object>();
		content73.put("tableName", "D_T2_OBSERVATION_DETAIL");
		content73.put("ITEM_ID", "VARCHAR2,32");
		content73.put("PART_ID", "VARCHAR2,32");
		content73.put("PROCESS_ID", "VARCHAR2,32");
		content73.put("PROCESSED_NAME", "VARCHAR2,64");
		content73.put("STEP", "NUMBER,22");
		content73.put("STEP_PIC_BLOB", "BLOB,4000");
		content73.put("STEP_REMARK", "VARCHAR2,500");
		checkLst.add(content73);

		Map<String, Object> content74 = new HashMap<String, Object>();
		content74.put("tableName", "D_T2_OBSERVATION_HEADER");
		content74.put("ITEM_ID", "VARCHAR2,32");
		content74.put("PART_ID", "VARCHAR2,32");
		content74.put("PART_NAME", "VARCHAR2,300");
		content74.put("PART_REMARK", "VARCHAR2,4000");
		content74.put("IS_TEMPLATE", "VARCHAR2,1");
		checkLst.add(content74);

		Map<String, Object> content75 = new HashMap<String, Object>();
		content75.put("tableName", "D_T2_PICS");
		content75.put("PIC_ID", "VARCHAR2,32");
		content75.put("PIC_BLOB", "BLOB,4000");
		checkLst.add(content75);

		Map<String, Object> content76 = new HashMap<String, Object>();
		content76.put("tableName", "D_T2_PRICE_GROUP");
		content76.put("PRICE_GROUP_ID", "VARCHAR2,32");
		content76.put("PRICE_GROUP_NAME", "VARCHAR2,32");
		content76.put("PRICE_GROUP_TYPE", "VARCHAR2,32");
		content76.put("PRICE_GROUP_NOTE", "VARCHAR2,64");
		content76.put("OWNER", "VARCHAR2,32");
		checkLst.add(content76);

		Map<String, Object> content77 = new HashMap<String, Object>();
		content77.put("tableName", "D_T2_PRICE_GROUP_BRANCH");
		content77.put("PRICE_GROUP_ID", "VARCHAR2,32");
		content77.put("BRANCH_ID", "VARCHAR2,32");
		checkLst.add(content77);

		Map<String, Object> content78 = new HashMap<String, Object>();
		content78.put("tableName", "D_T2_PRODUCTION_CYCLE");
		content78.put("BRANCH_ID", "VARCHAR2,32");
		content78.put("ITEM_ID", "VARCHAR2,32");
		content78.put("PRODUCTION_CYCLE", "NUMBER,22");
		checkLst.add(content78);

		Map<String, Object> content79 = new HashMap<String, Object>();
		content79.put("tableName", "D_T2_SHELF");
		content79.put("SHELF_ID", "VARCHAR2,32");
		content79.put("SHELF_NAME", "VARCHAR2,32");
		content79.put("DESCRIPTION", "VARCHAR2,120");
		checkLst.add(content79);

		Map<String, Object> content80 = new HashMap<String, Object>();
		content80.put("tableName", "D_T2_SHELF_ITEM");
		content80.put("SHELF_ID", "VARCHAR2,32");
		content80.put("ITEM_ID", "VARCHAR2,32");
		content80.put("PRIORITY", "NUMBER,22");
		checkLst.add(content80);

		Map<String, Object> content81 = new HashMap<String, Object>();
		content81.put("tableName", "D_T2_STORAGE_WORKSHOP");
		content81.put("STORAGE_ID", "VARCHAR2,32");
		content81.put("WORK_ORDER_ID", "VARCHAR2,32");
		content81.put("WORKSHOP", "VARCHAR2,64");
		content81.put("PRIORITY", "NUMBER,22");
		checkLst.add(content81);

		Map<String, Object> content82 = new HashMap<String, Object>();
		content82.put("tableName", "D_T2_SUPPLIER_BRANCH_ITEM");
		content82.put("SUPPLIER_ID", "VARCHAR2,32");
		content82.put("BRANCH_ID", "VARCHAR2,32");
		content82.put("ITEM_ID", "VARCHAR2,32");
		content82.put("PRIORITY", "NUMBER,22");
		checkLst.add(content82);

		Map<String, Object> content83 = new HashMap<String, Object>();
		content83.put("tableName", "D_T2_SUPPLY_CYCLE");
		content83.put("BRANCH_ID", "VARCHAR2,32");
		content83.put("ITEM_ID", "VARCHAR2,32");
		content83.put("SUPPLY_CYCLE", "NUMBER,22");
		content83.put("INVENTORYS_CYCLE", "NUMBER,22");
		checkLst.add(content83);

		Map<String, Object> content84 = new HashMap<String, Object>();
		content84.put("tableName", "D_T2_TAG_DETAIL");
		content84.put("TAG_ID", "VARCHAR2,32");
		content84.put("CODE", "VARCHAR2,32");
		checkLst.add(content84);

		Map<String, Object> content85 = new HashMap<String, Object>();
		content85.put("tableName", "D_T2_TAG_HEADER");
		content85.put("TAG_ID", "VARCHAR2,32");
		content85.put("TAG_NAME", "VARCHAR2,32");
		content85.put("TAG_NOTE", "VARCHAR2,128");
		content85.put("TYPE_CODE", "VARCHAR2,12");
		checkLst.add(content85);

		Map<String, Object> content86 = new HashMap<String, Object>();
		content86.put("tableName", "D_T2_THERAPY");
		content86.put("THERAPY_ID", "VARCHAR2,32");
		content86.put("THERAPY_NAME", "VARCHAR2,150");
		content86.put("THERAPY_DIMENSION", "VARCHAR2,16");
		content86.put("ITEM_ID", "VARCHAR2,32");
		content86.put("ITEM_NAME", "VARCHAR2,150");
		content86.put("ITEM_DIMENSION", "VARCHAR2,32");
		content86.put("UNIT_CONVERT_FACTOR", "NUMBER,22");
		content86.put("ITEM_COUNT", "NUMBER,22");
		content86.put("ITEM_USEAGE_RATE", "NUMBER,22");
		content86.put("ITEM_GROSS_COUNT", "NUMBER,22");
		content86.put("ITEM_AMT", "NUMBER,22");
		content86.put("ITEM_TYPE", "VARCHAR2,16");
		content86.put("OWNER", "VARCHAR2,32");
		checkLst.add(content86);

		Map<String, Object> content87 = new HashMap<String, Object>();
		content87.put("tableName", "D_T2_USER");
		content87.put("USER_ID", "VARCHAR2,32");
		content87.put("USER_NAME", "VARCHAR2,50");
		content87.put("PASSWORD", "VARCHAR2,100");
		content87.put("WEIGHT", "NUMBER,22");
		content87.put("EMAIL", "VARCHAR2,50");
		content87.put("TELEPHONE", "VARCHAR2,50");
		content87.put("GENDER", "VARCHAR2,10");
		checkLst.add(content87);

		Map<String, Object> content88 = new HashMap<String, Object>();
		content88.put("tableName", "D_T2_WORKSHOP");
		content88.put("WORK_ORDER_ID", "VARCHAR2,32");
		content88.put("WORKSHOP", "VARCHAR2,64");
		checkLst.add(content88);

		Map<String, Object> content89 = new HashMap<String, Object>();
		content89.put("tableName", "D_T3_MONTHLY_SETTLE_RECORD");
		content89.put("BRANCH_ID", "VARCHAR2,32");
		content89.put("BRANCH_NAME", "VARCHAR2,32");
		content89.put("MONTH_DATE", "DATE,7");
		content89.put("OPERATOR_ID", "VARCHAR2,32");
		content89.put("OPERATOR_NAME", "VARCHAR2,32");
		content89.put("OPERATING_TIME", "DATE,7");
		content89.put("SETTLE_STATUS", "VARCHAR2,64");
		content89.put("SETTLE_NOTE", "VARCHAR2,200");
		checkLst.add(content89);

		Map<String, Object> content90 = new HashMap<String, Object>();
		content90.put("tableName", "D_T_BILL_PAY");
		content90.put("CBILL_C", "VARCHAR2,32");
		content90.put("CPAY_C", "VARCHAR2,32");
		content90.put("CPAY_N", "VARCHAR2,64");
		content90.put("SUNIT", "VARCHAR2,16");
		content90.put("NPAYAMT", "NUMBER,22");
		content90.put("CVIP_C", "VARCHAR2,32");
		content90.put("CVIP_N", "VARCHAR2,20");
		content90.put("CARREAR_C", "VARCHAR2,32");
		content90.put("CARREAR_N", "VARCHAR2,30");
		content90.put("EINTYPE", "VARCHAR2,24");
		content90.put("NEXCHRATE", "NUMBER,22");
		content90.put("NOLDAMT", "NUMBER,22");
		content90.put("CGRANTMAN_C", "VARCHAR2,32");
		content90.put("CGRANTMAN", "VARCHAR2,20");
		content90.put("CPAYMAN_C", "VARCHAR2,32");
		content90.put("CPAYMAN", "VARCHAR2,20");
		content90.put("EPAYTYPE", "VARCHAR2,24");
		content90.put("CSHIFT_C", "VARCHAR2,3");
		content90.put("CSHIFT_N", "VARCHAR2,12");
		checkLst.add(content90);

		Map<String, Object> content91 = new HashMap<String, Object>();
		content91.put("tableName", "D_T_FOOD_BILL");
		content91.put("CFADEBILLMAN_C", "VARCHAR2,32");
		content91.put("CFADEBILLMAN", "VARCHAR2,20");
		content91.put("NDISAMTSERVICE", "NUMBER,22");
		content91.put("NSERVICEFEEOLD", "NUMBER,22");
		content91.put("DTTIMEBEGIN", "DATE,7");
		content91.put("DTTIMEEND", "DATE,7");
		content91.put("BUNTIME", "NUMBER,22");
		content91.put("NVIPFOODORIAMT", "NUMBER,22");
		content91.put("CFLOOR_N", "VARCHAR2,12");
		content91.put("CFLOOR_C", "VARCHAR2,3");
		content91.put("CDISFULLWHY", "VARCHAR2,60");
		content91.put("NFACTAMT", "NUMBER,22");
		content91.put("CARREAR_C", "VARCHAR2,32");
		content91.put("CARREAR_N", "VARCHAR2,30");
		content91.put("CBILL_C", "VARCHAR2,32");
		content91.put("DBUSINESS", "DATE,7");
		content91.put("CTABLE_C", "VARCHAR2,12");
		content91.put("CTABLE_N", "VARCHAR2,36");
		content91.put("CPERIOD_C", "VARCHAR2,12");
		content91.put("CPERIOD_N", "VARCHAR2,36");
		content91.put("CSHIFT_C", "VARCHAR2,12");
		content91.put("CSHIFT_N", "VARCHAR2,36");
		content91.put("CVIP_C", "VARCHAR2,32");
		content91.put("CVIP_N", "VARCHAR2,32");
		content91.put("IGUESTNUM", "NUMBER,22");
		content91.put("DTBILLTIME", "DATE,7");
		content91.put("DTSETTLETIME", "DATE,7");
		content91.put("NFOODAMT", "NUMBER,22");
		content91.put("ESERVICEPAY", "VARCHAR2,24");
		content91.put("NSERVICERATE", "NUMBER,22");
		content91.put("NSERVICEFEE", "NUMBER,22");
		content91.put("EMINPAY", "VARCHAR2,24");
		content91.put("NMINPAY", "NUMBER,22");
		content91.put("NDISAMT", "NUMBER,22");
		content91.put("NDISRATE", "NUMBER,22");
		content91.put("NPRESENTAMT", "NUMBER,22");
		content91.put("NROUNDAMT", "NUMBER,22");
		content91.put("NOUGHTAMT", "NUMBER,22");
		content91.put("NPAYAMT", "NUMBER,22");
		content91.put("BSETTLE", "NUMBER,22");
		content91.put("BUNSETTLE", "NUMBER,22");
		content91.put("CCREATEMAN_C", "VARCHAR2,32");
		content91.put("CCREATEMAN", "VARCHAR2,20");
		content91.put("CSETTLEMAN_C", "VARCHAR2,32");
		content91.put("CSETTLEMAN", "VARCHAR2,20");
		content91.put("CUNSETTLEMAN_C", "VARCHAR2,32");
		content91.put("CUNSETTLEMAN", "VARCHAR2,20");
		content91.put("CDISCOUNTMAN_C", "VARCHAR2,32");
		content91.put("CDISCOUNTMAN", "VARCHAR2,20");
		content91.put("CDISMANCUR_N", "VARCHAR2,32");
		content91.put("CDISCURWHY", "VARCHAR2,60");
		content91.put("SSENDMAN", "VARCHAR2,32");
		content91.put("CBRANCH_C", "VARCHAR2,32");
		content91.put("CBRANCH_N", "VARCHAR2,64");
		content91.put("SREMARK", "VARCHAR2,100");
		content91.put("BUNFEE", "NUMBER,22");
		content91.put("BUNMIN", "NUMBER,22");
		content91.put("EWHOLETYPE", "VARCHAR2,24");
		content91.put("SBILLTYPE", "VARCHAR2,12");
		content91.put("CTIMEFEE_C", "VARCHAR2,24");
		content91.put("NTIMEAMT", "NUMBER,22");
		content91.put("BUNTEA", "NUMBER,22");
		content91.put("BUNJIE", "NUMBER,22");
		content91.put("NDISAMTFULL", "NUMBER,22");
		content91.put("NDISRATECUR", "NUMBER,22");
		content91.put("NDISAMTCUR", "NUMBER,22");
		content91.put("CPOS_C", "VARCHAR2,32");
		content91.put("CFADEBILL_C", "VARCHAR2,32");
		content91.put("CSERMAN", "VARCHAR2,32");
		checkLst.add(content91);

		Map<String, Object> content92 = new HashMap<String, Object>();
		content92.put("tableName", "D_T_FOOD_BILLS");
		content92.put("CBILL_C", "VARCHAR2,32");
		content92.put("CFOOD_C", "VARCHAR2,32");
		content92.put("CFOOD_N", "VARCHAR2,64");
		content92.put("SUNIT", "VARCHAR2,16");
		content92.put("NPRC", "NUMBER,22");
		content92.put("NQTY", "NUMBER,22");
		content92.put("NAMT", "NUMBER,22");
		content92.put("NDISAMT", "NUMBER,22");
		content92.put("ESUITFLAG", "VARCHAR2,24");
		content92.put("CSUITBILL", "VARCHAR2,32");
		content92.put("CSUITNAME", "VARCHAR2,64");
		content92.put("ERETSENDFLAG", "VARCHAR2,12");
		content92.put("SRETSENDREMARK", "VARCHAR2,64");
		content92.put("CPRESENTBACKMAN", "VARCHAR2,32");
		content92.put("CLITCLS_C", "VARCHAR2,32");
		content92.put("CLITCLS_N", "VARCHAR2,64");
		content92.put("CFOODBILL", "VARCHAR2,4");
		content92.put("NEXTPRC", "NUMBER,22");
		content92.put("BDISCOUNT", "NUMBER,22");
		content92.put("SMADE", "VARCHAR2,64");
		content92.put("CSERVICEMAN", "VARCHAR2,32");
		content92.put("NDISRATE", "NUMBER,22");
		content92.put("CPOS_C", "VARCHAR2,32");
		content92.put("CLOGIN", "VARCHAR2,32");
		content92.put("CRETSENDBILL", "VARCHAR2,32");
		content92.put("CNAMEENG", "VARCHAR2,64");
		content92.put("CBIGCLS_C", "VARCHAR2,32");
		content92.put("CBIGCLS_N", "VARCHAR2,64");
		content92.put("DTINPUTTIME", "DATE,7");
		content92.put("IPRCTYPE", "NUMBER,22");
		content92.put("BSERVICEFEE", "NUMBER,22");
		content92.put("NDISAMTS", "NUMBER,22");
		content92.put("CDEP_C", "VARCHAR2,32");
		content92.put("CDEP_N", "VARCHAR2,64");
		content92.put("NSERVICEFEES", "NUMBER,22");
		content92.put("NDOQTY", "NUMBER,22");
		content92.put("NPRCOLD", "NUMBER,22");
		content92.put("CMAINMADE", "VARCHAR2,40");
		content92.put("CBILLQUICK", "VARCHAR2,40");
		checkLst.add(content92);

		Map<String, Object> content93 = new HashMap<String, Object>();
		content93.put("tableName", "D_T3_DAILY_FOOD_AMT");
		content93.put("BRANCH_ID", "VARCHAR2,32");
		content93.put("BUSINESS_DATE", "DATE,7");
		content93.put("ITEM_ID", "VARCHAR2,32");
		content93.put("FOOD_AMT", "NUMBER,22");
		checkLst.add(content93);

		Map<String, Object> content94 = new HashMap<String, Object>();
		content94.put("tableName", "D_T3_DAILY_SETTLE_RECORD");
		content94.put("BRANCH_ID", "VARCHAR2,32");
		content94.put("BRANCH_NAME", "VARCHAR2,32");
		content94.put("BUSINESS_DATE", "DATE,7");
		content94.put("OPERATOR_ID", "VARCHAR2,32");
		content94.put("OPERATOR_NAME", "VARCHAR2,32");
		content94.put("OPERATING_TIME", "DATE,7");
		content94.put("SETTLE_STATUS", "VARCHAR2,64");
		content94.put("SETTLE_NOTE", "VARCHAR2,200");
		content94.put("IS_CURRENT", "NUMBER,22");
		checkLst.add(content94);

		Map<String, Object> content95 = new HashMap<String, Object>();
		content95.put("tableName", "D_T3_DAILY_RAW_AMT");
		content95.put("BRANCH_ID", "VARCHAR2,32");
		content95.put("BUSINESS_DATE", "DATE,7");
		content95.put("ITEM_PRICE", "NUMBER,22");
		content95.put("PRICE_TYPE", "VARCHAR2,32");
		content95.put("ITEM_ID", "VARCHAR2,32");
		content95.put("AMT_THEORY", "NUMBER,22");
		content95.put("AMT_ACTUAL", "NUMBER,22");
		checkLst.add(content95);

		Map<String, Object> content96 = new HashMap<String, Object>();
		content96.put("tableName", "D_T0_FORM_STATUS");
		content96.put("FORM_ID", "VARCHAR2,32");
		content96.put("STATUS", "VARCHAR2,32");
		content96.put("STATUS_TIME", "DATE,7");
		content96.put("OPERATOR", "VARCHAR2,32");
		content96.put("STATUS_ORDER", "NUMBER,22");
		content96.put("IS_CURRENT", "NUMBER,22");
		checkLst.add(content96);

		Map<String, Object> content97 = new HashMap<String, Object>();
		content97.put("tableName", "D_T3_DAILY_STORAGE");
		content97.put("BRANCH_ID", "VARCHAR2,32");
		content97.put("BUSINESS_DATE", "DATE,7");
		content97.put("ITEM_ID", "VARCHAR2,32");
		content97.put("ITEM_COUNT_THEORY", "NUMBER,22");
		content97.put("ITEM_COUNT_ACTUAL", "NUMBER,22");
		checkLst.add(content97);

		Map<String, Object> content98 = new HashMap<String, Object>();
		content98.put("tableName", "D_T0_STORAGE_IN_OUT");
		content98.put("BRANCH_ID", "VARCHAR2,32");
		content98.put("STORAGE_ID", "VARCHAR2,32");
		content98.put("BUSINESS_DATE", "DATE,7");
		content98.put("OPERATION_TIME", "DATE,7");
		content98.put("ITEM_ID", "VARCHAR2,32");
		content98.put("ITEM_UNIT_PRICE", "NUMBER,22");
		content98.put("ORGI_COUNT", "NUMBER,22");
		content98.put("ITEM_COUNT_IN", "NUMBER,22");
		content98.put("ITEM_COUNT_OUT", "NUMBER,22");
		content98.put("RESULT_COUNT", "NUMBER,22");
		content98.put("FORM_ID", "VARCHAR2,32");
		content98.put("REASON", "VARCHAR2,64");
		content98.put("MY_TIMESTAMP", "VARCHAR2,32");
		checkLst.add(content98);

		Map<String, Object> content99 = new HashMap<String, Object>();
		content99.put("tableName", "D_T3_DAILY_BUSINESS_AMT");
		content99.put("BRANCH_ID", "VARCHAR2,32");
		content99.put("BUSINESS_DATE", "DATE,7");
		content99.put("BUSINESS_AMT", "NUMBER,22");
		checkLst.add(content99);

		Map<String, Object> content100 = new HashMap<String, Object>();
		content100.put("tableName", "D_T0_OPERATELOG");
		content100.put("ID", "VARCHAR2,36");
		content100.put("MENU_ID", "VARCHAR2,32");
		content100.put("MENU_NAME", "VARCHAR2,100");
		content100.put("URL", "VARCHAR2,200");
		content100.put("BUSINESS_ID", "VARCHAR2,32");
		content100.put("OPERATE_TYPE", "VARCHAR2,50");
		content100.put("TITLE", "VARCHAR2,100");
		content100.put("OPERATE_DESC", "VARCHAR2,4000");
		content100.put("OPERATOR_ID", "VARCHAR2,32");
		content100.put("OPERATOR_NAME", "VARCHAR2,100");
		content100.put("BRANCH_ID", "VARCHAR2,32");
		content100.put("OPERATE_TIME", "DATE,7");
		checkLst.add(content100);

		Map<String, Object> content101 = new HashMap<String, Object>();
		content101.put("tableName", "D_T2_STORAGE");
		content101.put("STORAGE_ID", "VARCHAR2,32");
		content101.put("ITEM_ID", "VARCHAR2,32");
		content101.put("ITEM_COUNT", "NUMBER,22");
		content101.put("SHELF_ID", "VARCHAR2,32");
		content101.put("LAST_IN_TIME", "DATE,7");
		content101.put("EXPIRED_TIME", "DATE,7");
		checkLst.add(content101);

		Map<String, Object> content102 = new HashMap<String, Object>();
		content102.put("tableName", "D_T0_OPERATION_VERSION");
		content102.put("FORM_ID", "VARCHAR2,32");
		content102.put("OPERATION_ID", "VARCHAR2,32");
		content102.put("OPERATION_NAME", "VARCHAR2,32");
		content102.put("OPERATION_TIME", "DATE,7");
		content102.put("VERSION", "NUMBER,22");
		content102.put("OPERATION_CONTENT", "VARCHAR2,32");
		checkLst.add(content102);

		try {
			Connection conn = DBUtil.getConnection();
			for (Map<String, Object> check : checkLst) {
				chackTable(conn, check);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 遍历表属性，进行字段名，属性，类型验证
	 * 
	 * @param conn
	 * @return
	 * @throws SQLException
	 */
	public static Map<String, String> chackTable(Connection conn, Map<String, Object> checkMap) throws SQLException {
		String tableName = checkMap.get("tableName").toString();
		String sql = "SELECT COLUMN_NAME,DATA_TYPE,DATA_LENGTH FROM dba_tab_columns a WHERE a.TABLE_NAME ='"
				+ tableName + "' AND a.owner='YXG'";// owner为表空间名，使用时需要修改，权限为高级权限
		System.out.println(sql);
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(sql);
		while (rs.next()) {
			String columnName = rs.getString("COLUMN_NAME");
			String item = checkMap.get(columnName).toString();
			int endIndex = item.indexOf(",");
			String dateType1 = item.substring(0, endIndex);// 属性类型
			String dateLength1 = item.substring(endIndex + 1);// 属性长度
			String dateType2 = rs.getString("DATA_TYPE");
			String dateLength2 = rs.getString("DATA_LENGTH");
			// 对表属性的类型，长度验证
			if (!dateType2.equals(dateType1)) {
				System.out.println("表" + tableName + "字段" + columnName + "类型应该为" + dateType1 + "实际为" + dateType2);
			}
			if (!dateLength2.equals(dateLength1)) {
				System.out.println("表" + tableName + "字段" + columnName + "长度应该为" + dateLength1 + "实际为" + dateLength2);
			}
			// 存在的字段设置为1
			checkMap.put(columnName, "1");
		}

		// 验证表字段是否存在
		Set set = checkMap.entrySet();
		Iterator i = set.iterator();
		while (i.hasNext()) {
			Map.Entry<String, String> entry1 = (Map.Entry<String, String>) i.next();
			if (entry1.getKey().equals("tableName")) {
				continue;// 表名，过滤掉
			}
			if (!entry1.getValue().equals("1")) {
				System.out.println("表" + tableName + "字段" + entry1.getKey() + "缺失");
			}
		}
		rs.close();
		st.close();
		return null;
	}
}
