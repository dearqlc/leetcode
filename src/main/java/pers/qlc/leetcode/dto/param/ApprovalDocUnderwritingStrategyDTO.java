package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

/**
 * @author zq
 */
@Data
public class ApprovalDocUnderwritingStrategyDTO {
    /**
     * 非价费备注
     */
    private String nonPriceremarks;

    /**
     * 申请人
     */
    private String proposerCode;
    /**
     * 申请人名称
     */
    private String proposerName;

    /**
     * 申请原因
     */
    private String reasonCode;

    /**
     * 申请原因
     */
    private String reasonName;

    /**
     * 影像信息
     */
    private List<ApprovalDocImageDTO> images;

    /**
     * 最高审批级别
     */
    private String highestApproval;

    /**
     * 不超预算最高审核级别
     */
    private String withinApprovalLevel;

    /**
     * 投保单号
     */
    private List<String> applicationNumber;

    /**
     * 非价格方案列表
     */
    private List<ApprovalDocNonPriceDTO> nonPrivceList;

    /**
     * 政策除外
     */
    private List<ApprovalDocPolicyExclusionDTO> policyExclusions;
}
