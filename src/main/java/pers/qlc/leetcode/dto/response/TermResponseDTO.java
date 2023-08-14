package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.Map;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:42
 * Content:
 */
@Data
public class TermResponseDTO {
    private String agreementTermNo;
    private String agreementTermContent;
    private Integer termSequenceNo;
    private Integer specialAgreedTypeCd;
    private Boolean isModify;
    private Boolean isCompelUsage;
    private Boolean isParam;
    private String termLargeField;
    private Map<String, String> values;
}
