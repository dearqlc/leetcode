package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ApprovalDocActualCommissionDetailDTO {


    /**
     * 产品类码值
     */
    private String productTypeCode;

    /**
     * 产品代码
     */
    private String productCode;

    /**
     * 产品简称
     */
    private String productName;

    /**
     * 新旧车
     */
    private List<String> newOldCar;

    /**
     * 车辆使用性质
     */
    private List<String> vehicleUsage;

    /**
     * 转保续保
     */
    private String newRenewal;

    /**
     * 是否过户
     */
    private String transferOwnership;

    /**
     * 三者险保额
     */
    private ApprovalDocRangeNumberDTO thirdPartyInsuranceValue;

    /**
     * 实际结算手续费比例
     */
    private Double actualCommissionRate;

    /**
     * 协议生效起期
     */
    private Date dateStart;

    /**
     * 协议生效止期
     */
    private Date dateEnd;

}
