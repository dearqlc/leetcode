package pers.qlc.leetcode.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 合作伙伴与渠道映射关系枚举
 *
 * @author zq
 */
@Getter
@AllArgsConstructor
public enum PartnerChannelMappingEnum {

//    A：车商渠道、B：电融渠道、C：线下渠道、D：中介渠道、E：网销渠道

    车商渠道(PartnerTypeEnum.A0101, "A", "车商渠道"),
    出行服务公司(PartnerTypeEnum.A0102, "A", "车商渠道"),
    专业代理(PartnerTypeEnum.A0103, "D", "中介"),
    兼业代理其他(PartnerTypeEnum.A0104, "D", "中介"),
    经纪公司(PartnerTypeEnum.A0105, "D", "中介"),
    燃气公司(PartnerTypeEnum.A0106, "D", "中介"),
    邮政集团(PartnerTypeEnum.A0107, "D", "中介"),
    银行及其他金融机构(PartnerTypeEnum.A0108, "D", "中介"),
    互联网业务合作伙伴(PartnerTypeEnum.A0109, "E", "网销");


    private PartnerTypeEnum partnerTypeEnum;
    private String channelCode;
    private String channelName;

    /**
     * 根据细分一级获取渠道
     *
     * @return
     */
    public static String getChannelByCode(String code) {
        if (code == null) {
            return null;
        }
        for (PartnerChannelMappingEnum type : PartnerChannelMappingEnum.values()) {
            if (type.partnerTypeEnum.getCode().equals(code)) {
                return type.channelCode;
            }
        }
        return null;
    }

    /**
     * 根据细分二级获取渠道
     *
     * @return
     */
    public static String getChannelByLevel4(String code) {
        if (code == null) {
            return null;
        }
        PartnerTypeEnum partnerTypeEnum = PartnerTypeEnum.getByCode(code);
        if (partnerTypeEnum == null) {
            return null;
        }
        for (PartnerChannelMappingEnum type : PartnerChannelMappingEnum.values()) {
            if (type.partnerTypeEnum.getCode().equals(partnerTypeEnum.getParent().getCode())) {
                return type.channelCode;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(getChannelByLevel4("A010101"));
    }
}
