package pers.qlc.leetcode.dto.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/10/14 15:39
 * Content:
 */
@Data
public class ControlStrategyInfo {
    /**
     * 规则集合
     */
    private List<RuleInfo> rules;
    /**
     * 合约控制策略类型
     */
    private String controlStrategyTypeCd;
}
