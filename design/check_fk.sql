--添加外键约束
-- alter table d_t2_item_meta 
-- add constraint fk_item_category
--     foreign key(CATEGORY_ID)
--     references d_t2_item_category(CATEGORY_ID)
--     on delete cascade;

--检查外键约束------------------------------------------------------------------------

--出品——>出品类别
select * from d_t2_item_meta where CATEGORY_ID not in (select CATEGORY_ID from d_t2_item_category);

