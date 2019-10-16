package com.aqr.book;

import java.io.Serializable;


@SuppressWarnings("serial")
public class CancelOrderBasedBook implements OrderBasedBook , Serializable {
	
	private static final long serialversionUID =  139347938L;
	

	//ORDER_ID
	private final String orderId;
	//ORDER_TYPE
	private final OrderType orderType = OrderType.CANCEL_ORDER;
	public CancelOrderBasedBook(String orderId) {
		super();
		this.orderId = orderId;
	}
	
	public String getOrderId() {
		return orderId;
	}

	@Override
	public String toString() {
		return "CancelOrderBasedBook [orderId=" + orderId + ", orderType=" + orderType + "]";
	}

	@Override
	public OrderType getOrderType() {
		// TODO Auto-generated method stub
		return orderType;
	}	
	
	
	

}
