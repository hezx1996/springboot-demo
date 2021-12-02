package com.goke.demo.controller;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimingRun {

    /**
     * 定时任务
     */
//@Scheduled(cron="* */1 * * * ?")
    public void  timerun(){
        for (int i=0;i<10;i++){
            System.out.println("第"+i+"次");
            if (i==2) {
                try {
                    System.out.println("第" + i + "次开始休眠了");
                   // Thread.sleep(50000);//1000是一秒、50000/50秒  11时(h)=39600000毫秒(ms)
                    System.out.println("休眠结束");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

   // @Scheduled(cron="0/5 * * * * ?")
    public void  time(){
        LocalDateTime now =LocalDateTime.of(2020,1,15,21,30);
        LocalDateTime end =LocalDateTime.of(2020,1,16,8,30);
        System.out.println("now:"+now.toString());
        System.out.println("end:"+end.toString());
        Duration duration = Duration.between(now,end);
        System.out.println("天："+duration.toDays());
        System.out.println("小时："+duration.toHours());
        System.out.println("秒："+duration.toMillis());
    }

    //@Scheduled(cron="0/5 * * * * ?")
    public void  test(){
        sleep(14, 20,50000);
        System.out.println("完了");
    }

    /**
     * @param h 小时（21）/21点
     * @param m  分钟（30）/30分
     * @param mis 休眠毫秒数 1000/休眠1秒
     */
    public static void sleep(int h,int m,long mis){
        LocalDateTime localDateTime =LocalDateTime.now();
        int hour = localDateTime.getHour();//小时
        int minute = localDateTime.getMinute();//分钟
        if (hour==h){//小时
            if (minute>=m){//分钟
                try {
                    Thread.sleep(mis);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
