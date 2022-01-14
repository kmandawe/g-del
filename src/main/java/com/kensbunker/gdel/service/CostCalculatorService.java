package com.kensbunker.gdel.service;

import com.kensbunker.gdel.enums.CostRule;
import com.kensbunker.gdel.request.GParcel;

public interface CostCalculatorService {
    double computeCost(GParcel parcel);
    CostRule getCostRule(GParcel parcel);
}
