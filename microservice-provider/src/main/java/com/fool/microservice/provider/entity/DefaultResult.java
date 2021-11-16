package com.fool.microservice.provider.entity;

import lombok.Data;

/**
 * @author fool
 * @date 2021/11/16 14:39
 */
@Data
public class DefaultResult<T> implements Result<T> {

    private int code;

    private String message;

    private T data;

    public DefaultResult() {
    }

    public DefaultResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
