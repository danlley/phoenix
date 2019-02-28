/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository;

/**
 * 订单流水操作仓储
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderOutRepositoryImpl.java, v 0.1 Feb 27, 2019 11:25:27 AM danlley Exp $
 */
public class PxGoodsOrderOutRepositoryImpl implements PxGoodsOrderOutRepository {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxGoodsOrderOutRepositoryImpl.class);

    /** 订单流水操作DAO */
    private PxGoodsOrderOutDAO pxGoodsOrderOutDAO;

    /** 
     * @throws PxManageException 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository#saveGoodsOrderOut(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public String saveGoodsOrderOut(PxGoodsOrderModel pxGoodsOrderModel) throws PxManageException {

        if (pxGoodsOrderModel == null || StringUtils.isBlank(pxGoodsOrderModel.getUserId()) || StringUtils.isBlank(pxGoodsOrderModel.getShopName())
            || StringUtils.isBlank(pxGoodsOrderModel.getOrderNo()) || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsModelList())) {
        }

        String userId = pxGoodsOrderModel.getUserId();
        String orderNo = pxGoodsOrderModel.getOrderNo();
        String shopName = pxGoodsOrderModel.getShopName();
        List<PxGoodsModel> pxGoodsModels = pxGoodsOrderModel.getPxGoodsModelList();

        PxGoodsOrderOutDO pxGoodsOrderOutDO = null;
        List<PxGoodsOrderOutDO> list = new ArrayList<PxGoodsOrderOutDO>();
        for (PxGoodsModel pxGoodsModel : pxGoodsModels) {
            pxGoodsOrderOutDO = constructOrderOutDO(userId, orderNo, shopName, pxGoodsModel);
            if (pxGoodsOrderOutDO == null) {
                throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_MODEL_ERR);
            }
            list.add(pxGoodsOrderOutDO);
        }

        if (CollectionUtils.isEmpty(list)) {
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_MODEL_ERR);
        }

        //TODO 放入事务中处理
        String result = null;
        for (PxGoodsOrderOutDO orderDO : list) {
            result = saveSingleGoodsOrderOut(orderDO);
        }

        return result;
    }

    /**
     * 构建订单流水数据模型
     * 
     * @param userId
     * @param orderNo
     * @param pxGoodsModel
     * @return
     */
    private PxGoodsOrderOutDO constructOrderOutDO(String userId, String orderNo, String shopName, PxGoodsModel pxGoodsModel) {
        if (StringUtils.isBlank(userId) || StringUtils.isBlank(shopName) || StringUtils.isBlank(orderNo) || pxGoodsModel == null) {
            return null;
        }

        PxGoodsOrderOutDO pxGoodsOrderOutDO = new PxGoodsOrderOutDO();

        pxGoodsOrderOutDO.setGoodsCommPrice(pxGoodsModel.getGoodsCommPrice());
        pxGoodsOrderOutDO.setGoodsId(pxGoodsModel.getGoodsId());
        pxGoodsOrderOutDO.setGoodsPrice(pxGoodsModel.getGoodsPrice());
        pxGoodsOrderOutDO.setGoodsTitle(pxGoodsModel.getGoodsTitle());

        if (pxGoodsModel.getGoodsType() != null) {
            pxGoodsOrderOutDO.setGoodsType(pxGoodsModel.getGoodsType().getValue());
        }

        pxGoodsOrderOutDO.setOrderNo(orderNo);
        pxGoodsOrderOutDO.setOrderStatus(PxOrderStatusEnum.PX_ORDER_INIT.getValue());
        pxGoodsOrderOutDO.setShopId(pxGoodsModel.getShopId());
        pxGoodsOrderOutDO.setShopName(shopName);
        pxGoodsOrderOutDO.setUserId(userId);
        pxGoodsOrderOutDO.setSellerAmount(pxGoodsModel.getGoodsSellAmount());

        return pxGoodsOrderOutDO;
    }

    /**
     * 保存订单流水
     * 
     * @param pxGoodsOrderOutDO
     * @return
     */
    private String saveSingleGoodsOrderOut(PxGoodsOrderOutDO pxGoodsOrderOutDO) {
        return pxGoodsOrderOutDAO.insert(pxGoodsOrderOutDO);
    }

    /**
     * Setter method for property <tt>pxGoodsOrderOutDAO</tt>.
     * 
     * @param pxGoodsOrderOutDAO value to be assigned to property pxGoodsOrderOutDAO
     */
    public void setPxGoodsOrderOutDAO(PxGoodsOrderOutDAO pxGoodsOrderOutDAO) {
        this.pxGoodsOrderOutDAO = pxGoodsOrderOutDAO;
    }

}
