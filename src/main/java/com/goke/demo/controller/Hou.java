
package com.goke.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author 隔壁子
 * @date 2020/4/15 13:12
 */


public class Hou {

    public static  void  main(String[] src){
      /*  int codeInt = (int)((Math.random()*9+1)*100000);
        String i = String.valueOf(codeInt);
        System.out.println(i);
        基本（刘珊珊）

        LocalDateTime time =LocalDateTime.now();
        LocalDateTime dateTime = LocalDateTime.now().minusMinutes(30);
        String format = dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        System.out.println(format);
        String s = time.toString();
        System.out.println(s);*/
      /*String [] cc=new String[]{
              "240850007310012",
              "240850007410010",
              "240850007510017",
              "240850014310013",
              "240850014510018",
              "240850014710018"
      };

        String [] cc2=new String[]{
                "240850046810018",
                "240850046910012",
                "240850046710016",
                "240850046510010",
                "240850046610018"
        };

        try {
        File file = new File("D:/ods/20200808/D01_DPS_CUR_BAL.txt");
        BufferedReader br = null;
        BufferedWriter bu = new BufferedWriter(new FileWriter(new File("D:/zhao.txt"), true));
            br = new BufferedReader(new FileReader(file));
            String s = null;
            while ((s = br.readLine()) != null) {
                if (s.contains("\u000701277\u0007")){
                  *//*  System.out.println("01277机构的");
                    bu.write(s+"\\n");
                    bu.flush();*//*
                    for (String s1:cc2) {
                        if(s.contains("\u0007"+s1+"\u0007")){
                           //System.out.println(s);
                            bu.write(s+"\n");
                            bu.flush();
                        }
                    }
                    //System.out.println(s);
                }
                //System.out.println("其他机构");
            }
            System.out.println("-----------读取完了");
            //bu.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

//<------------------------------------------------------------------------------------------------------->
        /*try {
            File file = new File("D:/失败清单");
            BufferedReader br = null;
            //BufferedWriter bu = new BufferedWriter(new FileWriter(new File("D:/liqi.txt"), true));
            if (file.exists()){
                System.out.println("文件存在");
                //存在
                if (file.isDirectory()){
                    System.out.println("是目录");
                    //是目录
                    File[] files = file.listFiles();
                    System.out.println("文件数有"+files.length);
                    for (File file1 : files) {
                        System.out.println("开始获取文件"+file1.getName());
                        br = new BufferedReader(new FileReader(file1));
                        BufferedWriter bu = new BufferedWriter(new FileWriter(new File("D:/liqi/"+file1.getName()), true));
                        String s = null;
                        while ((s = br.readLine()) != null){
                            if (!s.contains("待备案的账户已经在个人账户信息表中存在") && !s.contains("待变更或撤销的账户在个人账户信息表中已经被撤")){
                                //System.out.println(s);
                                bu.write(s+"\n");
                                bu.flush();
                            }
                        }
                        bu.close();
                    }
                    System.out.println("完成");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/

/*
        try {
            BufferedInputStream io =new BufferedInputStream(new FileInputStream(new File("D:/code.txt")));
            BufferedOutputStream ou = new BufferedOutputStream(new FileOutputStream(new File("D:/code2.txt")));
            byte[]  leng= new byte[1024];
            int len = 0;

            while(-1 != (len = io.read(leng))) {
               String str =new String(leng,0,len,"UTF-8");
                System.out.println(str);
                ou.write(str.getBytes());
            }
            ou.flush();
            ou.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
*/


 /*  List<String> list= new ArrayList<>();
  if(null != list) {
      for (String r : list) {
          System.out.println(r);
      }

  }else {
      System.out.println("空了");
  }*/

//String s ="12345fff，yyyiop,gtwhwhw，yyyyy，1234789999";

       /* String s ="";
        s.trim();
        String codes = s.replaceAll("，", ",");
        String[]  s1 = codes.split(",");
        System.out.println(s1.length+"----");
        for (String s2 : s1) {
            System.out.println(s2);
        }

        List<String> list = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            list.add("我是第"+i+"个");
        }

        System.out.println("数据初始化完成---------------------------------------------------");

        int total = list.size();
        int threads = 5;
        int oneSize = total/threads +1;
        int start = 0;
        int end = 0;

        System.out.println("数据总数"+total);

        List<List<String>> list2 = new ArrayList<>();

        for (int i = 0; i <threads ; i++) {
            System.out.println("开始"+start+" 结束"+end);
            start = i * oneSize;
            end = (i+1)*oneSize;
            System.out.println("开始"+start+"="+i+"*"+oneSize+"   结束"+end+"="+(i+1)+"*"+oneSize);
            if (i<threads-1){
                list2.add(list.subList(start,end));
                System.out.println("list2条数=上"+list2.size());
            }else {
                list2.add(list.subList(start,list.size()));
                System.out.println("list2条数=下"+list2.size());
            }

        }
        int i=1;
        for (List<String> strings : list2) {
            System.out.println("第"+i+"个对象-----------------------------------------------");
            for (String string : strings) {
                System.out.println("值:"+string);
            }
           i++;
        }

        System.out.println("任务完成"+list2.size());

        TimeUnit o =  TimeUnit.MILLISECONDS;
        System.out.println("+++++++"+o);
        System.out.println("-------"+TimeUnit.MILLISECONDS);*/

     /*   Date d1 = new Date();
        Date d2 = new Date();
        Instant instant =  d1.toInstant();
        LocalDateTime ldt = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        LocalDate startDate  = ldt.toLocalDate();

        Instant instant2 =  d2.toInstant();
        LocalDateTime ldt2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());
        LocalDate endDate = ldt2.toLocalDate();
        System.out.println("LocalDate: "+endDate);
        System.out.println("总相差的天数:" + startDate .until(endDate, ChronoUnit.DAYS));*/

/*
       // 指定转换格式
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse("2021-03-03",fmt);
        LocalDate endDate = LocalDateTime.ofInstant( new Date().toInstant(), ZoneId.systemDefault()).toLocalDate();
        //LocalDate endDate = LocalDate.parse("2021-03-23",fmt);
        double until = startDate.until(endDate, ChronoUnit.DAYS);
        System.out.println("until="+until);
        double date = (until * 2/3);
        System.out.println("date="+date);
        long num = (long) (date*10);
        System.out.println("num="+num);
        long  g =  (num%10);
        System.out.println("g="+g);
        long day;
        if (g<5){
            day = (long)date;
        }else{
            day = (long)(date+1);
        }
        System.out.println(day);*/

       /* LocalDate localDate = startDate.plusDays(date);
        System.out.println(localDate.toString());
        System.out.println(date);*/


        //System.out.println("总相差的天数:" + startDate.until(endDate, ChronoUnit.DAYS));


      /*  List<book> list = new ArrayList<>();
        book b=new book();
        b.setBookId(1);
        b.setBookName("卧槽");
        list.add(b);
        for (book b1 : list) {
            System.out.println(b1.getBookName());
        }
        System.out.println(list.size());
        list.get(0).setBookName("你好");
        for (book b2 : list) {
            System.out.println(b2.getBookName());
        }*/


      //hutool(糊涂)java工具类
     /*   String s = IdUtil.randomUUID();
        Console.log(s);
        String s1 = IdUtil.simpleUUID();
        Console.log(s1);
        String s2 = IdUtil.fastUUID();
        System.out.println(s2);
        String s3 = IdUtil.fastSimpleUUID();
        Console.log(s3);

        String[] strings = ArrayUtil.addAll(new String[]{"1", "2"});
        Console.log(strings);*/

       /* boolean contains = ArrayUtil.contains(new int[]{1, 2, 3, 4}, 5);
        Console.log(contains);*/
        List<String> list = new ArrayList<>();
        list.add("我");
        list.add("要去");
        list.add("北京");
        list.add("的");
        list.add("北京大学");

        List<String> list2= new ArrayList<>();
        list2.add("我");
        list2.add("要去");
        list2.add("北京");
        list2.add("的");
        list2.add("北京大学");



        if(list2 != null && list2.size()>1) {
            for (int i = 0; i < list2.size(); i++) {
                int i1 = i+1;
                if (i1 < list2.size() && i1<list.size()) {
                    int k =  list.indexOf(list2.get(i));
                    int k2 = list.indexOf(list2.get(i1));
                    System.out.println(list2.get(i)+"k:" + k + "-- k2:" + k2+list2.get(i1));
                    if (k2 < k) {
                        System.out.println("顺序错误了");
                        System.out.println("k:" + k + "-- k2:" + k2);
                        return;
                    }
                }
            }
        }
    }

    /***
     * 计算两个时间的3分之二的日期
     * @param startDate
     * @param endDate
     * @return
     */
    public  String earlyWarningDate(Date startDate, Date endDate){
        LocalDate startDatedt = LocalDateTime.ofInstant( startDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
        LocalDate endDatedt = LocalDateTime.ofInstant( endDate.toInstant(), ZoneId.systemDefault()).toLocalDate();
        long until = startDatedt.until(endDatedt, ChronoUnit.DAYS);
        long  date = until* 2/3;
        LocalDate localDate = startDatedt.plusDays(date);
        return localDate.toString();
    }

    /**
     * 日期相加天数
     * @param date  日期
     * @param day  加多少天
     * @return
     */
    public static Date subDay(Date date, int day) {
        Calendar rightNow = Calendar.getInstance();
        rightNow.setTime(date);
        rightNow.add(Calendar.DATE, day);
        Date dt = rightNow.getTime();
        return dt;
    }


}

