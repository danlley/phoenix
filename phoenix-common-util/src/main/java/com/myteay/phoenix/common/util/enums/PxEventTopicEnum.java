/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2016 All Rights Reserved.
 */
package com.myteay.phoenix.common.util.enums;

import org.apache.commons.lang.StringUtils;

/**
 * 消息事件主题
 * 
 * @author Administrator
 * @version $Id: PxEventTopicEnum.java, v 0.1 2016年3月20日 下午9:57:48 Administrator Exp $
 */
public enum PxEventTopicEnum {

                              PX_GOODS_STATUS_CHANGED("PX_GOODS_STATUS_CHANGED", "店铺信息变更事件"),

                              PX_CAMP_BASE_STATUS_CHANGED("PX_CAMP_BASE_STATUS_CHANGED", "店内营销活动信息变更事件"),

                              PX_CAMP_PRIZE_STATUS_CHANGED("PX_CAMP_PRIZE_STATUS_CHANGED", "店内营销活动奖品状态变更事件"),

                              PX_TASK_MOBILE_GOODS_CACHE_LOAD("PX_TASK_MOBILE_GOODS_CACHE_LOAD", "手机端商品列表定时加载事件"),

                              PX_TASK_SHOP_EXPIRED("PX_TASK_SHOP_EXPIRED", "监控店铺过期时间事件"),

                              PX_GOODS_COST_CHANGED("PX_GOODS_COST_CHANGED", "商品当日经营成本变更事件"),

                              PX_GOODS_COST_CFG_CHANGED("PX_GOODS_COST_CFG_CHANGED", "商品成本配置变更事件"),

                              CAMP_TASK_EXPIRED("CAMP_TASK_EXPIRED", "监控店铺营销活动过期时间事件"),

                              CAMP_STATUS_CHANGED("CAMP_STATUS_CHANGED", "监控店铺营销活动状态变更事件"),

                              CAMP_PRIZE_TASK_EXPIRED("CAMP_PRIZE_TASK_EXPIRED", "监控店铺营销活动奖品过期时间事件"),

                              CAMP_PRIZE_OUT_TASK_EXPIRED("CAMP_PRIZE_OUT_TASK_EXPIRED", "监控店铺营销活动奖品中奖流水过期时间事件"),

                              CAMP_PRIZE_OUT_EXPIRED("CAMP_PRIZE_OUT_EXPIRED", "对过期的已发放奖品进行过期处理"),

                              CAMP_PRIZE_OUT_CONSUMED_MV("CAMP_PRIZE_OUT_CONSUMED_MV", "对消费奖品进行迁移处理"),

                              CAMP_PRIZE_OUT_EXPIRED_MV("CAMP_PRIZE_OUT_EXPIRED_MV", "对已过期奖品进行迁移处理"),

                              PX_TASK_SHOP_EXPIRED_ORDERS("PX_TASK_SHOP_EXPIRED_ORDERS", "监控店铺过期订单事件"),

                              PX_TASK_SHOP_PAYED_ORDERS("PX_TASK_SHOP_PAYED_ORDERS", "监控店铺已支付待迁移订单事件"),

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
