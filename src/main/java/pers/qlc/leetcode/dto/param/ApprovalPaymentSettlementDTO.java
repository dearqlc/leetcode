package pers.qlc.leetcode.dto.param;

import lombok.Data;

/**
 * @author zq
 */
@Data
public class ApprovalPaymentSettlementDTO {
    /**
     * 结算频次 week-周,month-月,irregular-不定期
     */
    private String settlementPeriodCd;
    /**
     * 结算方式（payAfterInvoice-先开票后付款，payFirstThenInvoice-先付款后开票，payWithoutInvoice-无需付款）
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
     * 费用计算方式 含税/不含税
     */
    private String feeCalculateModeCd;
}
