package com.demo.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;



@ControllerAdvice
public class GlobalException {
	// handling specific exception
			@ExceptionHandler(ResourceNotFoundException.class)
			public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception,
					WebRequest request){
				ErrorDetails errorDetails = 
						new ErrorDetails(new Date(), exception.getMessage(), 
								request.getDescription(false));
				return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
			}

			
			// handling global exception
			
			@ExceptionHandler(Exception.class)
			public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
				ErrorDetails errorDetails = 
						new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
				return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			
			 @ExceptionHandler(OrderCancellationException.class)
			    public ResponseEntity<String> handleOrderCancellationException(OrderCancellationException ex) {
			        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
			    }
			
			
			  @ExceptionHandler(MethodArgumentNotValidException.class)
			    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
			        Map<String, String> errors = new HashMap<>();
			        ex.getBindingResult().getFieldErrors().forEach(error -> {
			            errors.put(error.getField(), error.getDefaultMessage());
			        });
			        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
			    }
			
			
			
}
