/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.core.service.camp.algorithm.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 算法状态
 * 
 * @author danlley
 * @version $Id: CampAlgorithmStatusEnum.java, v 0.1 Jan 9, 2019 11:20:40 PM danlley Exp $
 */
public enum CampAlgorithmStatusEnum {
                                     CAMP_ALGORITHM_START("CAMP_ALGORITHM_START", "允许出奖"),

                                     CAMP_ALGORITHM_PUSE("CAMP_ALGORITHM_PUSE", "暂停出奖"),

                                     CAMP_ALGORITHM_NO_PRIZE("CAMP_ALGORITHM_NO_PRIZE", "无奖品"),

                                     CAMP_ALGORITHM_END("CAMP_ALGORITHM_END", "出奖结束"),

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
    private CampAlgorithmStatusEnum(String value, String message) {
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
    public static CampAlgorithmStatusEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (CampAlgorithmStatusEnum type : values()) {
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
    public static CampAlgorithmStatusEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
    * 通过枚举<code>message</code>获得枚举
    * 
    * @param message       枚举描述
    * @return              枚举对象
    */
    public static CampAlgorithmStatusEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (CampAlgorithmStatusEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
