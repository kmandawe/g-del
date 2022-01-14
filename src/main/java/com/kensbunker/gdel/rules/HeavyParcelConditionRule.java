package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;

public class HeavyParcelConditionRule implements ConditionRule {
  private static final Double MAX_WEIGHT = 10.0;
  private static final Double MULTIPLIER_PHP = 20.0;

  @Override
  public boolean pass(GParcel parcel) {
    return parcel.getWeight() > MAX_WEIGHT;
  }

  @Override
  public double computeCost(GParcel parcel) {
    return parcel.getWeight() * MULTIPLIER_PHP;
  }
}
