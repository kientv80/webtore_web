package com.xyz.hayhay.db.dummydata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.xyz.hayhay.entirty.CategoryInfo;
import com.xyz.hayhay.entirty.WebsiteInfo;

public class DummyData {
	public static List<CategoryInfo> webstores = new ArrayList<>();
	public static Map<String,List<CategoryInfo>> categories = new HashMap<>();
	static {
		//	Init webstore
		CategoryInfo cateNews = new CategoryInfo("news", "http://360hay.com/news/tintuc", "", "Tin Tức", "", "");
		CategoryInfo tech = new CategoryInfo("tech", "http://360hay.com/news/tintuc", "", "Công Nghệ", "", "");
		CategoryInfo sport = new CategoryInfo("sport", "http://360hay.com/news/tintuc", "", "Thể Thao", "", "");
		CategoryInfo entertainment = new CategoryInfo("entertainment", "http://360hay.com/news/tintuc", "", "Giải Trí", "", "");
		webstores.add(cateNews);
		webstores.add(tech);
		webstores.add(sport);
		webstores.add(entertainment);

		cateNews.getWebsites().add(new WebsiteInfo("Tuổi Trẻ", "Tuổi Trẻ", "/images/website_icon/tuoitre.jpg", "", "http://tuoitre.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("VnExpress", "VnExpress", "/images/website_icon/vnexpress.png", "", "http://vnexpress.net/"));
		cateNews.getWebsites().add(new WebsiteInfo("Báo Xây Dựng", "Báo Xây Dựng", "/images/website_icon/xaydung.png", "", "http://www.baoxaydung.com.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("ZingNews", "ZingNews", "/images/website_icon/zingnews.jpg", "", "http://news.zing.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("VnEconomy", "VnEconomy", "/images/website_icon/vneconomy.png", "", "http://m.vneconomy.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("Bizlive", "Bizlive", "/images/website_icon/bizlive.png", "", "http://m.bizlive.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("Petrotimes", "Petrotimes", "/images/website_icon/petrotime.png", "", "http://petrotimes.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("VietNamWork", "VietNamWork", "/images/website_icon/vietnamwork.png", "", "http://www.vietnamworks.com/"));
		cateNews.getWebsites().add(new WebsiteInfo("Sức Khỏe Gia Đình", "Sức Khỏe Gia Đình", "/images/website_icon/suckhoegiadinh.jpg", "", "http://m.suckhoegiadinh.com.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("Afamily", "Afamily", "/images/website_icon/afamily.png", "", "http://m.afamily.vn/"));
		cateNews.getWebsites().add(new WebsiteInfo("Ngôi Sao", "Ngôi Sao", "/images/website_icon/ngoisaonet.jpg", "", "http://ngoisao.net/"));
		
		
		tech.getWebsites().add(new WebsiteInfo("ICTNews", "ICTNews", "/images/website_icon/ictnews.png", "", "http://ictnews.vn/"));
		tech.getWebsites().add(new WebsiteInfo("Tinh Tế", "Tinh Tế", "/images/website_icon/tinhte.png", "", "https://tinhte.vn/"));
		tech.getWebsites().add(new WebsiteInfo("Techz", "Techz", "/images/website_icon/techz.png", "", "http://www.techz.vn/"));
		tech.getWebsites().add(new WebsiteInfo("PCWorld", "PCWorld", "/images/website_icon/pcworld.png", "", "http://m.pcworld.com.vn/"));
		
		entertainment.getWebsites().add(new WebsiteInfo("Thế Giới Phim", "Thế Giới Phim", "/images/website_icon/thegioiphim.gif", "", "http://www.thegioiphim.com/"));
		entertainment.getWebsites().add(new WebsiteInfo("Phim Mới", "Phim Mới", "/images/website_icon/phimmoi.png", "", "http://www.phimmoi.net/"));
		entertainment.getWebsites().add(new WebsiteInfo("HDViet", "HDViet", "/images/website_icon/hdviet.png", "", "http://movies.hdviet.com/"));
		entertainment.getWebsites().add(new WebsiteInfo("Nhạc Của Tui", "Nhạc Của Tui", "/images/website_icon/nhaccuatui.jpg", "", "http://www.nhaccuatui.com/"));
		entertainment.getWebsites().add(new WebsiteInfo("Zing MP3", "Zing MP3", "/images/website_icon/zingmp3.png", "", "http://mp3.zing.vn/"));
		entertainment.getWebsites().add(new WebsiteInfo("Nhac.vn", "Nhac.vn", "/images/website_icon/nhac.png", "", "http://nhac.vn/"));
		
		sport.getWebsites().add(new WebsiteInfo("Thể Thao TV", "Thể Thao TV", "/images/website_icon/thethaotv.png", "", "http://thethaotv.vn/"));
		sport.getWebsites().add(new WebsiteInfo("Thể Thao 24h", "Thể Thao 24h", "/images/website_icon/thethao24h.png", "", "http://m.24h.com.vn/"));
		sport.getWebsites().add(new WebsiteInfo("Bóng Đá 24h", "Bóng Đá 24h", "/images/website_icon/bongda24h.png", "", "http://bongda24h.vn/"));
		sport.getWebsites().add(new WebsiteInfo("Thể Thao 24/7", "Thể Thao 24/7", "/images/website_icon/thethao247.png", "", "http://m.thethao247.vn/"));
		//Init category
		List<CategoryInfo> newsCategory = new ArrayList<>();
		List<CategoryInfo> cateEntertainment = new ArrayList<>();
		List<CategoryInfo> musicFilms = new ArrayList<>();
		List<CategoryInfo> webapps = new ArrayList<>();
		categories.put("news", newsCategory);
		categories.put("entertainment", cateEntertainment);
		categories.put("nhacphim", musicFilms);
		categories.put("webapps", webapps);
		newsCategory.add(new CategoryInfo("Tin Tổng Hợp", "/news/tintuc", "/images/icons/category/ic_news.png", "", "", ""));
		newsCategory.add(new CategoryInfo("Kinh Tế", "/news/kinhte", "/images/icons/category/ic_economy.png", "", "", ""));
		newsCategory.add(new CategoryInfo("Kinh Doanh", "/news/KINH_DOANH", "/images/icons/category/ic_kinhdoanh.png", "", "", ""));
		newsCategory.add(new CategoryInfo("Công Nghệ", "/news/congnghe", "/images/icons/category/ic_tech.png", "", "", ""));
		newsCategory.add(new CategoryInfo("Thể Thao", "/news/thethao", "/images/icons/category/ic_sport.png", "", "", ""));
		newsCategory.add(new CategoryInfo("Ô tô/Xe máy", "/news/xe", "/images/icons/category/ic_xe.png", "", "", ""));
		newsCategory.add(new CategoryInfo("Nhà Đất", "/news/batdongsan", "/images/icons/category/ic_building.png", "", "", ""));
		newsCategory.add(new CategoryInfo("Khởi Nghiệp", "/news/KHOINGHIEP", "/images/icons/category/ic_store.png", "", "", ""));
		
		cateEntertainment.add(new CategoryInfo("Ngôi Sao", "/news/ngoisao", "/images/icons/category/ic_ngoisao.png", "", "", ""));
		cateEntertainment.add(new CategoryInfo("Tình Yêu", "/news/tinhyeu", "/images/icons/category/ic_love.png", "", "", ""));
		cateEntertainment.add(new CategoryInfo("Chuyện Cười", "/news/funystory", "/images/icons/category/ic_smile.png", "", "", ""));
		
		musicFilms.add(new CategoryInfo("Nghe Nhạc", "/news/nhac", "/images/icons/category/ic_music.png", "", "", ""));
		musicFilms.add(new CategoryInfo("Xem Phim", "/news/film", "/images/icons/category/ic_film.png", "", "", ""));
		musicFilms.add(new CategoryInfo("Phim Chiếu Rạp", "/news/movie", "/images/icons/category/ic_cenima.png", "", "", ""));
		musicFilms.add(new CategoryInfo("Lịch Phát Sóng TV", "/entertainment/tvprogram", "/images/icons/category/ic_tv.png", "", "", ""));
		
		webapps.add(new CategoryInfo("Kết Quả Xổ Số", "/webstore/lottery", "/images/icons/category/ic_lottery.png", "", "", ""));
		webapps.add(new CategoryInfo("Lịch Phát Sóng TV", "/entertainment/tvprogram", "/images/icons/category/ic_tv.png", "", "", ""));
		webapps.add(new CategoryInfo("Từ Điển La Bàn", "http://m.dict.laban.vn/", "/images/icons/category/ic_dictionary.png", "", "", ""));
		
	}
}
