package com.colin.app.endpoint;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.colin.app.aspect.Audit;
import com.colin.app.dto.response.MessageResponse;
import com.colin.app.entity.domain.Item;
import com.colin.app.service.IItemService;
import com.colin.app.util.Constants;
import com.colin.app.util.AuditConstant.AuditAction;;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(path = "/secure/api/item")
public class ItemController {

	@Autowired
	IItemService itemService;

	/**
	 * find all item
	 * 
	 * @return ResponseEntity
	 */
	@RequestMapping(value = "/findAll", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<Item> entities = itemService.findAll();

		if (Objects.nonNull(entities)) {
			return new ResponseEntity<List<Item>>(entities, new HttpHeaders(), HttpStatus.ACCEPTED);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.AUTHENTICATION_LOGIN_FAIL));
		}
	}

	/**
	 * add item
	 * 
	 * @return ResponseEntity
	 */
	@Audit(table = Item.class, action = AuditAction.ADD)
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ResponseEntity<?> add(@RequestBody Item item) {
		// save item date as current time
		item.setDate(new Timestamp(System.currentTimeMillis()));
		
		Object result = itemService.save(item);
		
		if (result.toString().equals(Constants.DATABASE_ID_MUST_BE_UNIQUE)) {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.DATABASE_ID_MUST_BE_UNIQUE));
		} else {
			return new ResponseEntity<Item>(item, new HttpHeaders(), HttpStatus.ACCEPTED);
		}
	}

	/**
	 * update item
	 * 
	 * @return ResponseEntity
	 */
	@Audit(table = Item.class, action = AuditAction.UPDATE)
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<?> update(@RequestBody Item item) {
		// save item date as current time
		item.setDate(new Timestamp(System.currentTimeMillis()));
		if (Objects.nonNull(itemService.save(item))) {
			return new ResponseEntity<Item>(item, new HttpHeaders(), HttpStatus.ACCEPTED);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.ITEM_UPDATE_FAIL));
		}
	}

	/**
	 * delete item
	 * 
	 * @return ResponseEntity
	 */
	@Audit(table = Item.class, action = AuditAction.DELETE)
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ResponseEntity<?> delete(@RequestBody Item item) {
		if (itemService.delete(item)) {
			return new ResponseEntity<Boolean>(true, new HttpHeaders(), HttpStatus.ACCEPTED);
		} else {
			return ResponseEntity.badRequest().body(new MessageResponse(Constants.ITEM_DELETE_FAIL));
		}
	}
}
