package com.fool.microservice.provider.service;

import org.springframework.stereotype.Service;

/**
 * @author fool
 * @date 2021/10/22 16:26
 */
@Service
public class ProviderService {

    public String getFoolValue() {
        int i = 1 / 0;
        return "success";
    }

}
