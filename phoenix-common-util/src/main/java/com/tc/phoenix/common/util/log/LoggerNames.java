/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2019 All Rights Reserved.
 */
package com.tc.phoenix.common.util.log;

/**
 * 日志统一管理
 * 
 * @author danlley
 * @version $Id: LoggerNames.java, v 0.1 Apr 1, 2019 10:34:03 PM danlley Exp $
 */
public interface LoggerNames {

    /** 基础数据管理日志 */
    public static final String PX_MNG             = "PX-MNG";

    /** 定时任务日志 */
    public static final String PX_TASK            = "PX-TASK";

    /** 缓存管理日志 */
    public static final String PX_CACHE_DEFAULT   = "PX-CACHE-DEFAULT";

    /** 收银台默认日志 */
    public static final String PX_CASHIER_DEFAULT = "PX-CASHIER-DEFAULT";

    /** 收银台摘要日志 */
    public static final String PX_CASHIER_DIGEST  = "PX-CASHIER-DIGEST";

}
