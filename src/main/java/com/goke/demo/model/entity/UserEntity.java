package com.goke.demo.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 隔壁子
 * @date 2021/9/6 17:53
 * myBatis Plus 测试实体
 *
 */

@Data
@TableName(value = "user")//表名
public class UserEntity implements Serializable {
    private static final long serialVersionUID = -7676723800680139687L;

    @TableId(value = "uid",type = IdType.AUTO)//主键字段名,自增类型
    private Integer uid;

    @TableField(value = "uname")//属性字段名
    private String uname;

    @TableField(value = "pwd")
    private String pwd;

    @TableField(value = "title",select = false)
    private String title;

    @TableField(select = false)//不作为查询字段
    private String titles;
}
