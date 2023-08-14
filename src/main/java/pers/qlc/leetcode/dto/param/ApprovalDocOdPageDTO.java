package pers.qlc.leetcode.dto.param;

import lombok.Data;

/**
 * 申请单全量数据对象
 */
@Data
public class ApprovalDocOdPageDTO {

    /**
     * 申请单类型
     */
    private String approvalDocType;

    /**
     * 申请单号
     */
    private String approvalDocCode;

    /**
     * 申请单状态
     */
    private String status;

    /**
     * 信用码
     */
    private String socialCode;

    /**
     * 备注
     */
    private String remark;

    /**
     * 业务单元id
     */
    private String businessUnitId;

    /**
     * 业务单元类型
     */
    private String buType;

    /**
     * 审批来源
     */
    private String approvalSource;

    /**
     * 伙伴数据
     */
    private ApprovalDocBuDTO buDTO;

    /**
     * 合约数据
     */
    private ApprovalDocAgrDTO agrDTO;

    /**
     * 策略数据
     */
    private ApprovalDocStrategyDTO strategyDTO;


}
