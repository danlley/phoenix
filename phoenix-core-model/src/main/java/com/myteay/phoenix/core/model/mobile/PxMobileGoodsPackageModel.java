/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;
import java.util.List;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 商品套餐包模型
 * 
 * @author danlley
 * @version $Id: PxMobileGoodsPackageModel.java, v 0.1 Aug 19, 2018 8:04:10 PM danlley Exp $
 */
public class PxMobileGoodsPackageModel implements Serializable {

    /** serialVersionUID */
    private static final long                  serialVersionUID = -9017752727270857356L;

    /** 套餐包ID */
    private String                             packagesDetailId;

    /** 套餐包名称 */
    private String                             packageDetailName;

    /** 子套餐模型列表 */
    private List<PxMobileGoodsSubPackageModel> subPackages;

    /**
     * Getter method for property <tt>subPackages</tt>.
     * 
     * @return property value of subPackages
     */
    public List<PxMobileGoodsSubPackageModel> getSubPackages() {
        return subPackages;
    }

    /**
     * Setter method for property <tt>subPackages</tt>.
     * 
     * @param subPackages value to be assigned to property subPackages
     */
    public void setSubPackages(List<PxMobileGoodsSubPackageModel> subPackages) {
        this.subPackages = subPackages;
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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }

}
