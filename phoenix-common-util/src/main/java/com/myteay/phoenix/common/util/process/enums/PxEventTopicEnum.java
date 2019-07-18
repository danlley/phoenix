/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.process.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 消息事件主题
 * 
 * @author Administrator
 * @version $Id: PxEventTopicEnum.java, v 0.1 2016年3月20日 下午9:57:48 Administrator Exp $
 */
public enum PxEventTopicEnum {

    PX_CAMP_PRIZE_MARK("PX_CAMP_PRIZE_MARK", "营销活动奖品打标事件"),

    PX_CAMP_PRIZE_CONSUME("PX_CAMP_PRIZE_CONSUME", "营销活动奖品抵扣事件"),

    PX_GOODS_COST_MARAK("PX_GOODS_COST_MARAK", "成本打标事件"),

    PX_GOODS_COST_COVERED("PX_GOODS_COST_COVERED", "成本覆盖事件"),

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
    private PxEventTopicEnum(String value, String message) {
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
    public static PxEventTopicEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxEventTopicEnum type : values()) {
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
    public static PxEventTopicEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PxEventTopicEnum result : values()) {
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
    public static PxEventTopicEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxEventTopicEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
