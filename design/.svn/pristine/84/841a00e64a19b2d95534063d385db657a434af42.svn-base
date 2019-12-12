#!/usr/bin/env python
#coding=gb2312

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# 功能：将form中的数据读取出来并备份存储在文本文件中
# 作者：cjt
# 时间：2014.9.10
#-------------------------

import cx_Oracle
import datetime
import types

#define seprator for String split
seprator = '|~|'


#数据类型转换
#将其他类型的数据转换为字符串格式
#并分别加上前缀(int)、(date)等
def dataTypeConvert(dat):
    if dat is None:
        return '(none)'
    elif type(dat) is types.IntType:
        return '(int)' + str(dat)
    elif type(dat) is types.FloatType:
        return '(float)' + str(dat)
    elif type(dat) is datetime.datetime:
        return '(date)' + str(dat)
    else:
        return dat


def saveTableData(table):
    print('-------------------------' + table + '------------------------')
    cursor.execute('select * from ' + table)
    lines = []
    rows = cursor.fetchall()
    for row in rows:
        line = ''
        for x in row:
            line += seprator + dataTypeConvert(x)
        line = line[3:]
        line += '\n'
        lines.append(line)
    f = open('./formdata/' + table, 'w')
    f.writelines(lines)
    f.close()

#open connection to oracle
conn = cx_Oracle.connect('jono/jono@10.1.1.105/jono')
cursor = conn.cursor()

saveTableData('D_T0_FORM_META')
saveTableData('D_T0_FORM_STATUS')
saveTableData('D_T0_FORM_LOCK')
saveTableData('D_T0_BUSINESS_DATE')
saveTableData('D_T1_REQUEST_TEMPLATE_META')
saveTableData('D_T1_REQUEST_TEMPLATE_ITEM')
saveTableData('D_T1_REQUEST_DETAIL')
saveTableData('D_T1_PURCHASING_HEADER')
saveTableData('D_T1_PURCHASING_DETAIL')
saveTableData('D_T1_INPUT_HEADER')
saveTableData('D_T1_INPUT_DETAIL')
saveTableData('D_T1_SHIPPING_HEADER')
saveTableData('D_T1_SHIPPING_DETAIL')
saveTableData('D_T1_SHIPPING_ANTIAUDIT_HEADER')
saveTableData('D_T1_SHIPPING_ANTIAUDIT_DETAIL')
saveTableData('D_T1_RETURN_GOODS_HEADER')
saveTableData('D_T1_RETURN_GOODS_DETAIL')
saveTableData('D_T1_LOSS_HEADER')
saveTableData('D_T1_LOSS_DETAIL')
saveTableData('D_T1_TRANSFER_HEADER')
saveTableData('D_T1_TRANSFER_DETAIL')

cursor.close()
conn.close()
print('*********************所有数据保存都已经完成*********************')
