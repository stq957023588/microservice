package com.fool.microservice.auth.service;

import com.fool.microservice.auth.entity.User;
import com.fool.microservice.auth.entity.UserAuthority;
import com.fool.microservice.auth.mapper.AuthorityMapper;
import com.fool.microservice.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author fool
 * @date 2021/11/8 11:25
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserMapper userMapper;

    private final AuthorityMapper authorityMapper;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserDetailsServiceImpl(UserMapper userMapper, AuthorityMapper authorityMapper,
                                  PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.authorityMapper = authorityMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userMapper.selectByUsername(username);
        if (user == null) {
            user = userMapper.selectByEmail(username);
        }

        Set<UserAuthority> authorities = authorityMapper.selectByUserId(user.getId());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    public void add(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));

        userMapper.insertSelective(user);
    }

}
