package com.kensbunker.gdel.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kensbunker.gdel.GDelApplication;
import com.kensbunker.gdel.request.GParcel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(
    classes = GDelApplication.class,
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = GDelApplication.class, loader = SpringBootContextLoader.class)
@ActiveProfiles(value = "test")
@AutoConfigureMockMvc
public class CostCalculatorControllerTest {

  private static final String COST_ENDPOINT = "/api/gdel/cost";

  @Autowired protected MockMvc mockMvc;

  @Test
  public void computeCostRejectTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(51.0, 50, 50, 50);
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(4001))
        .andExpect(jsonPath("$.details", hasItem("Weight should not exceed 50.0kg")));
  }

  @Test
  public void computeCostHeavyParcelTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(50.0, 50, 50, 50);
    double expectedCost = 1000.0;
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(expectedCost));
  }

  @Test
  public void computeCostSmallParcelTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(10.0, 10, 10, 10);
    double expectedCost = 30.0;
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(expectedCost));
  }

  @Test
  public void computeCostMediumParcelTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(10.0, 10, 20, 10);
    double expectedCost = 80.0;
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(expectedCost));
  }

  @Test
  public void computeCostLargeParcelTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(10.0, 10, 20, 20);
    double expectedCost = 200.0;
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(expectedCost));
  }

  @Test
  public void computeCostMissingWeightTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(null, 10, 20, 20);
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(4001))
        .andExpect(jsonPath("$.message").value("JSON parameter is invalid"))
        .andExpect(jsonPath("$.details", hasItem("Weight can't be null")));
  }

  @Test
  public void computeCostMissingHeightTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(50.0, null, 20, 20);
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(4001))
        .andExpect(jsonPath("$.message").value("JSON parameter is invalid"))
        .andExpect(jsonPath("$.details", hasItem("Height can't be null")));
  }

  @Test
  public void computeCostMissingWidthTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(50.0, 10, null, 20);
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(4001))
        .andExpect(jsonPath("$.message").value("JSON parameter is invalid"))
        .andExpect(jsonPath("$.details", hasItem("Width can't be null")));
  }

  @Test
  public void computeCostMissingLengthTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(50.0, 10, 10, null);
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isBadRequest())
        .andExpect(jsonPath("$.code").value(4001))
        .andExpect(jsonPath("$.message").value("JSON parameter is invalid"))
        .andExpect(jsonPath("$.details", hasItem("Length can't be null")));
  }

  @Test
  public void computeCostLargeParcelWithDiscountTest() throws Exception {

    // Given
    GParcel parcel = new GParcel(10.0, 10, 20, 20);
    parcel.setVoucherCode("MYNT");
    double expectedCost = 187.75;
    String parcelPayload = getParcelPayload(parcel);

    // When
    RequestBuilder requestBuilder =
        MockMvcRequestBuilders.post(COST_ENDPOINT)
            .accept(MediaType.APPLICATION_JSON)
            .content(parcelPayload)
            .contentType(MediaType.APPLICATION_JSON);

    // Then
    mockMvc
        .perform(requestBuilder)
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.success").value(true))
        .andExpect(jsonPath("$.data").value(expectedCost));
  }

  private String getParcelPayload(GParcel parcel) throws JsonProcessingException {
    ObjectMapper objMapper = new ObjectMapper();
    return objMapper.writeValueAsString(parcel);
  }
}
