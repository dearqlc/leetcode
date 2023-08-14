package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalDocProductDTO {

    private String productCode;
    private String productName;
    private String productClassName;
    private String schemeCategory;
    private String schemeSmallCategory;
    private String productSubClassName;
    private String extendInfo;

    private List<ApprovalDocclauseDTO> clauseDTOS;

}
