package pers.qlc.leetcode.dto.param;

import lombok.Data;

import java.util.List;

@Data
public class ApprovalDocclauseDTO {
    private String clauseCode;
    private String clauseName;
    private String optionalType;
    private String insuredUpperLimit;
    private String insuredLowerLimit;
    private String days;
    private String times;
    private String insuredAmount;
    private String absoluteDeductibleAmount;
    private String absoluteDeductibleRate;
    private String numberOfPeople;
    private String dayLimitAmount;
    private String isMainClause;
    private String coverageCode;
    private String coverageDisplayName;
    private String absoluteDeductibleAmountDisable;
    private List<ApprovalDocLabelValueDTO> absoluteDeductibleAmountEnum;
    private String absoluteDeductibleAmountIsNeed;
    private String absoluteDeductibleRateDisable;
    private List<ApprovalDocLabelValueDTO> absoluteDeductibleRateEnum;
    private String absoluteDeductibleRateIsNeed;
    private String absoluteDeductibleRatePremium;
    private String addEquipmentIsNeed;
    private String addServiceNumber;
    private List<ApprovalDocCoverageDTO> additionalCoverageList;
    private String afterDiscountPremium;
    private String baseLimit;
    private String canCheckFlag;
    private String chargingPileIsNeed;
    private String checkFlag;
    private String coverageName;
    private String coverageType;
    private String dayLimitAmountDisable;
    private String dayLimitAmountIsNeed;
    private String daysDisable;
    private String daysEnum;
    private String daysIsNeed;
    private String endorsementOptionalType;
    private String insuredAmountDisable;
    private List<ApprovalDocLabelValueDTO> insuredAmountEnum;
    private String insuredAmountIsNeed;
    private List<ApprovalDocLabelValueDTO> limitAmountEnum;
    private String limitAmountIsNeed;
    private String lowerLimit;
    private List<String> majorCoverageCode;
    private String numberOfPeopleIsNeed;
    private List<String> relevantMainClauses;
    private String sequenceNo;
    private List<String> subCoverageList;
    private String temp;
    private String tempDisable;
    private String tempList;
    private String templimit;
    private String timesDisable;
    private List<ApprovalDocLabelValueDTO> timesEnum;
    private String timesIsNeed;
    private String upperLimit;
    private String limitAmount;
    private String mainInsuranceCode;
    private String mainInsuranceOptionalType;
}
