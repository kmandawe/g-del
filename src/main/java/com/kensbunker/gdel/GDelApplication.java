package com.kensbunker.gdel;

import com.kensbunker.gdel.components.ApplicationDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@SpringBootApplication
public class GDelApplication implements ServletContextInitializer {

  @Autowired private ApplicationDefaults applicationDefaults;

  public static void main(String[] args) {
    SpringApplication.run(GDelApplication.class, args);
  }

  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {
    applicationDefaults.initialize();
  }
}
