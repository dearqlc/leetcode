package pers.qlc.leetcode.dto.request;

import lombok.Data;

@Data
public class AgreementQueryRequestDTO {
    private String agreementMajorVersionNo;
    private String agreementMinorVersionNo;
    private String agreementNo;
}
