package pers.qlc.leetcode.dto.param;

import lombok.Data;


@Data
public class ApprovalDocPriceSchemeDetailDTO {
    /**
     * 方案编号
     */
    private String schemeNo;
    /**
     * 车辆使用性质
     */
    private String vehicleUsage;
    /**
     * 是否新能源 0 是 1否 todo枚举待定
     */
    private String isNewPower;

    /**
     * 新转续
     */
    private String newRenewal;
    /**
     * 车辆品牌编码
     */
    private String brandCodes;

    /**
     * 车辆品牌名称
     */
    private String brandName;
    /**
     * 车系编码
     */
    private String carSeriesCode;

    /**
     * 车系编码
     */
    private String carSeriesName;
    /**
     * 期望定价系数
     */
    private String expectPriceratio;
    /**
     * 期望商业险费用率
     */
    private String expectCommercialCost;

    /**
     * 期望交强险费用率
     */
    private String expectCtpCost;
}
