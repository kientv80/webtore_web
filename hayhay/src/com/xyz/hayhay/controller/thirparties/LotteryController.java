package com.xyz.hayhay.controller.thirparties;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.service.thirdparty.LotteryService;

@Controller
public class LotteryController extends BaseController {
	SimpleDateFormat df = new SimpleDateFormat("d-M-yyyy");
	public static Map<String, String> nameMapping = new LinkedHashMap<>();
	static {
		nameMapping.put("mien-bac-xsmb", "Miền Bắc");
		nameMapping.put("an-giang-xsag", "An Giang");
		nameMapping.put("binh-duong-xsbd", "Bình Dương");
		nameMapping.put("binh-dinh-xsbdi", "Bình Định");
		nameMapping.put("bac-lieu-xsbl", "Bạc Liêu");
		nameMapping.put("binh-phuoc-xsbp", "Bình Phước");
		nameMapping.put("ben-tre-xsbt", "Bến Tre");
		nameMapping.put("binh-thuan-xsbth", "Bình Thuận");
		nameMapping.put("ca-mau-xscm", "Cà Mau");
		nameMapping.put("can-tho-xsct", "Cần Thơ");
		nameMapping.put("dac-lac-xsdlk", "Đắc Lắc");
		nameMapping.put("dong-nai-xsdn", "Đồng Nai");
		nameMapping.put("da-nang-xsdng", "Đà Nẳng");
		nameMapping.put("dac-nong-xsdno", "Đắc Nông");
		nameMapping.put("dong-thap-xsdt", "Đống Tháp");
		nameMapping.put("gia-lai-xsgl", "Gia Lai");
		nameMapping.put("tp-hcm-xshcm", "TP Hồ Chí Minh");
		nameMapping.put("hau-giang-xshg", "Hậu Giang");
		nameMapping.put("kien-giang-xskg", "Kiên Giang");
		nameMapping.put("khanh-hoa-xskh", "Khánh Hòa");
		nameMapping.put("kon-tum-xskt", "Kon Tum");
		nameMapping.put("long-an-xsla", "Long An");
		nameMapping.put("lam-dong-xsld", "Lâm Đồng");
		nameMapping.put("ninh-thuan-xsnt", "Ninh Thận");
		nameMapping.put("phu-yen-xspy", "Phú Yên");
		nameMapping.put("quang-binh-xsqb", "Quảng Bình");
		nameMapping.put("quang-ngai-xsqng", "Quảng Ngãi");
		nameMapping.put("quang-nam-xsqnm", "Quảng Nam");
		nameMapping.put("quang-tri-xsqt", "Quảng Trị");
		nameMapping.put("soc-trang-xsst", "Sóc Trăng");
		nameMapping.put("tien-giang-xstg", "Tiền Giang");
		nameMapping.put("tay-ninh-xstn", "Tây Ninh");
		nameMapping.put("thua-thien-hue-xstth", "Thừa Thiên Huế");
		nameMapping.put("tra-vinh-xstv", "Trà Vinh");
		nameMapping.put("vinh-long-xsvl", "Vĩnh Long");
		nameMapping.put("vung-tau-xsvt", "Vũng Tầu");

	}

	@RequestMapping(value = "/webstore/lottery", method = RequestMethod.GET)
	public String lottery(ModelMap map, HttpServletResponse resp) {
		try {
			Date d = new Date(Calendar.getInstance().getTimeInMillis());
			int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
			if (hour <= 18) {
				Calendar dd = Calendar.getInstance();
				dd.add(Calendar.DAY_OF_MONTH, -1);
				d = new Date(dd.getTimeInMillis());
			}
			JSONArray rs = LotteryService.getLottery(d, "");
			map.put("date", df.format(d));
			map.put("days", get30Days());
			map.put("data", rs);
			map.put("agency", nameMapping);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "lottery";
	}

	@ResponseBody
	@RequestMapping(value = "/webstore/json/lottery", method = RequestMethod.GET)
	public void lotteryJson(String date, String agency, HttpServletResponse resp, ModelMap map) {
		try {
			Date d = null;
			if (!date.isEmpty()) {
				d = new Date(df.parse(date).getTime());
			}
			JSONArray rs = LotteryService.getLottery(d, agency);
			writeJSONArrayResponse(resp, rs);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private List<String> get30Days() {
		List<String> days = new ArrayList<>();

		Calendar c = Calendar.getInstance();
		for (int i = 0; i < 30; i++) {
			days.add(df.format(c.getTime()));
			c.add(Calendar.DAY_OF_MONTH, -1);
		}
		return days;
	}

	public static void main(String[] args) {
		DateFormat df = new SimpleDateFormat("d-M-yyyy", Locale.ENGLISH);
		try {
			System.out.println(df.parse("2-8-2016"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
