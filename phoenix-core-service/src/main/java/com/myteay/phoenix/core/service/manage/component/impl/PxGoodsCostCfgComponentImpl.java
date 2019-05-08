/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.manage.component.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.myteay.common.async.event.EventPublishService;
import com.myteay.common.util.log.Logger;
import com.myteay.common.util.log.LoggerFactory;
import com.myteay.phoenix.common.logs.LoggerNames;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.enums.PxEventTopicEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;
import com.myteay.phoenix.core.model.MtOperateResult;
import com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel;
import com.myteay.phoenix.core.model.manage.repository.PxGoodsCostCfgRepository;
import com.myteay.phoenix.core.service.manage.component.PxGoodsCostCfgComponent;
import com.myteay.phoenix.core.service.tools.PxEventPublishTool;

/**
 * 商品成本管理组件
 * 
 * @author danlley
 * @version $Id: PxGoodsCostCfgComponentImpl.java, v 0.1 May 7, 2019 1:26:24 PM danlley Exp $
 */
public class PxGoodsCostCfgComponentImpl implements PxGoodsCostCfgComponent {

    /** 日志 */
    private static final Logger         logger = LoggerFactory.getLogger(LoggerNames.PX_CASHIER_DEFAULT);

    /** 商品成本管理仓储 */
    private PxGoodsCostCfgRepository    pxGoodsCostCfgRepository;

    /** 事件发送组件 */
    private EventPublishService<String> eventPublishService;

    /** 事件发送组件 */
    @Autowired
    private PxEventPublishTool          pxEventPublishTool;

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsCostCfgComponent#manageGoodsCostCfgInfo(com.myteay.phoenix.core.model.manage.PxGoodsCostCfgAdvModel)
     */
    @Override
    public MtOperateResult<PxGoodsCostCfgAdvModel> manageGoodsCostCfgInfo(PxGoodsCostCfgAdvModel pxGoodsCostCfgAdvModel) {

        if (pxGoodsCostCfgAdvModel == null || StringUtils.isBlank(pxGoodsCostCfgAdvModel.getGoodsId())) {
            logger.warn("当前模型关键信息不可用，无法对当前商品进行成本管理 pxGoodsCostCfgAdvModel=" + pxGoodsCostCfgAdvModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.COST_MODEL_ERR);
        }

        PxGoodsCostCfgAdvModel originalModel = null;
        try {
            originalModel = pxGoodsCostCfgRepository.findSingleGoodsCostCfg(pxGoodsCostCfgAdvModel.getGoodsId());
        } catch (PxManageException e) {
            logger.warn("manageGoodsCostCfgInfo --> findSingleGoodsCostCfg method error: pxGoodsCostCfgAdvModel=" + pxGoodsCostCfgAdvModel, e);
        }

        //数据库中没有成本记录，需要重新写入
        if (originalModel == null) {
            try {
                originalModel = pxGoodsCostCfgRepository.saveGoodsCostCfgInfo(pxGoodsCostCfgAdvModel);
            } catch (PxManageException e) {
                logger.warn("manageGoodsCostCfgInfo --> saveGoodsCostCfgInfo error:  pxGoodsCostCfgAdvModel=" + pxGoodsCostCfgAdvModel);
                return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.COST_MODEL_OP_MNG_FAILD);
            }
            //异步更新缓存信息
            pxEventPublishTool.publishEventWithObject(PxEventTopicEnum.PX_GOODS_COST_CFG_CHANGED, pxGoodsCostCfgAdvModel);
            return new MtOperateResult<>(originalModel);
        }

        //开始执行成本信息更新动作
        try {
            originalModel.setGoodsCost(pxGoodsCostCfgAdvModel.getGoodsCost());
            pxGoodsCostCfgRepository.modifyGoodsCostCfgInfo(originalModel);
        } catch (PxManageException e) {
            logger.warn("manageGoodsCostCfgInfo --> modifyGoodsCostCfgInfo error:  pxGoodsCostCfgAdvModel=" + pxGoodsCostCfgAdvModel);
            return new MtOperateResult<>(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.COST_MODEL_OP_MNG_FAILD);
        }

        //异步更新缓存信息
        pxEventPublishTool.publishEventWithObject(PxEventTopicEnum.PX_GOODS_COST_CFG_CHANGED, pxGoodsCostCfgAdvModel);

        return new MtOperateResult<PxGoodsCostCfgAdvModel>(originalModel);
    }

    /** 
     * @see com.myteay.phoenix.core.service.manage.component.PxGoodsCostCfgComponent#queryAllGoodsCostCfg()
     */
    @Override
    public MtOperateResult<List<PxGoodsCostCfgAdvModel>> queryAllGoodsCostCfg(String shopId) throws PxManageException {

        List<PxGoodsCostCfgAdvModel> pxGoodsCostCfgAdvModels = pxGoodsCostCfgRepository.findAllGoodsCostCfg(shopId);

        return new MtOperateResult<List<PxGoodsCostCfgAdvModel>>(pxGoodsCostCfgAdvModels);
    }

    /**
     * Setter method for property <tt>pxGoodsCostCfgRepository</tt>.
     * 
     * @param pxGoodsCostCfgRepository value to be assigned to property pxGoodsCostCfgRepository
     */
    public void setPxGoodsCostCfgRepository(PxGoodsCostCfgRepository pxGoodsCostCfgRepository) {
        this.pxGoodsCostCfgRepository = pxGoodsCostCfgRepository;
    }

    /**
     * Setter method for property <tt>eventPublishService</tt>.
     * 
     * @param eventPublishService value to be assigned to property eventPublishService
     */
    public void setEventPublishService(EventPublishService<String> eventPublishService) {
        this.eventPublishService = eventPublishService;
    }

    /**
     * Setter method for property <tt>pxEventPublishTool</tt>.
     * 
     * @param pxEventPublishTool value to be assigned to property pxEventPublishTool
     */
    public void setPxEventPublishTool(PxEventPublishTool pxEventPublishTool) {
        this.pxEventPublishTool = pxEventPublishTool;
    }

}
