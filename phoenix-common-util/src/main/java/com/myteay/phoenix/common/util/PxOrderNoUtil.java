/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.common.util;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.myteay.common.util.tools.DateUtil;
import com.myteay.phoenix.common.util.enums.MtOperateExResultEnum;
import com.myteay.phoenix.common.util.enums.MtOperateResultEnum;
import com.myteay.phoenix.common.util.exception.PxManageException;

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

    /**
     * 获取中奖流水号
     * 
     * @return
     * @throws PxManageException
     */
    public static String getPrizeOutId() throws PxManageException {
        String date = DateUtil.format(new Date(), "yyyyMMddHHmmssSSS");
        String tail = getRandomIntegerWithCertainLen();

        if (StringUtils.isBlank(tail)) {
            throw new PxManageException(MtOperateResultEnum.CAMP_OPERATE_FAILED, MtOperateExResultEnum.CAMP_ILLEGAL_ARGUMENTS);
        }

        return date + tail;
    }

    /**
     * 活动等长度的随机数字符串
     * 
     * @return
     */
    private static String getRandomIntegerWithCertainLen() {
        String source = Integer.toString(new Random().nextInt(999));
        char[] arr = source.toCharArray();
        if (arr.length > 3) {
            return null;
        }

        char[] sourceArr = { '0', '0', '0' };
        System.arraycopy(arr, 0, sourceArr, sourceArr.length - arr.length, arr.length);

        return String.valueOf(sourceArr);
    }

}
