#!/usr/bin/env python
#coding=gbk

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# ���ܣ������ݿ�����������
# ���ߣ�cjt
# ʱ�䣺2014.7.16
#-------------------------

import os
import datetime
import cx_Oracle

#define one day for test date
dt1 = datetime.date(2014, 1, 1)

#define seprator for String split
seprator = ','


def addTableData(table_name):
    cursor.execute("select count(column_name) from user_tab_columns where table_name=upper('" + table_name + "')")
    rows = cursor.fetchall()
    columnCount = rows[0][0]
    f = open(table_name + '.csv', 'r')
    #��һ���Ǳ���ͷ���Թ�
    f.readline()
    lines = f.readlines()
    param = []
    for line in lines:
        line = line.strip()
        cells = line.split(seprator)
        param.append(cells[0:columnCount])
    f.close()
    cursor.execute('truncate table ' + table_name)
    if(len(param) > 0):
        #����Ĵ����������н��Ϊvls=' values(:1,:2,:3,:4,:5,...)'
        vls = ' values(:1'
        for x in range(2, columnCount+1):
            vls += ',:' + str(x)
        vls += ')'
    cursor.executemany('insert into ' + table_name + vls, param)

#open connection to oracle
conn = cx_Oracle.connect('jono/jono@10.1.1.105/jono')
cursor = conn.cursor()

print('----------------------ϵͳѡ�����ñ�---------------------')
param = []
param.append(('AMT_TTCNY', 'THEORY'))
cursor.execute("truncate table d_t0_option")
cursor.executemany("insert into d_t0_option values(:1,:2)", param)

print('----------------------���벿�Ż�����Ϣ---------------------')
addTableData('d_t2_branch')

print('----------------------������Ʒ�����Ϣ---------------------')
addTableData('d_t2_item_category')

print('----------------------������Ʒ������Ϣ---------------------')
addTableData('d_t2_item_meta')

print('----------------------������Ʒ�䷽��Ϣ---------------------')
addTableData('d_t2_therapy')

print('-------------------���빩Ӧ�̹���������Ϣ------------------')
addTableData('d_t2_supply_cycle')

print('------------------���빩Ӧ�̶�Ӧ����Ʒ��Ϣ-----------------')
addTableData('d_t2_supplier_branch_item')

print('----------------������Ʒ/��Ʒ����������Ϣ----------------')
addTableData('d_t2_production_cycle')

print('----------------------��������������Ϣ---------------------')
addTableData('d_t2_delivery_region')

print('-----------------�������������ŵ������Ϣ------------------')
addTableData('d_t2_delivery_region_branch')

print('---------------------��������ģʽ��Ϣ----------------------')
addTableData('d_t2_delivery_type')

print('---------------------�������͵�λ��Ϣ----------------------')
addTableData('d_t2_delivery_unit')

print('--------------------������Ʒ�Ƴ���Ϣ---------------------')
addTableData('d_t2_making_process')

print('--------------------�����ŵ���ֿ������Ϣ---------------------')
addTableData('d_t2_branch_storage')

print('--------------------����MRP��ر�����Ϣ---------------------')
addTableData('d_t2_mrp_amount')

print('-------------------������Ʒ�Ķ���۸�----------------------')
f = open('./d_t2_item_price.csv', 'r')
#��һ���Ǳ���ͷ���Թ�
f.readline()
lines = f.readlines()
param = []
for line in lines:
    line = line.strip()
    cells = line.split(seprator)
    if cells[1] != '':
        param.append((cells[0], cells[1], 'PURCHASE_PRICE', 1, 'efId1', dt1))
    if cells[2] != '':
        param.append((cells[0], cells[2], 'DISTRIBUTE_PRICE', 1, 'efId1', dt1))
    if cells[3] != '':
        param.append((cells[0], cells[3], 'WHOLESALE_PRICE', 1, 'efId1', dt1))
    if cells[4] != '':
        param.append((cells[0], cells[4], 'SALE_PRICE', 1, 'efId1', dt1))
    if cells[5] != '':
        param.append((cells[0], cells[5], 'VIP_PRICE', 1, 'efId1', dt1))
    if cells[6] != '':
        param.append((cells[0], cells[6], 'COST_PRICE', 1, 'efId1', dt1))
f.close()
cursor.execute("truncate table d_t2_item_price")
cursor.executemany("insert into d_t2_item_price values(:1,:2,:3,:4,:5,:6)", param)
 
print('-----------------������ŵ�Ŀ������Ϣ--------------------')
f = open('d_t2_storage.csv', 'r')
#��һ���Ǳ���ͷ���Թ�
f.readline()
lines = f.readlines()
param = []
dtStr = '%Y/%m/%d'
for line in lines:
    line = line.strip()
    cells = line.split(seprator)
    cells[4] = datetime.datetime.strptime(cells[4], dtStr)
    cells[5] = datetime.datetime.strptime(cells[5], dtStr)
    #���һ��Ϊ��Ʒ���ƣ�ֻ�Ƿ����û����ģ�����Ҫȥ��
    cells.pop(6)
    param.append(cells)
f.close()
cursor.execute("truncate table d_t2_storage")
cursor.executemany("insert into d_t2_storage values(:1,:2,:3,:4,:5,:6)", param)

print('------------------------����ͼƬ��Ϣ-----------------------')
cursor.execute("truncate table d_t2_pics")
for filename in os.listdir('./pics'):
    filefullname = os.path.join('./pics', filename)
    if os.path.isfile(filefullname):
        picId = os.path.splitext(filename)[0]
        #��ȡͼƬ�ļ�
        f = open(filefullname, 'rb')
        pic = f.read()
        f.close()
        sqlStr = "insert into d_t2_pics values('%s', :picBlob)" % (picId)
        cursor.setinputsizes(picBlob=cx_Oracle.BLOB)
        cursor.execute(sqlStr, {'picBlob': pic})

cursor.execute("commit")
cursor.close()
conn.close()

print('******************�������ݵ��붼�Ѿ����*******************')
