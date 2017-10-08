package com.xyz.hayhay.cache;

import org.apache.jcs.JCS;
import org.apache.jcs.access.exception.CacheException;
import org.apache.log4j.Logger;

public class JCSCacheClient {
	Logger log = Logger.getLogger(JCSCacheClient.class);
	private JCS cache;
	public JCSCacheClient(JCS cache) {
		this.cache = cache;
	}
	public Object get(Object key){
		return cache.get(key);
	}
	public void put(Object key, Object value){
		try {
			cache.put(key, value);
		} catch (CacheException e) {
			log.error("",e);
		}
	}
	public void clear() throws CacheException{
		cache.clear();
	}
	public void remove(Object key) throws CacheException{
		cache.remove(key);
	}
}
