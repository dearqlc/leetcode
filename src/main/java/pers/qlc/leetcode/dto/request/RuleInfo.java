package pers.qlc.leetcode.dto.request;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/10/14 15:40
 * Content:
 */
@Data
public class RuleInfo {
    /**
     * 规则号
     */
    private String ruleCode;
    /**
     * 规则名称
     */
    private String ruleName;
    /**
     * 执行单元编码
     */
    private String executeUnitCode;
    /**
     * 计费方式 (0-按量计费(按照规模，总数量，区间计算费用) 1-按件计费(按照每一个服务项目，按件/按次数计费))
     */
    private String agreementBillingMethodCd;
    /**
     * 计费类型(0-标准计费 1-一单一议)
     */
    private String agreementBillingTypeCd;
    /**
     * 是否启用
     */
    private Boolean isEnabled;
    /**
     * 规则类型 rule-规则 formula-计算公式 decision-决策表
     */
    private String ruleTypeCd;
}
