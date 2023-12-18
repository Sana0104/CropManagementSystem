package com.crop.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.crop.entity.Crop;



public interface CropRepository extends MongoRepository<Crop, String> {
	
	List<Crop> findByCropName(String cropName);
	
	List<Crop> findByCropType(String cropType);

}
