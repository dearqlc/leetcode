package pers.qlc.leetcode.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApprovalDocEmployeeDTO {

    /**
     * 员工姓名
     */
    private String name;

    /**
     * 员工类型
     * （销售业务对接人、理赔业务对接人
     */
    private String employeeType;// 码值

    /**
     * 是否专职中华
     */
    private Boolean isConcentrateCic;

    /**
     * 资格证号
     */
    private String qualifyNo;

    /**
     * 身份证号
     */
    private String identityNo;

    /**
     * 执业证号
     */
    private String practiceLicenseNo;

    /**
     * 联系号码
     */
    private String phone;

    /**
     * 邮箱
     */
    private String mail;

    /**
     * 作业行政区域
     */
    private List<ApprovalDocCompAppDTO> workAreas;

    /**
     * 备注
     */
    private String remarkContent;

    /**
     * 员工归属机构
     */
    private String orgCode;

    /**
     * 员工归属机构名称
     */
    private String orgName;
}
