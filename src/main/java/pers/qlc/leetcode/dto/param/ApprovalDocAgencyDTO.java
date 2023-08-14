package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.Date;

@Data
public class ApprovalDocAgencyDTO {

    /**
     * 合作产品大类
     */
    private String insuranceProductName;

    /**
     * 合作产品大类
     */
    private String insuranceProductCode;

    /**
     * 挂靠方名字
     */
    private String agencyName;

    /**
     * 代理机构合作伙伴代码
     */
    private String agencyPartnerRoleId;

    /**
     * 代理机构合作伙伴合约号
     */
    private String agencyPartnerAgrNo;

    /**
     * 个人代理代理人
     */
    private String agencyPersonCode;

    /**
     * 个人代理合约号
     */
    private String agencyPersonAgrNo;

    /**
     * 合作开始时间
     */
    private Date dependDateStart;

    /**
     * 合作终止时间
     */
    private Date dependDateEnd;

    /**
     * 是否默认代理
     */
    private Boolean isDefaultStr;

}
