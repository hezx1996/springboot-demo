package com.goke.demo.model.controller;

import cn.hutool.core.lang.Console;
import com.goke.config.helper.RedissonLockHelper;
import com.goke.demo.Utlis.RedisCacheManager;
import com.goke.demo.model.entity.User;
import com.goke.demo.model.entity.UserEntity;
import com.goke.demo.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * (User)表控制层
 *
 * @author 隔壁子
 * @since 2020-07-20 11:50:38
 */
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * synchronized (森亏乃斯特)
     * ReentrantLock(瑞安全的拿克)锁 --公平锁需要设置为true 默认为非公平锁-- 需手动释放锁
     * 非公平锁实际执行的效率要远远超出公平锁，除非
     * 程序有特殊需要，否则最常用非公平锁的分配机制。
     */
    private static final Lock lock = new ReentrantLock(true);

    /**
     * private Condition condition=lock.newCondition();（砍得行的）
     * condition..await(); 等待
     * condition.signal(); 唤醒等待的线程
     * condition.signalAll(); 唤醒所有等待的线程
     */
    private Condition condition = lock.newCondition();


    //volatile 关键字 保证了不同线程对这个变量进行操作时的可见性，即一个线程修改了某个变量的值，这新值对其他线程来说是立即可见的。
    public volatile boolean exit = false;

    /**
     * 服务对象
     */
    @Resource
    private UserService userService;

    @Autowired
    private RedisCacheManager redisCacheManager;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectById")
    public User selectOne(Integer id) {
        //RedissonLock上锁
        RedissonLockHelper.tryLock("6666");
        redisCacheManager.set("666", "7777", 60);
        Object o = redisCacheManager.get("666");
        Console.log("--------" + o);
        //RedissonLock释放锁
        RedissonLockHelper.relaseLock("6666");
        return this.userService.queryById(id);
    }


    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("/selectByIds")
    public User selectOnes(Integer id) {
     /*  if (exit){
           return new User().setTitle("线程执行中");
       }*/
        try {
            //ReentrantLock上锁
            lock.lock();
            //exit = true;
            System.out.println("-----------------------上锁-----");
            System.out.println("----开始休眠---");
            Thread.sleep(30000);
            System.out.println("----休眠结束---");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //必须手动释放锁
            lock.unlock();
            //exit = false;
            System.out.println("-----------------------解锁------");
        }
        return new User().setUname("小何").setTitle("很帅");
    }


    /**
     * 导出测试
     *
     * @return 单条数据
     */
    @GetMapping("/out")
    public void out(HttpServletResponse response) {
        this.userService.out(response);
    }

    @GetMapping("/outs")
    public void outs(HttpServletResponse response) throws Exception {
        this.userService.A(response);
    }

    /***
     * myBatis Plus测试
     */
    @GetMapping("/plus")
    public List<UserEntity> myBatisPlus(HttpServletResponse response) {
        return this.userService.myBatisPlus(response);
    }



}