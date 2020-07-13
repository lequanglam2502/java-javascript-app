package com.colin.app.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.colin.app.service.IRedisCacheService;

@Service
public class RedisCacheService<K, V> implements IRedisCacheService<K, V> {

	@Autowired
	@Qualifier("redisTemplate")
	RedisTemplate<K, V> redisTemplate;

	@Autowired
	@Qualifier("redisOperator")
	ValueOperations<K, V> redisOperator;

	@Value("${colin.app.redis.enable}")
	private boolean isRedisEnable;

	@Override
	public V get(K key) {
		if (isRedisEnable && redisTemplate.hasKey(key)) {
			return (V) redisOperator.get(key);
		}

		return null;
	}

	@Override
	public void put(K key, V value, long timeout, TimeUnit unit) {
		if (isRedisEnable) {
			redisOperator.set(key, value, timeout, unit);
		}
	}

	@Override
	public void evict(K key) {
		if (isRedisEnable) {
			redisTemplate.delete(key);
		}

	}

}
