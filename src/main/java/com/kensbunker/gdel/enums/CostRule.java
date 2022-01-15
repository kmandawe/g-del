package com.kensbunker.gdel.enums;

import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.rules.*;
import lombok.Getter;

/**
 * Defines the different rules for Cost Computation of a {@link GParcel} Since we are using an enum,
 * ordinal should be preserved and defines the precedence on which rules should be evaluated first.
 */
@Getter
public enum CostRule {
  REJECT("Reject", new RejectConditionRule()),
  HEAVY_PARCEL("Heavy Parcel", new HeavyParcelConditionRule()),
  SMALL_PARCEL("Small Parcel", new SmallParcelConditionRule()),
  MEDIUM_PARCEL("Medium Parcel", new MediumParcelConditionRule()),
  LARGE_PARCEL("Large Parcel", new LargeParcelConditionRule());
  private String label;
  private ConditionRule conditionRule;

  CostRule(String label, ConditionRule conditionRule) {
    this.label = label;
    this.conditionRule = conditionRule;
  }
}
