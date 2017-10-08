package com.xyz.webstore.mobile.config;

import java.util.HashMap;
import java.util.Map;

public class MobileAppConfig {
	private Map<String,String> appConfig = new HashMap<>();
	private String currentVersion = "1.0";
	public void addConfig(String key,String value){
		appConfig.put(key, value);
	}
}
