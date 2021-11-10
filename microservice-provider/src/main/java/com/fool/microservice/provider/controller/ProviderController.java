package com.fool.microservice.provider.controller;

import com.fool.microservice.provider.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fool
 * @date 2021/10/25 11:15
 */
@RestController
public class ProviderController {

    private ProviderService providerService;

    @Autowired
    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }

    @RequestMapping(value = "/getvalue", method = RequestMethod.GET)
    public String getValue() {
        return providerService.getFoolValue();
    }

    @RequestMapping(value = "auth-test", method = RequestMethod.GET)
    public String authTest() {
        return "Auth test";
    }

    @RequestMapping(value = "auth-test2", method = RequestMethod.GET)
    public String authTest2() {
        return "Auth test";
    }
}
