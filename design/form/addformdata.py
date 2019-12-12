#!/usr/bin/env python
#coding=gb2312

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# 功能：将以前从form类表格中导出的数据文件重新导入数据库表中
# 作者：cjt
# 时间：2014.9.11
#-------------------------

import cx_Oracle
import datetime

#define seprator for String split
seprator = '|~|'


#字符串转换为数字类型或者日期类型
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
        #如果是日期，就将字符串转换为日期类型
        for i in range(len(cells)):
            cells[i] = dataTypeConvert(cells[i])
        param.append(cells)
        print(param)
    f.close()

    cursor.execute('truncate table ' + table)
    if(len(param) > 0):
        #下面的代码最终运行结果为vls=' values(:1,:2,:3,:4,:5,...)'
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
print('*********************所有数据导入都已经完成*********************')
