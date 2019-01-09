/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.manage.enums;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.enums.EnumMessage;

/**
 * 商品类型枚举
 * 
 * @author danlley
 * @version $Id: PxGoodsTypeEnum.java, v 0.1 Dec 19, 2018 12:29:53 AM danlley Exp $
 */
public enum PxGoodsTypeEnum implements EnumMessage {
                                                    PX_GOODS_CR_CM_PKG("PX_GOODS_CR_CM_PKG", "成人普通套餐"),

                                                    PX_GOODS_RT_CM_PKG("PX_GOODS_RT_CM_PKG", "儿童普通套餐"),

                                                    PX_GOODS_RT_ZC_PKG("PX_GOODS_RT_ZC_PKG", "儿童早餐套餐"),

                                                    PX_GOODS_QSN_CM_PKG("PX_GOODS_QSN_CM_PKG", "青少年普通套餐"),

                                                    PX_GOODS_QSN_ZC_PKG("PX_GOODS_QSN_ZC_PKG", "青少年早餐套餐"),

                                                    PX_GOODS_CR_ZC_PKG("PX_GOODS_CR_ZC_PKG", "成人早餐套餐"),

                                                    PX_GOODS_YX_PKG("PX_GOODS_YX_PKG", "夜宵套餐"),

                                                    PX_GOODS_HB("PX_GOODS_HB", "主食"),

                                                    PX_GOODS_NC("PX_GOODS_NC", "奶茶"),

                                                    PX_GOODS_CY("PX_GOODS_CY", "茶饮"),

                                                    PX_GOODS_PS("PX_GOODS_PS", "配餐"),

                                                    PX_GOODS_BQL("PX_GOODS_BQL", "冰淇淋"),

    ;

    /** 枚举值 */
    private final String value;

    /** 枚举描述 */
    private final String message;

    /**
    * 私有构造方法
    * 
    * @param value         枚举值
    * @param message       枚举描述
    */
    private PxGoodsTypeEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    /**
    * Getter method for property <tt>value</tt>.
    * 
    * @return property value of value
    */
    public String getValue() {
        return value;
    }

    /**
    * Getter method for property <tt>message</tt>.
    * 
    * @return property value of message
    */
    public String getMessage() {
        return message;
    }

    /**
    * 通过值获取枚举对象
    * @param value     枚举值
    * @return          枚举对象
    */
    public static PxGoodsTypeEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxGoodsTypeEnum type : values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        return null;

    }

    /**
    * 通过枚举<code>value</code>获得枚举
    * 
    * @param value     枚举值
    * @return          枚举对象
    */
    public static PxGoodsTypeEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
    * 通过枚举<code>message</code>获得枚举
    * 
    * @param message       枚举描述
    * @return              枚举对象
    */
    public static PxGoodsTypeEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxGoodsTypeEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
