package pers.qlc.leetcode.dto.param;

import lombok.Data;

/**
 * 申请单审核列表总数
 *
 * @author hzj
 * @date 2023-4-24 09:30:12
 */
@Data
public class ApprovalTaskCountDTO {
    /**
     * 待审核总数
     */
    private Integer pendingCount;

    /**
     * 已处理总数
     */
    private Integer processedCount;

    /**
     * 已发起总数
     */
    private Integer initiatedCount;

    /**
     * 草稿总数
     */
    private Integer tempCount;

}
