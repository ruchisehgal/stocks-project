package com.commercetools.stocks.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Stock_Table", uniqueConstraints = {@UniqueConstraint(columnNames = { "productId" })})
public class Stock implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5143471244988670532L;
	@Id
	//@GeneratedValue
	@NotNull
	private String id;
	@NotNull
	private String productId;
	@NotNull
	private int quantity;
	private Date timeStamp;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getTimeStamp() {
		return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").format(timeStamp);
	}
	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}
	
	public Stock(String id, String productId, int quantity, Date timeStamp) {
		super();
		this.id = id;
		this.productId = productId;
		this.quantity = quantity;
		this.timeStamp = timeStamp;
	}
	public Stock() {
		super();
	}

	

}
