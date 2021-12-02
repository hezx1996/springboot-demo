package com.goke.demo.controller;

import com.goke.demo.Utlis.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 隔壁子
 * 异步线程
 * @date 2020/11/17 17:17
 */
@RestController
@RequestMapping("/async")
public class AsyncTaskController {
    private final static Logger LOG = LoggerFactory.getLogger(AsyncTaskController.class);

    @Autowired
    private AsyncTask asyncTask;

    @GetMapping(value = "/test")
    public Object test(){
        for (int i = 1; i <=10; i++) {
            List<String> list = new ArrayList<>();
            for (int j = 1; j <=i; j++) {
                list.add("我是第"+i+"个对象的第"+j+"个值");
            }
            //调用异步线程方法
            asyncTask.register(list);
        }
        System.out.println("主线程结束");
        return "OK";
    }

}
