package com.goke.demo.model.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 隔壁子
 * @date 2021/5/13 16:20
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = 2756158402595662927L;

    private String orderId;

    private String userId;

}
