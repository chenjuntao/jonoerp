--物流中心已分配量
CREATE OR REPLACE VIEW
    d_v_mrp_a_lc AS
WITH
    a AS
    (
        SELECT
            d.FORM_ID,
            ''FORM_ID2,
            d.ITEM_ID,
            d.ORDER_COUNT
        FROM
            D_T1_REQUEST_HEADER h
        INNER JOIN
            D_T1_REQUEST_DETAIL d
        ON
            h.FORM_ID = d.FORM_ID
        AND h.PURCHASE_STATUS = '已汇总'
        AND h.SHIPPING_STATUS IS NULL
        AND d.DELIVERY_TYPE ='UNIFIED'
    )
    ,
    b AS
    (
        SELECT
            h.FORM_ID,
            '' FORM_ID2,
            d.ITEM_ID,
            d.REQUEST_COUNT
        FROM
            D_T1_SHIPPING_HEADER h
        LEFT JOIN
            D_T1_REQUEST_HEADER rh
        ON
            h.FORM_REF_ID = rh.FORM_ID
        LEFT JOIN
            D_T0_FORM_STATUS s
        ON
            h.FORM_ID = s.FORM_ID
        AND s.IS_CURRENT = 1
        LEFT JOIN
            D_T1_PICKING_REF r
        ON
            h.FORM_ID = r.FORM_REF_ID
        INNER JOIN
            D_T1_SHIPPING_DETAIL d
        ON
            h.FORM_ID = d.FORM_ID
        WHERE
            h.PICK_STATUS IS NULL
        AND h.FORM_TYPE = 'INNER_UNIFIED'
        AND h.PROVIDER_ID = 'L00'
        AND h.FORM_REF_ID IS NOT NULL
        AND s.STATUS IS NOT NULL
        AND rh.PURCHASE_STATUS IS NOT NULL
    )
    ,
    c AS
    (
        SELECT
            sh.FORM_ID,
            pr.FORM_ID FORM_ID2,
            pr.ITEM_ID,
            pr.ITEM_COUNT
        FROM
            D_T1_SHIPPING_HEADER sh
        INNER JOIN
            D_T1_REQUEST_HEADER rh
        ON
            sh.FORM_REF_ID = rh.FORM_ID
        AND rh.PURCHASE_STATUS IS NOT NULL
        INNER JOIN
            D_T1_PICKING_REF pr
        ON
            sh.FORM_ID = pr.FORM_REF_ID
        AND sh.FORM_REF_ID IS NOT NULL
        AND sh.PROVIDER_ID = 'L00'
        AND sh.FORM_TYPE = 'INNER_UNIFIED'
        INNER JOIN
            D_T1_PICKING_HEADER ph
        ON
            ph.FORM_ID = pr.FORM_ID
        AND ph.AUDIT_TIME IS NULL
    )
    ,
    d AS
    (
        SELECT
            *
        FROM
            a
        UNION ALL
        SELECT
            *
        FROM
            b
        UNION ALL
        SELECT
            *
        FROM
            c
    )
    ,
    e AS
    (
        SELECT
            d.form_id,
            d.form_id2,
            d.item_id,
            DECODE(form_id2,NULL,d.ORDER_COUNT,u.DELIVERY_FACTOR*d.ORDER_COUNT) ORDER_COUNT
        FROM
            d
        LEFT JOIN
            D_T2_DELIVERY_UNIT u
        ON
            d.item_id = u.ITEM_ID
    )
SELECT
    item_id,
    SUM(ORDER_COUNT) item_count
FROM
    e
GROUP BY
    item_id