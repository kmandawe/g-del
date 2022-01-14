package com.kensbunker.gdel.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"success", "data", "message"})
public class ApiResponse<T> implements Serializable {

    // Default no-arg constructor
    public ApiResponse() {}

    // `success` automatically set to true
    public ApiResponse(T data) {
        this.data = data;
        this.success = true;
    }

    public Boolean success;
    public T data;
    public String message;
}