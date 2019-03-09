/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 订单上下文关键字管理枚举
 * 
 * @author danlley
 * @version $Id: PxOrderContextKeyEnum.java, v 0.1 Feb 26, 2019 11:51:48 AM danlley Exp $
 */
public enum PxOrderContextKeyEnum {

                                   /** 客户端MAC地址 */
                                   PX_ORDER_SECURITY_MAC("PX_ORDER_SECURITY_MAC", "客户端MAC地址"),

                                   /** 客户端IP地址 */
                                   PX_ORDER_SECURITY_IP("PX_ORDER_SECURITY_IP", "客户端IP地址"),

                                   /** 客户端操作系统 */
                                   PX_ORDER_SECURITY_OS("PX_ORDER_SECURITY_OS", "客户端操作系统"),

                                   /** 浏览器类型 */
                                   PX_ORDER_SECURITY_EXPLORE("PX_ORDER_SECURITY_EXPLORE", "浏览器类型"),

                                   /** 主机名 */
                                   PX_ORDER_SECURITY_HOST("PX_ORDER_SECURITY_HOST", "主机名"),

                                   /** 活动 */
                                   PX_ORDER_CAMP_ID("PX_ORDER_CAMP_ID", "活动ID"),

    ;
    /** value */
    private final String value;

    /** message */
    private final String message;

    /**
    * 私有构造方法
    * @param code
    * @param description
    */
    private PxOrderContextKeyEnum(String value, String message) {
        this.value = value;
        this.message = message;
    }

    /**
    * @return Returns the value.
    */
    public String getValue() {
        return value;
    }

    /**
    * @return Returns the message.
    */
    public String getMessage() {
        return message;
    }

    /**
    * 通过值获取枚举对象
    * @param value
    * @return
    */
    public static PxOrderContextKeyEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxOrderContextKeyEnum type : values()) {
            if (type.getValue().equals(value))
                return type;
        }
        return null;

    }

    /**
    * 通过枚举<code>value</code>获得枚举
    * 
    * @param value
    * @return
    */
    public static PxOrderContextKeyEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PxOrderContextKeyEnum result : values()) {
            if (result.getValue().equals(value)) {
                return result;
            }
        }
        return null;
    }

    /**
    * 通过枚举<code>name</code>获得枚举
    * 
    * @param message
    * @return
    */
    public static PxOrderContextKeyEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxOrderContextKeyEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
