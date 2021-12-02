package com.goke.demo.controller;

import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.goke.demo.Utlis.EasyExcelUtil;
import com.goke.demo.dto.TestModel;
import com.goke.demo.dto.acountVo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author 隔壁子
 * @date 2020/6/25 9:14
 */
@Controller
public class ExlceController {

    /**
     * 导入excel
     */
    @RequestMapping(value = "/excelImport", method = {RequestMethod.GET, RequestMethod.POST })
    public String excelImport(HttpServletRequest request, Model model, @RequestParam("uploadFile") MultipartFile files) throws Exception {
        if(files != null ){
            Map<String,Object> result = EasyExcelUtil.readExcel(files, new acountVo(),1);
            Boolean flag = (Boolean) result.get("flag");
            if(flag){
                List<Object> list = (List<Object>) result.get("datas");
                if(list != null && list.size() > 0){
                    for(Object o : list){
                        acountVo xfxx = (acountVo) o;
                        //自己逻辑
                        System.out.println("insert into T_ACCOUNT_INFO(ACCOUNT_ID,ACCOUNT_NO,ACCOUNT_NAME,ORG_CODE,STATE,IMMOV_ACCT_FLAG,JX_REPORT_STATUS) values(SEQ_ACCOUNT_INFO_OPEN.nextval,"+xfxx.getZh()+",'久悬报备测试','01002','U','0','0');");

                    }
                }
                System.out.println("------"+list.size());
            }else{
                System.out.println("表头格式错误");
            }
        }
        return "index";
    }



    /***
     * 导出excel
     */
    @RequestMapping("/excelOut")
    public void excelOut(HttpServletResponse rq) throws IOException {
        rq.setHeader("Content-Disposition", "attachment;filename=" +URLEncoder.encode("测试.xlsx","utf-8"));
        rq.setContentType("application/vnd.ms-excel;charset=UTF-8");
        List<TestModel> list = new ArrayList();
        for (int i = 1; i <=1000 ; i++) {
            TestModel model = new TestModel();
            model.setSjh("18582318548");
            model.setXm("小何"+i);
            model.setWxh("xijwwkk"+i);
            list.add(model);
        }
        ExcelWriter writer=new ExcelWriter(rq.getOutputStream(), ExcelTypeEnum.XLSX);
        Sheet sheet=new Sheet(1,0,list.get(0).getClass());
        sheet.setSheetName("sheet0");
        writer.write(list,sheet);
        writer.finish();//必须刷新出去
    }

}
