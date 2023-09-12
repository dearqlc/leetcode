package pers.qlc.leetcode.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class WJH {

    @ExcelProperty(value = "idExposure")
    private String idExposure;

    @ExcelProperty(value = "SNP")
    private String SNP;

    @ExcelProperty(value = "导入结果")
    private String result;

    @ExcelProperty(value = "失败原因")
    private String failReason;

}
