package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @Author: GW
 * @Date: 2022/11/10 21:43
 * Content:
 */
@Data
public class ParticipantResponseDTO {
    private String id;
    private String roleName;
    private Boolean isSingleRole;
    private BigDecimal shareDutyRate;
    private Integer participantNo;
    private String participantTypeCd;
    private String participantProperty;
    private String agreementId;
    private String participantName;
    private String streetCode;
    private String mobilePhoneNo;
    private String telephoneNo;
    private String permanentAddress;
    private String detailAddress;
    private String accountBranchCode;
    private String bankAccountNo;
    private String certTypeCd;
    private String certNo;
    private LocalDate certEffectiveStartDt;
    private LocalDate certEffectiveEndDt;
    private String signOrg;
    private Boolean isCertLongTerm;
    private Integer genderCd;
    private Integer age;
    private String nation;
    private String countryCode;
    private LocalDate birthDt;
    private String employeeNo;
    private String customerNo;
    private Integer custMlRiskCd;
    private String orgCode;
    private String fleetNo;
    private String belongOrg;
    private String agent;
    private String agencyAgreement;
    private String bizRiskTypeCd;
    private String bizCode;
    private String bizCompany;
    private String thirdSalesman;
    private String postalCode;
    private String email;
    private String orgTypeCd;
    private Boolean isCic;
    private String belongOrgCode;
    private CoinsuranceResponseDTO coinsurance;
}
