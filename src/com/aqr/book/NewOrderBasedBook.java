package com.aqr.book;

import java.io.Serializable;
import java.math.BigDecimal;

@SuppressWarnings("serial")
public class NewOrderBasedBook implements OrderBasedBook , Serializable  {

	private static final long serialversionUID =  128347938L;
	
	//ORDER_ID
	private final String orderId;
	//ORDER_TYPE
	private final OrderType orderType = OrderType.NEW_ORDER;	
	
	//LIMIT_PRICE
	private final  BigDecimal limitPrice;
	//SIDE (BUY/SELL)
	private final SellType sellType;
	//QUANTITY
	private final Integer quatity;
	
	// SYMBOL
	private final String symbol;
	
	
	public NewOrderBasedBook(String symbol,String orderId,  BigDecimal limitPrice, SellType sellType,
			Integer quatity) {	
		this.orderId = orderId;	
		this.limitPrice = limitPrice;
		this.sellType = sellType;
		this.quatity = quatity;
		this.symbol= symbol;
	}


	public String getOrderId() {
		return orderId;
	}


	public OrderType getOrderType() {
		return orderType;
	}


	public BigDecimal getLimitPrice() {
		return limitPrice;
	}



	public SellType getSellType() {
		return sellType;
	}


	public Integer getQuatity() {
		return quatity;
	}


	@Override
	public String getSymbol() {
		return symbol;
    }


	@Override
	public String toString() {
		return "NewOrderBasedBook [orderId=" + orderId + ", orderType=" + orderType + ", limitPrice=" + limitPrice
				+ ", sellType=" + sellType + ", quatity=" + quatity + ", symbol=" + symbol + "]";
	}
	

}
