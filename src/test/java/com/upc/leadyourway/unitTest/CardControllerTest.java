package com.upc.leadyourway.unitTest;

import com.upc.leadyourway.controller.CardController;
import com.upc.leadyourway.model.Card;
import com.upc.leadyourway.service.CardService;
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

public class CardControllerTest {

    @Mock
    private CardService cardService;

    private CardController cardController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cardController = new CardController(cardService);
    }

    @Test
    public void testGetCardById() {
        // Preparar datos de prueba
        Long cardId = 1L;
        Card card = new Card(/* completar con datos de prueba */);

        // Configurar comportamiento del mock
        when(cardService.getCardById(cardId)).thenReturn(card);

        // Ejecutar el método a probar
        ResponseEntity<Card> response = cardController.getCardById(cardId);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(card, response.getBody());

        // Verificar que se llamó el método del mock correctamente
        verify(cardService, times(1)).getCardById(cardId);
    }

    @Test
    public void testGetCardByUserId() {
        // Preparar datos de prueba
        Long userId = 1L;
        List<Card> cards = Arrays.asList(/* completar con datos de prueba */);

        // Configurar comportamiento del mock
        when(cardService.getCardByUserId(userId)).thenReturn(cards);

        // Ejecutar el método a probar
        ResponseEntity<List<Card>> response = cardController.getCardByUserId(userId);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(cards, response.getBody());

        // Verificar que se llamó el método del mock correctamente
        verify(cardService, times(1)).getCardByUserId(userId);
    }

    @Test
    public void testCreateCard() {
        // Preparar datos de prueba
        Long userId = 1L;
        Card card = new Card(/* completar con datos de prueba */);

        // Configurar comportamiento del mock
        when(cardService.createCard(userId, card)).thenReturn(card);

        // Ejecutar el método a probar
        ResponseEntity<Card> response = cardController.createCard(userId, card);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(card, response.getBody());

        // Verificar que se llamó el método del mock correctamente
        verify(cardService, times(1)).createCard(userId, card);
    }

    @Test
    public void testUpdateCardMain() {
        // Preparar datos de prueba
        Long cardId = 1L;
        boolean cardMain = true;
        Card card = new Card(/* completar con datos de prueba */);

        // Configurar comportamiento del mock
        when(cardService.updateCardMain(cardId, cardMain)).thenReturn(card);

        // Ejecutar el método a probar
        ResponseEntity<Card> response = cardController.updateCardMain(cardId, cardMain);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(card, response.getBody());

        // Verificar que se llamó el método del mock correctamente
        verify(cardService, times(1)).updateCardMain(cardId, cardMain);
    }

    @Test
    public void testDeleteCard() {
        // Preparar datos de prueba
        Long cardId = 1L;

        // Ejecutar el método a probar
        ResponseEntity<Void> response = cardController.deleteCard(cardId);

        // Verificar el resultado
        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

        // Verificar que se llamó el método del mock correctamente
        verify(cardService, times(1)).deleteCard(cardId);
    }

}
