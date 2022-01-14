package com.kensbunker.gdel.service.impl;

import com.kensbunker.gdel.enums.CostRule;
import com.kensbunker.gdel.request.GParcel;
import com.kensbunker.gdel.response.VoucherItem;
import com.kensbunker.gdel.service.CostCalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class CostCalculatorServiceImpl implements CostCalculatorService {

  private static final String VOUCHER_API = "/voucher/";
  private static final String API_KEY = "apikey";
  private final RestTemplate myntRestTemplate;

  public CostCalculatorServiceImpl(RestTemplate myntRestTemplate) {
    this.myntRestTemplate = myntRestTemplate;
  }

  @Override
  public double computeCost(GParcel parcel) {
    CostRule costRule = getCostRule(parcel);
    return costRule.getConditionRule().computeCost(parcel);
  }

  @Override
  public double computeCost(GParcel parcel, String voucherCode) {
    double costWithoutDiscount = computeCost(parcel);
    log.info("Cost without discount is: {}", costWithoutDiscount);
    Map<String, String> params = new HashMap<>();
    params.put("key", API_KEY);

    // Query parameters
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(VOUCHER_API + voucherCode)
            // Add query parameter
            .queryParam("key", API_KEY);

    VoucherItem voucherItem = myntRestTemplate.getForObject(builder.build().toUri().toString() , VoucherItem.class);
    float discount = voucherItem.getDiscount();
    double finalCost = costWithoutDiscount - discount;
    log.info(
        "Discount for voucher code {} is: {}; Final cost is {}",
        voucherCode,
        voucherItem.getDiscount(),
        finalCost);
    return finalCost;
  }

  @Override
  public CostRule getCostRule(GParcel parcel) {
    CostRule[] costRules = CostRule.values();
    for (CostRule costRule : costRules) {
      if (costRule.getConditionRule().pass(parcel)) {
        return costRule;
      }
    }
    // fallback
    return costRules[costRules.length - 1];
  }
}
