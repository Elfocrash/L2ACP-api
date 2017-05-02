/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : acis

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-05-02 11:46:12
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for l2acp_donateitems
-- ----------------------------
DROP TABLE IF EXISTS `l2acp_donateitems`;
CREATE TABLE `l2acp_donateitems` (
  `itemId` int(7) NOT NULL,
  `itemCount` int(9) NOT NULL DEFAULT '1',
  `enchant` int(5) NOT NULL DEFAULT '0',
  `price` int(6) NOT NULL DEFAULT '0'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l2acp_donateitems
-- ----------------------------
INSERT INTO `l2acp_donateitems` VALUES ('57', '100000', '0', '1');
INSERT INTO `l2acp_donateitems` VALUES ('6608', '1', '16', '1');
INSERT INTO `l2acp_donateitems` VALUES ('6598', '1', '16', '5');
INSERT INTO `l2acp_donateitems` VALUES ('6373', '1', '16', '5');
INSERT INTO `l2acp_donateitems` VALUES ('6379', '1', '0', '5');
INSERT INTO `l2acp_donateitems` VALUES ('5779', '1', '16', '5');
INSERT INTO `l2acp_donateitems` VALUES ('5767', '1', '16', '5');
INSERT INTO `l2acp_donateitems` VALUES ('512', '1', '16', '5');
INSERT INTO `l2acp_donateitems` VALUES ('2407', '1', '16', '5');

-- ----------------------------
-- Table structure for l2acp_donateservices
-- ----------------------------
DROP TABLE IF EXISTS `l2acp_donateservices`;
CREATE TABLE `l2acp_donateservices` (
  `serviceid` int(6) NOT NULL,
  `servicename` varchar(30) NOT NULL,
  `price` int(6) NOT NULL DEFAULT '0',
  PRIMARY KEY (`serviceid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l2acp_donateservices
-- ----------------------------
INSERT INTO `l2acp_donateservices` VALUES ('1', 'Change player\'s name', '1');
INSERT INTO `l2acp_donateservices` VALUES ('2', 'Set player nobless', '1');
INSERT INTO `l2acp_donateservices` VALUES ('3', 'Reset player\'s PK', '1');
INSERT INTO `l2acp_donateservices` VALUES ('4', 'Change player\'s sex', '1');

-- ----------------------------
-- Table structure for l2acp_donations
-- ----------------------------
DROP TABLE IF EXISTS `l2acp_donations`;
CREATE TABLE `l2acp_donations` (
  `id` int(9) NOT NULL AUTO_INCREMENT,
  `accountName` varchar(20) NOT NULL,
  `amount` int(5) DEFAULT NULL,
  `transactionid` varchar(60) DEFAULT NULL,
  `verificationSign` varchar(60) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l2acp_donations
-- ----------------------------

-- ----------------------------
-- Table structure for l2acp_luckywheelitems
-- ----------------------------
DROP TABLE IF EXISTS `l2acp_luckywheelitems`;
CREATE TABLE `l2acp_luckywheelitems` (
  `itemid` int(11) NOT NULL,
  `itemcount` int(11) DEFAULT '1',
  `enchant` int(11) DEFAULT '0',
  `chance` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of l2acp_luckywheelitems
-- ----------------------------
INSERT INTO `l2acp_luckywheelitems` VALUES ('6375', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6376', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6377', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6378', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6379', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6380', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6381', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6382', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6383', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('6384', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('7575', '1', '0', '8.33');
INSERT INTO `l2acp_luckywheelitems` VALUES ('7576', '1', '0', '8.33');

-- ----------------------------
-- Table structure for l2acp_onlineanalytics
-- ----------------------------
DROP TABLE IF EXISTS `l2acp_onlineanalytics`;
CREATE TABLE `l2acp_onlineanalytics` (
  `playercount` int(6) NOT NULL,
  `recordedtime` bigint(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `accounts` ADD donatepoints int(9) Default 0;