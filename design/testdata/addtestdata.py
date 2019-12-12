#!/usr/bin/env python
#coding=utf-8

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# 功能：向数据库插入测试数据
# 作者：cjt
# 时间：2014.7.16
#-------------------------

import os
import datetime
import cx_Oracle

import sys
print(sys.getdefaultencoding())

#define one day for test date
dt1 = datetime.date(2019, 1, 1)

#define seprator for String split
seprator = ','


def addTableData(table_name):
    cursor.execute("select count(column_name) from user_tab_columns where table_name=upper('" + table_name + "')")
    rows = cursor.fetchall()
    columnCount = rows[0][0]
    f = open(table_name + '.csv', 'r')
    #第一行是标题头，略过
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
        #下面的代码最终运行结果为vls=' values(:1,:2,:3,:4,:5,...)'
        vls = ' values(:1'
        for x in range(2, columnCount+1):
            vls += ',:' + str(x)
        vls += ')'
    cursor.executemany('insert into ' + table_name + vls, param)

#open connection to oracle
conn = cx_Oracle.connect('jono/jono@192.168.1.10/orcl')
cursor = conn.cursor()

print('----------------------系统选项配置表---------------------')
param = []
param.append(('AMT_TTCNY', 'THEORY'))
cursor.execute("truncate table d_t0_option")
cursor.executemany("insert into d_t0_option values(:1,:2)", param)

print('----------------------导入部门基础信息---------------------')
addTableData('d_t2_branch')

print('----------------------导入商品类别信息---------------------')
addTableData('d_t2_item_category')

print('----------------------导入商品基础信息---------------------')
addTableData('d_t2_item_meta')

print('----------------------导入商品配方信息---------------------')
addTableData('d_t2_therapy')

print('-------------------导入供应商供货周期信息------------------')
addTableData('d_t2_supply_cycle')

print('------------------导入供应商对应的商品信息-----------------')
addTableData('d_t2_supplier_branch_item')

print('----------------导入半成品/成品生产周期信息----------------')
addTableData('d_t2_production_cycle')

print('----------------------导入配送区域信息---------------------')
addTableData('d_t2_delivery_region')

print('-----------------导入配送区域门店关联信息------------------')
addTableData('d_t2_delivery_region_branch')

print('---------------------导入配送模式信息----------------------')
addTableData('d_t2_delivery_type')

print('---------------------导入配送单位信息----------------------')
addTableData('d_t2_delivery_unit')

print('--------------------导入半成品制程信息---------------------')
addTableData('d_t2_making_process')

print('--------------------导入门店与仓库关联信息---------------------')
addTableData('d_t2_branch_storage')

print('--------------------导入MRP相关变量信息---------------------')
addTableData('d_t2_mrp_amount')

print('-------------------导入商品的多个价格----------------------')
f = open('./d_t2_item_price.csv', 'r')
#第一行是标题头，略过
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
 
print('-----------------导入各门店的库存量信息--------------------')
f = open('d_t2_storage.csv', 'r')
#第一行是标题头，略过
f.readline()
lines = f.readlines()
param = []
dtStr = '%Y/%m/%d'
for line in lines:
    line = line.strip()
    cells = line.split(seprator)
    cells[4] = datetime.datetime.strptime(cells[4], dtStr)
    cells[5] = datetime.datetime.strptime(cells[5], dtStr)
    #最后一列为商品名称，只是方便用户读的，这里要去掉
    cells.pop(6)
    param.append(cells)
f.close()
cursor.execute("truncate table d_t2_storage")
cursor.executemany("insert into d_t2_storage values(:1,:2,:3,:4,:5,:6)", param)

print('------------------------导入图片信息-----------------------')
cursor.execute("truncate table d_t2_pics")
for filename in os.listdir('./pics'):
    filefullname = os.path.join('./pics', filename)
    if os.path.isfile(filefullname):
        picId = os.path.splitext(filename)[0]
        #读取图片文件
        f = open(filefullname, 'rb')
        pic = f.read()
        f.close()
        sqlStr = "insert into d_t2_pics values('%s', :picBlob)" % (picId)
        cursor.setinputsizes(picBlob=cx_Oracle.BLOB)
        cursor.execute(sqlStr, {'picBlob': pic})

cursor.execute("commit")
cursor.close()
conn.close()

print('******************所有数据导入都已经完成*******************')
