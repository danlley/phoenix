/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile.convertor;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.mobile.dataobject.PxMobileGoodsDO;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsHuiyuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsOrderTypeEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsQuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTuanEnum;
import com.myteay.phoenix.common.util.manage.enums.PxShopStatusEnum;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsBaseModel;
import com.myteay.phoenix.core.model.mobile.PxMobileGoodsModel;
import com.myteay.phoenix.core.model.mobile.PxMobileShopModel;

/**
 * 手机端商品管理模型转换器
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsModelConvertor.java, v 0.1 Aug 16, 2018 11:30:56 PM min.weixm Exp $
 */
public class PxMobileGoodsModelConvertor {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxMobileGoodsModelConvertor.class);

    /**
     * 模型列表转换
     * 
     * @param pxMobileGoodsDOs
     * @return
     */
    public static List<PxMobileGoodsModel> convertPxMobileGoodsDOList(List<PxMobileGoodsDO> pxMobileGoodsDOs) {
        if (CollectionUtils.isEmpty(pxMobileGoodsDOs)) {
            logger.warn("未找到已发布为过期商品列表");
            return null;
        }

        List<PxMobileGoodsModel> list = new ArrayList<>();
        PxMobileGoodsModel pxMobileGoodsModel = null;
        for (PxMobileGoodsDO pxMobileGoodsDO : pxMobileGoodsDOs) {
            pxMobileGoodsModel = convertDO2Model(pxMobileGoodsDO);
            if (pxMobileGoodsModel != null) {
                list.add(pxMobileGoodsModel);
            }
        }

        return list;
    }

    /**
     * convert DO 2 model
     * 
     * @param pxMobileGoodsDO
     * @return
     */
    private static PxMobileGoodsModel convertDO2Model(PxMobileGoodsDO pxMobileGoodsDO) {

        if (pxMobileGoodsDO == null) {
            return null;
        }

        PxMobileGoodsBaseModel pxMobileGoodsBaseModel = convert2PxMobileGoodsBaseModel(pxMobileGoodsDO);
        if (pxMobileGoodsBaseModel == null) {
            return null;
        }

        PxMobileGoodsModel pxMobileGoodsModel = new PxMobileGoodsModel();
        pxMobileGoodsModel.setPxMobileGoodsBaseModel(pxMobileGoodsBaseModel);
        pxMobileGoodsModel.setGoodsId(pxMobileGoodsBaseModel.getGoodsId());
        pxMobileGoodsModel.setPxMobileShopModel(convert2PxMobileShopModel(pxMobileGoodsDO));

        return pxMobileGoodsModel;
    }

    /**
     * 生成商品基本信息模型
     * 
     * @param pxMobileGoodsDO
     * @return
     */
    private static PxMobileGoodsBaseModel convert2PxMobileGoodsBaseModel(PxMobileGoodsDO pxMobileGoodsDO) {
        if (pxMobileGoodsDO == null) {
            return null;
        }

        PxMobileGoodsBaseModel pxMobileGoodsBaseModel = new PxMobileGoodsBaseModel();

        pxMobileGoodsBaseModel.setGmtGoodsCreated(pxMobileGoodsDO.getGmtGoodsCreated());
        pxMobileGoodsBaseModel.setGmtGoodsExpired(pxMobileGoodsDO.getGmtGoodsExpired());
        pxMobileGoodsBaseModel.setGoodsCommPrice(pxMobileGoodsDO.getGoodsCommPrice());
        pxMobileGoodsBaseModel.setGoodsDesc(pxMobileGoodsDO.getGoodsDesc());
        pxMobileGoodsBaseModel.setGoodsId(pxMobileGoodsDO.getGoodsId());
        pxMobileGoodsBaseModel.setGoodsImage(pxMobileGoodsDO.getGoodsImage());
        pxMobileGoodsBaseModel.setGoodsOnlineTime(pxMobileGoodsDO.getGoodsOnlineTime());
        pxMobileGoodsBaseModel.setGoodsPrice(pxMobileGoodsDO.getGoodsPrice());
        pxMobileGoodsBaseModel.setGoodsSellAmount(pxMobileGoodsDO.getGoodsSellAmount());
        pxMobileGoodsBaseModel.setGoodsStatus(pxMobileGoodsDO.getGoodsStatus());
        pxMobileGoodsBaseModel.setGoodsTitle(pxMobileGoodsDO.getGoodsTitle());

        if (StringUtils.isNotBlank(pxMobileGoodsDO.getIsHuiyuan())) {
            pxMobileGoodsBaseModel.setIsHuiyuan(PxGoodsHuiyuanEnum.getByValue(pxMobileGoodsDO.getIsHuiyuan()));
        }

        if (StringUtils.isNotBlank(pxMobileGoodsDO.getIsQuan())) {
            pxMobileGoodsBaseModel.setIsQuan(PxGoodsQuanEnum.getByValue(pxMobileGoodsDO.getIsQuan()));
        }

        if (StringUtils.isNotBlank(pxMobileGoodsDO.getIsTuan())) {
            pxMobileGoodsBaseModel.setIsTuan(PxGoodsTuanEnum.getByValue(pxMobileGoodsDO.getIsTuan()));
        }

        if (StringUtils.isNotBlank(pxMobileGoodsDO.getOrderType())) {
            pxMobileGoodsBaseModel.setOrderType(PxGoodsOrderTypeEnum.getByValue(pxMobileGoodsDO.getOrderType()));
        }

        return pxMobileGoodsBaseModel;
    }

    /**
     * 生成店铺模型
     * 
     * @param pxMobileGoodsDO
     * @return
     */
    private static PxMobileShopModel convert2PxMobileShopModel(PxMobileGoodsDO pxMobileGoodsDO) {
        if (pxMobileGoodsDO == null) {
            return null;
        }

        PxMobileShopModel pxMobileShopModel = new PxMobileShopModel();

        pxMobileShopModel.setGmtShopCreated(pxMobileGoodsDO.getGmtShopCreated());
        pxMobileShopModel.setGmtShopExpired(pxMobileGoodsDO.getGmtShopExpired());
        pxMobileShopModel.setShopAddress(pxMobileGoodsDO.getShopAddress());
        pxMobileShopModel.setShopId(pxMobileGoodsDO.getShopId());
        pxMobileShopModel.setShopName(pxMobileGoodsDO.getShopName());

        if (StringUtils.isNotBlank(pxMobileGoodsDO.getGoodsStatus())) {
            pxMobileShopModel.setShopStatus(PxShopStatusEnum.getByValue(pxMobileGoodsDO.getGoodsStatus()));
        }
        pxMobileShopModel.setShopTel(pxMobileGoodsDO.getShopTel());
        pxMobileShopModel.setWaiterName(pxMobileGoodsDO.getWaiterName());

        return pxMobileShopModel;
    }
}
