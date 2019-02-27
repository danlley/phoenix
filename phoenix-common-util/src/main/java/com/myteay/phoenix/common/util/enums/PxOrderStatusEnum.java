/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author danlley
 * @version $Id: PxOrderStatusEnum.java, v 0.1 Feb 27, 2019 12:47:13 PM danlley Exp $
 */
public enum PxOrderStatusEnum {

                               /** 初始状态 */
                               PX_ORDER_INIT("PX_ORDER_INIT", "初始状态"),

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
    private PxOrderStatusEnum(String value, String message) {
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
    public static PxOrderStatusEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxOrderStatusEnum type : values()) {
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
    public static PxOrderStatusEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PxOrderStatusEnum result : values()) {
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
    public static PxOrderStatusEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxOrderStatusEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
