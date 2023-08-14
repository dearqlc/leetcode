package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class DepartmentResponseAppDTO {
    /**
     * 部门编码
     */
    private String orgCode;
    /**
     * 机构名称
     */
    private String orgName;
}