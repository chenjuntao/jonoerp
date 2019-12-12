::::::::::::::::::::::::::::���´��������ݿ��в�ѯ:::::::::::::::::::::::::::::::

::�������������ݿ������IP������Ǳ���������Ϊlocalhost
set svr=localhost

::�������������ݿ���û��������� 
set usr=sa
set pwd=123

:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::
set option=-c -U"%usr%" -P"%pwd%" -S"%svr%" -t"|~|"
:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::

::�������ű�
bcp "select branch_no,branch_name,py_str,address,branch_man,branch_email,branch_tel,branch_fax,'RESTAURANT',branch_name from kmcyV51.dbo.bi_t_branch_info" queryout .\dat_info_branch %option%

::ԭ�����
bcp "select rtrim(item_clsno),item_clsname,up_clsno,level_num,'RAW' from kmcyV51.dbo.bi_t_item_cls where item_clsname not like '%%���Ʒ'" queryout .\dat_cls_raw %option%

::���Ʒ���
bcp "select rtrim(item_clsno),item_clsname,'*', '1','SEMIS' from kmcyV51.dbo.bi_t_item_cls where item_clsname like '%%���Ʒ'" queryout .\dat_cls_semis %option%

::��Ʒ���
bcp "select cBigCls_C,cBigCls_N,'*','1','PRODUCT' from kmcyV51.dbo.c_t_food_bigCls union select cLitCls_C,cLitCls_N,cBigCls_C,'2','PRODUCT' from kmcyV51.dbo.c_t_food_litCls" queryout .\dat_cls_food %option%

::ԭ����Ϣ
bcp "select rtrim(a.item_no),rtrim(a.item_no),a.item_name,a.item_subname,a.unit_no,a.py_str,rtrim(a.item_clsno),a.item_size,'RAW','rawsamplepic','ԭ�ϱ�ע' from kmcyV51.dbo.bi_t_item_info a, kmcyV51.dbo.bi_t_item_cls b where a.item_clsno = b.item_clsno and b.item_clsname not like '%%���Ʒ' and b.item_clsname <> '�ڿ�'" queryout .\dat_item_raw %option%

::���Ʒ��Ϣ
bcp "select rtrim(a.item_no),rtrim(a.item_no),a.item_name,a.item_subname,a.unit_no,a.py_str,rtrim(a.item_clsno),a.item_size,'SEMIS','semissamplepic','���Ʒ��ע' from kmcyV51.dbo.bi_t_item_info a, kmcyV51.dbo.bi_t_item_cls b where a.item_clsno = b.item_clsno and b.item_clsname like '%%���Ʒ'" queryout .\dat_item_semis %option%

::��Ʒ��Ϣ
bcp "select cFood_C,cFood_C,cFood_N,cNameEng,sUnit,sNameFast,cLitCls_C,'','PRODUCT','foodsamplepic','��Ʒ��ע' from kmcyV51.dbo.c_t_food where cLitCls_C <> '9090'" queryout .\dat_item_food %option%

::��Ʒ�ײ���Ϣ
bcp "select a.cSuit_C, a.cFood_C, b.cExchange_C, a.nPrc, 0 from kmcyV51.dbo.c_t_food_suit a left join kmcyV51.dbo.c_t_food_suitexchange b on a.cFood_C = b.cFood_C and a.cSuit_C=b.cSuit_C" queryout .\dat_item_food_suit %option%

::��Ʒ���ۼ�
bcp "select cFood_C,nPrc,'SALE',1,'efId1' from kmcyV51.dbo.c_t_food where cLitCls_C <> '9090'" queryout .\dat_food_price %option%

::�䷽��
bcp "SELECT a.cFood_C,a.cFood_N,a.sUnit,a.cItem_C,a.cItem_N,a.sLastUnit,a.sLastUnitRate,a.nLastQty,a.nLastRate,a.nItemQty,a.nItemQty*b.price,a.eItemType,'RESTAURANT' FROM kmcyV51.dbo.s_t_food_directions a, kmcyV51.dbo.bi_t_item_info b WHERE a.cItem_C=b.item_no" queryout .\dat_food_directions %option%

::��Ӧ����Ϣ��
bcp "select rtrim(supcust_no),sup_name,py_str,sup_addr,sup_man,sup_email,sup_tel,sup_fax,'SUPPLIER',other2 from kmcyV51.dbo.bi_t_supcust_info" queryout .\dat_info_supcust %option%