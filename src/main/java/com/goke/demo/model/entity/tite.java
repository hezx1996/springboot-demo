package com.goke.demo.model.entity;

import lombok.Data;

import java.util.List;

/**
 * @author 隔壁子
 * @date 2021/1/25 21:33
 */
@Data
public class tite {
    private Integer id;

    private String name;

    private List<User> user;

}
