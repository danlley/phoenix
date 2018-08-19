/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 用户手机端展示用的商品模型
 * 
 * @author min.weixm
 * @version $Id: PxMobileGoodsModel.java, v 0.1 Aug 16, 2018 7:46:24 PM min.weixm Exp $
 */
public class PxMobileGoodsModel implements Serializable {

    /** serialVersionUID */
    private static final long               serialVersionUID = -7840835410752681039L;

    /** 商品ID */
    private String                          goodsId;

    /** 商品基础模型 */
    private PxMobileGoodsBaseModel          pxMobileGoodsBaseModel;

    /** 店铺手机端模型 */
    private PxMobileShopModel               pxMobileShopModel;

    /** 手机端商品详情图片模型列表 */
    private List<PxMobileGoodsImagesModel>  pxMobileGoodsImagesModels;

    /** 温馨提醒模型列表 */
    private List<PxMobileGoodsNoticeModel>       pxMobileGoodsNotices;

    /** 套餐包模型列表 */
    private List<PxMobileGoodsPackageModel> pxMobileGoodsPackageModels;

    /**
     * Getter method for property <tt>pxMobileGoodsPackageModels</tt>.
     * 
     * @return property value of pxMobileGoodsPackageModels
     */
    public List<PxMobileGoodsPackageModel> getPxMobileGoodsPackageModels() {
        return pxMobileGoodsPackageModels;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsPackageModels</tt>.
     * 
     * @param pxMobileGoodsPackageModels value to be assigned to property pxMobileGoodsPackageModels
     */
    public void setPxMobileGoodsPackageModels(List<PxMobileGoodsPackageModel> pxMobileGoodsPackageModels) {
        this.pxMobileGoodsPackageModels = pxMobileGoodsPackageModels;
    }

    /**
     * Getter method for property <tt>pxMobileGoodsNotices</tt>.
     * 
     * @return property value of pxMobileGoodsNotices
     */
    public List<PxMobileGoodsNoticeModel> getPxMobileGoodsNotices() {
        return pxMobileGoodsNotices;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsNotices</tt>.
     * 
     * @param pxMobileGoodsNotices value to be assigned to property pxMobileGoodsNotices
     */
    public void setPxMobileGoodsNotices(List<PxMobileGoodsNoticeModel> pxMobileGoodsNotices) {
        this.pxMobileGoodsNotices = pxMobileGoodsNotices;
    }

    /**
     * Getter method for property <tt>pxMobileGoodsImagesModels</tt>.
     * 
     * @return property value of pxMobileGoodsImagesModels
     */
    public List<PxMobileGoodsImagesModel> getPxMobileGoodsImagesModels() {
        return pxMobileGoodsImagesModels;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsImagesModels</tt>.
     * 
     * @param pxMobileGoodsImagesModels value to be assigned to property pxMobileGoodsImagesModels
     */
    public void setPxMobileGoodsImagesModels(List<PxMobileGoodsImagesModel> pxMobileGoodsImagesModels) {
        this.pxMobileGoodsImagesModels = pxMobileGoodsImagesModels;
    }

    /**
     * Getter method for property <tt>goodsId</tt>.
     * 
     * @return property value of goodsId
     */
    public String getGoodsId() {
        return goodsId;
    }

    /**
     * Setter method for property <tt>goodsId</tt>.
     * 
     * @param goodsId value to be assigned to property goodsId
     */
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * Getter method for property <tt>pxMobileGoodsBaseModel</tt>.
     * 
     * @return property value of pxMobileGoodsBaseModel
     */
    public PxMobileGoodsBaseModel getPxMobileGoodsBaseModel() {
        return pxMobileGoodsBaseModel;
    }

    /**
     * Setter method for property <tt>pxMobileGoodsBaseModel</tt>.
     * 
     * @param pxMobileGoodsBaseModel value to be assigned to property pxMobileGoodsBaseModel
     */
    public void setPxMobileGoodsBaseModel(PxMobileGoodsBaseModel pxMobileGoodsBaseModel) {
        this.pxMobileGoodsBaseModel = pxMobileGoodsBaseModel;
    }

    /**
     * Getter method for property <tt>pxMobileShopModel</tt>.
     * 
     * @return property value of pxMobileShopModel
     */
    public PxMobileShopModel getPxMobileShopModel() {
        return pxMobileShopModel;
    }

    /**
     * Setter method for property <tt>pxMobileShopModel</tt>.
     * 
     * @param pxMobileShopModel value to be assigned to property pxMobileShopModel
     */
    public void setPxMobileShopModel(PxMobileShopModel pxMobileShopModel) {
        this.pxMobileShopModel = pxMobileShopModel;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
