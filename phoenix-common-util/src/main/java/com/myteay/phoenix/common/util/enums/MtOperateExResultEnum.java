/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2015 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 返回结果扩展码
 * 
 * @author Administrator
 * @version $Id: MtOperateExResultEnum.java, v 0.1 2015年12月1日 下午4:49:41 Administrator Exp $
 */
public enum MtOperateExResultEnum {

                                   //----------------          基本扩展结果信息信息          ----------------
                                   CAMP_OPERATE_SUCCESS("CAMP_OPERATE_SUCCESS", "00110001", "操作成功"),

                                   CAMP_SQL_EXE_INVALID("CAMP_SQL_EXE_INVALID", "00110002", "SQL执行异常"),

                                   CAMP_ILLEGAL_ARGUMENTS("CAMP_ILLEGAL_ARGUMENTS", "00110003", "参数异常"),

                                   CAMP_USERID_ERR("CAMP_USERID_ERR", "00110004", "userid不合法"),

                                   CAMP_UNKNOW_ERR("CAMP_UNKNOW_ERR", "00110005", "未知错误"),

                                   CAMP_OPERATE_FAILED("CAMP_OPERATE_FAILED", "00110006", "操作失败"),

                                   CAMP_DATA_DIC_NOT_FOUND("CAMP_DATA_DIC_NOT_FOUND", "00014001", "数据字典信息未找到"),

                                   //----------------          基本扩展结果信息信息          ----------------
                                   PX_SHOP_MODEL_INVALID("PX_SHOP_MODEL_INVALID", "00110001", "当前店铺模型非法"),

                                   PX_SHOP_MODEL_ERR("PX_SHOP_MODEL_ERR", "00110002", "店铺关键信息不可用"),

                                   PX_SHOP_SAVE_FAILD("PX_SHOP_SAVE_FAILD", "00110003", "店铺信息保存失败"),

                                   PX_SHOP_QUERY_FAILD("PX_SHOP_QUERY_FAILD", "00110004", "店铺信息查询失败"),

                                   PX_SHOP_OP_UNKNOW("PX_SHOP_OP_UNKNOW", "00110005", "店铺模型中的操作类型未知"),

                                   PX_SHOP_DELETE_FAILD("PX_SHOP_DELETE_FAILD", "00110006", "店铺信息删除失败"),

                                   PX_SHOP_UPDATE_FAILD("PX_SHOP_UPDATE_FAILD", "00110007", "店铺信息修改失败"),

                                   PX_GOODS_MODEL_INVALID("PX_GOODS_MODEL_INVALID", "00110008", "当前商品概要模型非法"),

                                   PX_GOODS_MODEL_ERR("PX_GOODS_MODEL_ERR", "00110009", "商品概要关键信息不可用"),

                                   PX_TEMPLATE_OP_UNKNOW("PX_TEMPLATE_OP_UNKNOW", "00110010", "处理模板操作类型未知"),

                                   PX_GOODS_QUERY_FAILD("PX_GOODS_QUERY_FAILD", "00110011", "商品概要信息查询失败"),

                                   PX_GOODS_UPDATE_FAILD("PX_GOODS_UPDATE_FAILD", "00110012", "商品概要信息修改失败"),

                                   PX_GOODS_DELETE_FAILD("PX_GOODS_DELETE_FAILD", "00110013", "商品概要信息删除失败"),

                                   PX_GOODS_SAVE_FAILD("PX_GOODS_SAVE_FAILD", "00110014", "商品概要信息保存失败"),

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
