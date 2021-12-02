package com.goke.demo.model.service.impl;

import com.goke.demo.model.dao.UserDao;
import com.goke.demo.model.entity.User;
import com.goke.demo.model.service.B;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 隔壁子
 * @date 2021/2/5 16:45
 */
@Service
public class Bimpl implements B {

    @Resource
    private UserDao userDao;


    @Override
    public void B(HttpServletResponse response) {
        User user = new User();
        user.setUname("B");
        this.userDao.insert(user);
       /* String s= null;
        s.endsWith("111");*/
    }
}
