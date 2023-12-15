package pers.qlc.leetcode.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author QLC
 * @Date 2022/12/8 19:01
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProtocolRequestDataDTO {

    /**
     * 机构
     */
    private String orgcd;

    /**
     * 合作伙伴代码
     */
    private String partnerCode;

    /**
     * 合作伙伴名称
     */
    private String partnerName;

    /**
     * 协议号
     */
    private String agentProtocolCode;

    /**
     * 服务代码
     */
    private String serviceCode;

    /**
     * 车俩数
     */
    private Integer carCount;

    /**
     * 生效日期
     */
    private String startDate;

    /**
     * 失效日期
     */
    private String endDate;

    /**
     * 渠道类型
     */
    private String orgChannel;

    /**
     * 价费类型：
     * 1价费非价费
     * 2价费（传递值）
     * 3非加费
     */
    private String isPriceFee;

    /**
     * 版本号:默认0
     */
    private String partnerVersionNo;

    /**
     * 是否4s店：
     * 1:是
     * 0:否
     */
    private String partnerSystemType;

    /**
     * 合作伙伴细分二级代码
     */
    private String partnerLv4TypeCode;

    /**
     * 合作伙伴细分二级名称
     */
    private String partnerLv4TypeName;

}
