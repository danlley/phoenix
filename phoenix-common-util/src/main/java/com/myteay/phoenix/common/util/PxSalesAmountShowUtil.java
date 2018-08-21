/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2018 All Rights Reserved.
 */
package com.myteay.phoenix.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 * 销量工具类
 * 
 * @author danlley
 * @version $Id: PxSalesAmountShowUtil.java, v 0.1 Aug 19, 2018 10:38:55 PM danlley Exp $
 */
public class PxSalesAmountShowUtil {

    /** 日志 */
    public static final Logger       logger                 = Logger.getLogger(PxSalesAmountShowUtil.class);

    /** 默认销量 */
    private static final String      NO_SALES_AMOUNT_STRING = "0";

    /** 销量大于1000 */
    private static final String      S_SALES_AMOUNT_STRING  = "1千+";

    /** 销量大于100 */
    private static final String      HS_SALES_AMOUNT_STRING = "6百+";

    /** 销量大于100 */
    private static final String      HT_SALES_AMOUNT_STRING = "3百+";

    /** 销量大于100 */
    private static final String      H_SALES_AMOUNT_STRING  = "1百+";

    /** 销量大于10 */
    private static final String      T_SALES_AMOUNT_STRING  = "10+";

    /** 星级展示设置 */
    private static final boolean[][] PX_STAR_SHOW           = { { false, false, false, false, false }, { true, false, false, false, false },
                                                                { true, true, false, false, false }, { true, true, true, false, false },
                                                                { true, true, true, true, false }, { true, true, true, true, true } };

    /**
     * 获取星型配置
     * 
     * @param salesAmount
     * @return
     */
    public static boolean[] getStarConfig(String salesAmount) {
        if (StringUtils.isBlank(salesAmount) || !StringUtils.isNumeric(salesAmount)) {
            return PX_STAR_SHOW[0];
        }

        int amount = 0;
        try {
            amount = Integer.parseInt(salesAmount);
        } catch (NumberFormatException e) {
            logger.warn("[星型图例]将商品销量转换为数字过程发生异常 salesAmount=" + salesAmount, e);
        }

        // 说明：下面执行逻辑的顺序不可调整

        if (amount > 1000) {
            return PX_STAR_SHOW[5];
        }

        if (amount > 600) {
            return PX_STAR_SHOW[4];
        }

        if (amount > 300) {
            return PX_STAR_SHOW[3];
        }

        if (amount > 100) {
            return PX_STAR_SHOW[2];
        }

        if (amount > 0) {
            return PX_STAR_SHOW[1];
        }

        if (amount == 0) {
            return PX_STAR_SHOW[0];
        }

        return PX_STAR_SHOW[0];
    }

    /**
     * 获得用于展示的销量
     * 
     * @param salesAmount
     * @return
     */
    public static String getSalesAmountShow(String salesAmount) {

        if (StringUtils.isBlank(salesAmount) || !StringUtils.isNumeric(salesAmount)) {
            return NO_SALES_AMOUNT_STRING;
        }

        int amount = 0;
        try {
            amount = Integer.parseInt(salesAmount);
        } catch (NumberFormatException e) {
            logger.warn("[销量显示]将商品销量转换为数字过程发生异常 salesAmount=" + salesAmount, e);
        }

        // 说明：下面执行逻辑的顺序不可调整

        if (amount > 1000) {
            return S_SALES_AMOUNT_STRING;
        }

        if (amount > 600) {
            return HS_SALES_AMOUNT_STRING;
        }

        if (amount > 300) {
            return HT_SALES_AMOUNT_STRING;
        }

        if (amount > 100) {
            return H_SALES_AMOUNT_STRING;
        }

        if (amount > 10) {
            return T_SALES_AMOUNT_STRING;
        }

        return NO_SALES_AMOUNT_STRING;
    }
}
