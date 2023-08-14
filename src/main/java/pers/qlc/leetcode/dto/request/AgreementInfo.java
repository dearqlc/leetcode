package pers.qlc.leetcode.dto.request;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/12/15 10:18
 * Content: 合约域合约信息
 */
@Data
public class AgreementInfo {

    /**
     * 合约号
     */
    private String agreementNo;

    /**
     * 合约版本号
     */
    private Integer agreementVersion;

    /**
     * 合同名称
     */
    private String agrName;

    /**
     * 合同起始日期
     */
    private Date agrStartDate;

    /**
     * 合同终止日期
     */
    private Date agrEndDate;

    /**
     * 是否总对总
     */
    private String agrIsHead;

    /**
     * 甲方签署机构代码
     */
    private String agrSignComCode;

    /**
     * 甲方签署机构名称
     */
    private String agrSignComName;

    /**
     * 甲方签署人
     */
    private String agrSignPerson;

    /**
     * 甲方签署人UM账号
     */
    private String agrUMAccount;

    /**
     * 签署时间(乙方线下签署时间)
     */
    private Date agrSignDate;

    /**
     * 签署时间（甲方线下签署时间）
     */
    private Date firstPartySignDt;

    /**
     * 伙伴id
     */
    private String roleId;

    /**
     * 伙伴名称
     */
    private String partnerName;

    /**
     * 是否是展业类数据
     */
    private Boolean isExpandBusiness;

    /**
     * 合约类型
     */
    private String agreementTypeCode;

    /**
     * 结算信息（包含账户信息）
     */
    private PaymentSettlementInfo paymentSettlement;

    /**
     * 协议授权范围
     */
    private List<DepartmentInfo> authorizationScope;

    /**
     * 合约项列表（包含服务项目）
     */
    private List<AgreementItemInfo> items;

    /**
     * 甲方签署人机构路径
     */
    private String agrSignComPath;

    /**
     * 合约域Json
     */
    private String agreementExtendJson;
}
