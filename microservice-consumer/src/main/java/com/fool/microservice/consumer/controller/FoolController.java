package com.fool.microservice.consumer.controller;

import com.fool.microservice.consumer.property.FoolProperty;
import com.fool.microservice.consumer.service.ProviderService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author fool
 * @date 2021/10/22 16:37
 */
@RestController
public class FoolController {

    private FoolProperty foolProperty;

    private ProviderService providerService;

    public RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    public void setProviderService(ProviderService providerService) {
        this.providerService = providerService;
    }

    @Autowired
    private void setFoolProperty(FoolProperty foolProperty) {
        this.foolProperty = foolProperty;
    }

    @RequestMapping(value = "value", method = RequestMethod.GET)
    public String getFoolValue() {
        return foolProperty.getValue();
    }


    @RequestMapping(value = "provider", method = RequestMethod.GET)
    public String provider() {
        return providerService.getValue();
    }

    @RequestMapping(value = "provider2", method = RequestMethod.GET)
    public String provider2() {
        return restTemplate.getForObject("http://provider/getvalue", String.class);
    }


    @LoadBalanced
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        String s = DigestUtils.md5Hex("HXdev@123456");
        System.out.println(s);
    }
}
