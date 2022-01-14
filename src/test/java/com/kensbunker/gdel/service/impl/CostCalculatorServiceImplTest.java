package com.kensbunker.gdel.service.impl;

import com.kensbunker.gdel.enums.CostRule;
import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.service.CostCalculatorService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
