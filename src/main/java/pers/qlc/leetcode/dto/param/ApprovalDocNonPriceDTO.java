package pers.qlc.leetcode.dto.param;

import lombok.Data;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Objects;

@Data
public class ApprovalDocNonPriceDTO {

    /**
     * 方案编号
     */
    private String schemeNo;

    /**
     * 车辆使用性质 （多选）
     */
    private List<String> vehicleUsages;

    /**
     * 车辆种类 (多选)
     */
    private List<ApprovalDocLabelValueDTO>  vehicleTypes;

    /**
     * 细化车型 (多选)
     */
    private List<ApprovalDocLabelValueDTO> models;

    /**
     * 车辆使用性质
     */
    private String vehicleUsage;

    /**
     * 车辆种类
     */
    private String vehicleTypeCode;

    /**
     * 车辆种类名称
     */
    private String vehicleTypeName;

    /**
     * 细化车型
     */
    private String modelCode;


    /**
     * 细化车型名称
     */
    private String modelName;

    /**
     * 是否新能源 1 新能源 0 非新能源
     */
    private String isNewPower;

    /**
     * 新转续
     */
    private String newRenewal;
    /**
     * 车辆品牌编码
     */
    private String brandCode;

    /**
     * 车辆品牌名称
     */
    private String brandName;
    /**
     * 车系编码
     */
    private String carSeriesCode;

    /**
     * 车系名称
     */
    private String carSeriesName;
    /**
     * 客户风险评级范围
     */
    private ApprovalDocRangeNumberDTO customerRiskRange;
    /**
     * 车慧达大货车三者+车损评级
     */
    private List<String>  truckModelVehicleLevel;

    /**
     * 车慧达大货车三者评级
     */
    private List<String> threeScoreChd;
    /**
     * NCD等级
     */
    private List<String> rangeNCD;

    /**
     * 车牌号以XX开始
     */
    private String startWithLicense;
    /**
     * 车龄范围
     */
    private ApprovalDocRangeNumberDTO vehicleAgeMax;
    /**
     * 新车购置价范围（万元）
     */
    private ApprovalDocRangeNumberDTO purchasePriceRange;
    /**
     * 核保方案
     */
    private ApprovalDocProductSchemeDTO productSchemeDTO;

    /**
     * 判断车辆结构是否重复
     * 忽略 schemeNo、productSchemeDTO
     */
    public boolean structEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ApprovalDocNonPriceDTO that = (ApprovalDocNonPriceDTO) o;

        return stringEquals(vehicleUsage, that.vehicleUsage) &&
                stringEquals(vehicleTypeCode, that.vehicleTypeCode) &&
                stringEquals(vehicleTypeName, that.vehicleTypeName) &&
                stringEquals(modelCode, that.modelCode) &&
                stringEquals(modelName, that.modelName) &&
                stringEquals(isNewPower, that.isNewPower) &&
                stringEquals(newRenewal, that.newRenewal) &&
                stringEquals(brandCode, that.brandCode) &&
                stringEquals(brandName, that.brandName) &&
                stringEquals(carSeriesCode, that.carSeriesCode) &&
                stringEquals(carSeriesName, that.carSeriesName) &&
                listEquals(truckModelVehicleLevel, that.truckModelVehicleLevel) &&
                listEquals(threeScoreChd, that.threeScoreChd) &&
                listEquals(rangeNCD, that.rangeNCD) &&
                stringEquals(startWithLicense, that.startWithLicense) &&
                rangeEquals(customerRiskRange, that.customerRiskRange) &&
                rangeEquals(vehicleAgeMax, that.vehicleAgeMax) &&
                rangeEquals(purchasePriceRange, that.purchasePriceRange);
    }

    private boolean stringEquals(String a, String b){
        // 只要有一个为空，该字段一定重复
        if(StringUtils.isBlank(a) || StringUtils.isBlank(b)){
            return true;
        }
        return a.equals(b);
    }

    private boolean listEquals(List a, List b){
        // 只要有一个为空，该字段一定重复
        if(CollectionUtils.isEmpty(a) || CollectionUtils.isEmpty(b)){
            return true;
        }
        return a==b || (a.containsAll(b) && b.containsAll(a));
    }

    private boolean rangeEquals(ApprovalDocRangeNumberDTO a, ApprovalDocRangeNumberDTO b) {
        // 只要有一个为空，该字段一定重复
        if(a==null||b==null) {
            return true;
        }
        // 只要有一个范围为空，该字段一定重复
        if(StringUtils.isBlank(a.getFrom()) || StringUtils.isBlank(a.getTo())
                || StringUtils.isBlank(b.getFrom()) || StringUtils.isBlank(b.getTo())) {
            return true;
        }
        if (a == b) return true;

        // 如果两边max、min分别包含的情况
        if( (a.obtainsBooMin() && b.obtainsBooMax())){
            return b.obtainsDoubleMin()<a.obtainsDoubleMax() && b.obtainsDoubleMax()>=a.obtainsDoubleMin();
        }else if(a.obtainsBooMax() && b.obtainsBooMin()){
            return b.obtainsDoubleMin()<=a.obtainsDoubleMax() && b.obtainsDoubleMax()>a.obtainsDoubleMin();
        }

        return b.obtainsDoubleMin()<a.obtainsDoubleMax() && b.obtainsDoubleMax()>a.obtainsDoubleMin();
    }
/*

    public static void main(String[] args) {
        ApprovalDocNonPriceDTO dto = new ApprovalDocNonPriceDTO();
        // 不相交 false
        System.out.println(dto.test1());
        // 香蕉 true
        System.out.println(dto.test2());
        // 边缘香蕉 true
        System.out.println(dto.test3());
        // 边缘相同但不香蕉 false
        System.out.println(dto.test4());
    }

    // 边缘相同但不香蕉
    private boolean test4(){
        ApprovalDocRangeNumberDTO a = new ApprovalDocRangeNumberDTO();
        a.setFrom("0");
        a.setTo("10");
        a.setIsMinIncludes(String.valueOf(true));
        a.setIsMaxIncludes(String.valueOf(true));
        ApprovalDocRangeNumberDTO b = new ApprovalDocRangeNumberDTO();
        b.setFrom("10");
        b.setTo("15");
        b.setIsMinIncludes(String.valueOf(false));
        b.setIsMaxIncludes(String.valueOf(true));
        return rangeEquals(a, b);
    }

    // 边缘相交
    private boolean test3(){
        ApprovalDocRangeNumberDTO a = new ApprovalDocRangeNumberDTO();
        a.setFrom("0");
        a.setTo("10");
        a.setIsMinIncludes(String.valueOf(false));
        a.setIsMaxIncludes(String.valueOf(true));
        ApprovalDocRangeNumberDTO b = new ApprovalDocRangeNumberDTO();
        b.setFrom("10");
        b.setTo("15");
        b.setIsMinIncludes(String.valueOf(true));
        b.setIsMaxIncludes(String.valueOf(false));
        return rangeEquals(a, b);
    }

    // 不相交
    private boolean test2(){
        ApprovalDocRangeNumberDTO a = new ApprovalDocRangeNumberDTO();
        a.setFrom("0");
        a.setTo("10");
        a.setIsMinIncludes(String.valueOf(true));
        a.setIsMaxIncludes(String.valueOf(true));
        ApprovalDocRangeNumberDTO b = new ApprovalDocRangeNumberDTO();
        b.setFrom("5");
        b.setTo("15");
        b.setIsMinIncludes(String.valueOf(true));
        b.setIsMaxIncludes(String.valueOf(true));
        return rangeEquals(a, b);
    }

    // 不相交
    private boolean test1(){
        ApprovalDocRangeNumberDTO a = new ApprovalDocRangeNumberDTO();
        a.setFrom("0");
        a.setTo("5");
        a.setIsMinIncludes(String.valueOf(true));
        a.setIsMaxIncludes(String.valueOf(true));
        ApprovalDocRangeNumberDTO b = new ApprovalDocRangeNumberDTO();
        b.setFrom("10");
        b.setTo("15");
        b.setIsMinIncludes(String.valueOf(true));
        b.setIsMaxIncludes(String.valueOf(true));
        return rangeEquals(a, b);
    }
*/

    @Override
    public int hashCode() {
        return Objects.hash(schemeNo, vehicleUsage, vehicleTypeCode, vehicleTypeName, modelCode, modelName, isNewPower, newRenewal, brandCode, brandName, carSeriesCode, carSeriesName, customerRiskRange, truckModelVehicleLevel, threeScoreChd, rangeNCD, startWithLicense, vehicleAgeMax, purchasePriceRange, productSchemeDTO);
    }

}
