/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 
 * @author danlley
 * @version $Id: PxPayTypeEnum.java, v 0.1 Mar 2, 2019 12:04:15 PM danlley Exp $
 */
public enum PxPayTypeEnum {

                           /** 现金支付 */
                           PX_CASH_PAY("PX_CASH_PAY", "现金支付"),

                           /** 现金支付 */
                           PX_WEIXIN_PAY("PX_WEIXIN_PAY", "微信支付"),

                           /** 现金支付 */
                           PX_ALIPAY_PAY("PX_ALIPAY_PAY", "支付宝支付"),

                           /** 未支付 */
                           PX_UN_PAY("PX_UN_PAY", "未支付"),

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
    private PxPayTypeEnum(String value, String message) {
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
    public static PxPayTypeEnum getByCode(String value) {
        if (StringUtils.isBlank(value)) {
            return null;
        }
        value = value.trim();
        for (PxPayTypeEnum type : values()) {
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
    public static PxPayTypeEnum getByValue(String value) {
        if (value == null) {
            return null;
        }
        for (PxPayTypeEnum result : values()) {
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
    public static PxPayTypeEnum getByMessage(String message) {
        if (StringUtils.isBlank(message)) {
            return null;
        }
        for (PxPayTypeEnum result : values()) {
            if (result.getMessage().equals(message)) {
                return result;
            }
        }
        return null;
    }
}
