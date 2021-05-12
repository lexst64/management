package com.management.tools;

public class ListIsEmptyException extends Exception {
	
	private static final long serialVersionUID = 1L;
	
	ListIsEmptyException(String msgText) {
		super(msgText);
	}
	
}
