package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class BankAccountResponseAppDTO {
    /**
     * 用途
     */
    private String purpose;
    /**
     * 支付结算支付方式
     */
    private String settlementMethodCd;
    /**
     * 账户类型
     */
    private String accountTypeCd;
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
     * 开户网点所在区域
     */
    private String accountBranchAreaCode;
    /**
     * 开户网点
     */
    private String accountBranchCode;
    /**
     * 对公标志
     */
    private Boolean isPublic;
}
