package test;
import static com.aqr.processors.BooksProcessor.readAndConsolidateOrderBasedBook;
import static com.aqr.processors.BooksProcessor.readAndProcessOrderBasedBook;
import static com.aqr.processors.BooksProcessor.readOrderBasedBook;
import static com.aqr.processors.BooksProcessor.readTopOfBookData;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.aqr.book.TopOfBook;
import com.aqr.processors.BookConsolidatorProcessor;
public class TestProcessors {

	
	
	public static void main(String args[]) throws Exception {
		
	//	System.out.println("TestOfBookTrades" +readTopOfBookData("TopOfBookTrade.txt"));
		
	//	System.out.println("Order Based Book" +readOrderBasedBook("OrderBasedBook.txt"));
	//	System.out.println("Order Based Book Processed " + readAndProcessOrderBasedBook("OrderBasedBook.txt"));
	//	System.out.println("Order Based Book" +readAndConsolidateOrderBasedBook(readTopOfBookData("TopOfBookTrade.txt"),readAndProcessOrderBasedBook("OrderBasedBook.txt")));
	
	/*	BookConsolidatorProcessor bcp = new BookConsolidatorProcessor();
		bcp.processOrders("TopOfBookTrade.txt", "OrderBasedBook.txt");
		List<TopOfBook> tobList = bcp.getTopBooks("IBM", 5);
		System.out.println("For Books : IBM");
		for(int i=0; i <tobList.size(); i++){
			TopOfBook tbBook = tobList.get(i);
			System.out.println("Level : " + i + " Bid Size : " +tbBook.getBestBidSize() +  " Bid Price : " + tbBook.getBestBidPrice() + " Offer Price : " + tbBook.getBestOfferPrice() +" Offer Size : " + tbBook.getBestOfferSize());
		}*/
	}
	
	@Test
	public void test_readTopOfBookData() {
		Assert.assertEquals(56,readTopOfBookData("TopOfBookTrade.txt").size());
	}
	@Test
	public void test_readAndProcessOrderBasedBook() {
		Assert.assertEquals(13,readAndProcessOrderBasedBook("OrderBasedBook.txt").size());
	}
	
	@Test
	public void test_readAndConsolidateOrderBasedBook() {
		Assert.assertEquals(3,readAndConsolidateOrderBasedBook(readTopOfBookData("TopOfBookTrade.txt"),readAndProcessOrderBasedBook("OrderBasedBook.txt")).size());
	}
	
	@Test
	public void test_BookConsolidatorProcessork() {
		BookConsolidatorProcessor bcp = new BookConsolidatorProcessor();
		bcp.processOrders("TopOfBookTrade.txt", "OrderBasedBook.txt");
		List<TopOfBook> tobList = bcp.getTopBooks("IBM", 5);
		for(int i=0; i <tobList.size(); i++){
			System.out.println("For Books : IBM");
			TopOfBook tbBook = tobList.get(i);
			System.out.println("Level : " + i + " Bid Size : " +tbBook.getBestBidSize() +  " Bid Price : " + tbBook.getBestBidPrice() + " Offer Price : " + tbBook.getBestOfferPrice() +" Offer Size : " + tbBook.getBestOfferSize());
		}
		Assert.assertEquals(5,tobList.size());
	}
}
