package com.renj1thr.expensetracker.controllers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.renj1thr.expensetracker.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class ExceptionControllerHandler {
	
	private String HTTPSTATUS_BAD_REQUEST = "400";
	private String HTTPSTATUS_NOT_FOUND = "404";
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
		
		log.error("Handling Number Format Exception");
        log.error(ex.getMessage());
        
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	    	
	        errors.put("TimeStamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
	        errors.put("status", HTTPSTATUS_BAD_REQUEST);
	        errors.put("message", error.getDefaultMessage());
	        errors.put("type", "Validation Error");
	        
	    });
	    return ResponseEntity.badRequest().body(errors);
	}
	
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<?> handleNotFoundExceptions(
			NotFoundException ex) {
		
		log.error("Handling Number Format Exception");
        log.error(ex.getMessage());
        
        Map<String, String> errors = new HashMap<>();
        
        errors.put("TimeStamp", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now()));
        errors.put("status", HTTPSTATUS_NOT_FOUND);
        errors.put("message", ex.getMessage());
        errors.put("type", "value not found");
        
        return ResponseEntity.badRequest().body(errors);
	}
	
}
