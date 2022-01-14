package com.kensbunker.gdel.service.impl;

import com.kensbunker.gdel.enums.CostRule;
import com.kensbunker.gdel.exception.ParcelRejectedException;
import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.service.CostCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles(value = "test")
class CostCalculatorServiceImplTest {

  @Autowired private CostCalculatorService costCalculatorService;

  @Test
  public void testRejectCondition() {
    GParcel parcel = new GParcel(51.0, 50, 50, 50);
    assertEquals(CostRule.REJECT, costCalculatorService.getCostRule(parcel));
  }

  @Test
  public void testHeavyParcelCondition() {
    GParcel parcel = new GParcel(50.0, 50, 50, 50);
    assertEquals(CostRule.HEAVY_PARCEL, costCalculatorService.getCostRule(parcel));
  }

  @Test
  public void testSmallParcelCondition() {
    GParcel parcel = new GParcel(10.0, 10, 10, 10);
    assertEquals(CostRule.SMALL_PARCEL, costCalculatorService.getCostRule(parcel));
  }

  @Test
  public void testMediumParcelCondition() {
    GParcel parcel = new GParcel(10.0, 10, 20, 10);
    assertEquals(CostRule.MEDIUM_PARCEL, costCalculatorService.getCostRule(parcel));
  }

  @Test
  public void testLargeParcelCondition() {
    GParcel parcel = new GParcel(10.0, 10, 20, 20);
    assertEquals(CostRule.LARGE_PARCEL, costCalculatorService.getCostRule(parcel));
  }

  @Test
  public void testRejectionComputeCost() {
    assertThrows(
        ParcelRejectedException.class,
        () -> {
          GParcel parcel = new GParcel(51.0, 50, 50, 50);
          costCalculatorService.computeCost(parcel);
        });
  }

  @Test
  public void testHeavyParcelComputeCost() {
    GParcel parcel = new GParcel(50.0, 50, 50, 50);
    double expectedCost = 1000.0;
    assertEquals(expectedCost, costCalculatorService.computeCost(parcel));
  }

  @Test
  public void testSmallParcelComputeCost() {
    GParcel parcel = new GParcel(10.0, 10, 10, 10);
    double expectedCost = 30.0;
    assertEquals(expectedCost, costCalculatorService.computeCost(parcel));
  }

  @Test
  public void testMediumParcelComputeCost() {
    GParcel parcel = new GParcel(10.0, 10, 20, 10);
    double expectedCost = 80.0;
    assertEquals(expectedCost, costCalculatorService.computeCost(parcel));
  }

  @Test
  public void testLargeParcelComputeCost() {
    GParcel parcel = new GParcel(10.0, 10, 20, 20);
    double expectedCost = 200.0;
    assertEquals(expectedCost, costCalculatorService.computeCost(parcel));
  }

  @Test
  public void testLargeParcelComputeCostWithDiscount() {
    GParcel parcel = new GParcel(10.0, 10, 20, 20);
    double expectedCost = 187.75;
    double discountedCost = costCalculatorService.computeCost(parcel, "MYNT");
    assertEquals(expectedCost, discountedCost);
  }
}
