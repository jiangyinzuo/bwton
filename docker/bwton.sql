/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80016
 Source Host           : localhost:3306
 Source Schema         : bwton

 Target Server Type    : MySQL
 Target Server Version : 80016
 File Encoding         : 65001

 Date: 15/02/2020 14:40:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;
DROP DATABASE IF EXISTS bwton;
CREATE DATABASE bwton;
USE bwton;

-- ----------------------------
-- Table structure for bwton_credit_day
-- ----------------------------
DROP TABLE IF EXISTS `bwton_credit_day`;
CREATE TABLE `bwton_credit_day`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `credit` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id_date`(`user_id`, `date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bwton_credit_day
-- ----------------------------

-- ----------------------------
-- Table structure for bwton_credit_month
-- ----------------------------
DROP TABLE IF EXISTS `bwton_credit_month`;
CREATE TABLE `bwton_credit_month`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL,
  `date` date NOT NULL,
  `credit` int(10) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id_date`(`user_id`, `date`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bwton_credit_month
-- ----------------------------

-- ----------------------------
-- Table structure for bwton_credit_week
-- ----------------------------
DROP TABLE IF EXISTS `bwton_credit_week`;
CREATE TABLE `bwton_credit_week`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `user_id` int(10) UNSIGNED NOT NULL,
  `week` date NOT NULL,
  `credit` double NOT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id_week`(`user_id`, `week`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bwton_credit_week
-- ----------------------------

-- ----------------------------
-- Table structure for bwton_friendship
-- ----------------------------
DROP TABLE IF EXISTS `bwton_friendship`;
CREATE TABLE `bwton_friendship`  (
  `friend_ship_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '好友关系ID',
  `user1_id` int(10) UNSIGNED NOT NULL COMMENT '用户1ID',
  `user2_id` int(10) UNSIGNED NOT NULL COMMENT '用户2ID',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '加好友时间',
  PRIMARY KEY (`friend_ship_id`) USING BTREE,
  UNIQUE INDEX `uk_user_1_id_user_2_id`(`user1_id`, `user2_id`) USING BTREE,
  INDEX `uk_user_2_id_user_1_id`(`user2_id`, `user1_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bwton_friendship
-- ----------------------------

-- ----------------------------
-- Table structure for bwton_user
-- ----------------------------
DROP TABLE IF EXISTS `bwton_user`;
CREATE TABLE `bwton_user`  (
  `user_id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `cipher` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `salt` tinyblob NOT NULL COMMENT '盐，用于加密',
  `telephone` char(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '手机号',
  `badge` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '出行徽章',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '创建时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `uk_telephone`(`telephone`) USING BTREE COMMENT '一个手机号绑定一个用户账号'
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bwton_user
-- ----------------------------
INSERT INTO `bwton_user` VALUES (5, '南山未北', '8c7344b0f5a64a480e4aca01d0cec74f', 0x38D37A20B226531C8D0F8E21081258DAB7507B10A3B2364EC913CD34C4CA4551, '12334522433', 0, '2020-02-07 21:58:03');
INSERT INTO `bwton_user` VALUES (6, '刘思远', '8cd4efb4b2d7ca956ff72bda927638d0', 0xE8A6369FC8608D475D100F9A86342BF085D58A23EFBB614E7F0401BE13251316, '12334522423', 0, '2020-02-07 21:58:32');
INSERT INTO `bwton_user` VALUES (8, '江胤', '0e68b089e7439d08b4d9068889b55fb0', 0xF54D80D5CCE5A517DEECDFA63006105559E0F62B39DE4B31B25B1523B49711EB, '12334322423', 0, '2020-02-07 21:58:43');
INSERT INTO `bwton_user` VALUES (10, '鲤鱼🐟', '134bdab66c089ac05c52d703a5a48fe8', 0xEE29F6BFE04C162DB0F489D725763420466FA999C413152636BDDA7BDF965F42, '12322322423', 0, '2020-02-07 21:58:57');
INSERT INTO `bwton_user` VALUES (11, 'Melody😄😄', 'ac751360ed4bd027f4ac876adec1b2a2', 0x34B086EDE7F548630F5E70AF2F5BF5B77091207744B70C640923E8B20CBF9787, '12322522423', 0, '2020-02-07 21:59:31');

-- ----------------------------
-- Table structure for bwton_user_prop
-- ----------------------------
DROP TABLE IF EXISTS `bwton_user_prop`;
CREATE TABLE `bwton_user_prop`  (
  `user_id` int(10) UNSIGNED NOT NULL,
  `speeder` smallint(5) UNSIGNED NOT NULL DEFAULT 0,
  `cover` smallint(5) UNSIGNED NOT NULL DEFAULT 0,
  `lottery` smallint(5) UNSIGNED NOT NULL DEFAULT 0,
  `reissue_card` smallint(5) UNSIGNED NOT NULL DEFAULT 0,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bwton_user_prop
-- ----------------------------
INSERT INTO `bwton_user_prop` VALUES (5, 0, 0, 0, 0);
INSERT INTO `bwton_user_prop` VALUES (6, 0, 0, 0, 0);
INSERT INTO `bwton_user_prop` VALUES (8, 0, 0, 0, 0);
INSERT INTO `bwton_user_prop` VALUES (10, 0, 0, 0, 0);
INSERT INTO `bwton_user_prop` VALUES (11, 0, 0, 0, 0);

SET FOREIGN_KEY_CHECKS = 1;
