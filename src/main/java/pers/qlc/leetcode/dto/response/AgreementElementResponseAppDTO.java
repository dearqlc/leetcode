package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class AgreementElementResponseAppDTO {

    /**
     * 合约元素上的文件信息
     */
    private List<DocumentResponseAppDTO> documents;
    /**
     * 合约元属上的扩展信息，一般是用来存储特殊合约的临时性扩展信息。
     * 需要定期清理，对于通用的合约格式，需要扩展合约/合约项对象来增强这个属性
     */
    private Map<String, Object> extendInfo;
    /**
     * 合约上的数额依赖，可以是金额，费率，次数等相关对象
     */
    private AmountResponseAppDTO left;
    /**
     * 合约内容的文字信息
     */
    private List<TermResponseAppDTO> terms;
    /**
     * 余额对象， 和Amount对象相对应
     */
    private BalanceResponseAppDTO balance;
    /**
     * 合约上的控制策略，包括计费，生效，失效等。
     */
    private List<ControlStrategyResponseAppDTO> strategy;
    /**
     * 合约上时间相关的内容信息， 包括开始时间，结束时间等。
     */
    private ValidityPeriodResponseAppDTO period;
}