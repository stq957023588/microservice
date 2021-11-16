package com.fool.microservice.provider.thread;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author fool
 * @date 2021/11/12 13:48
 */
public class ThreadJoinDemo {

    public static void main(String[] args) throws InterruptedException {
        timeLimitedDemo();
    }

    public static void timeLimitedDemo() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + "executing...");
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + "executed");
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + "waiting thread die");
        // 当前线程等待调用join方法的线程结束,等待10秒后结束等待
        thread.join(1000 * 5);
        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + "thread is die or time out");
    }


    public static void noTimeLimitedDemo() throws InterruptedException {
        Runnable runnable = () -> {
            System.out.println("executing...");
            try {
                Thread.sleep(1000 * 10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("executed");
        };

        Thread thread = new Thread(runnable);
        thread.start();

        System.out.println("waiting thread die");
        // 当前线程等待调用join方法的线程结束
        thread.join();
        System.out.println("thread is die");
    }

}
