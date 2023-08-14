package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/25 16:50
 * Content:
 */
@Data
public class AgreementParticipantDTO {
    /**
     * 乙方代码
     */
    private String roleId;
    /**
     * 乙方名称
     */
    private String partnerName;
    /**
     * 甲方代码
     */
    private String orgCode;
    /**
     * 甲方名称
     */
    private String orgName;
    /**
     * 共保归属机构
     */
    private String belongOrgCode;
}
