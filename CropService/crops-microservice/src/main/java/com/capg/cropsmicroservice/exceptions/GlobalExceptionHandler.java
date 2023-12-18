package com.capg.cropsmicroservice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(CropNotFoundException.class)
	public ResponseEntity<Object> handleCropNotFoundException(CropNotFoundException ex) {

		return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);

	}
	
}
