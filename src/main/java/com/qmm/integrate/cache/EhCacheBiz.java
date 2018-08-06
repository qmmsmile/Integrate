package com.qmm.integrate.cache;

import net.sf.ehcache.Cache;

public interface EhCacheBiz {

	void put(String key, Object value);
	
	void putAndFlush(String key, Object value);
	
	void putSpecifiedTimeAndFlush(String key, Object value, int seconds);
	
	Object get(String key);
	
	void remove(String key);
	
	Cache getEhCache();
	
}
