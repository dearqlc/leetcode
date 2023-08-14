package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class CoinsuranceResponseAppDTO {
    /**
     * 是否主共(联)保方 true-是,false-否
     */
    private Boolean isMajorInsurer;
    /**
     * 是否出单方 true-是,false-否
     */
    private Boolean isIssuer;
    /**
     * 共(联)保比率 0-100
     */
    private BigDecimal coinsuranceRate;
    /**
     * 代理(经纪)费比率 0-100
     */
    private BigDecimal agentFeeRate;
    /**
     * 出单费比率 0-100
     */
    private BigDecimal issueExpenseRate;
}