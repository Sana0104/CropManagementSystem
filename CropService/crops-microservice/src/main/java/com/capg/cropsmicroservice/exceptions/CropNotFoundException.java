package com.capg.cropsmicroservice.exceptions;

public class CropNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public CropNotFoundException(String message){
		super(message);
	}
	
}
