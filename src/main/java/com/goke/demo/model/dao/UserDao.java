package com.goke.demo.model.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.goke.demo.model.entity.User;
import com.goke.demo.model.entity.UserEntity;
import com.goke.demo.model.entity.tite;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * (User)表数据库访问层
 *
 * @author 隔壁子
 * @since 2020-07-20 11:50:38
 */
@Mapper
public interface UserDao extends BaseMapper<UserEntity> {

    /**
     * 通过ID查询单条数据
     *
     * @param uid 主键
     * @return 实例对象
     */
    User queryById(Integer uid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit  查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryAll(User user);

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param uid 主键
     * @return 影响行数
     */
    int deleteById(Integer uid);

    List<tite> queryAlls();
}