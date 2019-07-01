package com.commercetools.stocks.responsetype;

import java.util.List;
import com.commercetools.stocks.model.Stock;
import com.commercetools.stocks.model.StockSales;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class StockStatisticsResponse {

	private String requestTimestamp;
	private String range;
	private List<Stock> topAvailableProducts;
	private List<StockSales> topSellingProducts;
	public String getRequestTimestamp() {
		return requestTimestamp;
	}
	public void setRequestTimestamp(String requestTimestamp) {
		this.requestTimestamp = requestTimestamp;
	}
	public String getRange() {
		return range;
	}
	public void setRange(String range) {
		this.range = range;
	}
	public List<Stock> getTopAvailableProducts() {
		return topAvailableProducts;
	}
	public void setTopAvailableProducts(List<Stock> topAvailableProducts) {
		this.topAvailableProducts = topAvailableProducts;
	}
	public List<StockSales> getTopSellingProducts() {
		return topSellingProducts;
	}
	public void setTopSellingProducts(List<StockSales> topSellingProducts) {
		this.topSellingProducts = topSellingProducts;
	}
	
	

}
