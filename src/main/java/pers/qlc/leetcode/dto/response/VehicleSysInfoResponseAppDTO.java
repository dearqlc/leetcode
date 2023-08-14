package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @author zsh
 */
@Data
public class VehicleSysInfoResponseAppDTO {

    /**
     * 厂牌代码
     */
    private String subBrandId;

    /**
     * 厂牌名称
     */
    private String subBrand;

    /**
     * 车系名称
     */
    private String vehicleSys;

    /**
     * 车系代码
     */
    private String vehicleSysId;

    /**
     * 来源: 1-明觉 车系 2-阳杰车系
     */
    private String source;
}
