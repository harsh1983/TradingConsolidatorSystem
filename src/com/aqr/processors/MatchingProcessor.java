package com.aqr.processors;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.aqr.book.NewOrderBasedBook;
import com.aqr.book.TopOfBook;


public class MatchingProcessor {
	
    private String symbol;
    private Comparator<TopOfBook> topBookComparator = new Comparator<TopOfBook>() {
        @Override
        public int compare(TopOfBook s1, TopOfBook s2) {
            return s1.getBestBidPrice().compareTo(s2.getBestBidPrice());
        }
    };
    private Comparator<NewOrderBasedBook> buyComparator = new Comparator<NewOrderBasedBook>() {
         @Override
         public int compare(NewOrderBasedBook s1, NewOrderBasedBook s2) {
             return s1.getLimitPrice().compareTo(s2.getLimitPrice());
         }
     };
     
     private Comparator<NewOrderBasedBook> sellComparator = new Comparator<NewOrderBasedBook>() {
    	 @Override
         public int compare(NewOrderBasedBook s1, NewOrderBasedBook s2) {
             return s2.getLimitPrice().compareTo(s1.getLimitPrice());
         }
     };
    
    private PriorityQueue<NewOrderBasedBook> buyTrades = new PriorityQueue<>(buyComparator);
 	private PriorityQueue<NewOrderBasedBook> sellTrades =  new PriorityQueue<>(sellComparator);
 	private PriorityQueue<TopOfBook> topBook =  new PriorityQueue<>(topBookComparator);
	
	public MatchingProcessor(String symbol) {
		super();
		this.symbol = symbol;
	}
	public synchronized void  addBuyTrades(NewOrderBasedBook buyBook){
		buyTrades.add(buyBook);
	}
	public synchronized void addSellTrades(NewOrderBasedBook buyBook){
		sellTrades.add(buyBook);
	}
	public String getSymbol() {
		return symbol;
	}
	
	public synchronized void  addTopOfBook(TopOfBook tob){
		topBook.add(tob);
	}
	
	public synchronized List<TopOfBook> getTopConsolidatedBooks(Integer size){
		List<TopOfBook> listTopOfBook = new ArrayList<>(size);
		for(int i=0 ; i<size ; i++){
			NewOrderBasedBook buyTrade = buyTrades.poll();
			NewOrderBasedBook sellTrade =  sellTrades.poll();
			if(buyTrade!=null && sellTrade!=null){
			TopOfBook obj = new TopOfBook(symbol, buyTrade.getLimitPrice(), buyTrade.getQuatity(), sellTrade.getLimitPrice(),
					sellTrade.getQuatity());
			listTopOfBook.add(obj);
			}else{
				break;
			}

		}
		for(int i=0 ; i<size ; i++){
			listTopOfBook.add(topBook.poll());
		}
		
		return listTopOfBook;
 	}

}
