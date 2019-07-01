package com.commercetools.stocks.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Stock_Sales", uniqueConstraints = {@UniqueConstraint(columnNames = { "productId" })})
public class StockSales implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 50651839809106457L;
	
	@Id
	@GeneratedValue
	@NotNull
	private int prd_id;
	@NotNull
	private String fk_id;
	@NotNull
	private String productId;
	@NotNull
	private int itemsSold;
	
	private Date timestamp;
	
	public int getPrd_id() {
		return prd_id;
	}
	public void setPrd_id(int prd_id) {
		this.prd_id = prd_id;
	}
	public String getFk_id() {
		return fk_id;
	}
	public void setFk_id(String fk_id) {
		this.fk_id = fk_id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getItemsSold() {
		return itemsSold;
	}
	public void setItemsSold(int itemsSold) {
		this.itemsSold = itemsSold;
	}
	public String getTimestamp() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(timestamp);
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public StockSales(String fk_id, String productId, int itemsSold, Date timestamp) {
		super();
		this.fk_id = fk_id;
		this.productId = productId;
		this.itemsSold = itemsSold;
		this.timestamp = timestamp;
	}
	public StockSales() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	

	

}
