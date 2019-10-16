package com.aqr.datareader;

import java.io.File;
import java.net.URL;


/** I am assuming that the application will recieve feed on shared directory 
 *  assuming the feed type to be txt file only
 * *
 */
public class FeedFileReader {

	private static String  directoryPath = "../../../resources/";
	//Modify this class to read from generic location sort the read files and parse the same
	public static  File readFile(String fileName){
		try {
			URL path = FeedFileReader.class.getResource(directoryPath+fileName);
			return new File(path.getFile());

		} catch (Exception ex) {
			throw new RuntimeException("Error while reading file");
		}

	}
	
	
	  

}
