package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:44
 * Content:
 */
@Data
public class RuleResponseDTO {
    private String ruleCode;
    private String ruleName;
    private String ruleTypeCd;
    private String executeUnitCode;
    private String agreementBillingMethodCd;
    private String agreementBillingTypeCd;
    private Boolean isEnabled;
}
