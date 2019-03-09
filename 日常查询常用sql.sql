/**
 * 查询当前店铺总销量
 */
select cast(sum(goods_price * seller_amount) as decimal (19, 2)) as sumComm from px_goods_order_out where shop_id=24


/**
 * 查询当前店铺单日销量
 */
select * from (
select cast(sum(goods_price * seller_amount) as decimal (19, 2)) as sumComm, date_format(gmt_created,'%Y-%c-%d') sum_date  from px_goods_order_out group by sum_date
) as a where sum_date = '2019-3-08'

-- 查询用户是否下单成功
select * from (
select *,date_format(gmt_created,'%Y-%c-%d') sum_date from px_goods_order_out) as a where sum_date = '2019-3-08' order by gmt_created desc

-- 查询一周订单情况
select * from px_goods_order_out where gmt_created between '2019-3-04' and '2019-3-11'

-- 查询一周销量
select cast(sum(goods_price * seller_amount) as decimal (19, 2)) as sumComm  from px_goods_order_out where gmt_created between '2019-3-04' and '2019-3-11'

-- 查询一周内每天的销售情况
select cast(sum(goods_price * seller_amount) as decimal (19, 2)) as sumComm , date_format(gmt_created,'%Y-%c-%d') sum_date 
from px_goods_order_out where gmt_created between '2019-3-04' and '2019-3-11'  group by sum_date desc


-- 查询冰淇淋的单日销量
select * from (
select *,date_format(gmt_created,'%Y-%c-%d') sum_date from px_goods_order_out) as a where sum_date = '2019-3-06' and goods_title like '%冰淇淋%' order by gmt_created desc


-- 根据商品类型对商品进行统计分析
select cast(sum(goods_price * seller_amount) as decimal (19, 2)) as sumComm, date_format(gmt_created,'%Y-%c-%d') sum_date, goods_type  from px_goods_order_out group by sum_date,goods_type

update px_goods_order_out set seller_amount = '1'

update px_goods_order_out set pay_type = 'PX_CASH_PAY'




