package com.commercetools.stocks.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.PersistenceException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.StaleStateException;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.commercetools.stocks.model.Stock;
import com.commercetools.stocks.model.StockSales;

/*This class provides the implementation for StockDao interface*/
@Repository
public class StockDaoImpl implements StockDao {
	
	@Autowired
	private SessionFactory factory;
	/*This method updates the current value of Stock,
	 * care has been taken so that the Id,productId cannot be updated as they are unique constraints
	*In case there is an update request for which no entry exists in the dB,
	*StaleState RuntimeException is handled in the catch block
	**/
	@Override
	public String updateStock(Stock stock) {
		try{
		getSession().update(stock);
		return "Updated";
		}catch(PersistenceException pe) {
			pe.printStackTrace();
			return "Error";
		}catch (StaleStateException se) {
			se.printStackTrace();
			return "Error";
		}
		
	}

	/*This method retrieves the product 
	 * Information based on Product ID*/
	@Override
	public Stock getStockByProductId(String productId) {
		return (Stock) getSession().createCriteria(Stock.class).add(Restrictions.eq("productId", productId)).uniqueResult();
	}
	
	private Session getSession() {
		Session session = null;
		try {
			session = factory.getCurrentSession();
		} catch (HibernateException ex) {
			session = factory.openSession();
		}
		return session;
	}

	/*This method retrieves the Stock details from STOCK_TABLE to be able to calculate the topavailable stocks based on Quantity*/
	@SuppressWarnings("unchecked")
	@Override
	public List<Stock> getStockStatistics(Date date1, Date date2) {
		List<Stock> stocks = getSession().createCriteria(Stock.class).add(Restrictions.between("timeStamp", date2, date1)).list();
		return stocks;
		
	}
	
	/*This method retrieves the Stock details from STOCK_SALES to be able to calculate the topSelling stocks based on Items Sold*/
	@SuppressWarnings("unchecked")
	@Override
	public List<StockSales> getStockSoldStatistics(Date startDte, Date endDdte) {
		List<StockSales> stockSales = getSession().createCriteria(StockSales.class).list();
		return stockSales;
	}


}
