package com.goke.demo.controller;

import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.Validator;
import cn.hutool.core.util.IdcardUtil;
import org.springframework.boot.SpringBootVersion;
import org.springframework.core.SpringVersion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 隔壁子
 * @date 2021/5/13 16:18
 */

public class test2 {

    public static void main(String[] args) throws InterruptedException {


       /* String orderId1 = "2321837281372913";
        String userId1 = "20180701001";
        String orderId2 = "2321837281372914";
        String userId2 = "20180701002";
        String orderId3 = "2321837281372912";
        String userId3 = "20180701003";
        String orderId5 = "2321837281372918";
        String userId5 = "20180701004";
        String orderId6 = "2321837281372918";
        String userId6 = "20180701009";
        String orderId4 = "2321837281372918";
        String userId4 = "20180701005";

        Order order = new Order();
        order.setUserId(userId1);
        order.setOrderId(orderId1);
        Order order1 = new Order();
        order1.setOrderId(orderId2);
        order1.setUserId(userId2);

        Order order2 = new Order();
        order2.setOrderId(orderId3);
        order2.setUserId(userId3);
        Order order3 = new Order();
        order3.setOrderId(orderId4);
        order3.setUserId(userId4);
        Order order4 = new Order();
        order4.setOrderId(orderId5);
        order4.setUserId(userId5);
        Order order5 = new Order();
        order5.setUserId(userId6);
        order5.setOrderId(orderId6);

        List<Order> orderList = new ArrayList<Order>();
        orderList.add(order);
        orderList.add(order1);
        orderList.add(order2);
        orderList.add(order3);
        orderList.add(order4);
        orderList.add(order5);

        System.out.println(orderList.toString());
        System.out.println("=========================");
        System.out.println("=======这样子就是以orderid降序，如果ordeid相同再以userid降序==================");
        orderList.sort(Comparator.comparing(Order::getOrderId).thenComparing(Order::getUserId).reversed());
        System.out.println(orderList.toString());

        System.out.println("=======这样子就是以orderid降序，如果ordeid相同再以userid升序==================");
        orderList.sort(Comparator.comparing(Order::getOrderId).reversed().thenComparing(Order::getUserId));
        System.out.println(orderList.toString());

        System.out.println("=======这样子就是以orderid升序，如果ordeid相同再以userid升序==================");
        orderList.sort(Comparator.comparing(Order::getOrderId).thenComparing(Order::getUserId));
        System.out.println(orderList.toString());

        System.out.println("=======这样子就是以orderid升序，如果ordeid相同再以userid降序==================");
        orderList.sort(Comparator.comparing(Order::getOrderId).reversed().thenComparing(Order::getUserId).reversed());
        System.out.println(orderList.toString());
*/



   /*  String number = "我要是亿万富区多好啊,有1000000个亿加23456781万";
         String  number1 = number.replaceFirst("[^(0-9)]", "-");
        String  number12= number1.replaceAll("-", "");
        System.out.println(number1+"$$$$$"+number12);*/


        String ID_18 = "51370119960812671";
        String ID_15 = "111111111111111";

        //是否有效
        boolean valid = IdcardUtil.isValidCard(ID_18);
        boolean valid15 = IdcardUtil.isValidCard(ID_15);
        System.out.println(valid);

        String birthByIdCard1 = IdcardUtil.getBirthByIdCard(ID_18);
        System.out.println(birthByIdCard1);

        Short birthByIdCard = IdcardUtil.getMonthByIdCard(ID_18);
        System.out.println(birthByIdCard);
        //
        Short dayByIdCard = IdcardUtil.getDayByIdCard(ID_18);
        System.out.println(dayByIdCard);

        //获取系统月
        int month1 = LocalDate.now().getMonthValue();
        //获取系统日
        int dayOfMonth = LocalDate.now().getDayOfMonth();
        System.out.println(month1 + "-" + dayOfMonth);

        boolean validCard = IdcardUtil.isValidCard(ID_18);
        System.out.println(validCard);


        List<Integer> list = new ArrayList<>();
        list.add(-1111);
        list.add(-12);

        //距离
        List<Integer> distance = list.stream().filter(Integer -> (Integer < 0)).collect(Collectors.toList());
        List<Integer> distances = new ArrayList<>();
        distance.forEach(e -> {
            distances.add(e.intValue() * -1);
        });

        //已过
        List<Integer> exceed = list.stream().filter(Integer -> (Integer > 0)).collect(Collectors.toList());
        System.out.println(distance);
        System.out.println(distances);
        System.out.println(exceed);


        int i = Integer.parseInt("123");
        System.out.println(i);
        System.out.println("holler wroed");

        boolean integer = isInteger("12.94");
        System.out.println(integer);

        //判断是否是数字
        boolean number = Validator.isNumber("1234.9");
        Console.log(number);

        //对集合按照指定长度分段，每一个段为单独的集合，返回这个集合的列表：
        List<List<Integer>> split = ListUtil.split(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8), 10);
        Console.log(split);

        //spring版本
        String version = SpringVersion.getVersion();
        //springboot版本
        String version1 = SpringBootVersion.getVersion();
        System.out.println(version);
        System.out.println(version1);
    }

    //正则
    public static boolean isInteger(String str) {
        String regex = "^[+-]?\\d+(\\.\\d+)?$";
        return str.matches(regex);
    }


}
