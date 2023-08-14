package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class ValidityPeriodResponseAppDTO {
    /**
     *  签署时间（甲方线下签署时间）
     */
    private LocalDateTime firstPartySignDt;
    /**
     * 签署时间
     */
    private LocalDateTime signDt;
    /**
     * 起始时间
     */
    private LocalDateTime startDt;
    /**
     * 结束时间
     */
    private LocalDateTime endDt;
    /**
     * 状态变更时间
     */
    private LocalDateTime agreementModifiedDt;
    /**
     * 上面的时间默认显示到天
     */
    private String dateFormat;
    /**
     * 合同年度
     */
    private Integer contractYear;
}