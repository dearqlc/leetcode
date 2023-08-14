package pers.qlc.leetcode.dto.request;

import lombok.Data;

@Data
public class ApprovalResultRequestDTO {
    private String approvalDocCode;
    private String approvalRemark;
    private Boolean approvalResult;
}
