-- 物流中心在途
CREATE OR REPLACE VIEW
    d_v_mrp_o_lc AS
WITH
    a AS
    (
        SELECT
            d.FORM_ID,
            d.ITEM_ID,
            d.ITEM_COUNT,
            id.FORM_ID ref_form_id,
            id.RECEIVE_COUNT
        FROM
            D_T1_PURCHASING_HEADER h
        INNER JOIN
            D_T0_FORM_STATUS s
        ON
            h.FORM_ID = s.FORM_ID
        AND s.IS_CURRENT = 1
        AND s.STATUS IN ('已审核',
                         '已入库')
        AND h.DELIVERY_TYPE = 'UNIFIED'
        INNER JOIN
            D_T1_PURCHASING_DETAIL d
        ON
            h.FORM_ID = d.FORM_ID
        AND h.REQUESTER_ID = 'L00'
        AND h.PROVIDER_ID != 'F00'
        LEFT JOIN
            D_T1_INPUT_HEADER ih
        ON
            d.FORM_ID = ih.FORM_REF_ID
        AND ih.AUDIT_TIME IS NULL
        AND ih.FORM_TYPE != 'MANUAL'
        LEFT JOIN
            D_T1_INPUT_DETAIL id
        ON
            ih.FORM_ID = id.FORM_ID
        AND d.ITEM_ID = id.ITEM_ID
    )
    ,
    b AS
    (
        SELECT
            t.ITEM_ID,
            DECODE(SIGN(t.ITEM_COUNT - SUM(NVL(t.RECEIVE_COUNT,0))),-1,0,t.ITEM_COUNT - SUM(NVL
            (t.RECEIVE_COUNT,0))) item_count ,
            listagg('('||t.ref_form_id ||','||t.RECEIVE_COUNT ||')',',') within GROUP (ORDER BY
            t.ITEM_COUNT) infos
        FROM
            a t
        GROUP BY
            t.FORM_ID,
            t.ITEM_ID,
            t.ITEM_COUNT
    )
    ,
    c AS
    (
        SELECT
            d.ITEM_ID,
            SUM(d.ITEM_COUNT) ITEM_COUNT
        FROM
            D_T1_PURCHASING_HEADER h
        INNER JOIN
            D_T0_FORM_STATUS s
        ON
            h.FORM_ID = s.FORM_ID
        AND s.STATUS IN( '已审核',
                        '已入库')
        AND s.IS_CURRENT = 1
        AND h.REQUESTER_ID = 'L00'
        AND h.PROVIDER_ID = 'F00'
        AND h.DELIVERY_TYPE = 'UNIFIED'
        AND h.AUDIT_TIME IS NOT NULL
        AND h.PLAN_STATUS = '已汇总'
		AND h.OUT_STATUS is null
        INNER JOIN
            D_T1_PURCHASING_DETAIL d
        ON
            h.FORM_ID = d.FORM_ID
        LEFT JOIN
            D_T1_SHIPPING_HEADER sh
        ON
            h.FORM_ID = sh.FORM_REF_ID
        AND sh.INPUT_TIME IS NULL
        GROUP BY
            d.ITEM_ID
    )
    ,
    d AS
    (
        SELECT
            item_id,
            item_count
        FROM
            b
        UNION ALL
        SELECT
            *
        FROM
            c
    )
SELECT
    item_id,
    SUM(item_count)item_count
FROM
    d
GROUP BY
    item_id