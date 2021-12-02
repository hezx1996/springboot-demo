package com.goke.demo.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

@Data
public class TestModel extends BaseRowModel {
 
	@ExcelProperty(value = "姓名", index = 0)
	private String xm;
	@ExcelProperty(value = "微信号", index = 1)
	private String wxh;
	@ExcelProperty(value = "手机号", index = 2)
	private String sjh;

}