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

import com.myteay.common.util.exception.MtException;
import com.myteay.phoenix.common.dal.daointerface.PxGoodsOrderOutDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsOrderOutDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxOrderStatusEnum;
import com.myteay.phoenix.common.util.enums.PxPayTypeEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.common.util.manage.enums.PxGoodsTypeEnum;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;
import com.myteay.phoenix.core.model.PxGoodsOrderOutModel;
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
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository#deleteExpiredOrder(com.myteay.phoenix.core.model.PxGoodsOrderOutModel)
     */
    @Override
    public String deleteExpiredOrder(PxGoodsOrderOutModel pxGoodsOrderOutModel) throws MtException {
        if (pxGoodsOrderOutModel == null || StringUtils.isBlank(pxGoodsOrderOutModel.getId())) {
            logger.warn("当前订单模型不合法，无法完成废单清理工作 pxGoodsOrderOutModel=" + pxGoodsOrderOutModel);
            throw new MtException("当前订单模型不合法，无法完成废单清理工作");
        }

        pxGoodsOrderOutDAO.deleteByIdWithStatus(pxGoodsOrderOutModel.getId());

        return pxGoodsOrderOutModel.getId();
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository#findAllShopExpiredOrder()
     */
    @Override
    public PxGoodsOrderModel findAllShopExpiredOrder() {
        List<PxGoodsOrderOutDO> expiredOrderList = pxGoodsOrderOutDAO.selectExpiredGoodsOrderOutDOs();

        if (CollectionUtils.isEmpty(expiredOrderList)) {
            return null;
        }

        PxGoodsOrderModel pxGoodsOrderModel = new PxGoodsOrderModel();
        List<PxGoodsOrderOutModel> pxGoodsOrderOutModels = pxGoodsOrderModel.getPxGoodsOrderOutModelList();
        for (PxGoodsOrderOutDO pxGoodsOrderOutDO : expiredOrderList) {
            fillOrderOutModel(pxGoodsOrderOutModels, pxGoodsOrderOutDO);
        }

        return pxGoodsOrderModel;
    }

    /**
     * 填充订单流水模型
     * 
     * @param pxGoodsOrderOutDO
     * @return
     */
    private void fillOrderOutModel(List<PxGoodsOrderOutModel> pxGoodsOrderOutModels, PxGoodsOrderOutDO pxGoodsOrderOutDO) {

        PxGoodsOrderOutModel pxGoodsOrderOutModel = constructOrderOutModel(pxGoodsOrderOutDO);

        if (pxGoodsOrderOutModel != null) {
            pxGoodsOrderOutModels.add(pxGoodsOrderOutModel);
        }
    }

    /**
     * 构建订单流水模型
     * 
     * @param pxGoodsOrderOutDO
     * @return
     */
    private PxGoodsOrderOutModel constructOrderOutModel(PxGoodsOrderOutDO pxGoodsOrderOutDO) {
        if (pxGoodsOrderOutDO == null) {
            return null;
        }

        PxGoodsOrderOutModel pxGoodsOrderOutModel = new PxGoodsOrderOutModel();
        pxGoodsOrderOutModel.setGmtCreated(pxGoodsOrderOutDO.getGmtCreated());
        pxGoodsOrderOutModel.setGmtModified(pxGoodsOrderOutDO.getGmtModified());
        pxGoodsOrderOutModel.setGoodsCommPrice(pxGoodsOrderOutDO.getGoodsCommPrice());
        pxGoodsOrderOutModel.setGoodsId(pxGoodsOrderOutDO.getGoodsId());
        pxGoodsOrderOutModel.setGoodsPrice(pxGoodsOrderOutDO.getGoodsPrice());
        pxGoodsOrderOutModel.setGoodsTitle(pxGoodsOrderOutDO.getGoodsTitle());

        if (StringUtils.isNotBlank(pxGoodsOrderOutDO.getGoodsType())) {
            pxGoodsOrderOutModel.setGoodsType(PxGoodsTypeEnum.getByCode(pxGoodsOrderOutDO.getGoodsType()));
        }
        pxGoodsOrderOutModel.setId(pxGoodsOrderOutDO.getId());
        pxGoodsOrderOutModel.setOrderNo(pxGoodsOrderOutDO.getOrderNo());

        if (StringUtils.isNotBlank(pxGoodsOrderOutDO.getOrderStatus())) {
            pxGoodsOrderOutModel.setOrderStatus(PxOrderStatusEnum.getByCode(pxGoodsOrderOutDO.getOrderStatus()));
        }

        if (StringUtils.isNotBlank(pxGoodsOrderOutDO.getPayType())) {
            pxGoodsOrderOutModel.setPayType(PxPayTypeEnum.getByCode(pxGoodsOrderOutDO.getPayType()));
        }
        pxGoodsOrderOutModel.setSellerAmount(pxGoodsOrderOutDO.getSellerAmount());
        pxGoodsOrderOutModel.setShopId(pxGoodsOrderOutDO.getShopId());
        pxGoodsOrderOutModel.setShopName(pxGoodsOrderOutDO.getShopName());
        pxGoodsOrderOutModel.setUserId(pxGoodsOrderOutDO.getUserId());

        return pxGoodsOrderOutModel;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository#modifyGoodsOrderOut(java.lang.String, com.myteay.phoenix.common.util.enums.PxPayTypeEnum, com.myteay.phoenix.common.util.enums.PxOrderStatusEnum)
     */
    @Override
    public String modifyGoodsOrderOut(String orderNo, PxPayTypeEnum pxPayTypeEnum, PxOrderStatusEnum pxOrderStatusEnum) throws PxManageException {

        if (StringUtils.isBlank(orderNo)) {
            logger.warn("订单编号不可用，无法更新指定订单记录的状态 orderNo is null pxPayTypeEnum=" + pxPayTypeEnum + " pxOrderStatusEnum=" + pxOrderStatusEnum);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_UP_ERR);
        }

        PxGoodsOrderOutDO pxGoodsOrderOutDO = new PxGoodsOrderOutDO();
        pxGoodsOrderOutDO.setOrderNo(orderNo);

        if (pxPayTypeEnum != null) {
            pxGoodsOrderOutDO.setPayType(pxPayTypeEnum.getValue());
        }

        if (pxOrderStatusEnum != null) {
            pxGoodsOrderOutDO.setOrderStatus(pxOrderStatusEnum.getValue());
        }

        pxGoodsOrderOutDAO.updatePxGoods(pxGoodsOrderOutDO);

        return orderNo;
    }

    /** 
     * @throws PxManageException 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsOrderOutRepository#saveGoodsOrderOut(com.myteay.phoenix.core.model.PxGoodsOrderModel)
     */
    @Override
    public String saveGoodsOrderOut(PxGoodsOrderModel pxGoodsOrderModel) throws PxManageException {

        if (pxGoodsOrderModel == null || pxGoodsOrderModel.getPayType() == null || StringUtils.isBlank(pxGoodsOrderModel.getUserId())
            || StringUtils.isBlank(pxGoodsOrderModel.getShopName()) || StringUtils.isBlank(pxGoodsOrderModel.getOrderNo())
            || CollectionUtils.isEmpty(pxGoodsOrderModel.getPxGoodsModelList())) {
            logger.warn("模型不合法 pxGoodsOrderModel=" + pxGoodsOrderModel);
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_ORDER_OUT_OP_ERR);
        }

        String userId = pxGoodsOrderModel.getUserId();
        String orderNo = pxGoodsOrderModel.getOrderNo();
        String shopName = pxGoodsOrderModel.getShopName();
        List<PxGoodsModel> pxGoodsModels = pxGoodsOrderModel.getPxGoodsModelList();

        PxGoodsOrderOutDO pxGoodsOrderOutDO = null;
        List<PxGoodsOrderOutDO> list = new ArrayList<PxGoodsOrderOutDO>();
        for (PxGoodsModel pxGoodsModel : pxGoodsModels) {
            pxGoodsOrderOutDO = constructOrderOutDO(PxPayTypeEnum.PX_UN_PAY, userId, orderNo, shopName, pxGoodsModel);
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
            saveSingleGoodsOrderOut(orderDO);
            result = orderDO.getOrderNo();
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
    private PxGoodsOrderOutDO constructOrderOutDO(PxPayTypeEnum payType, String userId, String orderNo, String shopName, PxGoodsModel pxGoodsModel) {
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

        if (payType != null) {
            pxGoodsOrderOutDO.setPayType(payType.getValue());
        }

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
