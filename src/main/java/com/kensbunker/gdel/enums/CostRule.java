package com.kensbunker.gdel.enums;

import com.kensbunker.gdel.rules.*;
import lombok.Getter;

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
