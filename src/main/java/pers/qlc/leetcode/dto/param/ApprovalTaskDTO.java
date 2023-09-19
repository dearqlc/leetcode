package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.Date;

/**
 * 申请单任务信息
 */
@Data
public class ApprovalTaskDTO {
    /**
     * 合作伙伴名称
     */
    private String partnerName;
    /**
     * 协议名称
     */
    private String agrName;
    /**
     * 协议号
     */
    private String agrNo;
    /**
     * 申请单类型
     */
    private String approvalDocType;
    /**
     * 伙伴细分类型
     */
    private String partnerTypeCode;
    /**
     * 当前节点操作人角色代码
     */
    private String operatorRoleCode;
    /**
     * 申请人代码
     */
    private String approvalDocPersonCode;
    /**
     * 申请发起时间
     */
    private Date approvalDocDate;
    /**
     * 申请结束时间
     */
    private Date approvalDocCompleteDate;
    /**
     * 申请单号
     */
    private String approvalDocCode;
    /**
     * 申请单归属机构
     */
    private String orgCode;
    /**
     * 业务单元类型
     */
    private String unitType;
    /**
     * 审批单状态码值 枚举见 ApprovalDocStateEnum
     */
    private String approvalDocStatus;
    /**
     * 社会统一信用码
     */
    private String socialCode;

    /**
     * 申请发起时间
     */
    private Date approvalDocModifyDate;

    /**
     * 审批来源
     */
    private String approvalSource;
}
