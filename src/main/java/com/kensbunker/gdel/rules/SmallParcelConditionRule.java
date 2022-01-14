package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.utils.DimensionsUtils;

public class SmallParcelConditionRule implements ConditionRule {
  private static final Integer MAX_VOLUME = 1500;
  private static final Double MULTIPLIER_PHP = 0.03;

  @Override
  public boolean pass(GParcel parcel) {
    return DimensionsUtils.getVolume(parcel) < MAX_VOLUME;
  }

  @Override
  public double computeCost(GParcel parcel) {
    return MULTIPLIER_PHP * DimensionsUtils.getVolume(parcel);
  }
}
