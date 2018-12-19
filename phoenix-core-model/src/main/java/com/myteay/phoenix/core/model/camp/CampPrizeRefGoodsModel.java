/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.camp;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.core.model.manage.PxGoodsModel;

/**
 * 奖品关联商品模型
 * 
 * @author danlley
 * @version $Id: CampPrizeRefGoodsModel.java, v 0.1 Dec 19, 2018 10:59:43 PM danlley Exp $
 */
public class CampPrizeRefGoodsModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -9164177970508066778L;

    /** 奖品ID */
    private String            prizeId;

    /** 商品ID */
    private String            goodsId;

    /** 创建时间 */
    private Date              gmtCreated;

    /** 最后修改时间 */
    private Date              gmtModified;

    /** 商品概要信息 */
    private PxGoodsModel      pxGoodsModel;

    /**
     * Getter method for property <tt>prizeId</tt>.
     * 
     * @return property value of prizeId
     */
    public String getPrizeId() {
        return prizeId;
    }

    /**
     * Setter method for property <tt>prizeId</tt>.
     * 
     * @param prizeId value to be assigned to property prizeId
     */
    public void setPrizeId(String prizeId) {
        this.prizeId = prizeId;
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
     * Getter method for property <tt>gmtCreated</tt>.
     * 
     * @return property value of gmtCreated
     */
    public Date getGmtCreated() {
        return gmtCreated;
    }

    /**
     * Setter method for property <tt>gmtCreated</tt>.
     * 
     * @param gmtCreated value to be assigned to property gmtCreated
     */
    public void setGmtCreated(Date gmtCreated) {
        this.gmtCreated = gmtCreated;
    }

    /**
     * Getter method for property <tt>gmtModified</tt>.
     * 
     * @return property value of gmtModified
     */
    public Date getGmtModified() {
        return gmtModified;
    }

    /**
     * Setter method for property <tt>gmtModified</tt>.
     * 
     * @param gmtModified value to be assigned to property gmtModified
     */
    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
