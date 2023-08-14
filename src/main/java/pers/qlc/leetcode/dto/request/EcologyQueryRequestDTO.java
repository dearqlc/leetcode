package pers.qlc.leetcode.dto.request;

import lombok.Data;

@Data
public class EcologyQueryRequestDTO {
    private String participantName;
    private String insuranceClassCode;
    private String agreementTypeCd;
    private Integer pageSize;
}
