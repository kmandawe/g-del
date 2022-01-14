package com.kensbunker.gdel.config;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;

@Configuration
public class RestTemplateConfig {

  @Value("${api.mynt.host.baseurl}")
  private String apiHost;

  @Bean
  public RestTemplate myntRestTemplate() {
    RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory());
    restTemplate.setUriTemplateHandler(new DefaultUriBuilderFactory(apiHost));
    return restTemplate;
  }

  @Bean
  @ConditionalOnMissingBean
  public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
    HttpComponentsClientHttpRequestFactory clientHttpRequestFactory =
        new HttpComponentsClientHttpRequestFactory();
    clientHttpRequestFactory.setHttpClient(httpClient());
    return clientHttpRequestFactory;
  }

  @Bean
  public CloseableHttpClient httpClient() {
    return HttpClientBuilder.create().build();
  }
}
