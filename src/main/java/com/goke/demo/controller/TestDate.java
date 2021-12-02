package com.goke.demo.controller;

import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
 
/**
 * <p>TestDate 此类用于：</p>
 * <p>@author：hujm</p>
 * <p>@date：2021年04月22日 17:43</p>
 * <p>@remark：</p>
 */
public class TestDate {
 
    public static void main(String[] args) {
        //System.out.println(getJjr("2021-09-26",9));
        //System.out.println(getMonthWekDay(2021, 8));
        //System.out.println(JJR(2021, 8));

        String result1= HttpUtil.get("http://timor.tech/api/holiday/info/"+"2021-10-01");
        System.out.println(result1);
        cn.hutool.json.JSONObject jsonObject = JSONUtil.parseObj(result1);
        Integer code = jsonObject.getInt("code");
        System.out.println(code);
        String type = jsonObject.getStr("type");
        cn.hutool.json.JSONObject jsonObject1 = JSONUtil.parseObj(type);
        Integer type1 = jsonObject1.getInt("type");
        System.out.println(type1);
        String name = jsonObject1.getStr("name");
        System.out.println(name);

    }
 
    /**
     * 获取周末和节假日
     *
     * @param year
     * @param month
     * @return
     */
   /* public static Set<String> JJR(int year, int month) {
        //获取所有的周末
        Set<String> monthWekDay = getMonthWekDay(year, month);
        //http://timor.tech/api/holiday api文档地址
       // Map jjr = getJjr(year, month + 1);
        Integer code = (Integer) jjr.get("code");
        if (code != 0) {
            return monthWekDay;
        }
        Map<String, Map<String, Object>> holiday = (Map<String, Map<String, Object>>) jjr.get("holiday");
        Set<String> strings = holiday.keySet();
        for (String str : strings) {
            Map<String, Object> stringObjectMap = holiday.get(str);
            Integer wage = (Integer) stringObjectMap.get("wage");
            String date = (String) stringObjectMap.get("date");
            //筛选掉补班
            if (wage.equals(1)) {
                monthWekDay.remove(date);
            } else {
                monthWekDay.add(date);
            }
        }
        return monthWekDay;
    }*/
 
    /**
     * 获取节假日不含周末
     *
     * @param year
     * @param month
     * @return
     */
    private static Map getJjr(String year, int month) {
        String url = "http://timor.tech/api/holiday/year/"+year;
        OkHttpClient client = new OkHttpClient();
        Response response;
        //解密数据
        String rsa = null;
        Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/x-www-form-urlencoded")
                .build();
        try {
            response = client.newCall(request).execute();
            rsa = response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseObject(rsa, Map.class);
    }
 
    /**
     * 获取周末  月从0开始
     *
     * @param year
     * @param mouth
     * @return
     */
    public static Set<String> getMonthWekDay(int year, int mouth) {
        Set<String> dateList = new HashSet<>();
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = new GregorianCalendar(year, mouth, 1);
        Calendar endCalendar = new GregorianCalendar(year, mouth, 1);
        endCalendar.add(Calendar.MONTH, 1);
        while (true) {
            int weekday = calendar.get(Calendar.DAY_OF_WEEK);
            if (weekday == 1 || weekday == 7) {
                dateList.add(simdf.format(calendar.getTime()));
            }
            calendar.add(Calendar.DATE, 1);
            if (calendar.getTimeInMillis() >= endCalendar.getTimeInMillis()) {
                break;
            }
        }
        return dateList;
    }


}