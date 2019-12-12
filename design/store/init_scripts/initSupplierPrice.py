#!/usr/bin/env python
# coding=gbk

#-------------------------
# Copyright (c) 2015
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# ���ܣ���ʼ�����еĹ�Ӧ�̼۸����ݣ�Ҫ��������ԭ�ϡ��۸��൱��ʱ
# ���ߣ�liyzh
# ʱ�䣺2015.5.14
#-------------------------

import os 
os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.ZHS16GBK'

import cx_Oracle
from config import ipAddr, username, password
# open connection to oracle
conn = cx_Oracle.connect(username + '/' + password + '@' + ipAddr + '/jono')
cursor = conn.cursor()

# Ĭ��ʹ�ý����۳�ʼ����Ӧ�̵ļ۸�
priceSql = "SELECT item_price FROM JONO.d_t2_item_price p \
    where p.item_id = :1 and p.price_type = 'PURCHASE'"
def getPurchasePrice(itemId):
#     print itemId, priceSql
    cursor.execute(priceSql, (itemId, )) # ����������Ż���Ҫ����
    # �ж��ŵꡢԭ���빩Ӧ�����ߵĹ�ϵ�����Ƿ���ڣ���������ڣ���ʼ��һ��ֵ
    row = cursor.fetchone()
    if row == None: # ��һ����ԭ�ϴ��ڣ�����û�вɹ��ۣ���˵�����������̵����ݲ����Ǵ�С����ϵ�����ǽ���֮����Ի����ڲ�ͬ�����ݣ�����ܻ������µ����⣩
        return 0
    return row[0]

# ����ԭ����
materailSql = "select m.item_id, c.category_name from JONO.D_T2_ITEM_META m \
    inner join d_t2_item_category c on c.category_id = m.category_id \
    where m.ITEM_TYPE IN ('RAW', 'SEMIS')"
# �ж��ŵꡢԭ���빩Ӧ�����ߵĹ�ϵ�����Ƿ���ڣ���������ڣ���ʼ��һ��ֵ
selSupplierSql = "SELECT sbi.SUPPLIER_ID FROM D_T2_SUPPLIER_BRANCH_ITEM sbi \
    WHERE sbi.ITEM_ID = :1 AND sbi.BRANCH_ID = :2"
# ��������ڣ���ʼ��һ��ֵ
insertSql = "insert into  D_T2_SUPPLIER_BRANCH_ITEM(branch_id, item_id, supplier_id, PRIORITY) values (:1, :2, :3, 0)"
# ��ʼ����Ӧ�̼۸�
insertPriceSql = "insert into JONO.d_t2_item_price(item_id, price_type, supplier_id, item_price, is_current) values(:1, 'SUPPLIER', :2, :3, 1)"


insertArgs = []
insertPriceArgs = []

def scanRelation(itemId, branchId):
    cursor.execute(selSupplierSql, (itemId, branchId))
    # �ж��ŵꡢԭ���빩Ӧ�����ߵĹ�ϵ�����Ƿ���ڣ���������ڣ���ʼ��һ��ֵ
    rows = cursor.fetchall()
    if rows: # if not empty
        for row in rows:
            supplierId = row[0]
            insertPriceArgs.append((itemId, supplierId, getPurchasePrice(itemId)))
    else:
        # Ĭ�Ϲ�Ӧ��
        supplierId = "1001"
        insertArgs.append((branchId, itemId, supplierId))
#         print itemId
        insertPriceArgs.append((itemId, supplierId, getPurchasePrice(itemId)))

# ��չ�Ӧ�̼۸����ݣ�����װ��
cursor.execute("delete from d_t2_item_price where supplier_id is not null")

cursor.execute(materailSql)
rows = cursor.fetchall()
count = 0
for row in rows:
    itemId = row[0]
#     print itemId
    # �����������ĵ�����
    branchId = "L01"
    scanRelation(itemId, branchId)
    count += 1
    if count % 200 == 0:
        print count # show progress, give hope

# ���������ϵ������
cursor.executemany(insertSql, insertArgs)
# �������빩Ӧ�̼۸�����
cursor.executemany(insertPriceSql, insertPriceArgs)
cursor.execute("commit")

cursor.close()
conn.close()

print('---------------------��ʼ�����еĹ�Ӧ�̼۸����ݳɹ���----------------------')
