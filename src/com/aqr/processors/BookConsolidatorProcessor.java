package com.aqr.processors;


import static com.aqr.processors.BooksProcessor.readAndConsolidateOrderBasedBook;
import static com.aqr.processors.BooksProcessor.readAndProcessOrderBasedBook;
import static com.aqr.processors.BooksProcessor.readTopOfBookData;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.aqr.book.TopOfBook;


public class BookConsolidatorProcessor {

	private  Map<String,MatchingProcessor> bookMap = new HashMap();
	
	public void  processOrders(String topOfBookFeedName,String orderBasedBookFeedName){
		bookMap  =	readAndConsolidateOrderBasedBook(readTopOfBookData(topOfBookFeedName),readAndProcessOrderBasedBook(orderBasedBookFeedName));
	}
	
	public List<TopOfBook> getTopBooks(String symbol ,int size){
		if(bookMap.get(symbol)!=null)
		return bookMap.get(symbol).getTopConsolidatedBooks(size);
		else return Collections.EMPTY_LIST;
	}
}

 