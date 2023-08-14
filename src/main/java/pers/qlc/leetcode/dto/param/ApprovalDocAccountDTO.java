package pers.qlc.leetcode.dto.param;

import lombok.Data;

@Data
public class ApprovalDocAccountDTO {

    /**
     * 账户用途（理赔户20、手续费
     * 本次默认20
     */
    private String accountUsage = "20";

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
     * 账号（账户
     */
    private String accountNo;

    /**
     * 银行名称
     */
    private String bankName;

    /**
     * 银行代码
     */
    private String bankCode;

    /**
     * 交易方区域(账户省份/城市)
     */
    private String counterpartyArea;

    /**
     * 开户行名称
     */
    private String depositBankName;

    /**
     * 开户行代码（开户行联行号）
     */
    private String cnaps;

    /**
     * 是否对公
     */
    private Boolean isPublic;

    /**
     * 私人账户影像信息
     */
    private ApprovalDocImageDTO imageDTO;

//    /**
//     * 账户类型
//     * 默认银行卡 2
//     */
//    private String accountTypeCode = "2";
//    /**
//     * 币种
//     */
//    private String currencyType;
//    /**
//     * 开户行三级行政单位（区）
//     */
//    private String unitLvl3Code;
//    /**
//     * 开户行一级行政单位（省）
//     */
//    private String unitLvl1Code;
//
//    /**
//     * 开户行二级行政单位（市）
//     */
//    private String unitLvl2Code;
}
