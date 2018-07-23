/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.21-1ubuntu1 : Database - phoenix
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`phoenix` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `phoenix`;

/*Table structure for table `px_goods` */

DROP TABLE IF EXISTS `px_goods`;

CREATE TABLE `px_goods` (
  `goods_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '商品流水号',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `goods_image` varchar(128) NOT NULL COMMENT '商品缩略图',
  `goods_title` varchar(32) NOT NULL COMMENT '商品标题',
  `goods_desc` varchar(32) NOT NULL COMMENT '套餐信息类型',
  `goods_price` tinyint(4) NOT NULL COMMENT '当前售价',
  `goods_comm_price` tinyint(4) NOT NULL COMMENT '原始价格',
  `is_huiyuan` varchar(32) DEFAULT NULL COMMENT '是否会员',
  `is_quan` varchar(32) DEFAULT NULL COMMENT '是否有券',
  `is_tuan` varchar(32) DEFAULT NULL COMMENT '是否团购',
  `goods_sell_amount` varchar(32) DEFAULT NULL COMMENT '当前销售量',
  `gmt_expired` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '商品过期时间',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`goods_id`,`shop_id`,`goods_image`,`goods_title`,`goods_desc`,`goods_price`,`goods_comm_price`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_goods` */

/*Table structure for table `px_goods_packages` */

DROP TABLE IF EXISTS `px_goods_packages`;

CREATE TABLE `px_goods_packages` (
  `goods_packages_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '套餐ID',
  `goods_id` bigint(20) NOT NULL COMMENT '商品ID',
  `goods_online_time` varchar(32) NOT NULL COMMENT '营业时间',
  `order_type` varchar(32) NOT NULL COMMENT '订购类型，如：免预约',
  `goods_packages_image_id` bigint(20) NOT NULL COMMENT '套餐图片ID',
  `goods_packages_detail_id` bigint(20) NOT NULL COMMENT '套餐包详情ID',
  `goods_packages_notice_id` bigint(20) NOT NULL COMMENT '套餐提示信息ID',
  `gmt_created` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  KEY `goods_packages_id` (`goods_packages_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_goods_packages` */

/*Table structure for table `px_goods_packages_detail` */

DROP TABLE IF EXISTS `px_goods_packages_detail`;

CREATE TABLE `px_goods_packages_detail` (
  `packages_detail_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '套餐详情子包ID',
  `goods_packages_detail_id` bigint(20) NOT NULL COMMENT '套餐包详情ID',
  `package_detail_name` varchar(32) NOT NULL COMMENT '子包名称',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`packages_detail_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_goods_packages_detail` */

/*Table structure for table `px_goods_packages_image` */

DROP TABLE IF EXISTS `px_goods_packages_image`;

CREATE TABLE `px_goods_packages_image` (
  `image_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `goods_packages_image_id` bigint(20) DEFAULT NULL COMMENT '套餐包图片ID',
  `image` varchar(128) DEFAULT NULL COMMENT '图片地址',
  `gmt_create` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`image_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_goods_packages_image` */

/*Table structure for table `px_goods_packages_notice` */

DROP TABLE IF EXISTS `px_goods_packages_notice`;

CREATE TABLE `px_goods_packages_notice` (
  `packages_notice_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '套餐包提示子项ID',
  `goods_packages_notice_id` bigint(20) DEFAULT NULL COMMENT '套餐包提示ID',
  `packages_notice_name` varchar(32) DEFAULT NULL COMMENT '套餐包子项名称',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`packages_notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_goods_packages_notice` */

/*Table structure for table `px_packages_sub_notice` */

DROP TABLE IF EXISTS `px_packages_sub_notice`;

CREATE TABLE `px_packages_sub_notice` (
  `packages_sub_notice_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '套餐包单项提醒信息ID',
  `packages_notice_id` bigint(20) NOT NULL COMMENT '套餐包提醒ID',
  `sub_notice_detail` varchar(128) NOT NULL COMMENT '提醒内容',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`packages_sub_notice_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_packages_sub_notice` */

/*Table structure for table `px_shop` */

DROP TABLE IF EXISTS `px_shop`;

CREATE TABLE `px_shop` (
  `shop_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '店铺ID',
  `shop_name` varchar(32) NOT NULL COMMENT '店铺名称',
  `shop_address` varchar(64) NOT NULL COMMENT '店铺地址',
  `shop_tel` varchar(16) NOT NULL COMMENT '店铺联系电话',
  `waiter_name` varchar(32) NOT NULL COMMENT '店铺联系人',
  `owner_name` varchar(32) NOT NULL COMMENT '店铺主管',
  `owner_phone` varchar(32) NOT NULL COMMENT '店铺主管电话',
  `owner_idcard` varchar(32) NOT NULL COMMENT '店铺主管身份证',
  `shop_status` varchar(32) NOT NULL COMMENT '店铺状态',
  `gmt_expired` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '店铺过期时间',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_shop` */

/*Table structure for table `px_sub_packages` */

DROP TABLE IF EXISTS `px_sub_packages`;

CREATE TABLE `px_sub_packages` (
  `sub_packages_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '套餐子商品ID',
  `packages_detail_id` bigint(20) NOT NULL COMMENT '套餐子包ID',
  `sub_packages_name` varchar(32) NOT NULL COMMENT '子商品名称',
  `sub_packages_amount` varchar(32) NOT NULL COMMENT '子商品数量',
  `sub_packages_type` varchar(32) NOT NULL COMMENT '套餐类型，如：大份、小份等',
  `sub_package_price` varchar(32) NOT NULL COMMENT '子商品单价',
  `gmt_created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`sub_packages_id`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `px_sub_packages` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
