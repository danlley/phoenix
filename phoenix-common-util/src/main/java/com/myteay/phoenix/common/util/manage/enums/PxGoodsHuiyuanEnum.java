/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.manage.enums;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.enums.EnumMessage;

/**
 * 是否支持会员类型枚举
 * 
 * @author min.weixm
 * @version $Id: PxGoodsHuiyuanEnum.java, v 0.1 Jul 26, 2018 11:21:20 AM min.weixm Exp $
 */
public enum PxGoodsHuiyuanEnum implements EnumMessage {
                                                       PX_SUPPORT_HUIYUAN("PX_SUPPORT_HUIYUAN", "仅支持会员"),

                                                       PX_UNSUPPORT_HUIYUAN("PX_UNSUPPORT_HUIYUAN", "全员通用"),

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
    private PxGoodsHuiyuanEnum(String value, String message) {
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
    public static PxGoodsHuiyuanEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxGoodsHuiyuanEnum type : values()) {
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
    public static PxGoodsHuiyuanEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
    * 通过枚举<code>message</code>获得枚举
    * 
    * @param message       枚举描述
    * @return              枚举对象
    */
    public static PxGoodsHuiyuanEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxGoodsHuiyuanEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
