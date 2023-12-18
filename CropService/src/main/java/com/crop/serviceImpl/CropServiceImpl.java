package com.crop.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.crop.entity.Crop;
import com.crop.exception.CropNotFoundException;
import com.crop.repository.CropRepository;
import com.crop.service.CropService;



@Service
public class CropServiceImpl implements CropService {
	
	@Autowired
	private CropRepository repository;

	@Override
	public Crop addCrop(Crop crop) {
		
		Crop crops = new Crop();
		
		crops.setCropName(crop.getCropName());
		crops.setCropType(crop.getCropType());
		crops.setDescription(crop.getDescription());
		crops.setPrice(crop.getPrice());
		crops.setQuantity(crop.getQuantity());
		
		crops.setLocation(crop.getLocation());
		crops.setImage(crop.getImage());
		crops.setSellerName(crop.getSellerName());

		return repository.save(crops);
	}

	@Override
	public Crop updateCrop(String id, Crop crop) {
		
		Optional<Crop> c = repository.findById(id);
		if(c.isEmpty()) {
			throw new CropNotFoundException("Crop does'nt exists with id= "+id);
		}
		Crop crops = c.get();
		crops.setCropName(crop.getCropName());
		crops.setCropType(crop.getCropType());
		crops.setDescription(crop.getDescription());
		crops.setPrice(crop.getPrice());
		crops.setQuantity(crop.getQuantity());
		
		crops.setLocation(crop.getLocation());
		crops.setImage(crop.getImage());
		crops.setSellerName(crop.getSellerName());
		
		return repository.save(crops);
	}

	@Override
	public String deleteCrop(String id) {
		
		Optional<Crop> c = repository.findById(id);
		if(c.isEmpty()) {
			throw new CropNotFoundException("Crop does'nt exists with id= "+id);
		}

		repository.deleteById(id);
		return "Crop deleted successfully";
	}

	@Override
	public Crop viewCrop(String id) {
		
		Optional<Crop> c = repository.findById(id);
		if(c.isEmpty()) {
			throw new CropNotFoundException("Crop does'nt exists with id= "+id);
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
			throw new CropNotFoundException("No crops found for the specified crop type: " + cropType);
		}
		return crops;
	}

	@Override
	public List<Crop> viewAllCrops() {
		
		List<Crop> cropsList = repository.findAll();
		if(cropsList.isEmpty()) {
			throw new CropNotFoundException("crops not found");
		}
		return cropsList;
	}

}
