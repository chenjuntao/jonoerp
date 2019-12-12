#!/usr/bin/env python
#coding=gb2312

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# ���ܣ�����ǰ��form�����е����������ļ����µ������ݿ����
# ���ߣ�cjt
# ʱ�䣺2014.9.11
#-------------------------

import cx_Oracle
import datetime

#define seprator for String split
seprator = '|~|'


#�ַ���ת��Ϊ�������ͻ�����������
def dataTypeConvert(datstr):
    if datstr == '(none)':
        return None
    elif datstr[:5] == '(int)':
        return int(datstr[5:])
    elif datstr[:7] == '(float)':
        return float(datstr[7:])
    elif datstr[:6] == '(date)':
        return datetime.datetime.strptime(datstr[6:], '%Y-%m-%d %H:%M:%S')
    else:
        return datstr


def addTableData(table):
    print('-------------------------' + table + '------------------------')
    f = open('./formdata/' + table, 'r')
    lines = f.readlines()
    param = []
    for line in lines:
        line = line.strip()
        cells = line.split(seprator)
        #��������ڣ��ͽ��ַ���ת��Ϊ��������
        for i in range(len(cells)):
            cells[i] = dataTypeConvert(cells[i])
        param.append(cells)
        print(param)
    f.close()

    cursor.execute('truncate table ' + table)
    if(len(param) > 0):
        #����Ĵ����������н��Ϊvls=' values(:1,:2,:3,:4,:5,...)'
        vls = ' values(:1'
        for x in range(2, len(param[0])+1):
            vls += ',:' + str(x)
        vls += ')'
        cursor.executemany('insert into ' + table + vls, param)

#open connection to oracle
conn = cx_Oracle.connect('jono/jono@10.1.1.105/jono')
cursor = conn.cursor()

addTableData('D_T0_FORM_META')
addTableData('D_T0_FORM_STATUS')
addTableData('D_T0_FORM_LOCK')
addTableData('D_T0_BUSINESS_DATE')
addTableData('D_T1_REQUEST_TEMPLATE_META')
addTableData('D_T1_REQUEST_TEMPLATE_ITEM')
'''
addTableData('D_T1_REQUEST_DETAIL')
addTableData('D_T1_PURCHASING_HEADER')
addTableData('D_T1_PURCHASING_DETAIL')
addTableData('D_T1_INPUT_HEADER')
addTableData('D_T1_INPUT_DETAIL')
addTableData('D_T1_SHIPPING_HEADER')
addTableData('D_T1_SHIPPING_DETAIL')
addTableData('D_T1_SHIPPING_ANTIAUDIT_HEADER')
addTableData('D_T1_SHIPPING_ANTIAUDIT_DETAIL')
addTableData('D_T1_RETURN_GOODS_HEADER')
addTableData('D_T1_RETURN_GOODS_DETAIL')
addTableData('D_T1_LOSS_HEADER')
addTableData('D_T1_LOSS_DETAIL')
addTableData('D_T1_TRANSFER_HEADER')
addTableData('D_T1_TRANSFER_DETAIL')
'''

cursor.execute("commit")

cursor.close()
conn.close()
print('*********************�������ݵ��붼�Ѿ����*********************')
