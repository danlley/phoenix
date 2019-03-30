/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.camp.enums;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.enums.EnumMessage;

/**
 * 奖品状态
 * 
 * @author danlley
 * @version $Id: CampPrizeOutStatusEnum.java, v 0.1 Mar 27, 2019 11:13:19 PM danlley Exp $
 */
public enum CampPrizeOutStatusEnum implements EnumMessage {
                                                           CAMP_PRIZE_OUT_GIVEN("CAMP_PRIZE_OUT_GIVEN", "已发放"),

                                                           CAMP_PRIZE_OUT_CONSUMED("CAMP_PRIZE_OUT_CONSUMED", "已消费"),

                                                           CAMP_PRIZE_OUT_EXPIRED("CAMP_PRIZE_OUT_EXPIRED", "已过期"),

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
    private CampPrizeOutStatusEnum(String value, String message) {
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
    public static CampPrizeOutStatusEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (CampPrizeOutStatusEnum type : values()) {
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
    public static CampPrizeOutStatusEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
    * 通过枚举<code>message</code>获得枚举
    * 
    * @param message       枚举描述
    * @return              枚举对象
    */
    public static CampPrizeOutStatusEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (CampPrizeOutStatusEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
