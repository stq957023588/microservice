package com.fool.microservice.auth.service;

import com.fool.microservice.auth.entity.Authority;
import com.fool.microservice.auth.mapper.AuthorityMapper;
import org.springframework.stereotype.Service;

/**
 * @author fool
 * @date 2021/11/10 11:30
 */
@Service
public class AuthorityService {

    private final AuthorityMapper authorityMapper;

    public AuthorityService(AuthorityMapper authorityMapper) {
        this.authorityMapper = authorityMapper;
    }


    public void addAuthority(String authorityName) {
        Authority authority = new Authority();
        authority.setName(authorityName);
        authorityMapper.insertSelective(authority);
    }

}
