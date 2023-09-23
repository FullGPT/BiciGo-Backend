package com.upc.leadyourway.unitTest;

import com.upc.leadyourway.model.Bicycle;
import com.upc.leadyourway.model.User;
import com.upc.leadyourway.repository.AvailabilityRepository;
import com.upc.leadyourway.repository.BicycleRepository;
import com.upc.leadyourway.repository.UserRepository;
import com.upc.leadyourway.service.AvailabilityService;
import com.upc.leadyourway.service.BicycleService;
import com.upc.leadyourway.service.JwtService;
import com.upc.leadyourway.service.UserService;
import com.upc.leadyourway.service.impl.BicycleServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BicycleControllerTest {
    @Mock
    private BicycleRepository bicycleRepository;
    @Mock
    private BicycleService bicycleService;
    @Mock
    private UserService userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private AvailabilityRepository availabilityRepository;
    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);
        bicycleService = new BicycleServiceImpl(bicycleRepository, userService, availabilityRepository);
    }

    @Test
    public void PublishBicycle(){
        User user = new User();
        user.setId(1L);
        Bicycle bicycleToPublish = new Bicycle(1L, "Bicicleta 1", "Bicicleta azul", 20.0, "Aro 20", "BMX", "image.jpg",user);

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(bicycleRepository.save(any(Bicycle.class))).thenReturn(bicycleToPublish);


        Bicycle bicyclePublished = bicycleService.createBicycle(user.getId(), bicycleToPublish);

        assertNotNull(bicyclePublished);
        assertEquals(1L, bicyclePublished.getId().longValue());

        verify(userRepository, times(1)).save(any(User.class));
        verify(bicycleRepository, times(1)).save(any(Bicycle.class));
    }

    @Test
    public void EnterBicyclesInformation(){
        Bicycle bicyclePublished = new Bicycle(1L, "Bicicleta 1", "Bicicleta azul", 20.0, "Aro 20", "BMX", "image.jpg",new User());
        Bicycle bicycleUpdated = new Bicycle(1L, "Bicicleta 1", "Bicicleta negra", 30.0, "Aro 20", "BMX", "image.jpg",new User());

        when(bicycleRepository.save(any(Bicycle.class))).thenReturn(bicyclePublished);

        Bicycle bicycleEdited = bicycleService.updateBicycle(1L, bicycleUpdated);

        assertNotNull(bicycleEdited);
        assertNotEquals(bicyclePublished.getBicycleDescription(), bicycleEdited.getBicycleDescription());
        assertNotEquals(bicyclePublished.getBicyclePrice(), bicycleEdited.getBicyclePrice());

        verify(bicycleRepository, times(1)).save(any(Bicycle.class));
    }
}
