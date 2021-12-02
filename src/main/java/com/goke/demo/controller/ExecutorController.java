package com.goke.demo.controller;

import com.goke.demo.Utlis.ExecutorServiceUtils;
import com.goke.demo.Utlis.ThreadTask;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 隔壁子
 * 调用线程池
 * @date 2021/1/20 17:21
 */
@RestController
@RequestMapping("/executor")
public class ExecutorController {

    private static List<String> list = new ArrayList<>();
    static {
        for (int i = 0; i <100; i++) {
            list.add("我是你第"+i+"个女朋友");
        }
    }

    @RequestMapping("/test")
   public String executorTest(){

        List<String> list = ExecutorController.list;
       //计算组数量
        int size = list.size();
       //偏移量
        int count = 100;
        //100个放一个线程
        int total = (int) Math.ceil((double) size / count);
        //countDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行 jdk1.5以后引入
        CountDownLatch countDownLatch = new CountDownLatch(total);

        //构造函数创建对象
        /*ExecutorServiceUtils serviceUtils = new ExecutorServiceUtils(50, 1000, 60, TimeUnit.MILLISECONDS, new SynchronousQueue<Runnable>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        ExecutorService executorService = serviceUtils.getExecutor();*/

        //构造函数创建对象
         ExecutorServiceUtils serviceUtils = new ExecutorServiceUtils(50, 1000);
         ExecutorService executorService = serviceUtils.getExecutor();
        //方法构建对象
        //ExecutorService executorService = ExecutorServiceUtils.getExecutorService();

        System.out.println("线程开始了");
        //注意方法构建对象时 这里的循环数必须小于等于线程池最大线程数 最好写活
        for (int i = 0; i <total; i++) {
            List<String> cutList;
            if (i == total - 1) {
                cutList = list.subList(count * i, size);
            } else {
                cutList = list.subList(count * i, count * (i + 1));
            }
            //线程池启动线程
            executorService.execute(new ThreadTask(cutList,countDownLatch));
        }
        try {
            //调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
            countDownLatch.await();
            executorService.shutdown();
            System.out.println("线程结束了-------------------------");
            System.out.println("小何女朋友全带回家");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       return "ok";
   }
}

