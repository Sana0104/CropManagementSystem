package com.capg.cropsmicroservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.cropsmicroservice.exceptions.CropNotFoundException;
import com.capg.cropsmicroservice.model.Crop;
import com.capg.cropsmicroservice.repository.CropRepository;

@Service
public class CropServiceImpl implements ICropService {
	
	@Autowired
	private CropRepository repository;

	@Override
	public Crop addCrop(Crop crop) {
		
		Crop crop1 = new Crop();
		crop1.setCropName(crop.getCropName());
		crop1.setCropType(crop.getCropType());
		crop1.setDescription(crop.getDescription());
		crop1.setPricePerUnit(crop.getPricePerUnit());
		crop1.setQuantityAvailable(crop.getQuantityAvailable());
		
		crop1.setFarmLocation(crop.getFarmLocation());
		crop1.setSellerName(crop.getSellerName());
		crop1.setSellerContact(crop.getSellerContact());
		
		return repository.save(crop1);
	}

	@Override
	public Crop updateCrop(String id, Crop crop) {
		
		Optional<Crop> c = repository.findById(id);
		if(c.isEmpty()) {
			throw new CropNotFoundException("Such crop not exists");
		}
		Crop crop1 = c.get();
		crop1.setCropName(crop.getCropName());
		crop1.setCropType(crop.getCropType());
		crop1.setDescription(crop.getDescription());
		crop1.setPricePerUnit(crop.getPricePerUnit());
		crop1.setQuantityAvailable(crop.getQuantityAvailable());
		
		crop1.setFarmLocation(crop.getFarmLocation());
		crop1.setSellerName(crop.getSellerName());
		crop1.setSellerContact(crop.getSellerContact());
		
		return repository.save(crop1);
	}

	@Override
	public String deleteCrop(String id) {
		
		Optional<Crop> c = repository.findById(id);
		if(c.isEmpty()) {
			throw new CropNotFoundException("Such crop not exists");
		}
//		Crop crop = c.get();
		repository.deleteById(id);
		return "Crop deleted successfully";
	}

	@Override
	public Crop viewCrop(String id) {
		
		Optional<Crop> c = repository.findById(id);
		if(c.isEmpty()) {
			throw new CropNotFoundException("No such crop exists");
		}
		Crop crop = c.get();
		return crop;
	}

	@Override
	public List<Crop> viewByCropName(String cropName) {
		
		List<Crop> crops = repository.findByCropName(cropName);
		if(crops.isEmpty()) {
			throw new CropNotFoundException("No crops found for " + cropName);
		}
		return crops;
	}

	@Override
	public List<Crop> viewByCropType(String cropType) {
		
		List<Crop> crops = repository.findByCropType(cropType);
		if(crops.isEmpty()) {
			throw new CropNotFoundException("No crops found for " + cropType);
		}
		return crops;
	}

	@Override
	public List<Crop> viewAllCrops() {
		
		List<Crop> cropsList = repository.findAll();
		if(cropsList.isEmpty()) {
			throw new CropNotFoundException("No crops found");
		}
		return cropsList;
	}

}
