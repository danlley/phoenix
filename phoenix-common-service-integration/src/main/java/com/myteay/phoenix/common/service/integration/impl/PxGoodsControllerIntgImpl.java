/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.service.integration.impl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.myteay.phoenix.common.service.integration.PxGoodsControllerIntg;
import com.myteay.phoenix.common.util.HttpClientUtil;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsAdvModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 集成数据中心服务
 * 
 * @author min.weixm
 * @version $Id: PxGoodsControllerIntgImpl.java, v 0.1 Jun 11, 2019 7:13:12 PM min.weixm Exp $
 */
public class PxGoodsControllerIntgImpl implements PxGoodsControllerIntg {

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsControllerIntg#queryGoodsAll()
     */
    @Override
    public MtOperateResult<List<PxGoodsModel>> queryGoodsAll() {
        String url = "http://localhost:40051/myteay/api/phoenix/admin/manage/goods/all";

        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsModel>> obj = (MtOperateResult<List<PxGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsControllerIntg#queryGoodsListByShopId(java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsModel>> queryGoodsListByShopId(String shopId) {
        String url = "http://localhost:40051/myteay/api/phoenix/admin/manage/goods/list/shop/" + shopId;
        String result = HttpClientUtil.insureResponseGet(url);
        MtOperateResult<List<PxGoodsModel>> obj = (MtOperateResult<List<PxGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsControllerIntg#queryGoodsAdvAll(java.lang.String)
     */
    @Override
    public MtOperateResult<PxGoodsAdvModel> queryGoodsAdvAll(String goodsId) {
        String url = "http://localhost:40051/myteay/api/phoenix/admin/manage/goods/query/goods/adv/" + goodsId;
        String result = HttpClientUtil.insureResponsePost(url, null);
        MtOperateResult<PxGoodsAdvModel> obj = (MtOperateResult<PxGoodsAdvModel>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<PxGoodsAdvModel>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsControllerIntg#findPxShopOnlineGoodsByCondition(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public MtOperateResult<List<PxGoodsModel>> findPxShopOnlineGoodsByCondition(String shopId, String goodsType, String goodsTitle) {

        StringBuffer subUrl = new StringBuffer();
        if (StringUtils.isNotBlank(shopId)) {
            subUrl.append("shopId=" + shopId);
        }
        if (StringUtils.isNotBlank(goodsType)) {
            subUrl.append("&goodsType=" + goodsType);
        }
        if (StringUtils.isNotBlank(goodsTitle)) {
            try {
                subUrl.append("&goodsTitle=" + URLEncoder.encode(goodsTitle, "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        String url = null;
        url = "http://localhost:40051/myteay/api/phoenix/admin/manage/goods/query/goods/condition/?" + subUrl.toString();

        String result = HttpClientUtil.insureResponsePost(url, null);
        MtOperateResult<List<PxGoodsModel>> obj = (MtOperateResult<List<PxGoodsModel>>) JSON.parseObject(result,
            new TypeReference<MtOperateResult<List<PxGoodsModel>>>() {
            });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsControllerIntg#manageGoodsStatus(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    @Override
    public MtOperateResult<PxGoodsModel> manageGoodsStatus(PxGoodsModel pxGoodsModel) {
        String url = "http://localhost:40051/myteay/api/phoenix/admin/manage/goods/status/";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsModel));
        MtOperateResult<PxGoodsModel> obj = (MtOperateResult<PxGoodsModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<PxGoodsModel>>() {
        });
        return obj;
    }

    /** 
     * @see com.myteay.phoenix.common.service.integration.PxGoodsControllerIntg#manageGoods(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    @Override
    public MtOperateResult<PxGoodsModel> manageGoods(PxGoodsModel pxGoodsModel) {
        String url = "http://localhost:40051/myteay/api/phoenix/admin/manage/goods/manage";
        String result = HttpClientUtil.insureResponsePost(url, JSON.toJSONString(pxGoodsModel));
        MtOperateResult<PxGoodsModel> obj = (MtOperateResult<PxGoodsModel>) JSON.parseObject(result, new TypeReference<MtOperateResult<PxGoodsModel>>() {
        });
        return obj;
    }

}
