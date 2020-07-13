package com.colin.app.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.colin.app.entity.model.Stock;
import com.colin.app.service.IStockService;

/**
 * This controller is a socket to listen to the client request and return the
 * real time data getting from Reuters
 * 
 * @author colinle
 *
 */
@Controller
public class StockPriceSocket {

	@Autowired
	IStockService stockService;

	/**
	 * The socket to return real time data to client
	 * 
	 * @param message
	 * @return
	 */
	@MessageMapping("/get/data")
	@SendTo("/update")
	public List<Stock> sendMessage(String message) {
		return stockService.getRealTimeData();
	}
}
