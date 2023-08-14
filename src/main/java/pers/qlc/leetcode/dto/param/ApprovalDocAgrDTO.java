package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
public class ApprovalDocAgrDTO {

    /**
     * 甲方机构
     */
    private String agrSignComCode;

    /**
     * 协议名称
     */
    private String agrName;

    /**
     * 合约号
     */
    private String agrNo;

    /**
     * 协议生效起期 协议生效止期
     */
    private Date dateStart;

    /**
     * 协议生效止期
     */
    private Date dateEnd;

    /**
     * 协议签订
     */
    private Date agrSignDate;

    /**
     * 是否总对总
     */
    private Boolean agrIsHead;

    /**
     * 服务保证金缴纳金额（万）
     */
    private BigDecimal serviceBondPayAmount;

    /**
     * 协议授权机构范围
     * 码值多选
     */
//    private String authorizeRanges;
    private List<ApprovalDocOrgDTO> authorizeRanges;

    /**
     * 银行账户信息
     */
    private List<ApprovalDocAccountDTO> accounts;

    /**
     * 结算信息
     */
    private ApprovalPaymentSettlementDTO paymentSettlementDTO;

    /**
     * 合作项目
     */
    private List<CoopItemDTO> coopItemDTOList;

    /**
     * 合约域组件Json
     */
    private String agreementExtendJson;

    public String getAgrNo() {
        return agrNo;
    }

    public void setAgrNo(String agrNo) {
        this.agrNo = agrNo;
    }
}
