package com.kensbunker.gdel.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"code", "name", "message", "details"})
public class ErrorDetail {

  private int code;
  private String name;
  private String message;
  private List<String> details;

  public ErrorDetail(int code, String name, String message) {
    super();
    this.code = code;
    this.name = name;
    this.message = message;
  }
}
