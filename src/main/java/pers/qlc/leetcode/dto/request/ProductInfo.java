package pers.qlc.leetcode.dto.request;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/10/27 20:57
 * Content:
 */
@Data
public class ProductInfo {
    private String productCode;
    private String productName;
    private String insuranceClassCode;
    private String insuranceClassName;
}
