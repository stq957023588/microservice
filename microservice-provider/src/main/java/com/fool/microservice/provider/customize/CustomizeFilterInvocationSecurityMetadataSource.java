package com.fool.microservice.provider.customize;


import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author fool
 * @date 2021/10/21 9:28
 */
@Slf4j
@Component
public class CustomizeFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取请求地址
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        log.debug("Request url:{}", requestUrl);
        // 查询请求路径所需要的权限

        String[] attributes = {"ADMIN"};

        if ("/hello-world".equals(requestUrl)) {
            return SecurityConfig.createList("SUPER");
        }

        if ("/auth-test2".equals(requestUrl)) {
            return SecurityConfig.createList("SUPER");
        }

        return SecurityConfig.createList();
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

