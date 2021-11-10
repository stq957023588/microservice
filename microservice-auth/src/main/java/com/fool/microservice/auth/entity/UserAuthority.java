package com.fool.microservice.auth.entity;

import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;

/**
 * @author fool
 * @date 2021/11/9 16:39
 */
@EqualsAndHashCode
public class UserAuthority implements GrantedAuthority {

    private String authority;

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }
}
