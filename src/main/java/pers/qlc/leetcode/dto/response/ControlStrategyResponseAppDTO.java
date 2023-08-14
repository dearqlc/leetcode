package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class ControlStrategyResponseAppDTO {
    /**
     * 合约控制策略规则
     */
    private List<RuleResponseAppDTO> rules;
    /**
     * 合约控制策略类型 0-计费策略,1-有效策略,2-取消策略,3-通用能力策略
     */
    private String controlStrategyTypeCd;
}