package com.goke.demo.Utlis;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

import static org.apache.xmlbeans.impl.piccolo.xml.Piccolo.STRING;

/**
 * @author 隔壁子
 * @date 2021/7/8 12:47
 */

public class testUtoes {

    public static void main(String[] args) throws Exception {

        //File file = new File(" ");
        //  File file = new File(" ");
        //getSheetData(file);
        File file = new File(" ");
        String[][] result = getData(file, 1);

        int rowLength = result.length;
        /* 写入Txt文件 */
        File writename = new File("E:\\two.txt"); // 相对路径，如果没有则要建立一个新的output。txt文件
        writename.createNewFile(); // 创建新文件
        BufferedWriter out = new BufferedWriter(new FileWriter(writename));
        for(int i=0;i<61;i++) {
            out.write(
                    "UPDATE  SET FULL_NAME_ ='"+result[i][2]+"',DESC_ = '"+result[i][3]+"' ,  = '"+result[i][5]+"' , ='"+result[i][6]+"'\n" +
                            "WHERE  NAME_ = '"+result[i][1]+"'  ;"+

                            "\r\n"); // \r\n即为换行
            System.out.println();

        }
        out.flush(); // 把缓存区内容压入文件
        out.close(); // 最后记得关闭文件


    }

    static  void getSheetData(File file)throws IOException {
        FileInputStream inp = new FileInputStream(file);
        XSSFWorkbook wb = new XSSFWorkbook(inp);

        XSSFSheet sheet = wb.getSheetAt(3); // 获得第三个工作薄(2008工作薄)
        // 填充上面的表格,数据需要从数据库查询
//        HSSFRow row5 = sheet.getRow(4); // 获得工作薄的第五行
//        HSSFCell cell54 = row5.getCell(3);// 获得第五行的第四个单元格
//        cell54.setCellValue("测试纳税人名称");// 给单元格赋值
        //获得总列数
        int coloumNum=sheet.getRow(0).getPhysicalNumberOfCells();
        int rowNum=sheet.getLastRowNum();//获得总行数

        System.out.println(coloumNum);
        System.out.println(rowNum);
        System.out.println(sheet.getSheetName());
        int cellCount = 0;
        for (int i = 0; i < rowNum; i++) {
            XSSFRow row = sheet.getRow(i);
            if (row != null) {//row  该行所有单元格为空时，row是null值    2017-01-05 pelin
                System.out.println( i +"索引行getPhysicalNumberOfCells:" + row.getPhysicalNumberOfCells());
                System.out.println( i +"索引行getLastCellNum:" + row);
            }
        }
        System.out.println(cellCount);

    }


    /**

     * 读取Excel的内容，第一维数组存储的是一行中格列的值，二维数组存储的是多少个行

     * @param file 读取数据的源Excel

     * @param ignoreRows 读取数据忽略的行数，比喻行头不需要读入 忽略的行数为1

     * @return 读出的Excel中数据的内容

     * @throws FileNotFoundException

     * @throws IOException

     */

    public static String[][] getData(File file, int ignoreRows) throws FileNotFoundException, IOException, InvalidFormatException {
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(
                file));
        // 打开HSSFWorkbook
        InputStream inp = new FileInputStream(file);
        XSSFWorkbook  wb1 = (XSSFWorkbook) WorkbookFactory.create(inp);
        SXSSFWorkbook wb = new SXSSFWorkbook(wb1,1000);
        SXSSFCell cell = null;
        //所有sheet
        for (int sheetIndex = 0; sheetIndex < wb.getNumberOfSheets(); sheetIndex++) {
            SXSSFSheet st =  wb.getSheetAt(sheetIndex);
            //sheet名字判断
            if (st.getSheetName().equals("GEE1.0 ECU Master List")) {
                // 从第几行开始获取 小于总行数(st.getLastRowNum())
                for (int rowIndex = ignoreRows; rowIndex <= st.getLastRowNum(); rowIndex++) {
                    SXSSFRow row = st.getRow(rowIndex);
                    if (row == null) {
                        continue;
                    }
                    //所有列数
                    for (short columnIndex = 0; columnIndex <= row.getLastCellNum(); columnIndex++) {
                        String value = "";
                        cell = row.getCell(columnIndex);
                        if (cell != null) {
                            switch (cell.getCellType()) {
                                case STRING:
                                    value = cell.getStringCellValue();
                                    break;
                                default:
                                    value = "";
                            }

                        }
                    }

                }
            }

        }
        in.close();
        return null;

    }



    /**

     * 去掉字符串右边的空格

     * @param str 要处理的字符串

     * @return 处理后的字符串

     */

    public static String rightTrim(String str) {

        if (str == null) {

            return "";

        }

        int length = str.length();

        for (int i = length - 1; i >= 0; i--) {

            if (str.charAt(i) != 0x20) {

                break;

            }

            length--;

        }

        return str.substring(0, length);

    }


}
