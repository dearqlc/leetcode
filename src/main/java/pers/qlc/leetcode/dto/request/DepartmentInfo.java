package pers.qlc.leetcode.dto.request;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2023/4/18 14:22
 * Content: 授权范围
 */
@Data
public class DepartmentInfo {
    /**
     * 部门编码
     */
    private String orgCode;
    /**
     * 机构名称
     */
    private String orgName;
}
