package com.xyz.hayhay.socialplugin;

public class SocialServiceFactory {
	public static FaceBookPluginService getFaceBookService(){
		return FaceBookPluginServiceImpl.getInstance();
	}
}
