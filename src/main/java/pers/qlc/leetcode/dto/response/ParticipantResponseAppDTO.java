package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/11/14 16:09
 * Content:
 */
@Data
public class ParticipantResponseAppDTO {
    /**
     * 主键
     */
    private String id;
    /**
     * 当前参与方参与合约的角色，例如：甲方，乙方，代理，中介等
     */
    private String roleName;
    /**
     * 是否多个参与方参与同一个角色，例如再保合约中的几个分保方
     */
    private Boolean isSingleRole;
    /**
     * 共享角色的责任比例
     */
    private BigDecimal shareDutyRate;
    /**
     * 参与方所在位置的序号
     */
    private Integer participantNo;
    /**
     * 参与方类型
     */
    private String participantTypeCd;
    /**
     * 参与方性质
     */
    private String participantProperty;
    /**
     * 合同ID
     */
    private String agreementId;
    /**
     * 名称
     */
    private String participantName;
    /**
     * 省
     */
    private AddressResponseAppDTO provinceCode;
    /**
     * 市
     */
    private AddressResponseAppDTO cityCode;
    /**
     * 县/区
     */
    private AddressResponseAppDTO countyCode;
    /**
     * 街道
     */
    private String streetCode;
    /**
     * 移动电话
     */
    private String mobilePhoneNo;
    /**
     * 固定电话
     */
    private String telephoneNo;
    /**
     * 通讯地址
     */
    private String permanentAddress;
    /**
     * 详细地址
     */
    private String detailAddress;
    /**
     * 账户开户行
     */
    private String accountBranchCode;
    /**
     * 银行卡号
     */
    private String bankAccountNo;
    /**
     * 证件类型
     */
    private String certTypeCd;
    /**
     * 证件号码
     */
    private String certNo;
    /**
     * 证件有效期起
     */
    private LocalDate certEffectiveStartDt;
    /**
     * 证件有效期止
     */
    private LocalDate certEffectiveEndDt;
    /**
     * 签发机关
     */
    private String signOrg;
    /**
     * 证件是否长期
     */
    private Boolean isCertLongTerm;
    /**
     * 性别
     */
    private Integer genderCd;
    /**
     * 年龄
     */
    private Integer age;
    /**
     * 民族
     */
    private String nation;
    /**
     * 国籍
     */
    private String countryCode;
    /**
     * 出生日期
     */
    private LocalDate birthDt;
    /**
     * 员工编号
     */
    private String employeeNo;
    /**
     * 客户域的客户号
     */
    private String customerNo;
    /**
     * 客户洗钱风险标识
     */
    private Integer custMlRiskCd;
    /**
     * 组织机构编码
     */
    private String orgCode;
    /**
     * 车队号
     */
    private String fleetNo;
    /**
     * 归属机构
     */
    private String belongOrg;
    /**
     * 代理人
     */
    private String agent;

    /**
     * 中介机构编码agent_code
     */
    private String agentCode;

    /**
     * 代理协议
     */
    private String agencyAgreement;
    /**
     * 业务风险分类
     */
    private String bizRiskTypeCd;
    /**
     * 业务编码
     */
    private String bizCode;
    /**
     * 业务单位
     */
    private String bizCompany;
    /**
     * 第三方业务员
     */
    private String thirdSalesman;
    /**
     * 邮编
     */
    private String postalCode;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 机构代码类型(即机构代码的来源) innerCompanyCodeOfCic-中华内部机构，roleCodeOfPerformance-生态合作域角色，agentCodeOfSales-销管代理
     */
    private String orgTypeCd;
    /**
     * 这个参与方的合约元素
     */
    private AgreementElementResponseAppDTO agreementElement;
    /**
     * 与这个参与方相关的信息
     */
    private List<AgreementElementRelationshipResponseAppDTO> relationships = new ArrayList<>();
    /**
     * 共保信息
     */
    private CoinsuranceResponseAppDTO coinsurance;
}
