package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.utils.DimensionsUtils;

public class SmallParcelConditionRule implements ConditionRule {
  private static final Integer MAX_VOLUME = 1500;

  @Override
  public boolean pass(GParcel parcel) {
    return DimensionsUtils.getVolume(parcel.getHeight(), parcel.getWidth(), parcel.getLength())
        < MAX_VOLUME;
  }
}
