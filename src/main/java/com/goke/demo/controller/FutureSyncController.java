package com.goke.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author 隔壁子
 * Future(肺确儿)模式线程 异步+返回结果
 * @date 2021/7/21 11:14
 */
@RestController
@RequestMapping("/future")
public class FutureSyncController {

   private  List<Future> list = new ArrayList<>();

    @GetMapping("/test")
    public String futureThread(){

        if (list.size()>0){
            for (int i = 0; i < list.size(); i++) {
                //判断线程是否执行完
                if(!list.get(i).isDone()){
                 return "存在线程没结束!请稍后再试";
                }else {
                    list.remove(i);
                }
             }
        }

        for (int i = 0; i <100 ; i++) {
            FutureTask task =  new FutureTask(new RealData(i+""));
            new Thread(task).start();
            list.add(task);
        }
        return "ok";
    }


}


/**
 * 线程类 实现Callable接口 的call方法
 */
class RealData implements Callable{

    private String para;

    public RealData(String para){
        this.para = para;
    }

    @Override
    public Object call() throws Exception {
        //真实的业务逻辑
        StringBuffer sb = new StringBuffer();
        for (int i= 0 ;i < 100 ; i++){
            sb.append(para);
            try {
                System.out.println("线程名字"+Thread.currentThread().getName()+"--------"+para);
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
