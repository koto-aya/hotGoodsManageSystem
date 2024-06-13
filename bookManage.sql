/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 8.0.33 : Database - hot_goods_manage
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`hot_goods_manage` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `hot_goods_manage`;

/*Table structure for table `administrator` */

DROP TABLE IF EXISTS `administrator`;

CREATE TABLE `administrator` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `admin_name` varchar(50) NOT NULL COMMENT '管理员名称',
  `passwd` varchar(255) DEFAULT NULL COMMENT '密码',
  `avatar_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '管理员头像',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `administrator` */

LOCK TABLES `administrator` WRITE;

insert  into `administrator`(`id`,`admin_name`,`passwd`,`avatar_path`,`create_time`,`update_time`,`is_deleted`) values 
(1,'root','root','aaa','2024-06-06 11:57:06','2024-06-13 16:53:53',0),
(4,'admin','202cb962ac59075b964b07152d234b70','http://localhost:8080/hotGoodsManageSystem/img/7e82cb06cb02497887ec80f34e482f8c.jpg','2024-06-07 21:03:32','2024-06-13 16:57:30',0),
(9,'wangzewen','f4ad254b1258cc686c6d4b9652fbb354','http://localhost:8080/hotGoodsManageSystem/img/6906c02994d14e24acde571beb6403cb.jpg','2024-06-13 17:03:48','2024-06-13 17:03:48',0);

UNLOCK TABLES;

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '自增id',
  `gno` varchar(32) NOT NULL COMMENT '商品编号',
  `goods_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `goods_img` varchar(255) DEFAULT NULL COMMENT '商品图片',
  `goods_desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
  `price` double NOT NULL COMMENT '商品价格',
  `is_hot` tinyint DEFAULT '0' COMMENT '是否热门商品',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `is_deleted` tinyint DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `goods` */

LOCK TABLES `goods` WRITE;

insert  into `goods`(`id`,`gno`,`goods_name`,`goods_img`,`goods_desc`,`price`,`is_hot`,`create_time`,`update_time`,`is_deleted`) values 
(1,'1310fc5a69264b8e959525c210893951','洁唯【大规格】抽纸家用卫生纸巾婴儿面巾纸餐巾纸整箱批发实惠装','https://img.alicdn.com/bao/uploaded/i3/3937219703/O1CN01q17zBH2LY1zL29xgD_!!3937219703-0-C2M.jpg',NULL,88,1,'2024-06-07 10:52:03','2024-06-13 14:24:07',0),
(3,'3483a0aca77b4f0bbfd03ad16cf928bb','得力笔按动黑色中性笔签字笔刷题水笔0.5商务办公老师按压红蓝碳素速干圆珠笔学生考试用摁动式笔中性笔批发','https://img.alicdn.com/bao/uploaded/i3/2207704400411/O1CN01zCSNqG1EuHgvERuaI_!!2207704400411.jpg','fesg kdjirsg dagsrnin efldlsef nsleflrwl lsfnensfmaf sefnsngse  snes e nsei a esejwegrlig enfseg eenseinwe fefwe gpriger',123,1,'2024-06-08 16:13:53','2024-06-13 14:24:27',0),
(4,'d07b357a995c4625ab5215b261dabcfb','MSENSE蔓莎秘境无火香薰礼盒室内持久扩香器摆件高级香氛送礼女生','https://img.alicdn.com/bao/uploaded/i2/3937219703/O1CN018vliPv2LY1yrHgiba_!!3937219703-0-C2M.jpg','esgrgrgsgr',50,1,'2024-06-08 16:46:43','2024-06-13 14:25:08',0),
(5,'e841f5c052374d2cbd82817cffd18693','冻干柠檬片500g干片蜂蜜柠檬片泡水喝冷泡水喝果茶金桔柠檬百香果','https://img.alicdn.com/bao/uploaded/i4/3937219703/O1CN01cPMNQ52LY1ynMews0_!!3937219703-0-C2M.jpg','fegrsg rgs grs ',45,1,'2024-06-08 16:48:49','2024-06-13 14:25:34',0),
(6,'a8508bf723594e729b5c85cba3b037b1','搓澡巾洗澡强力搓泥澡巾北方女男士专用搓背长条后背神器搓澡拉背','https://img.alicdn.com/bao/uploaded/i4/3937219703/O1CN01KSVtLJ2LY1zNu3cs6_!!3937219703-0-C2M.jpg','测试商品',99,1,'2024-06-08 16:49:41','2024-06-13 14:26:28',0),
(7,'a2d83f43a08b4cb485c0190598900b1a','空调遥控器万能通用款全部海信美的奥克斯格力海尔志高长虹松下新科龙TCL格兰仕华凌春兰三菱扬子小米LG大金','https://img.alicdn.com/imgextra/i4/2990650384/O1CN01pC8JK61EhuyshhLQD_!!2990650384-0-alimamacc.jpg','测试商品',109,0,'2024-06-08 16:50:00','2024-06-13 14:26:31',0),
(8,'12b34448f26a47209062bf86c11d1ce7','test','http://localhost:8080/hotGoodsManageSystem/img/b70bb2333047402ea21f7203df9f32a0.jpg','testtest',999,0,'2024-06-13 17:07:27','2024-06-13 17:07:27',0);

UNLOCK TABLES;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
