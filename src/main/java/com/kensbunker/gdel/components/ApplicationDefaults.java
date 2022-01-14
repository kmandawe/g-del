package com.kensbunker.gdel.components;

import com.kensbunker.gdel.config.ErrorDefinitionProperties;
import com.kensbunker.gdel.exception.ErrorDetail;
import com.kensbunker.gdel.utils.GDelConstants;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ApplicationDefaults {

  private final ErrorDefinitionProperties errorDefinitionProperties;
  private final ServletContext context;

  public ApplicationDefaults(
      ErrorDefinitionProperties errorDefinitionProperties, ServletContext context) {
    this.errorDefinitionProperties = errorDefinitionProperties;
    this.context = context;
  }

  public void initialize() {
    setErrorCodeInContext(errorDefinitionProperties.getErrors());
  }

  private void setErrorCodeInContext(List<ErrorDetail> errorDetails) {
    Map<Integer, ErrorDetail> errorMap = new HashMap<>();
    errorDetails.forEach((error -> errorMap.put(error.getCode(), error)));
    context.setAttribute(GDelConstants.ERROR_MAP, errorMap);
  }
}
