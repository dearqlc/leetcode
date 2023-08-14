package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalDocAbilityDTO {

    /**
     * 业务覆盖范围
     */
    private List<ApprovalDocOrgDTO> abilityCoverages;

}
