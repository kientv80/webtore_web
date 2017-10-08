package com.xyz.hayhay.cache;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;

public class JSCCacheManager {

	Logger log = Logger.getLogger(JSCCacheManager.class);
	private static JSCCacheManager instance;

	private JSCCacheManager() {
	}

	public static JSCCacheManager getInstace() {
		synchronized (JSCCacheManager.class) {
			if (instance == null)
				instance = new JSCCacheManager();
		}
		return instance;
	}
	public JCSCacheClient getCache(String region){
		try {
			return new JCSCacheClient(JCS.getInstance(region));
		} catch (CacheException e) {
			e.printStackTrace();
		}
		return null;
	}

}
