package com.aqr.book;

import java.io.Serializable;
import java.math.BigDecimal;
@SuppressWarnings("serial")
public class TopOfBook implements Book, Serializable {

	private static final long serialversionUID =  129347938L;
	//SYMBOL, 
	private String symbol;
	//BEST_BID_PRICE
	private BigDecimal bestBidPrice;
	//BEST_BID_SIZE 
	private Integer  bestBidSize;
	//BEST_OFFER_PRICE
	private  BigDecimal bestOfferPrice;
	//BEST_OFFER_SIZE
	private Integer bestOfferSize;
	
	public TopOfBook(String symbol, BigDecimal bestBidPrice, Integer bestBidSize, BigDecimal bestOfferPrice,
			Integer bestOfferSize) {
		super();
		this.symbol = symbol;
		this.bestBidPrice = bestBidPrice;
		this.bestBidSize = bestBidSize;
		this.bestOfferPrice = bestOfferPrice;
		this.bestOfferSize = bestOfferSize;
	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getBestBidPrice() {
		return bestBidPrice;
	}

	public Integer getBestBidSize() {
		return bestBidSize;
	}

	public BigDecimal getBestOfferPrice() {
		return bestOfferPrice;
	}

	public Integer getBestOfferSize() {
		return bestOfferSize;
	}

	@Override
	public String toString() {
		return "TopOfBook [symbol=" + symbol + ", bestBidPrice=" + bestBidPrice + ", bestBidSize=" + bestBidSize
				+ ", bestOfferPrice=" + bestOfferPrice + ", bestOfferSize=" + bestOfferSize + "]";
	}
	
	
	
}
