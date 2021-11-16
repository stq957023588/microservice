package com.fool.microservice.provider.thread;

import com.alibaba.nacos.common.executor.NameThreadFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author fool
 * @date 2021/11/12 13:17
 */
public class ReentrantLockDemo {
    public static final Lock LOCK = new ReentrantLock();


    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 60, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10), new NameThreadFactory("ReentrantLock"));
        for (int i = 0; i < 3; i++) {
            executor.execute(ReentrantLockDemo::simpleDemo);
        }
        Thread.currentThread().join();
        // executor.execute(ReentrantLockDemo::simpleDemo);
    }

    public static void simpleDemo() {
        try {
            String threadName = Thread.currentThread().getName();
            System.out.println(threadName + " try to get lock ....");
            LOCK.lock();
            System.out.println(threadName + " get lock");
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            System.out.println(Thread.currentThread().getName() + " release lock");
            LOCK.unlock();
        }
    }

}
