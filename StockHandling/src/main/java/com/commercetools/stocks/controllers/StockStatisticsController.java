package com.commercetools.stocks.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.stocks.responsetype.StockStatisticsResponse;
import com.commercetools.stocks.service.StockService;

/*This controller would receive the request 
 * to provide the stock Statistics for the purpose of carrying analysis*/

@RestController
@RequestMapping("/statistics")
public class StockStatisticsController {
	@Autowired
	private StockService service;
	/*Entry point to retrieve the stock statistics--
	 * TopAvailable products & 
	 * TopSelling Products*/
	
	@GetMapping("/{timespan}")
	public StockStatisticsResponse getProduct(@PathVariable String timespan) {
		
		return service.getStockStatistics(timespan);
	}
	
}
