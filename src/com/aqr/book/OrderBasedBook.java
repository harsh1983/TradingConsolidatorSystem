package com.aqr.book;


public interface OrderBasedBook extends Book {	

	public String getOrderId();
	public OrderType getOrderType() ;	
	
	
}
