package pers.qlc.leetcode.dto.request;

import lombok.Data;

import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/10/14 15:35
 * Content:
 */
@Data
public class AgreementElementInfo {
    /**
     * 合约控制策略信息
     */
    private List<ControlStrategyInfo> strategy;
}
