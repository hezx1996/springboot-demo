package com.goke.demo.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 隔壁子
 * @date 2021/2/22 11:16
 */
@Data
public class book implements Serializable {

    private static final long serialVersionUID = -1030452018737016745L;

    private Integer bookId;

    private String bookName;
}
