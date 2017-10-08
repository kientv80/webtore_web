package com.xyz.hayhay.db.dummydata;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.xyz.hayhay.entirty.CategoryInfo;
import com.xyz.hayhay.entirty.MenuItem;
import com.xyz.hayhay.entirty.NewsTypes;

public class MappingHelper {
	public static Map<String,String> categoryTypeLabelMapping = new HashMap<>();
	public static Map<String,CategoryInfo> cateInfo = new HashMap<>();
	public static Map<String,List<CategoryInfo>> parentCateMapping = new HashMap<>();
	public static Map<String,List<MenuItem>> cateMenuItemMapping = new HashMap<>();
	public static Map<String, List<String>> cateGroup = new TreeMap<>();
	public static List<MenuItem> mainMenuitems = new ArrayList<>();
	public static List<MenuItem> otherMenuitems = new ArrayList<>();
	public static List<String> worldNews = new ArrayList<>();
	
	
	
	static {
		categoryTypeLabelMapping.put(NewsTypes.TYPE_TINTRONGNUOC, "Tin Trong Nước");
		categoryTypeLabelMapping.put(NewsTypes.TYPE_TINQUOCTE, "Tin Thế Giới");
		categoryTypeLabelMapping.put(NewsTypes.TYPE_SPORTNEWS, "Tin Thể Thao");
		
		categoryTypeLabelMapping.put(NewsTypes.XE, "Ô Tô - Xe Máy");
		categoryTypeLabelMapping.put(NewsTypes.OTO, "Ô Tô");
		categoryTypeLabelMapping.put(NewsTypes.XEMAY, "Xe Máy");
		categoryTypeLabelMapping.put(NewsTypes.SIEUXE, "Siêu Xe");
		categoryTypeLabelMapping.put(NewsTypes.XEDO, "Xe Độ");
		categoryTypeLabelMapping.put(NewsTypes.XE_DIENDAN,"Diễn Đàn");
		categoryTypeLabelMapping.put(NewsTypes.XE_THITRUONG,"Thị Trường");
		
		
		categoryTypeLabelMapping.put(NewsTypes.TYPE_KINHTE,  "Tin Kinh Tế");
		categoryTypeLabelMapping.put(NewsTypes.TAICHINH,"Tài Chính");
		categoryTypeLabelMapping.put(NewsTypes.CHUNGKHOAN,  "Chứng Khoán");
		categoryTypeLabelMapping.put(NewsTypes.TYGIA, "Tỷ Giá");
		categoryTypeLabelMapping.put(NewsTypes.NGANHANG, "Ngân Hàng");
		categoryTypeLabelMapping.put(NewsTypes.NGANSACH,  "Ngân Sách");
		categoryTypeLabelMapping.put(NewsTypes.THITRUONGVANG, "Thị Trường Vàng");
		categoryTypeLabelMapping.put(NewsTypes.TYPE_ECONOMY,  "Kinh Tế-Tài Chính");
		
//		categoryColorMapping.put(NewsTypes.TYPE_SPORTNEWS, new CateInfo(GREEN, "Tin Thể Thao");
		categoryTypeLabelMapping.put(NewsTypes.NGOISAO, "Tin Tức Ngôi Sao");
		categoryTypeLabelMapping.put(NewsTypes.TYPE_TINHYEU, "Tình Yêu");
		categoryTypeLabelMapping.put(NewsTypes.HUONGTHIEN,  "Sống Tốt");
		
		categoryTypeLabelMapping.put(NewsTypes.TYPE_SUCKHOE, "Sức Khỏe");
		categoryTypeLabelMapping.put(NewsTypes.AMTHUC, "Ẩm Thực");
		categoryTypeLabelMapping.put(NewsTypes.DINHDUONG,  "Dinh Dưỡng");
		categoryTypeLabelMapping.put(NewsTypes.THUOCQUANHTA,  "Thuốc Quanh Ta");
		categoryTypeLabelMapping.put(NewsTypes.BAITHUOC, "Bài Thuốc");
		categoryTypeLabelMapping.put(NewsTypes.YHOCCOTRUYEN,  "Y Học Cổ Truyến");
		categoryTypeLabelMapping.put(NewsTypes.BAITHUOCHAY,  "Bài Thuốc Hay");
//		
		categoryTypeLabelMapping.put(NewsTypes.TYPE_MUSIC, "Nhạc Mới");
		categoryTypeLabelMapping.put(NewsTypes.NHACTRE, "Nhạc Trẻ");
		categoryTypeLabelMapping.put(NewsTypes.NHACVANG, "Nhạc Vàng");
		categoryTypeLabelMapping.put(NewsTypes.NHACTET, "Nhạc Tết");
		categoryTypeLabelMapping.put(NewsTypes.NHACDO, "Nhạc Đỏ");
		categoryTypeLabelMapping.put(NewsTypes.NHACKHONGLOI, "Khac Không Lời");
		categoryTypeLabelMapping.put(NewsTypes.NHACNUOCNGOAI,  "Nhạc Nước Ngoài");
		categoryTypeLabelMapping.put(NewsTypes.NHACTHIEUNHI,  "Nhạc Thiếu Nhi");

		categoryTypeLabelMapping.put(NewsTypes.BONGDA, "Tin Bóng Đá");
		categoryTypeLabelMapping.put(NewsTypes.TENNIS, "Tennis");
		categoryTypeLabelMapping.put(NewsTypes.THETHEO_VIDEO,  "Video");
		categoryTypeLabelMapping.put(NewsTypes.GOLF, "Golf");
		categoryTypeLabelMapping.put(NewsTypes.HAUTRUONG,  "Hậu Trường");
		
		categoryTypeLabelMapping.put(NewsTypes.BOXING, "Kỹ Thuật Boxing");
		categoryTypeLabelMapping.put(NewsTypes.JUDOLEARNING,"Kỹ Thuật Judo");
		categoryTypeLabelMapping.put(NewsTypes.MMALEARNING, "Kỹ Thuật MMA");
		categoryTypeLabelMapping.put(NewsTypes.YOGALEARNING, "Kỹ Thuật Yoga");
		
		categoryTypeLabelMapping.put(NewsTypes.WN_BIZ, "Business");
		categoryTypeLabelMapping.put(NewsTypes.WN_HOME,  "News");
		categoryTypeLabelMapping.put(NewsTypes.WN_SIENCE, "Sience");
		categoryTypeLabelMapping.put(NewsTypes.WN_TECH, "Tech");
		worldNews.add(NewsTypes.WN_BIZ);
		worldNews.add(NewsTypes.WN_HEALTH);
		worldNews.add(NewsTypes.WN_HOME);
		worldNews.add(NewsTypes.WN_POLITICS);
		worldNews.add(NewsTypes.WN_SIENCE);
		worldNews.add(NewsTypes.WN_TECH);
		worldNews.add(NewsTypes.WORLDNEWS);
		
		categoryTypeLabelMapping.put(NewsTypes.KN_GIAOTIEP, "Kỹ Năng Giao Tiếp");
		categoryTypeLabelMapping.put(NewsTypes.KN_BAN_HANG, "Kỹ Năng Bán Hàng");
		categoryTypeLabelMapping.put(NewsTypes.KN_DAMPHAN_THUONGLUONG,"Kỹ Năng Đàm Phán, Thương Lượng");
		categoryTypeLabelMapping.put(NewsTypes.KN_GIAIQUYET_VANDE, "Kỹ Năng Giải Quyết Vấn Đề");
		categoryTypeLabelMapping.put(NewsTypes.KN_LAMVIEC_NHOM, "Kỹ Năng Làm Việc Nhóm");
		categoryTypeLabelMapping.put(NewsTypes.KN_MARKETING_ONLINE, "Kỹ Năng Marketing Online");
		categoryTypeLabelMapping.put(NewsTypes.KN_QL_THOIGIAN, "Kỹ Năng Quản Lý Thời Gian");
		categoryTypeLabelMapping.put(NewsTypes.KN_THUYETTRINH,"Kỹ Năng Thuyết Trình");
		categoryTypeLabelMapping.put(NewsTypes.kntaichinhdoannghiep, "Tái Chính Doanh Nghiệp");
		
		categoryTypeLabelMapping.put(NewsTypes.SHOWBIZVIET, "Showbiz-Việt");
		categoryTypeLabelMapping.put(NewsTypes.PHONGCACH, "Phong cách");
		categoryTypeLabelMapping.put(NewsTypes.HOLLYWOOD, "Hollywood");
		categoryTypeLabelMapping.put(NewsTypes.CHAUA, "Châu á");
		categoryTypeLabelMapping.put(NewsTypes.BENLE,  "Bên lề");
		
		categoryTypeLabelMapping.put(NewsTypes.TINHYEU, "Tình Yêu");
		categoryTypeLabelMapping.put(NewsTypes.GIOITINH, "Giới Tính");
		categoryTypeLabelMapping.put(NewsTypes.GIADINH,  "Gia Đình");
		categoryTypeLabelMapping.put(NewsTypes.GOCTAMHON, "Góc Tâm Hồn");
		categoryTypeLabelMapping.put(NewsTypes.MEVABE, "Mẹ và Bé");
		
		categoryTypeLabelMapping.put(NewsTypes.NHACTREMOI, "Nhac Trẻ Mới");
		categoryTypeLabelMapping.put(NewsTypes.NHACTRUTINH,  "Nhạc Trữ Tình");
		categoryTypeLabelMapping.put(NewsTypes.NHACTRINH,  "Nhạc Trịnh");
		categoryTypeLabelMapping.put(NewsTypes.ROCKVIET,  "Rock Việt");
		categoryTypeLabelMapping.put(NewsTypes.THIEUNHI, "Nhạc Thiếu Nhi");
		categoryTypeLabelMapping.put(NewsTypes.VIDEO,  "Video");
		categoryTypeLabelMapping.put(NewsTypes.NHACMOI, "Nhạc Mới");
		categoryTypeLabelMapping.put(NewsTypes.NHACRAP, "Nhạc RAP");
		categoryTypeLabelMapping.put(NewsTypes.NHACAUMY, "Nhạc Âu Mỹ");
		
		categoryTypeLabelMapping.put(NewsTypes.CNTT, "Công Nghệ Thông Tin");
		categoryTypeLabelMapping.put(NewsTypes.VIENTHONG,  "Viễng Thông");
		categoryTypeLabelMapping.put(NewsTypes.INTERNET, "Internet");
		categoryTypeLabelMapping.put(NewsTypes.TGS,  "Thế Giới Số");
		categoryTypeLabelMapping.put(NewsTypes.KHOINGHIEP, "Khởi Nghiệp");
		categoryTypeLabelMapping.put(NewsTypes.TYPE_CONGNGHE,  "Công Nghệ");
		
		categoryTypeLabelMapping.put(NewsTypes.MUABANNHADAT,  "Mua bán nhà đất");
		categoryTypeLabelMapping.put(NewsTypes.BDS_HCM,  "Mua bán nhà tại Hồ Chí Minh");
		categoryTypeLabelMapping.put(NewsTypes.BDS_HN,  "Mua bán nhà tại Hà Nôi");
		categoryTypeLabelMapping.put(NewsTypes.BDS_DN,  "Mua bán nhà tại Đà Nẵng");
		categoryTypeLabelMapping.put(NewsTypes.BDS_VT,  "Mua bán nhà tại Bà Rịa Vũng Tầu");
		
		categoryTypeLabelMapping.put(NewsTypes.MOVIE_PLAYING,  "Phim Đang Chiếu");
		categoryTypeLabelMapping.put(NewsTypes.MOVIE_COMMING,  "Phim Sắp Chiếu");
		categoryTypeLabelMapping.put(NewsTypes.MOVIE_DISCOUNT,  "Giảm giá/Khuyến mãi");
		
		categoryTypeLabelMapping.put(NewsTypes.NAUAN,  "Nấu Ăn");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_HEO,  "Món Ăn Từ Thịt Heo");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_GA,  "Món Ăn Từ Thịt Gà");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_BO,  "Món Ăn Từ Thịt Bò");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_CA,  "Món Ăn Từ Cá");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_MUC,  "Món Ăn Từ Mực");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_TOM,  "Món Ăn Từ Tôm");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_HAISAN,  "Món Ăn Từ Hai Sản");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_NAM,  "Món Ăn Từ Nấm");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_DAUHU,  "Món Ăn Từ Đậu Hũ");
		categoryTypeLabelMapping.put(NewsTypes.TYPE_MOVIE,  "Phim Chiếu Rạp");
		categoryTypeLabelMapping.put(NewsTypes.DIA_OC,  "Địa Ốc");
		categoryTypeLabelMapping.put(NewsTypes.NHADEP,  "Nhà Đẹp");
		categoryTypeLabelMapping.put(NewsTypes.QUYHOACH,  "Quy Hoạch");
		categoryTypeLabelMapping.put(NewsTypes.VATLIEU,  "Vật Liệu");
		categoryTypeLabelMapping.put(NewsTypes.KIENTRUC,  "Kiến Trúc");
		categoryTypeLabelMapping.put(NewsTypes.XAYDUNG,  "Xây Dựng");
		categoryTypeLabelMapping.put(NewsTypes.BATDONGSAN,  "Bất Động Sản");
		categoryTypeLabelMapping.put(NewsTypes.KHOINGHIEP,  "Khởi Nghiệp");
		
		categoryTypeLabelMapping.put(NewsTypes.FILM_CHIENTRANH,  "Phim Chiến Tranh");
		categoryTypeLabelMapping.put(NewsTypes.FILM_HAIHUOC,  "Phim Hài Hước");
		categoryTypeLabelMapping.put(NewsTypes.FILM_HANHDONG,  "Phim Hành Động");
		categoryTypeLabelMapping.put(NewsTypes.FILM_HOATHINH,  "Phim Hoạt Hình");
		categoryTypeLabelMapping.put(NewsTypes.FILM_KINHDI,  "Phim Kinh Dị");
		categoryTypeLabelMapping.put(NewsTypes.FILM_NHAC,  "Phim Thể Thao-Nhạc");
		categoryTypeLabelMapping.put(NewsTypes.FILM_PHIEULUU,  "Phim Phiêu Lưu");
		categoryTypeLabelMapping.put(NewsTypes.FILM_THANTHOAI,  "Phim Thần Thoại");
		categoryTypeLabelMapping.put(NewsTypes.FILM_TINHCAM,  "Phim Tình Cảm");
		categoryTypeLabelMapping.put(NewsTypes.FILM_VIENTUONG,  "Phim Viễn Tưởng");
		categoryTypeLabelMapping.put(NewsTypes.FILM_VOTHUAT,  "Phim Võ Thuật");
		categoryTypeLabelMapping.put(NewsTypes.ENTERTAINMENT,  "Giải Trí");
		categoryTypeLabelMapping.put(NewsTypes.TYPE_FILM,  "Phim Mới");
		
		categoryTypeLabelMapping.put(NewsTypes.NHAN_VAT,  "Nhân Vật");
		categoryTypeLabelMapping.put(NewsTypes.QUAN_TRI,  "Quản Trị");
		categoryTypeLabelMapping.put(NewsTypes.NGHE_NGHIEP,  "Nghề Nhiệp");
		categoryTypeLabelMapping.put(NewsTypes.THUONG_HIEU,  "Thương Hiệu");
		categoryTypeLabelMapping.put(NewsTypes.CHINHSACH,  "Chính Sách");
		categoryTypeLabelMapping.put(NewsTypes.KINH_NHIEM_KINH_DOANH,  "Chia Sẻ Kinh Nhiệm");
		categoryTypeLabelMapping.put(NewsTypes.DOANH_NGHIEP,  "Doanh Nghiệp");
		categoryTypeLabelMapping.put(NewsTypes.KINH_DOANH,  "Kinh Doanh");
		
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_RAUCU,  "Món Từ Rau Củ");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_TRUNG,  "Món Từ Trứng");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_MONY,  "Món Ý");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_MONHAN,  "Món Hàn");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_MONNHAT,  "Món Nhật");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_MONPHAP,  "Món Pháp");
		categoryTypeLabelMapping.put(NewsTypes.NAUAN_MONTHAI,  "Món Thái");
		
		
		cateInfo.put(NewsTypes.TINTUC, new CategoryInfo(NewsTypes.TINTUC, "http://360hay.com/news/" + NewsTypes.TINTUC, "http://360hay.com/images/icons/360logo.png", "Tin kinh tế, chính trị, thể thao, văn hóa, tài chính", "Tin kinh tế, chính trị, thể thao, văn hóa, tài chính", "kinh tế, chính trị, thể thao, văn hóa, tài chính"));
		cateInfo.put(NewsTypes.KINHTE, new CategoryInfo(NewsTypes.KINHTE, "http://360hay.com/news/" + NewsTypes.KINHTE, "http://360hay.com/images/icons/360logo.png", "Tin kinh tế, tài chính, ngân hàng, tiền tệ, tỷ giá, giá vàng", "Tin kinh tế, tài chính, ngân hàng, tiền tệ, tỷ giá, giá vàng", "kinh tế, tài chính, ngân hàng, tiền tệ, tỷ giá, giá vàng"));
		cateInfo.put(NewsTypes.CONGNGHE, new CategoryInfo(NewsTypes.CONGNGHE, "http://360hay.com/news/" + NewsTypes.CONGNGHE, "http://360hay.com/images/icons/360logo.png", "Tin công nghệ thông tin, viễn thông, internet, network, smart phone", "Tin công nghệ thông tin, viễn thông, internet, network, smart phone", "công nghệ thông tin, viễn thông, internet, network, smart phone"));
		cateInfo.put(NewsTypes.THETHAO, new CategoryInfo(NewsTypes.THETHAO, "http://360hay.com/news/" + NewsTypes.THETHAO, "http://360hay.com/images/icons/360logo.png", "Tin thể thao, bóng đá, tennis, golf", "Tin thể thao, bóng đá, tennis, golf", "thể thao, bóng đá, tennis, golf"));
		cateInfo.put(NewsTypes.SUCKHOE, new CategoryInfo(NewsTypes.SUCKHOE, "http://360hay.com/news/" + NewsTypes.SUCKHOE, "http://360hay.com/images/icons/360logo.png", "Tin sức khỏe, ẩm thực, dinh dưỡng, bài thuốc, thuốc quanh ta,y học cổ truyền,bài thuốc hay", "Tin sức khỏe, ẩm thực, dinh dưỡng, bài thuốc, thuốc quanh ta,y học cổ truyền,bài thuốc hay", "sức khỏe, ẩm thực, dinh dưỡng, bài thuốc, thuốc quanh ta,y học cổ truyền,bài thuốc hay"));
		cateInfo.put(NewsTypes.NGOISAO, new CategoryInfo(NewsTypes.NGOISAO, "http://360hay.com/news/" + NewsTypes.NGOISAO, "http://360hay.com/images/icons/360logo.png", "Tin ngôi sao, ca sĩ, nghệ sỹ", "Tin ngôi sao, ca sĩ, nghệ sỹ", "ngôi sao, ca sĩ, nghệ sỹ"));
		cateInfo.put(NewsTypes.TINHYEU, new CategoryInfo(NewsTypes.TINHYEU, "http://360hay.com/news/" + NewsTypes.TINHYEU, "http://360hay.com/images/icons/360logo.png", "Tin tình yêu, giới tính, gia đình, tâm hồn", "Tin tình yêu, giới tính, gia đình, tâm hồn", "tình yêu, giới tính, gia đình, tâm hồn"));
		cateInfo.put(NewsTypes.NHAC, new CategoryInfo(NewsTypes.NHAC, "http://360hay.com/news/" + NewsTypes.NHAC, "http://360hay.com/images/icons/360logo.png", "Albumn nhạc trẻ, trững tình, rock, nhạc trịnh", "Albumn nhạc trẻ, trững tình, rock, nhạc trịnh", "nhạc trẻ, trững tình, rock, nhạc trịnh"));
		cateInfo.put(NewsTypes.XE, new CategoryInfo(NewsTypes.XE, "http://360hay.com/news/" + NewsTypes.XE, "http://360hay.com/images/icons/360logo.png", "Tin tức về xe moto, ô tô, xe độ, siêu xe, diễng đàn về xe", "Tin tức về xe moto, ô tô, xe độ, siêu xe, diễng đàn về xe", "Tin tức về xe moto, ô tô, xe độ, siêu xe, diễng đàn về xe"));
		cateInfo.put(NewsTypes.VIECLAM, new CategoryInfo(NewsTypes.VIECLAM, "http://360hay.com/news/" + NewsTypes.VIECLAM, "http://360hay.com/images/icons/360logo.png", "Tin việc làm", "Tin việc làm", "việc làm"));
		cateInfo.put(NewsTypes.BATDONGSAN, new CategoryInfo(NewsTypes.BATDONGSAN, "http://360hay.com/news/" + NewsTypes.BATDONGSAN, "http://360hay.com/images/icons/360logo.png", "Tin mua, bán bất động sản, nhà cửa, đất đai, căn hộ", "Tin mua, bán bất động sản, nhà cửa, đất đai, căn hộ", "Tin mua, bán bất động sản, nhà cửa, đất đai, căn hộ"));
		cateInfo.put(NewsTypes.KYNANGMEN, new CategoryInfo(NewsTypes.KYNANGMEN, "http://360hay.com/news/" + NewsTypes.KYNANGMEN, "http://360hay.com/images/icons/360logo.png", "Các bài viết hay về kỹ năng mềm", "Các bài viết hay về kỹ năng giao tiếp, quản lý, kỹ ngăng nghe,kỹ ngăng thuyết trinh,kỹ ngăng giải quyết vấn đề, làm việc nhóm, bán hàng, marketing", "kỹ năng giao tiếp, quản lý, kỹ ngăng nghe,kỹ ngăng thuyết trinh,kỹ ngăng giải quyết vấn đề, làm việc nhóm, bán hàng, marketing "));
		cateInfo.put(NewsTypes.TVPROGRAM, new CategoryInfo(NewsTypes.TVPROGRAM, "http://360hay.com/news/" + NewsTypes.TVPROGRAM, "http://360hay.com/images/icons/360logo.png", "Lịch phát sóng chương trình TV", "Lịch phát sóng chương trình TV", "VTV, HTC, HTVC, SCTV"));
		cateInfo.put(NewsTypes.KHOINGHIEP, new CategoryInfo(NewsTypes.KHOINGHIEP, "http://360hay.com/news/" + NewsTypes.KHOINGHIEP, "http://360hay.com/images/icons/360logo.png", "Khởi Nghiệp", "Khởi Nghiệp", "Khởi Nghiệp"));
		cateInfo.put(NewsTypes.NHADEP, new CategoryInfo(NewsTypes.NHADEP, "http://360hay.com/news/" + NewsTypes.NHADEP, "http://360hay.com/images/icons/360logo.png", "Nhà Đẹp", "Nhà Đẹp", "Nhà Đẹp"));
		cateInfo.put(NewsTypes.QUYHOACH, new CategoryInfo(NewsTypes.QUYHOACH, "http://360hay.com/news/" + NewsTypes.QUYHOACH, "http://360hay.com/images/icons/360logo.png", "Quy Hoạch", "Quy Hoạch", "Quy Hoạch"));
		cateInfo.put(NewsTypes.VATLIEU, new CategoryInfo(NewsTypes.VATLIEU, "http://360hay.com/news/" + NewsTypes.VATLIEU, "http://360hay.com/images/icons/360logo.png", "Vật Liệu", "Vật Liệu", "Vật Liệu"));
		cateInfo.put(NewsTypes.KIENTRUC, new CategoryInfo(NewsTypes.KIENTRUC, "http://360hay.com/news/" + NewsTypes.KIENTRUC, "http://360hay.com/images/icons/360logo.png", "Kiến Trúc", "Kiến Trúc", "Kiến Trúc"));
		cateInfo.put(NewsTypes.BATDONGSAN, new CategoryInfo(NewsTypes.BATDONGSAN, "http://360hay.com/news/" + NewsTypes.BATDONGSAN, "http://360hay.com/images/icons/360logo.png", "Bất Động Sản", "Bất Động Sản", "Bất Động Sản"));
		
		cateInfo.put(NewsTypes.WN_HOME, new CategoryInfo(NewsTypes.WN_HOME, "http://360hay.com/news/" + NewsTypes.WN_HOME, "http://360hay.com/images/icons/360logo.png", "News", "News", "News"));
		cateInfo.put(NewsTypes.WN_BIZ, new CategoryInfo(NewsTypes.WN_BIZ, "http://360hay.com/news/" + NewsTypes.WN_BIZ, "http://360hay.com/images/icons/360logo.png", "Buseness news", "Buseness news", "Buseness news"));
		cateInfo.put(NewsTypes.WN_SIENCE, new CategoryInfo(NewsTypes.WN_SIENCE, "http://360hay.com/news/" + NewsTypes.WN_SIENCE, "http://360hay.com/images/icons/360logo.png", "Sience news", "Sience news", "Sience news"));
		cateInfo.put(NewsTypes.WN_TECH, new CategoryInfo(NewsTypes.WN_TECH, "http://360hay.com/news/" + NewsTypes.WN_TECH, "http://360hay.com/images/icons/360logo.png", "Technology news", "Technology news", "Technology news"));
		
		mainMenuitems.add(new MenuItem(NewsTypes.TINTUC,"/news/" + NewsTypes.TINTUC, "Tuyển tập các thông tin trong nước mới nhất", "Tin tức", "/images/icons/newspaper.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.KINHTE,"/news/" + NewsTypes.KINHTE, "Tuyển tập các thông tin kinh tế trong nước mới nhất", "Kinh tế", "/images/icons/newspaper.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.KINH_DOANH,"/news/" + NewsTypes.KINH_DOANH, "Kinh doanh", "Kinh doanh", "/images/icons/newspaper.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.CONGNGHE,"/news/" + NewsTypes.CONGNGHE, "Tuyển tập các thông tin kinh tế trong nước mới nhất", "Công Nghệ", "/images/icons/newspaper.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.THETHAO,"/news/" + NewsTypes.THETHAO, "Tuyển tập thông tin thể thao mới nhất", "Thể Thao", "/images/icons/sport.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.XE,"/news/" + NewsTypes.XE, "Xe", "Xe", "/images/icons/newspaper.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.BATDONGSAN,"/news/" + NewsTypes.BATDONGSAN, "Nhà Đất", "Nhà Đất", "/images/icons/newspaper.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.KHOINGHIEP,"/news/" + NewsTypes.KHOINGHIEP, "Khởi Nghiệp", "Khởi Nghiệp", "/images/icons/newspaper.png"));
		
		mainMenuitems.add(new MenuItem(NewsTypes.NGOISAO,"/cate/" + NewsTypes.NGOISAO, "Tuyển tập các thông tin về người nổi tiếng, ngôi sao", "Ngôi Sao", "/images/icons/star.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.SUCKHOE,"/cate/" + NewsTypes.SUCKHOE, "Tuyển tập các thông tin về sức khỏe, gia đình mới nhất", "Sức khỏe", "/images/icons/newspaper.png"));
		mainMenuitems.add(new MenuItem(NewsTypes.TINHYEU,"/cate/" + NewsTypes.TINHYEU, "Tình yêu - Gia đình", "Tinh Yêu", "/images/icons/love.png"));
		otherMenuitems.add(new MenuItem(NewsTypes.MOVIE,"/cate/" + NewsTypes.MOVIE, "Film/Phim đang chiếu, sắp chiếu trên các dạp Galaxy và các chương trình khuyến mãi.", "Phim chiếu rạp", "/images/icons/category/entertainment.jpg"));
		otherMenuitems.add(new MenuItem(NewsTypes.NHAC,"/cate/" + NewsTypes.NHAC, "Tập hợp các bài hát hay", "Nhạc", "/images/icons/guitar.png"));
		otherMenuitems.add(new MenuItem(NewsTypes.FILM,"/cate/" + NewsTypes.FILM, "Tập hợp phim hay", "Phim", "/images/icons/guitar.png"));
		otherMenuitems.add(new MenuItem(NewsTypes.FUNYSTORY,"/cate/" + NewsTypes.FUNYSTORY, "Cười", "Cười", "/images/icons/guitar.png"));
		otherMenuitems.add(new MenuItem(NewsTypes.NAUAN,"/cate/" + NewsTypes.NAUAN, "Nấu Ăn", "Nấu Ăn", "/images/icons/newspaper.png"));
		
		//Others
		//otherMenuitems.add(new MenuItem(NewsTypes.VIECLAM,"/job/" + NewsTypes.VIECLAM, "Việc làm", "Việc làm", "/images/icons/newspaper.png"));
		//otherMenuitems.add(new MenuItem(NewsTypes.KYNANGMEN,"/news/" + NewsTypes.KYNANGMEN, "Kỹ năng mềm", "Kỹ năng mềm", "/images/icons/newspaper.png"));
		
		otherMenuitems.add(new MenuItem(NewsTypes.WEBSTORE,"/" + NewsTypes.WEBSTORE, "Webstore", "Webstore", "/images/icons/guitar.png"));
		
		otherMenuitems.add(new MenuItem(NewsTypes.LETTERY,"/webstore/lottery", "Kết Quả Xổ Số", "Kết Quả Xổ Số", "/images/icons/guitar.png"));
		otherMenuitems.add(new MenuItem(NewsTypes.TVPROGRAM,"/entertainment/" + NewsTypes.TVPROGRAM, "360hay TV, tập hợp lịch phát sóng của các đài truyền hình VN như VTV, VTVC, HTV, Truyền hình Hà Nội vv", "Lịch phát sóng TV", "/images/icons/tv.png"));
		
		//Category group
		cateGroup.put(NewsTypes.TINTUC,  Arrays.asList(new String[] { NewsTypes.TYPE_TINTRONGNUOC, NewsTypes.TYPE_TINQUOCTE, NewsTypes.TYPE_ECONOMY, NewsTypes.TYPE_CONGNGHE, NewsTypes.KINH_DOANH, NewsTypes.KHOINGHIEP}));//NewsTypes.NGOISAO,
		cateGroup.put(NewsTypes.KINHTE, Arrays.asList(new String[] { NewsTypes.THITRUONGVANG, NewsTypes.TYPE_KINHTE,NewsTypes.CHINHSACH, NewsTypes.TAICHINH, NewsTypes.CHUNGKHOAN, NewsTypes.TYGIA, NewsTypes.NGANHANG, NewsTypes.NGANSACH }));
		cateGroup.put(NewsTypes.CONGNGHE,   Arrays.asList(new String[] { NewsTypes.CNTT,NewsTypes.DOANH_NGHIEP, NewsTypes.VIENTHONG, NewsTypes.INTERNET, NewsTypes.TGS, NewsTypes.KHOINGHIEP }));
		cateGroup.put(NewsTypes.THETHAO,    Arrays.asList(new String[] { NewsTypes.BONGDA, NewsTypes.TENNIS, NewsTypes.HAUTRUONG, NewsTypes.GOLF, NewsTypes.THETHEO_VIDEO }));
		cateGroup.put(NewsTypes.XE,  Arrays.asList(new String[] { NewsTypes.OTO, NewsTypes.XEMAY,  NewsTypes.XEDO, NewsTypes.SIEUXE, NewsTypes.XE_DIENDAN, NewsTypes.XE_THITRUONG }));
		cateGroup.put(NewsTypes.BATDONGSAN,  Arrays.asList(new String[] {NewsTypes.BATDONGSAN,NewsTypes.DIA_OC,NewsTypes.QUYHOACH,NewsTypes.KIENTRUC,NewsTypes.NHADEP,NewsTypes.VATLIEU }));
		cateGroup.put(NewsTypes.TINHYEU,  Arrays.asList(new String[] { NewsTypes.TINHYEU, NewsTypes.GIADINH,NewsTypes.MEVABE, NewsTypes.GOCTAMHON, NewsTypes.GIOITINH, NewsTypes.HUONGTHIEN }));
		cateGroup.put(NewsTypes.FUNYSTORY,   Arrays.asList(new String[] { NewsTypes.FUNYSTORY }));
		cateGroup.put(NewsTypes.NGOISAO,  Arrays.asList(new String[] { NewsTypes.SHOWBIZVIET, NewsTypes.PHONGCACH, NewsTypes.CHAUA, NewsTypes.HOLLYWOOD, NewsTypes.BENLE }));
		cateGroup.put(NewsTypes.MOVIE,  Arrays.asList(new String[] { NewsTypes.MOVIE_PLAYING,  NewsTypes.MOVIE_DISCOUNT,  NewsTypes.MOVIE_COMMING }));
		cateGroup.put(NewsTypes.NHAC,  Arrays.asList(new String[] { NewsTypes.NHACTREMOI, NewsTypes.NHACTRUTINH, NewsTypes.ROCKVIET, NewsTypes.NHACRAP, NewsTypes.NHACAUMY, NewsTypes.NHACTRINH, NewsTypes.THIEUNHI, NewsTypes.VIDEO, NewsTypes.NHACKHONGLOI }));
		cateGroup.put(NewsTypes.FILM,  Arrays.asList(new String[] { NewsTypes.FILM_CHIENTRANH, NewsTypes.FILM_VOTHUAT, NewsTypes.FILM_HANHDONG, NewsTypes.FILM_NHAC, NewsTypes.FILM_HAIHUOC, NewsTypes.FILM_HOATHINH, NewsTypes.FILM_KINHDI,  NewsTypes.FILM_PHIEULUU, NewsTypes.FILM_THANTHOAI, NewsTypes.FILM_TINHCAM, NewsTypes.FILM_VIENTUONG }));
		cateGroup.put(NewsTypes.SUCKHOE,  Arrays.asList(new String[] { NewsTypes.TYPE_SUCKHOE, NewsTypes.AMTHUC, NewsTypes.DINHDUONG, NewsTypes.BAITHUOC, NewsTypes.THUOCQUANHTA, NewsTypes.YHOCCOTRUYEN, NewsTypes.BAITHUOCHAY }));
		cateGroup.put(NewsTypes.NAUAN,  Arrays.asList(new String[] {  NewsTypes.NAUAN_HEO, NewsTypes.NAUAN_GA,NewsTypes.NAUAN_TOM,NewsTypes.NAUAN_TRUNG,NewsTypes.NAUAN_RAUCU, NewsTypes.NAUAN_BO, NewsTypes.NAUAN_CA,NewsTypes.NAUAN_MUC, NewsTypes.NAUAN_HAISAN, NewsTypes.NAUAN_NAM, NewsTypes.NAUAN_DAUHU , NewsTypes.NAUAN_MONY, NewsTypes.NAUAN_MONHAN, NewsTypes.NAUAN_MONNHAT, NewsTypes.NAUAN_MONTHAI, NewsTypes.NAUAN_MONPHAP}));
		cateGroup.put(NewsTypes.KYNANGMEN,   Arrays.asList(new String[] { NewsTypes.KN_GIAOTIEP, NewsTypes.KN_THUYETTRINH, NewsTypes.KN_GIAIQUYET_VANDE, NewsTypes.KN_LAMVIEC_NHOM, NewsTypes.KN_BAN_HANG, NewsTypes.KN_MARKETING_ONLINE, NewsTypes.KN_DAMPHAN_THUONGLUONG, NewsTypes.kntaichinhdoannghiep, NewsTypes.kntaichinhcanhan }));
		cateGroup.put(NewsTypes.KHOINGHIEP,   Arrays.asList(new String[] { NewsTypes.KHOINGHIEP}));
		cateGroup.put(NewsTypes.KINH_DOANH,   Arrays.asList(new String[] {NewsTypes.QUAN_TRI, NewsTypes.NGHE_NGHIEP, NewsTypes.THUONG_HIEU, NewsTypes.KINH_NHIEM_KINH_DOANH, NewsTypes.NHAN_VAT}));
		cateGroup.put(NewsTypes.ENTERTAINMENT,   Arrays.asList(new String[] {NewsTypes.NGOISAO,NewsTypes.TINHYEU, NewsTypes.FUNYSTORY}));
		cateGroup.put(NewsTypes.NHAC_FILMS,   Arrays.asList(new String[] {NewsTypes.TYPE_MUSIC, NewsTypes.TYPE_FILM, NewsTypes.MOVIE_NEWS}));
		
		cateGroup.put(NewsTypes.WORLDNEWS,  Arrays.asList(new String[] {NewsTypes.WN_BIZ, NewsTypes.WN_POLITICS,NewsTypes.WN_TECH, NewsTypes.WN_SIENCE, NewsTypes.WN_HEALTH}));
		cateGroup.put(NewsTypes.WN_HOME,  Arrays.asList(new String[] {NewsTypes.WN_BIZ, NewsTypes.WN_POLITICS,NewsTypes.WN_TECH, NewsTypes.WN_SIENCE, NewsTypes.WN_HEALTH}));
		
		cateGroup.put(NewsTypes.WN_BIZ,  Arrays.asList(new String[] { NewsTypes.WN_BIZ}));
		cateGroup.put(NewsTypes.WN_TECH,  Arrays.asList(new String[] { NewsTypes.WN_TECH}));
		cateGroup.put(NewsTypes.WN_SIENCE,  Arrays.asList(new String[] { NewsTypes.WN_SIENCE}));
		cateGroup.put(NewsTypes.WN_HEALTH,  Arrays.asList(new String[] { NewsTypes.WN_HEALTH}));
		cateGroup.put(NewsTypes.WN_POLITICS,  Arrays.asList(new String[] { NewsTypes.WN_POLITICS}));
		
		
		List<CategoryInfo> tintrongnuoc = new ArrayList<>();
		tintrongnuoc.add(new CategoryInfo("Chính Trị Xã Hội", "/news/type/"+NewsTypes.CHINHTRIXAHOI, null	, null, null, null));
		tintrongnuoc.add(new CategoryInfo("Thời Sự-Suy Nghĩ", "/news/type/"+NewsTypes.THOISU, null	, null, null, null));
		tintrongnuoc.add(new CategoryInfo("Phóng Sự-Ký Sự", "/news/type/"+NewsTypes.PHONGSU, null	, null, null, null));
		tintrongnuoc.add(new CategoryInfo("Môi Trường", "/news/type/"+NewsTypes.MOITRUONG, null	, null, null, null));
		tintrongnuoc.add(new CategoryInfo("Chuyện Thường Ngày", "/news/type/"+NewsTypes.CHUYENTHUONGNGAY, null	, null, null, null));
		tintrongnuoc.add(new CategoryInfo("Tiêu Điểm", "/news/type/"+NewsTypes.TIEUDIEM, null	, null, null, null));
		tintrongnuoc.add(new CategoryInfo("Pháp Luật", "/news/type/"+NewsTypes.PHAPLUAT, null	, null, null, null));
		tintrongnuoc.add(new CategoryInfo("Nhịp Sống Trẻ", "/news/type/"+NewsTypes.NHIPSONGTRE, null	, null, null, null));
		parentCateMapping.put(NewsTypes.TYPE_TINTRONGNUOC, tintrongnuoc);
		
		List<CategoryInfo> tinkinhte = new ArrayList<>();
		tinkinhte.add(new CategoryInfo("Tài Chính", "/news/type/"+NewsTypes.TAICHINH, null	, null, null, null));
		tinkinhte.add(new CategoryInfo("Ngân Hàng", "/news/type/"+NewsTypes.NGANHANG, null	, null, null, null));
		tinkinhte.add(new CategoryInfo("Ngân Sách", "/news/type/"+NewsTypes.NGANSACH, null	, null, null, null));
		tinkinhte.add(new CategoryInfo("Lãi Xuất", "/news/type/"+NewsTypes.LAISUAT, null	, null, null, null));
		tinkinhte.add(new CategoryInfo("Thị Trường Vàng", "/news/type/"+NewsTypes.THITRUONGVANG, null	, null, null, null));
		tinkinhte.add(new CategoryInfo("Tỷ Giá", "/news/type/"+NewsTypes.TYGIA, null	, null, null, null));
		tinkinhte.add(new CategoryInfo("Chứng Khoán", "/news/type/"+NewsTypes.CHUNGKHOAN, null	, null, null, null));
		parentCateMapping.put(NewsTypes.TYPE_ECONOMY, tinkinhte);
		
		List<CategoryInfo> congnghe = new ArrayList<>();
		congnghe.add(new CategoryInfo("CNTT", "/news/type/"+NewsTypes.CNTT, null	, null, null, null));//Công Nghệ Thông Tin
		congnghe.add(new CategoryInfo("Viễn Thông", "/news/type/"+NewsTypes.VIENTHONG, null	, null, null, null));
		congnghe.add(new CategoryInfo("Internet", "/news/type/"+NewsTypes.INTERNET, null	, null, null, null));
		congnghe.add(new CategoryInfo("Thế Giới Số", "/news/type/"+NewsTypes.TGS, null	, null, null, null));
		congnghe.add(new CategoryInfo("Công Nghệ", "/news/type/"+NewsTypes.TYPE_CONGNGHE, null	, null, null, null));
		parentCateMapping.put(NewsTypes.TYPE_CONGNGHE, congnghe);
		
		List<CategoryInfo> sport = new ArrayList<>();
		sport.add(new CategoryInfo("Bóng Đá", "/news/type/"+NewsTypes.BONGDA, null	, null, null, null));
		sport.add(new CategoryInfo("Tennis", "/news/type/"+NewsTypes.TENNIS, null	, null, null, null));
		sport.add(new CategoryInfo("Golf", "/news/type/"+NewsTypes.GOLF, null	, null, null, null));
		sport.add(new CategoryInfo("Hậu Trường", "/news/type/"+NewsTypes.HAUTRUONG, null	, null, null, null));
		parentCateMapping.put(NewsTypes.TYPE_SPORTNEWS, sport);
		
		List<CategoryInfo> ngoisao = new ArrayList<>();
		ngoisao.add(new CategoryInfo("Showbiz Viết", "/news/type/"+NewsTypes.SHOWBIZVIET, null	, null, null, null));
		ngoisao.add(new CategoryInfo("Châu Á", "/news/type/"+NewsTypes.CHAUA, null	, null, null, null));
		ngoisao.add(new CategoryInfo("Hollywood", "/news/type/"+NewsTypes.HOLLYWOOD, null	, null, null, null));
		ngoisao.add(new CategoryInfo("Phong Cách", "/news/type/"+NewsTypes.PHONGCACH, null	, null, null, null));
		ngoisao.add(new CategoryInfo("Bên Lề", "/news/type/"+NewsTypes.BENLE, null	, null, null, null));
		parentCateMapping.put(NewsTypes.NGOISAO, ngoisao);
		
		List<CategoryInfo> suckhoe = new ArrayList<>();
		suckhoe.add(new CategoryInfo("Y Hoc Cổ Truyền", "/news/type/"+NewsTypes.YHOCCOTRUYEN, null	, null, null, null));
		suckhoe.add(new CategoryInfo("Bài Thuốc", "/news/type/"+NewsTypes.BAITHUOC, null	, null, null, null));
		suckhoe.add(new CategoryInfo("Thuốc Quanh Ta", "/news/type/"+NewsTypes.THUOCQUANHTA, null	, null, null, null));
		suckhoe.add(new CategoryInfo("Ẩm Thực", "/news/type/"+NewsTypes.AMTHUC, null	, null, null, null));
		suckhoe.add(new CategoryInfo("Dinh Dưỡng", "/news/type/"+NewsTypes.DINHDUONG, null	, null, null, null));
		parentCateMapping.put(NewsTypes.TYPE_SUCKHOE, suckhoe);
		
		List<CategoryInfo> tinhyeu = new ArrayList<>();
		tinhyeu.add(new CategoryInfo("Tình Yêu", "/news/type/"+NewsTypes.TINHYEU, null	, null, null, null));
		tinhyeu.add(new CategoryInfo("Giới Tính", "/news/type/"+NewsTypes.GIOITINH, null	, null, null, null));
		tinhyeu.add(new CategoryInfo("Gia Đình", "/news/type/"+NewsTypes.GIADINH, null	, null, null, null));
		tinhyeu.add(new CategoryInfo("Góc Tâm Hồn", "/news/type/"+NewsTypes.GOCTAMHON, null	, null, null, null));
		parentCateMapping.put(NewsTypes.TINHYEU, tinhyeu);
		
		List<CategoryInfo> xe = new ArrayList<>();
		xe.add(new CategoryInfo("Diễn Đàn", "/news/type/"+NewsTypes.XE_DIENDAN, null	, null, null, null));
		xe.add(new CategoryInfo("Thị Trường", "/news/type/"+NewsTypes.XE_THITRUONG, null	, null, null, null));
		xe.add(new CategoryInfo("Xe Máy", "/news/type/"+NewsTypes.XEMAY, null	, null, null, null));
		xe.add(new CategoryInfo("Ô Tô", "/news/type/"+NewsTypes.OTO, null	, null, null, null));
		xe.add(new CategoryInfo("Siêu Xe", "/news/type/"+NewsTypes.SIEUXE, null	, null, null, null));
		xe.add(new CategoryInfo("Xe Độ", "/news/type/"+NewsTypes.XEDO, null	, null, null, null));
		parentCateMapping.put(NewsTypes.XE, xe);
		
		List<CategoryInfo> xaydug = new ArrayList<>();
		xaydug.add(new CategoryInfo("Nhà Đẹp", "/news/type/"+NewsTypes.NHADEP, null	, null, null, null));
		xaydug.add(new CategoryInfo("Quy Hoạch", "/news/type/"+NewsTypes.QUYHOACH, null	, null, null, null));
		xaydug.add(new CategoryInfo("Vật Liệu", "/news/type/"+NewsTypes.VATLIEU, null	, null, null, null));
		xaydug.add(new CategoryInfo("Kiến Trúc", "/news/type/"+NewsTypes.KIENTRUC, null	, null, null, null));
		xaydug.add(new CategoryInfo("Bất Động Sản", "/news/type/"+NewsTypes.BATDONGSAN, null	, null, null, null));
		parentCateMapping.put(NewsTypes.XAYDUNG, xaydug);
		
		
	}
	public static CategoryInfo getCateInfo(String cate){
		CategoryInfo c = cateInfo.get(cate);
		if(c == null)
			c = cateInfo.get(NewsTypes.TINTUC);
		return c;
	}
	public static List<MenuItem> getMenuItems(String category){
		return cateMenuItemMapping.get(category);
	}
}
