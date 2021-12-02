package com.goke.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @author 隔壁子
 * @date 2020/7/28 14:21
 */

public class acountVo extends BaseRowModel {
    @ExcelProperty(value = "账号", index = 0)
    private String zh;
    @ExcelProperty(value = "机构号", index = 1)
    private String code;

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "acountVo{" +
                "zh='" + zh + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}
