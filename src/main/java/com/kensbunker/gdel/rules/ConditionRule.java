package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;

@FunctionalInterface
public interface ConditionRule {
    boolean pass(GParcel parcel);
}
