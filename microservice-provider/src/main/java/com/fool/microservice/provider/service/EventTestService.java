package com.fool.microservice.provider.service;

import com.fool.microservice.provider.events.AuthApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @author fool
 * @date 2021/11/10 17:11
 */
@Slf4j
@Service
public class EventTestService {

    private final ApplicationEventPublisher eventPublisher;

    public EventTestService(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    public void test(String str) {

        AuthApplicationEvent authApplicationEvent = new AuthApplicationEvent(str);

        log.info("Publish auth event");
        eventPublisher.publishEvent(authApplicationEvent);
    }

}
