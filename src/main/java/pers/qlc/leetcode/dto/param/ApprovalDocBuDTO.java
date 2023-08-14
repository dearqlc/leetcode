package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ApprovalDocBuDTO {

    /**
     * 统一社会信用代码
     */
    private String socialCreditCode;

    /**
     * 合作伙伴名称
     */
    private String partnerName;

    /**
     * 客户域Id
     */
    private String customerId;

    /**
     * 伙伴名称
     */
    private String roleName;

    /**
     * 是否通过天眼查校验
     */
    private Boolean isCheck;

    /**
     * 法定代表人
     */
    private String legalPerson;

    /**
     * 地址
     */
    private String registryAddress;

    /**
     * 经度
     */
    private String registryLongitude;

    /**
     * 纬度
     */
    private String registryLatitude;

    /**
     * 经营范围
     */
    private String businessNature;

    /**
     * 注册资本
     */
    private BigDecimal registryMoney;

    /**
     * 营业执照起期
     */
    private Date busiLicenseDateStart;

    /**
     * 营业执照止期
     */
    private Date busiLicenseDateEnd;

    /**
     * 伙伴分类
     */
    private String partnerLv1TypeCode; 

    /**
     * 业务角色
     */
    private String partnerLv2TypeCode; 

    /**
     * 细分一级
     */
    private String partnerLv3TypeCode; 

    /**
     * 细分二级
     */
    private String partnerLv4TypeCode; 

    /**
     * 合作伙伴代码（车商）
     */
    private String roleId;

    /**
     * 合作伙伴代码（修理厂）
     */
    private String roleId2;

    /**
     * 合作伙伴简称
     */
    private String partnerAbbreviation;

    /**
     * 合作伙伴所属上级机构（经销商集团
     */
    private String groupPartnerRoleId;

    /**
     * 合作伙伴所属主机厂
     */
    private List<String> mainEngineFactoryRoleId;

    /**
     * 使用平台
     */
    private List<String> internetRoleId;

    /**
     * 归属业务机构
     */
    private String orgCode;

    /**
     * 归属业务机构名称
     */
    private String orgName;

    /**
     * 合作伙伴维护人员
     */
    private List<String> operator;

    /**
     * 是否保险代理
     */
    private Boolean isAgent;

    /**
     * 是否车辆维修
     */
    private Boolean isRepair;

    /**
     * 修理厂类型
     */
    private String repairPartnerType;

    /**
     * 预计伙伴年保费规模
     */
    private BigDecimal predictInsurancePremium;

    /**
     * 已合作保司
     */
    private List<ApprovalDocCompAppDTO> cooperationCompany;

    /**
     * 我司预期承保保费
     */
    private BigDecimal ourCompanyPredictInsurancePremium;

    /**
     * 是否有兼业代理资质
     */
    private Boolean isPartTimeAgentQualify;

    /**
     * 销售品牌
     */
    private List<ApprovalDocBrandDTO> saleBrands;

    /**
     * 维修品牌
     */
    private List<ApprovalDocBrandDTO> repairBrands;

    /**
     * 授权品牌
     */
    private List<ApprovalDocBrandDTO> authBrands;

    /**
     * 网点信息
     */
    private List<ApprovalDocBranchDTO> branches;

    /**
     * 业务对接人（合作伙伴员工
     */
    private List<ApprovalDocEmployeeDTO> partnerEmployee;
    /**
     * 合作伙伴业务人员
     *  如：
     * 调查人员
     * 律所人员信息（法务）
     */
     private List<ApprovalDocEmployeeDTO> partnerStaff;

    /**
     * 银行账户信息
     */
    private List<ApprovalDocAccountDTO> accounts;

    /**
     * 影像信息
     */
    private List<ApprovalDocImageDTO> images;

    /**
     * 机构类型代码
     */
    private String partnerOrgPropertiesCode;

    /**
     * 机构类型名称
     */
    private String partnerOrgPropertiesName;

    /**
     * 服务能力覆盖范围代码(全国，指定机构)
     */
    private String serviceAbilityCoverageCode;

    /**
     * 服务能力覆盖范围名称
     */
    private String serviceAbilityCoverageName;

    /**
     * 指定机构代码
     */
    private List<ApprovalDocCompAppDTO> appointedOrg;

    /**
     * 是否可缴纳服务保证金
     */
    private Boolean isPayServiceDeposit;

    /**
     * 能否开具增值税专用发票
     */
    private Boolean isInvoicingVat;

    /**
     * 是否造车新势力
     */
    private Boolean isCarBuildPartner;

    /**
     * 仓库地址
     */
    private String warehouseAddress;

    /**
     *  覆盖机构范围
     */
    private List<ApprovalDocAbilityDTO> abilityDTOList;

    /**
     * 客服电话
     */
    private String customerServicePhone;

    /**
     * 其他
     */
    private String repairOther;

    /**
     * 保险机构代码
     */
    private String insuranceInstitutionCode;
}
