/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.util;

import java.util.UUID;

/**
 * 获取订单号
 * 
 * @author danlley
 * @version $Id: PxOrderNoUtil.java, v 0.1 Feb 26, 2019 2:18:08 PM danlley Exp $
 */
public class PxOrderNoUtil {

    /**
     * 生成UUID
     * 
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().toUpperCase().replace("-", "");
    }

}
