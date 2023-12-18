package com.capg.cropsmicroservice;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.capg.cropsmicroservice.exceptions.CropNotFoundException;
import com.capg.cropsmicroservice.model.Crop;
import com.capg.cropsmicroservice.repository.CropRepository;
import com.capg.cropsmicroservice.service.CropServiceImpl;

@SpringBootTest
class CropServiceTest {
	
	@Mock
    private CropRepository cropRepository;

    @InjectMocks
    private CropServiceImpl cropService;

    @BeforeEach
    void setUp() {
        System.out.println("Testing in springboot");
    }
    
    @Test
    public void testAddCrop() {
        Crop crop = new Crop();
        crop.setCropName("TestCrop");
        crop.setCropType("TestType");
        crop.setDescription("TestDescription");
		crop.setPricePerUnit(10.0);
		crop.setQuantityAvailable(100);

        when(cropRepository.save(Mockito.any(Crop.class))).thenReturn(crop);

        Crop addedCrop = cropService.addCrop(crop);
        assertEquals("TestCrop", addedCrop.getCropName());
        assertEquals("TestType", addedCrop.getCropType());
        assertEquals("TestDescription",addedCrop.getDescription());
        assertEquals(10.0,addedCrop.getPricePerUnit());
        assertEquals(100, addedCrop.getQuantityAvailable());

    }
    
    @Test
    public void testViewByCropName() {
        
        List<Crop> crops = new ArrayList<>();
        Crop crop = new Crop();
        crop.setCropName("TestCrop");
        crop.setCropType("TestType");
        crop.setDescription("TestDescription");
		crop.setPricePerUnit(10.0);
		crop.setQuantityAvailable(100);
        // Set other properties as needed
        crops.add(crop);

        when(cropRepository.findByCropName("TestCrop")).thenReturn(crops);

        List<Crop> result = cropService.viewByCropName("TestCrop");

        
        assertEquals(1, result.size());
        Crop retrievedCrop = result.get(0);
        assertEquals("TestCrop", retrievedCrop.getCropName());
        assertEquals("TestType", retrievedCrop.getCropType());
        assertEquals("TestDescription",retrievedCrop.getDescription());
        assertEquals(10,retrievedCrop.getPricePerUnit());
        assertEquals(100, retrievedCrop.getQuantityAvailable());
    }
    
    @Test
    public void testViewByCropNameWhenNoCropsFound() {
        
        when(cropRepository.findByCropName("Wheat")).thenReturn(new ArrayList<>());

        assertThrows(CropNotFoundException.class, () -> cropService.viewByCropName("Wheat"));
    }

}
