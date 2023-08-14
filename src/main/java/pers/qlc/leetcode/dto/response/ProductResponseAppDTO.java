package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class ProductResponseAppDTO {
    /**
     * 产品号
     */
    private String productCode;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 产品大类编码
     */
    private String insuranceClassCode;
    /**
     * 产品大类名称
     */
    private String insuranceClassName;
}