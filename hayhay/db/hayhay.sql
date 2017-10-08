-- MySQL dump 10.13  Distrib 5.6.11, for Win64 (x86_64)
--
-- Host: localhost    Database: hayhay
-- ------------------------------------------------------
-- Server version	5.6.11

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `subject` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `content` mediumtext COLLATE utf8_unicode_ci NOT NULL,
  `image` varchar(500) COLLATE utf8_unicode_ci NOT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdby` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `comment_ids` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT ' ',
  `like_uids` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT ' ',
  `share_uids` varchar(45) COLLATE utf8_unicode_ci NOT NULL DEFAULT ' ',
  `type` int(10) unsigned NOT NULL,
  `like_count` int(10) unsigned NOT NULL DEFAULT '0',
  `comment_count` int(10) unsigned NOT NULL DEFAULT '0',
  `share_count` int(10) unsigned NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (5,'Cuoi cuoi','hello he la','/images/399513122424943laugh.jpg','2015-02-02 06:09:31','kienjudo','1','1',' ',1,0,0,0),(6,'Doc dao','hello he la','/images/thiennhien.jpg','2015-02-02 06:09:32','kienjudo','1','1',' ',2,0,0,0),(21,'Chỉ trích là vô ích','<p>Chỉ tr&iacute;ch l&agrave; v&ocirc; &iacute;ch (n&oacute; l&agrave;m cho kẻ bị chỉ tr&iacute;ch phải chống cự lại v&agrave; tự b&agrave;o chữa) m&agrave; c&ograve;n nguy hiểm, o&aacute;n th&ugrave;.&nbsp;</p>\r\n<p>Cho n&ecirc;n từ nay, mỗi lần muốn chỉ tr&iacute;ch ai, ta n&ecirc;n nhớ,&nbsp;lời chỉ tr&iacute;ch ta thốt ra, cũng như con chim bồ c&acirc;u, bao giờ n&oacute; cũng trở về chỗ cũ. Kẻ bị ta chỉ tr&iacute;ch sẽ t&igrave;m thấy hết l&yacute; luận để tự h&agrave;o chữa v&agrave; trở lại buộc tội ta.\'&nbsp;</p>','/images/999582549598775ung-xu-truoc-chi-trich.png','2015-02-03 16:25:09','kientv',' ',' ',' ',0,0,0,0),(22,'Ai cũng muốn được người ta khen mình.','<p>Ch&uacute;ng ta đều kh&aacute;t những lời khen ch&acirc;n th&agrave;nh m&agrave; than &ocirc;i! &iacute;t khi người ta cho ta c&aacute;i đ&oacute;.</p>\r\n<p>Những kẻ n&agrave;o đ&atilde; học được c&aacute;i b&iacute; quyết l&agrave;m thỏa m&atilde;n l&ograve;ng đ&oacute;i kh&aacute;t lời khen đ&oacute;, n&oacute; tuy k&iacute;n đ&aacute;o m&agrave; gi&agrave;y v&ograve; người ta, đ&acirc;m rễ trong l&ograve;ng người ta, th&igrave; kẻ ấy \"nắm được mọi người trong tay m&igrave;nh\" v&agrave; được mọi người t&ocirc;n trọng, s&ugrave;ng b&aacute;i, nghe lời, \"khi chết đi, kẻ đ&agrave;o huyệt ch&ocirc;n người đ&oacute; cũng phải kh&oacute;c người đ&oacute; nữa\".</p>','/images/1000197493223291khen.jpeg','2015-02-02 17:00:00','kientv',' ',' ',' ',0,0,0,0),(23,'Hãy khêu gợi ở người cái ý tự muốn làm công việc mà chính ta đề nghị với họ','<p>Tại sao cứ lu&ocirc;n lu&ocirc;n n&oacute;i tới c&aacute;i m&agrave; ch&uacute;ng ta muốn? Thực l&agrave; v&ocirc; &iacute;ch, ng&acirc;y thơ v&agrave; v&ocirc; l&yacute;. Đ&atilde; đ&agrave;nh, c&aacute;i g&igrave; ta th&iacute;ch th&igrave; ta để &yacute; tới lu&ocirc;n, nhưng chỉ c&oacute; một m&igrave;nh ta để &yacute; tới n&oacute;. V&igrave; những người kh&aacute;c họ cũng chỉ nghĩ tới c&aacute;i họ th&iacute;ch th&ocirc;i, kh&ocirc;ng cần biết ta th&iacute;ch c&aacute;i g&igrave;.</p>\r\n<p>Cho n&ecirc;n chỉ c&oacute; mỗi c&aacute;ch dẫn dụ người kh&aacute;c theo m&igrave;nh l&agrave; lựa c&aacute;ch n&oacute;i sao lời y&ecirc;u cầu của m&igrave;nh hạp với sở th&iacute;ch của họ v&agrave; chỉ cho họ c&aacute;ch đạt được sở th&iacute;ch đ&oacute;.</p>','/images/1000443033808623vp16.jpg','2015-02-03 16:53:01','kientv',' ','1',' ',0,1,0,0),(24,'Để cho tới đâu cũng được tiếp đón niềm nở','<p>Thiệt t&igrave;nh bạn cũng chẳng cần đọc s&aacute;ch n&agrave;y mới biết c&aacute;ch đắc nh&acirc;n t&acirc;m. Bạn chỉ cần &aacute;p dụng những phương ph&aacute;p thu phục cảm t&igrave;nh của một con vật m&agrave; cả ho&agrave;n cầu kh&ocirc;ng ai kh&ocirc;ng thương mến. L&aacute;t nữa ra đường bạn sẽ gặp n&oacute;. Khi c&ograve;n xa bạn chừng mười bước, n&oacute; đ&atilde; bắt đầu ve vẩy đu&ocirc;i rồi. Nếu bạn ngừng lại m&agrave; vuốt ve n&oacute; th&igrave; n&oacute; chồm l&ecirc;n v&agrave; tỏ ra trăm vẻ y&ecirc;u đương v&agrave; bạn c&oacute; thể biết chắc chắn rằng trong sự nồng n&agrave;n đ&oacute; kh&ocirc;ng c&oacute; một mảy may vụ lợi v&igrave; n&oacute; chẳng cần bạn mua gi&uacute;p một v&agrave;i m&oacute;n h&agrave;ng ế, m&agrave; cũng chẳng ham g&igrave; được kết duy&ecirc;n c&ugrave;ng bạn. C&oacute; bao giờ bạn ngừng lại một ph&uacute;t m&agrave; suy nghĩ rằng tr&ecirc;n vũ trụ n&agrave;y, chỉ c&oacute; con ch&oacute; l&agrave; kh&ocirc;ng cần l&agrave;m việc m&agrave; cũng sống một c&aacute;ch ung dung kh&ocirc;ng? Ta nu&ocirc;i g&agrave;, l&agrave; v&igrave; g&agrave; cho ta trứng: ta nu&ocirc;i b&ograve;, l&agrave; v&igrave; b&ograve; cho ta sữa; m&agrave; ta nu&ocirc;i con ho&agrave;ng yến cũng v&igrave; tiếng h&oacute;t của n&oacute;. Nhưng ta nu&ocirc;i ch&oacute; chỉ v&igrave; c&aacute;i l&yacute; độc nhất l&agrave; n&oacute; cho ta c&aacute;i &ecirc;m đềm của t&igrave;nh thương</p>','/images/1088533073767702hairstyle8.jpg','2015-02-04 17:00:00','kientv',' ',' ',' ',0,0,0,0),(25,'Một cách dễ dàng để gây mỹ cảm lúc sơ kiến','<p>C&oacute; nhiều b&agrave; muốn g&acirc;y mỹ cảm, ti&ecirc;u cả một gia t&agrave;i để đắp v&agrave;o th&acirc;n những nhung c&ugrave;ng v&oacute;c, đeo v&agrave;o m&igrave;nh những v&agrave;ng c&ugrave;ng ngọc, m&agrave; hỡi ơi, qu&ecirc;n hẳn c&aacute;i bộ mặt của m&igrave;nh đi, bắt n&oacute; mang những n&eacute;t chua ngoa v&agrave; &iacute;ch kỷ. Họ qu&ecirc;n rằng đối với đ&agrave;n &ocirc;ng, n&eacute;t mặt nụ cười quan trọng hơn tơ lụa kho&aacute;c l&ecirc;n m&igrave;nh. (Nh&acirc;n tiện, xin nhắc bạn, v&iacute; như b&agrave; nh&agrave; đ&ograve;i may một c&aacute;i &aacute;o nhung, th&igrave; xin chớ trả lời b&agrave; bằng c&acirc;u đ&oacute; nh&eacute;!). Charles Schwab m&agrave; tr&ecirc;n kia t&ocirc;i đ&atilde; kể chuyện, n&oacute;i rằng nụ cười của &ocirc;ng ta đ&aacute;ng gi&aacute; một triệu đồng. Số đ&oacute; c&ograve;n dưới sự thực, v&igrave; tất cả sự th&agrave;nh c&ocirc;ng lạ l&ugrave;ng của &ocirc;ng đều nhờ t&acirc;m t&iacute;nh &ocirc;ng, duy&ecirc;n k&iacute;n của &ocirc;ng. M&agrave; ch&iacute;nh nụ cười quyến rũ của &ocirc;ng lại l&agrave; khả năng khả &aacute;i nhất.</p>','/images/1088578094960953hairstyle7.jpg','2015-02-04 17:00:00','kientv',' ',' ',' ',0,0,0,0),(26,'Không theo quy tắc sau này tức là tự rước lấy thất bại ','<p>Năm 1898, Joe Farley chết một c&aacute;ch bất ngờ, để lại vợ g&oacute;a v&agrave; ba con c&ocirc;i với v&agrave;i trăm đồng bạc vốn. Đứa lớn nhất t&ecirc;n Jim, mười tuổi, phải gi&uacute;p việc trong một l&ograve; gạch: đẩy xe c&aacute;t, đổ c&aacute;t v&agrave;o khu&ocirc;n, phơi gạch. Kh&ocirc;ng c&oacute; th&igrave; giờ học, nhưng c&oacute; một thi&ecirc;n t&agrave;i trời cho ri&ecirc;ng d&acirc;n &aacute;i Nhĩ Lan l&agrave; bẩm sinh đ&atilde; biết nghệ thuật l&agrave;m cho người kh&aacute;c thương m&igrave;nh. Lớn l&ecirc;n, &ocirc;ng l&agrave;m ch&iacute;nh trị, tập nhớ t&ecirc;n họ v&agrave; vẻ mặt của người kh&aacute;c, m&agrave; lần lần tr&iacute; nhớ đ&oacute; trở n&ecirc;n kỳ diệu. Kh&ocirc;ng hề t&ograve;ng học một trường đại học n&agrave;o hết, m&agrave; chưa đầy bốn mươi s&aacute;u tuổi, c&oacute; tới bốn trường đại học cấp bằng danh dự cho &ocirc;ng, lại l&agrave;m Hội trưởng ủy ban d&acirc;n chủ quốc gia, v&agrave; Tổng gi&aacute;m đốc sở Bưu điện. Một lần được &ocirc;ng tiếp, t&ocirc;i hỏi &ocirc;ng b&iacute; quyết của sự th&agrave;nh c&ocirc;ng đ&oacute;. &Ocirc;ng đ&aacute;p: \"Nai lưng ra m&agrave; l&agrave;m việc\". T&ocirc;i c&atilde;i: \"Đừng n&oacute;i chơi m&agrave;!\".</p>','/images/1088709211486680hairstyle16.jpg','2015-02-04 17:00:00','kientv',' ',' ',' ',0,0,0,0),(27,'Bạn muốn thành một người nói chuyện có duyên không? Dễ lắm ','<p>Mới rồi, sau một tiệc rượu, chủ nh&agrave; mời t&ocirc;i đ&aacute;nh b&agrave;i. T&ocirc;i kh&ocirc;ng biết chơi m&agrave; b&agrave; ngồi b&ecirc;n cạnh t&ocirc;i cũng vậy. Ch&uacute;ng t&ocirc;i n&oacute;i chuyện với nhau. B&agrave; ấy biết rằng hồi trước t&ocirc;i c&oacute; việc phải ở b&ecirc;n ch&acirc;u &Acirc;u năm năm. B&agrave; n&oacute;i: \"&Ocirc;ng Carnegie, t&ocirc;i ước ao được &ocirc;ng tả cho t&ocirc;i nghe những thắng cảnh b&ecirc;n đ&oacute;\". Khi ch&uacute;ng t&ocirc;i lại ngồi tr&ecirc;n một chiếc ghế d&agrave;i, b&agrave; ta cho hay rằng mới ở ch&acirc;u Phi về với chồng b&agrave;. T&ocirc;i n&oacute;i: \"Ch&acirc;u Phi c&oacute; nhiều c&aacute;i th&uacute; lắm. T&ocirc;i vẫn mong mỏi từ l&acirc;u được dịp qua đ&oacute;, m&agrave; chỉ đi một lần tới Alger rồi trở về. T&ocirc;i ở Alger được đ&uacute;ng hai mươi bốn giờ đồng hồ... ở b&ecirc;n đ&oacute;, &ocirc;ng b&agrave; c&oacute; săn th&uacute; rừng kh&ocirc;ng?... C&oacute;? &Ocirc;ng b&agrave; thiệt gặp may t&ocirc;i muốn được như &ocirc;ng b&agrave; lắm. Xin b&agrave; kể cho t&ocirc;i nghe\". B&agrave; ta diễn thuyết trong bốn mươi lăm ph&uacute;t, kh&ocirc;ng nhớ g&igrave; tới những thắng cảnh b&ecirc;n &Acirc;u nữa. B&agrave; chỉ muốn gặp được người chăm ch&uacute; nghe b&agrave;, để b&agrave; c&oacute; c&aacute;i vui được dịp n&oacute;i tới b&agrave; v&agrave; những kỷ niệm của b&agrave;. B&agrave; đ&oacute; kỳ dị kh&ocirc;ng? Thưa kh&ocirc;ng! V&ocirc; số người cũng như b&agrave;. Tất cả ch&uacute;ng ta đều muốn diễn thuyết khi c&oacute; người chăm ch&uacute; nghe ta. Trong một bữa cơm tối, nh&agrave; một người bạn l&agrave;m nghề xuất bản, t&ocirc;i được gặp một nh&agrave; thực vật học c&oacute; danh. Đ&oacute; l&agrave; lần đầu ti&ecirc;n trong đời t&ocirc;i được gặp một nh&agrave; thực vật học v&agrave; &ocirc;ng ấy n&oacute;i chuyện nghe muốn m&ecirc;. T&ocirc;i x&iacute;ch lại gần &ocirc;ng, nghe &ocirc;ng diễn giải về c&aacute;c lo&agrave;i c&acirc;y cỏ v&agrave; những chi tiết lạ l&ugrave;ng về một c&acirc;y rất tầm thường l&agrave; khoai t&acirc;y... &Ocirc;ng khuy&ecirc;n t&ocirc;i nhiều điều rất qu&yacute; về c&aacute;ch giữ g&igrave;n khu vườn nhỏ của t&ocirc;i. Trong bữa tiệc đ&oacute;, c&oacute; mười hai &ocirc;ng kh&aacute;ch nữa m&agrave; t&ocirc;i như kh&ocirc;ng biết c&oacute; ai hết: t&ocirc;i phạm hết thảy những điều m&agrave; thường thức về x&atilde; giao để nghe trong mấy giờ đồng hồ nh&agrave; thực vật học của t&ocirc;i. Tới nửa đ&ecirc;m t&ocirc;i xin ph&eacute;p ra về. Sau n&agrave;y c&oacute; người cho hay rằng, t&ocirc;i vừa ra khỏi ph&ograve;ng, nh&agrave; th&ocirc;ng th&aacute;i đ&oacute; quay lại n&oacute;i với &ocirc;ng chủ nh&agrave;, khen t&ocirc;i thế n&agrave;y, thế kh&aacute;c v&agrave; cho rằng c&acirc;u chuyện t&ocirc;i rất hứng th&uacute; v&agrave; t&ocirc;i l&agrave; một người ăn n&oacute;i rất c&oacute; duy&ecirc;n. T&ocirc;i m&agrave; n&oacute;i chuyện c&oacute; duy&ecirc;n ư? Nhưng h&ocirc;m đ&oacute; t&ocirc;i c&oacute; thốt ra nửa lời n&agrave;o đ&acirc;u? Giả thử t&ocirc;i c&oacute; n&oacute;i, th&igrave; c&acirc;u chuyện đ&atilde; quay ra một vấn đề kh&aacute;c rồi; v&igrave; về khoa thảo mộc học, t&ocirc;i ho&agrave;n to&agrave;n kh&ocirc;ng biết ch&uacute;t chi hết. T&ocirc;i chỉ m&ecirc; mẩn nghe th&ocirc;i. V&igrave; những điều &ocirc;ng giảng giải kh&iacute;ch th&iacute;ch t&ocirc;i nhiều lắm. &Ocirc;ng ấy thấy r&otilde; như vậy v&agrave; điều đ&oacute; l&agrave;m cho &ocirc;ng vui l&agrave; lẽ tự nhi&ecirc;n. Chăm ch&uacute; nghe một người kh&aacute;c, kh&aacute;c g&igrave; nhiệt liệt khen họ. Một văn sĩ n&oacute;i: \"Say m&ecirc; nghe lời n&oacute;i của một người, tức l&agrave; t&ocirc;n k&iacute;nh người đ&oacute;, m&agrave; rất &iacute;t người kh&ocirc;ng cảm động trước sự t&ocirc;n k&iacute;nh đ&oacute;\". H&ocirc;m ấy t&ocirc;i kh&ocirc;ng những chỉ say m&ecirc; nghe m&agrave; th&ocirc;i, c&ograve;n tỏ với &ocirc;ng ấy một tấm l&ograve;ng qu&yacute; mến v&agrave; ngưỡng mộ ch&acirc;n th&agrave;nh nữa. T&ocirc;i n&oacute;i với &ocirc;ng rằng &ocirc;ng đ&atilde; chỉ bảo với t&ocirc;i rất nhiều, v&agrave; t&ocirc;i nghe n&oacute;i m&agrave; m&ecirc;. Đ&oacute; l&agrave; sự thực. T&ocirc;i lại n&oacute;i rằng: nếu được lang thang trong một c&aacute;nh đồng với &ocirc;ng th&igrave; th&uacute; v&ocirc; c&ugrave;ng. C&aacute;i đ&oacute; cũng l&agrave; sự thực nữa. T&ocirc;i ngỏ &yacute; muốn được t&aacute;i ngộ &ocirc;ng v&agrave; thiệt t&igrave;nh t&ocirc;i b&acirc;y giờ rất muốn được gặp &ocirc;ng lần nữa. Đ&oacute;, chỉ v&igrave; vậy m&agrave; &ocirc;ng khen t&ocirc;i l&agrave; n&oacute;i chuyện kh&eacute;o, sự thiệt t&ocirc;i chỉ l&agrave; một th&iacute;nh giả kiểu mẫu v&agrave; biết cổ vũ &ocirc;ng n&oacute;i th&ocirc;i. L&agrave;m sao cho kh&aacute;ch h&agrave;ng c&oacute; thiện cảm với ta, vui vẻ nghe ta, tin ta v&agrave; theo &yacute; ta? Theo gi&aacute;o sư C.W.Eliot th&igrave; kh&ocirc;ng kh&oacute; chi hết. Trước hết ta phải đặc biệt chăm ch&uacute; nghe họ. Kh&ocirc;ng c&oacute; chi l&agrave;m đẹp l&ograve;ng họ bằng. Điều đ&oacute; dễ hiểu qu&aacute; m&agrave;! Kh&ocirc;ng cần phải theo học bốn năm tại Harvard để t&igrave;m thấy ch&acirc;n l&yacute; đ&oacute;. Vậy m&agrave; t&ocirc;i thấy v&agrave; c&aacute;c bạn cũng thấy c&oacute; những nh&agrave; bu&ocirc;n kh&ocirc;ng ngần ngại mướn những cửa h&agrave;ng xa hoa, ch&uacute; trọng về sự bu&ocirc;n h&agrave;ng với c&aacute;i gi&aacute; hời nhất để c&oacute; thể b&aacute;n rẻ, m&agrave; vẫn lời, cửa h&agrave;ng họ trưng b&agrave;y lộng lẫy, họ ti&ecirc;u h&agrave;ng trăm đồng v&agrave;o c&ocirc;ng cuộc quảng c&aacute;o, m&agrave; rồi rốt cuộc, mướn những người l&agrave;m c&ocirc;ng kh&ocirc;ng biết nghệ thuật \"nghe\", ngắt lời kh&aacute;ch h&agrave;ng, c&atilde;i lại họ, l&agrave;m mất l&ograve;ng họ, như vậy c&oacute; kh&aacute;c g&igrave; đuổi họ ra khỏi cửa h&agrave;ng kh&ocirc;ng? Xin c&aacute;c bạn nghe chuyện &ocirc;ng J.C. Wooton, một người học tr&ograve; của t&ocirc;i. &Ocirc;ng ấy mua một bộ đồ tại một tiệm lớn nọ. Về nh&agrave; bận &iacute;t bữa &ocirc;ng bực m&igrave;nh v&igrave; thấy m&agrave;u &aacute;o th&ocirc;i ra v&agrave; l&agrave;m đen cổ &aacute;o sơ-mi. &Ocirc;ng đem bộ đồ đ&oacute; lại tiệm, ph&agrave;n n&agrave;n với người l&agrave;m c&ocirc;ng đ&atilde; b&aacute;n bộ đồ đ&oacute; cho &ocirc;ng. Cũng kh&ocirc;ng phải để ph&agrave;n n&agrave;n nữa, m&agrave; để giảng giải cho người b&aacute;n h&agrave;ng nghe, nhưng người n&agrave;y chưa nghe, đ&atilde; ngắt ngay lời: \"Ch&uacute;ng t&ocirc;i đ&atilde; b&aacute;n cả ng&agrave;n bộ đồ thứ đ&oacute;, m&agrave; chưa hề c&oacute; ai k&ecirc;u n&agrave;i chi hết\". Lời th&igrave; như vậy, nhưng giọng c&ograve;n tệ hơn nữa. Giọng hung hăng như muốn bảo: \"Ch&uacute; n&oacute;i dối, ch&uacute; ơi! T&ocirc;i đi guốc trong bụng ch&uacute; rồi!\".</p>','/images/1088949914664169hairstyle14.jpg','2015-02-04 17:00:00','kientv',' ',' ',' ',0,0,0,0),(28,'Làm sao để gây thiện cảm ','<p>Những ai đ&atilde; gặp Tổng thống Th&eacute;odore Roosevelt đều ngạc nhi&ecirc;n về sự biết nhiều,</p>','/images/1089282766561011hairstyle10.jpg','2015-02-04 17:00:00','kientv',' ',' ',' ',0,0,0,0);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_comment`
--

DROP TABLE IF EXISTS `article_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article_comment` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `article_id` int(10) unsigned NOT NULL,
  `content` varchar(2000) COLLATE utf8_unicode_ci NOT NULL,
  `createddate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `createdby` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `replycomment_ids` varchar(1000) COLLATE utf8_unicode_ci NOT NULL DEFAULT ' ',
  `like_uids` varchar(100) COLLATE utf8_unicode_ci NOT NULL DEFAULT ' ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_comment`
--

LOCK TABLES `article_comment` WRITE;
/*!40000 ALTER TABLE `article_comment` DISABLE KEYS */;
INSERT INTO `article_comment` VALUES (1,2,'my comment','2015-02-02 06:04:15','kienjudo',' ',' '),(2,3,'my comment','2015-02-02 06:08:49','kienjudo',' ',' '),(3,3,'my comment 1','2015-02-02 06:08:49','kienjudo',' ',' '),(4,3,'my comment 2','2015-02-02 06:08:49','kienjudo',' ',' '),(5,3,'my comment 3','2015-02-02 06:08:50','kienjudo',' ',' '),(6,4,'my comment','2015-02-02 06:09:31','kienjudo',' ',' '),(7,4,'my comment 1','2015-02-02 06:09:31','kienjudo',' ',' '),(8,4,'my comment 2','2015-02-02 06:09:31','kienjudo',' ',' '),(9,4,'my comment 3','2015-02-02 06:09:31','kienjudo',' ',' '),(10,5,'my comment','2015-02-02 06:09:31','kienjudo',' ',' '),(11,5,'my comment 1','2015-02-02 06:09:31','kienjudo',' ',' '),(12,6,'my comment11','2015-02-02 06:09:32','kienjudo',' ',' '),(13,6,'my comment 12','2015-02-02 06:09:32','kienjudo',' ',' '),(14,7,'my comment13','2015-02-02 06:09:32','kienjudo',' ',' '),(15,7,'my comment 14','2015-02-02 06:09:32','kienjudo',' ',' '),(16,8,'my comment5','2015-02-02 06:09:32','kienjudo',' ',' ');
/*!40000 ALTER TABLE `article_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyvalue`
--

DROP TABLE IF EXISTS `keyvalue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyvalue` (
  `key_` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `value_` varchar(5000) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`key_`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyvalue`
--

LOCK TABLES `keyvalue` WRITE;
/*!40000 ALTER TABLE `keyvalue` DISABLE KEYS */;
INSERT INTO `keyvalue` VALUES ('CUOI','5'),('DOCDAO','6'),('HAY','23,24,25'),('lastest_0','28'),('lastest_1','5'),('lastest_2','6');
/*!40000 ALTER TABLE `keyvalue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tracking`
--

DROP TABLE IF EXISTS `tracking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tracking` (
  `id` varchar(200) NOT NULL,
  `count` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tracking`
--

LOCK TABLES `tracking` WRITE;
/*!40000 ALTER TABLE `tracking` DISABLE KEYS */;
INSERT INTO `tracking` VALUES ('2015-02-03 01about',1),('2015-02-03 01cuoi',2),('2015-02-03 01docdao',2),('2015-02-03 01hay',3),('2015-02-03 01home',2),('2015-02-03 01_article_1',3),('2015-02-03 11cuoi',1),('2015-02-03 11docdao',1),('2015-02-03 11hay',1),('2015-02-03 11home',2),('2015-02-03 13home',3),('2015-02-03 14cuoi',1),('2015-02-03 14hay',1),('2015-02-03 14home',3),('2015-02-03 16about',2),('2015-02-03 16cuoi',2),('2015-02-03 16docdao',3),('2015-02-03 16hay',2),('2015-02-03 16home',4),('2015-02-03 17about',2),('2015-02-03 23cuoi',14),('2015-02-03 23docdao',14),('2015-02-03 23hay',17),('2015-02-03 23home',20),('2015-02-03 23_article_1',1),('2015-02-03 23_article_2',1),('2015-02-03 23_article_21',4),('2015-02-03 23_article_22',2),('2015-02-03 23_article_23',3),('2015-02-03 23_article_5',1),('2015-02-04 10home',8),('2015-02-04 11cuoi',1),('2015-02-04 11docdao',1),('2015-02-04 11hay',1),('2015-02-04 11home',2),('2015-02-04 13cuoi',1),('2015-02-04 13docdao',1),('2015-02-04 13hay',1),('2015-02-04 13home',4),('2015-02-04 14cuoi',3),('2015-02-04 14docdao',3),('2015-02-04 14hay',2),('2015-02-04 14home',4),('2015-02-04 14_article_21',1),('2015-02-04 14_article_22',1),('2015-02-04 14_article_23',1),('2015-02-04 23cuoi',1),('2015-02-04 23docdao',1),('2015-02-04 23hay',1),('2015-02-04 23home',3),('2015-02-04 23_article_23',1),('2015-02-05 00cuoi',3),('2015-02-05 00docdao',2),('2015-02-05 00hay',5),('2015-02-05 00home',7),('2015-02-05 00_article_21',1),('2015-02-05 00_article_22',3),('2015-02-05 00_article_23',2),('2015-02-05 00_article_24',1),('2015-02-05 00_article_25',2),('2015-02-05 00_article_27',1),('2015-02-05 10cuoi',1),('2015-02-05 10docdao',1),('2015-02-05 10hay',3),('2015-02-05 10home',1),('2015-02-05 23home',5),('2015-02-05 23_article_23',5),('2015-02-05 23_article_28',2),('2015-02-06 00cuoi',1),('2015-02-06 00hay',2),('2015-02-06 00_article_22',1),('2015-02-06 00_article_23',19),('2015-02-06 00_article_24',10),('2015-02-06 00_article_25',9),('2015-02-06 00_article_28',5),('2015-02-06 01hay',2),('2015-02-06 01home',8),('2015-02-06 09home',1),('2015-02-06 10_article_28',1);
/*!40000 ALTER TABLE `tracking` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-02-06 11:02:47
