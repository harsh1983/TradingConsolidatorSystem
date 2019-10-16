package com.aqr.book;

public interface Book {
 
	default  String  getSymbol(){
		return "" ;
	}
}
