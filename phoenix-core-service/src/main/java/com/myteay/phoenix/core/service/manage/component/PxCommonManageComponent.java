/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component;

import java.util.List;

import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.camp.CampBaseModel;
import com.myteay.phoenix.core.model.manage.PxGoodsAdvModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesDetailModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesNoticeModel;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesSubNoticeModel;
import com.myteay.phoenix.core.model.manage.PxShopModel;
import com.myteay.phoenix.core.model.manage.PxSubPackagesModel;

/**
 * 后台一般性简单业务管理组件
 * 
 * @author min.weixm
 * @version $Id: PxCommonManageComponent.java, v 0.1 Jul 24, 2018 11:58:41 AM min.weixm Exp $
 */
public interface PxCommonManageComponent {

    /**
     * 查询所有店铺模型
     * 
     * @return
     */
    public MtOperateResult<List<PxShopModel>> queryShopAll();

    /**
     * 查询所有已过期店铺模型
     * 
     * @return
     */
    public List<PxShopModel> queryShopExpiredAll();

    /**
     * 查询所有商品摘要模型
     * 
     * @return
     */
    public MtOperateResult<List<PxGoodsModel>> queryGoodsAll();

    /**
     * 查询所有商品摘要模型
     * 
     * @param goodsId
     * @return
     */
    public MtOperateResult<PxGoodsAdvModel> queryGoodsAdvAll(String goodsId);

    /**
     * 通过店铺ID查询商品列表
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<PxGoodsModel>> queryGoodsListByShopId(String shopId);

    /**
     * 通过商品摘要ID查询所有的套餐包
     * 
     * @param goodsId
     * @return
     */
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListByGoodsId(String goodsId);

    /**
     * 通过商品摘要ID查询所有的温馨提醒摘要
     * 
     * @param goodsId
     * @return
     */
    public MtOperateResult<List<PxGoodsPackagesNoticeModel>> queryPackagesNoticeListByGoodsId(String goodsId);

    /**
     * 通过商品摘要ID查询所有的温馨提醒子项
     * 
     * @param packagesNoticeId
     * @return
     */
    public MtOperateResult<List<PxGoodsPackagesSubNoticeModel>> queryPackagesNoticeListByNoticeId(String packagesNoticeId);

    /**
     * 通过商品摘要ID查询所有的商品详情图片列表
     * 
     * @param goodsId
     * @return
     */
    public MtOperateResult<List<PxGoodsPackagesImageModel>> queryPackagesImageListByGoodsId(String goodsId);

    /**
     * 查询所有套餐包
     * 
     * @return
     */
    public MtOperateResult<List<PxGoodsPackagesDetailModel>> queryPackagesDetailListAll();

    /**
     * 通过套餐包ID查询所有的子套餐
     * 
     * @param packagesDetailId
     * @return
     */
    public MtOperateResult<List<PxSubPackagesModel>> querySubPackagesByPackagesId(String packagesDetailId);

    /**
     * 通过店铺ID，查询当前店铺下的所有活动列表
     * 
     * @param shopId
     * @return
     */
    public MtOperateResult<List<CampBaseModel>> queryCampBaseListByShopId(String shopId);
}
