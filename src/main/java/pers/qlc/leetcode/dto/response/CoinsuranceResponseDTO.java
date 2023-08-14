package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author :QLC
 * @Date :2023/6/27 11:31
 * @Description :
 */
@Data
public class CoinsuranceResponseDTO {

    /**
     * 是否主共(联)保方 true-是,false-否
     */
    private Boolean isMajorInsurer;

    /**
     * 是否出单方 true-是,false-否
     */
    private Boolean isIssuer;

    /**
     * 共(联)保比率 0-1
     */
    private BigDecimal coinsuranceRate;

    /**
     * 代理(经纪)费比率 0-1
     */
    private BigDecimal agentFeeRate;

    /**
     * 出单费比率 0-1
     */
    private BigDecimal issueExpenseRate;

}
