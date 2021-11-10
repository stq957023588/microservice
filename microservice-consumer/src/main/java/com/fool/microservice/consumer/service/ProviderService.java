package com.fool.microservice.consumer.service;

import com.fool.microservice.consumer.fallback.factory.ProviderServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author fool
 * @date 2021/10/25 11:42
 */
// @FeignClient(name = "provider", fallback = ProviderServiceFallback.class)
@FeignClient(name = "provider", fallbackFactory = ProviderServiceFallbackFactory.class)
public interface ProviderService {

    @RequestMapping("/getvalue")
    String getValue();

    @RequestMapping("/getvalue2")
    String getValue2();

}
