package pers.qlc.leetcode.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class AgreementSyncExcelDTO {

    @ExcelProperty(value = "agreement_no")
    private String agreementNo;

    @ExcelProperty(value = "agreement_version")
    private String agreementVersion;

    @ExcelProperty(value = "role_id")
    private String roleId;

    @ExcelProperty(value = "business_type_code")
    private String businessTypeCode;

    @ExcelProperty(value = "business_id")
    private String businessId;

    @ExcelProperty(value = "导入结果")
    private String result;

    @ExcelProperty(value = "失败原因")
    private String failReason;
}
