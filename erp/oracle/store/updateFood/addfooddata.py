#!/usr/bin/env python
#coding=gbk

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# ���ܣ�����SQLSERVER�е����������ļ�ͨ��python�ű������Oracle��
# ���ߣ�cjt
# ʱ�䣺2014.7.16
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
        #����Ĵ����������н��Ϊvls=' values(:1,:2,:3,:4,:5,...)'
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

print('----------------��Ʒ���-----------------')
param = readTxtData('dat_cls_food')
#���PATH�ֶΣ����ڿ������գ��ȳ�������ʱ�ܹ��Զ�����
for cell in param:
    cell.append('')
cursor.execute("delete from d_t2_item_category where ITEM_TYPE = 'PRODUCT'")
addTableData('d_t2_item_category', param)

print('----------------��Ʒ��Ϣ-----------------')
param = readTxtData('dat_item_food')
for cells in param:
    #������������
    cells.append('10')
    #�����Ƿ����ñ�־(0δ���ã�1������)		
    cells.append(1)
	#������Ч����(��Ʒ�������޸Ķ�ʱ��Ч)
    cells.append(dt1)
cursor.execute("delete from d_t2_item_meta where ITEM_TYPE = 'PRODUCT'")
addTableData('d_t2_item_meta', param)
#ȥ���ñ��еĹ���������пո񣨵������Ѿ����ǿո�������'\0'������Ϊjson���ܴ���
cursor.execute("update d_t2_item_meta set item_specification='' where item_specification='\0'")
cursor.execute("update d_t2_item_meta set QUERY_CODE='' where QUERY_CODE='\0'")

print('---------------------��Ʒ�ײ���Ϣ----------------------')
param = readTxtData('dat_item_food_suit')
cursor.execute("truncate table d_t2_item_food_suit")
addTableData('d_t2_item_food_suit', param)

# print('---------------------��Ʒ���ۼ���Ϣ----------------------')
# param = readTxtData('dat_food_price')
# for cells in param:
#     #����۸���Ч����
#     cells.append(dt1)
#     #���빩Ӧ�̱���
#     cells.append('')
# cursor.execute("truncate table d_t2_item_price")
# addTableData('d_t2_item_price', param)

print('----------------------�ɱ�����Ϣ-----------------------')
param = readTxtData('dat_food_directions')
cursor.execute("delete from d_t2_therapy where owner='RESTAURANT'")
addTableData("d_t2_therapy", param)

cursor.execute("commit")

cursor.close()
conn.close()

print('******************�������ݵ��붼�Ѿ����*******************')
