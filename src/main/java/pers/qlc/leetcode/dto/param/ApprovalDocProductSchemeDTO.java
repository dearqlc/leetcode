package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalDocProductSchemeDTO {

    /**
     * 选择产品列表
     */
    private List<String> productList;

    /**
     * 选择产品详情列表
     */
    private List<ApprovalProductDTO> productDTOS;

    /**
     * 商业险
     */
    private ApprovalDocProductDTO commercialProductDTO;

    /**
     * 交强险
     */

    private ApprovalDocProductDTO ctpProductDTO;
    /**
     * 意外险 集合
     */
    private List<ApprovalDocProductDTO> accidentProducts;
    /**
     * 增值服务
     */
    private List<AddValueServiceDTO> addValueServices;

}
