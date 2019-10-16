package com.aqr.processors;

import static com.aqr.datareader.FeedFileReader.readFile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.stream.Collectors;

import com.aqr.book.CancelOrderBasedBook;
import com.aqr.book.ModifyOrderBasedBook;
import com.aqr.book.NewOrderBasedBook;
import com.aqr.book.OrderBasedBook;
import com.aqr.book.OrderType;
import com.aqr.book.SellType;
import com.aqr.book.TopOfBook;
import com.aqr.exception.FeedParsingException;

import static  com.aqr.book.OrderType.CANCEL_ORDER;
import static  com.aqr.book.OrderType.NEW_ORDER;
import static  com.aqr.book.OrderType.MODIFY_ORDER;
public class BooksProcessor {


	public static List<TopOfBook> readTopOfBookData(String fileName){
	List<TopOfBook> topOfBookMap = new ArrayList<TopOfBook>();	
		try {
        BufferedReader br = new BufferedReader(new FileReader(readFile(fileName)));
		
        //read header only
        String firstLine = br.readLine();
             //read data post header
        String line = br.readLine();
        while(line!=null){
        	String[] entity = line.split(",");
     		if (entity != null) {
     			//FeedParsingException
     			topOfBookMap.add(new TopOfBook(entity[0], new BigDecimal(entity[1]), Integer.valueOf(entity[2]), new BigDecimal(entity[3]), Integer.valueOf(entity[4])));
     		} else {
     			throw new RuntimeException("Bad  Values");
     		}
        	line = br.readLine();
        }
		} catch (Exception e) {			
			throw new FeedParsingException("error while reading Top Of Book files");
		}
     return topOfBookMap;
	}

	public static  List<? super OrderBasedBook> readOrderBasedBook(String fileName){
		List<? super OrderBasedBook> orderBasedBooks = new ArrayList<>();	

		try {
        BufferedReader br = new BufferedReader(new FileReader(readFile(fileName)));
		
        //read header only
        String firstLine = br.readLine();
             //read data post header
        String line = br.readLine();
              
        while(line!=null){
        	String[] entity = line.split(",");
     		if (entity != null) {
     			switch(OrderType.valueOf(entity[0].trim())){
       			case NEW_ORDER: orderBasedBooks.add(new NewOrderBasedBook(entity[1] , entity[5] ,new BigDecimal(entity[2].trim())  , SellType.valueOf(entity[3].trim()),Integer.valueOf(entity[4].trim())));break;
     			case MODIFY_ORDER : orderBasedBooks.add(new ModifyOrderBasedBook( entity[5],Integer.valueOf(entity[4].trim())));break;
     			case CANCEL_ORDER: orderBasedBooks.add(new CancelOrderBasedBook(entity[5]));break;
     			}
     		} else {
     			throw new RuntimeException("Bad  Values");
     		}
     	
        	line = br.readLine();
        }
		} catch (Exception e) {			
			throw new FeedParsingException("error while reading Order Based Book files");
		}
     return orderBasedBooks;
	}
	

	public static List<? super OrderBasedBook>  readAndProcessOrderBasedBook(String fileName){
		Map<String,? super OrderBasedBook> orderBasedBooks = new HashMap<>();	

		try {
        BufferedReader br = new BufferedReader(new FileReader(readFile(fileName)));
		
        //read header only
        String firstLine = br.readLine();
             //read data post header
        String line = br.readLine();
              
        while(line!=null){
        	String[] entity = line.split(",");
     		if (entity != null) {
     			switch(OrderType.valueOf(entity[0].trim())){
       			case NEW_ORDER: orderBasedBooks.put(entity[5].trim(),new NewOrderBasedBook(entity[1] , entity[5] ,new BigDecimal(entity[2].trim())  , SellType.valueOf(entity[3].trim()),Integer.valueOf(entity[4].trim())));break;
     			case MODIFY_ORDER : {
     				NewOrderBasedBook tempObj = (NewOrderBasedBook) orderBasedBooks.get(entity[5].trim());
     				orderBasedBooks.put(entity[5].trim(),
     						new NewOrderBasedBook(tempObj.getSymbol() , tempObj.getOrderId(),tempObj.getLimitPrice() ,tempObj.getSellType(),Integer.valueOf(entity[4].trim())));break;
     			}
     			case CANCEL_ORDER: orderBasedBooks.remove(entity[5].trim());break;
     			}
     		} else {
     			throw new RuntimeException("Bad OrderBasedBook  Values");
     		}
     	
        	line = br.readLine();
        }
		} catch (Exception e) {			
			throw new FeedParsingException("error while reading Order Based Book files");
		}
     return orderBasedBooks.values().stream()
    			.collect(Collectors.toList());
	}
	public static  Map<String,MatchingProcessor> readAndConsolidateOrderBasedBook(List<TopOfBook> topOfBook ,List<? super OrderBasedBook> listOrderBook){
		Map<String,MatchingProcessor> consolidatedBooks = new HashMap<>();	

		
        for(TopOfBook tpBook : topOfBook){
        	if(consolidatedBooks.containsKey(tpBook.getSymbol())){
        		consolidatedBooks.get(tpBook.getSymbol()).addTopOfBook(tpBook);
        	}else{
        		MatchingProcessor mp = new MatchingProcessor(tpBook.getSymbol());
        		mp.addTopOfBook(tpBook);
        		consolidatedBooks.put(tpBook.getSymbol(), mp);
        	}
        	
        }
        for(Object odrBoook : listOrderBook){
        	NewOrderBasedBook obj = (NewOrderBasedBook)odrBoook;
        	MatchingProcessor mp;
        	if(consolidatedBooks.containsKey(obj.getSymbol())){
        		mp =consolidatedBooks.get(obj.getSymbol());
        	}else{
        		mp = new MatchingProcessor(obj.getSymbol());
        	
        	}
        	if(obj.getSellType()== SellType.SELL){
    			mp.addSellTrades(obj);
    		}else{
    			mp.addBuyTrades(obj);
    		}
    		consolidatedBooks.put(obj.getSymbol(), mp);
        	
        }
		
     return consolidatedBooks;
	}
	
	
}
