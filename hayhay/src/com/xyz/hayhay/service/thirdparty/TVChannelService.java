package com.xyz.hayhay.service.thirdparty;

import java.util.List;

import com.xyz.hayhay.entirty.Program;
import com.xyz.hayhay.entirty.TVStation;

public interface TVChannelService {
	public TVStation getTVStation(String stationName);
	public List<Program> getPrograms(int channelId);
	public List<Program> getRunningPrograms(String hour, String tv);
}
