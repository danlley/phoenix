/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl.admin.message;

import java.io.File;
import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;
import com.myteay.phoenix.core.model.manage.PxGoodsPackagesImageModel;

/**
 * 商品详情交互单据
 * 
 * @author min.weixm
 * @version $Id: PxGoodsPackagesImageMessage.java, v 0.1 Aug 1, 2018 2:29:47 PM min.weixm Exp $
 */
public class PxGoodsPackagesImageMessage implements Serializable {

    /** serialVersionUID */
    private static final long         serialVersionUID = -7766117552901211964L;

    /** 商品详情模型 */
    private PxGoodsPackagesImageModel pxGoodsPackagesImageModel;

    /** 图片文件 */
    private File                      file;

    /**
     * Getter method for property <tt>pxGoodsPackagesImageModel</tt>.
     * 
     * @return property value of pxGoodsPackagesImageModel
     */
    public PxGoodsPackagesImageModel getPxGoodsPackagesImageModel() {
        return pxGoodsPackagesImageModel;
    }

    /**
     * Setter method for property <tt>pxGoodsPackagesImageModel</tt>.
     * 
     * @param pxGoodsPackagesImageModel value to be assigned to property pxGoodsPackagesImageModel
     */
    public void setPxGoodsPackagesImageModel(PxGoodsPackagesImageModel pxGoodsPackagesImageModel) {
        this.pxGoodsPackagesImageModel = pxGoodsPackagesImageModel;
    }

    /**
     * Getter method for property <tt>file</tt>.
     * 
     * @return property value of file
     */
    public File getFile() {
        return file;
    }

    /**
     * Setter method for property <tt>file</tt>.
     * 
     * @param file value to be assigned to property file
     */
    public void setFile(File file) {
        this.file = file;
    }

    /** 
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
