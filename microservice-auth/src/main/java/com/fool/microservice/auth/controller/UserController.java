package com.fool.microservice.auth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author fool
 * @date 2021/11/8 11:27
 */
@Slf4j
@RequestMapping("/users")
@RestController
public class UserController {

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public Principal getUser(Principal principal) {
        log.info("User:{}", principal.toString());
        return principal;
    }

}
