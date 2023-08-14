package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalDocPriceSchemeDTO {
    private String startDate;
    private String endDate;
    private List<ApprovalDocPriceSchemeDetailDTO> approvalDocPriceSchemeDetailDTOList;

}
