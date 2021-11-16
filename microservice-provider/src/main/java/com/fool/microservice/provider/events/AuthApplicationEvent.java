package com.fool.microservice.provider.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author fool
 * @date 2021/11/10 17:06
 */
@Getter
public class AuthApplicationEvent extends ApplicationEvent {

    private final String value;


    /**
     * Create a new {@code ApplicationEvent}.
     *
     * @param source the object on which the event initially occurred or with
     *               which the event is associated (never {@code null})
     */
    public AuthApplicationEvent(String source) {
        super(source);
        this.value = source;
    }
}
