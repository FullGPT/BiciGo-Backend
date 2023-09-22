package com.upc.leadyourway.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.leadyourway.dto.AuthenticationResponse;
import com.upc.leadyourway.dto.LoginRequest;
import com.upc.leadyourway.dto.RegisterRequest;
import com.upc.leadyourway.model.Roles;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
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
                .userFirstName("Alex")
                .userLastName("Milton")
                .userEmail("correalexo@example.com")
                .userPassword("contraseña123")
                .userPhone("12341235678")
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
    }
    @Test
    public void testLogin() throws Exception {
        // Crear un objeto LoginRequest con datos de prueba
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("correo@example.com");
        loginRequest.setUserPassword("contraseña");

        // Convertir el objeto a JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(loginRequest);

        // Realizar una solicitud POST al endpoint /api/leadyourway/v1/auth/login
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/leadyourway/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk()) // Espera una respuesta HTTP 200 OK
                .andReturn();

        // Verificar el contenido de la respuesta
        String responseBody = mvcResult.getResponse().getContentAsString();
        // Aquí puedes analizar el cuerpo de la respuesta JSON para verificar que el inicio de sesión fue exitoso.
        // Por ejemplo, puedes verificar si el token de acceso se encuentra en la respuesta.
    }
}