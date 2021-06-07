/**
 * GanguTianCan.com Inc.
 * Copyright (c) 2005-2021 All Rights Reserved.
 */
package com.tc.phoenix.common.util;

import java.io.File;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传工具类
 * 
 * @author min.weixm
 * @version $Id: MtFileUtils.java, v 0.1 Aug 4, 2018 2:49:49 PM min.weixm Exp $
 */
public class MtFileUtils {

    /** 日志 */
    public static final Logger logger = Logger.getLogger(MtFileUtils.class);

    /**
     * 上传文件
     * 
     * @param file
     * @param pathPrefix
     * @return
     */
    public static String upload(MultipartFile file, String pathPrefix) {

        if (logger.isInfoEnabled()) {
            logger.info("开始上传文件");
        }

        String fileName = file.getOriginalFilename();
        String fileNameUploaded = new Date().getTime() + ".jpg";

        if (logger.isInfoEnabled()) {
            logger.info("fileName=" + fileName + " fileNameUploaded" + fileNameUploaded + " path=" + pathPrefix);
        }
        File targetFile = new File(pathPrefix, fileNameUploaded);
        if (!targetFile.getParentFile().exists()) {
            targetFile.getParentFile().mkdirs();
        }

        try {
            file.transferTo(targetFile);
        } catch (Throwable e) {
            logger.warn(" fileName=" + fileName + " err: " + e.getMessage(), e);
            return null;
        }

        if (logger.isInfoEnabled()) {
            logger.info(" targetFile=" + targetFile.getAbsolutePath());
        }

        return fileNameUploaded;
    }

}
