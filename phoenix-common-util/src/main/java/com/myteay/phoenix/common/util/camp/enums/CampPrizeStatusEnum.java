/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.camp.enums;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.enums.EnumMessage;

/**
 * 奖品状态枚举
 * 
 * @author danlley
 * @version $Id: CampPrizeStatusEnum.java, v 0.1 Dec 17, 2018 10:14:39 PM danlley Exp $
 */
public enum CampPrizeStatusEnum implements EnumMessage {
                                                        CAMP_PRIZE_DRAFT("CAMP_PRIZE_DRAFT", "草稿"),

                                                        CAMP_PRIZE_ONLINE("CAMP_PRIZE_ONLINE", "已上架"),

                                                        CAMP_PRIZE_OFFLINE("CAMP_PRIZE_OFFLINE", "已下架"),

                                                        CAMP_PRIZE_EXPIRED("CAMP_PRIZE_EXPIRED", "已过期"),

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
    private CampPrizeStatusEnum(String value, String message) {
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
    public static CampPrizeStatusEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (CampPrizeStatusEnum type : values()) {
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
    public static CampPrizeStatusEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
    * 通过枚举<code>message</code>获得枚举
    * 
    * @param message       枚举描述
    * @return              枚举对象
    */
    public static CampPrizeStatusEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (CampPrizeStatusEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
