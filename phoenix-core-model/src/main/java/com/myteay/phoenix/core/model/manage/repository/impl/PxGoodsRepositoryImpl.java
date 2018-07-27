/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage.repository.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.util.CollectionUtils;

import com.myteay.phoenix.common.dal.daointerface.PxGoodsDAO;
import com.myteay.phoenix.common.dal.dataobject.PxGoodsDO;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;
import com.myteay.phoenix.core.model.manage.convertor.PxGoodsConvertor;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository;
import com.myteay.phoenix.core.model.manage.tools.PxManageValidateTool;

/**
 * 商品概要管理仓储
 * 
 * @author min.weixm
 * @version $Id: PxGoodsRepositoryImpl.java, v 0.1 Jul 26, 2018 11:52:05 AM min.weixm Exp $
 */
public class PxGoodsRepositoryImpl implements PxGoodsRepository {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(PxGoodsRepositoryImpl.class);

    /** 商品概要操作DAO */
    private PxGoodsDAO         pxGoodsDAO;

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository#removeGoodsInfo(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    @Override
    public void removeGoodsInfo(PxGoodsModel pxGoodsModel) throws PxManageException {
        if (pxGoodsModel == null) {
            logger.warn("当前店铺模型不可用，无法删除店铺信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        // TODO 删除前，需要检查是否存在商品关联，如果存在，则不允许进行删除
        pxGoodsDAO.deleteById(pxGoodsModel.getGoodsId());
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository#modifyGoodsInfo(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    @Override
    public PxGoodsModel modifyGoodsInfo(PxGoodsModel pxGoodsModel) throws PxManageException {
        PxGoodsDO pxGoodsDO = PxGoodsConvertor.convertModel2DO(pxGoodsModel);

        pxGoodsDAO.updatePxGoods(pxGoodsDO);

        PxGoodsDO freshPxGoodsDO = pxGoodsDAO.findPxGoodsById(pxGoodsDO.getShopId());
        return PxGoodsConvertor.convertDO2Model(freshPxGoodsDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository#saveGoodsInfo(com.myteay.phoenix.core.model.manage.PxGoodsModel)
     */
    @Override
    public PxGoodsModel saveGoodsInfo(PxGoodsModel pxGoodsModel) throws PxManageException {
        if (pxGoodsModel == null) {
            logger.warn("当前店铺模型不可用，无法保存店铺信息");
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.PX_GOODS_MODEL_INVALID);
        }

        PxManageValidateTool.validatePxGoodsModel(pxGoodsModel);

        PxGoodsDO pxGoodsDO = PxGoodsConvertor.convertModel2DO(pxGoodsModel);
        String goodsId = pxGoodsDAO.insert(pxGoodsDO);

        PxGoodsDO freshPxGoodsDO = pxGoodsDAO.findPxGoodsById(goodsId);

        return PxGoodsConvertor.convertDO2Model(freshPxGoodsDO);
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository#findAll()
     */
    @Override
    public List<PxGoodsModel> findAll() throws PxManageException {
        List<PxGoodsDO> list = pxGoodsDAO.findPxGoodsAll();

        List<PxGoodsModel> modelList = new ArrayList<>();
        PxGoodsModel pxGoodsModel = null;
        for (PxGoodsDO pxGoodsDO : list) {
            pxGoodsModel = PxGoodsConvertor.convertDO2Model(pxGoodsDO);
            if (pxGoodsModel != null) {
                modelList.add(pxGoodsModel);
            }
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository#findGoodsByShopId(java.lang.String)
     */
    @Override
    public List<PxGoodsModel> findGoodsByShopId(String shopId) throws PxManageException {

        List<PxGoodsDO> pxGoodsDOs = pxGoodsDAO.findPxGoodsByShopId(shopId);
        if (CollectionUtils.isEmpty(pxGoodsDOs)) {
            return null;
        }

        List<PxGoodsModel> modelList = new ArrayList<>();
        for (PxGoodsDO pxGoodsDO : pxGoodsDOs) {
            modelList.add(PxGoodsConvertor.convertDO2Model(pxGoodsDO));
        }

        return modelList;
    }

    /** 
     * @see com.myteay.phoenix.core.model.manage.repository.PxGoodsRepository#findSingleGoods(java.lang.String)
     */
    @Override
    public PxGoodsModel findSingleGoods(String goodsId) throws PxManageException {
        PxGoodsDO freshPxGoodsDO = pxGoodsDAO.findPxGoodsById(goodsId);
        return PxGoodsConvertor.convertDO2Model(freshPxGoodsDO);
    }

    /**
     * Setter method for property <tt>pxGoodsDAO</tt>.
     * 
     * @param pxGoodsDAO value to be assigned to property pxGoodsDAO
     */
    public void setPxGoodsDAO(PxGoodsDAO pxGoodsDAO) {
        this.pxGoodsDAO = pxGoodsDAO;
    }

}
