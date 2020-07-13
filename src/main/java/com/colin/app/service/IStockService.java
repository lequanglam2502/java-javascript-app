package com.colin.app.service;

import java.util.List;

import com.colin.app.entity.model.Stock;

public interface IStockService {

	List<Stock> getRealTimeData();
}
