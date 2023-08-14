package pers.qlc.leetcode.dto;

import lombok.Data;

@Data
public class EcologyQueryDTO {
    private String id;
    private String agreementNo;
    private String agreementMajorVersionNo;
    private String agreementMinorVersionNo;
    private String agreementName;
    private String agreementTypeCd;
    private String signDt;
    private String startDt;
    private String endDt;
    private String participantName;
    private String agreementStatusCd;
    private String certTypeCd;
    private String certNo;
    private String agentCode;
}
