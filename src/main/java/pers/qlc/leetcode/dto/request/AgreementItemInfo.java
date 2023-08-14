package pers.qlc.leetcode.dto.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/10/14 15:28
 * Content:
 */
@Data
public class AgreementItemInfo {
    /**
     * 服务信息
     */
    private ServiceInfo service;
    /**
     * 合约项上的合约元素
     */
    private AgreementElementInfo agreementElement;
    /**
     * 服务地区
     */
    private List<AddressInfo> areas;
    /**
     * 适用产品
     */
    private List<ProductInfo> products;
}
