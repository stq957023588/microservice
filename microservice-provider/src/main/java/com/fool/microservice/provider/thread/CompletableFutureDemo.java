package com.fool.microservice.provider.thread;

import io.micrometer.core.instrument.util.NamedThreadFactory;

import java.util.concurrent.*;

/**
 * @author fool
 * @date 2021/11/12 15:52
 */
public class CompletableFutureDemo {

    private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(2, 5, 60, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(10), new NamedThreadFactory("CompletableFutureDemo"));

    public static void main(String[] args) {
        test();
    }


    public static void test() {
        CompletableFuture<String> resultFuture = new CompletableFuture<>();


        EXECUTOR.submit(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("execute complete");
            resultFuture.complete("AAAAA");
        });

        EXECUTOR.submit(() -> {

            String s = null;
            try {
                System.out.println("wait execute result");
                s = resultFuture.get();
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(s);

        });
        try {
            Thread.currentThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}
