package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:41
 * Content:
 */
@Data
public class AmountResponseDTO {
    private Integer count;
    private String unitCurrencyCd;
    private BigDecimal unitOriginalValue;
    private String totalCurrencyCd;
    private BigDecimal totalOriginalValue;
    private String originalCurrencyCd;
    private String localCurrencyCd;
    private Long exchangeRateId;
    private BigDecimal exchangeRate;
    private BigDecimal originalCurrencyAmount;
    private BigDecimal localCurrencyAmount;
}
