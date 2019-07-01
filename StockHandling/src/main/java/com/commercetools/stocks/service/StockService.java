package com.commercetools.stocks.service;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.StaleStateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commercetools.stocks.dao.StockDao;
import com.commercetools.stocks.model.Stock;
import com.commercetools.stocks.model.StockSales;
import com.commercetools.stocks.responsetype.StockGetResponse;
import com.commercetools.stocks.responsetype.StockResponse;
import com.commercetools.stocks.responsetype.StockStatisticsResponse;


/*This Layer consists the busines logic for updating and retrieving the stock Information*/


@Service
@Transactional
public class StockService {
	@Autowired
	private StockDao dao;

	public StockResponse updateStock(Stock stock) {
		try{
			stock.setTimeStamp(new Date());
			String message = dao.updateStock(stock);
			StockResponse response = new StockResponse();
			response.setStatus("success");
			response.setMessage(message);
			response.setTxDate(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
			return response;
		} catch(StaleStateException se) {
			se.printStackTrace();
			return null;
		}
		
		
	}

	public StockGetResponse getStockByProductId(String productId) {
		StockGetResponse getStock = new StockGetResponse();
		Stock stock = dao.getStockByProductId(productId);
		getStock.setProductId(productId);
		getStock.setRequestTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
		getStock.setStock(stock);
		return getStock;
	}

	public StockStatisticsResponse getStockStatistics(String timespan) {
		String str [] = timespan.split("&");
		String startDate = str[0].split("=")[1];
		String endDate = str[1].split("=")[1];
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		List<Stock> stocks = new ArrayList<>();
		List<StockSales> topSellingProducts = new ArrayList<>();
		StockStatisticsResponse getStatistics = new StockStatisticsResponse();
		try {
			//new end
			Date date1= formatter.parse(startDate);
			Timestamp startDte = new Timestamp(date1.getTime());
			Date date2= formatter.parse(endDate); 
			Timestamp endDdte = new Timestamp(date2.getTime());
			stocks = dao.getStockStatistics(startDte,endDdte);
			topSellingProducts = dao.getStockSoldStatistics(startDte,endDdte);
			//Sorting the List in Descending order based on Quantity of stocks
			Collections.sort(stocks, new Comparator<Stock>() {
		        @Override public int compare(Stock p1, Stock p2) {
		            return p2.getQuantity() - p1.getQuantity(); // Ascending
		        }

		    });
			//Retrieving the top 3 values from the stocks
			List<Stock> topAvailable = new ArrayList<Stock>(stocks.subList(0,3));
			
			//Sorting the list in descending order based on Items sold per stock
			Collections.sort(topSellingProducts, new Comparator<StockSales>() {
		        @Override public int compare(StockSales s1, StockSales s2) {
		            return s2.getItemsSold() - s1.getItemsSold(); // Ascending
		        }

		    });
			//Retrieving the top 3 items sold
			List<StockSales> topSold = new ArrayList<StockSales>(topSellingProducts.subList(0,3));
			
			String range = startDate + "," + endDate;
			getStatistics.setRequestTimestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(new Date()));
			getStatistics.setRange(range);
			getStatistics.setTopAvailableProducts(topAvailable);
			getStatistics.setTopSellingProducts(topSold);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return getStatistics;
	}

}
