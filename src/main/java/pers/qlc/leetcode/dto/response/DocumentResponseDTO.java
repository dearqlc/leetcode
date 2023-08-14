package pers.qlc.leetcode.dto.response;

import lombok.Data;

import java.util.List;

/**
 * @Author: GW
 * @Date: 2022/9/20 11:41
 * Content:
 */
@Data
public class DocumentResponseDTO {
    private String fileOssId;
    private String fileName;
    private String filePath;
    private List<String> tags;
}
