package leetcode;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pers.qlc.leetcode.ApplicationStartUp;
import pers.qlc.leetcode.constant.HttpConstant;
import pers.qlc.leetcode.dto.AgreementDTO;
import pers.qlc.leetcode.dto.request.PartnerProtocolRequestDTO;
import pers.qlc.leetcode.dto.request.ProtocolRequestDataDTO;
import pers.qlc.leetcode.dto.request.SignatureRequestDTO;
import pers.qlc.leetcode.dto.response.PartnerAgrSyncResponseDTO;
import pers.qlc.leetcode.dto.response.SignatureResponseDTO;
import pers.qlc.leetcode.model.DeskModel;
import pers.qlc.leetcode.service.IDeskService;
import pers.qlc.leetcode.util.ExcelUtils;

import java.io.File;
import java.util.List;

/**
 * @Author QLC
 * @Date 2022/12/27 11:01
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationStartUp.class)
class ApplicationStartUpTest {

    @Autowired
    private IDeskService deskService;

    /**
     * 获取restTemplate
     */
    private final RestTemplate restTemplate = new RestTemplate();

    /**
     * Excel文件地址
     */
    private static final String PATH_NAME = "E:\\xxx.xlsx";

    /**
     * 签名地址
     */
    private static final String SIGNATURE_URL = HttpConstant.PREFIX + HttpConstant.PROD_HOST + HttpConstant.SIGNATURE;

    /**
     * 同步协议地址
     */
    private static final String PROTOCOL_URL = HttpConstant.PREFIX + HttpConstant.PROD_HOST + HttpConstant.PROTOCOL;

    /**
     * 批量同步协议到议价平台
     */
    @Test
    public void syncAgreement() {
        // 解析Excel
        List<AgreementDTO> agreementList = ExcelUtils.readExcelWithCheck(
                AgreementDTO.class,
                new File(PATH_NAME),
                (t, value) -> ((AgreementDTO) t).setResult(value),
                (t, value) -> ((AgreementDTO) t).setFailReason(value)
        );

        // 遍历Excel数据
        for (AgreementDTO agreement : agreementList) {
            // 获取签名
            SignatureRequestDTO signatureRequestDTO = new SignatureRequestDTO(100);
            ResponseEntity<SignatureResponseDTO> response = restTemplate.postForEntity(SIGNATURE_URL, signatureRequestDTO, SignatureResponseDTO.class);
            if (response.getBody() == null) {
                log.info("获取签名失败！");
                return;
            }

            // 填充签名数据
            PartnerProtocolRequestDTO protocolRequestDTO = new PartnerProtocolRequestDTO();
            protocolRequestDTO.setHost(response.getBody().getData().getHost());
            protocolRequestDTO.setPolicy(response.getBody().getData().getPolicy());
            protocolRequestDTO.setAccessKey(response.getBody().getData().getAccessKey());
            protocolRequestDTO.setSignature(response.getBody().getData().getSignature());
            protocolRequestDTO.setExpiretime(response.getBody().getData().getExpiretime());

            // 填充合约数据
            ProtocolRequestDataDTO data = new ProtocolRequestDataDTO();
            data.setCarCount(1);
            data.setOrgcd(agreement.getOrgCode());
            data.setPartnerCode(agreement.getRoleId());
            data.setPartnerName(agreement.getPartnerName());
            data.setAgentProtocolCode(agreement.getAgreementNo());
            data.setEndDate(agreement.getDateEnd().substring(0, 10));
            data.setStartDate(agreement.getDateStart().substring(0, 10));
            protocolRequestDTO.setData(data);

            // 同步到议价
            log.info("同步合作伙伴协议信息到议价平台, request: {}", JSON.toJSONString(protocolRequestDTO, true));
            ResponseEntity<PartnerAgrSyncResponseDTO> postForEntity = restTemplate.postForEntity(PROTOCOL_URL, protocolRequestDTO, PartnerAgrSyncResponseDTO.class);
            log.info("同步合作伙伴协议信息到议价平台, response:{}", JSON.toJSONString(postForEntity.getBody(), true));
        }
    }

    /**
     * 数据库测试方法
     */
    @Test
    public void dbTest() {
        LambdaQueryWrapper<DeskModel> deskQueryWrapper = new LambdaQueryWrapper<>();
        deskQueryWrapper.eq(DeskModel::getDeskId, 1);
        List<DeskModel> list = deskService.list(deskQueryWrapper);
        System.out.println(list);
    }
}
