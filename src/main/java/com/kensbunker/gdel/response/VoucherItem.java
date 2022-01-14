package com.kensbunker.gdel.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDate;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VoucherItem {
    private String code;
    private Float discount;
    private LocalDate expiry;
}
