package com.qmm.integrate.cache.impl;

import com.qmm.integrate.cache.EhCacheBiz;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Service;

@Service("ehCacheBiz")
public class EhCacheBizImpl implements EhCacheBiz {
	
	@Autowired
	CacheManager cacheManager;

	@Override
	public void put(String key, Object value) {
		Cache cache = getEhCache();
		Element element = new Element(key, value);
		cache.put(element);
	}

	@Override
	public void putAndFlush(String key, Object value) {
		Cache cache = getEhCache();
		Element element = new Element(key, value);
		cache.put(element);
		cache.flush();
	}

	@Override
	public void putSpecifiedTimeAndFlush(String key, Object value, int seconds) {
		Cache cache = getEhCache();
		Element element = new Element(key, value);
		element.setTimeToLive(seconds);
		cache.put(element);
		cache.flush();
	}

	@Override
	public Object get(String key) {
		Cache cache = getEhCache();
		Element element = cache.get(key);
		return element == null ? null : element.getObjectValue();
	}

	@Override
	public void remove(String key) {
		Cache cache = getEhCache();
		cache.remove(key);
	}

	@Override
	public Cache getEhCache(){
		return getEhCache("sysCache");
	}
	
	private Cache getEhCache(String cacheName){
		EhCacheCacheManager ehCacheCacheManager = (EhCacheCacheManager) cacheManager;
		net.sf.ehcache.CacheManager ehCacheManager = ehCacheCacheManager.getCacheManager();
		Cache cache = ehCacheManager.getCache(cacheName);
		return cache;
	}

}
