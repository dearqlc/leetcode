/**
 * Aliyun.com Inc.
 * Copyright (c) 2004-2022 All Rights Reserved.
 */
package pers.qlc.leetcode.enums;

import com.alibaba.common.lang.StringUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * 合作伙伴分类
 *
 * @author lengbaojin.pt
 * @version $Id: PartnerTypeEnum.java, v 0.1 2022-09-05 16:46 lengbaojin.pt Exp $$
 */
@Getter
@AllArgsConstructor
public enum PartnerTypeEnum {

    A("渠道展业合作伙伴", "A", null),
    B("基础服务合作伙伴", "B", null),
    C("增值服务合作伙伴", "C", null),
    D("再/共保合作伙伴", "D", null),

    A01("业务渠道", "A01", PartnerTypeEnum.A),

    A0101("车商渠道", "A0101", PartnerTypeEnum.A01),
    A0102("出行服务公司", "A0102", PartnerTypeEnum.A01),
    A0103("专业代理", "A0103", PartnerTypeEnum.A01),
    A0104("兼业代理其他", "A0104", PartnerTypeEnum.A01),
    A0105("经纪公司", "A0105", PartnerTypeEnum.A01),
    A0106("燃气渠道", "A0106", PartnerTypeEnum.A01),
    A0107("邮政集团", "A0107", PartnerTypeEnum.A01),
    A0108("银行及其他金融机构", "A0108", PartnerTypeEnum.A01),
    A0109("互联网业务合作伙伴", "A0109", PartnerTypeEnum.A01),

    A010101("乘用车4S店", "A010101", PartnerTypeEnum.A0101),
    A010102("商用车4S店", "A010102", PartnerTypeEnum.A0101),
    A010103("二级经销商", "A010103", PartnerTypeEnum.A0101),
    A010104("专业二手车销售商", "A010104", PartnerTypeEnum.A0101),
    A010105("汽车新零售商", "A010105", PartnerTypeEnum.A0101),
    A010106("非4S店修理厂", "A010106", PartnerTypeEnum.A0101),
    A010107("汽车金融公司", "A010107", PartnerTypeEnum.A0101),
    A010108("经销商集团", "A010108", PartnerTypeEnum.A0101),
    A010109("主机厂", "A010109", PartnerTypeEnum.A0101),
    A010200("出行服务公司", "A010200", PartnerTypeEnum.A0102),
    A010301("集团性代理公司", "A010301", PartnerTypeEnum.A0103),
    A010302("全国性专业保险代理公司", "A010302", PartnerTypeEnum.A0103),
    A010303("区域性专业保险代理公司", "A010303", PartnerTypeEnum.A0103),
    A010401("石油石化类", "A010401", PartnerTypeEnum.A0104),
    A010402("电信电力类", "A010402", PartnerTypeEnum.A0104),
    A010403("旅游出行类", "A010403", PartnerTypeEnum.A0104),
    A010404("保险公司代理", "A010404", PartnerTypeEnum.A0104),
    A010405("其他兼业代理机构", "A010405", PartnerTypeEnum.A0104),
    A010501("央企直管经纪公司", "A010501", PartnerTypeEnum.A0105),
    A010502("市场化经纪公司", "A010502", PartnerTypeEnum.A0105),
    A010601("全国性燃气公司", "A010601", PartnerTypeEnum.A0106),
    A010602("地方性燃气公司", "A010602", PartnerTypeEnum.A0106),
    A010603("燃气行业其它公司", "A010603", PartnerTypeEnum.A0106),
    A010701("邮政公司", "A010701", PartnerTypeEnum.A0107),
    A010801("中央银行", "A010801", PartnerTypeEnum.A0108),
    A010802("政策性银行", "A010802", PartnerTypeEnum.A0108),
    A010803("国有大型商业银行", "A010803", PartnerTypeEnum.A0108),
    A010804("全国性股份制商业银行", "A010804", PartnerTypeEnum.A0108),
    A010805("城市商业银行", "A010805", PartnerTypeEnum.A0108),
    A010806("农村商业银行", "A010806", PartnerTypeEnum.A0108),
    A010807("农村合作银行", "A010807", PartnerTypeEnum.A0108),
    A010808("村镇银行", "A010808", PartnerTypeEnum.A0108),
    A010809("农村信用联社、农村信用社", "A010809", PartnerTypeEnum.A0108),
    A010810("外资银行", "A010810", PartnerTypeEnum.A0108),
    A010811("中外合资银行", "A010811", PartnerTypeEnum.A0108),
    A010812("互联网银行", "A010812", PartnerTypeEnum.A0108),
    A010813("其它银行", "A010813", PartnerTypeEnum.A0108),
    A010814("其它金融机构", "A010814", PartnerTypeEnum.A0108),
    A010901("综合性平台", "A010901", PartnerTypeEnum.A0109),
    A010902("行业性平台", "A010902", PartnerTypeEnum.A0109),
    A010903("信息流平台", "A010903", PartnerTypeEnum.A0109),
    A010904("区域性平台", "A010904", PartnerTypeEnum.A0109),

    B01("车辆配件供应商", "B01", PartnerTypeEnum.B),
    B02("车辆维修", "B02", PartnerTypeEnum.B),
    B03("技术服务伙伴", "B03", PartnerTypeEnum.B),
    B04("损余处理供应商", "B04", PartnerTypeEnum.B),
    B05("公估服务供应商", "B05", PartnerTypeEnum.B),
    B06("公检法机构", "B06", PartnerTypeEnum.B),
    B07("鉴定机构", "B07", PartnerTypeEnum.B),
    B08("法务服务供应商", "B08", PartnerTypeEnum.B),
    B09("医疗服务供应商", "B09", PartnerTypeEnum.B),

    B0101("汽配城供应商", "B0101", PartnerTypeEnum.B01),
    B0102("区域中心库", "B0102", PartnerTypeEnum.B01),
    B0103("配套生产厂商", "B0103", PartnerTypeEnum.B01),
    B0104("区域代理商", "B0104", PartnerTypeEnum.B01),
    B0105("汽配电商", "B0105", PartnerTypeEnum.B01),
    B0201("维修服务", "B0201", PartnerTypeEnum.B02),
    B0300("技术服务伙伴", "B0300", PartnerTypeEnum.B03),
    B0400("残值拍卖商", "B0400", PartnerTypeEnum.B04),
    B0401("损余物资回收商", "B0401", PartnerTypeEnum.B04),
    B0500("公估服务供应商", "B0500", PartnerTypeEnum.B05),
    B0601("刑警队", "B0601", PartnerTypeEnum.B06),
    B0602("派出所", "B0602", PartnerTypeEnum.B06),
    B0603("纪委", "B0603", PartnerTypeEnum.B06),
    B0604("法院", "B0604", PartnerTypeEnum.B06),
    B0605("仲裁机构", "B0605", PartnerTypeEnum.B06),
    B0701("伤残、失能鉴定机构/个人", "B0701", PartnerTypeEnum.B07),
    B0801("律师事务所", "B0801", PartnerTypeEnum.B08),
    B0802("专业追偿机构/调查机构", "B0802", PartnerTypeEnum.B08),
    B0901("医院", "B0901", PartnerTypeEnum.B09),
    B0902("TPA", "B0902", PartnerTypeEnum.B09),

    B010100("汽配城供应商", "B010100", PartnerTypeEnum.B0101),
    B010200("区域中心库", "B010200", PartnerTypeEnum.B0102),
    B010300("配套生产厂商", "B010300", PartnerTypeEnum.B0103),
    B010400("区域代理商", "B010400", PartnerTypeEnum.B0104),
    B010500("汽配电商", "B010500", PartnerTypeEnum.B0105),
    B020101("品牌厂家授权服务站", "B020101", PartnerTypeEnum.B0201),
    B020102("综合一类厂", "B020102", PartnerTypeEnum.B0201),
    B020103("综合二类厂", "B020103", PartnerTypeEnum.B0201),
    B020104("综合三类厂", "B020104", PartnerTypeEnum.B0201),
    B030000("技术服务伙伴", "B030000", PartnerTypeEnum.B0300),
    B040000("残值拍卖商", "B040000", PartnerTypeEnum.B0400),
    B040100("损余物资回收商", "B040100", PartnerTypeEnum.B0401),
    B050000("公估服务供应商", "B050000", PartnerTypeEnum.B0500),
    B060100("刑警队", "B060100", PartnerTypeEnum.B0601),
    B060200("派出所", "B060200", PartnerTypeEnum.B0602),
    B060300("纪委", "B060300", PartnerTypeEnum.B0603),
    B060400("法院", "B060400", PartnerTypeEnum.B0604),
    B060500("仲裁机构", "B060500", PartnerTypeEnum.B0605),
    B070101("医院", "B070101", PartnerTypeEnum.B0701),
    B070102("专业鉴定机构", "B070102", PartnerTypeEnum.B0701),
    B070103("医疗专家", "B070103", PartnerTypeEnum.B0701),
    B080100("律师事务所", "B080100", PartnerTypeEnum.B0801),
    B080200("专业追偿机构/调查机构", "B080200", PartnerTypeEnum.B0802),
    B090100("医院", "B090100", PartnerTypeEnum.B0901),
    B090200("TPA", "B090200", PartnerTypeEnum.B0902),

    C01("医疗辅助服务供应商", "C01", PartnerTypeEnum.C),
    C02("健康服务供应商", "C02", PartnerTypeEnum.C),
    C03("车后服务供应商", "C03", PartnerTypeEnum.C),

    C0101("人伤援助机构（直升机等）", "C0101", PartnerTypeEnum.C01),
    C0102("残疾器具供应商", "C0102", PartnerTypeEnum.C01),
    C0103("跟踪服务供应商（医院）", "C0103", PartnerTypeEnum.C01),
    C0104("医护服务（护工等）", "C0104", PartnerTypeEnum.C01),
    C0201("药品配送", "C0201", PartnerTypeEnum.C02),
    C0202("绿通", "C0202", PartnerTypeEnum.C02),
    C0203("健康咨询\\服务", "C0203", PartnerTypeEnum.C02),
    C0204("体检机构", "C0204", PartnerTypeEnum.C02),
    C0301("车后增值服务", "C0301", PartnerTypeEnum.C03),

    C010100("人伤援助机构（直升机等）", "C010100", PartnerTypeEnum.C0101),
    C010200("残疾器具供应商", "C010200", PartnerTypeEnum.C0102),
    C010300("跟踪服务供应商（医院）", "C010300", PartnerTypeEnum.C0103),
    C010401("医院", "C010401", PartnerTypeEnum.C0104),
    C010402("专业护理机构", "C010402", PartnerTypeEnum.C0104),
    C020100("药品配送", "C020100", PartnerTypeEnum.C0201),
    C020200("绿通", "C020200", PartnerTypeEnum.C0202),
    C020301("咨询", "C020301", PartnerTypeEnum.C0203),
    C020302("综合服务", "C020302", PartnerTypeEnum.C0203),
    C020401("医院", "C020401", PartnerTypeEnum.C0204),
    C020402("民办", "C020402", PartnerTypeEnum.C0204),
    C030101("快修服务供应商", "C030101", PartnerTypeEnum.C0301),
    C030102("汽车俱乐部", "C030102", PartnerTypeEnum.C0301),
    C030103("车务公司", "C030103", PartnerTypeEnum.C0301),
    C030104("汽车装美店", "C030104", PartnerTypeEnum.C0301),
    C030105("代驾平台", "C030105", PartnerTypeEnum.C0301),
    C030106("汽车租赁", "C030106", PartnerTypeEnum.C0301),
    C030107("汽车检测站", "C030107", PartnerTypeEnum.C0301),
    C030108("救援服务", "C030108", PartnerTypeEnum.C0301),

    D01("再保险公司", "D01", PartnerTypeEnum.D),
    D02("再保险经纪公司", "D02", PartnerTypeEnum.D),
    D03("保险公司", "D03", PartnerTypeEnum.D),
    D04("自保公司", "D04", PartnerTypeEnum.D),
    D05("共保体", "D05", PartnerTypeEnum.D),
    D06("保险互助组织", "D06", PartnerTypeEnum.D),
    D07("劳合社", "D07", PartnerTypeEnum.D),
    D08("辛迪加", "D08", PartnerTypeEnum.D),

    D0101("总公司", "D0101", PartnerTypeEnum.D01),
    D0102("各级分公司", "D0102", PartnerTypeEnum.D01),
    D0201("总公司", "D0201", PartnerTypeEnum.D02),
    D0202("各级分公司", "D0202", PartnerTypeEnum.D02),
    D0301("总公司", "D0301", PartnerTypeEnum.D03),
    D0302("各级分公司", "D0302", PartnerTypeEnum.D03),
    D0401("总公司", "D0401", PartnerTypeEnum.D04),
    D0402("各级分公司", "D0402", PartnerTypeEnum.D04),
    D0500("共保体", "D0500", PartnerTypeEnum.D05),
    D0600("保险互助组织", "D0600", PartnerTypeEnum.D06),
    D0700("劳合社", "D0700", PartnerTypeEnum.D07),
    D0800("辛迪加", "D0800", PartnerTypeEnum.D08),

    D010100("总公司", "D010100", PartnerTypeEnum.D0101),
    D010200("各级分公司", "D010200", PartnerTypeEnum.D0102),
    D020100("总公司", "D020100", PartnerTypeEnum.D0201),
    D020200("各级分公司", "D020200", PartnerTypeEnum.D0202),
    D030100("总公司", "D030100", PartnerTypeEnum.D0301),
    D030200("各级分公司", "D030200", PartnerTypeEnum.D0302),
    D040100("总公司", "D040100", PartnerTypeEnum.D0401),
    D040200("各级分公司", "D040200", PartnerTypeEnum.D0402),
    D050000("共保体", "D050000", PartnerTypeEnum.D0500),
    D060000("保险互助组织", "D060000", PartnerTypeEnum.D0600),
    D070000("劳合社", "D070000", PartnerTypeEnum.D0700),
    D080000("辛迪加", "D080000", PartnerTypeEnum.D0800);

    private String name;
    private String code;
    private PartnerTypeEnum parent;

    public static PartnerTypeEnum getByCode(String code) {
        if (code == null) {
            return null;
        }
        for (PartnerTypeEnum type : PartnerTypeEnum.values()) {
            if (code.equals(type.getCode())) {
                return type;
            }
        }
        return null;
    }


    public static String getNameByCode(String code) {
        if (code == null) {
            return null;
        }
        for (PartnerTypeEnum type : PartnerTypeEnum.values()) {
            if (code.equals(type.getCode())) {
                return type.getName();
            }
        }
        return null;
    }

    public static List<PartnerTypeEnum> getByParent(PartnerTypeEnum parent) {
        List<PartnerTypeEnum> returnList = new ArrayList<>();
        if (parent == null) {
            return returnList;
        }
        for (PartnerTypeEnum type : PartnerTypeEnum.values()) {
            if (parent.equals(type.getParent())) {
                returnList.add(type);
            }
        }
        return returnList;
    }

    public static String getChannelTypeByCode(String code) {
        if (StringUtil.isBlank(code)) {
            return null;
        }
        for (PartnerTypeEnum type : PartnerTypeEnum.values()) {
            if (code.equals(type.getCode())) {
                // 渠道没有父级
                if (type.getParent() == null) {
                    return type.getCode();
                }
                return getChannelTypeByCode(type.getParent().getCode());
            }
        }
        return null;
    }

    public static PartnerTypeEnum getByName(String name) {
        if (name == null) {
            return null;
        }
        for (PartnerTypeEnum type : PartnerTypeEnum.values()) {
            if (name.equals(type.getName())) {
                return type;
            }
        }
        return null;
    }


}