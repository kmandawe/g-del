package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.exception.ParcelRejectedException;
import com.kensbunker.gdel.request.GParcel;

public class RejectConditionRule implements ConditionRule {
  private static final Double MAX_WEIGHT = 50.0;

  @Override
  public boolean pass(GParcel parcel) {
    return parcel.getWeight() > MAX_WEIGHT;
  }

  @Override
  public double computeCost(GParcel parcel) {
    // this condition should reject
    throw new ParcelRejectedException("Weight should not exceed " + MAX_WEIGHT + "kg");
  }
}
