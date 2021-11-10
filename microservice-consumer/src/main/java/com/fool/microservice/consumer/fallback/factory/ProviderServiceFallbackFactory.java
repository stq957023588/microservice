package com.fool.microservice.consumer.fallback.factory;

import com.fool.microservice.consumer.service.ProviderService;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author fool
 * @date 2021/10/26 16:16
 */
@Slf4j
@Component
public class ProviderServiceFallbackFactory implements FallbackFactory<ProviderService> {
    @Override
    public ProviderService create(Throwable throwable) {
        log.error("Call provider service error", throwable);
        return new ProviderService() {
            @Override
            public String getValue() {
                return "fallback factory ";
            }

            @Override
            public String getValue2() {
                return null;
            }
        };
    }
}
