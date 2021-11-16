package com.fool.microservice.provider.thread;

import com.alibaba.nacos.common.executor.NameThreadFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author fool
 * @date 2021/11/11 13:36
 */
public class DynamicChangeThreadPoolSettingDemo {


    public static void main(String[] args) throws InterruptedException {
        dynamicModifyExecutor();
    }

    private static ThreadPoolExecutor buildThreadPoolExecutor() {

        return new ThreadPoolExecutor(2,
                5,
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(10),
                new NameThreadFactory("Fool"));

    }


    private static void dynamicModifyExecutor() throws InterruptedException {
        ThreadPoolExecutor executor = buildThreadPoolExecutor();

        for (int i = 0; i < 15; i++) {
            executor.submit(() -> {
                threadPoolState(executor, "创建任务");
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        threadPoolState(executor, "改变之前");
        /*
        修改线程池的最大线程数,覆盖原有最大线程数,
        判断当前线程数是否大于新的最大线程数
        如果大于,中断闲置的线程
         */
        executor.setMaximumPoolSize(10);
        /*
        修改核心线程数,新的核心线程数必须大于最大线程数
        判断当前线程数是否大于核心线程数,如果是那么中断闲置的线程
        如果新的核心线程数大于原核心线程数,增加工作线程,知道等待队列为空
         */
        executor.setCorePoolSize(10);
        // executor.prestartCoreThread();
        threadPoolState(executor, "改变之后");
        Thread.currentThread().join();

    }

    private static void threadPoolState(ThreadPoolExecutor executor, String name) {
        LinkedBlockingQueue queue = (LinkedBlockingQueue) executor.getQueue();

        System.out.println(new SimpleDateFormat("HH:mm:ss").format(new Date()) + "\t" +
                Thread.currentThread().getName() + "-" + name + ":" +
                "核心线程数:" + executor.getCorePoolSize() +
                "\t活动线程数:" + executor.getActiveCount() +
                "\t最大线程数:" + executor.getMaximumPoolSize() +
                "\t线程池活跃度:" + divide(executor.getActiveCount(), executor.getMaximumPoolSize()) +
                "\t任务完成数:" + executor.getCompletedTaskCount() +
                "\t队列大小:" + (queue.size() + queue.remainingCapacity()) +
                "\t当前排队线程数:" + queue.size() +
                "\t队列剩余大小:" + queue.remainingCapacity() +
                "\t队列使用度:" + divide(queue.size(), queue.size() + queue.remainingCapacity()));


    }

    private static String divide(int num1, int num2) {
        return String.format("%1.2f%%", Double.parseDouble(num1 + "") / Double.parseDouble(num2 + "") * 100);
    }


}
