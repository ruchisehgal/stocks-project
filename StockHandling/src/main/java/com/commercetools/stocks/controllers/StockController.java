package com.commercetools.stocks.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.commercetools.stocks.model.Stock;
import com.commercetools.stocks.responsetype.StockGetResponse;
import com.commercetools.stocks.service.StockService;

/*This Controller deals with Updating the stocks 
 * and Fetching record per stock based on Product Information*/

@RestController
@RequestMapping("/stock")
public class StockController {
	@Autowired
	private StockService service;

	/*This rest api serves as the entry point to update the current stock
	 * This will return a Status 201--Indicating that Stock was updated/
	 * Status 204- Indicating that update Failed/
	 * Status 400 -Indicating erroneous input JSON*/
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/updateStatus")
	public ResponseEntity  updateStatus(@RequestBody @Valid Stock stock) {
		
		try{
		service.updateStock(stock);
		return new ResponseEntity(HttpStatus.CREATED);
		}catch(RuntimeException e) {
			e.printStackTrace();
			return new ResponseEntity(HttpStatus.NO_CONTENT); 
		}
		
	}
	
	/*This Rest API serves as the entry point to 
	 * fetch the stock information based on input Product Id*/
	@GetMapping("/{productId}")
	public StockGetResponse getProduct(@PathVariable String productId) {
		return service.getStockByProductId(productId);
	}
}
