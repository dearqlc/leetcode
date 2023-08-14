package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class DocumentResponseAppDTO {
    /**
     * 文件在OSS上的ID号
     */
    private String fileOssId;
    /**
     * 文件名，含后缀，e.g. 照片1.jpg
     */
    private String fileName;
    /**
     * 文档存储路径
     */
    private String filePath;
    /**
     * 合约的分类信息
     */
    private List<String> tags;
}