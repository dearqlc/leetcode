package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/11/14 14:00
 * Content:
 */
@Data
public class AgreementResponseAppDTO {

    /**
     * 主键
     */
    private String agreementInstanceId;
    /**
     * 一级版本号
     */
    private Integer agreementMajorVersionNo;
    /**
     * 二级版本号
     */
    private Integer agreementMinorVersionNo;
    /**
     * 合约号
     */
    private String agreementNo;
    /**
     * 合约类型
     */
    private String agreementTypeCd;
    /**
     * 合约元素的状态，在合约对象上就是合约对象的状态
     */
    private String agreementStatusCd;
    /**
     * 合同名称
     */
    private String agreementName;
    /**
     * 总对总协议
     */
    private Boolean isGeneralAgreement;
    /**
     * 是否是合约
     */
    private Boolean isAgreement;
    /**
     * 业务对接方式 0-线上对接 1-系统授权 2-线上对接
     */
    private Integer bizLinkModeCd;
    /**
     * 争议解决方式类型 01-诉讼 02-仲裁 03-协商 04-其他
     */
    private String resolveTypeCd;
    /**
     * 解决组织名称
     */
    private String resolveOrgName;
    /**
     * 合约项列表
     */
    private List<AgreementItemResponseAppDTO> items = new ArrayList<>();
    /**
     * 合约上的所有参与方
     */
    private List<ParticipantResponseAppDTO> participants = new ArrayList<>();
    /**
     * 合约状态变更
     */
    private List<HistoryResponseAppDTO> histories = new ArrayList<>();
    /**
     * 合约结算方式
     */
    private PaymentSettlementResponseAppDTO paymentSettlement;
    /**
     * 税务信息
     */
    private TaxResponseAppDTO tax;
    /**
     * 针对合约对象的描述， 无法律意义
     */
    private String agreementDesc;
    /**
     * 统一社会信用代码
     */
    private String socialCreditCode;
    /**
     * 授权协议范围
     */
    private List<DepartmentResponseAppDTO> authorizationScope = new ArrayList<>();
    /**
     * 适用区域
     */
    private List<AddressResponseAppDTO> areas = new ArrayList<>();
    /**
     * 协议签订地址
     */
    private String agreementSignAddress;
    /**
     * 联共保方式
     */
    private String jointInsuranceTypeCd;
    /**
     * 是否有效
     */
    private Boolean isValid;
    /**
     * 这个合约的合约元素
     */
    private AgreementElementResponseAppDTO agreementElement;
    /**
     * 与这个合约相关的信息
     */
    private List<AgreementElementRelationshipResponseAppDTO> relationships = new ArrayList<>();

    private String orgCode;

    private String orgName;
}
