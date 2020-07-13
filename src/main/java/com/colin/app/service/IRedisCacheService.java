package com.colin.app.service;

import java.util.concurrent.TimeUnit;

/**
 * This is the general Redis cache service
 * To use it, just put get, set and delete
 * to get, put and evict to the Redis cache
 * 
 * @author colinle
 *
 * @param <K>
 * @param <V>
 */
public interface IRedisCacheService<K, V> {
	public V get(K key);
	
	public void put(K key, V value, long timeout, TimeUnit unit);
	
	public void evict(K key);
}
