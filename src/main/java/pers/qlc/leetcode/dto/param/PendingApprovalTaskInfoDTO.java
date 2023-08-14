package pers.qlc.leetcode.dto.param;

import lombok.Data;

/**
 * 当前审批操作人
 *
 * @author hzj
 * @date 2023-4-25 10:21:02
 */
@Data
public class PendingApprovalTaskInfoDTO {
    /**
     * 当前环节操作人 角色或平台代码
     */
    private String currentOperatorCode;
    /**
     * 当前环节操作人 角色或平台名称
     */
    private String currentOperatorName;
    /**
     * 操作类型 角色或者平台
     */
    private String operateType;

}
