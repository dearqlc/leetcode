package pers.qlc.leetcode.dto.param;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

@Data
@EqualsAndHashCode
public class ApprovalDocRangeNumberDTO {

    /**
     * 从什么值
     */
    private String from;
    /**
     * 至什么值
     */
    private String to;
    /**
     * （不含或包含）
     */
    private Boolean isMinIncludes;
    /**
     * （不含或包含）
     */
    private Boolean isMaxIncludes;

    public double obtainsDoubleMin(){
        return StringUtils.isBlank(from)?Double.MIN_VALUE:Double.parseDouble(from);
    }

    public double obtainsDoubleMax(){
        return StringUtils.isBlank(to)?Double.MAX_VALUE:Double.parseDouble(to);
    }

    public boolean obtainsBooMin(){
        return isMinIncludes;
    }

    public boolean obtainsBooMax(){
        return isMaxIncludes;
    }
}
