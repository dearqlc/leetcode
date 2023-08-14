package pers.qlc.leetcode.dto.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApprovalDocImageDTO {

    /**
     * 影像类型：许可证影像，身份证影像，其他影像...
     */
    private Integer imgTypeCode;

    /**
     * 影像名称
     */
    private String imgName;

    /**
     * 影像ossId
     */
    private String imgOssId;

    /**
     * 影像描述
     */
    private String imgDescription;

}
