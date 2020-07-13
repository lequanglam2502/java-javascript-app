package com.colin.app.entity.model;

/**
 * This model to map stock client data
 * 
 * @author colinle
 *
 */
public class Stock {
	private String symbol;

	private String price;

	private String trend;

	public Stock(String symbol, String price, String trend) {
		this.symbol = symbol;
		this.price = price;
		this.trend = trend;
	}
}
