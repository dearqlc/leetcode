package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:42
 * Content:
 */
@Data
public class ControlStrategyResponseDTO {
    private List<RuleResponseDTO> rules;
    private String controlStrategyTypeCd;
}
