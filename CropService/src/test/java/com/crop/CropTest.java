package com.crop;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.crop.entity.Crop;
import com.crop.exception.CropNotFoundException;
import com.crop.repository.CropRepository;
import com.crop.serviceImpl.CropServiceImpl;

@SpringBootTest
public class CropTest {

    @Mock
    private CropRepository cropRepository;

    @InjectMocks
    private CropServiceImpl cropService;

    @Test
    public void testAddCrop() {
        Crop crop = new Crop();
        crop.setCropName("Tomato");
        crop.setCropType("Vegetable");
        crop.setDescription("Fresh red tomatoes");
        crop.setPrice(2.5);
        crop.setQuantity(50);
        crop.setLocation("Farm A");
        crop.setImage("tomato.jpg");
        crop.setSellerName("John Doe");

        when(cropRepository.save(crop)).thenReturn(crop);

        Crop savedCrop = cropService.addCrop(crop);
        assertEquals("Tomato", savedCrop.getCropName());
    }

    @Test
    public void testViewCrop() {
        Crop crop = new Crop();
        crop.setCropName("Potato");
        crop.setCropType("Vegetable");
        crop.setDescription("Fresh potatoes");
        crop.setPrice(1.5);
        crop.setQuantity(100);
        crop.setLocation("Farm B");
        crop.setImage("potato.jpg");
        crop.setSellerName("Jane Doe");

        when(cropRepository.findById("1")).thenReturn(Optional.of(crop));

        Crop retrievedCrop = cropService.viewCrop("1");
        assertEquals("Potato", retrievedCrop.getCropName());
    }

    @Test
    public void testDeleteCrop() {
        Crop crop = new Crop();
        crop.setId("1");
        crop.setCropName("Potato");
        crop.setCropType("Vegetable");
        crop.setDescription("Fresh potatoes");
        crop.setPrice(1.5);
        crop.setQuantity(100);
        crop.setLocation("Farm B");
        crop.setImage("potato.jpg");
        crop.setSellerName("Jane Doe");

        when(cropRepository.findById("1")).thenReturn(Optional.of(crop));

        String response = cropService.deleteCrop("1");
        assertEquals("Crop deleted successfully", response);
    }

    @Test
    public void testViewAllCrops() {
        Crop crop1 = new Crop();
        crop1.setCropName("Tomato");
        crop1.setCropType("Vegetable");
        crop1.setDescription("Fresh red tomatoes");
        crop1.setPrice(2.5);
        crop1.setQuantity(50);
        crop1.setLocation("Farm A");
        crop1.setImage("tomato.jpg");
        crop1.setSellerName("John Doe");

        Crop crop2 = new Crop();
        crop2.setCropName("Potato");
        crop2.setCropType("Vegetable");
        crop2.setDescription("Fresh potatoes");
        crop2.setPrice(1.5);
        crop2.setQuantity(100);
        crop2.setLocation("Farm B");
        crop2.setImage("potato.jpg");
        crop2.setSellerName("Jane Doe");

        List<Crop> crops = new ArrayList<>();
        crops.add(crop1);
        crops.add(crop2);

        when(cropRepository.findAll()).thenReturn(crops);

        List<Crop> retrievedCrops = cropService.viewAllCrops();
        assertEquals(2, retrievedCrops.size());
    }

}
