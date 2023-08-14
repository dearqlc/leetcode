package pers.qlc.leetcode.dto.response;


import lombok.Data;

import java.util.Map;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class TermResponseAppDTO {
    /**
     * 合约条款編号
     */
    private String agreementTermNo;
    /**
     * 从产品域复制过来，待定：是否需要复制过来
     */
    private String agreementTermContent;
    /**
     * 条款所处位置的索引 从1开始递增 用于打印的条款排序
     */
    private Integer termSequenceNo;
    /**
     * 特别约定类型
     */
    private Integer specialAgreedTypeCd;
    /**
     * 是否可修改
     */
    private Boolean isModify;
    /**
     * 是否强制使用
     */
    private Boolean isCompelUsage;
    /**
     * 是否参数化
     */
    private Boolean isParam;
    /**
     * 大字段
     */
    private String termLargeField;
    /**
     * 合约条款集
     */
    private Map<String, String> values;
}