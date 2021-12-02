package com.goke.demo.Utlis;

import com.goke.demo.model.entity.User;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;


/**
 * 功能说明：Excel 导入/导出
 * 典型用法：无
 * 特殊用法：无
 * 创建者：phil
 * 创建时间： 2017年11月9日
 * 修改人：phil
 * 修改时间：
 * 修改原因：
 * 修改内容：
 * 版本号：1.0
 */
public class ExcelUtil {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0");// 格式化 number为整

    private static final DecimalFormat DECIMAL_FORMAT_PERCENT = new DecimalFormat("##.00%");//格式化分比格式，后面不足2位的用0补齐

//	private static final DecimalFormat df_per_ = new DecimalFormat("0.00%");//格式化分比格式，后面不足2位的用0补齐,比如0.00,%0.01%

//	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd"); // 格式化日期字符串

    private static final FastDateFormat FAST_DATE_FORMAT = FastDateFormat.getInstance("yyyy/MM/dd");

    private static final DecimalFormat DECIMAL_FORMAT_NUMBER = new DecimalFormat("0.00E000"); //格式化科学计数器

    private static final Pattern POINTS_PATTERN = Pattern.compile("0.0+_*[^/s]+"); //小数匹配


    //证件过期Excle
    public static Workbook expiredExcle(List<User> wrinCusList) throws Exception{
        SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-ddd");
        String date=sdf.format(new Date());
        List<String> titles = new ArrayList<String>();
        titles.add("材料种类");
        titles.add("材料名称");
        titles.add("单位");
        titles.add("订货数量");
        titles.add("实际验收数量");
        titles.add("检测价格");
        titles.add("小计");
        titles.add("备注");

        SXSSFWorkbook wb=new SXSSFWorkbook(1000); // 定义一个新的工作簿
        SXSSFSheet sheet = wb.createSheet("Sheet1");  // 创建第一个Sheet页

        CellStyle headStyle = getCellStyle(wb, 12);
        headStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        headStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        sheet.setDefaultColumnWidth(20);// 默认列宽
        SXSSFRow row=sheet.createRow(0); // 创建一个行
        SXSSFCell cell; // 创建一个单元格  第1列
        if(wrinCusList.size()==0){
            cell = getCell(sheet, 0, 0);
            setText(cell, "无对应数据");
            return wb;
        }
        String value = "";
        for (int k = 0; k <=8 ; k++) {
            switch(k){
                case 0:value ="验收单编号:cl001";break;
                case 1:value ="合计:988.88";break;
                case 2:value ="实际验收时间:2021-01-08";break;
                case 3:value ="供应商名称:白沙州市场";break;
                case 4:value ="需求送达日期:2021-01-30";break;
                case 5:value ="联系人:张大仙";break;
                case 6:value = "验收食堂:第一食堂";break;
                case 7:value ="状态:审核中";break;
                case 8:value ="类型:正常";break;
            }
            if (k%2==0){
                //头部数据
                cell = getCell(sheet, 0, k);
                setText(cell, value);
            }else {
                cell = getCell(sheet, 1, k-1);
                setText(cell, value);
            }
        }

        User mainTain = new User();
        int x = 0;//分sheet时 填充数据的行坐标重置
        int count = (int) Math.ceil((double)wrinCusList.size()/60000)==0?
                1:(int) Math.ceil((double)wrinCusList.size()/60000);	//总页数
        int page = 0;//当前页数
        int t = 0;//记录当前页的数据总数
        CellStyle ricghtStyle = getCellStyle(wb, 11);
        ricghtStyle.setAlignment(HorizontalAlignment.RIGHT);//水平居右
        CellStyle centerStyle = getCellStyle(wb, 11);

        centerStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        centerStyle.setVerticalAlignment(VerticalAlignment.CENTER);// 垂直居中

        for(int i=0;i<wrinCusList.size(); i++){
            mainTain = wrinCusList.get(i);
            if(i%60000==0){//60000条一页
                page++;
                if(i != 0){
                    sheet = wb.createSheet("Sheet"+(i/60000+1));
                    sheet.setDefaultColumnWidth(20);// 默认列宽
                }
                for (int j = 0; j < titles.size(); j++) { //设置标题
                    String title = titles.get(j);
                    cell = getCell(sheet, 2, j);
                    cell.setCellStyle(headStyle);
                    setText(cell, title);
                }
                /*if(i==0){
                    t = wrinCusList.size()<=60000?wrinCusList.size():60000;
                }else{
                    t = (page)*60000<=wrinCusList.size()?60000:wrinCusList.size()%60000;
                }
               if((page)*60000>=wrinCusList.size()){
                    cell = getCell(sheet, t+3, 0);
                    setText(cell, "共 "+wrinCusList.size()+" 户");
                }
                cell = getCell(sheet, t+4, 4);
                setText(cell, "第"+page+"页    共"+count+"页");
                cell = getCell(sheet, t+4, 8);
                setText(cell, "打印日期:"+date);*/
            }
            x = i%60000;
            if ("1".equals(mainTain.getUname())){
                cell = getCell(sheet, x+3, 0);
                cell.setCellStyle(headStyle);
                setText(cell, mainTain.getTitle());
            }else {
                for (int j = 1; j < titles.size(); j++) {
                    switch(j){
                        case 1:value = mainTain.getUname();break;
                        case 2:value = mainTain.getPwd();break;
                        case 3:value = mainTain.getTitle();break;
                        case 4:value = new BigDecimal(12).setScale(2,BigDecimal.ROUND_DOWN).toString();break;
                    }

                    if (j==3||j==4){
                        cell = getCell(sheet, x+3, j);
                        cell.setCellStyle(centerStyle);
                        setText(cell, value);
                    }else {
                        cell = getCell(sheet, x+3, j);
                        cell.setCellStyle(ricghtStyle);
                        setText(cell, value);
                    }
                }
            }
        }
        return wb;
    }


    public static void setText(SXSSFCell cell, String text) {
        cell.setCellType(org.apache.poi.ss.usermodel.Cell.CELL_TYPE_STRING);
        cell.setCellValue(text);
    }

    public static SXSSFCell getCell(SXSSFSheet sheet, int row, int col) {
        SXSSFRow sheetRow = sheet.getRow(row);
        if (sheetRow == null) {
            sheetRow = sheet.createRow(row);
        }
        SXSSFCell cell = sheetRow.getCell(col);
        if (cell == null) {
            cell = sheetRow.createCell(col);
        }
        return cell;
    }

    public static CellStyle getCellStyle(SXSSFWorkbook wb,int size){
        // 设置style
        CellStyle headStyle =  wb.createCellStyle();
        Font font = wb.createFont();
        font.setFontHeightInPoints((short)size);
        headStyle.setFont(font);
        return headStyle;
    }

}
