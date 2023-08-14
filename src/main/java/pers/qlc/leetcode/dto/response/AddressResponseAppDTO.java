package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class AddressResponseAppDTO {
    /**
     * 行政区划上面的地址号，和下面的文字地址保持一致
     */
    private String areaNo;
    /**
     * 行政区划的文字信息，例如：浙江省杭州市
     */
    private String area;
    /**
     * 省市区  1- 省  2-市 3-区
     */
    private Integer level;
}