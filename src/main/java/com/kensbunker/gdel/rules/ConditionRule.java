package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;

public interface ConditionRule {
    boolean pass(GParcel parcel);
    double computeCost(GParcel parcel);
}
