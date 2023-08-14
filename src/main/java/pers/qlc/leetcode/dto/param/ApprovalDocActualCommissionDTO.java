package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalDocActualCommissionDTO {

    /**
     * 实际结算手续费设置
     */
    private List<ApprovalDocActualCommissionDetailDTO> actualCommissionDetailList;

    /**
     * 合约号
     */
    private String agreementNo;

    /**
     * 合约名称
     */
    private String agreementName;
}
