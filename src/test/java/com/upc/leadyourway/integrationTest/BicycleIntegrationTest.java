package com.upc.leadyourway.integrationTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.upc.leadyourway.model.Bicycle;
import com.upc.leadyourway.model.User;
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
public class BicycleIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testPublishBicycle() throws Exception {
        Bicycle bicycle = Bicycle.builder()
                .id(1L)
                .bicycleName("Bike 1")
                .bicycleDescription("Blue Bike")
                .bicycleModel("BMX")
                .bicyclePrice(200.0)
                .bicycleSize("Big")
                .imageData("image.jpg")
                .user(new User())
                .build();
        ObjectMapper objectMapper = new ObjectMapper();
        String requestBody = objectMapper.writeValueAsString(bicycle);

        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/api/leadyourway/v1/bicycles/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = mvcResult.getResponse().getContentAsString();

    }



}
