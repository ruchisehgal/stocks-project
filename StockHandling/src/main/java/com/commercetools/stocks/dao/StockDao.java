package com.commercetools.stocks.dao;

import java.util.Date;
import java.util.List;

import com.commercetools.stocks.model.Stock;
import com.commercetools.stocks.model.StockSales;


/*The Data interface layer providing Abstraction to the underlying database 
 * and sending Back the requested information to the Service layer*/


public interface StockDao {
	
	public String updateStock(Stock stock);
	
	public Stock getStockByProductId(String productId);

	public List<Stock> getStockStatistics(Date date1,Date date2);

	public List<StockSales> getStockSoldStatistics(Date startDte, Date endDdte);


}
