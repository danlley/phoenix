/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;

/**
 * 商品详情图片模型
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageModel.java, v 0.1 Aug 1, 2018 12:18:42 PM min.weixm Exp $
 */
public class PxGoodsPackagesImageModel implements Serializable {

    /** serialVersionUID */
    private static final long   serialVersionUID = 6824055732309080137L;

    /** 图片ID */
    private String              imageId;

    /** 商品ID */
    private String              goodsId;

    /** 图片名称 */
    private String              image;

    /** 创建时间 */
    private Date                gmtCreated;

    /** 修改时间 */
    private Date                gmtModified;

    /** 操作类型 */
    private PxOperationTypeEnum operationType;

    /**
     * Getter method for property <tt>operationType</tt>.
     * 
     * @return property value of operationType
     */
    public PxOperationTypeEnum getOperationType() {
        return operationType;
    }

    /**
     * Setter method for property <tt>operationType</tt>.
     * 
     * @param operationType value to be assigned to property operationType
     */
    public void setOperationType(PxOperationTypeEnum operationType) {
        this.operationType = operationType;
    }

    /**
     * Getter method for property <tt>imageId</tt>.
     * 
     * @return property value of imageId
     */
    public String getImageId() {
        return imageId;
    }

    /**
     * Setter method for property <tt>imageId</tt>.
     * 
     * @param imageId value to be assigned to property imageId
     */
    public void setImageId(String imageId) {
        this.imageId = imageId;
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
     * Getter method for property <tt>image</tt>.
     * 
     * @return property value of image
     */
    public String getImage() {
        return image;
    }

    /**
     * Setter method for property <tt>image</tt>.
     * 
     * @param image value to be assigned to property image
     */
    public void setImage(String image) {
        this.image = image;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
