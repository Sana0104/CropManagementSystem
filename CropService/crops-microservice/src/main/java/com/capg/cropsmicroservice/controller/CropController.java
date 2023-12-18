package com.capg.cropsmicroservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capg.cropsmicroservice.model.Crop;
import com.capg.cropsmicroservice.service.CropServiceImpl;

@RestController
@RequestMapping("/crops")
public class CropController {
	
	@Autowired
	private CropServiceImpl service;
	
	@PostMapping("/add")
	public ResponseEntity<Crop> addCrop(@RequestBody Crop crop){
		
		Crop c1 = service.addCrop(crop);
		return new ResponseEntity<>(c1, HttpStatus.CREATED);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<Crop> updateCrop(@PathVariable String id, @RequestBody Crop crop){
		
		Crop c1 = service.updateCrop(id, crop);
		return new ResponseEntity<>(c1, HttpStatus.CREATED);
	}
	
	@GetMapping("/view/{cropId}")
	public ResponseEntity<Crop> viewCrop(@PathVariable String cropId){
		
		Crop c1 = service.viewCrop(cropId);
		return new ResponseEntity<>(c1, HttpStatus.OK);
	}
	
	@GetMapping("/name/{cropName}")
	public ResponseEntity<List<Crop>> viewByCropName(@PathVariable String cropName){
		
		List<Crop> list = service.viewByCropName(cropName);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/type/{cropType}")
	public ResponseEntity<List<Crop>> viewByCropType(@PathVariable String cropType){
		
		List<Crop> list = service.viewByCropType(cropType);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteCrop(@PathVariable String id){
		
		String s1 = service.deleteCrop(id);
		return new ResponseEntity<>(s1, HttpStatus.OK);
	}

	@GetMapping("/")
	public ResponseEntity<List<Crop>> viewAllCrops(){
		
		List<Crop> list = service.viewAllCrops();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
}
