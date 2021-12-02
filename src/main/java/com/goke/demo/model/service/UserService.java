package com.goke.demo.model.service;

import com.goke.demo.model.entity.User;
import com.goke.demo.model.entity.UserEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * (User)表服务接口
 *
 * @author 隔壁子
 * @since 2020-07-20 11:50:38
 */
public interface UserService {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    User queryById(Integer uid);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 实例对象
     */
    User update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 是否成功
     */
    boolean deleteById(Integer uid);

    void out(HttpServletResponse response);

    /**
     *
     * @param response
     */
    void A(HttpServletResponse response) throws Exception;

    /**
     * myBatis puls 测试
     * @param response
     * @return
     */
    List<UserEntity> myBatisPlus(HttpServletResponse response);
}