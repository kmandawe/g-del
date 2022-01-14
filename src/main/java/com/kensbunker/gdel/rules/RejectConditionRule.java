package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;

public class RejectConditionRule implements ConditionRule {
    private static final Double MAX_WEIGHT = 50.0;
    @Override
    public boolean pass(GParcel parcel) {
        return parcel.getWeight() > MAX_WEIGHT;
    }
}
