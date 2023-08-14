package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author zsh
 */
@Data
public class AddValueServiceDTO {
    /**
     *增值服务id
     */
    private String addedValueId;

    /**
     *增值服务名称
     */
    private String addedValueName;

    /**
     *增值服务成本
     */
    private BigDecimal cost;

    /**
     *增值服务价格
     */
    private BigDecimal price;

    /**
     *增值服务类型
     */
    private Integer addedValueTypeCd;

    /**
     *条款服务分类
     */
    private Integer clauseTypeCd;

    /**
     *条款服务分类
     */
    private String coverageCode;

    /**
     *责任名称
     */
    private String productTypeCd;

    /**
     *产品类型
     */
    private String productTypeName;

    /**
     *产品类型名称
     */
    private String serverProductCode;

    /**
     *服务产品代码
     */
    private String serverProductName;

    /**
     *条款编码
     */
    private String  serviceClauseCode;

    /**
     *条款名称
     */
    private String  serviceClauseName;

    /**
     *次数
     */
    private Integer number;
}
