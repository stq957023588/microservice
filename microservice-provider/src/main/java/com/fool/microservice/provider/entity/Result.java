package com.fool.microservice.provider.entity;

/**
 * @author fool
 * @date 2021/11/16 14:38
 */
public interface Result<T> {
    int SUCCESS = 200;

    int FAILED = 300;

    int ACCESS_DENIED = 401;

    int AUTH_FAILED = 402;

    int ERROR = 500;


    void setCode(int code);

    int getCode();

    void setMessage(String message);

    String getMessage();

    void setData(T data);

    T getData();
}
