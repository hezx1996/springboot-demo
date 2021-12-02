package com.goke.demo.Utlis;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

/**
 * 文件处理工具类
 * @author 隔壁子
 * @date 2020/5/21 9:18
 */
public class FileUtils {

    /**
     * 删除目录及文件 返回boolean
     */
    public static boolean  delFile(File file) {
        if (file.exists()) {
            return false;
        }
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                delFile(f);
            }
        }
        return file.delete();
    }

    
    /**
     * 指定文件路径下结构
     * @param src 文件路径
     */
    public static void printFile(File src){
        System.out.println(src.getName());
        File[] list = src.listFiles();
        for (File file : list)
        {
            if(file.isFile()){
                System.out.println(file.getName());
            }else{
                printFile(file);
            }
        }
    }

    public static void downLoad(String filePath, HttpServletResponse response) throws Exception {
           File f = new File(filePath);
           if (!f.exists()) {
                  response.sendError(404, "File not found!");
                   return;
             }
             response.setContentType("application/x-msdownload");
             response.setHeader("Content-Disposition", "attachment; filename=" + f.getName());
             BufferedInputStream br = new BufferedInputStream(new FileInputStream(f));//读
             OutputStream out = response.getOutputStream();//写
             response.reset(); // 非常重要
            byte[] buf = new byte[1024];
            int len = 0;
            while ((len = br.read(buf)) != -1)
                out.write(buf, 0, len);
                br.close();
                out.close();
          }

}
