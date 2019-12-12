#!/usr/bin/env python
#coding=utf8

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
import cx_Oracle

#set chinese encoding
# os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.ZHS16GBK'

# open connection to oracle
conn = cx_Oracle.connect('jono/jono@192.168.1.10/orcl')
cursor = conn.cursor()

print('----------------------查询系统选项配置表---------------------')
cursor.execute("select * from d_t0_option")
rows = cursor.fetchall()
param = []
for row in rows:
	print(row)


print('----------------------查询部门基础信息-----------------------')
cursor.execute("select * from d_t2_branch")
rows = cursor.fetchall()
param = []
for row in rows:
	print(row)

cursor.close()
conn.close()

print('******************测试数据库连接完成*******************')
