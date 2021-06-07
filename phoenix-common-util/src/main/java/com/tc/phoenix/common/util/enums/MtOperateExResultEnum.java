/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.tc.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * 
 * @author min.weixm
 * @version $Id: MtOperateExResultEnum.java, v 0.1 Feb 10, 2020 10:10:17 PM min.weixm Exp $
 */
public enum MtOperateExResultEnum {

                                   CAMP_OPERATE_SUCCESS("CAMP_OPERATE_SUCCESS", "00110001", "操作成功"),

                                   CAMP_ILLEGAL_ARGUMENTS("CAMP_ILLEGAL_ARGUMENTS", "00110003", "参数异常"),

                                   CAMP_USERID_ERR("CAMP_USERID_ERR", "00110004", "userid不合法"),

                                   CAMP_UNKNOW_ERR("CAMP_UNKNOW_ERR", "00110005", "未知错误"),

                                   CAMP_OPERATE_FAILED("CAMP_OPERATE_FAILED", "00110006", "操作失败"),

                                   CAMP_UN_SUPPORT("CAMP_UN_SUPPORT", "00011008", "当前不支持该操作"),

    ;
    /** 枚举值 */
    private final String value;

    /** 枚举描述 */
    private final String message;

    /** 枚举描述 */
    private final String code;

    /**
     * 私有构造方法
     * 
     * @param value         枚举值
     * @param message       枚举描述
     */
    private MtOperateExResultEnum(String value, String code, String message) {
        this.value = value;
        this.code = code;
        this.message = message;
    }

    /**
     * Getter method for property <tt>code</tt>.
     * 
     * @return property value of code
     */
    public String getCode() {
        return code;
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
    public static MtOperateExResultEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtOperateExResultEnum type : values()) {
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
    public static MtOperateExResultEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
     * 通过枚举<code>message</code>获得枚举
     * 
     * @param message       枚举描述
     * @return              枚举对象
     */
    public static MtOperateExResultEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtOperateExResultEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
