package com.colin.app.entity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.colin.app.entity.domain.Item;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
	Object findByName(String name);
}
