package pers.qlc.leetcode.dto.response;

import lombok.Data;

/**
 * @Author: GW
 * @Date: 2022/11/14 15:40
 * Content:
 */
@Data
public class ServiceResponseAppDTO {
    /**
     * 服务代码，例如C001
     */
    private String serviceCode;
    /**
     * 服务代码对应的名字，例如洗车
     */
    private String serviceName;
    /**
     * 对于服务的文字描述
     */
    private String serviceDescription;
    /**
     * 扩展信息
     */
    private String serviceExtendInfo;

}