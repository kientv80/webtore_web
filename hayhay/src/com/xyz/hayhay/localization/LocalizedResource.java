package com.xyz.hayhay.localization;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import com.xyz.hayhay.util.ConfigurationHelper;

public class LocalizedResource {

	public static final String VI_VN = "vi_VN";
	public static final String EN_ENGLISH = "en_english";
	private static LocalizedResource instance;
	Properties vnResources;
	Properties enResources;
	public static LocalizedResource getInstance(){
		if(instance == null)
			try {
				instance = new LocalizedResource();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		return instance;
	}
	private LocalizedResource() throws FileNotFoundException, IOException{
		vnResources = new Properties();
		vnResources.load(new FileInputStream(new File(ConfigurationHelper.getInstance().getValue("appDir")+"/resources/string.properties")));
		enResources = new Properties();
		enResources.load(new FileInputStream(new File(ConfigurationHelper.getInstance().getValue("appDir")+"/resources/string_en.properties")));
	}
	public String getValue(String key, String locale){
		if(locale != null && VI_VN.equalsIgnoreCase(locale.trim())){
			return vnResources.getProperty(key);
		}else{
			return enResources.getProperty(key);
		}
	}
	
}
