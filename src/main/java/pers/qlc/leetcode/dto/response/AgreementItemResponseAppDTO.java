package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class AgreementItemResponseAppDTO {
    /**
     * 主键
     */
    private String id;
    /**
     * 合约项所在位置的序号 从1开始递增
     */
    private Integer itemSequenceNo;
    /**
     * 费率
     */
    private RatioResponseAppDTO feeRate;
    /**
     * 固定费额
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
    /**
     * 服务项目
     */
    private List<ServiceResponseAppDTO> services;
    /**
     * 适用产品
     */
    private List<ProductResponseAppDTO> products = new ArrayList<>();
    /**
     * 适用机构
     */
    private List<DepartmentResponseAppDTO> departments = new ArrayList<>();
    /**
     * 这个合约项的合约元素
     */
    private AgreementElementResponseAppDTO agreementElement;
    /**
     * 与这个合约项相关的信息
     */
    private List<AgreementElementRelationshipResponseAppDTO> relationships = new ArrayList<>();
    /**
     * 适用区域
     */
    private List<AddressResponseAppDTO> areas = new ArrayList<>();

    /**
     * 适用区域(增值服务区域)
     */
    private List<AddressResponseAppDTO> regions = new ArrayList<>();
}
