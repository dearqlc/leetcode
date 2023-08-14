package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:40
 * Content:
 */
@Data
public class AgreementElementResponseDTO {
    private List<DocumentResponseDTO> documents;
    private Map<String, Object> extendInfo;
    private AmountResponseDTO left;
    private List<TermResponseDTO> terms;
    private BalanceResponseDTO balance;
    private List<ControlStrategyResponseDTO> strategy;
    private ValidityPeriodResponseDTO period;
}
