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

#set chinese encoding
os.environ['NLS_LANG'] = 'SIMPLIFIED CHINESE_CHINA.ZHS16GBK'

#define one day for test date
dt1 = datetime.date(2019, 1, 1)
threeYear = datetime.timedelta(days=1095)

#define seprator for String split
seprator = '|~|'


def readTxtData(txtFile):
    f = open('./storedata/' + txtFile, 'r')
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
    cursor.executemany('insert into ' + table_name + vls, param)

from config import ipAddr, username, password
# open connection to oracle
conn = cx_Oracle.connect(username + '/' + password + '@' + ipAddr + '/jono')
cursor = conn.cursor()

print('----------------------系统选项配置表---------------------')
param = []
param.append(('AMT_TTCNY', 'THEORY'))
param.append(('CLOSE_DATE', '23'))
param.append(('BOX_VOLUME', '200'))
cursor.execute("truncate table d_t0_option")
cursor.executemany("insert into d_t0_option values(:1,:2)", param)

print('----------------------部门基础信息-----------------------')
param = readTxtData('dat_info_branch')
param += readTxtData('dat_info_branch_add')
cursor.execute("truncate table d_t2_branch")
addTableData('d_t2_branch', param)

print('----------------原材料、半成品、出品类别-----------------')
param = readTxtData('dat_cls_raw')
param += readTxtData('dat_cls_semis')
param += readTxtData('dat_cls_food')
#添加PATH字段，现在可以留空，等程序运行时能够自动生成
for cell in param:
    cell.append('')
cursor.execute("truncate table d_t2_item_category")
addTableData('d_t2_item_category', param)

print('----------------------餐厅及中央工厂配方表-----------------------')
param = readTxtData('dat_food_directions')
param += readTxtData('dat_food_directions1')
cursor.execute("truncate table d_t2_therapy")
addTableData("d_t2_therapy", param)

print('----------------------导入图片信息-----------------------')
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

print('----------------原材料、半成品、出品信息-----------------')
param = readTxtData('dat_item_raw')
param += readTxtData('dat_item_semis')
param += readTxtData('dat_item_food')
for cells in param:
    #加入箱子类型
    cells.append('10')
    #加入是否启用标志(0未启用，1已启用)		
    cells.append('1')
	#加入生效日期(出品新增和修改定时生效)
    cells.append('2013-01-01')
cursor.execute("truncate table d_t2_item_meta")
addTableData('d_t2_item_meta', param)
#去掉该表中的规格列中所有空格（导进来已经不是空格而变成了'\0'），因为json不能处理
cursor.execute("update d_t2_item_meta set item_specification='' where item_specification='\0'")
cursor.execute("update d_t2_item_meta set QUERY_CODE='' where QUERY_CODE='\0'")

print('---------------------出品销售价信息----------------------')
param = readTxtData('dat_food_price')
for cells in param:
    #加入价格生效日期
    cells.append(dt1)
    #加入供应商编码
    cells.append('')
cursor.execute("truncate table d_t2_item_price")
addTableData('d_t2_item_price', param)

print('---------------供应商信息--------------------')
param = readTxtData('dat_info_supcust')
addTableData("d_t2_branch", param)

print('---------------供货周期信息--------------------')
param = readTxtData('dat_item_raw')
param2 = []
for cells in param:
    #添加供货周期（自己造的虚拟数据）
    cycle = random.randint(0, 5)
    param2.append(('L01', cells[0], cycle))
    param2.append(('F01', cells[0], cycle + 1))
cursor.execute("truncate table d_t2_supply_cycle")
addTableData("d_t2_supply_cycle", param2)

##########################以下从御商数据库中查询##########################

print('---------------------供应商对应的商品--------------------')
param = readTxtData('dat_sup_item')
newParam = []
for cells in param:
	if cells[0] == "1042": # 中央厨房转换为中央工厂
		cells[01] = "F01"
    cells.append(0)
	newParam.append(cells)
cursor.execute("truncate table d_t2_supplier_branch_item")
addTableData('d_t2_supplier_branch_item', newParam)

print('---------------------商品的多个价格----------------------')
param = readTxtData('dat_item_price')
param2 = []
for cells in param:
    param2.append((cells[0], cells[1], 'PURCHASE', 1, 'efId1', dt1, ''))
    param2.append((cells[0], cells[2], 'BENCHMARK', 1, 'efId1', dt1, ''))
    param2.append((cells[0], cells[3], 'WHOLESALE', 1, 'efId1', dt1, ''))
    param2.append((cells[0], cells[4], 'RETAIL', 1, 'efId1', dt1, ''))
    param2.append((cells[0], cells[5], 'JOIN', 1, 'efId1', dt1, ''))
addTableData('d_t2_item_price', param2)

print('---------------------清理多余的价格数据(御商中的物料比天天中的多)----------------------')
cleanSql = "DELETE FROM D_T2_ITEM_PRICE p2 WHERE p2.ITEM_ID IN ( \
		SELECT p.ITEM_ID FROM JONO.D_T2_ITEM_PRICE p WHERE p.ITEM_ID NOT IN ( \
			SELECT m.ITEM_ID FROM JONO.D_T2_ITEM_META m ) \
		GROUP BY p.ITEM_ID \
	)"
cursor.execute(cleanSql)

print('----------------------各门店的库存量---------------------')
param = readTxtData('dat_branch_stock')
for cells in param:
    lastintime = datetime.datetime.strptime(cells.pop(3), '%Y-%m-%d %H:%M:%S')
    expdate = lastintime + threeYear
    #库位
    cells.append('1')
    #最近入库时间
    cells.append(lastintime)
    #原料有效期
    cells.append(expdate)
f.close()
cursor.execute('truncate table d_t2_storage')
addTableData('d_t2_storage', param)

#####################以下都是自己造的数据#####################


print('--------------------门店多品牌分组---------------------')
param = readTxtData('dat_brand_group')
cursor.execute("truncate table d_t2_brand_group")
addTableData("d_t2_brand_group", param)

print('------------多品牌分组与门店的对应关系-----------------')
param = readTxtData('dat_brand_branch')
cursor.execute("truncate table d_t2_brand_branch")
addTableData("d_t2_brand_branch", param)

print('----------------------价格组---------------------------')
param = readTxtData('dat_price_group')
cursor.execute("truncate table d_t2_price_group")
addTableData("d_t2_price_group", param)

print('-------------价格组与门店的对应关系--------------------')
param = readTxtData('dat_price_group_branch')
cursor.execute("truncate table d_t2_price_group_branch")
addTableData("d_t2_price_group_branch", param)

#各餐厅默认只有一个仓库，且仓库ID与餐厅ID相同，物流中心和中央工厂则有一个分仓
print('-------------------仓库-部门关联表---------------------')
cursor.execute("select branch_id,branch_name from d_t2_branch where branch_type='RESTAURANT'")
rows = cursor.fetchall()
param = []
for row in rows:
    bid = row[0]
    sname = row[1] + '仓库'
    param.append((bid, bid, sname, 0))
param.append(('L01', 'L01A', '长沙物流中心主仓', 0))
param.append(('L01', 'L01B', '长沙物流中心分仓', 1))
param.append(('F01', 'F01A', '长沙中央工厂主仓', 0))
param.append(('F01', 'F01B', '长沙中央工厂分仓', 1))
cursor.execute("truncate table d_t2_branch_storage")
addTableData("d_t2_branch_storage", param)

#物流中心和中央工厂的初始库存量是采用将各个门店的库存量相加的虚拟数据
print('---------------物流中心和中央工厂库存量----------------')
cursor.execute('select item_id, sum(item_count) from d_t2_storage group by item_id')
rows = cursor.fetchall()
param = []
for row in rows:
    itemId = row[0]
    itemCount = row[1]
    #最小库存量为10
    if itemCount < 10:
        itemCount = 10
    #库存量最后不保留小数
    itemCount = round(itemCount, 0)
    lastintime = dt1
    expdate = lastintime + threeYear
    param.append(('L01A', itemId, itemCount*2, '1', lastintime, expdate))#物流中心主仓
    param.append(('L01B', itemId, itemCount, '1', lastintime, expdate))#物流中心分仓
    param.append(('F01A', itemId, itemCount/2, '1', lastintime, expdate))#中央工厂主仓
    param.append(('F01B', itemId, itemCount/3, '1', lastintime, expdate))#中央工厂分仓
addTableData('d_t2_storage', param)

print('-----------------半成品/成品生产周期表-----------------')
#数据自造的假数据：根据每种商品的配送价/12然后四舍五入得到生产周期天数
cursor.execute("select b.item_id, round(b.item_price/12) from d_t2_item_meta a, d_t2_item_price b where a.item_id=b.item_id and a.item_type<>'RAW' and b.price_type='BENCHMARK'")
rows = cursor.fetchall()
param = []
for row in rows:
    param.append(('F01', row[0], row[1]))
cursor.execute("truncate table d_t2_production_cycle")
addTableData("d_t2_production_cycle", param)

print('----------------------配送区域表-----------------------')
#数据自造的假数据，长沙的0天，株洲1天，常德和娄底2天
param = []
param.append(('r01', '长沙', 'L01', 0))
param.append(('r02', '湖南', 'L01', 1))
# param.append(('r03', '外部订货方', 'L01', 2))
cursor.execute("truncate table D_T2_DELIVERY_REGION")
addTableData("D_T2_DELIVERY_REGION", param)

print('-------------------配送区域门店关联表------------------')
#数据自造的假数据，长沙的0天，株洲1天，常德和娄底2天
cursor.execute("select branch_id,branch_address from d_t2_branch where branch_type='RESTAURANT'")
rows = cursor.fetchall()
param = []
for row in rows:
    if row[1] is None:
        param.append(('r02', row[0]))
    elif row[1].startswith('长沙'):
        param.append(('r01', row[0]))
    else:
        param.append(('r02', row[0]))
#插入两个外部订货方以及两个加盟店
# param.append(('r03', 'O01'))
# param.append(('r03', 'O02'))
param.append(('r01', 'J01'))
param.append(('r01', 'J02'))
cursor.execute("truncate table D_T2_DELIVERY_REGION_BRANCH")
addTableData("D_T2_DELIVERY_REGION_BRANCH", param)

cursor.execute("commit")

cursor.close()
conn.close()

print('******************所有数据导入都已经完成*******************')
