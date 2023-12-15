package pers.qlc.leetcode.syncAgr;

/**
 * @Author :QLC
 * @Date :2023/12/12 10:15
 * @Description :
 */

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class AssessorDTO {
    /**
     * 字段
     */
    @ExcelIgnore
    @ExcelProperty(value = "字段")
    private String filed;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String name;

    /**
     * 作业行政区域
     */
    @ExcelProperty(value = "作业行政区域")
    private String workAreas;

    /**
     * 是否专职中华
     */
    @ExcelProperty(value = "是否专职中华")
    private String isConcentrateCic;

    /**
     * 资格证号
     */
    @ExcelProperty(value = "资格证号")
    private String qualifyNo;

    /**
     * 身份证号
     */
    @ExcelProperty(value = "身份证号")
    private String identityNo;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String phone;

    /**
     * 邮箱
     */
    @ExcelProperty(value = "邮箱")
    private String mail;

    /**
     * 归属机构
     */
    @ExcelProperty(value = "归属机构")
    private String orgCode;

    /**
     * 负责人
     */
    @ExcelProperty(value = "负责人")
    private String externalHead;

    /**
     * 负责产品大类
     */
    @ExcelProperty(value = "负责产品大类")
    private String responsibleInsurance;

    /**
     * 导入结果
     */
    @ExcelProperty(value = "导入结果")
    private String result;

    /**
     * 失败原因
     */
    @ExcelProperty(value = "失败原因")
    private String failReason;

}
