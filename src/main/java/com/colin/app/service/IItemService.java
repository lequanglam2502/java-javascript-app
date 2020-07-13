package com.colin.app.service;

import java.util.List;

import com.colin.app.entity.domain.Item;

public interface IItemService {

	List<Item> findAll();

	Object save(Item item);

	boolean delete(Item item);
}
