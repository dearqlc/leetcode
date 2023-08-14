package pers.qlc.leetcode.dto.request;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/10/14 15:24
 * Content:
 */
@Data
public class BankAccountInfo {
    /**
     * 支付方式
     */
    private String payModePaymentCd;
    /**
     * 卡折类型
     */
    private String cardTypeCd;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 账号
     */
    private String accountNo;

    /**
     * 银行名称(银行大类)
     */
    private String bankName;
    /**
     * 银行代码
     */
    private String bankCode;
    /**
     * 账户省份/城市
     */
    private String counterpartyArea;
    /**
     * 开户网点
     */
    private String cnaps;
    /**
     * 对公标志
     */
    private Boolean isPublic;
}
