/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 操作类型枚举
 * 
 * @author min.weixm
 * @version $Id: PxOperationTypeEnum.java, v 0.1 Jul 24, 2018 7:03:09 PM min.weixm Exp $
 */
public enum PxOperationTypeEnum {
                                 /** 新增 */
                                 PX_ADD("PX_ADD", "新增"),

                                 /** 修改 */
                                 PX_MODIFY("PX_MODIFY", "修改"),

                                 /** 删除 */
                                 PX_DELETE("PX_DELETE", "删除"),

                                 /** 单条查询 */
                                 PX_QUERY_ONE("PX_QUERY_ONE", "单条查询"),

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
    private PxOperationTypeEnum(String value, String message) {
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
    public static PxOperationTypeEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxOperationTypeEnum type : values()) {
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
    public static PxOperationTypeEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PxOperationTypeEnum result : values()) {
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
    public static PxOperationTypeEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxOperationTypeEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
