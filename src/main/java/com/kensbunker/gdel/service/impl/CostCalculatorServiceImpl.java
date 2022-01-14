package com.kensbunker.gdel.service.impl;

import com.kensbunker.gdel.enums.CostRule;
import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.service.CostCalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CostCalculatorServiceImpl implements CostCalculatorService {
  @Override
  public double computeCost(GParcel parcel) {
    CostRule costRule = getCostRule(parcel);
    return costRule.getConditionRule().computeCost(parcel);
  }

  @Override
  public CostRule getCostRule(GParcel parcel) {
    CostRule[] costRules = CostRule.values();
    for (CostRule costRule : costRules) {
      if (costRule.getConditionRule().pass(parcel)) {
        return costRule;
      }
    }
    // fallback
    return costRules[costRules.length - 1];
  }
}
