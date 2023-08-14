package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

/**
 * 策略数据
 */
@Data
public class ApprovalDocStrategyDTO {

    /**
     * 影像白名单-影像材料是否可后补
     */
    private Boolean isImgReplenish;

    /**
     * 挂靠个人代理
     */
    private List<ApprovalDocAgencyDTO> personAgency;

    /**
     * 挂靠中介代理
     */
    private List<ApprovalDocAgencyDTO> partnerAgency;

    /**
     * 1 价费非价费 2 价费 3 非价费
     */
    private String isPriceFee;

    /**
     * 核保方案列表
     */
    private ApprovalDocUnderwritingStrategyDTO approvalDocUnderwritingStrategyDTOs;

    /**
     * 价费方案列表
     */
    private ApprovalDocPriceSchemeDTO approvalDocPriceSchemeDTO;

    /**
     * 备注
     */
    private String strategyRemark;

    /**
     * 影像信息
     */
    private List<ApprovalDocImageDTO> images;

    /**
     * 实际结算手续费
     */
    private ApprovalDocActualCommissionDTO approvalDocActualCommissionDTO;


}
