package com.upc.leadyourway.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.leadyourway.dto.AuthenticationResponse;
import com.upc.leadyourway.dto.RegisterRequest;
import com.upc.leadyourway.model.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthentificationIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testRegisterStudent() throws Exception {
        // Crear un objeto RegisterRequest con datos de prueba
        RegisterRequest registerRequest = RegisterRequest.builder()
                .userFirstName("Nombre")
                .userLastName("Apellido")
                .userEmail("correo@example.com")
                .userPassword("contraseña")
                .userPhone("123456789")
                .imageData("imagen")
                .role(Roles.USER)
                .build();

        // Convertir el objeto a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(registerRequest);

        // Realizar una solicitud POST al endpoint /api/leadyourway/v1/auth/register
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/leadyourway/v1/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated()) // Espera una respuesta HTTP 201 Created
                .andReturn();

        // Verificar el contenido de la respuesta
        String responseBody = mvcResult.getResponse().getContentAsString();
        AuthenticationResponse authenticationResponse = objectMapper.readValue(responseBody, AuthenticationResponse.class);

        // Realiza las aserciones necesarias para verificar el resultado

        // Otras aserciones según tu lógica de negocio
    }
}