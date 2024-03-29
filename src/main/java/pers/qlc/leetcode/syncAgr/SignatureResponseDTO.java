package pers.qlc.leetcode.syncAgr;

import lombok.Data;
import pers.qlc.leetcode.dto.response.SignatureResponseDataDTO;

/**
 * @Author QLC
 * @Date 2022/12/8 19:01
 * @Description
 */
@Data
public class SignatureResponseDTO {

    /**
     * 返回状态
     */
    private Integer code;

    /**
     * 返回提示消息
     */
    private String msg;

    /**
     * 返回数据体
     */
    private SignatureResponseDataDTO data;

}
