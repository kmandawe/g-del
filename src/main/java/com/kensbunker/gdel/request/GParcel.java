package com.kensbunker.gdel.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@ToString
public class GParcel {
  @NotNull(message = "Weight can't be null")
  private Double weight;

  @NotNull(message = "Height can't be null")
  private Integer height;

  @NotNull(message = "Width can't be null")
  private Integer width;

  @NotNull(message = "Length can't be null")
  private Integer length;
}
