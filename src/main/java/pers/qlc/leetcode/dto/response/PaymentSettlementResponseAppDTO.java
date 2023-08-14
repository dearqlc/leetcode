package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class PaymentSettlementResponseAppDTO {
    /**
     * 结算周期 day-日,week-周,month-月,quarter-季,year-年,irregular-不定期
     */
    private String settlementPeriodCd;
    /**
     * 结算日
     */
    private Integer settlementDay;
    /**
     * 银行账号
     */
    private BankAccountResponseAppDTO bankAccount;
    /**
     * 结算方式 1-银行转账,2-支票,3-现金,4-其它
     */
    private Integer settlementMethodCd;
    /**
     * 结算环境 先开票后付款/先付款后开票/无票付款
     */
    private String settlementEnvironmentCd;
    /**
     * 开票方式 发票开至中支/发票开至分公司
     */
    private String invoiceModeCd;
    /**
     * 付款方式 一对一结算/总对总集中合并支付
     */
    private String paymentModeCd;
    /**
     * 费用计算方式 含税/不含税
     */
    private String feeCalculateModeCd;
    /**
     * 币种
     */
    private String currencyCd;
}