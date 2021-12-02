package com.goke.demo.model.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-07-20 11:50:38
 */
@Data
@Accessors(chain=true)
public class User implements Serializable {
    private static final long serialVersionUID = -18180846464317778L;

    private Integer uid;

    private String uname;

    private String pwd;

    private String title;

    private String titles;

    private List<book> book;


}