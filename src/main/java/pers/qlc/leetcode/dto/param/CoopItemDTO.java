package pers.qlc.leetcode.dto.param;

import lombok.Data;

/**
 * 合作项目
 * @author zq
 */
@Data
public class CoopItemDTO {
    /**
     * 合作项目编码
     */
    private String itemCode;
    /**
     * 合作项目名称
     */
    private String itemName;
    /**
     *  是否合作 0 不合作 1 合作
     */
    private String coopStatus;
}
