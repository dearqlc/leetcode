package pers.qlc.leetcode.dto.request;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: GW
 * @Date: 2022/10/14 15:30
 * Content: 服务项目
 */
@Data
public class ServiceInfo {
    /**
     * 服务代码
     */
    private String serviceCode;
    /**
     * 服务代码对应的名字，例如洗车
     */
    private String serviceName;
    /**
     * 对于服务的文字描述
     */
    private String serviceDescription;
    /**
     * 扩展信息
     */
    private String serviceExtendInfo;
    /**
     * 固定费率
     */
    private Boolean isFix;
    /**
     * 放空时间，取消等待时间（默认0，单位分钟）
     */
    private Integer hesitationPeriod;
    /**
     * 首付款支付比率(百分比)
     */
    private BigDecimal rateOfDownPayment;
    /**
     * 过户最长时效(天)
     */
    private Integer dayOfVehicleTransfer;
    /**
     * 事故车报价有效期(天)
     */
    private Integer dayOfAccidentVehicleQuotation;
    /**
     * 水淹车报价有效期(天)
     */
    private Integer dayOfFloodedVehicleQuotation;
    /**
     * 残值处理金额
     */
    private BigDecimal residualAmount;
    /**
     * 首付款时间
     */
    private LocalDate downPaymentDate;
}
