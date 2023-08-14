package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:42
 * Content:
 */
@Data
public class ValidityPeriodResponseDTO {
    private LocalDateTime signDt;
    private LocalDateTime startDt;
    private LocalDateTime endDt;
    private LocalDateTime agreementModifiedDt;
    private String dateFormat;
    private Integer contractYear;
}
