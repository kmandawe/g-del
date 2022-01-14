package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;

public class LargeParcelConditionRule implements ConditionRule {

  @Override
  public boolean pass(GParcel parcel) {
    // This is a fallback rule, just pass condition
    return true;
  }
}
