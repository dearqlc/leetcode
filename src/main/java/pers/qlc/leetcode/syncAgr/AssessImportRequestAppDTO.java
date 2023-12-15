package pers.qlc.leetcode.syncAgr;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * @Author :QLC
 * @Date :2023/12/13 14:35
 * @Description :
 */
@Data
public class AssessImportRequestAppDTO {

    /**
     * ossId
     */
    @NotBlank(message = "ossId不能为空")
    private String ossId;

    /**
     * 公估师列表(页面上已有的公估师)
     */
    private List<AssessorRequestAppDTO> assessorRequestDTOList;

}
