/**
 * Myteay.com Inc.
 * Copyright (c) 2015-2019 All Rights Reserved.
 */
package com.myteay.phoenix.biz.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.myteay.phoenix.common.util.enums.PxOrderContextKeyEnum;
import com.myteay.phoenix.core.model.PxGoodsOrderModel;

/**
 * 订单上下文处理工具类
 * 
 * @author danlley
 * @version $Id: PxGoodsOrderContextUtil.java, v 0.1 Feb 26, 2019 11:57:55 AM danlley Exp $
 */
public class PxGoodsOrderContextUtil {

    /**
     * 填充请求上下文
     * 
     * @param message
     * @param request
     */
    public static void fillOrderContext(PxGoodsOrderModel message, HttpServletRequest request) {
        message.setOrderContext(new HashMap<String, String>());
        Map<String, String> orderContext = message.getOrderContext();

        if (StringUtils.isBlank(message.getUserId())) {
            message.setUserId("741200201802250955144140000");
        }

        // 浏览器地址
        orderContext.put(PxOrderContextKeyEnum.PX_ORDER_SECURITY_EXPLORE.getValue(), getRequestBrowserInfo(request));

        // Host
        orderContext.put(PxOrderContextKeyEnum.PX_ORDER_SECURITY_HOST.getValue(), getRemoteHost(request));

        // IP地址
        orderContext.put(PxOrderContextKeyEnum.PX_ORDER_SECURITY_IP.getValue(), getRemoteIp(request));

        // 
        orderContext.put(PxOrderContextKeyEnum.PX_ORDER_SECURITY_MAC.getValue(), getRequestBrowserInfo(request));

        // 操作系统
        orderContext.put(PxOrderContextKeyEnum.PX_ORDER_SECURITY_OS.getValue(), getRequestSystemInfo(request));
    }

    /**
     * 返回发出请求的客户机的主机名
     * 
     * @param request
     * @return
     */
    private static String getRemoteHost(HttpServletRequest request) {
        return request.getRemoteHost();
    }

    /**
     * 获取访问者IP
     * 在一般情况下使用Request.getRemoteAddr()即可，但是经过nginx等反向代理软件后，这个方法会失效。
     * 
     * 本方法先从Header中获取X-Real-IP，如果不存在再从X-Forwarded-For获得第一个IP(用,分割)，
     * 如果还不存在则调用Request .getRemoteAddr()。
     * @param request
     * @return
     */
    private static String getRemoteIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Real-IP");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            return ip;
        }
        ip = request.getHeader("X-Forwarded-For");
        if (ip != null && !"".equals(ip) && !"unknown".equalsIgnoreCase(ip)) {
            // 多次反向代理后会有多个IP值，第一个为真实IP。
            int index = ip.indexOf(',');
            if (index != -1) {
                return ip.substring(0, index);
            } else {
                return ip;
            }
        } else {
            return request.getRemoteAddr();
        }
    }

    /**
     * 获取来访者的浏览器版本
     * @param request
     * @return
     */
    private static String getRequestBrowserInfo(HttpServletRequest request) {
        String browserVersion = null;
        String header = request.getHeader("user-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        if (header.indexOf("MSIE") > 0) {
            browserVersion = "IE";
        } else if (header.indexOf("Firefox") > 0) {
            browserVersion = "Firefox";
        } else if (header.indexOf("Chrome") > 0) {
            browserVersion = "Chrome";
        } else if (header.indexOf("Safari") > 0) {
            browserVersion = "Safari";
        } else if (header.indexOf("Camino") > 0) {
            browserVersion = "Camino";
        } else if (header.indexOf("Konqueror") > 0) {
            browserVersion = "Konqueror";
        }
        return browserVersion;
    }

    /**
     * 获取系统版本信息
     * @param request
     * @return
     */
    private static String getRequestSystemInfo(HttpServletRequest request) {
        String systenInfo = null;
        String header = request.getHeader("user-agent");
        if (header == null || header.equals("")) {
            return "";
        }
        //得到用户的操作系统
        if (header.indexOf("NT 6.0") > 0) {
            systenInfo = "Windows Vista/Server 2008";
        } else if (header.indexOf("NT 5.2") > 0) {
            systenInfo = "Windows Server 2003";
        } else if (header.indexOf("NT 5.1") > 0) {
            systenInfo = "Windows XP";
        } else if (header.indexOf("NT 6.0") > 0) {
            systenInfo = "Windows Vista";
        } else if (header.indexOf("NT 6.1") > 0) {
            systenInfo = "Windows 7";
        } else if (header.indexOf("NT 6.2") > 0) {
            systenInfo = "Windows Slate";
        } else if (header.indexOf("NT 6.3") > 0) {
            systenInfo = "Windows 9";
        } else if (header.indexOf("NT 5") > 0) {
            systenInfo = "Windows 2000";
        } else if (header.indexOf("NT 4") > 0) {
            systenInfo = "Windows NT4";
        } else if (header.indexOf("Me") > 0) {
            systenInfo = "Windows Me";
        } else if (header.indexOf("98") > 0) {
            systenInfo = "Windows 98";
        } else if (header.indexOf("95") > 0) {
            systenInfo = "Windows 95";
        } else if (header.indexOf("Mac") > 0) {
            systenInfo = "Mac";
        } else if (header.indexOf("Unix") > 0) {
            systenInfo = "UNIX";
        } else if (header.indexOf("Linux") > 0) {
            systenInfo = "Linux";
        } else if (header.indexOf("SunOS") > 0) {
            systenInfo = "SunOS";
        }
        return systenInfo;
    }

}
