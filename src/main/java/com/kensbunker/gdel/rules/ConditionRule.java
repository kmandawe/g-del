package com.kensbunker.gdel.rules;

import com.kensbunker.gdel.request.GParcel;

/**
 * This defines the contract for creating a condition rule. A {@link ConditionRule} instance is used
 * in a particular {@link com.kensbunker.gdel.enums.CostRule} which defines the condition to run
 * against the {@link GParcel} instance to check if it should use that {@link
 * com.kensbunker.gdel.enums.CostRule} for computing the cost.
 */
public interface ConditionRule {
    /**
     * Call this method to check if {@link GParcel} passes the condition
     * @param parcel the {@link GParcel} to check for this condition
     * @return true if it passes the condition, false otherwise
     */
    boolean pass(GParcel parcel);

    /**
     * Defines the computation of the cost. For example,
     * multiply 10.0 by the weight of the {@link GParcel}
     * @param parcel, the {@link GParcel} instance
     * @return the computed cost
     */
    double computeCost(GParcel parcel);
}
