package pers.qlc.leetcode.dto.response;

import lombok.Data;
import pers.qlc.leetcode.dto.EcologyQueryDTO;

import java.util.List;

@Data
public class EcologyQueryResponseDTO {
    private List<EcologyQueryDTO> data;
}
