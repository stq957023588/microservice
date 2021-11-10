package com.fool.microservice.consumer.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author fool
 * @date 2021/10/22 16:26
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "fool")
public class FoolProperty {

    private String value;

}
