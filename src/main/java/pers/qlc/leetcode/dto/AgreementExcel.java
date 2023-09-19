package pers.qlc.leetcode.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Author :QLC
 * @Date :2023/5/15 18:30
 * @Description :
 */
@Data
public class AgreementExcel {

    @ExcelProperty(value = "role_id")
    private String roleId;

    @ExcelProperty(value = "partner_name")
    private String partnerName;

    @ExcelProperty(value = "date_start")
    private String dateStart;

    @ExcelProperty(value = "date_end")
    private String dateEnd;

    @ExcelProperty(value = "org_code")
    private String orgCode;

    @ExcelProperty(value = "agreement_no")
    private String agreementNo;

}
