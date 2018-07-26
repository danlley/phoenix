/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.manage.enums;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.enums.EnumMessage;

/**
 * 优惠券类型枚举
 * 
 * @author min.weixm
 * @version $Id: PxGoodsOrderTypeEnum.java, v 0.1 Jul 26, 2018 11:21:20 AM min.weixm Exp $
 */
public enum PxGoodsOrderTypeEnum implements EnumMessage {
                                                         PX_NON_APPOINTMENT("PX_NON_APPOINTMENT", "免预约"),

                                                         PX_NEED_APPOINTMENT("PX_NEED_APPOINTMENT", "需要预约"),

                                                         PX_NOTHING("PX_NOTHING", "无"),

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
    private PxGoodsOrderTypeEnum(String value, String message) {
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
    public static PxGoodsOrderTypeEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxGoodsOrderTypeEnum type : values()) {
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
    public static PxGoodsOrderTypeEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
    * 通过枚举<code>message</code>获得枚举
    * 
    * @param message       枚举描述
    * @return              枚举对象
    */
    public static PxGoodsOrderTypeEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxGoodsOrderTypeEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
