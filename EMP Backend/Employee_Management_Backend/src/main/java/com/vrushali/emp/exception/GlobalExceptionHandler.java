package com.vrushali.emp.exception;

import java.net.http.HttpHeaders;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import payload.ErrorDetails;

@ControllerAdvice
public class GlobalExceptionHandler //extends ResponseEntityExceptionHandler
{
	
	//handle specific exception
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
																		WebRequest webrequest)
	{
		
		ErrorDetails errordetails=new ErrorDetails(new Date(),
													exception.getMessage(),
													webrequest.getDescription(false)
													);
		return new ResponseEntity<>(errordetails,HttpStatus.NOT_FOUND);
	}
	
	
	
	//Global Exception
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGlobalException(Exception exception,
																		WebRequest webrequest)
	{
		
		ErrorDetails errordetails=new ErrorDetails(new Date(),
													exception.getMessage(),
													webrequest.getDescription(false)
													);
		return new ResponseEntity(errordetails,HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object>handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
																WebRequest request)
	{
		
		Map<String,String> errors = new HashMap<String, String>();
		ex.getBindingResult().getAllErrors().forEach((error)->{
			String fieldName = ((FieldError) error).getField();
			String message = error.getDefaultMessage();
			errors.put(fieldName, message);
		});
		
			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

}
