package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: GW
 * @Date: 2022/11/14 16:09
 * Content:
 */
@Data
public class HistoryResponseAppDTO {

    /**
     * 主键
     */
    private String id;
    /**
     * 一级版本号
     */
    private Integer agreementMajorVersionNo;
    /**
     * 二级版本号
     */
    private Integer agreementMinorVersionNo;
    /**
     * 合约号
     */
    private String agreementNo;
    /**
     * 合约版本对应的状态， 同一个合约号的合约，处于生效/草稿状态的合约都最多只有一个。
     */
    private String agreementStatusCd;
    /**
     * 合约生效日期
     */
    private LocalDateTime effectDt;
    /**
     * 当前版本的最后更新人
     */
    private String modifier;
    /**
     * 当前版本的最后更新时间
     */
    private LocalDateTime gmtModified;
    /**
     * 当前版本的最后审计人
     */
    private String auditBy;
    /**
     * 当前版本的最后更新时间
     */
    private LocalDateTime auditDt;
}