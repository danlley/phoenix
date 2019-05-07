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

                                   CAMP_DATA_DIC_NOT_FOUND("CAMP_DATA_DIC_NOT_FOUND", "00011007", "数据字典信息未找到"),

                                   CAMP_UN_SUPPORT("CAMP_UN_SUPPORT", "00011008", "当前不支持该操作"),

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

                                   PX_PKG_DETAIL_MODEL_INVALID("PX_PKG_DETAIL_MODEL_INVALID", "00110015", "当前套餐包模型非法"),

                                   PX_PKG_DETAIL_MODEL_ERR("PX_PKG_DETAIL_MODEL_ERR", "00110016", "套餐包关键信息不可用"),

                                   PX_PKG_DETAIL_QUERY_FAILD("PX_PKG_DETAIL_QUERY_FAILD", "00110019", "套餐包信息查询失败"),

                                   PX_PKG_DETAIL_UPDATE_FAILD("PX_PKG_DETAIL_UPDATE_FAILD", "00110020", "套餐包信息修改失败"),

                                   PX_PKG_DETAIL_DELETE_FAILD("PX_PKG_DETAIL_DELETE_FAILD", "00110021", "套餐包信息删除失败"),

                                   PX_PKG_DETAIL_SAVE_FAILD("PX_PKG_DETAIL_SAVE_FAILD", "00110022", "套餐包信息保存失败"),

                                   PX_SUB_PKG_MODEL_INVALID("PX_SUB_PKG_MODEL_INVALID", "00110017", "子套餐模型非法"),

                                   PX_SUB_PKG_MODEL_ERR("PX_SUB_PKG_MODEL_ERR", "00110018", "子套餐关键信息不可用"),

                                   PX_SUB_PKG_QUERY_FAILD("PX_SUB_PKG_QUERY_FAILD", "00110023", "子套餐信息查询失败"),

                                   PX_SUB_PKG_UPDATE_FAILD("PX_SUB_PKG_UPDATE_FAILD", "00110024", "子套餐信息修改失败"),

                                   PX_SUB_PKG_DELETE_FAILD("PX_SUB_PKG_DELETE_FAILD", "00110025", "子套餐信息删除失败"),

                                   PX_SUB_PKG_SAVE_FAILD("PX_SUB_PKG_SAVE_FAILD", "00110026", "子套餐信息保存失败"),

                                   PX_PKG_IMAGE_MODEL_INVALID("PX_PKG_IMAGE_MODEL_INVALID", "00110027", "当前套餐详情图片模型非法"),

                                   PX_PKG_IMAGE_MODEL_ERR("PX_PKG_IMAGE_MODEL_ERR", "00110028", "套餐详情图片关键信息不可用"),

                                   PX_PKG_IMAGE_QUERY_FAILD("PX_PKG_IMAGE_QUERY_FAILD", "00110029", "套餐详情图片信息查询失败"),

                                   PX_PKG_IMAGE_DELETE_FAILD("PX_PKG_IMAGE_DELETE_FAILD", "00110030", "套餐详情图片信息删除失败"),

                                   PX_PKG_IMAGE_SAVE_FAILD("PX_PKG_IMAGE_SAVE_FAILD", "00110031", "套餐详情图片信息保存失败"),

                                   PX_PKG_ADV_QUERY_FAILD("PX_PKG_ADV_QUERY_FAILD", "00110032", "套餐高级查询失败"),

                                   PX_PKG_NOTICE_MODEL_ERR("PX_PKG_NOTICE_MODEL_ERR", "00110033", "温馨提醒摘要关键信息不可用"),

                                   PX_PKG_NOTICE_MODEL_INVALID("PX_PKG_DETAIL_NOTICE_INVALID", "00110034", "当前温馨提醒摘要模型非法"),

                                   PX_PKG_NOTICE_QUERY_FAILD("PX_PKG_NOTICE_QUERY_FAILD", "00110035", "温馨提醒摘要信息查询失败"),

                                   PX_PKG_NOTICE_UPDATE_FAILD("PX_PKG_NOTICE_UPDATE_FAILD", "00110036", "温馨提醒摘要信息修改失败"),

                                   PX_PKG_NOTICE_DELETE_FAILD("PX_PKG_NOTICE_DELETE_FAILD", "00110037", "温馨提醒摘要信息删除失败"),

                                   PX_PKG_NOTICE_SAVE_FAILD("PX_PKG_NOTICE_SAVE_FAILD", "00110038", "温馨提醒摘要信息保存失败"),

                                   PX_PKG_SUB_NOTICE_MODEL_ERR("PX_PKG_SUB_NOTICE_MODEL_ERR", "00110039", "温馨提醒子项关键信息不可用"),

                                   PX_PKG_SUB_NOTICE_MODEL_INVALID("PX_PKG_SUB_NOTICE_MODEL_INVALID", "00110040", "温馨提醒子项模型非法"),

                                   PX_PKG_SUB_NOTICE_QUERY_FAILD("PX_PKG_SUB_NOTICE_QUERY_FAILD", "00110041", "温馨提醒子项信息查询失败"),

                                   PX_PKG_SUB_NOTICE_UPDATE_FAILD("PX_PKG_SUB_NOTICE_UPDATE_FAILD", "00110042", "温馨提醒子项信息修改失败"),

                                   PX_PKG_SUB_NOTICE_DELETE_FAILD("PX_PKG_SUB_NOTICE_DELETE_FAILD", "00110043", "温馨提醒子项信息删除失败"),

                                   PX_PKG_SUB_NOTICE_SAVE_FAILD("PX_PKG_SUB_NOTICE_SAVE_FAILD", "00110044", "温馨提醒子项信息保存失败"),

                                   PX_GOODS_NOTFOUND_ERR("PX_GOODS_NOTFOUND_EXCEPTION", "00110045", "当前商品未找到"),

                                   PX_GOODS_STATUS_EQUAL("PX_GOODS_STATUS_EQUAL", "00110046", "当前商品状态无需变更"),

                                   PX_GOODS_TYPE_UNCOVERED("PX_GOODS_TYPE_UNCOVERED", "00110047", "商品团、券、会员支持、预约类型等信息不完整"),

                                   PX_GOODS_PRICE_UNCOVERED("PX_GOODS_PRICE_UNCOVERED", "00110048", "商品价格信息不完整"),

                                   PX_GOODS_TITLE_UNCOVERED("PX_GOODS_TITLE_UNCOVERED", "00110049", "商品标题及套餐信息不完整"),

                                   PX_GOODS_ID_UNCOVERED("PX_GOODS_ID_UNCOVERED", "00110050", "商品Id或所在店铺Id信息缺失"),

                                   PX_GOODS_IMG_TIME_UNCOVERED("PX_GOODS_IMG_TIME_UNCOVERED", "00110050", "商品图片或在线时间不完整"),

                                   PX_SHOP_EXPIRED_ERR("PX_SHOP_EXPIRED_ERR", "00110051", "店铺过期时间不可用"),

                                   PX_SHOP_OFFLINE_ERR("PX_SHOP_OFFLINE_ERR", "00110052", "店铺已下架"),

                                   PX_IMG_AMOUNT_ERR("PX_IMG_AMOUNT_ERR", "00110053", "商品详情图片数量不合法"),

                                   PX_IMG_DETAIL_TYPE_ERR("PX_IMG_DETAIL_TYPE_ERR", "00110054", "商品详情图片格式不合法"),

                                   PX_IMG_TYPE_ERR("PX_IMG_TYPE_ERR", "00110055", "商品摘要图片格式不合法"),

                                   PX_GOODS_PACKAGE_AMOUNT_ERR("PX_GOODS_PACKAGE_AMOUNT_ERR", "00110056", "商品套餐包数量信息不合法"),

                                   PX_GOODS_SUB_PACKAGE_AMOUNT_ERR("PX_GOODS_SUB_PACKAGE_AMOUNT_ERR", "00110057", "商品子套餐数量不合法"),

                                   PX_GOODS_SUB_PACKAGE_TYPE_ERR("PX_GOODS_SUB_PACKAGE_TYPE_ERR", "00110058", "子套餐类型不合法"),

                                   PX_GOODS_SUB_PACKAGE_PRICE_ERR("PX_GOODS_SUB_PACKAGE_PRICE_ERR", "00110059", "子套餐单价不合法"),

                                   PX_GOODS_SUB_PACKAGE_TOTAL_ERR("PX_GOODS_SUB_PACKAGE_TOTAL_ERR", "00110060", "子套餐数量不合法"),

                                   PX_GOODS_SUB_PACKAGE_NAME_ERR("PX_GOODS_SUB_PACKAGE_NAME_ERR", "00110061", "子套餐名称不合法"),

                                   PX_GOODS_NOTICE_AMOUNT_ERR("PX_GOODS_NOTICE_AMOUNT_ERR", "00110062", "商品温馨提醒数量不合法"),

                                   PX_GOODS_NOTICE_NAME_ERR("PX_GOODS_NOTICE_NAME_ERR", "00110063", "商品温馨提醒分类名称不合法"),

                                   PX_GOODS_SUB_NOTICE_AMOUNT_ERR("PX_GOODS_SUB_NOTICE_AMOUNT_ERR", "00110064", "商品温馨提醒子项数量不合法"),

                                   PX_GOODS_SUB_NOTICE_TITLE_ERR("PX_GOODS_SUB_NOTICE_TITLE_ERR", "00110065", "商品温馨提醒子项内容不合法"),

                                   PX_GOODS_STATUS_UNKNOW("PX_GOODS_STATUS_UNKNOW", "00110066", "商品的目标变更状态未知，请确认商品是否需要发布or下架"),

                                   PX_GOODS_EXPIRE_UNKNOW("PX_GOODS_EXPIRE_UNKNOW", "00110067", "商品过期时间不可用"),

                                   PX_GOODS_EXPIRE_ERR("PX_GOODS_EXPIRE_ERR", "00110068", "商品过期时间已到，无法保持发布状态"),

                                   PX_SHOP_EXPIRE_ERR("PX_SHOP_EXPIRE_ERR", "00110069", "店铺合同已到期"),

                                   PX_SHOP_HAS_GOODS_ERR("PX_SHOP_HAS_GOODS_ERR", "00110070", "店铺包含商品，无法删除"),

                                   PX_GOODS_HAS_CHILD_ERR("PX_GOODS_HAS_CHILD_ERR", "00110071", "商品包含子项，无法删除"),

                                   PX_GOODS_DEL_STATUS_ERR("PX_GOODS_DEL_STATUS_ERR", "00110072", "商品状态为已发布、已下线，则不允许进行删除"),

                                   PX_PACKAGES_DEL_ERR("PX_PACKAGES_DEL_ERR", "00110073", "商品套餐包含子项，无法删除"),

                                   PX_PACKAGES_DEL_VALIDATE_ERR("PX_PACKAGES_DEL_VALIDATE_ERR", "00110074", "检查套餐子项出错，无法删除套餐"),

                                   PX_PACKAGES_NOTICE_DEL_ERR("PX_PACKAGES_NOTICE_DEL_ERR", "00110075", "商品温馨提醒包含子项，无法删除"),

                                   PX_SUB_NOTICE_VALIDATE_ERR("PX_SUB_NOTICE_VALIDATE_ERR", "00110076", "检查温馨提醒子项出错，无法删除"),

                                   PX_SUB_PKGS_DEL_VALIDATE_ERR("PX_SUB_PKGS_DEL_VALIDATE_ERR", "00110077", "子套餐对应的商品状态为已发布或已下架，不允许删除"),

                                   PX_SUB_NOTICE_DEL_VALIDATE_ERR("PX_SUB_NOTICE_DEL_VALIDATE_ERR", "00110078", "子提醒对应的商品状态为已发布或已下架，不允许删除"),

                                   PX_SHOP_ONLINE_DEL_ERR("PX_SHOP_ONLINE_DEL_ERR", "00110079", "店铺状态在线，无法删除"),

                                   PX_GOODS_ONLINE_ADD_ERR("PX_GOODS_ONLINE_ADD_ERR", "00110080", "当前商品不满足追加套餐包条件，请检查商品是否已发布或已下线"),

                                   PX_GOODS_ONLINE_MODIFY_ERR("PX_GOODS_ONLINE_MODIFY_ERR", "00110081", "当前商品不满足修改套餐包条件，请检查商品是否已发布或已下线 "),

                                   PX_IMG_ONLINE_ADD_ERR("PX_IMG_ONLINE_ADD_ERR", "00110082", "当前商品不满足追加详情图片条件，请检查商品是否已发布或已下线"),

                                   PX_SUBPKG_ONLINE_ADD_ERR("PX_SUBPKG_ONLINE_ADD_ERR", "00110083", "当前商品不满足追加子套餐条件，请检查商品是否已发布或已下线"),

                                   PX_NOTICE_ONLINE_ADD_ERR("PX_NOTICE_ONLINE_ADD_ERR", "00110084", "当前商品不满足追加温馨提醒分类条件，请检查商品是否已发布或已下线"),

                                   PX_NOTICE_ONLINE_MODIFY_ERR("PX_NOTICE_ONLINE_MODIFY_ERR", "00110085", "当前商品不满足修改温馨提醒分类条件，请检查商品是否已发布或已下线"),

                                   PX_SUBNOTICE_ONLINE_ADD_ERR("PX_SUBNOTICE_ONLINE_ADD_ERR", "00110086", "当前商品不满足追加子提醒条件，请检查商品是否已发布或已下线"),

                                   PX_GOODS_ORDER_OUT_MODEL_ERR("PX_GOODS_ORDER_OUT_MODEL_ERR", "00110087", "订单模型不可用"),

                                   PX_GOODS_ORDER_OUT_OP_ERR("PX_GOODS_ORDER_OUT_OP_ERR", "00110088", "订单流水操作失败"),

                                   PX_GOODS_ORDER_OUT_UP_ERR("PX_GOODS_ORDER_OUT_UP_ERR", "00110089", "订单流水更新失败"),

                                   CAMP_BASE_MODEL_INVALID("CAMP_BASE_MODEL_INVALID", "00210001", "店内营销基本信息模型非法"),

                                   CAMP_BASE_DEL_STATUS_ERR("CAMP_BASE_DEL_STATUS_ERR", "00210002", "店内营销活动状态为已发布、已下线，则不允许进行删除"),

                                   CAMP_BASE_DELETE_FAILD("CAMP_BASE_DELETE_FAILD", "00210003", "店内营销活动删除失败"),

                                   CAMP_BASE_HAS_CHILD_ERR("CAMP_BASE_HAS_CHILD_ERR", "00210004", "店内营销活动包含子项，无法删除"),

                                   CAMP_BASE_SAVE_FAILD("CAMP_BASE_SAVE_FAILD", "00210005", "店内营销活动保存失败"),

                                   CAMP_BASE_QUERY_FAILD("CAMP_BASE_QUERY_FAILD", "00210006", "店内营销单个活动查询失败"),

                                   CAMP_BASE_UPDATE_FAILD("CAMP_BASE_UPDATE_FAILD", "00210007", "店内营销单个活动信息修改失败"),

                                   CAMP_PRIZE_QUERY_FAILD("CAMP_PRIZE_QUERY_FAILD", "00210008", "店内营销奖品信息查询失败"),

                                   CAMP_PRIZE_REF_GOODS_MODEL_ERR("CAMP_PRIZE_REF_GOODS_MODEL_ERR", "00210009", "奖品关联商品模型校验失败"),

                                   CAMP_PRIZE_REF_GOODS_ERR("CAMP_PRIZE_REF_GOODS_ERR", "00210010", "在线活动的奖品关联商品"),

                                   CAMP_PRIZE_REF_GOODS_MNG_ERR("CAMP_PRIZE_REF_GOODS_MNG_ERR", "00210011", "管理店内营销活动奖品关联商品发生异常"),

                                   CAMP_PRIZE_REF_GOODS_QRY_ERR("CAMP_PRIZE_REF_GOODS_QRY_ERR", "00210012", "查询店内营销活动奖品关联商品发生异常"),

                                   CAMP_PRIZE_REF_GOODS_OVERFLOW("CAMP_PRIZE_REF_GOODS_OVERFLOW", "00210013", "奖品价值超过商品价值，将无法进行后续抵扣"),

                                   CAMP_BASE_UPDATE_REF_ERR("CAMP_BASE_UPDATE_REF_ERR", "00210014", "活动状态变更前的关联性检查未通过"),

                                   CAMP_PRIZE_UPDATE_REF_ERR("CAMP_PRIZE_UPDATE_REF_ERR", "00210015", "奖品状态变更前的关联性检查未通过"),

                                   CAMP_PRIZE_HAS_CHILD_ERR("CAMP_PRIZE_HAS_CHILD_ERR", "00210016", "店内营销活动奖品包含子项，无法删除"),

                                   CAMP_BASE_ONLINE_ERR("CAMP_BASE_ONLINE_ERR", "00210016", "当前活动已启动，无法保存当前奖品"),

                                   COST_MODEL_ERR("COST_MODEL_ERR", "00310001", "成本模型不可用"),

                                   COST_MODEL_OP_MNG_FAILD("COST_MODEL_OP_MNG_FAILD", "00310002", "成本模型管理失败"),

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
