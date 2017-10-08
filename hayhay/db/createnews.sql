DROP TABLE IF EXISTS `hayhay`.`news`;
CREATE TABLE  `hayhay`.`news` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `shotdesc` varchar(10000) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `url` varchar(500) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `fromwebsite` varchar(100) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `imageurl` varchar(300) CHARACTER SET utf8 COLLATE utf8_unicode_ci NOT NULL,
  `viewcount` int(10) unsigned NOT NULL DEFAULT '0',
  `type` varchar(100) NOT NULL,
  `ishotnews` tinyint(1) NOT NULL,
  `newsorder` varchar(45) NOT NULL DEFAULT 'M',
  `collectedtime` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=392 DEFAULT CHARSET=latin1;