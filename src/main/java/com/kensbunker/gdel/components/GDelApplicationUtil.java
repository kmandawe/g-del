package com.kensbunker.gdel.components;

import com.kensbunker.gdel.exception.ErrorDetail;
import com.kensbunker.gdel.utils.GDelConstants;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import java.util.Map;

@Component
public class GDelApplicationUtil {

  private final ServletContext context;

  public GDelApplicationUtil(ServletContext context) {
    this.context = context;
  }

  public ErrorDetail getErrorDetail(int errorCode) {
    ErrorDetail errorDetail = null;
    if (context != null) {
      Map<Integer, ErrorDetail> errorMap =
          (Map<Integer, ErrorDetail>) context.getAttribute(GDelConstants.ERROR_MAP);
      errorDetail = (errorMap != null) ? errorMap.get(errorCode) : null;
    }
    return errorDetail;
  }
}
