/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80012
 Source Host           : localhost:3306
 Source Schema         : linsen

 Target Server Type    : MySQL
 Target Server Version : 80012
 File Encoding         : 65001

 Date: 26/08/2020 18:07:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for plan_day
-- ----------------------------
DROP TABLE IF EXISTS `plan_day`;
CREATE TABLE `plan_day`  (
  `day_id` int(11) NOT NULL AUTO_INCREMENT,
  `week_id` int(11) DEFAULT NULL,
  `day_date` date DEFAULT NULL,
  `day_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci,
  `day_state` tinyint(2) DEFAULT NULL COMMENT '完成状态 3-未完成 4-完成',
  `day_score` int(255) DEFAULT NULL,
  `day_desc` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`day_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for plan_master
-- ----------------------------
DROP TABLE IF EXISTS `plan_master`;
CREATE TABLE `plan_master`  (
  `master_id` int(11) NOT NULL AUTO_INCREMENT,
  `parent_id` int(11) NOT NULL DEFAULT -1 COMMENT '父级ID 月份就是年份ID 周就是月份ID',
  `plan_date` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '月周',
  `master_type` tinyint(2) NOT NULL COMMENT '1-年 2-月 3-周 4-日',
  `plan_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '主题',
  `plan_content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '以分号区分',
  `plan_create_date` datetime(0) NOT NULL COMMENT '创建日期',
  `plan_start` date NOT NULL COMMENT '开始日期',
  `plan_end` date NOT NULL COMMENT '结束日期',
  `plan_state` tinyint(4) NOT NULL COMMENT '0-未开始 1-进行中 2-未完成 4-完成',
  `plan_score` tinyint(3) NOT NULL DEFAULT 0 COMMENT '分数',
  `plan_desc` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '描述',
  `plan_delete` tinyint(1) NOT NULL DEFAULT 0 COMMENT '删除标志 1-删除',
  PRIMARY KEY (`master_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for plan_year
-- ----------------------------
DROP TABLE IF EXISTS `plan_year`;
CREATE TABLE `plan_year`  (
  `year_id` int(11) NOT NULL AUTO_INCREMENT,
  `year` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年份',
  `year_target` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '年度目标',
  `year_score` int(3) DEFAULT NULL COMMENT '年度分数',
  `year_summary` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci COMMENT '年度总结',
  `year_state` tinyint(1) DEFAULT NULL COMMENT '年度状态 0-未开始 1-进行中 2-未完成 4-完成',
  PRIMARY KEY (`year_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
