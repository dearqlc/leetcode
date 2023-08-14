package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class BalanceResponseAppDTO {
    /**
     * 余额
     */
    private BigDecimal balance;
    /**
     * 剩余次数
     */
    private Integer remainTimes;
}