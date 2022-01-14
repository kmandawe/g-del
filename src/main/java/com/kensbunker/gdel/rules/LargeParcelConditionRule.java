package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.utils.DimensionsUtils;

public class LargeParcelConditionRule implements ConditionRule {

  private static final Double MULTIPLIER_PHP = 0.05;

  @Override
  public boolean pass(GParcel parcel) {
    // This is a fallback rule, just pass condition
    return true;
  }

  @Override
  public double computeCost(GParcel parcel) {
    return MULTIPLIER_PHP * DimensionsUtils.getVolume(parcel);
  }
}
