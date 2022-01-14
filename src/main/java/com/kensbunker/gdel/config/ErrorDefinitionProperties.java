package com.kensbunker.gdel.config;

import com.kensbunker.gdel.exception.ErrorDetail;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
@PropertySource(
        factory = YamlPropertySourceFactory.class,
        value = "classpath:error_definitions.yml")
@NoArgsConstructor
@ConfigurationProperties
public class ErrorDefinitionProperties {
    private List<ErrorDetail> errors = new ArrayList<>();
}
