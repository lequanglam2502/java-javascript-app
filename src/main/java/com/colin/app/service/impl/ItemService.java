package com.colin.app.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.colin.app.entity.domain.Item;
import com.colin.app.entity.repository.ItemRepository;
import com.colin.app.service.IItemService;
import com.colin.app.service.IRedisCacheService;
import com.colin.app.util.Constants;

@Service
public class ItemService implements IItemService {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	IRedisCacheService<String, List<Item>> redisCacheService;
	
	/**
	 * get all item and store it in Redis cache
	 * 
	 * Cachable is the default EHcache, uncomment to use it
	 * see the itemListCache manager config
	 */
	@Override
	//@Cacheable(value="itemListCache")
	public List<Item> findAll() {
		// get cache
		List<Item> result = redisCacheService.get("itemListRedisCache");
		if (Objects.nonNull(result)) {
			return result;
		}

		result = itemRepository.findAll();

		// cache put
		redisCacheService.put("itemListRedisCache", result, 2, TimeUnit.MINUTES);

		return result;
	}

	/**
	 * save item and evict cache
	 * 
	 * Cachable is the default EHcache, uncomment to use it
	 * see the itemListCache manager config
	 */
	@Override
	// @CacheEvict(value = "itemListCache", allEntries=true)
	public Object save(Item item) {
		try {
			Object result = itemRepository.save(item);

			// evict cache
			redisCacheService.evict("itemListRedisCache");

			return result;
		} catch (DataIntegrityViolationException e) {
			return Constants.DATABASE_ID_MUST_BE_UNIQUE;
		}
	}
	
	/**
	 * delete item and evict cache
	 * 
	 * Cachable is the default EHcache, uncomment to use it
	 * see the itemListCache manager config
	 */
	@Override
	//@CacheEvict(value = "itemListCache", allEntries=true)
	public boolean delete(Item item) {
		try {
			itemRepository.delete(item);

			// evict cache
			redisCacheService.evict("itemListRedisCache");

			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
