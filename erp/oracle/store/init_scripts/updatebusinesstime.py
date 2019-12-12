#!/usr/bin/env python
#coding=gb2312

#-------------------------
# Copyright (c) 2014
# Tanry Electronic Technology Co., Ltd.
# ChangSha, China
# All Rights Reserved.
# 功能：将从SQLSERVER中导出的数据文件通过python脚本导入进Oracle中
# 作者：cjt
# 时间：2014.7.16
#-------------------------

import cx_Oracle
import datetime

#define one day for test date
dt0 = datetime.date.today()
dt1 = datetime.date(2014, 1, 1)

from config import ipAddr, username, password
# open connection to oracle
conn = cx_Oracle.connect(username + '/' + password + '@' + ipAddr + '/jono')
cursor = conn.cursor()

print('-------------------------更新各个餐厅营业时间表------------------------')
param = []
cursor.execute("select branch_id from d_t2_branch")
rows = cursor.fetchall()
for row in rows:
    param.append((row[0], dt1))

cursor.execute("truncate table d_t0_business_date")
cursor.executemany("insert into d_t0_business_date values(:1,:2)", param)

cursor.execute("commit")

cursor.close()
conn.close()
print('*********************各个餐厅营业时间更新完成*********************')
