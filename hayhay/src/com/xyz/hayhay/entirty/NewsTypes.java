package com.xyz.hayhay.entirty;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class NewsTypes {
	public static enum CATEGORY {HotNews,Economic,Business,Tech,Politics,Science,Opinion,Realty,Sport,Health,CarAndMotor,Entertainment,Cooking,Love,Funny };
	public static enum TYPE{HotNews,
		Economic,Finance,Budget,Banking,Gold,Stock,
		Figure,Management,Job,Trademark,Experience,Company,Business,
		Football,Tennis,Golf, UFC,
		IT,Internet,Telecom,Tech,
		FamousPerson,Style,Fashion,BehindTheScenes,
		Funny,
		Health,HealthyFood,MedicineFood,
		CookingPig,CookingHen,CookingShrimp,CookingEgg,CookingVegetable,CookingCow,CookingFish,CookingVegetarian,CookingItaly,CookingKorea,CookingJapan,CookingThai,CookingFrance,
		Science,Politics,Opinion,
		Car,Motor,AdjustCar,SupperCar,
		Realty,Building,Zoing,Architecture,BeautifulHouse, Material,
		StatUp,Love,Family,MomAndBaby,Soul,Gender, Sport}
	public static Map<CATEGORY,List<TYPE>> cateTypeMapping = new HashMap<>();
	static{
		cateTypeMapping.put(CATEGORY.HotNews, Arrays.asList(new TYPE[]{TYPE.HotNews,TYPE.Politics,TYPE.Opinion,TYPE.Business, TYPE.Economic,TYPE.Tech,TYPE.Science,TYPE.Realty}));
		cateTypeMapping.put(CATEGORY.Economic, Arrays.asList(new TYPE[]{TYPE.Economic,TYPE.Finance,TYPE.Budget,TYPE.Banking,TYPE.Gold}));
		cateTypeMapping.put(CATEGORY.Business, Arrays.asList(new TYPE[]{TYPE.Figure,TYPE.Management,TYPE.Job,TYPE.Trademark,TYPE.Experience,TYPE.Company, TYPE.StatUp}));
		cateTypeMapping.put(CATEGORY.Sport, Arrays.asList(new TYPE[]{TYPE.Football,TYPE.Tennis,TYPE.Golf,TYPE.UFC,TYPE.Sport}));
		cateTypeMapping.put(CATEGORY.Tech, Arrays.asList(new TYPE[]{ TYPE.IT,TYPE.Internet,TYPE.Telecom,TYPE.Tech}));
		cateTypeMapping.put(CATEGORY.Entertainment, Arrays.asList(new TYPE[]{TYPE.FamousPerson,TYPE.Style,TYPE.Fashion,TYPE.BehindTheScenes}));
		cateTypeMapping.put(CATEGORY.Funny, Arrays.asList(new TYPE[]{TYPE.Funny}));
		cateTypeMapping.put(CATEGORY.Health, Arrays.asList(new TYPE[]{TYPE.Health,TYPE.HealthyFood,TYPE.MedicineFood}));
		cateTypeMapping.put(CATEGORY.Cooking, Arrays.asList(new TYPE[]{TYPE.CookingCow,TYPE.CookingEgg,TYPE.CookingFish,TYPE.CookingFrance,TYPE.CookingHen,TYPE.CookingJapan,TYPE.CookingKorea,TYPE.CookingPig,TYPE.CookingShrimp,TYPE.CookingThai,TYPE.CookingVegetarian,TYPE.CookingItaly}));
		cateTypeMapping.put(CATEGORY.CarAndMotor, Arrays.asList(new TYPE[]{TYPE.Car,TYPE.Motor,TYPE.AdjustCar,TYPE.SupperCar}));
		cateTypeMapping.put(CATEGORY.Realty, Arrays.asList(new TYPE[]{TYPE.Realty,TYPE.Building,TYPE.Zoing,TYPE.Architecture,TYPE.BeautifulHouse,TYPE.Material}));
		cateTypeMapping.put(CATEGORY.Love, Arrays.asList(new TYPE[]{TYPE.Love,TYPE.MomAndBaby,TYPE.Soul,TYPE.Family,TYPE.Gender}));
		
		cateTypeMapping.put(CATEGORY.Science, Arrays.asList(new TYPE[]{TYPE.Science}));
		cateTypeMapping.put(CATEGORY.Opinion, Arrays.asList(new TYPE[]{TYPE.Opinion}));
		cateTypeMapping.put(CATEGORY.Politics, Arrays.asList(new TYPE[]{TYPE.Politics}));
		
	
	}
	
	public static String DINHDUONG = "DINHDUONG";
	public static final String TYPE_TINTRONGNUOC="TINTRONGNUOC";
	public static final String TYPE_TINQUOCTE="TINQUOCTE";
	public static final String TYPE_ECONOMY="ECONOMY";
	public static final String TYPE_KINHTE="KINHTE";
	public static final String TYPE_SPORTNEWS="SPORTNEWS";
	public static final String TYPE_CONGNGHE="CONGNGHE";
	public static final String NGOISAO="NGOISAO";
	public static final String TYPE_SUCKHOE="SUCKHOE";
	public static final String TYPE_VIECLAM="VIECLAM";
	public static final String TYPE_TINHYEU="TINHYEU";
	public static final String TYPE_MUSIC = "MUSIC";
	public static final String TYPE_MOVIE = "movie_news";
	
	public static final String WN_HOME = "WN_HOME";
	public static final String WN_TECH = "WN_TECH";
	public static final String WN_SIENCE = "WN_SIENCE";
	public static final String WN_BIZ = "WN_BIZ";
	public static final String WN_HEALTH = "WN_HEALTH";
	public static final String WN_POLITICS = "WN_POLITICS";
	
	public static final String HUONGTHIEN = "HUONGTHIEN";
	public static final String SOFTSKILLS = "SOFTSKILLS";
	
	public static final String YHOCCOTRUYEN = "YHOCCOTRUYEN";
	public static final String BAITHUOC = "BAITHUOC";
	public static final String THUOCQUANHTA = "THUOCQUANHTA";
	public static final String AMTHUC = "AMTHUC";
	public static final String HOCKTVMVM="HOCKTVMVM";
	public static final String HOCTAICHINH="HOCTAICHINH";
	public static final String MMALEARNING="MMALEARNING";
	public static final String KICKBOXINGLEARNING="KICKBOXINGLEARNING";
	public static final String BOXING="BOXING";
	public static final String JUDOLEARNING="JUDOLEARNING";
	public static final String YOGALEARNING="YOGALEARNING";
	public static final String TAICHINH = "TAICHINH";
	public static final String NGANHANG = "NGANHANG";
	public static final String NGANSACH = "NGANSACH";
	public static final String THITRUONGVANG = "THITRUONGVANG";
	public static final String TYGIA = "TYGIA";
	public static final String CHUNGKHOAN = "CHUNGKHOAN";
	public static final String LAISUAT = "LAISUAT";
	
	public static final String NHACVANG = "NHACVANG";
	public static final String NHACDO = "NHACDO";
	public static final String NHACTRE = "NHACTRE";
	public static final String NHACTHIEUNHI = "NHACTHIEUNHI";
	public static final String NHACKHONGLOI = "NHACKHONGLOI";
	public static final String NHACNUOCNGOAI = "NHACNUOCNGOAI";
	public static final String NHACTET = "NHACTET";
	
	public static final String KN_GIAOTIEP = "kngiaotiep";
	public static final String KN_THUYETTRINH = "knthuyettrinh";
	public static final String KN_QL_THOIGIAN = "knquanlythoigian";
	public static final String KN_LAMVIEC_NHOM = "knlamviecnhom";
	public static final String KN_BAN_HANG = "knbanhang";
	public static final String KN_MARKETING_ONLINE = "marketingonline";
	public static final String KN_DAMPHAN_THUONGLUONG = "kndamphanthuongluong";
	public static final String KN_GIAIQUYET_VANDE = "kngiaiquyetvande";
	
	public static final String knqlcamsuc = "knqlcamsuc";
	public static final String knphonvan = "knphonvan";
	public static final String kntuduysangtao = "kntuduysangtao";
	public static final String kntuhoc = "kntuhoc";
	public static final String kntaodongluc = "kntaodongluc";
	public static final String knthichnghi = "knthichnghi";
	public static final String kndhnn = "kndhnn";
	public static final String thuongmaidt = "thuongmaidt";
	public static final String knquanlylanhdao = "knquanlylanhdao";
	public static final String knsoanthaovb = "knsoanthaovb";
	public static final String kntaichinhdoannghiep = "kntaichinhdoannghiep";
	public static final String kntaichinhcanhan = "kntaichinhcanhan";
	public static final String knphanquyenvauythaccv = "knphanquyenvauythaccv";
	public static final String knqlhoso = "knqlhoso";
	public static final String vhdoanhnghiep = "vhdoanhnghiep";
	
	
	public static final String BONGDA = "BONGDA";
	public static final String TENNIS = "TENNIS";
	public static final String GOLF = "GOLF";
	public static final String HAUTRUONG = "HAUTRUONG";
	public static final String THETHEO_VIDEO = "THETHEO_VIDEO";
	
	public static final String SHOWBIZVIET = "SHOWBIZVIET";
	public static final String CHAUA = "CHAUA";
	public static final String HOLLYWOOD = "HOLLYWOOD";
	public static final String PHONGCACH = "PHONGCACH";
	public static final String BENLE = "BENLE";
	
	public static final String TINHYEU = "TINHYEU";
	public static final String GIOITINH = "GIOITINH";
	public static final String GIADINH = "GIADINH";
	public static final String GOCTAMHON = "GOCTAMHON";
	public static final String NHACMOI = "NHACMOI";
	public static final String NHACTREMOI = "NHACTREMOI";
	public static final String NHACTRUTINH = "NHACTRUTINH";
	public static final String NHACTRINH = "NHACTRINH";
	public static final String ROCKVIET = "ROCKVIET";
	public static final String THIEUNHI = "THIEUNHI";
	public static final String VIDEO = "VIDEO";
	public static final String CNTT = "CNTT";
	public static final String VIENTHONG = "VIENTHONG";
	public static final String INTERNET = "INTERNET";
	public static final String TGS = "TGS";
	public static final String KHOINGHIEP = "KHOINGHIEP";
	public static final String BAITHUOCHAY = "BAITHUOCHAY";
	public static final String XE = "XE";
	public static final String OTO = "OTO";
	public static final String XEDO = "XEDO";
	public static final String SIEUXE = "SIEUXE";
	public static final String XE_DIENDAN = "XE_DIENDAN";
	public static final String XE_THITRUONG = "XE_THITRUONG";
	public static final String XEMAY = "XEMAY";
	
	public static final String MUABANNHADAT = "MUABANNHADAT";
	public static final String BDS_HCM = "BDS_HCM";
	public static final String BDS_HN = "BDS_HN";
	public static final String BDS_DN = "BDS_DN";
	public static final String BDS_VT = "BDS_VT";
	public static final String FUNYSTORY = "funystory";

	public static final String MOVIE_NEWS = "movie_news";
	public static final String MOVIE_PLAYING = "movie_playing";
	public static final String MOVIE_COMMING = "movie_comming";
	public static final String MOVIE_DISCOUNT = "movie_discount";
	
	public static final String NAUAN_HEO = "nauan_thitheo";
	public static final String NAUAN_GA = "nauan_thitga";
	public static final String NAUAN_BO = "nauan_thitbo";
	public static final String NAUAN_MUC = "nauan_muc";
	public static final String NAUAN_TOM = "nauan_tom";
	public static final String NAUAN_CA = "nauan_ca";
	public static final String NAUAN_HAISAN = "nauan_haisan";
	public static final String NAUAN_NAM = "nauan_nam";
	public static final String NAUAN_DAUHU = "nauan_dauhu";
	public static final String NAUAN_HAISANKHAC = "nauan_haisankhac";
	public static final String NAUAN = "nauan";
	
	
	
	public static final String DIA_OC = "diaoc";
	public static final String NHADEP = "nhadep";
	public static final String QUYHOACH = "quyhoach";
	public static final String VATLIEU = "vatlieu";
	public static final String KIENTRUC = "kientruc";
	public static final String XAYDUNG = "xaydung";
	public static final String BATDONGSAN = "batdongsan";
	public static final String CHINHTRIXAHOI = "CHINHTRIXAHOI";
	public static final String THOISU = "THOISU";
	public static final String PHONGSU = "PHONGSU";
	public static final String CHUYENTHUONGNGAY = "CHUYENTHUONGNGAY";
	public static final String TIEUDIEM ="TIEUDIEM";
	public static final String PHAPLUAT = "PHAPLUAT";
	public static final String NHIPSONGTRE = "NHIPSONGTRE";
	public static final String MOITRUONG = "MOITRUONG";
	public static final String NHACAUMY = "NHACAUMY";
	public static final String NHACRAP = "NHACRAP";
	public static final String FILM_HANHDONG = "FILM_HANHDONG";
	public static final String FILM_HAIHUOC = "FILM_HAIHUOC";
	public static final String FILM_VIENTUONG = "FILM_VIENTUONG";
	public static final String FILM_VOTHUAT = "FILM_VOTHUAT";
	public static final String FILM_THANTHOAI = "FILM_THANTHOAI";
	public static final String FILM_NHAC = "FILM_NHAC";
	public static final String FILM_CHIENTRANH = "FILM_CHIENTRANH";
	public static final String FILM_KINHDI = "FILM_KINHDI";
	public static final String FILM_HOATHINH = "FILM_HOATHINH";
	public static final String FILM_PHIEULUU = "FILM_PHIEULUU";
	public static final String FILM_TINHCAM = "FILM_TINHCAM";
	public static final String TYPE_FILM = "TYPE_FILM";
	public static final String ENTERTAINMENT = "entertainment";
	
	public static final String NHAN_VAT = "NHAN_VAT";
	public static final String QUAN_TRI = "QUAN_TRI";
	public static final String NGHE_NGHIEP = "NGHE_NGHIEP";
	public static final String THUONG_HIEU = "THUONG_HIEU";
	public static final String KINH_NHIEM_KINH_DOANH = "KINH_NHIEM_KINH_DOANH";
	public static final String KINH_DOANH = "KINH_DOANH";
	public static final String CHINHSACH = "CHINH_SACH";
	public static final String DOANH_NGHIEP = "DOANH_NGHIEP";
	public static final String NHAC_FILMS = "nhacphim";
	public static final String LETTERY = "lottery";
	public static final String NAUAN_RAUCU = "raucu";
	public static final String NAUAN_TRUNG = "trung";
	public static final String NAUAN_MONY = "mony";
	public static final String NAUAN_MONHAN = "monhan";
	public static final String NAUAN_MONNHAT = "monnhat";
	public static final String NAUAN_MONTHAI = "monthai";
	public static final String NAUAN_MONPHAP = "monphap";
	public static final String NAUAN_CACNUOC = "nauancacnuoc";
	public static final String MEVABE = "mevabe";
	
}
