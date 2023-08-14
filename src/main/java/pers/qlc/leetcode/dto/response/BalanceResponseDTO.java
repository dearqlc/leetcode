package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:42
 * Content:
 */
@Data
public class BalanceResponseDTO {
    private BigDecimal balance;
    private Integer remainTimes;
}
