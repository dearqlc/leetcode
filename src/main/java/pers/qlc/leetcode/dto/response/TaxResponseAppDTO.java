package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class TaxResponseAppDTO {


    /**
     * 纳税人类型
     */
    private Integer taxpayerType;

    /**
     * 发票类型
     */
    private Integer taxType;

    /**
     * 税率
     */
    private BigDecimal rate;
}