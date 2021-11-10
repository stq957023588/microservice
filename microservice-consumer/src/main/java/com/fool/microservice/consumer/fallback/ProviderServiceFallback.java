package com.fool.microservice.consumer.fallback;

import com.fool.microservice.consumer.service.ProviderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author fool
 * @date 2021/10/25 13:53
 */
@Slf4j
@Component
public class ProviderServiceFallback implements ProviderService {
    @Override
    public String getValue() {
        log.warn("fallback");
        return "fallback";
    }

    @Override
    public String getValue2() {
        return null;
    }
}
