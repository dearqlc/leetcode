package pers.qlc.leetcode.syncAgr;

import lombok.Data;

import java.util.List;

/**
 * @Author :QLC
 * @Date :2023/12/13 14:36
 * @Description :
 */
@Data
public class AssessorRequestAppDTO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 作业行政区域
     */
    private List<AreaRequestAppDTO> workAreas;

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
    private List<InsuranceRequestAppDTO> responsibleInsurance;

}
