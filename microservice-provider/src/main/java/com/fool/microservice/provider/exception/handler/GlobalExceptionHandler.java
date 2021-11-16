package com.fool.microservice.provider.exception.handler;

import com.fool.microservice.provider.entity.DefaultResult;
import com.fool.microservice.provider.entity.Result;
import com.fool.microservice.provider.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fool
 * @date 2021/11/16 14:33
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({Exception.class})
    public DefaultResult<Object> handle(Exception e, HttpServletRequest request) {
        log.error("Catch exception:{}.Ip:{},Path:{}", e.getMessage(), IpUtils.getIpAddr(request), request.getServletPath(), e);
        return new DefaultResult<>(Result.ERROR, e.getMessage(), null);
    }

}
