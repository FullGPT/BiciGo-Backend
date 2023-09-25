
package com.upc.leadyourway.unitTest;

import com.upc.leadyourway.controller.RentController;
import com.upc.leadyourway.dto.RentDto;
import com.upc.leadyourway.model.Rent;
import com.upc.leadyourway.service.RentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class RentControllerTest {

    @Mock
    private RentService rentService;

    private RentController rentController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        rentController = new RentController(rentService);
    }

    @Test
    public void testGetRentById() {
        // Preparar datos de prueba
        Long rentId = 1L;
        Rent rent = new Rent(/* completar con datos de prueba */);

        // Configurar comportamiento del mock
        when(rentService.getById(rentId)).thenReturn(rent);

        // Ejecutar el método a probar
        ResponseEntity<Rent> response = rentController.getRentById(rentId);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rent, response.getBody());

        // Verificar que se llamó el método del mock correctamente
        verify(rentService, times(1)).getById(rentId);
    }

    @Test
    public void testGetRentByBicycleId() {
        // Preparar datos de prueba
        Long bicycleId = 1L;
        List<Rent> rents = Arrays.asList(/* completar con datos de prueba */);

        // Configurar comportamiento del mock
        when(rentService.getByBicycleId(bicycleId)).thenReturn(rents);

        // Ejecutar el método a probar
        ResponseEntity<List<Rent>> response = rentController.getRentByBicycleId(bicycleId);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rents, response.getBody());

        // Verificar que se llamó el método del mock correctamente
        verify(rentService, times(1)).getByBicycleId(bicycleId);
    }

    @Test
    public void testCreateRent() {
        // Preparar datos de prueba
        RentDto rentDto = new RentDto(/* completar con datos de prueba */);
        Rent rent = new Rent(/* completar con datos de prueba */);

        // Configurar comportamiento del mock
        when(rentService.create(rentDto)).thenReturn(rent);

        // Ejecutar el método a probar
        ResponseEntity<Rent> response = rentController.createRent(rentDto);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(rent, response.getBody());

        // Verificar que se llamó el método del mock correctamente
        verify(rentService, times(1)).create(rentDto);
    }

    @Test
    public void testDeleteRent() {
        // Preparar datos de prueba
        Long rentId = 1L;

        // Ejecutar el método a probar
        ResponseEntity<Void> response = rentController.deleteRent(rentId);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que se llamó el método del mock correctamente
        verify(rentService, times(1)).delete(rentId);
    }

}