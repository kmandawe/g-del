package com.kensbunker.gdel.aop;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kensbunker.gdel.components.GDelApplicationUtil;
import com.kensbunker.gdel.exception.ErrorDetail;
import com.kensbunker.gdel.exception.ParcelRejectedException;
import com.kensbunker.gdel.utils.ErrorConstants;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

  private final GDelApplicationUtil applicationUtil;
  private final ObjectMapper objectMapper;

  public RestResponseEntityExceptionHandler(
      GDelApplicationUtil applicationUtil, ObjectMapper objectMapper) {
    this.applicationUtil = applicationUtil;
    this.objectMapper = objectMapper;
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException e,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest webRequest) {
    List<String> details = new ArrayList<>();
    for (ObjectError error : e.getBindingResult().getAllErrors()) {
      details.add(error.getDefaultMessage());
    }
    ErrorDetail errorDetail = applicationUtil.getErrorDetail(ErrorConstants.INVALID_PARAMS);
    errorDetail.setMessage(errorDetail.getMessage());
    errorDetail.setDetails(details);
    logAndReturnResponse(e, errorDetail, HttpStatus.BAD_REQUEST);
    return new ResponseEntity<>(errorDetail, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ParcelRejectedException.class)
  public ResponseEntity<ErrorDetail> parcelRejectedException(
      ParcelRejectedException e, WebRequest webRequest) {
    List<String> details = new ArrayList<>();
    details.add(e.getMessage());
    ErrorDetail errorDetail = applicationUtil.getErrorDetail(ErrorConstants.INVALID_PARAMS);
    errorDetail.setDetails(details);
    return logAndReturnResponse(e, errorDetail, HttpStatus.BAD_REQUEST);
  }

  @SneakyThrows
  private ResponseEntity<ErrorDetail> logAndReturnResponse(
      Exception e, ErrorDetail errorDetail, HttpStatus status) {
    log.error(
        "Got an error response: {} with message: {}",
        objectMapper.writeValueAsString(errorDetail),
        e.getMessage());
    return new ResponseEntity<>(errorDetail, status);
  }
}
