package com.capg.cropsmicroservice.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="crops")
public class Crop {
	
	private String id;
	private String cropName;
	private String cropType;
	private String description;
	private double pricePerUnit;
    private int quantityAvailable;
    
    private String farmLocation;
    private String sellerName;
    private long sellerContact;

}
