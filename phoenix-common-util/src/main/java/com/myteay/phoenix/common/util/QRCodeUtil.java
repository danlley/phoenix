package com.myteay.phoenix.common.util;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.OutputStream;
import java.util.Hashtable;

import javax.imageio.ImageIO;

import org.apache.log4j.Logger;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.myteay.common.util.qrcode.BufferedImageLuminanceSource;

/**
 * 二维码生成工具类（带中心图片模式）
 * 
 * @author Administrator
 * @version $Id: QRCodeUtil.java, v 0.1 2016年9月3日 下午11:20:21 Administrator Exp $
 */
public class QRCodeUtil {

    /** 日志 */
    public static final Logger  logger      = Logger.getLogger(QRCodeUtil.class);

    /** 编码格式 */
    private static final String CHARSET     = "utf-8";

    /** 图片类型 */
    private static final String FORMAT_NAME = "JPG";

    /** 二维码尺寸 */
    private static final int    QRCODE_SIZE = 300;

    /** LOGO宽度 */
    private static final int    WIDTH       = 60;

    /** LOGO高度 */
    private static final int    HEIGHT      = 60;

    /**
     * 二维码生成
     * 
     * @param content       二维码内容
     * @param imgPath       图片路径
     * @param needCompress  是否需要压缩
     * @return              返回生成好的二维码图片
     * @throws Exception    异常处理
     */
    private static BufferedImage createImage(String content, String imgPath, boolean needCompress) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
        hints.put(EncodeHintType.MARGIN, 1);
        BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
        int width = bitMatrix.getWidth();
        int height = bitMatrix.getHeight();
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }
        if (imgPath == null || "".equals(imgPath)) {
            return image;
        }
        // 插入图片
        QRCodeUtil.insertImage(image, imgPath, needCompress);
        return image;
    }

    /**
     * 插入中心图片
     * 
     * @param source        已经成圣的二维码
     * @param imgPath       二维码中心图片所在位置
     * @param needCompress  是否需要压缩
     * @throws Exception    异常处理
     */
    private static void insertImage(BufferedImage source, String imgPath, boolean needCompress) throws Exception {
        File file = new File(imgPath);
        if (!file.exists()) {
            System.err.println("" + imgPath + "   该文件不存在！");
            return;
        }
        Image src = ImageIO.read(new File(imgPath));
        int width = src.getWidth(null);
        int height = src.getHeight(null);
        if (needCompress) { // 压缩LOGO
            if (width > WIDTH) {
                width = WIDTH;
            }
            if (height > HEIGHT) {
                height = HEIGHT;
            }
            Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics g = tag.getGraphics();
            g.drawImage(image, 0, 0, null); // 绘制缩小后的图
            g.dispose();
            src = image;
        }
        // 插入LOGO
        Graphics2D graph = source.createGraphics();
        int x = (QRCODE_SIZE - width) / 2;
        int y = (QRCODE_SIZE - height) / 2;
        graph.drawImage(src, x, y, width, height, null);
        Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
        graph.setStroke(new BasicStroke(3f));
        graph.draw(shape);
        graph.dispose();
    }

    /**
     * 二维码编码
     * 
     * @param content       二维码内容
     * @param imgPath       中心图片存放路径
     * @param destPath      目标二维码生成后的图片存放路径
     * @param needCompress  是否压缩
     * @throws Exception    异常处理
     */
    public static void encode(String content, String imgPath, String destPath, boolean needCompress) throws Exception {

        if (logger.isInfoEnabled()) {
            logger.info("开始创建二维码content=" + content + " imgPath=" + imgPath + " destPath=" + destPath + " needCompress=" + needCompress);
        }

        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);
        File file = new File(destPath + "/" + content + ".jpg");
        ImageIO.write(image, FORMAT_NAME, file);
        String path = null;
        if (file != null) {
            path = file.getAbsolutePath();
        }

        logger.warn("二维码生成路径为 path=" + path);
    }

    /**
     * 二维码编码
     * 
     * @param content       二维码内容
     * @param imgPath       中心图片存放路径
     * @param destPath      目标二维码生成后的图片存放路径
     * @param needCompress  是否压缩
     * @throws Exception    异常处理
     */
    public static String encode(String content, String imgPath, boolean needCompress, String destPath) throws Exception {

        if (logger.isInfoEnabled()) {
            logger.info("开始创建二维码content=" + content + " imgPath=" + imgPath + " destPath=" + destPath + " needCompress=" + needCompress);
        }

        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        mkdirs(destPath);

        File file = new File(destPath + "/" + content + ".jpg");
        ImageIO.write(image, FORMAT_NAME, file);
        String path = null;
        if (file != null) {
            path = file.getAbsolutePath();
        }

        logger.warn("二维码生成路径为 path=" + path);

        return content + ".jpg";
    }

    /**
     * 生成二维码存放路径
     * 
     * @param destPath  目标路径地址
     */
    public static void mkdirs(String destPath) {
        File file = new File(destPath);
        //当文件夹不存在时，mkdirs会自动创建多层目录，区别于mkdir．(mkdir如果父目录不存在则会抛出异常)
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 默认二维码生成方式
     * 
     * @param content       二维码内容
     * @param imgPath       中心图片存放路径
     * @param destPath      二维码图片存放路径
     * @throws Exception    异常处理
     */
    public static void encode(String content, String imgPath, String destPath) throws Exception {
        QRCodeUtil.encode(content, imgPath, destPath, false);
    }

    /**
     * 不带图片的二维码生成方式
     * 
     * @param content       二维码内容
     * @param destPath      图片存放路径
     * @param needCompress  是否压缩
     * @throws Exception    异常处理方式
     */
    public static void encode(String content, String destPath, boolean needCompress) throws Exception {
        QRCodeUtil.encode(content, null, destPath, needCompress);
    }

    /**
     * 不进行压缩的二维码图片生成，且没有携带中心图片
     * 
     * @param content       二维码内容
     * @param destPath      二维码图片存放路径
     * @throws Exception    异常处理
     */
    public static void encode(String content, String destPath) throws Exception {
        QRCodeUtil.encode(content, null, destPath, false);
    }

    /**
     * 二维码生成后直接写入IO的编码方式
     * 
     * @param content       二维码内容
     * @param imgPath       中心图片所在路径
     * @param output        用来写入二维码的IO流
     * @param needCompress  是否需要压缩
     * @throws Exception    异常处理
     */
    public static void encode(String content, String imgPath, OutputStream output, boolean needCompress) throws Exception {
        BufferedImage image = QRCodeUtil.createImage(content, imgPath, needCompress);
        ImageIO.write(image, FORMAT_NAME, output);
    }

    /**
     * 不带中心图片的二维码编码后直接写入IO流，同时不做压缩处理
     * 
     * @param content       二维码内容
     * @param output        用来写入二维码的IO流
     * @throws Exception    异常处理
     */
    public static void encode(String content, OutputStream output) throws Exception {
        QRCodeUtil.encode(content, null, output, false);
    }

    /**
     * 二维码文件解码
     * 
     * @param file          二维码文件
     * @return              解析后的二维码包含的信息
     * @throws Exception    异常处理
     */
    public static String decode(File file) throws Exception {
        BufferedImage image;
        image = ImageIO.read(file);
        if (image == null) {
            return null;
        }
        BufferedImageLuminanceSource source = new BufferedImageLuminanceSource(image);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Result result;
        Hashtable<DecodeHintType, String> hints = new Hashtable<DecodeHintType, String>();
        hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
        result = new MultiFormatReader().decode(bitmap, hints);
        String resultStr = result.getText();
        return resultStr;
    }

    /**
     * 直接通过二维码图片存放路径对二维码进行解码
     * 
     * @param path          二维码图片存放路径
     * @return              解码后的二维码存放信息
     * @throws Exception    异常处理
     */
    public static String decode(String path) throws Exception {
        return QRCodeUtil.decode(new File(path));
    }

}
