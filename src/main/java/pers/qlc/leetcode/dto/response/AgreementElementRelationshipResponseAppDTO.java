package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class AgreementElementRelationshipResponseAppDTO {
    /**
     * 关联合约的合约号
     */
    private String fromAgreementNo;
    /**
     * 关联合约项或参与方位置
     */
    private Integer fromSequenceNo;
    /**
     * from对象的ID
     */
    private String fromObjectId;
    /**
     * 关联类型
     */
    private String agreementRelationTypeCd;
}