/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : huodongchat

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2017-02-05 14:38:46
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `qq_message`
-- ----------------------------
DROP TABLE IF EXISTS `qq_message`;
CREATE TABLE `qq_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  `qid` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `zan` int(11) DEFAULT '0',
  `note` varchar(500) DEFAULT NULL,
  `ndate` varchar(50) DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `fusername` varchar(50) DEFAULT NULL,
  `attach` varchar(200) DEFAULT NULL,
  `attachname` varchar(200) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qq_message
-- ----------------------------
INSERT INTO `qq_message` VALUES ('1', '2', '1', null, '1', null, '我来了', '2017-02-04 19:17:20', '小波', '小凤', null, null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('2', '2', '1', null, '2', null, null, '2017-02-04 19:18:05', '小波', '小凤', 'c019bf1a-88f3-4f01-abea-e0b7f9f30b86.aac', null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('3', '2', '1', null, '3', null, null, '2017-02-04 19:18:13', '小波', '小凤', 'f47757d5-e99d-45a3-a834-6313f453a682.jpg', '586e5b574d438b7a9deba36d.jpg', 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('4', '2', '1', null, '1', null, '我来了哈哈', '2017-02-04 21:35:07', '小波', '小凤', null, null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('5', '1', '2', null, '1', null, '我是小凤呵呵', '2017-02-04 21:35:14', '小凤', '小波', null, null, '8695a457-d642-485e-ae68-e427b614b374.jpg');
INSERT INTO `qq_message` VALUES ('6', '2', '3', null, '1', null, '哈哈', '2017-02-04 21:35:30', '小波', '小伊', null, null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('7', '3', '2', null, '1', null, '我来了', '2017-02-04 21:35:51', '小伊', '小波', null, null, '9d9ae017-a219-4ee2-bbf3-914ef0157ed6.jpg');
INSERT INTO `qq_message` VALUES ('8', '3', '2', null, '1', null, '这里是聊天', '2017-02-04 21:35:59', '小伊', '小波', null, null, '9d9ae017-a219-4ee2-bbf3-914ef0157ed6.jpg');
INSERT INTO `qq_message` VALUES ('9', '3', '2', null, '1', null, '不错哦呵呵呵', '2017-02-04 21:36:03', '小伊', '小波', null, null, '9d9ae017-a219-4ee2-bbf3-914ef0157ed6.jpg');
INSERT INTO `qq_message` VALUES ('10', '3', '2', null, '1', null, '你在干什么', '2017-02-04 21:36:06', '小伊', '小波', null, null, '9d9ae017-a219-4ee2-bbf3-914ef0157ed6.jpg');
INSERT INTO `qq_message` VALUES ('11', '3', '2', null, '1', null, '呵呵', '2017-02-04 21:36:15', '小伊', '小波', null, null, '9d9ae017-a219-4ee2-bbf3-914ef0157ed6.jpg');
INSERT INTO `qq_message` VALUES ('12', '2', '3', null, '1', null, '我在', '2017-02-04 21:36:21', '小波', '小伊', null, null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('13', '2', '3', null, '2', null, null, '2017-02-04 21:36:29', '小波', '小伊', 'b9808f5d-80e2-4542-a09f-c5224e14d317.aac', null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('14', '2', '3', null, '3', null, null, '2017-02-04 21:36:38', '小波', '小伊', 'e800891c-07c1-486e-b3c1-d5240ca36855.jpg', '586e5b574d438b7a9deba36d.jpg', 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('15', '2', '3', null, '3', null, null, '2017-02-04 21:36:45', '小波', '小伊', '4be30fc4-b894-440e-96d9-bd63881a68e4.jpg', '586f5a334d438b7a9deba692.jpg', 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('16', '2', '1', '1', '1', '0', '呵呵', '2017-02-04 21:42:06', '小波', '小凤', null, null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('17', '2', '3', null, '1', null, '呵呵', '2017-02-04 21:43:02', '小波', '小伊', null, null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_message` VALUES ('18', '2', '1', null, '1', null, '用', '2017-02-04 21:53:41', '小波', '小凤', null, null, 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');

-- ----------------------------
-- Table structure for `qq_posts`
-- ----------------------------
DROP TABLE IF EXISTS `qq_posts`;
CREATE TABLE `qq_posts` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `uid` varchar(10) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `ndate` varchar(50) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qq_posts
-- ----------------------------
INSERT INTO `qq_posts` VALUES ('1', '今天真舒服', '这里是内容呵呵', '2', '小波', '2017-02-04 21:38:01', '2d7ca9d5-02f3-4587-82b1-3bb9cc5775a0.jpg');

-- ----------------------------
-- Table structure for `qq_qunzu`
-- ----------------------------
DROP TABLE IF EXISTS `qq_qunzu`;
CREATE TABLE `qq_qunzu` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `uid` varchar(10) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `ndate` varchar(50) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  `school` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qq_qunzu
-- ----------------------------
INSERT INTO `qq_qunzu` VALUES ('1', '爬山涉水', '周末去梧桐山', '1', '小凤', null, null, null, null);
INSERT INTO `qq_qunzu` VALUES ('2', '骑行', '周末骑行重庆', '3', '小伊', null, null, null, null);

-- ----------------------------
-- Table structure for `qq_replay`
-- ----------------------------
DROP TABLE IF EXISTS `qq_replay`;
CREATE TABLE `qq_replay` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` varchar(10) DEFAULT NULL,
  `note` varchar(500) DEFAULT NULL,
  `uid` varchar(10) DEFAULT NULL,
  `username` varchar(200) DEFAULT NULL,
  `ndate` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qq_replay
-- ----------------------------
INSERT INTO `qq_replay` VALUES ('1', '1', '自己的可以删除？别人可以评论', '2', '小波', '2017-02-04 21:38:28');

-- ----------------------------
-- Table structure for `qq_type`
-- ----------------------------
DROP TABLE IF EXISTS `qq_type`;
CREATE TABLE `qq_type` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(500) DEFAULT NULL,
  `ownid` varchar(10) DEFAULT NULL,
  `btype` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qq_type
-- ----------------------------

-- ----------------------------
-- Table structure for `qq_user`
-- ----------------------------
DROP TABLE IF EXISTS `qq_user`;
CREATE TABLE `qq_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `passwd` varchar(50) DEFAULT NULL,
  `roletype` varchar(50) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `tel` varchar(50) DEFAULT NULL,
  `qq` varchar(20) DEFAULT NULL,
  `wechat` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `birth` varchar(20) DEFAULT NULL,
  `img` varchar(200) DEFAULT NULL,
  `sid` varchar(10) DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `fids` varchar(200) DEFAULT NULL,
  `qids` varchar(200) DEFAULT NULL,
  `fname` varchar(200) DEFAULT NULL,
  `yuanxi` varchar(200) DEFAULT NULL,
  `zhuanye` varchar(200) DEFAULT NULL,
  `nianji` varchar(200) DEFAULT NULL,
  `xuehao` varchar(200) DEFAULT NULL,
  `status` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qq_user
-- ----------------------------
INSERT INTO `qq_user` VALUES ('1', '小凤', '111111', null, 'ideabobo@126.com', '15123385885', '543548596', '1223', '男', '1986-02-04', 'dd7ae926-c753-4ca5-8f63-1de8aaea1803.jpg', null, '重庆壁山', '2', '1', ',波仔', null, null, null, null, null);
INSERT INTO `qq_user` VALUES ('2', '小波', '1', null, 'ideabobo@126.com', '15123385885', '543548596', '1223', '男', '1986-02-04', 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg', null, '重庆壁山', '1,3', '1', ',波仔', null, null, null, null, null);
INSERT INTO `qq_user` VALUES ('3', '小伊', '111111', null, 'ideabobo@126.com', '15123385885', '543548596', 'ideabobo', '女', '2001-02-04', '9d9ae017-a219-4ee2-bbf3-914ef0157ed6.jpg', null, '重庆璧山', '2', '2', '波仔', null, null, null, null, null);

-- ----------------------------
-- Table structure for `qq_yzmessage`
-- ----------------------------
DROP TABLE IF EXISTS `qq_yzmessage`;
CREATE TABLE `qq_yzmessage` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ndate` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `tid` int(11) DEFAULT NULL,
  `fid` int(11) DEFAULT NULL,
  `fuser` varchar(255) DEFAULT NULL,
  `img` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qq_yzmessage
-- ----------------------------
INSERT INTO `qq_yzmessage` VALUES ('1', '2017-02-04 19:09:11', '已同意', '1', '2', '小波', 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
INSERT INTO `qq_yzmessage` VALUES ('2', '2017-02-04 21:33:40', '已同意', '3', '2', '小波', 'e5584c6f-ef5b-4f21-963c-64fd585ca4e9.jpg');
