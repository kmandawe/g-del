package com.kensbunker.gdel.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GParcel {
    private Double weight;
    private Integer height;
    private Integer width;
    private Integer length;
}
