package com.demo.exception;

public class OrderCancellationException extends RuntimeException {
	
	public OrderCancellationException(String message)
	{
		super(message);
	}
}
