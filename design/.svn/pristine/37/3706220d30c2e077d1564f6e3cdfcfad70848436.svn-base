-- 查询mrp计算所需的各个量
-- 净需求=毛需求+已分配量+安全库存
-- -实际库存-在途量
CREATE OR REPLACE VIEW
    d_v_mrp_amount_cf AS
WITH
    cf AS
    (
        SELECT
            b.BRANCH_ID
        FROM
            JONO.D_T2_BRANCH b
        WHERE
            b.BRANCH_TYPE = 'CENTRALFACTORY'
    )
    ,
    real_count AS--遍历所有下属仓库查询库存
    (
        SELECT
            bs.BRANCH_ID,
            st.ITEM_ID,
            SUM(st.ITEM_COUNT) AS real_count
        FROM
            D_T2_STORAGE st
        RIGHT JOIN
            JONO.D_T2_BRANCH_STORAGE bs
        ON
            bs.STORAGE_ID = st.STORAGE_ID
        INNER JOIN
            cf
        ON
            cf.BRANCH_ID = bs.BRANCH_ID
        GROUP BY
            bs.BRANCH_ID,
            st.ITEM_ID
    )
SELECT
    cf.BRANCH_ID,
    i.ITEM_ID,
    ROUND(r.real_count, 2) AS real_count,
    m.ALLOCATION,
    m.ON_THE_WAY,
    s.SUPPLY_CYCLE as period,
    NVL(s.INVENTORYS_CYCLE,0) as inventorysCycle,
    sbi.SUPPLIER_ID,
    pc.PRODUCTION_CYCLE,
    u.MIN_ORDER_COUNT
FROM
    JONO.D_T2_ITEM_META i
CROSS JOIN
    cf
LEFT JOIN
    real_count r
ON
    r.BRANCH_ID = cf.BRANCH_ID
AND i.ITEM_ID = r.ITEM_ID
LEFT JOIN
    D_T2_MRP_AMOUNT m
ON
    m.BRANCH_ID = cf.BRANCH_ID
AND m.ITEM_ID = i.ITEM_ID
LEFT JOIN
    JONO.D_T2_SUPPLY_CYCLE s
ON
    s.ITEM_ID = i.ITEM_ID
AND s.BRANCH_ID = cf.BRANCH_ID
LEFT JOIN
    JONO.D_T2_SUPPLIER_BRANCH_ITEM sbi
ON
    cf.BRANCH_ID = sbi.BRANCH_ID
AND sbi.ITEM_ID = i.ITEM_ID
AND sbi.PRIORITY = 0
LEFT JOIN
    JONO.D_T2_PRODUCTION_CYCLE pc
ON
    pc.BRANCH_ID = sbi.SUPPLIER_ID
AND sbi.ITEM_ID = pc.ITEM_ID
LEFT JOIN
    D_T2_DELIVERY_UNIT u
ON
    u.ITEM_ID = i.ITEM_ID
WHERE
    i.ITEM_TYPE IN ('RAW',
                    'SEMIS')
ORDER BY
    cf.BRANCH_ID,
    i.ITEM_ID