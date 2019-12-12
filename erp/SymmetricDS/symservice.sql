delete from sym_trigger_router;
delete from sym_trigger;
delete from sym_router;
delete from sym_channel where channel_id in ('item_channel', 'sale_channel');
delete from sym_node_group_link;
delete from sym_node_group;
delete from sym_node_identity;
delete from sym_node_security;
delete from sym_node_host;
delete from sym_node;


insert into sym_node_group(node_group_id, description)
    values('corp', 'headquarters of corp');
insert into sym_node_group(node_group_id, description)
    values('store', 'stores of corp');


insert into sym_node_group_link(source_node_group_id, target_node_group_id, data_event_action)
    values('store', 'corp', 'P');
insert into sym_node_group_link (source_node_group_id, target_node_group_id, data_event_action)
    values ('corp', 'store', 'W');


insert into sym_node(node_id, node_group_id, external_id, sync_enabled)
    values('000', 'corp', '000', 1);
insert into sym_node_security (node_id,node_password,registration_enabled,registration_time,initial_load_enabled,initial_load_time,initial_load_id,initial_load_create_by,rev_initial_load_enabled,rev_initial_load_time,rev_initial_load_id,rev_initial_load_create_by,created_at_node_id)
    values ('000','changeme', 0, current_timestamp, 0, current_timestamp, null, null, 0, null, null, null, '000');
insert into sym_node_identity
    values('000');


insert into sym_channel(channel_id, processing_order, max_batch_size, enabled, description)
    values('item_channel', 1, 1000000, 1, 'item and pricing data from corp to store');
insert into sym_channel(channel_id, processing_order, max_batch_size, enabled, description)
    values('sale_channel', 1, 1000000, 1, 'sale data from store to corp');


insert into sym_trigger(trigger_id, source_table_name, channel_id, last_update_time, create_time)
    values('food_bigcls_trigger', 'C_T_FOOD_BIGCLS', 'item_channel', current_timestamp, current_timestamp);
insert into sym_trigger(trigger_id, source_table_name, channel_id, last_update_time, create_time)
    values('food_litcls_trigger', 'C_T_FOOD_LITCLS', 'item_channel', current_timestamp, current_timestamp);
insert into sym_trigger(trigger_id, source_table_name, channel_id, last_update_time, create_time)
    values('food_info_trigger', 'C_T_FOOD', 'item_channel', current_timestamp, current_timestamp);
insert into sym_trigger(trigger_id, source_table_name, channel_id, last_update_time, create_time)
    values('food_price_trigger', 'C_T_FOOD_PRICE', 'item_channel', current_timestamp, current_timestamp);

insert into sym_trigger(trigger_id, source_table_name, channel_id, last_update_time, create_time)
    values('sale_bill_triger', 'D_T_FOOD_BILL', 'sale_channel', current_timestamp, current_timestamp);
insert into sym_trigger(trigger_id, source_table_name, channel_id, last_update_time, create_time)
    values('sale_bills_triger', 'D_T_FOOD_BILLS', 'sale_channel', current_timestamp, current_timestamp);
insert into sym_trigger(trigger_id, source_table_name, channel_id, last_update_time, create_time)
    values('sale_pay_triger', 'D_T_BILL_PAY', 'sale_channel', current_timestamp, current_timestamp);
	


insert into sym_router(router_id, source_node_group_id, target_node_group_id, router_type, create_time, last_update_time)
    values('corp_2_store', 'corp', 'store', 'default', current_timestamp, current_timestamp);
insert into sym_router(router_id, source_node_group_id, target_node_group_id, router_type, create_time, last_update_time)
    values('store_2_corp', 'store', 'corp', 'default', current_timestamp, current_timestamp);
insert into sym_router(router_id, source_node_group_id, target_node_group_id, router_type,router_expression, create_time, last_update_time)
    values('corp_2_one_store', 'corp', 'store', 'column', 'BRANCH_ID=:EXTERNAL_ID',current_timestamp, current_timestamp);
--insert into sym_router(router_id, source_node_group_id, target_node_group_id, router_type,router_expression, create_time, last_update_time)
--    values('store_2_corp_nospacecfoodc', 'store', 'corp', 'bsh', 'CF00D_C!=null && CFOOD_C.equals(CFOOD_C.trim())',current_timestamp, current_timestamp);
insert into sym_router(router_id, source_node_group_id, target_node_group_id, router_type,router_expression, create_time, last_update_time)
    values('store_2_corp_sheftnotnull', 'store', 'corp', 'bsh', 'CSHIFT_C!=null && !CSHIFT_C.equals("")',current_timestamp, current_timestamp);



insert into sym_trigger_router(trigger_id, router_id, initial_load_order, last_update_time, create_time)
    values('sale_bill_triger', 'store_2_corp', 1, current_timestamp, current_timestamp);
insert into sym_trigger_router(trigger_id, router_id, initial_load_order, last_update_time, create_time)
    values('sale_bills_triger', 'store_2_corp', 1, current_timestamp, current_timestamp);
insert into sym_trigger_router(trigger_id, router_id, initial_load_order, last_update_time, create_time)
    values('sale_pay_triger', 'store_2_corp_sheftnotnull', 1, current_timestamp, current_timestamp);
	

insert into sym_trigger_router(trigger_id, router_id, initial_load_order, last_update_time, create_time)
    values('food_bigcls_trigger', 'corp_2_store', 1, current_timestamp, current_timestamp);
insert into sym_trigger_router(trigger_id, router_id, initial_load_order, last_update_time, create_time)
    values('food_litcls_trigger', 'corp_2_store', 2, current_timestamp, current_timestamp);
insert into sym_trigger_router(trigger_id, router_id, initial_load_order, last_update_time, create_time)
    values('food_info_trigger', 'corp_2_store', 3, current_timestamp, current_timestamp);
insert into sym_trigger_router(trigger_id, router_id, initial_load_order, initial_load_select, last_update_time, create_time)
    values('food_price_trigger', 'corp_2_one_store', 4, 'BRANCH_ID=''$(externalId)''', current_timestamp, current_timestamp);


--传输表映射
insert into SYM_TRANSFORM_TABLE(transform_id, source_node_group_id,target_node_group_id, transform_point, source_table_name, target_table_name, delete_action,column_policy)
    values('litfood_tfs', 'corp', 'store', 'LOAD', 'C_T_FOOD_LITCLS', 'C_T_FOOD_LITCLS', 'DEL_ROW', 'SPECIFIED');
insert into SYM_TRANSFORM_TABLE(transform_id, source_node_group_id,target_node_group_id, transform_point, source_table_name, target_table_name, delete_action,column_policy)
    values('bigfood_tfs', 'corp', 'store', 'LOAD', 'C_T_FOOD_BIGCLS', 'C_T_FOOD_BIGCLS', 'DEL_ROW', 'SPECIFIED');
insert into SYM_TRANSFORM_TABLE(transform_id, source_node_group_id,target_node_group_id, transform_point, source_table_name, target_table_name, delete_action,column_policy)
    values('food_tfs', 'corp', 'store', 'LOAD', 'C_T_FOOD', 'C_T_FOOD', 'DEL_ROW', 'SPECIFIED');
insert into SYM_TRANSFORM_TABLE(transform_id, source_node_group_id,target_node_group_id, transform_point, source_table_name, target_table_name, delete_action,column_policy)
    values('food_price_tfs', 'corp', 'store', 'LOAD', 'C_T_FOOD_PRICE', 'C_T_FOOD', 'UPDATE_COL', 'SPECIFIED');
insert into SYM_TRANSFORM_TABLE(transform_id, source_node_group_id,target_node_group_id, transform_point, source_table_name, target_table_name, delete_action,column_policy)
    values('food_bills_tfs', 'store', 'corp', 'LOAD', 'D_T_FOOD_BILLS', 'D_T_FOOD_BILLS', 'UPDATE_COL', 'SPECIFIED');



--cfood_c_big大类表列映射
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('bigfood_tfs','*','CBIGCLS_C','CBIGCLS_C',1,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('bigfood_tfs','*','CBIGCLS_N','CBIGCLS_N',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('bigfood_tfs','*','SAREA','SAREA',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('bigfood_tfs','*','CBRANCH_C','CBRANCH_C',0,'copy',NULL);

--cfood_c_lit小类表列类映射
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('litfood_tfs','*','CLITCLS_C','CLITCLS_C',1,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('litfood_tfs','*','CLITCLS_N','CLITCLS_N',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('litfood_tfs','*','BSERVICEFEE','BSERVICEFEE',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('litfood_tfs','*','BDISCOUNT','BDISCOUNT',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('litfood_tfs','*','CBIGCLS_C','CBIGCLS_C',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('litfood_tfs','*','CBIGCLS_N','CBIGCLS_N',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('litfood_tfs','*','CBRANCH_C','CBRANCH_C',0,'copy',NULL);

--food表列映射
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','ITEM_ID','cFood_C',1,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','ITEM_BAR_CODE','cSecond_c',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','ITEM_NAME','cFood_N',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','ITEM_NAME_ENG','cNameEng',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','ITEM_DIMENSION','sUnit',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','QUERY_CODE','sNameFast',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','CATEGORY_ID','cLitCls_C',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','ITEM_NOTE','sDesc',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','ENABLED','bUse',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','BINHANDPRO','binhandpro',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','BDISFOOD','bdis_food',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_tfs','*','BDISCOUNT','bDiscount',0,'copy',NULL);

--cfood_price列映射
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_price_tfs','*','BRANCH_ID','cBranch_C',0,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_price_tfs','*','ITEM_ID','cFood_C',1,'copy',NULL);
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_price_tfs','*','ITEM_PRICE','nPrc',0,'copy',NULL);

--food_bills列映射
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CBILL_C','CBILL_C',1,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CFOODBILL','CFOODBILL',1,'copy',copy);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CFOOD_C','CFOOD_C',1,'bsh','if (!CFOOD_C.equals(CFOOD_C.trim())) {return CFOOD_C.trim();}else {return CFOOD_C;}');	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CFOOD_N','CFOOD_N',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','SUNIT','SUNIT',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NPRC','NPRC',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NQTY','NQTY',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NAMT','NAMT',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NEXTPRC','NEXTPRC',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','BDISCOUNT','BDISCOUNT',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','ESUITFLAG','ESUITFLAG',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CSUITBILL','CSUITBILL',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','ERETSENDFLAG','ERETSENDFLAG',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','SRETSENDREMARK','SRETSENDREMARK',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','SMADE','SMADE',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CSERVICEMAN','CSERVICEMAN',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NDISRATE','NDISRATE',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NDISAMT','NDISAMT',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CPOS_C','CPOS_C',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CLOGIN','CLOGIN',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CRETSENDBILL','CRETSENDBILL',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CSUITNAME','CSUITNAME',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CNAMEENG','CNAMEENG',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CLITCLS_C','CLITCLS_C',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CLITCLS_N','CLITCLS_N',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CBIGCLS_C','CBIGCLS_C',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CBIGCLS_N','CBIGCLS_N',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','DTINPUTTIME','DTINPUTTIME',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','IPRCTYPE','IPRCTYPE',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','BSERVICEFEE','BSERVICEFEE',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NDISAMTS','NDISAMTS',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CDEP_C','CDEP_C',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CDEP_N','CDEP_N',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NSERVICEFEES','NSERVICEFEES',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NDOQTY','NDOQTY',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CPRESENTBACKMAN','CPRESENTBACKMAN',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','NPRCOLD','NPRCOLD',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CMAINMADE','CMAINMADE',0,'copy',NULL);	
insert into SYM_TRANSFORM_COLUMN    (transform_id, include_on, source_column_name, target_column_name, pk, transform_type,TRANSFORM_EXPRESSION)
    values('food_bills_tfs','*','CBILLQUICK','CBILLQUICK',0,'copy',NULL);	

	

	
--冲突策略
insert into SYM_CONFLICT(CONFLICT_ID,SOURCE_NODE_GROUP_ID,TARGET_NODE_GROUP_ID,DETECT_TYPE,RESOLVE_TYPE,PING_BACK,CREATE_TIME,LAST_UPDATE_TIME)
    values('conflict_fallback', 'corp', 'store', 'USE_PK_DATA', 'FALLBACK', 'OFF', current_timestamp, current_timestamp);
commit;
