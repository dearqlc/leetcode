package pers.qlc.leetcode.syncAgr;

/**
 * @Author :QLC
 * @Date :2023/12/12 10:15
 * @Description :
 */

import lombok.Data;

import java.util.List;

@Data
public class AssessorImportResponseAppDTO {
    /**
     * 姓名
     */
    private String name;

    /**
     * 作业行政区域
     */
    private List<AreaResponseAppDTO> workAreas;

    /**
     * 是否专职中华
     */
    private Boolean isConcentrateCic;

    /**
     * 资格证号
     */
    private String qualifyNo;

    /**
     * 身份证号
     */
    private String identityNo;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 归属机构
     */
    private String orgCode;

    /**
     * 负责人
     */
    private String externalHead;

    /**
     * 负责产品大类
     */
    private List<InsuranceResponseAppDTO> responsibleInsurance;
}
