package com.xyz.hayhay.controller.news;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xyz.hayhay.controller.BaseController;
import com.xyz.hayhay.entirty.Hour;
import com.xyz.hayhay.entirty.Program;
import com.xyz.hayhay.entirty.TVStation;
import com.xyz.hayhay.service.thirdparty.TVChannelService;

@Controller
public class TVController extends BaseController {
	@Autowired
	TVChannelService tvChannelService;

	@RequestMapping(value = "/entertainment/tvprogram", method = RequestMethod.GET)
	public String vtvChannel(String tvstation, ModelMap map,HttpServletRequest request) {
		Map<String, TVStation> tvs = getTVStation();
		List<TVStation> tvStations = new ArrayList<>(tvs.values());
		String layout = "tvprogram";
		String hour = processHour(null);
		if(tvstation == null || tvstation.isEmpty())
			tvstation = "VTV";
		map.put("runningPrograms", tvChannelService.getRunningPrograms(hour, tvstation));
		map.put("tvStations", tvStations);
		map.put("tvstation", tvstation);
		map.put("channels", tvChannelService.getTVStation(tvstation).getChannels());
		map.put("cate", "tvprogram");
		return layout;
	}

	@RequestMapping(value = "/entertainment/tvprogram/filter", method = RequestMethod.GET)
	public String vtvPrograms(Integer id, String tvstation, String chanelName,ModelMap map, HttpServletRequest request) {
		List<Program> programs = tvChannelService.getPrograms(id);
		map.put("runningPrograms", programs);
		Map<String, TVStation> tvs = getTVStation();
		List<TVStation> tvStations = new ArrayList<>(tvs.values());
		map.put("tvStations", tvStations);
		map.put("channels", tvChannelService.getTVStation(tvstation).getChannels());
		map.put("channel", chanelName);
		map.put("tvstation", tvstation);
		return "tvprogram";
	}
	
	private Map<String, TVStation> getTVStation() {
		Map<String, TVStation> tvs = new HashMap<String, TVStation>();
		TVStation vtv = new TVStation(0, "VTV");
		vtv.setLogo("/images/icons/vtv.jpg");
		vtv.setDesc("Lịch phát sóng trên VTV");
		tvs.put(vtv.getName(), vtv);

		TVStation vtvCab = new TVStation(1, "VTVCab");
		vtvCab.setLogo("/images/icons/vtvcab.jpg");
		vtvCab.setDesc("Lịch phát sóng trên VTVCab");
		tvs.put(vtvCab.getName(), vtvCab);

		TVStation htv = new TVStation(2, "HTVC");
		htv.setLogo("/images/icons/htvc.jpg");
		htv.setDesc("Lịch phát sóng trên HTV và HTVC");
		tvs.put(htv.getName(), htv);

		TVStation thhn = new TVStation(3, "HNTV");
		thhn.setLogo("/images/icons/thhn.jpg");
		thhn.setDesc("Lịch phát sóng đài truyền hình Hà Nôi");
		tvs.put(thhn.getName(), thhn);

		return tvs;
	}

	public List<Hour> getHours(int selectedHour) {
		List<Hour> hours = new ArrayList<>();
		for (int i = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); i <= 23; i++) {
			Hour h = new Hour();
			h.setHour(i);
			if (i == selectedHour)
				h.setSelected(true);
			hours.add(h);
		}
		return hours;
	}

	private String processHour(String hour) {
		if (hour == null || hour.isEmpty()) {
			hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "";
		} else {
			try {
				Integer.parseInt(hour);
			} catch (Exception e) {
				hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) + "";
			}
		}

		if (Calendar.getInstance().get(Calendar.HOUR_OF_DAY) < 10 && hour.length() < 2)
			hour = "0" + hour;
		return hour;
	}

	public static void main(String[] args) {
		String a = "Xem truyền hình trực tiếp kênh VTV9";
		System.out.println(a.substring(a.indexOf("VTV"), a.length()));
	}
}
