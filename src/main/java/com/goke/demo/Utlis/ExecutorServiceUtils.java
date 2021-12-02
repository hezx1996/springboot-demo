package com.goke.demo.Utlis;

import java.util.concurrent.*;

/**
 * @author 隔壁子
 * 线程池
 * @date 2021/1/20 16:28
 */
public class ExecutorServiceUtils {

    /**
     * 核心线程数
     */
    private static int corePoolSize = 100;
    /**
     * 最大线程数
     */
    private static int maximumPoolSize = 1000;
    /**
     * 当线程池中空闲线程数量超过corePoolSize时，多余的线程会在多长时间内被销毁；
     */
    private static int keepAliveTime = 60;
    /***
     *keepAliveTime的单位
     */
    private static TimeUnit unit =  TimeUnit.MILLISECONDS;
    /**
     * 任务队列，被添加到线程池中，但尚未被执行的任务；它一般分为直接提交队列、有界任务队列、无界任务队列、优先任务队列几种
     */
    private static  BlockingQueue<Runnable> workQueue = new SynchronousQueue<Runnable>();
    /**
     * 线程工厂，用于创建线程，一般用默认即可；
     */
    private static ThreadFactory threadFactory = Executors.defaultThreadFactory();
    /**
     *拒绝策略；当任务太多来不及处理时，如何拒绝任务
     */
    private static  RejectedExecutionHandler  handler = new ThreadPoolExecutor.AbortPolicy();

    /***
     * 线程池对象
     */
    ExecutorService  executorService= null;


    /***
     * 构造方法获取线程池
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @param keepAliveTime 多余的线程会在多长时间内被销毁
     * @param unit TimeUnit单位
     * @param workQueue 任务队列
     * @param threadFactory 线程工厂
     * @param handler 拒绝策略
     * @return
     */
    public  ExecutorServiceUtils(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler){
        this.executorService = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);
    }

    /***
     * 构造方法获取线程池
     * @param corePoolSize 核心线程数
     * @param maximumPoolSize 最大线程数
     * @return
     */
    public  ExecutorServiceUtils(int corePoolSize, int maximumPoolSize){
        this.executorService = new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);
    }

    /***
     * 获取线程池
     * @return
     */
    public ExecutorService getExecutor(){
        return this.executorService;
    }

    /***
     * 获取线程池
     * ExecutorServiceUtils.getExecutorService().execute(线程类);
     */
    public static ExecutorService getExecutorService(){
        if (corePoolSize < 0 || maximumPoolSize <= 0 || maximumPoolSize < corePoolSize || keepAliveTime < 0) {
            throw new IllegalArgumentException();
        }
        if (workQueue == null || threadFactory == null || handler == null) {
            throw new NullPointerException();
        }
         return new ThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue,threadFactory,handler);
    }

}
