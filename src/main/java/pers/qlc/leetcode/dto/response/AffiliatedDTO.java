package pers.qlc.leetcode.dto.response;


import lombok.Data;

import java.util.Date;

@Data
public class AffiliatedDTO {
    private String roleId;
    private String partnerName;
    private String agreementIsCoop;
    private String insuranceProductCode;
    private String insuranceProductName;
    private String roleIdDepend;
    private String partnerNameDepend;
    private Date dependDateStart;
    private Date dependDateEnd;
    private Integer isDefault;
    private String agreementNoDepend;
    private String dependType;
}
