/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.tc.phoenix.common.util;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期反序列化格式化工具
 * 
 * @author min.weixm
 * @version $Id: PxDateFormat.java, v 0.1 Aug 7, 2018 9:58:52 AM min.weixm Exp $
 */
public class PxDateFormat extends DateFormat {

    /** serialVersionUID */
    private static final long serialVersionUID = 2705007866345086150L;

    /** 日期格式化 */
    private DateFormat        dateFormat;

    /** 日期格式化模板 */
    private SimpleDateFormat  format1          = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");

    /**
     * @param dateFormat
     */
    public PxDateFormat(DateFormat dateFormat) {
        this.dateFormat = dateFormat;
    }

    /** 
     * @see java.text.DateFormat#format(java.util.Date, java.lang.StringBuffer, java.text.FieldPosition)
     */
    @Override
    public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return dateFormat.format(date, toAppendTo, fieldPosition);
    }

    /** 
     * @see java.text.DateFormat#parse(java.lang.String, java.text.ParsePosition)
     */
    @Override
    public Date parse(String source, ParsePosition pos) {

        Date date = null;

        try {

            date = format1.parse(source, pos);
        } catch (Exception e) {

            date = dateFormat.parse(source, pos);
        }

        return date;
    }

    /** 
     * @see java.text.DateFormat#parse(java.lang.String)
     */
    @Override
    public Date parse(String source) throws ParseException {
        // 主要还是装饰这个方法
        Date date = null;

        try {
            // 先按我的规则来
            date = format1.parse(source);
        } catch (Exception e) {
            // 不行，那就按原先的规则吧
            date = dateFormat.parse(source);
        }
        return date;
    }

    /** 
     * @see java.text.DateFormat#clone()
     */
    @Override
    public Object clone() {
        // 这里装饰clone方法的原因是因为clone方法在jackson中也有用到
        Object format = dateFormat.clone();
        return new PxDateFormat((DateFormat) format);
    }

}
