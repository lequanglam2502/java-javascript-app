package com.colin.app.config.cache;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.config.CacheConfiguration;

@Configuration
@EnableCaching
public class EhCacheConfig {
	@Bean
    public EhCacheManagerFactoryBean cacheManagerFactory() {
        return new EhCacheManagerFactoryBean();
    }
     
    @Bean
    public EhCacheCacheManager generalEhCacheManager() {
    	CacheManager cacheManager = cacheManagerFactory().getObject();
    	
    	/**
    	 * this is the config time and evict policy for storing 
    	 * item list in EhCache, you can create bean config by ehcache.xml file
    	 * enable it if you want to use
    	 */
        CacheConfiguration itemListConfig = new CacheConfiguration()
            .eternal(false)                     // if true, timeouts are ignored
            .timeToIdleSeconds(30)            // time since last accessed before item is marked for removal
            .timeToLiveSeconds(60)            // time since inserted before item is marked for removal
            .maxEntriesLocalHeap(1000)           // total items that can be stored in cache
            .memoryStoreEvictionPolicy("LRU")   // eviction policy for when items exceed cache. LRU = Least Recently Used, LFU, FIFO
            .name("itemListEhCache");
        cacheManager.addCache(new Cache(itemListConfig));
        
        /**
    	 * this is the config time and evict policy for storing 
    	 * username list in EhCache, you can create bean config by ehcache.xml file
    	 */
        CacheConfiguration userNameConfig = new CacheConfiguration()
            .eternal(false)                     // if true, timeouts are ignored
            .timeToIdleSeconds(60)            // time since last accessed before item is marked for removal
            .timeToLiveSeconds(120)            // time since inserted before item is marked for removal
            .maxEntriesLocalHeap(1000)           // total items that can be stored in cache
            .memoryStoreEvictionPolicy("LRU")   // eviction policy for when items exceed cache. LRU = Least Recently Used, LFU, FIFO
            .name("usernameEhCache");
        cacheManager.addCache(new Cache(userNameConfig));

        
        return new EhCacheCacheManager(cacheManager);
    }
    
}
