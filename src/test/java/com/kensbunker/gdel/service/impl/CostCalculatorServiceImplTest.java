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

    @Autowired
    private CostCalculatorService costCalculatorService;

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
}