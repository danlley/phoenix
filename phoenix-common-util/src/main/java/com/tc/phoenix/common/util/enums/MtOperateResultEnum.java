/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2015-2020 All Rights Reserved.
 */
package com.tc.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 操作结果返回码
 * 
 * @author min.weixm
 * @version $Id: MtOperateResultEnum.java, v 0.1 Feb 10, 2020 10:10:00 PM min.weixm Exp $
 */
public enum MtOperateResultEnum {

                                 CAMP_OPERATE_SUCCESS("CAMP_OPERATE_SUCCESS", "操作成功"),

                                 CAMP_OPERATE_FAILED("CAMP_OPERATE_FAILED", "操作失败"),

                                 CAMP_OPERATE_UNKONW("CAMP_OPERATE_UNKONW", "操作结果未知"),

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
    private MtOperateResultEnum(String value, String message) {
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
    public static MtOperateResultEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (MtOperateResultEnum type : values()) {
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
    public static MtOperateResultEnum getByValue(String value) {
        return getByCode(value);
    }

    /**
     * 通过枚举<code>message</code>获得枚举
     * 
     * @param message       枚举描述
     * @return              枚举对象
     */
    public static MtOperateResultEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (MtOperateResultEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
