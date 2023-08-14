package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/9/19 19:54
 * Content:
 */
@Data
public class AgreementResponseDTO {
    private String modifier;
    private LocalDateTime gmtModified;
    private String creator;
    private LocalDateTime gmtCreate;
    private String agreementInstanceId;
    private Integer agreementMajorVersionNo;
    private Integer agreementMinorVersionNo;
    private String agreementNo;
    private String agreementTypeCd;
    private String agreementStatusCd;
    private String agreementName;
    private Boolean isGeneralAgreement;
    private Boolean isAgreement;
    private Integer bizLinkModeCd;
    private String resolveTypeCd;
    private String resolveOrgName;
    private String agreementDesc;
    private String socialCreditCode;
    private String agreementSignAddress;
    private String jointInsuranceTypeCd;
    private Boolean isValid;
    private AgreementElementResponseDTO agreementElement;
    private List<ParticipantResponseDTO> participants;
    private String orgCode;
    private String orgName;
}
