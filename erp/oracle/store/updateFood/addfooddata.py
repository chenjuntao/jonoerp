#!/usr/bin/env python
#coding=gbk

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# 功能：将从SQLSERVER中导出的数据文件通过python脚本导入进Oracle中
# 作者：cjt
# 时间：2014.7.16
#-------------------------

import os
import datetime
import random
import cx_Oracle

#---------------------------------------------------------------------------
ipAddr = "10.1.1.105"
username = "jono"
password = "jono"
sid = "jono"
#---------------------------------------------------------------------------

#set chinese encoding
os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.ZHS16GBK'

#define one day for test date
dt1 = datetime.date(2014, 1, 1)
threeYear = datetime.timedelta(days=1095)

#define seprator for String split
seprator = '|~|'


def readTxtData(txtFile):
    f = open(txtFile, 'r')
    lines = f.readlines()
    param = []
    for line in lines:
        line = line.strip()
        cells = line.split(seprator)
        param.append(cells)
    f.close()
    return param


def addTableData(table_name, param):
    cursor.execute("select count(column_name) from user_tab_columns where table_name=upper('" + table_name + "')")
    rows = cursor.fetchall()
    columnCount = rows[0][0]
    if(len(param) > 0):
        #下面的代码最终运行结果为vls=' values(:1,:2,:3,:4,:5,...)'
        vls = ' values(:1'
        for x in range(2, columnCount+1):
            vls += ',:' + str(x)
        vls += ')'
    for para in param:
        cursor.execute('insert into ' + table_name + vls, para)
    # cursor.executemany('insert into ' + table_name + vls, param)

# open connection to oracle
conn = cx_Oracle.connect(username + '/' + password + '@' + ipAddr + '/' + sid)
cursor = conn.cursor()

print('----------------出品类别-----------------')
param = readTxtData('dat_cls_food')
#添加PATH字段，现在可以留空，等程序运行时能够自动生成
for cell in param:
    cell.append('')
cursor.execute("delete from d_t2_item_category where ITEM_TYPE = 'PRODUCT'")
addTableData('d_t2_item_category', param)

print('----------------出品信息-----------------')
param = readTxtData('dat_item_food')
for cells in param:
    #加入箱子类型
    cells.append('10')
    #加入是否启用标志(0未启用，1已启用)		
    cells.append(1)
	#加入生效日期(出品新增和修改定时生效)
    cells.append(dt1)
cursor.execute("delete from d_t2_item_meta where ITEM_TYPE = 'PRODUCT'")
addTableData('d_t2_item_meta', param)
#去掉该表中的规格列中所有空格（导进来已经不是空格而变成了'\0'），因为json不能处理
cursor.execute("update d_t2_item_meta set item_specification='' where item_specification='\0'")
cursor.execute("update d_t2_item_meta set QUERY_CODE='' where QUERY_CODE='\0'")

print('---------------------出品套餐信息----------------------')
param = readTxtData('dat_item_food_suit')
cursor.execute("truncate table d_t2_item_food_suit")
addTableData('d_t2_item_food_suit', param)

# print('---------------------出品销售价信息----------------------')
# param = readTxtData('dat_food_price')
# for cells in param:
#     #加入价格生效日期
#     cells.append(dt1)
#     #加入供应商编码
#     cells.append('')
# cursor.execute("truncate table d_t2_item_price")
# addTableData('d_t2_item_price', param)

print('----------------------成本卡信息-----------------------')
param = readTxtData('dat_food_directions')
cursor.execute("delete from d_t2_therapy where owner='RESTAURANT'")
addTableData("d_t2_therapy", param)

cursor.execute("commit")

cursor.close()
conn.close()

print('******************所有数据导入都已经完成*******************')
