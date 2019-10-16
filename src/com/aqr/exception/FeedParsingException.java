package com.aqr.exception;

public class FeedParsingException extends RuntimeException { 
	private String message;

	public FeedParsingException(String string) { 
		this.message = string; }

	@Override
	public String getMessage() {
		return message;
	}
}
