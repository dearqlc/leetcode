package pers.qlc.leetcode.dto.request;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/12/15 10:24
 * Content:
 */
@Data
public class PaymentSettlementInfo {

    /**
     * 结算频次（month-按月，week-按周，irregular-不定期）
     */
    private String settlementPeriodCd;
    /**
     * 结算日
     */
    private Integer settlementDay;
    /**
     * 账户信息
     */
    private BankAccountInfo bankAccount;

    private Integer settlementMethodCd;
    /**
     * 结算方式（payAfterInvoice-先开票后付款，payFirstthenInvoice-先付款后开票，payWithoutInvoice-无需付款）
     */
    private String settlementEnvironmentCd;
    /**
     * 开票方式（toBranch-发票开至中支，toSubsidiary-发票开至分公司）
     */
    private String invoiceModeCd;
    /**
     * 付款方式（separatedPayment-一对一结算，centralizedPayment-总对总集中合并支付）
     */
    private String paymentModeCd;
    /**
     * 费用计算方式（withTax-含税，withoutTax-不含税）
     */
    private String feeCalculateModeCd;
    /**
     * 币种
     */
    private String currencyCd;
}
