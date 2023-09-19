package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ApprovalDocPolicyExclusionDTO {
    /**
     * 核保方案编号
     */
    private List<String> schemeNos;

    /**
     * 规则类型编码
     */
    private String roleTypeCode;

    /**
     * 规则类型名称
     */
    private String roleTypeName;

    /**
     * 规则名称
     */
    private String roleName;

    /**
     * 审批权限
     */
    private String authApprovalLevel;

    /**
     * 提示语
     */
    private String underwriteLanguage;

    /**
     * 流向编码
     */
    private String regularFlowDirectionCode;

    /**
     * 流向名称
     */
    private String regularFlowDirectionName;

    /**
     * 规则Id
     */
    private String ruleBaseId;

    /**
     * 商业险市场费用
     */
    private BigDecimal marketExpenses;

    /**
     * 交强险市场费用
     */
    private BigDecimal clivtaMarketExpenses;

    /**
     * 自主系数上限
     */
    private BigDecimal upperLimit;

    /**
     * 自主系数下限
     */
    private BigDecimal lowerLimit;

    /**
     * 试算返回json，同步定价平台要用
     */
    private String rulePlatformResult;

}
