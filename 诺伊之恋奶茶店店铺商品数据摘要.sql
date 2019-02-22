select
    a.goods_title '单品或套餐名', a.goods_image '参考图片', a.goods_desc '分类', a.goods_price '活动售价', 
    a.goods_comm_price '原价', a.goods_online_time '营业时间', 
    (case when a.goods_type = 'PX_GOODS_CY' then '茶饮' else
            ( case when a.goods_type = 'PX_GOODS_BQL' then '冰淇淋' else
                    ( case when a.goods_type = 'PX_GOODS_NC' then '奶茶' else
                            ( case when a.goods_type = 'PX_GOODS_CR_CM_PKG' then '成人普通套餐' else
                                    ( case when a.goods_type = 'PX_GOODS_YX_PKG' then '夜宵套餐' else
                                            ( case when a.goods_type = 'PX_GOODS_PS' then '配餐' else
                                                    ( case when a.goods_type = 'PX_GOODS_HB' then '主食' else a.goods_type end
                                                    )end
                                            )end
                                    )end
                            )end
                    )end
            )end) '套餐类型', 
    (case when a.is_quan='PX_HAS_QUAN' then '支持优惠券' end) '是否支持优惠券', b.package_detail_name '子套餐类别', 
    c.sub_packages_name '子套餐名称', c.sub_packages_amount '子套餐数量', c.sub_package_price '子套餐价格'
from px_goods a , px_goods_packages_detail b, px_sub_packages c
where
    a.shop_id='24' and a.goods_id = b.goods_id and b.packages_detail_id = c.packages_detail_id
order by a.goods_title,b.package_detail_name desc