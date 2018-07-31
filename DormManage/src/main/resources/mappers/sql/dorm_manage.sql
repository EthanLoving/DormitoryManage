/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50022
Source Host           : 127.0.0.1:3306
Source Database       : dorm_manage

Target Server Type    : MYSQL
Target Server Version : 50022
File Encoding         : 65001

Date: 2018-05-05 00:55:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for article_record
-- ----------------------------
DROP TABLE IF EXISTS `article_record`;
CREATE TABLE `article_record` (
  `id` varchar(32) NOT NULL,
  `article_name` varchar(50) NOT NULL,
  `build_id` varchar(32) NOT NULL,
  `enter_time` datetime default NULL,
  `out_time` datetime default NULL,
  `creater` varchar(30) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of article_record
-- ----------------------------
INSERT INTO `article_record` VALUES ('0821a665c8e74c3cbfd88ef72f5da85d', '饮水机', '12d6ac918a914a109a89fbc76ca08d20', '2018-02-26 22:09:16', '2018-02-07 22:09:00', '王五', '2018-02-26 22:09:08');
INSERT INTO `article_record` VALUES ('4f084dbabced4400ac52c042316d4860', '空调', '0f565a8ed80743978799aaed371583f0', '2018-03-21 18:58:22', '2018-04-30 22:21:05', '张三', '2018-03-28 22:43:22');
INSERT INTO `article_record` VALUES ('5c0b771d0e95476280f82b0525fce4f7', '水壶', '3cc1a3a94cc745e2b1e172c8af1f260b', '2018-05-08 22:51:21', null, '张三', '2018-05-04 22:51:25');
INSERT INTO `article_record` VALUES ('63e25910192548249ef25cf13c672f52', '杯子', '427301b9832f49ca898db433a2873f04', '2018-02-19 18:29:08', '2018-02-19 16:39:46', '张三', '2018-02-25 16:39:52');
INSERT INTO `article_record` VALUES ('7814e076e5bf4de2b5df3e704b2e786d', '吹风机', '255cb55f854e4a8ba23d7fe9021b50a1', '2018-02-27 22:09:51', '2018-01-31 22:09:55', '王五', '2018-02-26 22:10:03');
INSERT INTO `article_record` VALUES ('af35febbc4be4a169036a818e4c84a0f', '椅子', '0f565a8ed80743978799aaed371583f0', '2018-02-19 16:39:17', '2018-02-08 22:09:25', '张三', '2018-02-25 16:39:26');

-- ----------------------------
-- Table structure for dorm
-- ----------------------------
DROP TABLE IF EXISTS `dorm`;
CREATE TABLE `dorm` (
  `id` varchar(32) NOT NULL,
  `dorm_no` varchar(30) NOT NULL,
  `dorm_capacity` int(1) NOT NULL default '4',
  `dorm_num` int(1) NOT NULL default '0',
  `build_id` varchar(32) NOT NULL,
  `is_delete` int(1) NOT NULL default '0',
  `creater` varchar(30) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dorm
-- ----------------------------
INSERT INTO `dorm` VALUES ('025a64fc58244dc8a9a998e07b2b224f', 't_4002', '4', '0', 'fac26c3b29b7474989e9cadd1f10904d', '0', '张三', '2018-02-23 19:25:01');
INSERT INTO `dorm` VALUES ('052058a1eda947c488b28f5719839048', 't_3001', '4', '0', '1f52c18a19354fdcb2ca7d7f74e73f74', '1', '张三', '2018-02-23 19:23:49');
INSERT INTO `dorm` VALUES ('0551603c3ae04ca5aaf65d8e803e63a6', 'y_6001', '4', '1', '6a57d3dda980496e90dbe32c151e7a05', '0', '张三', '2018-02-23 19:30:34');
INSERT INTO `dorm` VALUES ('0d34def28ef94a9388d4971a1736dd0b', 't_5001', '4', '0', '12d6ac918a914a109a89fbc76ca08d20', '0', '张三', '2018-02-23 16:32:34');
INSERT INTO `dorm` VALUES ('13b08986354549c79e18fb4f7fa18902', 'y_1001', '4', '0', '9544159dbb354bfb9b169713f6753665', '1', '张三', '2018-02-23 19:27:19');
INSERT INTO `dorm` VALUES ('13ce8ee7fcbd4a768b8e8c955210cb7e', 'girl_1001', '6', '0', '4e9d2f0f53a9465eb3a49167df2e869f', '0', '张三', '2018-02-23 19:34:40');
INSERT INTO `dorm` VALUES ('1d3fba72a735440ab764bd2830b9e107', 't_8002', '4', '0', 'a14baca7e8dc4a03a78feb1046dc4647', '0', '张三', '2018-02-23 19:26:50');
INSERT INTO `dorm` VALUES ('2a5e0743e3cb4aeeb6fcb52cf620c817', 't_2001', '4', '0', '3cc1a3a94cc745e2b1e172c8af1f260b', '0', '张三', '2018-02-23 16:33:22');
INSERT INTO `dorm` VALUES ('3fef46755e234b929cafb0e8bafcaba5', 't_6001', '4', '0', 'e6162acd90684a0ea7a69585fdfe28fe', '0', '张三', '2018-02-23 19:25:19');
INSERT INTO `dorm` VALUES ('43de41504ed84d70aa8e079223cbce86', 'y_3001', '8', '0', '42df4c73f6d448ef8172b80775a8f261', '1', '张三', '2018-02-23 19:28:31');
INSERT INTO `dorm` VALUES ('4419c27107dd419f8711919ab04836eb', 'y_2002', '6', '0', '3cc1a3a94cc745e2b1e172c8af1f260b', '0', '张三', '2018-02-23 19:28:17');
INSERT INTO `dorm` VALUES ('458d9770f3084e718230635b3634bc5b', 't_7001', '4', '0', 'ec4517052e7b4b78bcb4cce9b302830c', '0', '张三', '2018-02-23 19:25:56');
INSERT INTO `dorm` VALUES ('4642a87d9e85405c96cec96b954b8d10', 'y_5002', '6', '0', '752228cd06b4486da57619bb59e0aaa1', '1', '张三', '2018-02-23 19:30:17');
INSERT INTO `dorm` VALUES ('4751fc68d2f642edb66e7b1b74a53fe9', 't_5112', '5', '0', '12d6ac918a914a109a89fbc76ca08d20', '1', '张三', '2018-05-04 21:46:53');
INSERT INTO `dorm` VALUES ('4aa07332e49c4531a4e88d4e3213db8f', 't_2002', '4', '0', 'c89039f527914a6b97b49f2a812de7e2', '0', '张三', '2018-02-23 16:34:39');
INSERT INTO `dorm` VALUES ('4fae20f81e944cad946e770a077d42dc', 't_6002', '4', '0', 'e6162acd90684a0ea7a69585fdfe28fe', '0', '张三', '2018-02-23 19:25:33');
INSERT INTO `dorm` VALUES ('50ad94270e3d4831b155655ea5f1a5fc', 't_1001', '4', '0', '9e16853fa3364e0f85c3c29d11a0375f', '1', '张三', '2018-02-23 19:22:31');
INSERT INTO `dorm` VALUES ('5cde882183a74d0dbb173d3b838692f3', 'y_4001', '6', '0', '0f565a8ed80743978799aaed371583f0', '0', '张三', '2018-02-23 19:29:19');
INSERT INTO `dorm` VALUES ('6582bd5a20f54ca8b487eea3ef85a0f5', 't_4001', '4', '1', 'fac26c3b29b7474989e9cadd1f10904d', '0', '张三', '2018-02-23 19:24:09');
INSERT INTO `dorm` VALUES ('6ad983e27cba4bfd9b0696d1e7f69c33', 'boy_1001', '4', '0', '255cb55f854e4a8ba23d7fe9021b50a1', '0', '张三', '2018-02-23 19:31:15');
INSERT INTO `dorm` VALUES ('731a2d42754b4be183d7d0dacf22a347', 't_7002', '4', '0', 'ec4517052e7b4b78bcb4cce9b302830c', '0', '张三', '2018-02-23 19:26:14');
INSERT INTO `dorm` VALUES ('78fa0c2bf10148c5858ab7e39e3ecd01', 'y_4002', '6', '0', '0f565a8ed80743978799aaed371583f0', '0', '张三', '2018-02-23 19:29:33');
INSERT INTO `dorm` VALUES ('865b1c69b4984e33ad547075279bef18', 'y_2001', '6', '0', '3cc1a3a94cc745e2b1e172c8af1f260b', '0', '张三', '2018-02-23 19:28:00');
INSERT INTO `dorm` VALUES ('8b8a9ee0eed64d90a55f656f359defb0', 't_8003', '6', '0', 'a14baca7e8dc4a03a78feb1046dc4647', '1', '张三', '2018-05-04 21:14:17');
INSERT INTO `dorm` VALUES ('8e8db3fc9dd04095ac36a2974a939951', 't_3002', '4', '0', '1f52c18a19354fdcb2ca7d7f74e73f74', '1', '张三', '2018-02-23 19:24:35');
INSERT INTO `dorm` VALUES ('8f2b5e7d39204c73ba9d891ab0aab5b8', 't_5002', '4', '0', '12d6ac918a914a109a89fbc76ca08d20', '1', '张三', '2018-02-23 16:32:55');
INSERT INTO `dorm` VALUES ('9a0e6e464d0842f1a54d7a41fd33690b', 'y_1002', '6', '0', '9544159dbb354bfb9b169713f6753665', '1', '张三', '2018-02-23 19:27:44');
INSERT INTO `dorm` VALUES ('9abb5bf108e84a4e886e745836ee2709', 'y_5001', '8', '0', '752228cd06b4486da57619bb59e0aaa1', '1', '张三', '2018-02-23 19:30:01');
INSERT INTO `dorm` VALUES ('a032c16e4a0a4c4faa6669a70c59c5a6', 't_1002', '4', '0', '9e16853fa3364e0f85c3c29d11a0375f', '1', '张三', '2018-02-23 19:23:24');
INSERT INTO `dorm` VALUES ('c60598ed9b1b4566bdf116af7545196b', 'y_3002', '8', '0', '42df4c73f6d448ef8172b80775a8f261', '1', '张三', '2018-02-23 19:28:57');
INSERT INTO `dorm` VALUES ('d437571b07ff4eb78d46420250cf959b', 't_5110', '4', '4', '12d6ac918a914a109a89fbc76ca08d20', '0', '张三', '2018-05-04 21:46:29');
INSERT INTO `dorm` VALUES ('ed084433b1d44db9b92f80a3631eee9f', 't_8001', '4', '1', 'a14baca7e8dc4a03a78feb1046dc4647', '0', '张三', '2018-02-23 19:26:29');

-- ----------------------------
-- Table structure for dorm_build
-- ----------------------------
DROP TABLE IF EXISTS `dorm_build`;
CREATE TABLE `dorm_build` (
  `id` varchar(32) NOT NULL,
  `build_no` varchar(30) NOT NULL,
  `build_name` varchar(50) NOT NULL,
  `build_type` int(1) NOT NULL,
  `build_description` varchar(100) default NULL,
  `build_is_delete` int(1) NOT NULL default '0',
  `creater` varchar(30) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `build_no` (`build_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dorm_build
-- ----------------------------
INSERT INTO `dorm_build` VALUES ('0f565a8ed80743978799aaed371583f0', 'yicheng_4', '议城四栋', '0', '议城四栋女生宿舍', '0', '张三', '2018-02-22 04:04:03');
INSERT INTO `dorm_build` VALUES ('12d6ac918a914a109a89fbc76ca08d20', 'tenglong5', '腾龙五栋', '1', '腾龙五栋男生宿舍', '0', '张三', '2018-02-21 05:05:04');
INSERT INTO `dorm_build` VALUES ('1f52c18a19354fdcb2ca7d7f74e73f74', 'tenglong3', '腾龙三栋', '1', '腾龙三栋男生宿舍', '1', '张三', '2018-02-14 02:02:57');
INSERT INTO `dorm_build` VALUES ('255cb55f854e4a8ba23d7fe9021b50a1', 'boy_yard', '男生大院', '1', '男生大院宿舍楼', '0', '张三', '2018-02-15 22:58:01');
INSERT INTO `dorm_build` VALUES ('2d7b81f51ba14ac4a23a028f080a5679', 'yicheng_10', '议城十栋', '1', '议城十栋男生宿舍', '1', '张三', '2018-02-20 02:00:57');
INSERT INTO `dorm_build` VALUES ('37b9f2fb4b4e4dd68872b06a018f5e72', 'yicheng_8', '议城八栋', '0', '议城八栋女生宿舍', '0', '张三', '2018-02-16 03:55:02');
INSERT INTO `dorm_build` VALUES ('3cc1a3a94cc745e2b1e172c8af1f260b', 'yicheng_2', '议城二栋', '1', '议城二栋男生宿舍', '0', '张三', '2018-02-17 03:57:56');
INSERT INTO `dorm_build` VALUES ('427301b9832f49ca898db433a2873f04', 'yicheng_12', '议城十二栋', '0', '议城十二栋女生宿舍', '0', '张三', '2018-02-13 21:57:58');
INSERT INTO `dorm_build` VALUES ('42df4c73f6d448ef8172b80775a8f261', 'yicheng_3', '议城三栋', '0', '议城三栋女生宿舍', '1', '张三', '2018-02-19 21:55:56');
INSERT INTO `dorm_build` VALUES ('4dbb4ed8864d4a14aa3dc1b172bafced', 'tenglong19', '腾龙十九栋', '0', '腾龙十九栋男生宿舍', '1', '张三', '2018-05-04 21:40:15');
INSERT INTO `dorm_build` VALUES ('4e9d2f0f53a9465eb3a49167df2e869f', 'girl_yard', '女生大院', '0', '女生大院宿舍楼', '0', '张三', '2018-02-18 15:11:45');
INSERT INTO `dorm_build` VALUES ('504d4a94decf4405ad203a275b8d9ff9', 'yichneg_13', '议城十三栋', '1', '这是议城十三栋男生宿舍，用省略号来表示超出的内容。', '0', '张三', '2018-03-19 10:20:49');
INSERT INTO `dorm_build` VALUES ('6a57d3dda980496e90dbe32c151e7a05', 'yicheng_6', '议城六栋', '1', '议城六栋男生宿舍', '0', '张三', '2018-02-21 01:58:02');
INSERT INTO `dorm_build` VALUES ('752228cd06b4486da57619bb59e0aaa1', 'yicheng_5', '议城五栋', '0', '议城五栋女生宿舍', '1', '张三', '2018-02-18 23:59:03');
INSERT INTO `dorm_build` VALUES ('9544159dbb354bfb9b169713f6753665', 'yicheng_1', '议城一栋', '1', '议城一栋男生宿舍', '1', '张三', '2018-02-16 21:58:04');
INSERT INTO `dorm_build` VALUES ('969eb2a15f9a486584ce77bbbd6a6669', 'yicheng_7', '议城七栋', '0', '议城七栋女生宿舍', '0', '张三', '2018-02-15 22:59:57');
INSERT INTO `dorm_build` VALUES ('9e16853fa3364e0f85c3c29d11a0375f', 'tenglong1', '腾龙一栋', '0', '腾龙一栋女生宿舍', '1', '张三', '2018-02-13 10:02:58');
INSERT INTO `dorm_build` VALUES ('a14baca7e8dc4a03a78feb1046dc4647', 'tenglong8', '腾龙八栋', '0', '腾龙八栋女生宿舍', '0', '张三', '2018-02-17 22:58:58');
INSERT INTO `dorm_build` VALUES ('a15e71c27a4346af830e334a96a3ce65', 'yicheng_9', '议城九栋', '1', '议城九栋男生宿舍', '0', '张三', '2018-02-19 22:54:56');
INSERT INTO `dorm_build` VALUES ('c89039f527914a6b97b49f2a812de7e2', 'tenglong2', '腾龙二栋', '0', '腾龙二栋女生宿舍', '0', '张三', '2018-02-11 21:24:39');
INSERT INTO `dorm_build` VALUES ('d0ca0da1b8074f2a83bb6f145b00c3d6', 'tenglong20', '腾龙二十栋', '1', '腾龙二十栋男生宿舍', '1', '张三', '2018-05-04 21:06:41');
INSERT INTO `dorm_build` VALUES ('e60a91cec99b4225b9ac87d07751fe11', 'yicheng_14', '议城十四栋', '0', '议城十四栋女生宿舍，位于本校天桥对面的宿舍楼中，旁边有很多小商店。', '0', '张三', '2018-03-28 22:05:36');
INSERT INTO `dorm_build` VALUES ('e6162acd90684a0ea7a69585fdfe28fe', 'tenglong6', '腾龙六栋', '1', '腾龙六栋男生宿舍', '0', '张三', '2018-02-20 08:46:40');
INSERT INTO `dorm_build` VALUES ('ec4517052e7b4b78bcb4cce9b302830c', 'tenglong7', '腾龙七栋', '0', '腾龙七栋女生宿舍', '0', '张三', '2018-02-23 01:20:30');
INSERT INTO `dorm_build` VALUES ('fac26c3b29b7474989e9cadd1f10904d', 'tenglong4', '腾龙四栋', '1', '腾龙四栋男生宿舍', '0', '张三', '2018-02-23 14:15:12');
INSERT INTO `dorm_build` VALUES ('ff46e784e5184089b346a10d78c93bed', 'yicheng_11', '议城十一栋', '0', '议城十一栋女生宿舍', '1', '张三', '2018-02-23 15:11:14');

-- ----------------------------
-- Table structure for dorm_employee
-- ----------------------------
DROP TABLE IF EXISTS `dorm_employee`;
CREATE TABLE `dorm_employee` (
  `id` varchar(32) NOT NULL,
  `empno` varchar(30) NOT NULL,
  `empname` varchar(30) NOT NULL,
  `empsex` int(1) default NULL,
  `birthday` date default NULL,
  `phone` char(11) NOT NULL,
  `build_id` varchar(32) NOT NULL,
  `job` varchar(50) NOT NULL,
  `create_date` datetime NOT NULL,
  `photo` varchar(50) default NULL,
  `creater` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dorm_employee
-- ----------------------------
INSERT INTO `dorm_employee` VALUES ('1eb8231037bd485bb35982a7150fb904', 'employee05', '高圆圆', '0', '1983-03-10', '15744563210', '12d6ac918a914a109a89fbc76ca08d20', '查宿员', '2018-02-23 19:48:03', '3220d17a18ba4cd7bf9c1e88501b8a09.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('20c817524630433aa0a08839b044d68c', 'employee01', '花木兰', '0', '1980-03-04', '15288745632', '0f565a8ed80743978799aaed371583f0', '咨询员', '2018-02-23 19:43:46', '0e6b48542df7475cbd287c0de6ee0f74.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('2685819661cb45989db559592a863091', 'employee04', '杨颖', '0', '1988-06-14', '15788963210', '1f52c18a19354fdcb2ca7d7f74e73f74', '充卡员', '2018-02-23 19:47:33', 'a7499fafdf4746bba67145fbac2c5b42.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('3903b64df1bc446191b9f5d644e709f1', 'employee11', '张三丰', '1', '1977-10-18', '18411254698', '3cc1a3a94cc745e2b1e172c8af1f260b', '接待员', '2018-02-23 19:53:23', 'fdfaa36ed93949d2ae0fa366066e7705.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('502484e5d07e460cae262ca4f8d326a4', 'employee12', '赵丽颖', '0', '1985-03-19', '18744563213', '12d6ac918a914a109a89fbc76ca08d20', '咨询员', '2018-02-23 19:54:15', '408140685b2b4ac18299392b50fde6c1.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('65b3d87d7a34474099b663292f396639', 'employee06', '刘亦菲', '0', '1989-06-13', '15744213014', '12d6ac918a914a109a89fbc76ca08d20', '清洁员', '2018-02-23 19:48:47', '3489150217c7427f877c3a57106ccd1e.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('715b77991df1464fbe312bd54ecdebc6', 'employee03', '杨幂', '0', '1986-08-04', '15877456321', '12d6ac918a914a109a89fbc76ca08d20', '咨询员', '2018-02-23 19:46:43', 'c02e5f55ec5b449b95c20e5fb2383f3b.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('726b329c5f724591bef9f3723e221683', 'employee08', '李易峰', '1', '1984-10-22', '15744563102', '2d7b81f51ba14ac4a23a028f080a5679', '接待员', '2018-02-23 19:50:09', '6cdacc70eb664113a0628ade5e8a1e70.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('91711131547f40e7af7e9f2bcc1ae5ee', 'employee13', '谢霆锋', '1', '1980-06-11', '15288741231', '255cb55f854e4a8ba23d7fe9021b50a1', '接待员', '2018-05-04 21:57:51', 'dcbdcd05d5cb472899d24c6a1b9e55a4.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('b083f0f38a7c42348373ccde35d4fd96', 'employee02', '刘诗诗', '0', '1986-03-04', '15877412301', '12d6ac918a914a109a89fbc76ca08d20', '接待员', '2018-02-23 19:45:47', '5d57990172af403784721cc5b381998c.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('bdfa0a51ceae480dbdb291b63aaf186a', 'employee09', '黄晓明', '1', '1984-08-13', '18744521358', '2d7b81f51ba14ac4a23a028f080a5679', '咨询员', '2018-02-23 19:50:49', 'ab97e6e85e474dedb81c27e51967130a.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('d3fd3ad3b50a43a08a65fb66bba5545f', 'employee10', '李刚', '1', '1975-05-06', '17544132574', '37b9f2fb4b4e4dd68872b06a018f5e72', '咨询员', '2018-02-23 19:51:52', 'f841f8dafbcf4701b375d43b9cc35d79.jpg', '张三');
INSERT INTO `dorm_employee` VALUES ('edd3d64b574f4fa0a05a8eed9e634e14', 'employee07', '林志玲', '0', '1980-07-15', '15788963241', '1f52c18a19354fdcb2ca7d7f74e73f74', '咨询员', '2018-02-23 19:49:21', '0761be0ce98e4307a142fd24c4f08a63.jpg', '张三');

-- ----------------------------
-- Table structure for dorm_vistor
-- ----------------------------
DROP TABLE IF EXISTS `dorm_vistor`;
CREATE TABLE `dorm_vistor` (
  `id` varchar(32) NOT NULL,
  `vistor_name` varchar(30) NOT NULL,
  `vistor_phone` char(11) NOT NULL,
  `vistor_idcard` char(18) NOT NULL,
  `start_time` datetime NOT NULL,
  `end_time` datetime default NULL,
  `visted_name` varchar(30) default NULL,
  `creater` varchar(30) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dorm_vistor
-- ----------------------------
INSERT INTO `dorm_vistor` VALUES ('cde6c8754a9146dcb730960e7fcffede', '张晓', '15477894120', '154874545754541423', '2018-02-26 22:11:04', '2018-02-26 22:11:10', '露娜', '王五', '2018-02-26 22:11:04');
INSERT INTO `dorm_vistor` VALUES ('d1231f61ac834c608ed5d01f281a9845', '刘德华', '17455745621', '15478454575564', '2018-03-28 22:29:54', '2018-03-28 22:47:47', '王华', '张三', '2018-03-28 22:29:54');
INSERT INTO `dorm_vistor` VALUES ('dc4de096dccf49d7ae760d88b2875965', '渣渣辉', '15277412341', '15744123210', '2018-04-30 22:12:50', '2018-04-30 22:12:58', '张家辉', '张三', '2018-04-30 22:12:50');
INSERT INTO `dorm_vistor` VALUES ('f9de0fcb7cdc48f4bef8b8f973f4865c', '王三', '15877464514', '157469874563214754', '2018-02-25 16:46:46', '2018-02-25 16:46:50', '程咬金', '张三', '2018-02-25 16:46:46');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(32) NOT NULL,
  `stuno` varchar(50) NOT NULL,
  `stuname` varchar(50) NOT NULL,
  `stusex` int(1) NOT NULL,
  `birthday` date default NULL,
  `email` varchar(50) NOT NULL,
  `phone` char(11) NOT NULL,
  `create_date` datetime NOT NULL,
  `dorm_id` varchar(32) NOT NULL,
  `class_name` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `creater` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('0933ac00e79c4f7aba9483346baeefd2', '201440410111', '程咬金', '1', '1995-05-15', 'chengyaojin@qq.com', '15744123654', '2018-02-23 19:58:38', 'd437571b07ff4eb78d46420250cf959b', '计科二班', 'fefb8e8abf65682b69a2e35378b06651', '张三');
INSERT INTO `student` VALUES ('26ee657f1a3c48049c3e088cdbe14d4c', '201440410103', '李潇潇', '0', '2011-01-04', '22@3.cn', '15244712311', '2018-05-04 23:02:12', '0551603c3ae04ca5aaf65d8e803e63a6', '网工二班', '6936c970a312879e3c3c30ceea188d53', '张三');
INSERT INTO `student` VALUES ('42de2be9d19c4484b4be1a470e41d2f4', '201440410101', '露娜1', '0', '1994-05-10', 'luna@qq.com', '15977456321', '2018-02-25 16:53:00', 'ed084433b1d44db9b92f80a3631eee9f', '网工一班', '89bdc1245e5b80af561536bc726b147e', '张三');
INSERT INTO `student` VALUES ('79a2a16f1fbd4f4488dd6312cc2c07f1', '201440410114', '花木兰', '0', '1995-07-11', 'huamulan@qq.com', '15877463241', '2018-02-25 16:34:19', '6582bd5a20f54ca8b487eea3ef85a0f5', '艺术一班', '78f5b6ecf01438aad481cdcb74ab4eb1', '张三');
INSERT INTO `student` VALUES ('7c4c57228e8b496db30936b17e908858', '201440410113', '亚瑟', '1', '2005-02-13', 'yase@163.com', '15244136521', '2018-02-23 20:01:27', 'd437571b07ff4eb78d46420250cf959b', '计科一班', '115e90d524ba711f54c9ce44d0999bcf', '张三');
INSERT INTO `student` VALUES ('80d70c24643d4b5798087a0f9f07ad19', '201440410110', '兰陵王', '1', '1995-11-02', 'lanling@qq.com', '18271670322', '2018-02-23 19:57:00', 'd437571b07ff4eb78d46420250cf959b', '计科一班', '14d141152d6ccf9909302bd24f5e8511', '张三');
INSERT INTO `student` VALUES ('ee39f398a4fc47d782e2a0310603f7c3', '201440410112', '赵云', '1', '1995-02-21', 'zhaoyun@qq.com', '17855412364', '2018-02-23 19:59:26', 'd437571b07ff4eb78d46420250cf959b', '计科三班', '221cbcd221e2a12ba91d994f24f8c1a9', '张三');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `username` char(20) NOT NULL,
  `password` char(50) NOT NULL,
  `name` varchar(30) NOT NULL,
  `birthday` date default NULL,
  `sex` int(1) default NULL,
  `phone` char(11) NOT NULL,
  `flag` int(1) NOT NULL,
  `creater` varchar(30) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', 'e10adc3949ba59abbe56e057f20f883e', '张三', '2018-02-13', '1', '15894145210', '0', '张三', '2018-02-08 20:00:00');
INSERT INTO `user` VALUES ('26140ac4fd064459bb1f4e1c2f84edbd', 'emp_wangwu', '54251737176ec7a2b85012382bf46c0a', '王五', '1984-02-14', '1', '15277415213', '1', '张三', '2018-03-28 22:58:07');
INSERT INTO `user` VALUES ('96cb79700e6940ca99c8ffb47c9491d4', 'emp_lisi', '6fe344a7790cf39dda059d98ea94d5d6', '李四', '1980-02-11', '1', '15288741232', '1', '张三', '2018-03-10 14:14:48');
INSERT INTO `user` VALUES ('ab674e5cc62646fab4f8c857f6d572cc', 'emp_zhaoliu', '64445723c9a07bec637abcc7237b172b', '赵六', '1978-08-08', '1', '15277412311', '1', '张三', '2018-05-04 23:19:17');
