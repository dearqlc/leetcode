import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.lang.StringUtils;

/**
 * @Author QLC
 * @Date 2023/2/2 15:20
 * @Description
 */
@Getter
@AllArgsConstructor
public enum UsageStatus {

    USED("Y", "使用中"),
    UNUSED("N", "未使用");

    private String code;
    private String desc;

    public static UsageStatus getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }
        for (UsageStatus value : UsageStatus.values()) {
            if (value.getCode().equals(code)) {
                return value;
            }
        }
        return null;
    }

}
