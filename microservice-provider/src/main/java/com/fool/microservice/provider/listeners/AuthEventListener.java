package com.fool.microservice.provider.listeners;

import com.fool.microservice.provider.events.AuthApplicationEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * @author fool
 * @date 2021/11/10 17:13
 */
@Slf4j
@Component
public class AuthEventListener {


    @Async("eventThreadPool")
    @EventListener(value = AuthApplicationEvent.class, condition = "#authApplicationEvent.value == 'A'")
    public CompletableFuture<AuthApplicationEvent> authApplicationEventValueA(AuthApplicationEvent authApplicationEvent) {
        try {
            log.info("AUTH 1 :{}", authApplicationEvent.getValue());
        } catch (Exception e) {
            // 当返回值不为空时,将被下一个对应的Listener监听到
            return CompletableFuture.completedFuture(new AuthApplicationEvent("B"));
        }
        // 返回值为控制结束发布
        return CompletableFuture.completedFuture(null);
    }


    @Async("eventThreadPool")
    @EventListener(value = AuthApplicationEvent.class, condition = "#authApplicationEvent.value == 'B'")
    public CompletableFuture<AuthApplicationEvent> authApplicationEventValueB(AuthApplicationEvent authApplicationEvent) {
        log.info("AUTH 1 :{}", authApplicationEvent.getValue());
        return CompletableFuture.completedFuture(null);
    }
}
