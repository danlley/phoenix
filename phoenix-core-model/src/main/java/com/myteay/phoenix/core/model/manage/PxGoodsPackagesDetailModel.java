/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.manage;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.common.util.enums.PxOperationTypeEnum;

/**
 * 套餐包模型
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesDetailModel.java, v 0.1 Jul 27, 2018 5:06:34 PM min.weixm Exp $
 */
public class PxGoodsPackagesDetailModel implements Serializable {

    /** serialVersionUID */
    private static final long        serialVersionUID = -9180667135808287150L;

    /** 套餐包ID */
    private String                   packagesDetailId;

    /** 商品概要ID */
    private String                   goodsId;

    /** 套餐包名称 */
    private String                   packageDetailName;

    /** 创建时间 */
    private Date                     gmtCreated;

    /** 最后修改时间 */
    private Date                     gmtModified;

    /** 操作类型 */
    private PxOperationTypeEnum      operationType;

    /** 子套餐模型 */
    private List<PxSubPackagesModel> pxSubPackagesModels;

    /**
     * Getter method for property <tt>pxSubPackagesModels</tt>.
     * 
     * @return property value of pxSubPackagesModels
     */
    public List<PxSubPackagesModel> getPxSubPackagesModels() {
        return pxSubPackagesModels;
    }

    /**
     * Setter method for property <tt>pxSubPackagesModels</tt>.
     * 
     * @param pxSubPackagesModels value to be assigned to property pxSubPackagesModels
     */
    public void setPxSubPackagesModels(List<PxSubPackagesModel> pxSubPackagesModels) {
        this.pxSubPackagesModels = pxSubPackagesModels;
    }

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
     * Getter method for property <tt>packagesDetailId</tt>.
     * 
     * @return property value of packagesDetailId
     */
    public String getPackagesDetailId() {
        return packagesDetailId;
    }

    /**
     * Setter method for property <tt>packagesDetailId</tt>.
     * 
     * @param packagesDetailId value to be assigned to property packagesDetailId
     */
    public void setPackagesDetailId(String packagesDetailId) {
        this.packagesDetailId = packagesDetailId;
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
     * Getter method for property <tt>packageDetailName</tt>.
     * 
     * @return property value of packageDetailName
     */
    public String getPackageDetailName() {
        return packageDetailName;
    }

    /**
     * Setter method for property <tt>packageDetailName</tt>.
     * 
     * @param packageDetailName value to be assigned to property packageDetailName
     */
    public void setPackageDetailName(String packageDetailName) {
        this.packageDetailName = packageDetailName;
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
