package com.goke.demo.Utlis;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @author 隔壁子
 * @date 2021/1/20 17:28
 */
public class ThreadTask implements Runnable {

    /***
     * 对象
     */
    private Object ob;

    /**
     * 线程记数
     */
    private CountDownLatch countDownLatch;


    public ThreadTask(Object ob, CountDownLatch countDownLatch) {
        this.ob=ob;
        this.countDownLatch=countDownLatch;
    }

    @Override
    public void run() {
     if(null==ob){
         System.out.println("参数为空了");
       return;
     }
        List<String> list = (List<String>) ob;
        for (String s : list) {
            System.out.println("线程名字"+Thread.currentThread().getName()+"--------"+s);
        }
        //将计数器值减1
        countDownLatch.countDown();
        System.out.println("线程名字"+Thread.currentThread().getName()+"结束了");
    }
}
