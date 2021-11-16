package com.fool.microservice.provider.config;

import io.micrometer.core.instrument.util.NamedThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fool
 * @date 2021/11/11 10:45
 */
@Configuration
public class BeanConfig {

    @Bean("eventThreadPool")
    ThreadPoolExecutor threadPoolExecutor() {
        return new ThreadPoolExecutor(5, 5, 1, TimeUnit.MINUTES, new LinkedBlockingDeque<>(), new NamedThreadFactory("event"));
    }
}
