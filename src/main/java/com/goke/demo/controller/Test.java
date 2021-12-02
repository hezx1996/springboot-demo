package com.goke.demo.controller;

import com.goke.demo.model.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * 这两个注解都可以 @Configuration会使得bean对象变得复杂的多，当你序列化的时候，或者转json的时候会很笨重，
 * 推荐@Component，还有一点@ConfigurationProperties这个注解需要配合@Configuration，使用@Component无法注入，
 * 这两个注解很让人头疼，我一直是优先使用Component，只有它搞不定了，才会用Configuration
 *
 * 注意——：虽然Component注解也会当做配置类，但是并不会为其生成CGLIB代理Class，方法执行时会new操作，所以是不同的对象。
 * 当是Configuration注解时，第一次会new对象，第二次调用时直接从BeanFactory之中获取对象，所以得到的是同一个对象
 *（所以得场合使用）
 * @Configuration
 * @Component
 */
@Configuration
public class Test {

   @Bean
    public  void  text(){
        System.out.println("holler word");
    }

    @Bean
    public User user(){
       User user = new User();
       user.setUname("小何");
       user.setTitle("快脱单啊");
       return user;
    }

}

