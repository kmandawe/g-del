package com.kensbunker.gdel.controller;

import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.response.ApiResponse;
import com.kensbunker.gdel.service.CostCalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Slf4j
@RequestMapping("/api/gdel/cost")
public class CostCalculatorController {
  private final CostCalculatorService costCalculatorService;

  public CostCalculatorController(CostCalculatorService costCalculatorService) {
    this.costCalculatorService = costCalculatorService;
  }

  @PostMapping
  public ApiResponse<Double> computeDeliveryCost(@Valid @RequestBody GParcel parcel) {
    log.info("Compute delivery cost request: {}", parcel);
    Double cost = costCalculatorService.computeCost(parcel);
    log.info("Compute delivery cost response data: {}", cost);
    return new ApiResponse<>(cost);
  }
}
