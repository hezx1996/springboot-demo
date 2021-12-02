package com.goke.demo.Utlis;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author 隔壁子
 * 线程池
 * @date 2020/11/17 18:03
 */
@Configuration
@EnableAsync
public class TaskExecutorConfig implements AsyncConfigurer {
    /**
     * 设置ThreadPoolExecutor的核心线程数
     */
    private static final int CORE_POOL_SIZE = 10;
    /**
     * 设置ThreadPoolExecutor的最大线程数
     */
    private static final int MAX_POOL_SIZE = 10;
    /**
     * 设置ThreadPoolExecutor的BlockingQueue的容量。缓冲队列：用来缓冲执行任务的队列
     */
    private static final int QUEUE_CAPACITY = 500;
    /**
     * 允许线程的空闲时间60秒：当超过了核心线程之外的线程在空闲时间到达之后会被销毁
     */
    private static final int  KEEPA_LINE_SECODS = 60;
    /**
     * 通过重写getAsyncExecutor方法，制定默认的任务执行由该方法产生
     *
     * 配置类实现AsyncConfigurer接口并重写getAsyncExcutor方法，并返回一个ThreadPoolTaskExevutor
     * 这样我们就获得了一个基于线程池的TaskExecutor
     */
    @Override
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(CORE_POOL_SIZE);
        taskExecutor.setMaxPoolSize(MAX_POOL_SIZE);
        taskExecutor.setQueueCapacity(QUEUE_CAPACITY);
        taskExecutor.setKeepAliveSeconds(KEEPA_LINE_SECODS);
        // 缓冲队列满了之后的拒绝策略：由调用线程处理（一般是主线程）
        taskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        taskExecutor.initialize();
        return taskExecutor;
    }
}
