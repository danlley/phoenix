/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 商品高级模型
 * 
 * @author min.weixm
 * @version $Id: PxGoodsAdvModel.java, v 0.1 Aug 4, 2018 7:09:37 PM min.weixm Exp $
 */
public class PxGoodsAdvModel implements Serializable {

    /** serialVersionUID */
    private static final long                serialVersionUID = 8404973323881894567L;

    /** 商品概要模型 */
    private PxGoodsModel                     pxGoodsModel;

    /** 套餐包模型列表 */
    private List<PxGoodsPackagesDetailModel> pxGoodsPackagesDetailModels;

    /** 商品详情图片模型 */
    private List<PxGoodsPackagesImageModel>  pxGoodsPackagesImageModels;

    /**
     * Getter method for property <tt>pxGoodsModel</tt>.
     * 
     * @return property value of pxGoodsModel
     */
    public PxGoodsModel getPxGoodsModel() {
        return pxGoodsModel;
    }

    /**
     * Setter method for property <tt>pxGoodsModel</tt>.
     * 
     * @param pxGoodsModel value to be assigned to property pxGoodsModel
     */
    public void setPxGoodsModel(PxGoodsModel pxGoodsModel) {
        this.pxGoodsModel = pxGoodsModel;
    }

    /**
     * Getter method for property <tt>pxGoodsPackagesDetailModels</tt>.
     * 
     * @return property value of pxGoodsPackagesDetailModels
     */
    public List<PxGoodsPackagesDetailModel> getPxGoodsPackagesDetailModels() {
        return pxGoodsPackagesDetailModels;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesDetailModels</tt>.
     * 
     * @param pxGoodsPackagesDetailModels value to be assigned to property pxGoodsPackagesDetailModels
     */
    public void setPxGoodsPackagesDetailModels(List<PxGoodsPackagesDetailModel> pxGoodsPackagesDetailModels) {
        this.pxGoodsPackagesDetailModels = pxGoodsPackagesDetailModels;
    }

    /**
     * Getter method for property <tt>pxGoodsPackagesImageModels</tt>.
     * 
     * @return property value of pxGoodsPackagesImageModels
     */
    public List<PxGoodsPackagesImageModel> getPxGoodsPackagesImageModels() {
        return pxGoodsPackagesImageModels;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesImageModels</tt>.
     * 
     * @param pxGoodsPackagesImageModels value to be assigned to property pxGoodsPackagesImageModels
     */
    public void setPxGoodsPackagesImageModels(List<PxGoodsPackagesImageModel> pxGoodsPackagesImageModels) {
        this.pxGoodsPackagesImageModels = pxGoodsPackagesImageModels;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
