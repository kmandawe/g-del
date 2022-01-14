package com.kensbunker.gdel.request;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@ToString
public class GParcel {
  @NotNull(message = "Weight can't be null")
  private final Double weight;

  @NotNull(message = "Height can't be null")
  private final Integer height;

  @NotNull(message = "Width can't be null")
  private final Integer width;

  @NotNull(message = "Length can't be null")
  private final Integer length;

  private String voucherCode;

  public GParcel(Double weight, Integer height, Integer width, Integer length) {
    this.weight = weight;
    this.height = height;
    this.width = width;
    this.length = length;
  }
}
