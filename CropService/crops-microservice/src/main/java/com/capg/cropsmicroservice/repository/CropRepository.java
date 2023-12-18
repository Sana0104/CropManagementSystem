package com.capg.cropsmicroservice.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.capg.cropsmicroservice.model.Crop;

public interface CropRepository extends MongoRepository<Crop, String> {
	
	List<Crop> findByCropName(String cropName);
	
	List<Crop> findByCropType(String cropType);

}
