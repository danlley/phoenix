/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.camp.enums;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.enums.EnumMessage;

/**
 * 活动状态枚举
 * 
 * @author danlley
 * @version $Id: CampStatusEnum.java, v 0.1 Dec 16, 2018 11:14:38 PM danlley Exp $
 */
public enum CampStatusEnum implements EnumMessage {
                                                   CAMP_DRAFT("CAMP_DRAFT", "草稿"),

                                                   CAMP_ONLINE("CAMP_ONLINE", "已启动"),

                                                   CAMP_OFFLINE("CAMP_OFFLINE", "已停止"),

                                                   CAMP_EXPIRED("CAMP_EXPIRED", "已过期"),

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
    private CampStatusEnum(String value, String message) {
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
    public static CampStatusEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (CampStatusEnum type : values()) {
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
    public static CampStatusEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
    * 通过枚举<code>message</code>获得枚举
    * 
    * @param message       枚举描述
    * @return              枚举对象
    */
    public static CampStatusEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (CampStatusEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
