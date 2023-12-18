package com.crop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crop.entity.Crop;
import com.crop.service.CropService;

@RestController
@RequestMapping("/crops")
@CrossOrigin(origins = "http://localhost:3000")
public class CropController {
	
	@Autowired
	private CropService cropService;
	
	
	@PostMapping("/addCrop")
	public ResponseEntity<Crop> addCrop(@RequestBody Crop crop){
		
		Crop crops = cropService.addCrop(crop);
		return new ResponseEntity<>(crops, HttpStatus.CREATED);
	}
	
	@PutMapping("/updateCrop/{id}")
	public ResponseEntity<Crop> updateCrop(@PathVariable String id, @RequestBody Crop crop){
		
		Crop crops = cropService.updateCrop(id, crop);
		return new ResponseEntity<>(crops, HttpStatus.CREATED);
	}
	
	@GetMapping("/viewCropById/{cropId}")
	public ResponseEntity<Crop> viewCrop(@PathVariable String cropId){
		
		Crop c1 = cropService.viewCrop(cropId);
		return new ResponseEntity<>(c1, HttpStatus.OK);
	}
	
	@GetMapping("/viewCropByName/{cropName}")
	public ResponseEntity<List<Crop>> viewByCropName(@PathVariable String cropName){
		
		List<Crop> crops = cropService.viewByCropName(cropName);
		return new ResponseEntity<>(crops, HttpStatus.OK);
	}
	
	@GetMapping("/viewByCropType/{cropType}")
	public ResponseEntity<List<Crop>> viewByCropType(@PathVariable String cropType){
		
		List<Crop> crops = cropService.viewByCropType(cropType);
		return new ResponseEntity<>(crops, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteCropById/{id}")
	public ResponseEntity<String> deleteCrop(@PathVariable String id){
		
		String crops = cropService.deleteCrop(id);
		return new ResponseEntity<>(crops, HttpStatus.OK);
	}

	@GetMapping("/viewAllCrops")
	public ResponseEntity<List<Crop>> viewAllCrops(){
		
		List<Crop> crops = cropService.viewAllCrops();
		return new ResponseEntity<>(crops, HttpStatus.OK);
	}
	
}
