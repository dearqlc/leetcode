package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class AmountResponseAppDTO {
    /**
     * 数量
     */
    private Integer count;
    /**
     * 单价币种
     */
    private String unitCurrencyCd;
    /**
     * 单价原金额
     */
    private BigDecimal unitOriginalValue;
    /**
     * 合计币种
     */
    private String totalCurrencyCd;
    /**
     * 合计原金额
     */
    private BigDecimal totalOriginalValue;
    /**
     * 原币种
     */
    private String originalCurrencyCd;
    /**
     * 本位币种
     */
    private String localCurrencyCd;
    /**
     * 汇率编码
     */
    private String exchangeRateId;
    /**
     * 汇率
     */
    private BigDecimal exchangeRate;
    /**
     * 原币种金额
     */

    private BigDecimal originalCurrencyAmount;
    /**
     * 本位币金额
     */

    private BigDecimal localCurrencyAmount;
}