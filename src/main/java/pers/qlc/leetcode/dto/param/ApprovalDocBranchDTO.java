package pers.qlc.leetcode.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @Author QLC
 * @Date 2023/3/21 16:17
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDocBranchDTO {

    /**
     * 网点id
     */
    private String branchId;

    /**
     * 网点编码
     */
    private String branchCode;

    /**
     * 网点名称
     */
    private String branchName;

    /**
     * 网点归属机构名称
     */
    private String orgName;

    /**
     * 网点归属机构编码
     */
    private String orgCode;

    /**
     * 网点归属机构路径
     */
    private String orgPath;

    /**
     * 网点地址名称
     */
    private String branchAddressName;

    /**
     * 网点地址经度
     */
    private BigDecimal branchAddressLongitude;

    /**
     * 网点地址纬度
     */
    private BigDecimal branchAddressLatitude;

    /**
     * 指定送修店名称
     */
    private String branchAppointPartnerName;

    /**
     * 指定送修店roleId
     */
    private String branchAppointRoleId;

    /**
     * 指定送修店送修码
     */
    private String branchAppointRecommendCode;
}
