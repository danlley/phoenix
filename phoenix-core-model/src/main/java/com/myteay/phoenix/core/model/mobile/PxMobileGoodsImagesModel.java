/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.core.model.mobile;

import java.io.Serializable;

import com.myteay.common.util.tools.ToStringUtil;

/**
 * 手机端商品图片模型
 * 
 * @author danlley
 * @version $Id: PxMobileGoodsImagesModel.java, v 0.1 Aug 19, 2018 7:15:56 PM danlley Exp $
 */
public class PxMobileGoodsImagesModel implements Serializable {

    /** serialVersionUID */
    private static final long serialVersionUID = -2313290357714402255L;

    /** 图片ID */
    private String            imageId;

    /** 图片名称 */
    private String            image;

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
     * @see java.lang.Object#toString()
     */
    public String toString() {
        return ToStringUtil.toShortString(this);
    }
}
