package com.crop.service;

import java.util.List;

import com.crop.entity.Crop;


public interface CropService {
	
	
	public Crop updateCrop(String id, Crop crop);
	
	public String deleteCrop(String id);
	
	public Crop viewCrop(String id);
	
	public List<Crop> viewByCropName(String cropName);
	
	public List<Crop> viewByCropType(String cropType);
	
	public List<Crop> viewAllCrops();

	public Crop addCrop(Crop crop);

}
