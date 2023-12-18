package com.capg.cropsmicroservice.service;

import java.util.List;

import com.capg.cropsmicroservice.model.Crop;

public interface ICropService {
	
	public Crop addCrop(Crop crop);
	
	public Crop updateCrop(String id, Crop crop);
	
	public String deleteCrop(String id);
	
	public Crop viewCrop(String id);
	
	public List<Crop> viewByCropName(String cropName);
	
	public List<Crop> viewByCropType(String cropType);
	
	public List<Crop> viewAllCrops();

}
