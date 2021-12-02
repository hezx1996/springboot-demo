package com.goke.demo.Utlis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author 隔壁子
 *  使用@Async注解创建多线程
 * @date 2020/11/17 15:30
 */
@Component
public class AsyncTask {
    private static final Logger LOG = LoggerFactory.getLogger(AsyncTask.class);

        @Async
        public void register(List<String> list){
        LOG.info("多线程开始注册模拟");
            try {
            for (String s : list) {
                LOG.info("list值为-------:"+s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("多线程注册成功");
    }









}
