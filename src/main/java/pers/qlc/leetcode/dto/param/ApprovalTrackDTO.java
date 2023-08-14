package pers.qlc.leetcode.dto.param;

import lombok.Data;

/**
 * 申请单流水轨迹信息dto
 */
@Data
public class ApprovalTrackDTO {
    /**
     * 申请单状态
     */
    private String approvalDocState;
    /**
     * 申请人姓名
     */
    private String operatorName;
    /**
     * 申请人代码
     */
    private String operatorCode;
    /**
     * 审批时间
     */
    private Long operationTime;
    /**
     * 审批意见
     */
    private String operationRemark;
    /**
     * 工作流id
     */
    private String workFlowId;
    /**
     * 申请单操作类型代码
     */
    private String operation;
    /**
     * 操作类型 人或者平台
     */
    private String operateType;
    /**
     * 当前节点是否审核中 true为审核中
     */
    private boolean pendingNow;
}
