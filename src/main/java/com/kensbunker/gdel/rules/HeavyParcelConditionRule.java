package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;

public class HeavyParcelConditionRule implements ConditionRule {
    private static final Double MAX_WEIGHT = 10.0;
    @Override
    public boolean pass(GParcel parcel) {
        return parcel.getWeight() > MAX_WEIGHT;
    }
}
