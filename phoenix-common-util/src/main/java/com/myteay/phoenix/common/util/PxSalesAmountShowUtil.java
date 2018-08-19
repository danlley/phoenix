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
    public static final Logger  logger                 = Logger.getLogger(PxSalesAmountShowUtil.class);

    /** 默认销量 */
    private static final String NO_SALES_AMOUNT_STRING = "0";

    /** 销量大于1000 */
    private static final String S_SALES_AMOUNT_STRING  = "1000+";

    /** 销量大于100 */
    private static final String H_SALES_AMOUNT_STRING  = "100+";

    /** 销量大于10 */
    private static final String T_SALES_AMOUNT_STRING  = "10+";

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
            logger.warn("将商品销量转换为数字过程发生异常 salesAmount=" + salesAmount, e);
        }

        // 说明：下面执行逻辑的顺序不可调整

        if (amount > 1000) {
            return S_SALES_AMOUNT_STRING;
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
