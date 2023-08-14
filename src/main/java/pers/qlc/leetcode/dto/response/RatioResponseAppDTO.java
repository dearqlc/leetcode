package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class RatioResponseAppDTO {
    /**
     * 固定费率
     */
    private Boolean isFix;
    /**
     * 默认手续费率
     */
    private BigDecimal defaultRate;
    /**
     * 最高手续费率
     */
    private BigDecimal maxFeeRate;
    /**
     * 最低手续费率
     */
    private BigDecimal minFeeRate;
}